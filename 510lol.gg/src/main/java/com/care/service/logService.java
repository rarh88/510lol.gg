package com.care.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.dto.DTO;
import com.care.dto.LeagueEntrydto;
import com.care.dto.MostCham;
import com.care.dto.MostDTO;
import com.care.dto.userDTO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class logService {
   private static final String namespace="com.care.mybatis.myMapper";
   @Autowired
   private SqlSession sqlSession;
   
   public int idchk1(DTO dto,HttpSession session) {
      int a = 0;
      String id = dto.getId();
      try {
         dto = sqlSession.selectOne(namespace+".list",id);
         
         session.setAttribute("snick", dto.getNick());
         session.setAttribute("stier", dto.getTier());
         
      }catch(Exception e) {
         e.printStackTrace();
         a = 1;
      }
      

      
      
      return a;
   }
   
   
   public int idchk(DTO dto,HttpSession session) {
      
      int a = 0;
      String id = dto.getId();
      System.out.println(a);
      System.out.println("서비스"+id);
      try {
         dto = sqlSession.selectOne(namespace+".list",id);
         
         session.setAttribute("snick", dto.getNick());
         session.setAttribute("stier", dto.getTier());
         
         
         
         if(dto!=null) {
            a = 1;
         }else {
            a = 0;
         }
      }catch(Exception e) {
         e.printStackTrace();
         a = 0;
      
         
      }
      

      System.out.println("test:"+a);
      
      return a;
   }
   
   
   
   public int regichk(Model model) {
      Map<String, Object>map = model.asMap();
      HttpServletRequest request = (HttpServletRequest)map.get("request");
      
      String test = request.getParameter("id");
      int a = 0;
      HttpSession session = request.getSession();
      int s = 123;
      DTO dto = new DTO();
      dto.setId(request.getParameter("id"));
      dto.setPw(request.getParameter("pw"));
      dto.setName(request.getParameter("name"));
      dto.setPhonenum(request.getParameter("phonenum"));
      
      if(session.getAttribute("tier")==null) {
         a = 2;
         return a;
      }
      else {
         String nick = (String) session.getAttribute("nick");
         String tier = (String) session.getAttribute("tier");
         String rank = (String) session.getAttribute("rank");
         int score = (Integer) session.getAttribute("score");
         int wins = (Integer) session.getAttribute("wins");
         int total = (Integer) session.getAttribute("total");
         String winrate = (String) session.getAttribute("winrate");
         int profileiconid = (Integer)session.getAttribute("profileiconid");
       

        int best1 = (Integer)session.getAttribute("best1"); 
        int best2 = (Integer)session.getAttribute("best2"); 
        int best3 = (Integer)session.getAttribute("best3"); 
        
        String best1kda = "null";
        String best2kda = "null";
        String best3kda = "null";
        
        String best1winrate ="null";
        String best2winrate ="null";
        String best3winrate ="null";
        
        int best1total = 0;
        int best2total = 0;
        int best3total = 0;
        
         
         String accountid = (String)session.getAttribute("accountid");
         
         dto.setNick(nick);
         dto.setTier(tier);
         dto.setRank(rank);
         dto.setScore(score);
         dto.setWins(wins);
         dto.setTotal(total);
         dto.setWinrate(winrate);
         dto.setProfileiconid(profileiconid);
	     dto.setBest1(best1);
	     dto.setBest2(best2);
	     dto.setBest3(best3);
	     
	     dto.setBest1kda(best1kda);
	     dto.setBest2kda(best2kda);
	     dto.setBest3kda(best3kda);
	     dto.setBest1winrate(best1winrate);
	     dto.setBest2winrate(best2winrate);
	     dto.setBest3winrate(best3winrate);
         
        dto.setBest1total(best1total);
        dto.setBest2total(best2total);
        dto.setBest3total(best3total);
	     
	     
         dto.setAccountid(accountid);
         a = sqlSession.insert(namespace+".regi",dto);
         a = 1;
      }
      
      session.removeAttribute("nick");
      session.removeAttribute("tier");
      session.removeAttribute("rank");
      session.removeAttribute("score");
      session.removeAttribute("wins");
      session.removeAttribute("total");
      session.removeAttribute("winrate");
      session.removeAttribute("profileiconid");
      session.removeAttribute("most1");
      session.removeAttribute("most2");
      session.removeAttribute("most3");
      session.removeAttribute("most4");
      session.removeAttribute("most5");
      session.removeAttribute("accountid");
      session.removeAttribute("best1");
      session.removeAttribute("best2");
      session.removeAttribute("best3");
      

      return a;
   }
   
   public int regichks(Model model,HttpSession session) {
      Map<String, Object>map = model.asMap();
      HttpServletRequest request = (HttpServletRequest)map.get("request");
   
      int a = 0;

      int s = 123;

      DTO dto = new DTO();
       MostDTO m1kda = new MostDTO();
        MostDTO m2kda = new MostDTO();
        MostDTO m3kda = new MostDTO();
        MostDTO m4kda = new MostDTO();
        MostDTO m5kda = new MostDTO();
        
       String phonenum = request.getParameter("phonenum"); 
        
        
      dto.setPhonenum(phonenum);
      
     
      
      
      
      

      if(session.getAttribute("userId")!=null) {
         String id = (String)session.getAttribute("sid");
         String pw = (String)session.getAttribute("userId");
         String name = (String)session.getAttribute("sid");
         
         dto.setId(id);
         dto.setPw(pw);
         dto.setName(name);
         
      }else {
         String email = (String)session.getAttribute("semail");
         String id = (String)session.getAttribute("sid");
         String name = (String)session.getAttribute("sname");
      
         dto.setId(id);
         dto.setPw(email);
         dto.setName(name);
         

      }
      
      
      if(session.getAttribute("nick")==null) {
         a = 2;
      }
      else {
         String nick = (String) session.getAttribute("nick");
         String tier = (String) session.getAttribute("tier");
         String rank = (String) session.getAttribute("rank");
         int score = (Integer) session.getAttribute("score");
         int wins = (Integer) session.getAttribute("wins");
         int total = (Integer) session.getAttribute("total");
         String winrate = (String) session.getAttribute("winrate");
         int profileiconid = (Integer)session.getAttribute("profileiconid");
         String accountid = (String)session.getAttribute("accountid");
         int best1 = (Integer)session.getAttribute("best1"); 
         int best2 = (Integer)session.getAttribute("best2"); 
         int best3 = (Integer)session.getAttribute("best3"); 
         
         
         dto.setNick(nick);
         dto.setTier(tier);
         dto.setRank(rank);
         dto.setScore(score);
         dto.setWins(wins);
         dto.setTotal(total);
         dto.setWinrate(winrate);
         dto.setProfileiconid(profileiconid);
         dto.setBest1(best1);
         dto.setBest2(best2);
         dto.setBest3(best3);
         dto.setAccountid(accountid);
         
         String best1kda = "null";
         String best2kda = "null";
         String best3kda = "null";
         
         String best1winrate ="null";
         String best2winrate ="null";
         String best3winrate ="null";
         
         int best1total = 0;
         int best2total = 0;
         int best3total = 0;
         
         
         dto.setBest1kda(best1kda);
	     dto.setBest2kda(best2kda);
	     dto.setBest3kda(best3kda);
	     dto.setBest1winrate(best1winrate);
	     dto.setBest2winrate(best2winrate);
	     dto.setBest3winrate(best3winrate);
         
        dto.setBest1total(best1total);
        dto.setBest2total(best2total);
        dto.setBest3total(best3total);
         
         
      }
      
      if(a!=2) {
         a = sqlSession.insert(namespace+".regi",dto);
         a = 1;
         
      }
      
      session.removeAttribute("nick");
      session.removeAttribute("tier");
      session.removeAttribute("rank");
      session.removeAttribute("score");
      session.removeAttribute("wins");
      session.removeAttribute("total");
      session.removeAttribute("winrate");
      session.removeAttribute("profileiconid");
      session.removeAttribute("accountid");
      session.removeAttribute("best1");
      session.removeAttribute("best2");
      session.removeAttribute("best3");
      

      return a;
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   public int loginchk(Model model,DTO dto) {
      Map<String, Object>map = model.asMap();
      HttpServletRequest request = (HttpServletRequest)map.get("request");
      String test1 = request.getParameter("id");
      String test2 = request.getParameter("pw");

      dto.setId(request.getParameter("id"));
      dto.setPw(request.getParameter("pw"));
      int a = 0;
	HttpSession session = request.getSession();
      try {
         dto = sqlSession.selectOne(namespace+".list",dto.getId());
         model.addAttribute("nick", dto.getNick());
         System.out.println("test");
         System.out.println(dto.getId());
         System.out.println(dto.getPw());
         
         //세션닉네임값
         session.setAttribute("snick", dto.getNick());
         session.setAttribute("stier", dto.getTier());
         
         }catch(Exception e ) {
            System.out.println("안댐");
            a = 0;
            e.printStackTrace();

            return a;
         }
      
      if(dto.getId()==null) {
         a = 0;
         System.out.println("????????????");
      }else {
         if(test2.equals(dto.getPw())) {
            System.out.println("확인");
            a = 1;
         }
      }
   
      return a;
   }
   
   public DTO mypage(Model model ,String id) {
      DTO dto = new DTO();
      
      
      System.out.println("==="+id);
      
      dto = sqlSession.selectOne(namespace+".mypage",id);
      
      model.addAttribute("lists",dto);
      return dto;
   }
   
   
   public void loginUpdate(Model model) {
           Map<String, Object> map = model.asMap();
           String nickname = (String)map.get("nick");
           String nick = (String)map.get("nick");
            String API_KEY = (String)map.get("APIKEY");
            BufferedReader br = null;
            BufferedReader br2 = null;
            BufferedReader br3 = null;
            
            userDTO temp = null;
           
            MostCham moo = null;
            DTO ddto = new DTO();
            ArrayList<MostCham> mostC = new ArrayList<MostCham>();
            ArrayList<LeagueEntrydto> leaguearr = new ArrayList<LeagueEntrydto>();
            
            
         }
   
   
   public int mypagechk(Model model, HttpSession session) {
		Map<String, Object>map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
	
		int a = 0;
		
		System.out.println(request.getParameter("pwc"));
		System.out.println(request.getParameter("phonenumc"));
		System.out.println(session.getAttribute("sid"));
		
		String id = (String)session.getAttribute("sid");
		String pw = request.getParameter("pwc");
		String phonenum = request.getParameter("phonenumc");
		DTO dto = new DTO();
		
		dto.setId(id);
		dto.setPw(pw);
		dto.setPhonenum(phonenum);
		
		System.out.println("test "+phonenum);
		
		try {
           sqlSession.update(namespace+".mypageupdate",dto);
			a = 1 ;
		}catch(Exception e){
			e.printStackTrace();
			a = 0;
		}
		
		
		return a;
	}
   
   
   public DTO idsearchchk(Model model,String phonenum) {
		Map<String, Object>map = model.asMap();
		
		
		DTO dto = new DTO();
	   int c = 0;
	   try {
			dto = sqlSession.selectOne(namespace+".idsearchchk",phonenum);
			model.addAttribute("lists",dto);
			
			
			
			if(dto.getId()!=null) {
				String id = dto.getId();
				model.addAttribute("id", id);
			}else {
				c = 2;
				model.addAttribute("c",c);
			}
		}catch(Exception e) {
			c = 2;
			model.addAttribute("c",c);
			e.printStackTrace();
		}
		
		return dto;
   }
   
   public DTO pwsearchchk(Model model,String id, String name) {
	   Map<String, Object>map = model.asMap();
	   DTO dto = new DTO();
	   
	   try {
		   dto = sqlSession.selectOne(namespace+".pwsearchchk",id);
		   System.out.println(dto.getPw());
		   if(dto.getName().equals(name)) {
			   model.addAttribute("lists",dto);
			   String pw = dto.getPw();
			   model.addAttribute("pw", pw);
		   }
		   
		   
		   
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	   
	   
	   return dto;
   }
   
   public DTO chgmypage3(Model model,String champ,String test,String nick) {
	   
	    
		DTO dto = new DTO();
		dto.setNick(nick);
		
		
		 if(test.equals("best1")) {
			int best1 = Integer.parseInt(champ);
			 dto.setBest1(best1);
			 System.out.println(best1);
	         sqlSession.update(namespace+".changeBest1",dto);
		 }else if(test.equals("best2")) {
			 int best2 = Integer.parseInt(champ);
			 dto.setBest2(best2);
			 System.out.println(best2);
	         sqlSession.update(namespace+".changeBest2",dto);
		 }else {
			 int best3 = Integer.parseInt(champ);
			 dto.setBest3(best3);
	         sqlSession.update(namespace+".changeBest3",dto);
			 
		 }

		 
		 
		 
		 
		 
		 

		 
		 
		 
		   return dto;
   }
   
   
   
   
   
}
   
   
   
   