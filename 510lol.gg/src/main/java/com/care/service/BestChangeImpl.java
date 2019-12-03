package com.care.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.dto.DTO;

@Service
public class BestChangeImpl implements Services {

    private static final String namespace="com.care.mybatis.myMapper";
    @Autowired
    private SqlSession sqlSession;
   @Override
   public void execute(Model model) {
      // TODO Auto-generated method stub
      Map<String, Object> map = model.asMap();
      HttpServletRequest request = (HttpServletRequest)map.get("request");
      String nick = (String) map.get("nick");
      String champnum = "";
      String best = "";
      for(int i=1; i<4; i++) {
         String champ = request.getParameter("best"+i);
         if(champ!=null) {
            best = "best"+i;
            champnum = champ;
         }
      }
      DTO dto = new DTO();
      dto.setNick(nick);
      if(best.equals("best1")) {
         int chmapp = Integer.parseInt(champnum);
         dto.setBest1(chmapp);
         int bool = sqlSession.update(namespace+".changeBest1", dto);
         System.out.println(bool);
         model.addAttribute("chk", bool);
         
      }else if(best.equals("best2")) {
         int chmapp = Integer.parseInt(champnum);
         dto.setBest2(chmapp);
         int bool = sqlSession.update(namespace+".changeBest2", dto);
         System.out.println(bool);
         model.addAttribute("chk", bool);
      }else {
         int chmapp = Integer.parseInt(champnum);
         dto.setBest3(chmapp);
         int bool = sqlSession.update(namespace+".changeBest3", dto);
         System.out.println(bool);
         model.addAttribute("chk", bool);
      }
      
   
      
   }
      
}