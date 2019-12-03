package com.care.service;




import java.io.BufferedReader;
import java.io.IOException;
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

import com.care.dto.ChampKDADTO;
import com.care.dto.ChampMasteryDTO;
import com.care.dto.DTO;
import com.care.dto.LeagueEntrydto;
import com.care.dto.Most5KdaDTO;
import com.care.dto.MostCham;
import com.care.dto.MostDTO;
import com.care.dto.SearchDTO;
import com.care.dto.SearchnonDTO;
import com.care.dto.userDTO;
import com.care.dto.userMatchDTO;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Service
public class FindUserImpl implements Services{
   
   private static final String namespace="com.care.mybatis.myMapper";
   @Autowired
   private SqlSession sqlSession;
   
   @Override
   public void execute(Model model) {
     
      // TODO Auto-generated method stub
      Map<String, Object> map = model.asMap();
      HttpServletRequest request = (HttpServletRequest)map.get("request");
      String nickname = request.getParameter("nickname");
      nickname = nickname.replaceAll(" ","");
      String nick= request.getParameter("nickname");
      nick = nick.replaceAll(" ","");
      String API_KEY = (String)map.get("APIKEY");
      BufferedReader br = null;
      BufferedReader br2 = null;
      BufferedReader br3 = null;
      BufferedReader br4 = null;
      BufferedReader br5 = null;
      userDTO temp = null;
      SearchDTO sdto = new SearchDTO();
      SearchDTO tdto = new SearchDTO();
      SearchDTO ndto = new SearchDTO();
      MostCham moo = null;
      String id = null;
      String accountId = null;
      ArrayList<MostCham> mostC = new ArrayList<MostCham>();  
      ArrayList<LeagueEntrydto> leaguearr = new ArrayList<LeagueEntrydto>();
      MostDTO mo1kda = new MostDTO();
      MostDTO mo2kda = new MostDTO();
      MostDTO mo3kda = new MostDTO();
      MostDTO mo4kda = new MostDTO();
      MostDTO mo5kda = new MostDTO();
      
      

         SearchnonDTO nomemberdto = sqlSession.selectOne(namespace+".findnonemember03", nick);
         if(nomemberdto==null) {
            
            try{ 
         
               // nickname 검색
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

               
               
               int profileIconId = k.get("profileIconId").getAsInt();
               String name = k.get("name").getAsString(); 
               String puuid = k.get("puuid").getAsString(); 
               long summonerLevel = k.get("summonerLevel").getAsLong();
               long revisionDate = k.get("revisionDate").getAsLong(); 
               id = k.get("id").getAsString();
               accountId = k.get("accountId").getAsString(); 
               temp = new userDTO(profileIconId,name,puuid,summonerLevel,revisionDate,id,accountId); 
               sdto.setAccountid(accountId);
               tdto.setAccountid(accountId);
               ndto.setAccountid(accountId);
               sdto.setProfileiconid(profileIconId);
               tdto.setProfileiconid(profileIconId);
             
            // nickname으로 랭크, 티어 , 점수 출력   
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
                  
                  }
                  
                        // Most5 출력   
                  String urlstr3 = "https://kr.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/"+id+"?api_key="+API_KEY;
                  URL url3 = new URL(urlstr3);
                  HttpURLConnection urlconnection3 = (HttpURLConnection) url3.openConnection(); 
                  urlconnection3.setRequestMethod("GET");
                  br3 = new BufferedReader(new InputStreamReader(urlconnection3.getInputStream(),"UTF-8"));
                  String result3 = "";
                  String line3; 
                  while((line3 = br3.readLine()) != null) { // 그 받아온 문자열을 계속 br에서 줄단위로 받고 출력하겠다.
                     result3 = result3 + line3;
                  }
                  JsonParser jsonParser3 = new JsonParser();
                  JsonArray arr2 = (JsonArray) jsonParser3.parse(result3); 
                  int g = arr2.size();
                  
                  for(int i=0;i<g;i++){
                     JsonObject k3 = (JsonObject) arr2.get(i);
                     int most = k3.get("championId").getAsInt();
                     moo = new MostCham(most);
                     mostC.add(moo);
                     
                  }
           
                  sdto.setNick(nick);
                  tdto.setNick(nick);
               
                  int most1;
                  int most2;
                  int most3;
                  int most4;
                  int most5;
                  for(int j=0; j<mostC.size(); j++) {
                     if(j==0) {
                        most1 = mostC.get(j).getMost1();
                        sdto.setMost1(most1);
                        tdto.setMost1(most1);
                       
                     }else if(j==1){
                        most2 = mostC.get(j).getMost1();
                        sdto.setMost2(most2);
                        tdto.setMost2(most2);
                       
                     }else if(j==2) {
                        most3 = mostC.get(j).getMost1();
                        sdto.setMost3(most3);
                        tdto.setMost3(most3);
                      
                     }else if(j==3) {
                        most4 = mostC.get(j).getMost1();
                        sdto.setMost4(most4);
                        tdto.setMost4(most4);
                        
                     }
                     else if(j==4) {
                        most5 = mostC.get(j).getMost1();
                        sdto.setMost5(most5);
                        tdto.setMost5(most5);
                      
                     }else {
                        
                     }
                  }
                  
                  for(int i=0;i<leaguearr.size();i++) {
                     System.out.println(leaguearr.get(i).getQueueType());
                     if(leaguearr.get(i).getQueueType().equals("RANKED_SOLO_5x5")) {
                        
                        int total = leaguearr.get(i).getWins() + leaguearr.get(i).getLosses();
                        float fwinrate = (float)leaguearr.get(i).getWins()/total;
                        String winrate = String.format("%.2f", fwinrate);
                        sdto.setQueuetype(leaguearr.get(i).getQueueType()); 
                        
                        sdto.setTotal(total);
                        sdto.setWinrate(winrate);
                        sdto.setRank(leaguearr.get(i).getRank());
                        sdto.setTier(leaguearr.get(i).getTier());
                        sdto.setScore(leaguearr.get(i).getLeaguePoints());
                  
                           
                        }else if(leaguearr.get(i).getQueueType().equals("RANKED_FLEX_SR")) {
                           
                             int total = leaguearr.get(i).getWins() + leaguearr.get(i).getLosses();
                             float fwinrate = (float)leaguearr.get(i).getWins()/total;
                              String winrate = String.format("%.2f", fwinrate);
                              tdto.setQueuetype(leaguearr.get(i).getQueueType()); 
                              tdto.setTotal(total);
                              tdto.setWinrate(winrate);
                              tdto.setRank(leaguearr.get(i).getRank());
                              tdto.setTier(leaguearr.get(i).getTier());
                              tdto.setScore(leaguearr.get(i).getLeaguePoints());
                        }
                     }
                     
          
                  System.out.println("========"+sdto.getNick());
                  model.addAttribute("imgURL", "http://ddragon.leagueoflegends.com/cdn/9.16.1/img/profileicon/"+temp.getProfileiconid()+".png");
                  model.addAttribute("leagueInfo", sdto); 
                  System.out.println(sdto);
                  System.out.println(tdto);
                
                     
                  System.out.println("-------------"+sdto.getMost5());
             
        
               System.out.println(accountId);
               ArrayList<ChampKDADTO> kdarr1 = new ArrayList<ChampKDADTO>();
               ArrayList<ChampKDADTO> kdarr2 = new ArrayList<ChampKDADTO>();
               ArrayList<ChampKDADTO> kdarr3 = new ArrayList<ChampKDADTO>();
               ArrayList<ChampKDADTO> kdarr4 = new ArrayList<ChampKDADTO>();
               ArrayList<ChampKDADTO> kdarr5 = new ArrayList<ChampKDADTO>();
               
               String urlstr4 = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/"+accountId+"?endIndex=50&beginIndex=0&api_key="+API_KEY;
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
               ArrayList<userMatchDTO> umarr1 = new ArrayList<userMatchDTO>();
               ArrayList<userMatchDTO> umarr2 = new ArrayList<userMatchDTO>();
               ArrayList<userMatchDTO> umarr3 = new ArrayList<userMatchDTO>();
               ArrayList<userMatchDTO> umarr4 = new ArrayList<userMatchDTO>();
               ArrayList<userMatchDTO> umarr5 = new ArrayList<userMatchDTO>();
               
         
               for(int i=0; i<arr3.size(); i++) {
                  JsonObject k4 = (JsonObject) arr3.get(i);
                  if(sdto.getMost1() == k4.get("champion").getAsInt()) {
                     userMatchDTO umdto1 = new userMatchDTO();
                     umdto1.setGameId(k4.get("gameId").getAsLong());
                     umdto1.setChampion(k4.get("champion").getAsInt());
                     umarr1.add(umdto1);
                  }else if(sdto.getMost2() == k4.get("champion").getAsInt()) {
                     userMatchDTO umdto2 = new userMatchDTO();
                     umdto2.setGameId(k4.get("gameId").getAsLong());
                     umdto2.setChampion(k4.get("champion").getAsInt());
                     umarr2.add(umdto2);
                  }else if(sdto.getMost3() == k4.get("champion").getAsInt()) {
                     userMatchDTO umdto3 = new userMatchDTO();
                     umdto3.setGameId(k4.get("gameId").getAsLong());
                     umdto3.setChampion(k4.get("champion").getAsInt());
                     umarr3.add(umdto3);
                  }else if(sdto.getMost4() == k4.get("champion").getAsInt()) {
                     userMatchDTO umdto4 = new userMatchDTO();
                     umdto4.setGameId(k4.get("gameId").getAsLong());
                     umdto4.setChampion(k4.get("champion").getAsInt());
                     umarr4.add(umdto4);
                  }else if(sdto.getMost5() == k4.get("champion").getAsInt()) {
                     userMatchDTO umdto5 = new userMatchDTO();
                     umdto5.setGameId(k4.get("gameId").getAsLong());
                     umdto5.setChampion(k4.get("champion").getAsInt());
                     umarr5.add(umdto5);
                  }
               }
               
               
               for(int z=0; z<5; z++) {
                  if(z==0) {
                     for(int i=0; i<umarr1.size(); i++) {
                     String urlstr5 = "https://kr.api.riotgames.com/lol/match/v4/matches/"+umarr1.get(i).getGameId()+"?api_key="+API_KEY;
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
                        int participantId = 0;
                        if(accountId.equals(account)) {
                           participantId = player.get("participantId").getAsInt();
                        }
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
                                    kdadto.setChampionId(umarr1.get(0).getChampion());
                                    System.out.println(umarr1.get(0).getChampion());
                                    System.out.println("Most1 : "+kills );
                                    System.out.println("Pppp"+p);
                                    kdadto.setKills(kills);
                                    kdadto.setAssists(assists);
                                    kdadto.setDeaths(deaths);
                                    kdadto.setWin(win);
                                    kdarr1.add(kdadto);
                                    
                                    
                                 }
                              }
                           
                        }
                        
                     
                     System.out.println("karr1"+kdarr1.size());
                        
      
                    }
                     float fwinrate = 0;
                     
                     int kill = 0;
                     int death = 0;
                     int assist = 0;
                     int win = 0;
                     int champ = 0;
                     int totalgame = kdarr1.size();
                     System.out.println("tooo"+totalgame);
                     for(int q =0; q<kdarr1.size(); q++) {
                        kill += kdarr1.get(q).getKills();
                        death += kdarr1.get(q).getDeaths();
                        assist += kdarr1.get(q).getAssists();
                        champ = kdarr1.get(q).getChampionId();
                        if(kdarr1.get(q).isWin() == true) {
                           win++;
                        }
                     }
                     float fkda = 0;
                     if((kill!=0||assist!=0) && death!=0) {
                        fkda = (kill+assist)/death;
                     }else if(death==0){
                        fkda = (kill+assist);
                     }
                     String kda = String.format("%.2f", fkda);
                     fwinrate = (float)win/totalgame;
                     String winrate =  String.format("%.2f", fwinrate);
                    
                     mo1kda.setNick(nick);
                     mo1kda.setChamp(sdto.getMost1());
                     mo1kda.setTotal(totalgame);
                     mo1kda.setWinrate(winrate);
                     mo1kda.setKda(kda);
                   
                    

                  }else if(z==1) {
                        for(int i=0; i<umarr2.size(); i++) {
                        String urlstr5 = "https://kr.api.riotgames.com/lol/match/v4/matches/"+umarr2.get(i).getGameId()+"?api_key="+API_KEY;
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
                                       kdadto.setChampionId(umarr2.get(0).getChampion());
                                       System.out.println(sdto.getMost2());
                                       System.out.println("Most2 : "+kills);
                                       kdadto.setKills(kills);
                                       kdadto.setAssists(assists);
                                       kdadto.setDeaths(deaths);
                                       kdadto.setWin(win);
                                       kdarr2.add(kdadto);
                                       
                                       
                                    }
                                 }
                              
                              }
                           }
                        }
                        float fwinrate = 0;
                        
                        int kill = 0;
                        int death = 0;
                        int assist = 0;
                        int win = 0;
                        int champ = 0;
                        int totalgame = kdarr2.size();
                        System.out.println("tooo"+totalgame);
                        for(int q =0; q<kdarr2.size(); q++) {
                           kill += kdarr2.get(q).getKills();
                           death += kdarr2.get(q).getDeaths();
                           assist += kdarr2.get(q).getAssists();
                           champ = kdarr2.get(q).getChampionId();
                           if(kdarr2.get(q).isWin() == true) {
                              win++;
                           }
                        }
                        float fkda = 0;
                        if((kill!=0||assist!=0) && death!=0) {
                           fkda = (kill+assist)/death;
                        }else if(death==0){
                           fkda = (kill+assist);
                        }
                        String kda = String.format("%.2f", fkda);
                        fwinrate = (float)win/totalgame;
                        String winrate =  String.format("%.2f", fwinrate);
                      
                        mo2kda.setNick(nick);
                        mo2kda.setChamp(sdto.getMost2());
                        mo2kda.setTotal(totalgame);
                        mo2kda.setWinrate(winrate);
                        mo2kda.setKda(kda);
                        
                    
                           
                  }
                  else if(z==2) {
                     for(int i=0; i<umarr3.size(); i++) {
                        String urlstr5 = "https://kr.api.riotgames.com/lol/match/v4/matches/"+umarr3.get(i).getGameId()+"?api_key="+API_KEY;
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
                                       System.out.println("Most3 : "+kills);
                                       kdadto.setChampionId(umarr3.get(0).getChampion());
                                       System.out.println(sdto.getMost3());
                                       kdadto.setKills(kills);
                                       kdadto.setAssists(assists);
                                       kdadto.setDeaths(deaths);
                                       kdadto.setWin(win);
                                       kdarr3.add(kdadto);
                                       
                                       
                                    }
                                 }
                              
                              }
                           }
                        }
                     float fwinrate = 0;
                     
                     int kill = 0;
                     int death = 0;
                     int assist = 0;
                     int win = 0;
                     int champ = 0;
                     int totalgame = kdarr3.size();
                     System.out.println("tooo"+totalgame);
                     for(int q =0; q<kdarr3.size(); q++) {
                        kill += kdarr3.get(q).getKills();
                        death += kdarr3.get(q).getDeaths();
                        assist += kdarr3.get(q).getAssists();
                        champ = kdarr3.get(q).getChampionId();
                        if(kdarr3.get(q).isWin() == true) {
                           win++;
                        }
                     }
                     float fkda = 0;
                     if((kill!=0||assist!=0) && death!=0) {
                        fkda = (kill+assist)/death;
                     }else if(death==0){
                        fkda = (kill+assist);
                     }
                     String kda = String.format("%.2f", fkda);
                     fwinrate = (float)win/totalgame;
                     String winrate =  String.format("%.2f", fwinrate);
                   
                     mo3kda.setNick(nick);
                     mo3kda.setChamp(sdto.getMost3());
                     mo3kda.setTotal(totalgame);
                     mo3kda.setWinrate(winrate);
                     mo3kda.setKda(kda);
                    
                  }
                  else if(z==3) {
                     for(int i=0; i<umarr4.size(); i++) {
                        String urlstr5 = "https://kr.api.riotgames.com/lol/match/v4/matches/"+umarr4.get(i).getGameId()+"?api_key="+API_KEY;
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
                                       System.out.println("Most4 : "+kills);
                                       kdadto.setChampionId(umarr4.get(0).getChampion());
                                       System.out.println(sdto.getMost4());
                                       kdadto.setKills(kills);
                                       kdadto.setAssists(assists);
                                       kdadto.setDeaths(deaths);
                                       kdadto.setWin(win);
                                       kdarr4.add(kdadto);
                                       
                                       
                                    }
                                 }
                              
                              }
                           }
                        }
                     float fwinrate = 0;
                     
                     int kill = 0;
                     int death = 0;
                     int assist = 0;
                     int win = 0;
                     int champ = 0;
                     int totalgame = kdarr4.size();
                     System.out.println("tooo"+totalgame);
                     for(int q =0; q<kdarr4.size(); q++) {
                        kill += kdarr4.get(q).getKills();
                        death += kdarr4.get(q).getDeaths();
                        assist += kdarr4.get(q).getAssists();
                        champ = kdarr4.get(q).getChampionId();
                        if(kdarr4.get(q).isWin() == true) {
                           win++;
                        }
                     }
                     float fkda = 0;
                     if((kill!=0||assist!=0) && death!=0) {
                        fkda = (kill+assist)/death;
                     }else if(death==0){
                        fkda = (kill+assist);
                     }
                     String kda = String.format("%.2f", fkda);
                     fwinrate = (float)win/totalgame;
                     String winrate =  String.format("%.2f", fwinrate);
                    
                     mo4kda.setNick(nick);
                     mo4kda.setChamp(sdto.getMost4());
                     mo4kda.setTotal(totalgame);
                     mo4kda.setWinrate(winrate);
                     mo4kda.setKda(kda);
                     
                   
                  
                  }else {
                     for(int i=0; i<umarr5.size(); i++) {
                        String urlstr5 = "https://kr.api.riotgames.com/lol/match/v4/matches/"+umarr5.get(i).getGameId()+"?api_key="+API_KEY;
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
                                       System.out.println("Most5 : "+kills);
                                       
                                       kdadto.setChampionId(umarr5.get(0).getChampion());
                                       System.out.println(sdto.getMost5());
                                       kdadto.setKills(kills);
                                       kdadto.setAssists(assists);
                                       kdadto.setDeaths(deaths);
                                       kdadto.setWin(win);
                                       kdarr5.add(kdadto);
                                       
                                       
                                    }
                                 }
                              
                              }
                           }
                        }
                     float fwinrate = 0;
                     
                     int kill = 0;
                     int death = 0;
                     int assist = 0;
                     int win = 0;
                     int champ = 0;
                     int totalgame = kdarr5.size();
                     System.out.println("tooo"+totalgame);
                     for(int q =0; q<kdarr5.size(); q++) {
                        kill += kdarr5.get(q).getKills();
                        death += kdarr5.get(q).getDeaths();
                        assist += kdarr5.get(q).getAssists();
                        champ = kdarr5.get(q).getChampionId();
                        if(kdarr5.get(q).isWin() == true) {
                           win++;
                        }
                     }
                     float fkda = 0;
                     if((kill!=0||assist!=0) && death!=0) {
                        fkda = (kill+assist)/death;
                     }else if(death==0){
                        fkda = (kill+assist);
                     }
                     String kda = String.format("%.2f", fkda);
                     fwinrate = (float)win/totalgame;
                     String winrate =  String.format("%.2f", fwinrate);
                   
                     mo5kda.setNick(nick);
                     mo5kda.setChamp(sdto.getMost5());
                     mo5kda.setTotal(totalgame);
                     mo5kda.setWinrate(winrate);
                     mo5kda.setKda(kda);
                     System.out.println(sdto.getMost5());

                  }
               }
              
               System.out.println("============================"+mo1kda.getChamp());
               sqlSession.insert(namespace+".kdaInsert", mo1kda);
               System.out.println(mo1kda.getChamp());
               model.addAttribute("smo1kda", mo1kda);
               sqlSession.insert(namespace+".kdaInsert", mo2kda);
               System.out.println(mo2kda.getChamp());
               model.addAttribute("smo2kda", mo2kda);
               sqlSession.insert(namespace+".kdaInsert", mo3kda);
               System.out.println(mo3kda.getChamp());
               model.addAttribute("smo3kda", mo3kda);
               sqlSession.insert(namespace+".kdaInsert", mo4kda);
               System.out.println(mo4kda.getChamp());
               model.addAttribute("smo4kda", mo4kda);
               
               sqlSession.insert(namespace+".kdaInsert", mo5kda);
               model.addAttribute("smo5kda", mo5kda);
               if(sdto.getQueuetype()!=null && mo5kda.getKda() != null) {
                   sqlSession.insert(namespace+".nonmember03", sdto);
                }else if(sdto.getQueuetype()==null && tdto.getQueuetype()!=null && mo5kda.getKda() != null) {
                   sqlSession.insert(namespace+".nonmember03", tdto);
                }
               String Found="yes";
               model.addAttribute("Found", Found);
               DTO haveDTO = sqlSession.selectOne(namespace+".findmember03", nick);
               if(haveDTO!=null) {
                  model.addAttribute("msg", haveDTO.getNick());
               }
            }
             catch(IOException e) {
            	 String Found="no";
                 model.addAttribute("Found", Found);
            }
           
            }else { // === 비회원이 테이블에 존재할 경우
               System.out.println("@@@@@@@@@@@@@@");
               System.out.println(nomemberdto.getMost1());
               System.out.println(nomemberdto.getMost2());
               System.out.println(nomemberdto.getMost3());
               System.out.println(nomemberdto.getMost4());
               System.out.println(nomemberdto.getMost5());
            
              String ni = nomemberdto.getNick();
              
              ChampMasteryDTO cmdto = new ChampMasteryDTO();
              cmdto.setNick(nick);
              ChampMasteryDTO m1dto = sqlSession.selectOne(namespace+".findMost1", cmdto);
             ChampMasteryDTO m2dto = sqlSession.selectOne(namespace+".findMost2", cmdto);
             ChampMasteryDTO m3dto = sqlSession.selectOne(namespace+".findMost3", cmdto);
              ChampMasteryDTO m4dto = sqlSession.selectOne(namespace+".findMost4", cmdto);
              ChampMasteryDTO m5dto = sqlSession.selectOne(namespace+".findMost5", cmdto);
    
              
              
               
            model.addAttribute("finduser", nomemberdto);
            model.addAttribute("m1dto", m1dto);
            model.addAttribute("m2dto", m2dto);
            model.addAttribute("m3dto", m3dto);
            model.addAttribute("m4dto", m4dto);
            model.addAttribute("m5dto", m5dto);
            model.addAttribute("imgURL", "http://ddragon.leagueoflegends.com/cdn/9.16.1/img/profileicon/"+nomemberdto.getProfileiconid()+".png");
            String Found="yes";
            model.addAttribute("Found", Found);
            DTO haveDTO = sqlSession.selectOne(namespace+".findmember03", nick);
            if(haveDTO!=null) {
               model.addAttribute("msg", haveDTO.getNick());
            }
            
            }
            
          
            

   }
}