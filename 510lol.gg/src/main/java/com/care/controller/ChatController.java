package com.care.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * Handles requests for the application home page.
 */
@Controller
public class ChatController {
   
   private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
   
   @RequestMapping(value = "home", method = RequestMethod.GET)
   public String home(Locale locale, Model model)throws Exception {
      
         
      
      return "home";
   }
   @RequestMapping("CHALLENGER")
   public String main8() {
      
      return "challenger";
   }
   @RequestMapping("GRANDMASTER")
   public String main7() {
      
      return "grandmaster";
   }
   @RequestMapping("MASTER")
   public String main6() {
      
      return "master";
   }
   @RequestMapping("DIAMOND")
   public String main5() {
      
      return "diamond";
   }
   
   @RequestMapping("PLATINUM")
   public String main4() {
      
      return "platinum";
   }
   
   @RequestMapping("GOLD")
   public String main() {
      
      return "gold";
   }

   @RequestMapping("SILVER")
   public String main2() {
      
      return "silver";
   }

   @RequestMapping("BRONZE")
   public String main3() {
      
      return "bronze";
   }
   
   @RequestMapping("IRON")
   public String noperson() {
      
      return "iron";
   }
   
}