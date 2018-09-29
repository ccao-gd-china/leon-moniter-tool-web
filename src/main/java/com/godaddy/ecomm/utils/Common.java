package com.godaddy.ecomm.utils;

import java.text.DecimalFormat;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Class to provide common tool methods
 */
public class Common {

  public static String prettyXML(String xmlString, int indent) {
    try {
      // Turn xml string into a document
      Document document = DocumentBuilderFactory.newInstance()
          .newDocumentBuilder()
          .parse(new InputSource(new ByteArrayInputStream(xmlString.getBytes("utf-8"))));

      // Remove whitespaces outside tags
      document.normalize();
      XPath xPath = XPathFactory.newInstance().newXPath();
      NodeList nodeList = (NodeList) xPath.evaluate("//text()[normalize-space()='']",
          document,
          XPathConstants.NODESET);

      for (int i = 0; i < nodeList.getLength(); ++i) {
        Node node = nodeList.item(i);
        node.getParentNode().removeChild(node);
      }

      // Setup pretty print options
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      transformerFactory.setAttribute("indent-number", indent);
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");

      // Return pretty print xml string
      StringWriter stringWriter = new StringWriter();
      transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
      return breakLine(stringWriter.toString());

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static String breakLine(String formattedXml) {
    String[] strings = formattedXml.split("\\n");
    StringBuilder sb = new StringBuilder();
    for (String string : strings) {
      if (string.length() > 70) {
        String[] strs = string.split(" ");
        String newline = "";
        for (int i = 0; i < strs.length; i++) {
          if (strs[i].length() == 0) {
            sb.append(" ");
          } else {
            sb.append(strs[i] + " ");
          }

          newline += strs[i];
          if (newline.length() > 100) {
            sb.append(" \r\n");
            newline = "";
          } else {
            if ((i != strs.length - 1) && ((newline + strs[i + 1]).length() > 100)) {
              sb.append(" \r\n");
              newline = "";
            }
          }

        }

      } else {
        sb.append(string + "\n");
      }
    }
    return sb.toString();
  }

  /**
   * method to increase days for a given string date.
   *
   * @param datestr string date.
   * @param amount the amount of date to be added.
   */
  public static String increaseDate(String datestr, int amount) throws ParseException {
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(formatter.parse(datestr));
    calendar.add(Calendar.DATE, amount);

    return formatter.format(calendar.getTime());
  }

  public static String getUTC_Date(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss 'UTC'");
    return formatter.format(date);
  }

  public static String escapeTime(String str) {
    if(str==null){
      return "";
    }
    return str.substring(0,10)+"%20"+str.substring(11,19);
  }

  public static String get_spent_time(Date start_date){
    Date after = new Date();
    long l = after.getTime() - start_date.getTime();
    long day = l / (24 * 60 * 60 * 1000);
    long hour = (l / (60 * 60 * 1000) - day * 24);
    long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
    long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
    return "time spent: " + min + " minutes," + s + " seconds";
  }

  public static Date formatToDate(String date_entered) throws ParseException {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    return dateFormat.parse(date_entered);
  }

  public static Date add_minutes(Date date,int n){
    return new Date(date.getTime() + n * 60 * 1000);
  }

  public static String formatDateToString(Date date) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return dateFormat.format(date);
  }

  public static String get_num_with_2_decimal(double d){
    DecimalFormat df = new DecimalFormat("0.0");
    return df.format(d);
  }
}
