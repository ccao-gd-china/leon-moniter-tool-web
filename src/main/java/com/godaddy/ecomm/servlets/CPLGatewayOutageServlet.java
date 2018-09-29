package com.godaddy.ecomm.servlets;

import com.godaddy.ecomm.base.cpl.CPLGatewaySelectionFailureForAutorenewal;
import com.godaddy.ecomm.base.cpl.CPLQueryVo;
import com.godaddy.ecomm.base.cpl.CommonPurchaseLogWithSomeKeyFields;
import com.godaddy.ecomm.service.CPLService;
import com.godaddy.ecomm.utils.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/cpl_gateway_outage_servlet")
public class CPLGatewayOutageServlet extends HttpServlet {

  private static int count = 1;
  private CPLService cplService = new CPLService();

  private Logger logger = LoggerFactory.getLogger(CPLGatewayOutageServlet.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String queryString = req.getQueryString();
    if (queryString != null) {
      resp.sendRedirect(req.getContextPath() + "/cpl_gateway_outage_servlet");
    } else {
      getServletContext().getRequestDispatcher("/WEB-INF/cpl_gateway_outage.html")
          .forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    CPLQueryVo cplQueryVo;
    List<CommonPurchaseLogWithSomeKeyFields> purchaseLogs;
    int recordsTotal;
    int recordsFiltered;

    try {
      //Parameter from jquery plug-in 'DataTables'.
      String queryStr = req.getParameter("search[value]");

      cplQueryVo = initCPLQueryVo2(req);
      purchaseLogs = cplService.getGatewayOutageDatas(queryStr , cplQueryVo);
      recordsTotal = cplService.getCount("monitor_cpl_payment_gateway_outage",cplQueryVo);

      recordsFiltered = recordsTotal;
      resp.setContentType("application/json");
      resp.setCharacterEncoding("utf-8");
      //create Json Object
      JSONObject jsonObject;
      if (purchaseLogs != null && purchaseLogs.size() != 0) {
        jsonObject = JsonParser.mapToJson("data", purchaseLogs);
        jsonObject.put("recordsTotal", recordsTotal);
        jsonObject.put("recordsFiltered", recordsFiltered);

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

  private CPLQueryVo initCPLQueryVo(HttpServletRequest req) {
    CPLQueryVo cplQueryVo = new CPLQueryVo();

    //Parameters from jquery plug-in 'DataTables'.
    int startIndex = Integer.parseInt(req.getParameter("start"));
    int amount = Integer.parseInt(req.getParameter("length"));
    cplQueryVo.setStartIndex(startIndex);
    cplQueryVo.setAmount(amount);

    return cplQueryVo;
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
