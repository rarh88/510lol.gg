package com.care.service;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.dto.upload_imgDTO;


@Service
public class UpDownService {
	
	private static final String namespace = "com.care.mybatis.myMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	public void fileck(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest)map.get("request");
		String num = request.getParameter("num");
		if(sqlSession.selectOne(namespace+".fileck",num)!=null) {
		model.addAttribute("img",sqlSession.selectOne(namespace+".fileck",num));}
	}
	
	public void insertfile(String num,String fileurl,String orifile) {
		upload_imgDTO updto = new upload_imgDTO();
		updto.setBoardnum(Integer.parseInt(num));
		updto.setImgname(fileurl);
		updto.setOrifile(orifile);
		
		if(sqlSession.selectOne(namespace+".fileck",num) == null) {
			sqlSession.insert(namespace+".insertfile",updto);
		}
	}
	public upload_imgDTO downck(String num) {
		
		return sqlSession.selectOne(namespace+".downck",num);
	}
	public void filedelete(String num) {
		sqlSession.delete(namespace+".filedelete",num);
	}
}
