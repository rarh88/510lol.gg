package com.care.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.dto.League_expDTO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class TierRankImpl implements Services{

	private static final String namespace="com.care.mybatis.myMapper";
	@Autowired
	private SqlSession sqlSession;

	
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		String fqueue = request.getParameter("queue");
		String ftier = request.getParameter("tier");
		String fdivision = request.getParameter("division");
		int page = Integer.parseInt(request.getParameter("page"));
		String API_KEY = (String)map.get("APIKEY");
		BufferedReader br = null;
		
		ArrayList<League_expDTO> arr = new ArrayList<League_expDTO>();
		try {
			String urlstr = "https://kr.api.riotgames.com/lol/league-exp/v4/entries/"+fqueue+"/"+ftier+"/"+fdivision+"?page="+page+"&api_key="+API_KEY;;
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
			JsonArray jarr = (JsonArray) jsonParser.parse(result); 
			for(int i=0;i<jarr.size();i++){
				
				JsonObject k = (JsonObject) jarr.get(i);
				String queueType = k.get("queueType").getAsString();
				String summonerName = k.get("summonerName").getAsString();
				 int wins = k.get("wins").getAsInt();
				 boolean veteran = k.get("veteran").getAsBoolean();
				 int losses = k.get("losses").getAsInt();
				 String rank = k.get("rank").getAsString();
				 String tier = k.get("tier").getAsString();
				 String leagueId = k.get("leagueId").getAsString();
				 int leaguePoints = k.get("leaguePoints").getAsInt();
				 League_expDTO dto = new League_expDTO(queueType, summonerName, wins, veteran, losses, rank, tier, leagueId, leaguePoints);
				 arr.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("leagueexp", arr);
	}

}
