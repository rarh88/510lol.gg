package com.care.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.dto.LeagueEntrydto;
import com.care.dto.weeklyRotationDTO;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.sf.json.JSONArray;

@Service
public class RotationImpl implements Services {

	private static final String namespace="com.care.mybatis.myMapper";
	@Autowired
	private SqlSession sqlSession;
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		String API_KEY = (String)map.get("APIKEY");
		BufferedReader br = null;
		ArrayList<Integer> Intarr = new ArrayList<Integer>();
		ArrayList<Integer> Intarr2 = new ArrayList<Integer>();
		weeklyRotationDTO rc = new weeklyRotationDTO();

		try {
		String urlstr = "https://kr.api.riotgames.com/lol/platform/v3/champion-rotations?api_key="+API_KEY;
		URL url = new URL(urlstr);
		HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection(); 
		urlconnection.setRequestMethod("GET");
		br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
		String result = "";
		String line; 
		while((line= br.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
			result = result + line; }
		JsonParser jsonParser = new JsonParser(); 
		JsonObject k = (JsonObject) jsonParser.parse(result); 
		JsonArray jrr = (JsonArray)k.get("freeChampionIdsForNewPlayers");
		for(int j=0; j<jrr.size(); j++) {
			int a = jrr.get(j).getAsInt();
			System.out.println(a);
			Intarr.add(a);
		}
		
		JsonArray krr = (JsonArray)k.get("freeChampionIds");
		for(int q=0; q<krr.size(); q++) {
			int champid= krr.get(q).getAsInt();
			
			Intarr2.add(champid);
		}

		
		model.addAttribute("NewPlayerrotation", Intarr); 
		model.addAttribute("weeklyrotation", Intarr2); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
