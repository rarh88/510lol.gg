package com.care.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.dto.MsgDTO;

@Service
public class SendMsgImpl implements Services {
   
     private static final String namespace="com.care.mybatis.myMapper";
       @Autowired
       private SqlSession sqlSession;
   
   @Override
   public void execute(Model model) {
      // TODO Auto-generated method stub
      Map<String, Object> map = model.asMap();
      HttpServletRequest request = (HttpServletRequest) map.get("request");
      String sender = request.getParameter("sender");
      String receiver = request.getParameter("receiver");
      String content = request.getParameter("content");
      System.out.println(sender);
      System.out.println(content);
      
      MsgDTO dto = new MsgDTO();
      dto.setMsender(sender);
      dto.setMreceiver(receiver);
      dto.setMcontent(content);
      
   
      
      int msgcount = sqlSession.selectOne(namespace+".msgCount", dto);
      System.out.println("================="+msgcount);
      if(msgcount > 20) {
         int min = sqlSession.selectOne(namespace+".minMnumber", dto);
         sqlSession.delete(namespace+".msgdel", min);
      }
      
      int bool = sqlSession.insert(namespace+".sendMsg", dto);
      
      model.addAttribute("chk", bool);
      System.out.println(sender);
      System.out.println(receiver);
      System.out.println(content);
   }

}