package com.care.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.dto.ChampKDADTO;
import com.care.dto.userMatchDTO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
@Service
public class MactherSelectChampImpl implements Services {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String,Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String nickname = request.getParameter("sid");
		String API_KEY = (String)map.get("APIKEY");
		String[] select_Champ = null;
		if(request.getParameterValues("Top_champ")!=null) {
			select_Champ = request.getParameterValues("Top_champ");
		}else if(request.getParameterValues("Jungle_champ")!=null) {
			select_Champ = request.getParameterValues("Jungle_champ");
		}else if(request.getParameterValues("Mid_champ")!=null) {
			select_Champ = request.getParameterValues("Mid_champ");
		}else if(request.getParameterValues("Ad_champ")!=null) {
			select_Champ = request.getParameterValues("Ad_champ");
		}else if(request.getParameterValues("Sup_champ")!=null) {
			select_Champ = request.getParameterValues("Sup_champ");
		}
		
		BufferedReader br = null;
		BufferedReader br4 = null;
		BufferedReader br5 = null;
		int countt = 1;
		
		try {
			String urlstr = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+ nickname +"?api_key="+API_KEY; 
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
				System.out.println(select_Champ[x]);
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
				System.out.println(arr3);
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
										kdadto.setChampionId(Integer.parseInt(select_Champ[x]));
										System.out.println("select_Champ["+x+"] : "+kills );
										System.out.println(select_Champ[x]);
										kdadto.setKills(kills);
										kdadto.setAssists(assists);
										kdadto.setDeaths(deaths);
										kdadto.setWin(win);
										Mkdarr1.add(kdadto);
			
										
									}
								}
						}
					}
				}
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
										kdadto.setChampionId(Integer.parseInt(select_Champ[x]));
										System.out.println("select_Champ["+x+"] : "+kills );
										kdadto.setKills(kills);
										kdadto.setAssists(assists);
										kdadto.setDeaths(deaths);
										kdadto.setWin(win);
										Mkdarr2.add(kdadto);
										
										
									}
								}
						}
					}
				}
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
										kdadto.setChampionId(Integer.parseInt(select_Champ[x]));
										System.out.println("select_Champ["+x+"] : "+kills );
										kdadto.setKills(kills);
										kdadto.setAssists(assists);
										kdadto.setDeaths(deaths);
										kdadto.setWin(win);
										Mkdarr3.add(kdadto);
										
										
									}
								}
						}
					}
				}
				model.addAttribute("MselectChamp3", Mkdarr3);
			}
			}
				else {
					int p = x+1;
					System.out.println(p);
					String MselectChamp = "MselectChamp"+p;
					System.out.println(MselectChamp);
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
	
	}
 
}
