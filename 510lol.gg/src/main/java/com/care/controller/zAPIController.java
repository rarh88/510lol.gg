package com.care.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.net.HttpURLConnection; 
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.dto.ChampKDADTO;
import com.care.dto.DTO;
import com.care.dto.LeagueEntrydto;
import com.care.dto.League_expDTO;
import com.care.dto.MostDTO;
import com.care.dto.userDTO;
import com.care.service.BestChangeImpl;
import com.care.service.CheckKdaImpl;
import com.care.service.ChkButtonImpl;
import com.care.service.ChkMyMsgImpl;
import com.care.service.FindUserImpl;
import com.care.service.MactherSelectChampImpl;
import com.care.service.MatchingKdaImpl;
import com.care.service.ReadMsgImpl;
import com.care.service.ReplyMsgDelImpl;
import com.care.service.RotationImpl;
import com.care.service.SendMsgImpl;
import com.care.service.Services;
import com.care.service.TierRankImpl;
import com.care.service.TierRankImpl1;
import com.care.service.UserUpdateImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser; 

@Controller
public class zAPIController {
   
   private Services ser;
   
   ApplicationContext applicationContext = ApplicationContextprovider.applicationContext;
   
   final static String API_KEY = "RGAPI-a09d7c69-7f1f-4e09-a071-cf512db6be85";
   
   @RequestMapping("FindUser")
   public String FindUser() {
      return "FindUser";
   }
   
   @RequestMapping("findUserRe")
   public String findUserRe(Model model, HttpServletRequest request) {
      model.addAttribute("request", request);
      model.addAttribute("APIKEY", API_KEY);
      ser = applicationContext.getBean("findUserImpl",FindUserImpl.class);
      ser.execute(model);

//      ser = applicationContext.getBean("checkKdaImpl", CheckKdaImpl.class);
//      ser.execute(model);
      
      Map<String, Object> map = model.asMap();
      String Found = (String)map.get("Found");
      System.out.println("+++++++++++++++");
      System.out.println(Found);
      if(Found.equals("yes")) {
      
         return "findUserRe";
      }else {
         return "NotFound";
      }
   }
   @RequestMapping("FindUser1")
   public String FindUser1() {
      return "FindUser1";
   }
   
   
   
   
   
   
   
   
   
   
   @RequestMapping("NotFound")
   public String NotFound() {
      return "NotFound";
   }
   
   @RequestMapping("League-exp")
   public String League_exp(Model model, HttpServletRequest request){
      model.addAttribute("request", request);
      model.addAttribute("APIKEY", API_KEY);
      ser = applicationContext.getBean("tierRankImpl1",TierRankImpl1.class);
      ser.execute(model);
      return "League-exp";
   }
   
   @RequestMapping("league-exp-re")
   public String league_exp_re(Model model, HttpServletRequest request) {
      model.addAttribute("request", request);
      model.addAttribute("APIKEY", API_KEY);
      ser = applicationContext.getBean("tierRankImpl",TierRankImpl.class);
      ser.execute(model);
      
      
      return "league-exp-re";
   }
   
