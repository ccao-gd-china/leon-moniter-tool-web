package com.godaddy.ecomm.servlets;

import com.godaddy.ecomm.base.cpl.AutoRefund;
import com.godaddy.ecomm.base.cpl.CPLQueryVo;
import com.godaddy.ecomm.base.cpl.CommonPurchaseLogWithSomeKeyFields;
import com.godaddy.ecomm.service.CPLService;
import com.godaddy.ecomm.utils.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/cpl_auto_refund_servlet")
public class cpl_auto_refund_servlet extends HttpServlet {

  private static int count = 1;
  private CPLService cplService = new CPLService();

  private Logger logger = LoggerFactory.getLogger(cpl_auto_refund_servlet.class);
  private String startDate;
  private String endDate;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    String queryString = req.getQueryString();
    if (queryString != null) {
      try {
        startDate = req.getParameter("startDate");
        endDate = req.getParameter("endDate");
      } catch (Exception e) {
        logger.error(e.getMessage());
      }
      getServletContext().getRequestDispatcher("/WEB-INF/cpl_auto_refund_monitor.html")
        .forward(req, resp);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {

    CPLQueryVo cplQueryVo;
    List<AutoRefund> auto_refund_list;
    int recordsTotal;
    int recordsFiltered;

    try {
      //Parameter from jquery plug-in 'DataTables'.
      String queryStr = req.getParameter("search[value]");

      if (!queryStr.equals("")) {
        destroyQueryFields();
        cplQueryVo = initCPLQueryVo2(req);
        auto_refund_list = cplService.get_cpl_autorefund_logs_by_period(queryStr,cplQueryVo);
        recordsTotal = cplService.get_cpl_autorefund_logs_count_by_period(queryStr,cplQueryVo);
      } else {
        cplQueryVo = initCPLQueryVo2(req);
        auto_refund_list = cplService.get_cpl_autorefund_logs_by_period(startDate, endDate, cplQueryVo);
        recordsTotal = cplService.get_cpl_autorefund_logs_count_by_period(startDate, endDate, cplQueryVo);
      }
      recordsFiltered = recordsTotal;
      resp.setContentType("application/json");
      resp.setCharacterEncoding("utf-8");
      //create Json Object
      JSONObject jsonObject;
      if (auto_refund_list != null && auto_refund_list.size() != 0) {
        jsonObject = JsonParser.mapToJson("data", auto_refund_list);
        jsonObject.put("recordsTotal", recordsTotal);
        jsonObject.put("recordsFiltered", recordsFiltered);

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

    if (startDate != null) {
      cplQueryVo.setStartDate(startDate);
    }
    if (endDate != null) {
      cplQueryVo.setEndDate(endDate);
    }

    return cplQueryVo;
  }

  private void destroyQueryFields() {
    startDate = null;
    endDate = null;
  }
}
