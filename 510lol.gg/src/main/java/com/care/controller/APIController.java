package com.care.controller;

import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.care.dto.ChampKDADTO;
import com.care.dto.DTO;
import com.care.dto.LeagueEntrydto;
import com.care.dto.MostCham;
import com.care.dto.MostDTO;
import com.care.dto.userDTO;
import com.care.dto.userMatchDTO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class APIController {
	private static final String namespace="com.care.mybatis.myMapper";
	@Autowired
	private SqlSession sqlSession;
	   
   final static String API_KEY = "RGAPI-a09d7c69-7f1f-4e09-a071-cf512db6be85";
   
   @RequestMapping("findUserRe1")
      public String findUserRe1(Model model, HttpServletRequest request) {
   
         String nick = request.getParameter("nickname");
         nick = nick.replaceAll(" ","");
         HttpSession session = request.getSession();
         int total;
         float winrates;
         String winrate;
         int a = 100;
         BufferedReader br = null;
         BufferedReader br2 = null;
         BufferedReader br3 = null;
         BufferedReader br4 = null;
         BufferedReader br5 = null;
         userDTO temp = null;
         MostCham moo = null;
         ArrayList<MostCham> mostC = new ArrayList<MostCham>();
        
        
          session.removeAttribute("llid");
          session.removeAttribute("nick");
          session.removeAttribute("rank");
          session.removeAttribute("total");
          session.removeAttribute("tier");
          session.removeAttribute("score");
          session.removeAttribute("wins");
          session.removeAttribute("winrate");
          session.removeAttribute("best1");
          session.removeAttribute("best2");
          session.removeAttribute("best3");
          session.removeAttribute("c");
          
          DTO dto = new DTO();
          int b = 0;
          //1조건
          try {
        	  dto = sqlSession.selectOne(namespace+".list1",nick);
        	  
        	  
        	 if(dto.getId()!=null) {
        		 b=4;
        		 session.setAttribute("c", b);
        	 }
        //1조건	 
          }catch(Exception e) {
        	  ArrayList<LeagueEntrydto> leaguearr = new ArrayList<LeagueEntrydto>();
              try{ 
                String urlstr = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ nick +"?api_key="+API_KEY; 
                 URL url = new URL(urlstr); 
                 HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection(); 
                 urlconnection.setRequestMethod("GET");
                 br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
                 //여기에 문자열을 받아와라. 
                 String result = ""; 
                 String line; 
                 while((line = br.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
                    result = result + line; }
                 JsonParser jsonParser = new JsonParser(); 
                 JsonObject k = (JsonObject) jsonParser.parse(result); 
                 int profileIconId = k.get("profileIconId").getAsInt();
                 String name = k.get("name").getAsString(); 
                 String puuid = k.get("puuid").getAsString(); 
                 long summonerLevel = k.get("summonerLevel").getAsLong();
                 long revisionDate = k.get("revisionDate").getAsLong(); 
                 String id = k.get("id").getAsString();
                 String accountId = k.get("accountId").getAsString(); 
                 temp = new userDTO(profileIconId,name,puuid,summonerLevel,revisionDate,id,accountId); 
                    
                 session.setAttribute("llid", id);
                int c = 2;
                session.setAttribute("c", c);
                 
                 
                 
                 String urlstr2 = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+id+"?api_key="+API_KEY;
                 URL url2 = new URL(urlstr2);
                 HttpURLConnection urlconnection2 = (HttpURLConnection) url2.openConnection(); 
                 urlconnection2.setRequestMethod("GET");
                 br2 = new BufferedReader(new InputStreamReader(urlconnection2.getInputStream(),"UTF-8"));
                 String result2 = "";
                 String line2; 
                 while((line2 = br2.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
                    result2 = result2 + line2; }
                 JsonParser jsonParser2 = new JsonParser();
                 JsonArray arr = (JsonArray) jsonParser2.parse(result2); 
                 for(int i=0;i<arr.size();i++){
                 LeagueEntrydto leagueInfo;
                 JsonObject k2 = (JsonObject) arr.get(i);
                 int wins = k2.get("wins").getAsInt(); 
                 int losses = k2.get("losses").getAsInt(); 
                 String rank = k2.get("rank").getAsString(); 
                 String tier = k2.get("tier").getAsString(); 
                 String queueType = k2.get("queueType").getAsString(); 
                 int leaguePoints = k2.get("leaguePoints").getAsInt(); 
                 String leagueId = k2.get("leagueId").getAsString(); 
                 leagueInfo = new LeagueEntrydto(queueType, wins, losses, leagueId, rank,tier, leaguePoints);
                 leaguearr.add(leagueInfo);
                 
                 model.addAttribute("finduser", temp);
                 model.addAttribute("imgURL", "http://ddragon.leagueoflegends.com/cdn/9.16.1/img/profileicon/"+temp.getProfileiconid()+".png");
                 model.addAttribute("leagueInfo", leaguearr);
                 if(queueType.equals("RANKED_SOLO_5x5")) {
                   total = wins+losses;
                   winrates = (float)wins/(float)total*(float)a;
                   winrate = String.format("%.1f" , winrates);
                   
                   
                   session.setAttribute("rank", rank);
                   session.setAttribute("total", total);
                   session.setAttribute("tier", tier);
                   session.setAttribute("score", leaguePoints);
                   session.setAttribute("nick", nick);
                   session.setAttribute("wins", wins);
                   session.setAttribute("winrate", winrate);
                   session.setAttribute("profileiconid", profileIconId);
                   session.setAttribute("accountid", accountId);
                 }else {
                   session.setAttribute("test1", 2); 
                 }
    
                }
         
              }catch(Exception r){

                 System.out.println(r.getMessage()); 
                 return "lolsview";
              }
        	  e.printStackTrace();
          }
          
          
          
         
        
       
      return "lolsview";
   }
   
   @RequestMapping("lolsview")
   public String lolsview(HttpServletRequest request, Model model) {
      HttpSession session = request.getSession();
      return "lolsview";
   }
   
   
   
   
   
   
   
   @RequestMapping("updateparty")
   public String updateparty(HttpServletRequest request, Model model) {
	   HttpSession session = request.getSession();
	   
	   String id = (String)session.getAttribute("sid");
	   String nick = (String)session.getAttribute("snick");
	   
	   DTO dto = new DTO();
	   int b1kill = 0;
	   int b1death = 0;
	   int b1assists = 0;
	   int b1win = 0;
	   int b1total = 0;
	   int b2kill = 0;
	   int b2death = 0;
	   int b2assists = 0;
	   int b2win = 0;
	   int b2total = 0;
	   int b3kill = 0;
	   int b3death = 0;
	   int b3assists = 0;
	   int b3win = 0;
	   int b3total = 0;
	   
	   int b1 = 0;
		int b2 = 0;
		int b3 = 0;
	  
		dto.setId(id);
		dto.setNick(nick);
	   
		System.out.println(nick);
		
		
		
	   if(id==null) {
		   
	   }else {
		   dto = sqlSession.selectOne(namespace+".list2",nick);
		   
		   
		
		  
		   
		   
		   
		   
		   
		   
			
			int best1 = dto.getBest1();
			int best2 = dto.getBest2();
			int best3 = dto.getBest3();

			
			
			int [] select_Champ = new int[] {best1,best2,best3};
			
			
			
			   String uptime = dto.getUptime();
			  
			
				Date nowdate = new Date();
	           SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHHmm");  
	           Date time = new Date();

	           try {

	        	  
	               Date timess = format1.parse(uptime);
	        	   
	        	   
	        	   
	               
		           
		           
		           if(timess.getTime() >= nowdate.getTime()) {
					   

		        	   System.out.println("안지남!!!!!!!!!!!!!!!!");
					   
					   
				   }else {
						 System.out.println("시간지남 !!!!!!!!!!!");	
					   
						 //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
						 
						 
						 Map<String,Object> map = model.asMap();
							
						   System.out.println(select_Champ[0]);
						   System.out.println(select_Champ[1]);
						   System.out.println(select_Champ[2]);
							
							
							
							BufferedReader br = null;
							BufferedReader br4 = null;
							BufferedReader br5 = null;
							int countt = 1;
							
							try {
								String urlstr = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ nick +"?api_key="+API_KEY; 
								URL url = new URL(urlstr); 
								HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection(); 
								urlconnection.setRequestMethod("GET");
								br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
								//여기에 문자열을 받아와라. 
								String result = ""; 
								String line; 

								while((line = br.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
									result = result + line; }
								JsonParser jsonParser = new JsonParser(); 
								JsonObject k = (JsonObject) jsonParser.parse(result); 
								String accountId = k.get("accountId").getAsString(); 
								ArrayList<ChampKDADTO> Mkdarr1 = new ArrayList<ChampKDADTO>();
								ArrayList<ChampKDADTO> Mkdarr2 = new ArrayList<ChampKDADTO>();
								ArrayList<ChampKDADTO> Mkdarr3 = new ArrayList<ChampKDADTO>();
								
								for(int x=0; x<select_Champ.length; x++) {
								
									String urlstr4 = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/"+accountId+"?champion="+select_Champ[x]+"&queue=420&season=13&&endIndex=10&beginIndex=0&api_key="+API_KEY;
									
									URL url4 = new URL(urlstr4);
									HttpURLConnection urlconnection4 = (HttpURLConnection) url4.openConnection(); 
									urlconnection4.setRequestMethod("GET");
									br4 = new BufferedReader(new InputStreamReader(urlconnection4.getInputStream(),"UTF-8"));
									String result4 = "";
									String line4; 
									while((line4 = br4.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
										result4 = result4 + line4;
									}

									JsonParser jsonParser4 = new JsonParser();
									JsonObject aaa = (JsonObject)  jsonParser4.parse(result4); 
									JsonArray arr3 = (JsonArray) aaa.get("matches");
									ArrayList<userMatchDTO> umarr = new ArrayList<userMatchDTO>();
									
									if(arr3.size()!=0) {
										
									
									for(int i=0; i<arr3.size(); i++) {
										JsonObject k4 = (JsonObject) arr3.get(i);
										userMatchDTO umdto = new userMatchDTO();
										umdto.setGameId(k4.get("gameId").getAsLong());
										umdto.setChampion(k4.get("champion").getAsInt());
										umarr.add(umdto);
									}
									
									if(x==0) {
										
									for(int i=0; i<umarr.size(); i++) {
										String urlstr5 = "https://kr.api.riotgames.com/lol/match/v4/matches/"+umarr.get(i).getGameId()+"?api_key="+API_KEY;
										URL url5 = new URL(urlstr5);
										HttpURLConnection urlconnection5 = (HttpURLConnection) url5.openConnection(); 
										urlconnection5.setRequestMethod("GET");
										br5 = new BufferedReader(new InputStreamReader(urlconnection5.getInputStream(),"UTF-8"));
										String result5 = "";
										String line5; 
										while((line5 = br5.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
											result5 = result5 + line5;
										}
										JsonParser jsonParser5 = new JsonParser();
										JsonObject k5 = (JsonObject) jsonParser5.parse(result5);
										
										//int chking = -1;
										
										JsonArray identities = (JsonArray)k5.get("participantIdentities"); 
										for(int j=0; j<identities.size(); j++) {
											JsonObject player = (JsonObject)identities.get(j);
											JsonObject detail_player = (JsonObject)player.get("player");
											String account = (String)detail_player.get("accountId").getAsString();
										
											if(accountId.equals(account)) {
												int participantId = player.get("participantId").getAsInt();
												
												JsonArray participants = (JsonArray)k5.get("participants"); 
												for(int p=0; p<participants.size(); p++) {
													JsonObject kda = (JsonObject)participants.get(p);
													int inparticipantId = kda.get("participantId").getAsInt();
													if(participantId == inparticipantId) {
														ChampKDADTO kdadto = new ChampKDADTO();
														JsonObject stats = (JsonObject)kda.get("stats"); 
															int kills = stats.get("kills").getAsInt();
															int assists = stats.get("assists").getAsInt();
															int deaths = stats.get("deaths").getAsInt();
															boolean win = stats.get("win").getAsBoolean();
															kdadto.setChampionId((select_Champ[x]));
															
															
															System.out.println("111select_Champ["+x+"] : "+kills );
															
															kdadto.setKills(kills);
															kdadto.setAssists(assists);
															kdadto.setDeaths(deaths);
															kdadto.setWin(win);
															Mkdarr1.add(kdadto);
															
															float fwinrate = 0;
									                        
									                        int kill = 0;
									                        int death = 0;
									                        int assist = 0;
									                        int win1 = 0;
									                        int champ = 0;
									                        int totalgame = Mkdarr1.size();
									                        
									                        System.out.println("tooo"+totalgame);
									                        
									                        for(int q =0; q<Mkdarr1.size(); q++) {
									                           kill += Mkdarr1.get(q).getKills();
									                           death += Mkdarr1.get(q).getDeaths();
									                           assist += Mkdarr1.get(q).getAssists();
									                           champ = Mkdarr1.get(q).getChampionId();
									                           if(Mkdarr1.get(q).isWin() == true) {
									                              win1++;
									                           }
									                        }
									                        
									                        System.out.println("1");
									                        float kda1s = (kill+assist)/death; 
									                        String kda1 = String.format("%.2f", kda1s);
									                        
									                        System.out.println("2");
									                        fwinrate = (float)win1/totalgame*100;
									                        String winrate =  String.format("%.2f", fwinrate);
															
									                        
									                        System.out.println("모스트1 총판="+Mkdarr1.size());
									                        System.out.println("모스트1 킬="+kill);
															System.out.println("모스트1데스="+death);
															System.out.println("모스트1어시="+assist);
															System.out.println("모스트1KDA="+kda1);
															System.out.println("승률="+winrate);
															
															dto.setBest1kda(kda1);
															dto.setBest1winrate(winrate);
															dto.setBest1total(totalgame);
															sqlSession.update(namespace+".best1up",dto);
															   
															
															
															
															
															
														}
													}
												
											}
										}
									}
									
									b1=1;
									 
									model.addAttribute("MselectChamp1", Mkdarr1);
									countt ++;
								}else if(x==1) {
									for(int i=0; i<umarr.size(); i++) {
										String urlstr5 = "https://kr.api.riotgames.com/lol/match/v4/matches/"+umarr.get(i).getGameId()+"?api_key="+API_KEY;
										URL url5 = new URL(urlstr5);
										HttpURLConnection urlconnection5 = (HttpURLConnection) url5.openConnection(); 
										urlconnection5.setRequestMethod("GET");
										br5 = new BufferedReader(new InputStreamReader(urlconnection5.getInputStream(),"UTF-8"));
										String result5 = "";
										String line5; 
										while((line5 = br5.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
											result5 = result5 + line5;
										}
										JsonParser jsonParser5 = new JsonParser();
										JsonObject k5 = (JsonObject) jsonParser5.parse(result5);
										
										//int chking = -1;
										
										JsonArray identities = (JsonArray)k5.get("participantIdentities"); 
										for(int j=0; j<identities.size(); j++) {
											JsonObject player = (JsonObject)identities.get(j);
											JsonObject detail_player = (JsonObject)player.get("player");
											String account = (String)detail_player.get("accountId").getAsString();
										
											if(accountId.equals(account)) {
												int participantId = player.get("participantId").getAsInt();
												
												JsonArray participants = (JsonArray)k5.get("participants"); 
												for(int p=0; p<participants.size(); p++) {
													JsonObject kda = (JsonObject)participants.get(p);
													int inparticipantId = kda.get("participantId").getAsInt();
													if(participantId == inparticipantId) {
														ChampKDADTO kdadto = new ChampKDADTO();
														JsonObject stats = (JsonObject)kda.get("stats"); 
															int kills = stats.get("kills").getAsInt();
															int assists = stats.get("assists").getAsInt();
															int deaths = stats.get("deaths").getAsInt();
															boolean win = stats.get("win").getAsBoolean();
															kdadto.setChampionId((select_Champ[x]));
															System.out.println("select_Champ["+x+"] : "+kills );
															kdadto.setKills(kills);
															kdadto.setAssists(assists);
															kdadto.setDeaths(deaths);
															kdadto.setWin(win);
															Mkdarr2.add(kdadto);
															
															System.out.println("222select_Champ["+x+"] : "+kills );
															
															float fwinrate2 = 0;
									                        
									                        int kill2 = 0;
									                        int death2 = 0;
									                        int assist2 = 0;
									                        int win2 = 0;
									                        int champ2 = 0;
									                        int totalgame2 = Mkdarr2.size();

									                        for(int q =0; q<Mkdarr2.size(); q++) {
									                           kill2 += Mkdarr2.get(q).getKills();
									                           death2 += Mkdarr2.get(q).getDeaths();
									                           assist2 += Mkdarr2.get(q).getAssists();
									                           champ2 = Mkdarr2.get(q).getChampionId();
									                           if(Mkdarr2.get(q).isWin() == true) {
									                              win2++;
									                           }
									                        }
									                        float fkda2 = 0;
									                        if((kill2!=0||assist2!=0) && death2!=0) {
									                           fkda2 = (kill2+assist2)/death2;
									                        }else if(death2==0){
									                           fkda2 = (kill2+assist2);
									                        }

									                        fwinrate2 = (float)win2/totalgame2*100;
									                        String winrate2 =  String.format("%.2f", fwinrate2);
									                        float kda2s = (kill2+assist2)/death2; 
									                        String kda2 = String.format("%.2f", kda2s);
									                        
									                        
									                        System.out.println("모스트2 총판="+Mkdarr2.size());
									                        System.out.println("모스트2 킬="+kill2);
															System.out.println("모스트2데스="+death2);
															System.out.println("모스트2어시="+assist2);
															System.out.println("모스트2KDA="+fkda2);
															System.out.println("승률="+winrate2);
									                        
															dto.setBest2kda(kda2);
															dto.setBest2winrate(winrate2);
															dto.setBest2total(totalgame2);
															sqlSession.update(namespace+".best2up",dto);
															   
															
															
															
														}
													}
											}
										}
									}
									 
									b2=1;
									model.addAttribute("MselectChamp2", Mkdarr2);
									countt ++;
								}else {
									for(int i=0; i<umarr.size(); i++) {
										String urlstr5 = "https://kr.api.riotgames.com/lol/match/v4/matches/"+umarr.get(i).getGameId()+"?api_key="+API_KEY;
										URL url5 = new URL(urlstr5);
										HttpURLConnection urlconnection5 = (HttpURLConnection) url5.openConnection(); 
										urlconnection5.setRequestMethod("GET");
										br5 = new BufferedReader(new InputStreamReader(urlconnection5.getInputStream(),"UTF-8"));
										String result5 = "";
										String line5; 
										while((line5 = br5.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
											result5 = result5 + line5;
										}
										JsonParser jsonParser5 = new JsonParser();
										JsonObject k5 = (JsonObject) jsonParser5.parse(result5);
										
										//int chking = -1;
										
										JsonArray identities = (JsonArray)k5.get("participantIdentities"); 
										for(int j=0; j<identities.size(); j++) {
											JsonObject player = (JsonObject)identities.get(j);
											JsonObject detail_player = (JsonObject)player.get("player");
											String account = (String)detail_player.get("accountId").getAsString();
										
											if(accountId.equals(account)) {
												int participantId = player.get("participantId").getAsInt();
												
												JsonArray participants = (JsonArray)k5.get("participants"); 
												for(int p=0; p<participants.size(); p++) {
													JsonObject kda = (JsonObject)participants.get(p);
													int inparticipantId = kda.get("participantId").getAsInt();
													if(participantId == inparticipantId) {
														ChampKDADTO kdadto = new ChampKDADTO();
														JsonObject stats = (JsonObject)kda.get("stats"); 
															int kills = stats.get("kills").getAsInt();
															int assists = stats.get("assists").getAsInt();
															int deaths = stats.get("deaths").getAsInt();
															boolean win = stats.get("win").getAsBoolean();
															kdadto.setChampionId((select_Champ[x]));
															System.out.println("select_Champ["+x+"] : "+kills );
															kdadto.setKills(kills);
															kdadto.setAssists(assists);
															kdadto.setDeaths(deaths);
															kdadto.setWin(win);
															Mkdarr3.add(kdadto);
															
															System.out.println("333select_Champ["+x+"] : "+kills );
															
															float fwinrate3 = 0;

									                        int kill3 = 0;
									                        int death3 = 0;
									                        int assist3 = 0;
									                        int win3 = 0;
									                        int champ3 = 0;
									                        int totalgame3 = Mkdarr3.size();

									                        for(int q =0; q<Mkdarr3.size(); q++) {
									                           kill3 += Mkdarr3.get(q).getKills();
									                           death3 += Mkdarr3.get(q).getDeaths();
									                           assist3 += Mkdarr3.get(q).getAssists();
									                           champ3 = Mkdarr3.get(q).getChampionId();
									                           if(Mkdarr3.get(q).isWin() == true) {
									                              win3++;
									                           }
									                        }
									                        float fkda3 = 0;
									                        if((kill3!=0||assist3!=0) && death3!=0) {
									                           fkda3 = (kill3+assist3)/death3;
									                        }else if(death3==0){
									                           fkda3 = (kill3+assist3);
									                        }

									                        fwinrate3 = (float)win3/totalgame3*100;
									                        String winrate3 =  String.format("%.2f", fwinrate3);
															
									                        System.out.println("모스트3 총판="+Mkdarr3.size());
									                        System.out.println("모스트3 킬="+kill3);
															System.out.println("모스트3데스="+death3);
															System.out.println("모스트3어시="+assist3);
															System.out.println("모스트3KDA="+fkda3);
															System.out.println("승률="+winrate3);
															
															 float kda3s = (kill3+assist3)/death3; 
										                     String kda3 = String.format("%.2f", kda3s);
															
															dto.setBest3kda(kda3);
															dto.setBest3winrate(winrate3);
															dto.setBest3total(totalgame3);
															
															sqlSession.update(namespace+".best3up",dto);
															
															
															
														}
													}
											}
										}
									}
									

									
									
									model.addAttribute("MselectChamp3", Mkdarr3);
									b3 = 1;
									
									}
								}
									else {
				
										int p = x+1;
										System.out.println(p);
										String MselectChamp = "MselectChamp"+p;
										
										
										ArrayList<ChampKDADTO> Mkdarr = new ArrayList<ChampKDADTO>();
										model.addAttribute(MselectChamp, Mkdarr);
									}
								}
							}catch(Exception e) {
								System.out.println("countt"+countt);
								
								if(countt == 2 || countt ==1) {
									ArrayList<ChampKDADTO> Mkdarr3 = new ArrayList<ChampKDADTO>();
									ArrayList<ChampKDADTO> Mkdarr2 = new ArrayList<ChampKDADTO>();
									model.addAttribute("MselectChamp2", Mkdarr2);
									model.addAttribute("MselectChamp3", Mkdarr3);
								}else if(countt == 3){
									ArrayList<ChampKDADTO> Mkdarr3 = new ArrayList<ChampKDADTO>();
									model.addAttribute("MselectChamp3", Mkdarr3);
								}
							}	
						   
						   
						   
						   
							 int total = 0;
							   String nope = "데이터없음";
								if(b1 == 0) {
									dto.setBest1kda(nope);
									dto.setBest1winrate(nope);
									dto.setBest1total(total);
									sqlSession.update(namespace+".best1up",dto);
									if(b2 == 0) {
										dto.setBest2kda(nope);
										dto.setBest2winrate(nope);
										dto.setBest2total(total);
										sqlSession.update(namespace+".best2up",dto);
										if(b3 == 0) {
											dto.setBest3kda(nope);
											dto.setBest3winrate(nope);
											dto.setBest3total(total);
											sqlSession.update(namespace+".best3up",dto);
										}
									}
								}else if(b2 == 0) {
									dto.setBest2kda(nope);
									dto.setBest2winrate(nope);
									dto.setBest2total(total);
									sqlSession.update(namespace+".best2up",dto);
									if(b3 == 0) {
										dto.setBest3kda(nope);
										dto.setBest3winrate(nope);
										dto.setBest3total(total);
										sqlSession.update(namespace+".best3up",dto);
									}
								}else if(b3 == 0) {
									dto.setBest3kda(nope);
									dto.setBest3winrate(nope);
									dto.setBest3total(total);
									sqlSession.update(namespace+".best3up",dto);
									
								}
							
							   
								sqlSession.update(namespace+".uptdate",dto);
						 
						 
						 
						 
						 
						 
						 
						 
				   }
		           
		           
			   }catch (Exception e) {
				   e.printStackTrace();
			   }
			   
			   
			   
			   
			   
			   
			   
		   
		   
		   if(dto.getBest1kda().equals("null") || dto.getBest2kda().equals("null") || dto.getBest3kda().equals("null")) {
			   
			   Map<String,Object> map = model.asMap();
				
			   System.out.println(select_Champ[0]);
			   System.out.println(select_Champ[1]);
			   System.out.println(select_Champ[2]);
				
				
				
				BufferedReader br = null;
				BufferedReader br4 = null;
				BufferedReader br5 = null;
				int countt = 1;
				
				try {
					String urlstr = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ nick +"?api_key="+API_KEY; 
					URL url = new URL(urlstr); 
					HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection(); 
					urlconnection.setRequestMethod("GET");
					br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
					//여기에 문자열을 받아와라. 
					String result = ""; 
					String line; 

					while((line = br.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
						result = result + line; }
					JsonParser jsonParser = new JsonParser(); 
					JsonObject k = (JsonObject) jsonParser.parse(result); 
					String accountId = k.get("accountId").getAsString(); 
					ArrayList<ChampKDADTO> Mkdarr1 = new ArrayList<ChampKDADTO>();
					ArrayList<ChampKDADTO> Mkdarr2 = new ArrayList<ChampKDADTO>();
					ArrayList<ChampKDADTO> Mkdarr3 = new ArrayList<ChampKDADTO>();
					
					for(int x=0; x<select_Champ.length; x++) {
					
						String urlstr4 = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/"+accountId+"?champion="+select_Champ[x]+"&queue=420&season=13&&endIndex=10&beginIndex=0&api_key="+API_KEY;
						
						URL url4 = new URL(urlstr4);
						HttpURLConnection urlconnection4 = (HttpURLConnection) url4.openConnection(); 
						urlconnection4.setRequestMethod("GET");
						br4 = new BufferedReader(new InputStreamReader(urlconnection4.getInputStream(),"UTF-8"));
						String result4 = "";
						String line4; 
						while((line4 = br4.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
							result4 = result4 + line4;
						}

						JsonParser jsonParser4 = new JsonParser();
						JsonObject aaa = (JsonObject)  jsonParser4.parse(result4); 
						JsonArray arr3 = (JsonArray) aaa.get("matches");
						ArrayList<userMatchDTO> umarr = new ArrayList<userMatchDTO>();
						
						if(arr3.size()!=0) {
							
						
						for(int i=0; i<arr3.size(); i++) {
							JsonObject k4 = (JsonObject) arr3.get(i);
							userMatchDTO umdto = new userMatchDTO();
							umdto.setGameId(k4.get("gameId").getAsLong());
							umdto.setChampion(k4.get("champion").getAsInt());
							umarr.add(umdto);
						}
						
						if(x==0) {
							
						for(int i=0; i<umarr.size(); i++) {
							String urlstr5 = "https://kr.api.riotgames.com/lol/match/v4/matches/"+umarr.get(i).getGameId()+"?api_key="+API_KEY;
							URL url5 = new URL(urlstr5);
							HttpURLConnection urlconnection5 = (HttpURLConnection) url5.openConnection(); 
							urlconnection5.setRequestMethod("GET");
							br5 = new BufferedReader(new InputStreamReader(urlconnection5.getInputStream(),"UTF-8"));
							String result5 = "";
							String line5; 
							while((line5 = br5.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
								result5 = result5 + line5;
							}
							JsonParser jsonParser5 = new JsonParser();
							JsonObject k5 = (JsonObject) jsonParser5.parse(result5);
							
							//int chking = -1;
							
							JsonArray identities = (JsonArray)k5.get("participantIdentities"); 
							for(int j=0; j<identities.size(); j++) {
								JsonObject player = (JsonObject)identities.get(j);
								JsonObject detail_player = (JsonObject)player.get("player");
								String account = (String)detail_player.get("accountId").getAsString();
							
								if(accountId.equals(account)) {
									int participantId = player.get("participantId").getAsInt();
									
									JsonArray participants = (JsonArray)k5.get("participants"); 
									for(int p=0; p<participants.size(); p++) {
										JsonObject kda = (JsonObject)participants.get(p);
										int inparticipantId = kda.get("participantId").getAsInt();
										if(participantId == inparticipantId) {
											ChampKDADTO kdadto = new ChampKDADTO();
											JsonObject stats = (JsonObject)kda.get("stats"); 
												int kills = stats.get("kills").getAsInt();
												int assists = stats.get("assists").getAsInt();
												int deaths = stats.get("deaths").getAsInt();
												boolean win = stats.get("win").getAsBoolean();
												kdadto.setChampionId((select_Champ[x]));
												
												
												System.out.println("111select_Champ["+x+"] : "+kills );
												
												kdadto.setKills(kills);
												kdadto.setAssists(assists);
												kdadto.setDeaths(deaths);
												kdadto.setWin(win);
												Mkdarr1.add(kdadto);
												
												float fwinrate = 0;
						                        
						                        int kill = 0;
						                        int death = 0;
						                        int assist = 0;
						                        int win1 = 0;
						                        int champ = 0;
						                        int totalgame = Mkdarr1.size();
						                        
						                        System.out.println("tooo"+totalgame);
						                        
						                        for(int q =0; q<Mkdarr1.size(); q++) {
						                           kill += Mkdarr1.get(q).getKills();
						                           death += Mkdarr1.get(q).getDeaths();
						                           assist += Mkdarr1.get(q).getAssists();
						                           champ = Mkdarr1.get(q).getChampionId();
						                           if(Mkdarr1.get(q).isWin() == true) {
						                              win1++;
						                           }
						                        }
						                        
						                        System.out.println("1");
						                        float kda1s = (kill+assist)/death; 
						                        String kda1 = String.format("%.2f", kda1s);
						                        
						                        System.out.println("2");
						                        fwinrate = (float)win1/totalgame*100;
						                        String winrate =  String.format("%.2f", fwinrate);
												
						                        
						                        System.out.println("모스트1 총판="+Mkdarr1.size());
						                        System.out.println("모스트1 킬="+kill);
												System.out.println("모스트1데스="+death);
												System.out.println("모스트1어시="+assist);
												System.out.println("모스트1KDA="+kda1);
												System.out.println("승률="+winrate);
												
												dto.setBest1kda(kda1);
												dto.setBest1winrate(winrate);
												dto.setBest1total(totalgame);
												sqlSession.update(namespace+".best1up",dto);
												   
												
												
												
												
												
											}
										}
									
								}
							}
						}
						
						b1=1;
						 
						model.addAttribute("MselectChamp1", Mkdarr1);
						countt ++;
					}else if(x==1) {
						for(int i=0; i<umarr.size(); i++) {
							String urlstr5 = "https://kr.api.riotgames.com/lol/match/v4/matches/"+umarr.get(i).getGameId()+"?api_key="+API_KEY;
							URL url5 = new URL(urlstr5);
							HttpURLConnection urlconnection5 = (HttpURLConnection) url5.openConnection(); 
							urlconnection5.setRequestMethod("GET");
							br5 = new BufferedReader(new InputStreamReader(urlconnection5.getInputStream(),"UTF-8"));
							String result5 = "";
							String line5; 
							while((line5 = br5.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
								result5 = result5 + line5;
							}
							JsonParser jsonParser5 = new JsonParser();
							JsonObject k5 = (JsonObject) jsonParser5.parse(result5);
							
							//int chking = -1;
							
							JsonArray identities = (JsonArray)k5.get("participantIdentities"); 
							for(int j=0; j<identities.size(); j++) {
								JsonObject player = (JsonObject)identities.get(j);
								JsonObject detail_player = (JsonObject)player.get("player");
								String account = (String)detail_player.get("accountId").getAsString();
							
								if(accountId.equals(account)) {
									int participantId = player.get("participantId").getAsInt();
									
									JsonArray participants = (JsonArray)k5.get("participants"); 
									for(int p=0; p<participants.size(); p++) {
										JsonObject kda = (JsonObject)participants.get(p);
										int inparticipantId = kda.get("participantId").getAsInt();
										if(participantId == inparticipantId) {
											ChampKDADTO kdadto = new ChampKDADTO();
											JsonObject stats = (JsonObject)kda.get("stats"); 
												int kills = stats.get("kills").getAsInt();
												int assists = stats.get("assists").getAsInt();
												int deaths = stats.get("deaths").getAsInt();
												boolean win = stats.get("win").getAsBoolean();
												kdadto.setChampionId((select_Champ[x]));
												System.out.println("select_Champ["+x+"] : "+kills );
												kdadto.setKills(kills);
												kdadto.setAssists(assists);
												kdadto.setDeaths(deaths);
												kdadto.setWin(win);
												Mkdarr2.add(kdadto);
												
												System.out.println("222select_Champ["+x+"] : "+kills );
												
												float fwinrate2 = 0;
						                        
						                        int kill2 = 0;
						                        int death2 = 0;
						                        int assist2 = 0;
						                        int win2 = 0;
						                        int champ2 = 0;
						                        int totalgame2 = Mkdarr2.size();

						                        for(int q =0; q<Mkdarr2.size(); q++) {
						                           kill2 += Mkdarr2.get(q).getKills();
						                           death2 += Mkdarr2.get(q).getDeaths();
						                           assist2 += Mkdarr2.get(q).getAssists();
						                           champ2 = Mkdarr2.get(q).getChampionId();
						                           if(Mkdarr2.get(q).isWin() == true) {
						                              win2++;
						                           }
						                        }
						                        float fkda2 = 0;
						                        if((kill2!=0||assist2!=0) && death2!=0) {
						                           fkda2 = (kill2+assist2)/death2;
						                        }else if(death2==0){
						                           fkda2 = (kill2+assist2);
						                        }

						                        fwinrate2 = (float)win2/totalgame2*100;
						                        String winrate2 =  String.format("%.2f", fwinrate2);
						                        float kda2s = (kill2+assist2)/death2; 
						                        String kda2 = String.format("%.2f", kda2s);
						                        
						                        
						                        System.out.println("모스트2 총판="+Mkdarr2.size());
						                        System.out.println("모스트2 킬="+kill2);
												System.out.println("모스트2데스="+death2);
												System.out.println("모스트2어시="+assist2);
												System.out.println("모스트2KDA="+fkda2);
												System.out.println("승률="+winrate2);
						                        
												dto.setBest2kda(kda2);
												dto.setBest2winrate(winrate2);
												dto.setBest2total(totalgame2);
												sqlSession.update(namespace+".best2up",dto);
												   
												
												
												
											}
										}
								}
							}
						}
						 
						b2=1;
						model.addAttribute("MselectChamp2", Mkdarr2);
						countt ++;
					}else {
						for(int i=0; i<umarr.size(); i++) {
							String urlstr5 = "https://kr.api.riotgames.com/lol/match/v4/matches/"+umarr.get(i).getGameId()+"?api_key="+API_KEY;
							URL url5 = new URL(urlstr5);
							HttpURLConnection urlconnection5 = (HttpURLConnection) url5.openConnection(); 
							urlconnection5.setRequestMethod("GET");
							br5 = new BufferedReader(new InputStreamReader(urlconnection5.getInputStream(),"UTF-8"));
							String result5 = "";
							String line5; 
							while((line5 = br5.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
								result5 = result5 + line5;
							}
							JsonParser jsonParser5 = new JsonParser();
							JsonObject k5 = (JsonObject) jsonParser5.parse(result5);
							
							//int chking = -1;
							
							JsonArray identities = (JsonArray)k5.get("participantIdentities"); 
							for(int j=0; j<identities.size(); j++) {
								JsonObject player = (JsonObject)identities.get(j);
								JsonObject detail_player = (JsonObject)player.get("player");
								String account = (String)detail_player.get("accountId").getAsString();
							
								if(accountId.equals(account)) {
									int participantId = player.get("participantId").getAsInt();
									
									JsonArray participants = (JsonArray)k5.get("participants"); 
									for(int p=0; p<participants.size(); p++) {
										JsonObject kda = (JsonObject)participants.get(p);
										int inparticipantId = kda.get("participantId").getAsInt();
										if(participantId == inparticipantId) {
											ChampKDADTO kdadto = new ChampKDADTO();
											JsonObject stats = (JsonObject)kda.get("stats"); 
												int kills = stats.get("kills").getAsInt();
												int assists = stats.get("assists").getAsInt();
												int deaths = stats.get("deaths").getAsInt();
												boolean win = stats.get("win").getAsBoolean();
												kdadto.setChampionId((select_Champ[x]));
												System.out.println("select_Champ["+x+"] : "+kills );
												kdadto.setKills(kills);
												kdadto.setAssists(assists);
												kdadto.setDeaths(deaths);
												kdadto.setWin(win);
												Mkdarr3.add(kdadto);
												
												System.out.println("333select_Champ["+x+"] : "+kills );
												
												float fwinrate3 = 0;

						                        int kill3 = 0;
						                        int death3 = 0;
						                        int assist3 = 0;
						                        int win3 = 0;
						                        int champ3 = 0;
						                        int totalgame3 = Mkdarr3.size();

						                        for(int q =0; q<Mkdarr3.size(); q++) {
						                           kill3 += Mkdarr3.get(q).getKills();
						                           death3 += Mkdarr3.get(q).getDeaths();
						                           assist3 += Mkdarr3.get(q).getAssists();
						                           champ3 = Mkdarr3.get(q).getChampionId();
						                           if(Mkdarr3.get(q).isWin() == true) {
						                              win3++;
						                           }
						                        }
						                        float fkda3 = 0;
						                        if((kill3!=0||assist3!=0) && death3!=0) {
						                           fkda3 = (kill3+assist3)/death3;
						                        }else if(death3==0){
						                           fkda3 = (kill3+assist3);
						                        }

						                        fwinrate3 = (float)win3/totalgame3*100;
						                        String winrate3 =  String.format("%.2f", fwinrate3);
												
						                        System.out.println("모스트3 총판="+Mkdarr3.size());
						                        System.out.println("모스트3 킬="+kill3);
												System.out.println("모스트3데스="+death3);
												System.out.println("모스트3어시="+assist3);
												System.out.println("모스트3KDA="+fkda3);
												System.out.println("승률="+winrate3);
												
												 float kda3s = (kill3+assist3)/death3; 
							                     String kda3 = String.format("%.2f", kda3s);
												
												dto.setBest3kda(kda3);
												dto.setBest3winrate(winrate3);
												dto.setBest3total(totalgame3);
												
												sqlSession.update(namespace+".best3up",dto);
												
												
												
											}
										}
								}
							}
						}
						

						
						
						model.addAttribute("MselectChamp3", Mkdarr3);
						b3 = 1;
						
						}
					}
						else {
	
							int p = x+1;
							System.out.println(p);
							String MselectChamp = "MselectChamp"+p;
							
							
							ArrayList<ChampKDADTO> Mkdarr = new ArrayList<ChampKDADTO>();
							model.addAttribute(MselectChamp, Mkdarr);
						}
					}
				}catch(Exception e) {
					System.out.println("countt"+countt);
					
					if(countt == 2 || countt ==1) {
						ArrayList<ChampKDADTO> Mkdarr3 = new ArrayList<ChampKDADTO>();
						ArrayList<ChampKDADTO> Mkdarr2 = new ArrayList<ChampKDADTO>();
						model.addAttribute("MselectChamp2", Mkdarr2);
						model.addAttribute("MselectChamp3", Mkdarr3);
					}else if(countt == 3){
						ArrayList<ChampKDADTO> Mkdarr3 = new ArrayList<ChampKDADTO>();
						model.addAttribute("MselectChamp3", Mkdarr3);
					}
				}	
			   
			   
			   
			   
				 int total = 0;
				   String nope = "데이터없음";
					if(b1 == 0) {
						dto.setBest1kda(nope);
						dto.setBest1winrate(nope);
						dto.setBest1total(total);
						sqlSession.update(namespace+".best1up",dto);
						if(b2 == 0) {
							dto.setBest2kda(nope);
							dto.setBest2winrate(nope);
							dto.setBest2total(total);
							sqlSession.update(namespace+".best2up",dto);
							if(b3 == 0) {
								dto.setBest3kda(nope);
								dto.setBest3winrate(nope);
								dto.setBest3total(total);
								sqlSession.update(namespace+".best3up",dto);
							}
						}
					}else if(b2 == 0) {
						dto.setBest2kda(nope);
						dto.setBest2winrate(nope);
						dto.setBest2total(total);
						sqlSession.update(namespace+".best2up",dto);
						if(b3 == 0) {
							dto.setBest3kda(nope);
							dto.setBest3winrate(nope);
							dto.setBest3total(total);
							sqlSession.update(namespace+".best3up",dto);
						}
					}else if(b3 == 0) {
						dto.setBest3kda(nope);
						dto.setBest3winrate(nope);
						dto.setBest3total(total);
						sqlSession.update(namespace+".best3up",dto);
						
					}
				
				   
					sqlSession.update(namespace+".uptdate",dto);
			   
				
			   
		   }
		   
		   
		  
		   
		   
		  
		   
		   
		  
	   }
	   
	   
	   
	   
	   
	   
		
		
	   
	   
	   return "updateparty";
	   
	   
   }
   
   @RequestMapping("accc")
   public String accc() {
	   return "accc";
   }
   @RequestMapping("asd")
   public String asd() {
	   return "asd";
   }
   
   
   
   
   
   
}