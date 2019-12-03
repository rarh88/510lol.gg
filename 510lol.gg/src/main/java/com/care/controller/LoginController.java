package com.care.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.dto.DTO;
import com.care.dto.NaverLoginApi;
import com.care.service.logService;
import com.care.service.naverloginservice;
import com.github.scribejava.core.model.OAuth2AccessToken;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import com.care.service.*;



@Controller
public class LoginController {
	final static String API_KEY = "RGAPI-a09d7c69-7f1f-4e09-a071-cf512db6be85";
	
	private String apiResult = null;

	private final static String K_CLIENT_ID = "dab51a4dfebbddccbe7e1f2a9b97f6cd";
	private final static String K_REDIRECT_URI = "localhost:8082/controller/kakaologin";
	
	ApplicationContext applicationContext = ApplicationContextprovider.applicationContext;
	@Autowired
	logService service;
	@Autowired
	naverloginservice naverloginservice;
	@Autowired
	kakaoapi kakao;
	
	
	

	
	@RequestMapping("login")
	public String login(Model model, HttpSession session) {
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverloginservice.getAuthorizationUrl(session);

		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		System.out.println("네이버:" + naverAuthUrl);
		//네이버
		model.addAttribute("url", naverAuthUrl);
		
		return "login";
	}
	
	@RequestMapping(value="naverlogin", method= {RequestMethod.GET, RequestMethod.POST})
	public String naverlogin(Model model, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException {

		System.out.println("여기는 callback");
		OAuth2AccessToken oauthToken;
		oauthToken = naverloginservice.getAccessToken(session, code, state);
		
		apiResult = naverloginservice.getUserProfile(oauthToken);
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;

		JSONObject response_obj = (JSONObject)jsonObj.get("response");

		//response의 nickname값 파싱
		String name = (String)response_obj.get("name");
		String id = (String)response_obj.get("id");
		String email = (String)response_obj.get("email");

		System.out.println(response_obj);
		
		
		System.out.println(id);
		System.out.println(email);
		System.out.println(name);
		//4.파싱 닉네임 세션으로 저장
		session.setAttribute("sid",id); //세션 생성
		session.setAttribute("semail", email);
		session.setAttribute("sname", name);
		
		int a = 0;
		DTO dto = new DTO();
		dto.setId(id);
		a = service.idchk(dto, session);
		
		model.addAttribute("result", apiResult);
		model.addAttribute("a",a);
		
		System.out.println(a);

		return "naverlogin";
	}
	

	@RequestMapping(value="kakaologin")
	public String kakaologin(@RequestParam("code") String code, HttpSession session) {
		session.removeAttribute("access_Token");
		session.removeAttribute("userId");
		session.removeAttribute("nickname");
		
		String access_Token = kakao.getAccessToken(code);
	    HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
	    System.out.println("login Controller : " + userInfo);
	    
	    DTO dto = new DTO();
	    int a = 0;
	    //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
	    if (userInfo.get("email") != null) {
	    	
	        session.setAttribute("userId", userInfo.get("email"));
	        session.setAttribute("nickname", userInfo.get("nickname"));
	        session.setAttribute("access_Token", access_Token);
	        session.setAttribute("sid", userInfo.get("nickname"));
	        String id = (String)session.getAttribute("sid");
	        String pw = (String)session.getAttribute("userId");
	        
	        dto.setId(id);
	        a = service.idchk(dto, session);
	        if(a==0) {
	        	return "kakaologinchk";
	        }
	        
	    }
	    return "kakaologin";
	}

	
	
	
	
	@RequestMapping("loginchk")
	public String loginchk(Model model, HttpServletRequest request,DTO dto) {
		int a = 0;
		model.addAttribute("request",request);
		model.addAttribute("APIKEY", API_KEY);
		a = service.loginchk(model,dto);
		model.addAttribute("a",a);
		HttpSession session = request.getSession();

		if(a == 1) {
			session.setAttribute("sid", dto.getId());
			System.out.println(session.getAttribute("sid"));
			
			Map<String, Object> map = model.asMap();
			String nick = (String) map.get("nick");
			model.addAttribute("nick", nick);
			
			service.loginUpdate(model);
		}

		return "loginchk";
	}
	
	@RequestMapping(value="ajax_chk",produces="text/json; charset=utf-8", method= RequestMethod.GET)
	@ResponseBody
	public String ajax_chk(DTO dto) {
		JSONObject json = new JSONObject();
		int a = 5;
		
		System.out.println("컨트롤러"+dto.getId());
		a = service.idchk(dto, null);
		
		System.out.println("1이면성공 / 0이면 실패");
		
		System.out.println(a);

		json.put("a", a);
		
		System.out.println("진짜테스트");
		System.out.println("json = " + json.toString());
		return json.toString();
	}
	
	@RequestMapping(value="ajax_chk7",produces="application/text;charset=utf-8")
	@ResponseBody
	public String ajax_chk7(DTO dto) {
		int a = 0;
		String s = "확인";
		a = service.idchk(dto, null);
		System.out.println("ajax"+a);
		return s;
	}
	
	
	@RequestMapping("regimem")
	public String regimem(Model model,HttpServletRequest request) {
		return "regimem";
	}
	@RequestMapping("regichk")
	public String regichk(Model model, HttpServletRequest request) {
		int a = 0;
		model.addAttribute("request",request);
		a = service.regichk(model);
		model.addAttribute("a",a);
		return "regichk";
	}
	
	@RequestMapping("regichks")
	public String regichks(Model model,HttpServletRequest request) {
		int a = 0;
		
		HttpSession session = request.getSession();
		model.addAttribute("request",request);
		a = service.regichks(model,session);
		model.addAttribute("a",a);
		
		return "regichks";
	}
	
	
	@RequestMapping("lols")
	public String lols(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
 		session.removeAttribute("test1");
		return "lols";
	}
	@RequestMapping("ok")
	public String ok(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int best1 = Integer.parseInt(request.getParameter("best1"));
		int best2 = Integer.parseInt(request.getParameter("best2"));
		int best3 = Integer.parseInt(request.getParameter("best3"));
		session.setAttribute("best1", best1);
		session.setAttribute("best2", best2);
		session.setAttribute("best3", best3);
		
	
		
		
		session.setAttribute("ok", 1);
		return "ok";
	}
	
	@RequestMapping("mypage")
	public String mypage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sid");
		String sid = id;
		System.out.println(id);
		
		model.addAttribute("id",id);
		if(session.getAttribute("sid")!=null) {
			service.mypage(model,id);
		}
		model.addAttribute("sid",sid);
		return "mypage";
	}
	
	
	 @RequestMapping(value = "logout", method = { RequestMethod.GET, RequestMethod.POST })
	   public String logout(HttpSession session)throws IOException {
	      System.out.println(session.getAttribute("sid") + "  :  세션아이디");
	      session.removeAttribute("sid");
	      session.removeAttribute("snick");
	      session.removeAttribute("stier");
	      session.invalidate();
	   return "redirect:login";
	   }
	 
