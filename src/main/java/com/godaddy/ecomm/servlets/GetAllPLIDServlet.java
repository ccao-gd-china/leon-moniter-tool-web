package com.godaddy.ecomm.servlets;

import com.godaddy.ecomm.service.FulfillmentService;
import com.godaddy.ecomm.utils.JsonParser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getAllPLID")
public class GetAllPLIDServlet extends HttpServlet {
  private Logger logger = LoggerFactory.getLogger(GetAllPLIDServlet .class);
  private FulfillmentService fulfillmentService = new FulfillmentService();

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp){
      try{
        List<String> allPLID = fulfillmentService.getAllPLID();
        //create Json Object
        JSONObject jsonObject;
        if (allPLID.size() > 0) {
          jsonObject = new JSONObject();
          jsonObject.put("data", allPLID);
        } else {
          jsonObject = new JSONObject();
          jsonObject.put("data", "");
        }
        PrintWriter out = resp.getWriter();
        out.print(jsonObject.toString());
      }catch (Exception e){
        logger.error(e.getLocalizedMessage());
      }
  }
}
