package com.godaddy.ecomm.servlets;

import com.godaddy.ecomm.base.cpl.CPLGatewaySelectionFailureForAutorenewal;
import com.godaddy.ecomm.base.cpl.CPLQueryVo;
import com.godaddy.ecomm.service.CPLService;
import com.godaddy.ecomm.utils.JsonParser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/cpl_gateway_selection_failure_details")
public class cpl_gateway_selection_failure_details extends HttpServlet {

    private static int count = 1;
    private CPLService cplService = new CPLService();

    private Logger logger = LoggerFactory.getLogger(cpl_gateway_selection_failure_details.class);
    private String snapshot_id;
    private String startDate;
    private String endDate;
    private String order_id;
    private String shopper_id;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String queryString = req.getQueryString();
        if (queryString != null) {
            try {
                snapshot_id = req.getParameter("snapshot_id");
                startDate = req.getParameter("startDate");
                endDate = req.getParameter("endDate");
                order_id = req.getParameter("order_id");
                shopper_id = req.getParameter("shopper_id");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            resp.sendRedirect(req.getContextPath() + "/cpl_gateway_selection_failure_details");
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/cplGateWaySelectionFailureForAutorenewal_details.html")
                    .forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        CPLQueryVo cplQueryVo;
        List<CPLGatewaySelectionFailureForAutorenewal> purchaseLogs;
        int recordsTotal;
        int recordsFiltered;

        try {
            //Parameter from jquery plug-in 'DataTables'.
            String queryStr = req.getParameter("search[value]");

            cplQueryVo = initCPLQueryVo2(req);
            purchaseLogs = cplService.get_cpl_gateway_selection_failure_details_logs(cplQueryVo, order_id, shopper_id);
            recordsTotal = cplService.get_cpl_gateway_selection_failure_details_count("CPLAutorenewalLog", cplQueryVo, order_id, shopper_id);

            recordsFiltered = recordsTotal;
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf-8");
            //create Json Object
            JSONObject jsonObject;
            if (purchaseLogs != null && purchaseLogs.size() != 0) {
                jsonObject = JsonParser.mapToJson("data", purchaseLogs);
                jsonObject.put("recordsTotal", recordsTotal);
                jsonObject.put("recordsFiltered", recordsFiltered);
                jsonObject.put("snapshotId", purchaseLogs.get(0).getSnapshot_Id());

                if (startDate != null) {
                    jsonObject.put("startDate", startDate);
                }
                if (endDate != null) {
                    jsonObject.put("endDate", endDate);
                }
            } else {
                jsonObject = new JSONObject();
                jsonObject.put("data", "");
                jsonObject.put("recordsTotal", 0);
                jsonObject.put("recordsFiltered", 0);
            }

            jsonObject.put("draw", count++);

            PrintWriter out = resp.getWriter();
            out.print(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private CPLQueryVo initCPLQueryVo2(HttpServletRequest req) {
        CPLQueryVo cplQueryVo = new CPLQueryVo();

        //Parameters from jquery plug-in 'DataTables'.
        int startIndex = Integer.parseInt(req.getParameter("start"));
        int amount = Integer.parseInt(req.getParameter("length"));
        cplQueryVo.setStartIndex(startIndex);
        cplQueryVo.setAmount(amount);

        return cplQueryVo;
    }

}