	 @RequestMapping(value="kakaologout")
	 public String kakaologout(HttpSession session) {
	     kakao.kakaoLogout((String)session.getAttribute("access_Token"));
	     session.removeAttribute("access_Token");
	     session.removeAttribute("userId");
	     session.removeAttribute("snick");
	      session.removeAttribute("stier");
	     session.invalidate();
	     return "kakaologout";
	 }
	 
	 @RequestMapping("main")
	 public String main(Model model, HttpSession session) {
		 
		 
		 return "main";
	 }
	 
	 @RequestMapping("kakaotest")
	 public String kakaotest() {
		 return "kakaotest";
	 }
	 
	 @RequestMapping("mypagechk")
	 public String mypagechk(Model model, HttpServletRequest request) {
		 int a = 0;
		 model.addAttribute("request",request);
		 HttpSession session = request.getSession();
		 a = service.mypagechk(model,session);
		 model.addAttribute("a",a);
			 
		 return "mypagechk";
	 }
	 
	 @RequestMapping("idsearch")
	 public String idsearch() {
		 return "idsearch";
	 }
	 @RequestMapping("idsearchchk")
	 public String idsearchchk(Model model, HttpServletRequest request) {
		 int c = 0;
		 model.addAttribute("request",request);
		 String phonenum = request.getParameter("phonenum");
		 
		 
		 service.idsearchchk(model,phonenum);
		
		 return "idsearchchk";
	 }
	 @RequestMapping("pwsearch")
	 public String pwsearch() {
		 return "pwsearch";
	 }
	 
	 @RequestMapping("pwsearchchk")
	 public String pwsearchchk(Model model, HttpServletRequest request) {
		 model.addAttribute("request",request);
		 String id = request.getParameter("id");
		 String name = request.getParameter("name");
		 
		 
		 service.pwsearchchk(model,id,name);
		 
		 
		 return "pwsearchchk";
	 }
	 
	 @RequestMapping(value="ajax_mypage1",produces="application/text;charset=utf-8")
	 @ResponseBody
	 public String ajax_mypage1(DTO dto, HttpServletRequest request,Model model) {
		 System.out.println("챔프"+dto.getChamp());
		 System.out.println("best///"+ dto.getTest());
		 
		 HttpSession session = request.getSession();
		 String nick = (String)session.getAttribute("snick");
		 
		 String champ = dto.getChamp();
		 String test = dto.getTest();
		 
		 service.chgmypage3(model,champ,test,nick);

		 
		 
		 
		 
		 
		 return "mypage";
	 }
	 
	 
	 
	 
	 
	 
	 
	
}