   @RequestMapping(value="user_update", produces="application/json;charset=utf-8")
   @ResponseBody
   public String user_update(Model model, HttpServletRequest request) throws JsonProcessingException {
      model.addAttribute("request", request);
      model.addAttribute("APIKEY", API_KEY);
      ser = applicationContext.getBean("userUpdateImpl",UserUpdateImpl.class);
      ser.execute(model);
      Map<String, Object> map = model.asMap();
      DTO dto = (DTO)map.get("dto");
      MostDTO smo1kda = (MostDTO)map.get("smo1kda");
      MostDTO smo2kda = (MostDTO)map.get("smo2kda");
      MostDTO smo3kda = (MostDTO)map.get("smo3kda");
      MostDTO smo4kda = (MostDTO)map.get("smo4kda");
      MostDTO smo5kda = (MostDTO)map.get("smo5kda");
      
      
   
      Map<String, Object> updatemap = new HashMap<String, Object>();
      updatemap.put("nick", dto.getNick());
      updatemap.put("rank", dto.getRank());
      updatemap.put("tier", dto.getTier());
      updatemap.put("score", dto.getScore());
      updatemap.put("total", dto.getTotal());
      updatemap.put("winrate", dto.getWinrate());
      updatemap.put("most1", dto.getMost1());
      updatemap.put("most2", dto.getMost2());
      updatemap.put("most3", dto.getMost3());
      updatemap.put("most4", dto.getMost4());
      updatemap.put("most5", dto.getMost5());
      updatemap.put("img", dto.getProfileiconid());
      updatemap.put("m1total", smo1kda.getTotal());
      updatemap.put("m1winrate", smo1kda.getWinrate());
      updatemap.put("m1kda", smo1kda.getKda());
      updatemap.put("m2total", smo2kda.getTotal());
      updatemap.put("m2winrate", smo2kda.getWinrate());
      updatemap.put("m2kda", smo2kda.getKda());
      updatemap.put("m3total", smo3kda.getTotal());
      updatemap.put("m3winrate", smo3kda.getWinrate());
      updatemap.put("m3kda", smo3kda.getKda());
      updatemap.put("m4total", smo4kda.getTotal());
      updatemap.put("m4winrate", smo4kda.getWinrate());
      updatemap.put("m4kda", smo4kda.getKda());
      updatemap.put("m5total", smo5kda.getTotal());
      updatemap.put("m5winrate", smo5kda.getWinrate());
      updatemap.put("m5kda", smo5kda.getKda());
      
      
      
      ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
      //자료를 담아서 문자열로 바꾼다 => {aaa(키):a입니다.(값)}
      String strJson = mapper.writeValueAsString(updatemap);
      
      return strJson;
   }
   
   @RequestMapping("rotation")
   public String rotaiton(Model model) {
      model.addAttribute("APIKEY", API_KEY);
      ser = applicationContext.getBean("rotationImpl", RotationImpl.class);
      ser.execute(model);
      return "rotation";
   }
   
   @RequestMapping("Matching")
   public String Matching() {
      return "Matching";
   }
   @RequestMapping("goMatching")
   public String goMatching(Model model, HttpServletRequest request) {
      model.addAttribute("request", request);   
      model.addAttribute("APIKEY", API_KEY);
      ser = applicationContext.getBean("mactherSelectChampImpl",MactherSelectChampImpl.class);
      ser.execute(model);
      ser = applicationContext.getBean("matchingKdaImpl",MatchingKdaImpl.class);
      ser.execute(model);
   
      return "goMatching";
   }
   
      @RequestMapping("report")
      public String report() {
         
         return "report";
      }
      @RequestMapping("write_report")
      public String write_report() {
         
         return "write_report";
      }
      @RequestMapping("reporting")
      public String reporting(Model model, HttpServletRequest request) {
         model.addAttribute("request", request);
         
         return "reporting";
         
      }

      @RequestMapping(value="chkButton", produces="application/json;charset=utf-8")
      @ResponseBody
      public String chkButton(Model model, HttpServletRequest request) throws JsonProcessingException {
         model.addAttribute("request", request);
         ser = applicationContext.getBean("chkButtonImpl", ChkButtonImpl.class);
         ser.execute(model);
         
         Map<String, Object> map = model.asMap();
         String btt = (String)map.get("btt");
        
         
         
         Map<String, Object> updatemap = new HashMap<String, Object>();
         updatemap.put("btt", btt);
          
          
          
          ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
          //자료를 담아서 문자열로 바꾼다 => {aaa(키):a입니다.(값)}
          String strJson = mapper.writeValueAsString(updatemap);
          
          return strJson;
  
      }
      @RequestMapping("changeBest")
      public String changeBest() {
         return "changeBest";
      }
      @RequestMapping("bestChange")
      public String bestChange(Model model, HttpServletRequest request) {
         model.addAttribute("request", request);
         HttpSession session = request.getSession();
         String nick = (String) session.getAttribute("snick");
         model.addAttribute("nick", nick);
            ser = applicationContext.getBean("bestChangeImpl", BestChangeImpl.class);
             ser.execute(model);
             
            Map<String, Object>map = model.asMap();
            int bool = (Integer) map.get("chk");
            if(bool == 1) {
               return "good";
            }else {
               return "error";
            }
             
            
      }
      @RequestMapping("sendMsg")
      public String sendMsg() {
         return "sendMsg";
      }
      
     
   
