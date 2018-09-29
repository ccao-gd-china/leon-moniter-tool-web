package com.godaddy.ecomm.servlets;

import com.godaddy.ecomm.base.fulfillment.ErrorOrderRequestXml;
import com.godaddy.ecomm.base.fulfillment.FulfillmentQueryVo;
import com.godaddy.ecomm.service.FulfillmentService;
import com.godaddy.ecomm.utils.JsonParser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/FulfillmentServlet")
public class FulfillmentServlet extends HttpServlet {

  private static int count = 1;
  private FulfillmentService fulfillmentService = new FulfillmentService();

  private Logger logger = LoggerFactory.getLogger(FulfillmentServlet.class);

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
      resp.sendRedirect("/MonitorTool/FulfillmentServlet");
    } else {
      RequestDispatcher dispatcher = getServletContext()
        .getRequestDispatcher("/WEB-INF/orderRequestXml.html");
      dispatcher.forward(req, resp);
    }
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    FulfillmentQueryVo fulfillmentQueryVo;
    List<ErrorOrderRequestXml> orderRecords;
    int recordsTotal;
    int recordsFiltered;

    try {
      //Parameter from jquery plug-in 'DataTables'.
      String queryStr = req.getParameter("search[value]");
      if (!queryStr.equals("")) {
        if (queryStr.contains("all_existing_error_orders")) {
          destroyQueryFields();
          fulfillmentQueryVo = initFulfillmentQueryVo(req);
          orderRecords = fulfillmentService.getErrorOrderRequests(fulfillmentQueryVo);
          recordsTotal = fulfillmentService.getErrorOrderRequestsNumber(fulfillmentQueryVo);
        } else {
          if (queryStr.contains("latest_error_orders")) {
            destroyQueryFields();
            fulfillmentQueryVo = initFulfillmentQueryVo(req);
            orderRecords = fulfillmentService.getLatestErrorOrderRequests(fulfillmentQueryVo);
            recordsTotal = 1;
          } else {
            // Time search, only time related
            destroyQueryFields();
            fulfillmentQueryVo = initFulfillmentQueryVo(req);
            orderRecords = fulfillmentService.getErrorOrderRequests(queryStr, fulfillmentQueryVo);
            recordsTotal = fulfillmentService.getErrorOrderRequestsNumber(queryStr, fulfillmentQueryVo);
          }
        }
      } else {
        // get request, only time related
        fulfillmentQueryVo = initFulfillmentQueryVo(req);
        orderRecords = fulfillmentService.getErrorOrderRequests(fulfillmentQueryVo);
        recordsTotal = fulfillmentService.getErrorOrderRequestsNumber(fulfillmentQueryVo);
      }
      recordsFiltered = recordsTotal;
      resp.setContentType("application/json");
      resp.setCharacterEncoding("utf-8");
      //create Json Object
      JSONObject jsonObject;
      if (orderRecords != null && orderRecords.size() != 0) {
        jsonObject = JsonParser.mapToJson("data", orderRecords);
        jsonObject.put("recordsTotal", recordsTotal);
        jsonObject.put("recordsFiltered", recordsFiltered);
        //jsonObject.put("snapshotId", orderRecords.get(0).getSnapshot_id());
        jsonObject.put("privateLabelIds",
          fulfillmentService.getAllPrivateLabelId(orderRecords.get(0).getSnapshot_id()));

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
      String jsonStr = jsonObject.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;");

      out.print(jsonStr);

    } catch (Exception e) {
      e.printStackTrace();
    }


  }


  private FulfillmentQueryVo initFulfillmentQueryVo(HttpServletRequest req) {
    FulfillmentQueryVo fulfillmentQueryVo = new FulfillmentQueryVo();

    //Parameters from jquery plug-in 'DataTables'.
    int startIndex = Integer.parseInt(req.getParameter("start"));
    int amount = Integer.parseInt(req.getParameter("length"));
    fulfillmentQueryVo.setStartIndex(startIndex);
    fulfillmentQueryVo.setAmount(amount);

    if (snapshot_id != null) {
      fulfillmentQueryVo.setSnapshot_id(Integer.parseInt(snapshot_id));
    }
    if (startDate != null) {
      fulfillmentQueryVo.setStartDate(startDate);
    }
    if (endDate != null) {
      fulfillmentQueryVo.setEndDate(endDate);
    }

    return fulfillmentQueryVo;
  }

  private void destroyQueryFields() {
    snapshot_id = null;
    startDate = null;
    endDate = null;
  }


}
