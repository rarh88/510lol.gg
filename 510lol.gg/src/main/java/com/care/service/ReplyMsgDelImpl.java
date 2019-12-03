package com.care.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ReplyMsgDelImpl implements Services{

    private static final String namespace="com.care.mybatis.myMapper";
     @Autowired
     private SqlSession sqlSession;
   @Override
   public void execute(Model model) {
      // TODO Auto-generated method stub
      Map<String, Object> map = model.asMap();
      HttpServletRequest request = (HttpServletRequest) map.get("request");
      int mnumber = Integer.parseInt(request.getParameter("mnumber"));
      
      System.out.println(mnumber+"=====================");
      sqlSession.delete(namespace+".replyMsgDel", mnumber);
   }

}