      @RequestMapping(value="goMsg", produces="application/json;charset=utf-8")
      @ResponseBody
      public String goMsg(Model model, HttpServletRequest request) throws JsonProcessingException {
         model.addAttribute("request", request);
        
         ser=applicationContext.getBean("sendMsgImpl",  SendMsgImpl.class);
         ser.execute(model);
         
         Map<String, Object> map = model.asMap();
         int chk = (Integer) map.get("chk");
         System.out.println(chk);
         String bool = "false";
         if(chk == 1) {
            bool = "true";
         }else {
            bool = "false";
         }
         Map<String, Object> updatemap = new HashMap<String, Object>();
          updatemap.put("chk", bool);
          
          ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
          //자료를 담아서 문자열로 바꾼다 => {aaa(키):a입니다.(값)}
          String strJson = mapper.writeValueAsString(updatemap);
         
          
          return strJson;
      }
      /////////////////////////////////////////////// 2019 11 27 2차 
      
      @RequestMapping("chkMsg")
      public String chkMsg(Model model, HttpServletRequest request) {
         model.addAttribute("request", request);
         
         ser=applicationContext.getBean("chkMyMsgImpl",  ChkMyMsgImpl.class);
          ser.execute(model);
         return "chkMsg";
      }
      
    
      @RequestMapping(value=" myMsg", produces="application/json;charset=utf-8")
      @ResponseBody
      public void myMsg(Model model, HttpServletRequest request ) {
         model.addAttribute("request", request);
         
         ser=applicationContext.getBean("chkMyMsgImpl",  ChkMyMsgImpl.class);
          ser.execute(model);
          
          
          
      }
      
      @RequestMapping("readMsg")
      public String readMsg(Model model, HttpServletRequest request) {
         model.addAttribute("request", request);
         ser=applicationContext.getBean("readMsgImpl",  ReadMsgImpl.class);
          ser.execute(model);
          
         return "readMsg";
         
      }
      
      @RequestMapping("replyMsg")
      public String replyMsg() {
         
         return "replyMsg";
      }
      
      @RequestMapping(value="replyMsgg", produces="application/json;charset=utf-8")
      @ResponseBody
      public String replyMsg(Model model, HttpServletRequest request) throws JsonProcessingException {
         model.addAttribute("request", request);
         ser = applicationContext.getBean("replyMsgDelImpl", ReplyMsgDelImpl.class);
         ser.execute(model);
         
         
         ser=applicationContext.getBean("sendMsgImpl",  SendMsgImpl.class);
         ser.execute(model);
        
         
         
         Map<String, Object> map = model.asMap();
         int chk = (Integer) map.get("chk");
         System.out.println(chk);
         String bool = "false";
         if(chk == 1) {
            bool = "true";
         }else {
            bool = "false";
         }
         Map<String, Object> updatemap = new HashMap<String, Object>();
          updatemap.put("chk", bool);
          
          ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
          //자료를 담아서 문자열로 바꾼다 => {aaa(키):a입니다.(값)}
          String strJson = mapper.writeValueAsString(updatemap);
         
          
          return strJson;
      }
      
      @RequestMapping("delMsg")
      public String delMsg(Model model, HttpServletRequest request) {
         model.addAttribute("request", request);
         String mreceiver = request.getParameter("user");
         System.out.println("asdsadsadsadsads"+mreceiver);
          ser = applicationContext.getBean("replyMsgDelImpl", ReplyMsgDelImpl.class);
           ser.execute(model);
           
           return "delMsg";
      }
   }