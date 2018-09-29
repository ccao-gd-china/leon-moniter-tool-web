package com.godaddy.ecomm.servlets;

import com.godaddy.ecomm.service.CPLService;
import com.godaddy.ecomm.service.FulfillmentService;
import com.godaddy.ecomm.utils.Common;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index_servlet")
public class index_servlet extends HttpServlet {

  private FulfillmentService fulfillmentService = new FulfillmentService();
  private CPLService cplService = new CPLService();

  private Logger logger = LoggerFactory.getLogger(index_servlet.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException {

    JSONObject jsonObject = new JSONObject();
    String lastUpdateTime = "";

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String now_time = df.format(new Date());

    Calendar calendar1 = Calendar.getInstance();
    calendar1.add(Calendar.DATE, -3);
    String three_days_ago = df.format(calendar1.getTime());

    //Reseller API fulfillment:
    {
      try {
        lastUpdateTime = cplService.getLastUpdateTime("collect_time_of_fulfillment_for_index_page");
        jsonObject.put("fulfillment_time", lastUpdateTime.substring(0, 19));
      } catch (Exception e) {
        e.printStackTrace();
      }
      int fulfillment_count = 0;
      try {
        fulfillment_count = cplService.getIndexCount("errorOrderRequestXml");
      } catch (Exception e) {
        e.printStackTrace();
      }

      if (fulfillment_count == 0) {
        jsonObject.put("fulfillment_count", 0);
      } else {
        jsonObject.put("fulfillment_count", fulfillment_count);
        String start_time = "";
        String end_time = "";
        int snapshot_id = 0;
        try {
          start_time = cplService.getTime("errorOrderRequestXml", "start_time");
          end_time = cplService.getTime("errorOrderRequestXml", "end_time");
          snapshot_id = fulfillmentService.getLatestSnapshot_id();
        } catch (Exception e) {
          e.printStackTrace();
        }
        String link = getLink("FulfillmentServlet", snapshot_id + "", start_time, end_time);
        jsonObject.put("fulfillment_link", link);
      }
    }

    //Incorrect gateway action type for BA:
    {
      try {
        lastUpdateTime = cplService
          .getLastUpdateTime("collect_time_of_cpl_incorrect_gateway_for_index_page");
      } catch (Exception e) {
        e.printStackTrace();
      }
      int incorrect_gateway_count = 0;
      try {
        incorrect_gateway_count = cplService.getIndexCount("commonPurchaseLog");
      } catch (Exception e) {
        e.printStackTrace();
      }
      jsonObject.put("incorrect_gateway_time", lastUpdateTime.substring(0, 19));
      if (incorrect_gateway_count == 0) {
        jsonObject.put("incorrect_gateway_count", 0);
        String link = getLink("CPLServlet", "all_CPL_logs", now_time, three_days_ago);
        jsonObject.put("incorrect_gateway_link", link);
      } else {
        jsonObject.put("incorrect_gateway_count", incorrect_gateway_count);
        String start_time = "";
        String end_time = "";
        try {
          start_time = cplService.getTime("commonPurchaseLog", "start_time");
          end_time = cplService.getTime("commonPurchaseLog", "end_time");
        } catch (Exception e) {
          e.printStackTrace();
        }
        String link = getLink("CPLServlet", "all_CPL_logs", start_time, end_time);
        jsonObject.put("incorrect_gateway_link", link);
      }
    }

    //Duplicate ISC refund:
    {
      try {
        lastUpdateTime = cplService
          .getLastUpdateTime("collect_time_of_cpl_duplicate_isc_refund_for_index_page");
      } catch (Exception e) {
        e.printStackTrace();
      }
      int duplicate_ISC_count = 0;
      try {
        duplicate_ISC_count = cplService.getIndexCount("CPLISCRefundLog");
      } catch (Exception e) {
        e.printStackTrace();
      }
      jsonObject.put("duplicate_ISC_time", lastUpdateTime.substring(0, 19));
      if (duplicate_ISC_count == 0) {
        jsonObject.put("duplicate_ISC_count", 0);
        String link = getLink("CPLISCServlet", "all_CPLISCRefund_Logs", now_time, three_days_ago);
        jsonObject.put("duplicate_ISC_link", link);
      } else {
        jsonObject.put("duplicate_ISC_count", duplicate_ISC_count);
        String start_time = "";
        String end_time = "";
        try {
          start_time = cplService.getTime("CPLISCRefundLog", "start_time");
          end_time = cplService.getTime("CPLISCRefundLog", "end_time");
        } catch (Exception e) {
          e.printStackTrace();
        }
        String link = getLink("CPLISCServlet", "all_CPLISCRefund_Logs", start_time, end_time);
        jsonObject.put("duplicate_ISC_link", link);
      }
    }

    //Gateway selection failure:
    {
      try {
        lastUpdateTime = cplService
          .getLastUpdateTime("collect_time_of_cpl_gateway_selection_failure_for_index_page");
      } catch (Exception e) {
        e.printStackTrace();
      }
      int gateway_selection_failure_count = 0;
      try {
        gateway_selection_failure_count = cplService.getIndexCount("CPLAutorenewalLog");
      } catch (Exception e) {
        e.printStackTrace();
      }
      jsonObject.put("gateway_selection_failure_time", lastUpdateTime.substring(0, 19));
      if (gateway_selection_failure_count == 0) {
        jsonObject.put("gateway_selection_failure_count", 0);
        String link = getLink("CPLAutorenewalServlet", "CPLAutorenewal", now_time, three_days_ago);
        jsonObject.put("gateway_selection_failure_link", link);
      } else {
        jsonObject.put("gateway_selection_failure_count", gateway_selection_failure_count);
        String start_time = "";
        String end_time = "";
        try {
          start_time = cplService.getTime("CPLAutorenewalLog", "start_time");
          end_time = cplService.getTime("CPLAutorenewalLog", "end_time");
        } catch (Exception e) {
          e.printStackTrace();
        }
        String link = getLink("CPLAutorenewalServlet", "CPLAutorenewal", start_time, end_time);
        jsonObject.put("gateway_selection_failure_link", link);
      }
    }

    //auto-refund monitor:
    {
      try {
        lastUpdateTime = cplService.getLastUpdateTime("collect_time_of_cpl_auto_refund");
      } catch (Exception e) {
        e.printStackTrace();
      }
      String auto_refund_str = "" ;
      try {
        auto_refund_str = cplService.get_auto_refund_str(lastUpdateTime);
      } catch (Exception e) {
        e.printStackTrace();
      }
      jsonObject.put("auto_refund_time", lastUpdateTime.substring(0, 19));
      if (auto_refund_str.length() < 1) {
        jsonObject.put("auto_refund_str", "");

        String link = getLink("cpl_auto_refund_servlet",null,now_time,three_days_ago);

        jsonObject.put("auto_refund_link", link);
      } else {
        jsonObject.put("auto_refund_str", auto_refund_str.split("#&")[0]);
        jsonObject.put("auto_refund_link", auto_refund_str.split("#&")[1]);
      }
    }

    //Gateway outage failure:
    {
      try {
        lastUpdateTime = cplService
          .getLastUpdateTime("collect_time_of_cpl_gateway_outage_for_index_page");
      } catch (Exception e) {
        e.printStackTrace();
      }
      jsonObject.put("gateway_outage_time", lastUpdateTime.substring(0, 19));

      String num ="" ;
      try {
        num = cplService.get_data_from_db_usefulDate("home_page_outage_num");
      } catch (Exception e) {
        e.printStackTrace();
      }

      int total_num = Integer.parseInt(num.split("/")[0]);
      int success_num = Integer.parseInt(num.split("/")[1]);

      if (total_num*0.2 < success_num) {
        jsonObject.put("gateway_outage_str", "");
      } else {
        jsonObject.put("gateway_outage_str", "00");
      }

      String link = getLink("cpl_gateway_outage_servlet", "", null, null);
      jsonObject.put("gateway_outage_link", link);
    }

    Date date = new Date();
    //exception monitor:
    {
      try {
        lastUpdateTime = cplService.getLastUpdateTime("allCPLRows_time");
        jsonObject.put("exception_monitor_time", lastUpdateTime.substring(0, 19));
      } catch (Exception e) {
        e.printStackTrace();
      }
      String exception_monitor_str = "" ;
      try {
        exception_monitor_str = cplService.get_exception_monitor_str();
      } catch (Exception e) {
        e.printStackTrace();
      }
      if (exception_monitor_str.length() < 1) {
        jsonObject.put("exception_monitor_str", "");

        String link = getLink("cpl_exception_monitor_servlet","all_CPL_logs",now_time,three_days_ago);
        jsonObject.put("exception_monitor_link", link);

      } else {
        jsonObject.put("exception_monitor_str", exception_monitor_str);
      }
    }
    logger.debug(Common.get_spent_time(date));

    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    response.getWriter().write(jsonObject.toString());
  }

  private String getLink(String servlet, String snapshot_id, String start_time, String end_time) {
    return "http://paymentmon.cloud.phx3.gdg/MonitorTool/" + servlet + "?snapshot_id=" + snapshot_id
      + "&startDate=" + Common.escapeTime(start_time) + "&endDate=" + Common.escapeTime(end_time);
  }

}
