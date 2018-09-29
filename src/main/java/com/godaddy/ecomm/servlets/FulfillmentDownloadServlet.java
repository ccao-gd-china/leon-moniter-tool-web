package com.godaddy.ecomm.servlets;

import com.godaddy.ecomm.base.fulfillment.ErrorOrderRequestXml;
import com.godaddy.ecomm.base.fulfillment.FulfillmentQueryVo;
import com.godaddy.ecomm.service.FulfillmentService;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/FulfillmentDownloadServlet")
public class FulfillmentDownloadServlet extends HttpServlet {

  private FulfillmentService fulfillmentService = new FulfillmentService();

  private Logger logger = LoggerFactory.getLogger(FulfillmentDownloadServlet.class);


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response){
    FulfillmentQueryVo fulfillmentQueryVo;
    List<ErrorOrderRequestXml> orderRecords;

    fulfillmentQueryVo = initFulfillmentQueryVo(request);
    try {

      orderRecords = fulfillmentService.getErrorOrderRequests(fulfillmentQueryVo);

      response.setContentType("octets/stream");
      //      response.addHeader("Content-Disposition", "attachment;filename=test.xls");
      String excelName = "Reseller Error Order Requests Info";
      response.addHeader("Content-Disposition", "attachment;filename=" + new String(excelName.getBytes("UTF-8"), "UTF-8") + ".xls");
      String[] headers = new String[]{"Order Id", "PLID", "Severity", "Create Date", "Order Status", "Error Message", "Order XML"};
      OutputStream out = response.getOutputStream();
      exportExcel(excelName, headers, getList(orderRecords), out);
      out.close();
    } catch (Exception e) {
      logger.error(e.getLocalizedMessage());
    }
  }

  private FulfillmentQueryVo initFulfillmentQueryVo(HttpServletRequest req) {
    FulfillmentQueryVo fulfillmentQueryVo = new FulfillmentQueryVo();
    fulfillmentQueryVo.setStartIndex(0);
    fulfillmentQueryVo.setAmount(100000);
    if (req.getParameter("filterSeverity").equals("")) {
      fulfillmentQueryVo.setFilterSeverity(null);
    } else {
      fulfillmentQueryVo.setFilterSeverity(req.getParameter("filterSeverity"));
    }

    if (req.getParameter("filterPLID").equals("")) {
      fulfillmentQueryVo.setFilterPLID(null);
    } else {
      fulfillmentQueryVo.setFilterPLID(req.getParameter("filterPLID"));
    }

    if (req.getParameter("filterMessage").trim().equals("")) {
      fulfillmentQueryVo.setFilterMessage(null);
    } else {
      fulfillmentQueryVo.setFilterMessage("%" + req.getParameter("filterMessage") + "%");
    }

    if (req.getParameter("startDate").equals("")) {
      fulfillmentQueryVo.setStartDate(null);
    } else {
      fulfillmentQueryVo.setStartDate(req.getParameter("startDate"));
    }

    if (req.getParameter("endDate").equals("")) {
      fulfillmentQueryVo.setEndDate(null);
    } else {
      fulfillmentQueryVo.setEndDate(req.getParameter("endDate"));
    }

    return fulfillmentQueryVo;
  }


  public List<Map<String, Object>> getList(List<ErrorOrderRequestXml> orderRecords) {
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    for (int i = 0; i < orderRecords.size(); i++) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("OrderId", orderRecords.get(i).getOrder_id());
      map.put("PLID", orderRecords.get(i).getPrivateLabelId());
      map.put("Severity", orderRecords.get(i).getSeverity());
      map.put("CreateDate", orderRecords.get(i).getCreateDate());
      map.put("OrderStatus", orderRecords.get(i).getApi_orderRequestStatusId());
      map.put("ErrorMessage", orderRecords.get(i).getMessageText());
      map.put("OrderXML", orderRecords.get(i).getOrderXML());
      list.add(map);
    }
    return list;
  }

  protected void exportExcel(String title, String[] headers, List mapList, OutputStream out) {
    HSSFWorkbook workbook = new HSSFWorkbook();
    HSSFSheet sheet = workbook.createSheet(title);
    sheet.setDefaultColumnWidth(20);
    HSSFCellStyle style = workbook.createCellStyle();

    HSSFRow row = sheet.createRow(0);
    for (int i = 0; i < headers.length; i++) {
      HSSFCell cell = row.createCell(i);
      cell.setCellStyle(style);
      HSSFRichTextString text = new HSSFRichTextString(headers[i]);
      cell.setCellValue(text);
    }
    for (int i = 0; i < mapList.size(); i++) {
      Map<String, Object> map = (Map<String, Object>) mapList.get(i);
      row = sheet.createRow(i + 1);
      int j = 0;
      Object value = null;
      value = map.get("OrderId");
      if (value instanceof Integer) {
        row.createCell(j++).setCellValue(String.valueOf(value));
      }

      value = map.get("PLID");
      if (value instanceof Integer) {
        row.createCell(j++).setCellValue(String.valueOf(value));
      }

      row.createCell(j++).setCellValue(map.get("Severity").toString());

      row.createCell(j++).setCellValue(map.get("CreateDate").toString());

      value = map.get("OrderStatus");
      if (value instanceof Integer) {
        row.createCell(j++).setCellValue(String.valueOf(value));
      }

      row.createCell(j++).setCellValue(map.get("ErrorMessage").toString());

      row.createCell(j++).setCellValue(map.get("OrderXML").toString());
    }
    try {
      workbook.write(out);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
