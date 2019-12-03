package com.care.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.dto.DTO;
import com.care.dto.SearchnonDTO;

@Service
public class ChkButtonImpl implements Services {

   private static final String namespace="com.care.mybatis.myMapper";
      @Autowired
      private SqlSession sqlSession;
   
   @Override
   public void execute(Model model) {
      // TODO Auto-generated method stub
      Map<String, Object> map = model.asMap();
      HttpServletRequest request = (HttpServletRequest)map.get("request");
      String nick = request.getParameter("nick");
    
         SearchnonDTO sdto = sqlSession.selectOne(namespace+".findnonemember03", nick);
         if(sdto != null) {
           
            
            try {
                String uptime = sdto.getUptime();
               Date nowdate = new Date();
               SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMddHHmmss");
               Date time = dateForm.parse(uptime);
         
               

               if(time.getTime() >= nowdate.getTime()) {
                  model.addAttribute("btt", "no");
               }else {
                  model.addAttribute("btt", "yes");
               }
            }catch(Exception e) {
                e.printStackTrace();
            }
            
         }

     
         
         
      }
      
      
   }