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

@WebServlet("/CPLAutorenewalServlet")
public class CPLAutorenewalServlet extends HttpServlet {

  private static int count = 1;
  private CPLService cplService = new CPLService();

  private Logger logger = LoggerFactory.getLogger(CPLAutorenewalServlet.class);
  private String snapshot_id;
  private String startDate;
  private String endDate;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String queryString = req.getQueryString();
    if (queryString != null) {
      try {
        snapshot_id = req.getParameter("snapshot_id");
        startDate = req.getParameter("startDate");
        endDate = req.getParameter("endDate");
      } catch (Exception e) {
        logger.error(e.getMessage());
      }
      resp.sendRedirect(req.getContextPath() + "/CPLAutorenewalServlet");
    } else {
      getServletContext().getRequestDispatcher("/WEB-INF/cplGateWaySelectionFailureForAutorenewal.html")
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

      if (!queryStr.equals("")) {
        if (queryStr.contains("latest")) {
          destroyQueryFields();
          cplQueryVo = initCPLQueryVo2(req);
          purchaseLogs = cplService.getCPLAutorenewalByLatestSnapshotId(cplQueryVo);
          recordsTotal = cplService.getCPLAutorenewalCountByLatestSnapshotId();
        } else {
          destroyQueryFields();
          cplQueryVo = initCPLQueryVo2(req);
          purchaseLogs = cplService.getALLCPLAutorenewalLogsByPeriod(queryStr, cplQueryVo);
          recordsTotal = cplService.getCountByPeriod("CPLAutorenewalLog", queryStr, cplQueryVo);
        }
      } else {
        cplQueryVo = initCPLQueryVo2(req);
        purchaseLogs = cplService.getALLCPLAutorenewalLogs(cplQueryVo);
        recordsTotal = cplService.getCount("CPLAutorenewalLog",cplQueryVo);
      }
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

  private CPLQueryVo initCPLQueryVo(HttpServletRequest req) {
    CPLQueryVo cplQueryVo = new CPLQueryVo();

    //Parameters from jquery plug-in 'DataTables'.
    int startIndex = Integer.parseInt(req.getParameter("start"));
    int amount = Integer.parseInt(req.getParameter("length"));
    cplQueryVo.setStartIndex(startIndex);
    cplQueryVo.setAmount(amount);

    if (snapshot_id != null && !snapshot_id.contains("all_CPL_logs")) {//is strings
      cplQueryVo.setSnapshot_id(Integer.parseInt(snapshot_id));
    }
    if (startDate != null) {
      cplQueryVo.setStartDate(startDate);
    }
    if (endDate != null) {
      cplQueryVo.setEndDate(endDate);
    }

    return cplQueryVo;
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
    snapshot_id = null;
    startDate = null;
    endDate = null;
  }

}
