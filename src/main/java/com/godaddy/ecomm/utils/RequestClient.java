package com.godaddy.ecomm.utils;

import com.godaddy.ecomm.base.Global;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class RequestClient {

  private static Logger logger = LoggerFactory.getLogger(RequestClient.class);

  public static Response httpRequest(String url, String responseType, String postData) {
    Client client = ClientBuilder.newClient();
    return client.target(url).request(responseType).post(Entity.json(postData));
  }

  public static Response httpRequest(String postDate) throws Exception {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("text", postDate);
    return RequestClient
        .httpRequest(Global.Slack_PayloadURL, Global.Content_type, jsonObject.toString());
  }


}
