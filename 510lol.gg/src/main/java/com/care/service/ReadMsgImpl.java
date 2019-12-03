package com.care.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.dto.MsgDTO;

@Service
public class ReadMsgImpl implements Services {

	   private static final String namespace="com.care.mybatis.myMapper";
	      @Autowired
	      private SqlSession sqlSession;
	
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		int mnumber = Integer.parseInt(request.getParameter("mnumber"));
		System.out.println("=================== "+mnumber);
		sqlSession.update(namespace+".upMsg", mnumber);
		MsgDTO dto = sqlSession.selectOne(namespace+".readMsg", mnumber);
		System.out.println("==========="+dto.getMst());
		System.out.println("==========="+dto.getMreceiver());
		
		model.addAttribute("readm", dto);
		
		
	}

	
	
	
}
