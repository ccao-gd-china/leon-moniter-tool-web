package com.godaddy.ecomm.utils;

import com.godaddy.ecomm.base.fulfillment.ErrorOrderRequestXml;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

public class JsonParser {

  public static JSONObject readFile(String filePath, String objName) throws Exception {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(filePath);
    BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
    StringBuilder strBuilder = new StringBuilder();

    String inputStr;
    JSONObject config = null;

    try {
      while ((inputStr = streamReader.readLine()) != null) {
        strBuilder.append(inputStr);
      }
      config = new JSONObject(strBuilder.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return config.getJSONObject(objName);
  }

  public static JSONObject mapToJson(String key, List objects) throws Exception {
    JSONObject json = new JSONObject();
    JSONArray data = new JSONArray();

    for (Object orderRequest : objects) {
      JSONObject jsonOrderRequest = new JSONObject(orderRequest);

      if (orderRequest.getClass().getSimpleName().equals("ErrorOrderRequestXml")) {
        parseOrderRequestXml(jsonOrderRequest, (ErrorOrderRequestXml) orderRequest);
      }

      data.put(jsonOrderRequest);
    }

    json.put(key, data);
    return json;
  }

  private static void parseOrderRequestXml(JSONObject jsonObject,
      ErrorOrderRequestXml orderRequest) {
    String orderXML = jsonObject.getString("orderXML");
    orderXML = Common.prettyXML(orderXML, 2);
    jsonObject.put("orderXML", orderXML);

    Date createDate = orderRequest.getCreateDate();
    jsonObject.put("createDate", Common.getUTC_Date(createDate));

    //Date snapshotTime = orderRequest.getSnapshotTime();
    //jsonObject.put("snapshotTime", Common.getUTC_Date(snapshotTime));

  }


}
