package com.care.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.dto.BoardSearchDTO;
import com.care.dto.boardDTO;
import com.care.dto.hitupDTO;
import com.care.dto.pageDTO;
import com.care.dto.repageDTO;

@Service
public class BoardService {

	
	private static final String namespace = "com.care.mybatis.myMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	public void saveboard(boardDTO bdto) {
		System.out.println("boardservice "+bdto.getBoardname());
		
		sqlSession.insert(namespace+".saveboard",bdto);
		int num = maxnum();
		sqlSession.insert(namespace+".insert_sequence_num", num);
	}
	public void freelist(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest)map.get("request");
		
		int stnum = 0;
		pageDTO pdto = new pageDTO();
		
		if(request.getParameter("stnum") == null) {
			stnum = 1;
			model.addAttribute("start",stnum);
		}else {
			stnum  = Integer.parseInt(request.getParameter("stnum"));
			model.addAttribute("start",stnum);
		}
		
		if( stnum == 1) {
			pdto.setStnum(1);
			pdto.setEndnum(10);
		model.addAttribute("freelist",sqlSession.selectList(namespace+".freelist",pdto));
		}else {
			stnum = stnum*10-9;
			int endnum = stnum+9;
			pdto.setStnum(stnum);
			pdto.setEndnum(endnum);
		model.addAttribute("freelist",sqlSession.selectList(namespace+".freelist",pdto));
		}
		
		int totalnum = sqlSession.selectOne(namespace+".freenum");
		int totalpage=0;
		if(totalnum % 10 == 0) {
			totalpage = totalnum/10;
			model.addAttribute("totalpage",totalpage);
		}else {totalpage = (totalnum/10)+1;
			model.addAttribute("totalpage",totalpage);
		}
	}
	public void OGlist(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest)map.get("request");
		
		int stnum = 0;
		pageDTO pdto = new pageDTO();
		
		if(request.getParameter("stnum") == null) {
			stnum = 1;
			model.addAttribute("start",stnum);
		}else {
			stnum  = Integer.parseInt(request.getParameter("stnum"));
			model.addAttribute("start",stnum);
		}
		
		if( stnum == 1) {
			pdto.setStnum(1);
			pdto.setEndnum(10);
		model.addAttribute("OGlist",sqlSession.selectList(namespace+".OGlist",pdto));
		}else {
			stnum = stnum*10-9;
			int endnum = stnum+9;
			pdto.setStnum(stnum);
			pdto.setEndnum(endnum);
		model.addAttribute("OGlist",sqlSession.selectList(namespace+".OGlist",pdto));
		}
		
		int totalnum = sqlSession.selectOne(namespace+".OGnum");
		int totalpage=0;
		if(totalnum % 10 == 0) {
			totalpage = totalnum/10;
			model.addAttribute("totalpage",totalpage);
		}else {totalpage = (totalnum/10)+1;
			model.addAttribute("totalpage",totalpage);
		}
	}
	public void buglist(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest)map.get("request");
		int stnum = 0;
		pageDTO pdto = new pageDTO();
		
		if(request.getParameter("stnum") == null) {
			stnum = 1;
			model.addAttribute("start",stnum);
		}else {
			stnum  = Integer.parseInt(request.getParameter("stnum"));
			model.addAttribute("start",stnum);
		}
		
		if( stnum == 1) {
			pdto.setStnum(1);
			pdto.setEndnum(10);
		model.addAttribute("buglist",sqlSession.selectList(namespace+".buglist",pdto));
		}else {
			stnum = stnum*10-9;
			int endnum = stnum+9;
			pdto.setStnum(stnum);
			pdto.setEndnum(endnum);
		model.addAttribute("buglist",sqlSession.selectList(namespace+".buglist",pdto));
		}
		
		int totalnum = sqlSession.selectOne(namespace+".bugnum");
		int totalpage=0;
		if(totalnum % 10 == 0) {
			totalpage = totalnum/10;
			model.addAttribute("totalpage",totalpage);
		}else {totalpage = (totalnum/10)+1;
			model.addAttribute("totalpage",totalpage);
		}
	}
	public void search(BoardSearchDTO bsdto,Model model) {
		System.out.println(bsdto.getBoardname());
		System.out.println(bsdto.getSearchvalue());
		System.out.println(bsdto.getSearchtitle());
		model.addAttribute("searchvalue",bsdto.getBoardname());
		model.addAttribute("searchvalue",bsdto.getSearchvalue());
		model.addAttribute("searchtitle",bsdto.getSearchtitle());
		int stnum = 0;
		
		if(bsdto.getStnum() == 0) {
			stnum = 1;
			model.addAttribute("start",stnum);
		}else {
			stnum  = bsdto.getStnum();
			model.addAttribute("start",stnum);
		}
		
		if( stnum == 1) {
			bsdto.setStnum(1);
			bsdto.setEndnum(10);
		}else {
			stnum = stnum*10-9;
			int endnum = stnum+9;
			bsdto.setStnum(stnum);
			bsdto.setEndnum(endnum);
		}
		int totalnum = 0;
		if(bsdto.getSearchtitle().equals("title")) {
			totalnum = sqlSession.selectOne(namespace+".titlesearchnum",bsdto);
			System.out.println("title"+totalnum);
			model.addAttribute("boardname",bsdto.getBoardname());
			model.addAttribute("searchresult",
					sqlSession.selectList(namespace+".searchtitle", bsdto));
		}else if(bsdto.getSearchtitle().equals("id")) {
			totalnum = sqlSession.selectOne(namespace+".idsearchnum",bsdto);
			System.out.println(totalnum);
			model.addAttribute("boardname",bsdto.getBoardname());
			model.addAttribute("searchresult",
					sqlSession.selectList(namespace+".searchid", bsdto));
		}else{
			totalnum = sqlSession.selectOne(namespace+".boardsearchnum",bsdto);
			model.addAttribute("boardname",bsdto.getSearchtitle());
			model.addAttribute("searchresult",
					sqlSession.selectList(namespace+".boardsearch", bsdto));
		}
		
		int totalpage=0;
		if(totalnum % 10 == 0) {
			totalpage = totalnum/10;
			model.addAttribute("totalpage",totalpage);
		}else {totalpage = (totalnum/10)+1;
			model.addAttribute("totalpage",totalpage);
			
		}
		
		
	}
	public void view(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest)map.get("request");
		String num = request.getParameter("num");
		sqlSession.selectOne(namespace+".hitup",num);
		model.addAttribute("view",sqlSession.selectOne(namespace+".view", num));
		

		
		int stnum = 0;
		repageDTO rpdto = new repageDTO();
			rpdto.setIdgroup(Integer.parseInt(request.getParameter("idgroup")));
		
		if(request.getParameter("stnum") == null) {
			stnum = 1;
			model.addAttribute("start",stnum);
		}else {
			stnum  = Integer.parseInt(request.getParameter("stnum"));
			model.addAttribute("start",stnum);
		}
		
		if( stnum == 1) {
			rpdto.setStnum(1);
			rpdto.setEndnum(10);
		model.addAttribute("replyview",sqlSession.selectList(namespace+".replylist",rpdto));
		}else {
			stnum = stnum*10-9;
			int endnum = stnum+9;
			rpdto.setStnum(stnum);
			rpdto.setEndnum(endnum);
		model.addAttribute("replyview",sqlSession.selectList(namespace+".replylist",rpdto));
		}
		
		int totalnum = sqlSession.selectOne(namespace+".replynum",rpdto);
		int totalpage=0;
		if(totalnum % 10 == 0) {
			totalpage = totalnum/10;
			model.addAttribute("totalpage",totalpage);
		}else {totalpage = (totalnum/10)+1;
			model.addAttribute("totalpage",totalpage);
		}
		
	}
	
	public void total(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest)map.get("request");
		int stnum = 0;
		pageDTO pdto = new pageDTO();
		
		if(request.getParameter("stnum") == null) {
			stnum = 1;
			model.addAttribute("start",stnum);
		}else {
			stnum  = Integer.parseInt(request.getParameter("stnum"));
			model.addAttribute("start",stnum);
		}
		
		if( stnum == 1) {
			pdto.setStnum(1);
			pdto.setEndnum(10);
		model.addAttribute("total",sqlSession.selectList(namespace+".total",pdto));
		}else {
			stnum = stnum*10-9;
			System.out.println(stnum);
			int endnum = stnum+9;
			System.out.println(endnum);
			pdto.setStnum(stnum);
			pdto.setEndnum(endnum);
		model.addAttribute("total",sqlSession.selectList(namespace+".total",pdto));
		}
		
		int totalnum = sqlSession.selectOne(namespace+".totalnum");
		int totalpage=0;
		if(totalnum % 10 == 0) {
			totalpage = totalnum/10;
			model.addAttribute("totalpage",totalpage);
		}else {totalpage = (totalnum/10)+1;
			model.addAttribute("totalpage",totalpage);
		}
	}
	public void boardsearch(Model model) {
		Map<String,Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest)map.get("request");
		String board= request.getParameter("boardname");
		String searchvalue = request.getParameter("searchvalue");
		
		if(board.equals("자유게시판")) {
			model.addAttribute("searchresult",sqlSession.selectList(namespace+".freesearch",searchvalue));
			model.addAttribute("boardname",board);
		}else if(board.equals("공지사항")) {
			model.addAttribute("searchresult",sqlSession.selectList(namespace+".OGsearch",searchvalue));
			model.addAttribute("boardname",board);
		}
	}
	public void delete(boardDTO bdto) {
		sqlSession.delete(namespace+".delete",bdto);
	}
	public void updateview(Model model) {
		Map<String,Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest)map.get("request");
		String num =request.getParameter("num");
		model.addAttribute("bdto",sqlSession.selectOne(namespace+".view", num));
	}
	public void update(Model model) {
		Map<String,Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest)map.get("request");
		boardDTO bdto = new boardDTO();
		bdto.setTitle(request.getParameter("title"));
		bdto.setContent(request.getParameter("content"));
		bdto.setNum(Integer.parseInt(request.getParameter("num")));
		sqlSession.update(namespace+".update",bdto);
	}
	public void reply(Model model) {
		Map<String,Object> map = model.asMap();
		HttpServletRequest request = 
				(HttpServletRequest)map.get("request");

		boardDTO bdto = new boardDTO();
		bdto.setId(request.getParameter("id"));
		bdto.setTitle(request.getParameter("title"));
		bdto.setBoardname(request.getParameter("boardname"));
		bdto.setContent(request.getParameter("content"));
		bdto.setIdgroup(Integer.parseInt(request.getParameter("idgroup")));
		bdto.setStep(Integer.parseInt(request.getParameter("step")));
		bdto.setIndent(Integer.parseInt(request.getParameter("indent")));
		sqlSession.update(namespace+".reqlyupdate",bdto);
		
		sqlSession.insert(namespace+".reply",bdto);
	}
	
	public void replyupdate(boardDTO bdto) {
		
		sqlSession.update(namespace+".replyupdate",bdto);
	}
	
	public String hit2up(boardDTO bdto) {
		
		hitupDTO hdto = new hitupDTO();
		hdto.setNum(bdto.getNum());
		hdto.setId(bdto.getId());
		
		if(sqlSession.selectOne(namespace+".hit2ck",hdto) == null) {
			sqlSession.insert(namespace+".hit2up",hdto);
			sqlSession.update(namespace+".hit2update",hdto);
			return sqlSession.selectOne(namespace+".hit2",bdto);
		}else{	
			sqlSession.delete(namespace+".hitdel",hdto);
			sqlSession.update(namespace+".hit2down",hdto);
			return sqlSession.selectOne(namespace+".hit2",bdto);
		}
	}
	public String replyhit2up(boardDTO bdto) {
		
		hitupDTO hdto = new hitupDTO();
		
		hdto.setNum(bdto.getNum());
		hdto.setId(bdto.getId());
		
		if(sqlSession.selectOne(namespace+".hit2ck",hdto) ==null){
			sqlSession.insert(namespace+".hit2up",hdto);
			sqlSession.update(namespace+".hit2update",hdto);
			return sqlSession.selectOne(namespace+".replyhit2",bdto);
		}else{
			sqlSession.delete(namespace+".hitdel",hdto);
			sqlSession.update(namespace+".hit2down",hdto);
			return sqlSession.selectOne(namespace+".replyhit2",bdto);
		}
	}
	public void reply2(boardDTO bdto) {
		sqlSession.insert(namespace+".reply2",bdto);
	}
	
	public int replydel(boardDTO bdto) {
		return sqlSession.delete(namespace+".replydel",bdto);
		
	}
	public int maxnum() {
		return sqlSession.selectOne(namespace+".maxnum");
	}
	public int sequence_maxnum() {
		return sqlSession.selectOne(namespace+".sequence_maxnum");
	}
}
