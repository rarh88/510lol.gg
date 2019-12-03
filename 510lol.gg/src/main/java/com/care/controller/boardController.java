package com.care.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.dto.BoardSearchDTO;
import com.care.dto.boardDTO;
import com.care.service.BoardService;
import com.care.service.UpDownService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class boardController {

	
	public static int maxnum=0;
	@Autowired
	private BoardService bs;
	
	@Autowired
	private UpDownService us;
	
	@Autowired
	private FileController fcon;
	
	
	@RequestMapping("totalboard")
	public String totalBoard(Model model,HttpServletRequest request) {
		model.addAttribute("request",request);
		bs.total(model);
		return"board/totalboard";
	}
	@RequestMapping("champ")
	public String slog() {
		return"board/ChampInfo";
	}

	@RequestMapping("bug")
	public String report(Model model,HttpServletRequest request) {
		model.addAttribute("request",request);
		bs.buglist(model);
		return "board/bug";
	}

	@RequestMapping("0g")
	public String Og(Model model,HttpServletRequest request) {
		model.addAttribute("request",request);
		bs.OGlist(model);
		return "board/0g";
	}

	@RequestMapping("free")
	public String free(Model model,HttpServletRequest request) {
		model.addAttribute("request",request);
		bs.freelist(model);
		return"board/free";
	}
	@RequestMapping("writeboard")
	public String writeboard(HttpServletRequest request,Model model) {
		model.addAttribute("boardname",request.getParameter("boardname"));
		if(request.getParameter("fileurl") != "") {
			model.addAttribute("fileurl",request.getParameter("fileurl"));
		}
		if(request.getParameter("content")!="" && request.getParameter("title")!="") {
			model.addAttribute("content",request.getParameter("content"));
			model.addAttribute("title",request.getParameter("title"));
			
		}
		
			return "board/writeboard";
	}
	
//	@RequestMapping("saveboard")
//	public String saveboard(HttpServletRequest request,Model model) {
//		model.addAttribute("request",request);
//		bs.saveboard(model);
//		
//		if(maxnum < bs.maxnum()) {
//			maxnum = bs.maxnum();
//		}
//		System.out.println(maxnum);
//		return "redirect:totalboard";
//	}
	
	
	@RequestMapping("search")
	public String search(BoardSearchDTO bsdto,Model model) {
		bs.search(bsdto,model);
		return "board/searchresult";
	}
	
	
	@RequestMapping("view")
	public String view(HttpServletRequest request,Model model) {
		model.addAttribute("request",request);
		bs.view(model);
		String fileurl = request.getParameter("fileurl");
		us.fileck(model);
		if(fileurl != "") {
        	model.addAttribute("fileurl",fileurl);
        	String orifile = request.getParameter("orifile"); 
        	model.addAttribute("orifile",orifile);
		}
		
		return "board/view";
	}
//	@RequestMapping("totalsearch")
//	public String boardsearch(HttpServletRequest request,Model model) {
//		model.addAttribute("request",request);
//		bs.boardsearch(model);
//		return "board/searchresult";
//	}
	@RequestMapping("delete")
	public String delete(boardDTO bdto,Model model) {
		bs.delete(bdto);
		fcon.filedelete(bdto);
		return "redirect:totalboard";
	}
	@RequestMapping("updateview")
	public String updateview(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		bs.updateview(model);
		return "board/updateview";
	}
	@RequestMapping("update")
	public String update(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		bs.update(model);

		if(request.getParameter("boardname").equals("자유게시판")) {
			
			return "redirect:free";
		}else if(request.getParameter("boardname").equals("공지사항")){
			return "redirect:0g";
		}else return "redirect:totalboard";
	}
	@RequestMapping("reply")
	public String reply(HttpServletRequest request, Model model) {
		model.addAttribute("request",request);
		bs.reply(model);
		bs.view(model);
		
		String originFileName = request.getParameter("fileName");
		if(originFileName != "") {
        	model.addAttribute("fileName",originFileName);
        }
		model.addAttribute("num",request.getParameter("num"));
		model.addAttribute("idgroup",request.getParameter("idgroup"));
		return "redirect:view";
	}
	
	@RequestMapping("replyupdate")
	public String replyupdate(boardDTO bdto,Model model) {
		bs.replyupdate(bdto);
		System.out.println(bdto.getIdgroup());
		System.out.println(bdto.getContent());
		System.out.println(bdto.getReplynum());
		model.addAttribute("num",bdto.getNum());
		model.addAttribute("idgroup",bdto.getIdgroup());
		return "redirect:view";
	}
	
	
	@RequestMapping(value="hit2up",produces="aplication/text;charset=utf-8")
	@ResponseBody
	public String hit2up(boardDTO bdto) {
		 return bs.hit2up(bdto);
	}
	
	@RequestMapping(value="replyhit2up",produces="application/text;charset=utf-8")
	@ResponseBody
	public String replyhit2up(boardDTO bdto) {
		return bs.replyhit2up(bdto);
	}
	
	@RequestMapping("reply2")
	public String reply2(Model model,boardDTO bdto,HttpServletRequest request){
		bs.reply2(bdto);
		model.addAttribute("idgroup",bdto.getIdgroup());
		model.addAttribute("num",bdto.getNum());
		return "redirect:view";
	}
	
	@RequestMapping(value="replydel", produces="application/text;charset=utf-8")
	@ResponseBody
	public String replydel(boardDTO bdto) throws JsonProcessingException {
		return Integer.toString(bs.replydel(bdto));
		
//		System.out.println("controll"+a);
//		Map<String, Object> map = model.asMap();
//		Map<String, Object> updatemap = new HashMap<String, Object>();
//		updatemap.put("result", a);
//
//
//
//		ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
//		//자료를 담아서 문자열로 바꾼다 => {aaa(키):a입니다.(값)}
//		String strJson = mapper.writeValueAsString(updatemap);
//		System.out.println("controller"+strJson);
//		return strJson;
	}




}
