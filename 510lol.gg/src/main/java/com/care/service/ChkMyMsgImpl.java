package com.care.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.dto.MsgDTO;

@Service
public class ChkMyMsgImpl implements Services{

   
   private static final String namespace="com.care.mybatis.myMapper";
    @Autowired
    private SqlSession sqlSession;
    
   @Override
   public void execute(Model model) {
      // TODO Auto-generated method stub
      Map<String, Object> map = model.asMap();
      HttpServletRequest request = (HttpServletRequest)map.get("request");
      
      String mreceiver = request.getParameter("mreceiver");
      int stnum = 1;
      int endnum = 5;
      if(request.getParameter("stnum")!= null) {
         int a = Integer.parseInt(request.getParameter("stnum"));
         stnum = (a*5)-4;
         endnum = a*5;
      }
     
      System.out.println("endnum"+endnum);
      MsgDTO dto = new MsgDTO();
      dto.setMreceiver(mreceiver);
      dto.setStnum(stnum);
      dto.setEndnum(endnum);
      List<MsgDTO> pageing = sqlSession.selectList(namespace+".pageingMsg", dto);
      int totalmsg = sqlSession.selectOne(namespace+".myMsg", mreceiver);
      int total_msg = totalmsg/5;
      if(totalmsg%5>0) {
         total_msg += 1;
      }
      model.addAttribute("myMsg", pageing);
      model.addAttribute("totalmsg", totalmsg);
      model.addAttribute("mytotalMsg", total_msg);
      
   }

}