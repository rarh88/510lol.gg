package com.care.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.dto.ChampKDADTO;
import com.care.dto.Most5KdaDTO;

@Service
public class CheckKdaImpl implements Services{

   @Override
   public void execute(Model model) {
      // TODO Auto-generated method stub

      float fwinrate = 0;
      for(int z=0; z<5; z++) {
         if(z==0) {         
         int kill = 0;
         int death = 0;
         int assist = 0;
         int win = 0;      
            Map<String, Object>map = model.asMap();
            ArrayList<ChampKDADTO> most1Kda = (ArrayList<ChampKDADTO>)map.get("kdarr1");
            int totalgame = most1Kda.size();
            for(int q =0; q<most1Kda.size(); q++) {
               kill += most1Kda.get(q).getKills();
               death += most1Kda.get(q).getDeaths();
               assist += most1Kda.get(q).getAssists();
               if(most1Kda.get(q).isWin() == true) {
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
            Most5KdaDTO mo1kda = new Most5KdaDTO();
            mo1kda.setWin(win);
            mo1kda.setGameCount(totalgame);
            mo1kda.setKill(kill);
            mo1kda.setAssist(assist);
            mo1kda.setDeath(death);
            mo1kda.setWinrate(winrate);
            mo1kda.setKill(kill);
            mo1kda.setKda(kda);
            model.addAttribute("mo1kda", mo1kda);
         }
         else if(z==1) {   
            int kill = 0;
            int death = 0;
            int assist = 0;
            int win = 0;
            Map<String, Object>map = model.asMap();
            ArrayList<ChampKDADTO> most2Kda = (ArrayList<ChampKDADTO>)map.get("kdarr2");
            int totalgame = most2Kda.size();
            for(int q =0; q<most2Kda.size(); q++) {
               kill += most2Kda.get(q).getKills();
               death += most2Kda.get(q).getDeaths();
               assist += most2Kda.get(q).getAssists();
               if(most2Kda.get(q).isWin() == true) {
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
            Most5KdaDTO mo2kda = new Most5KdaDTO();
            mo2kda.setWin(win);
            mo2kda.setGameCount(totalgame);
            mo2kda.setKill(kill);
            mo2kda.setAssist(assist);
            mo2kda.setDeath(death);
            mo2kda.setWinrate(winrate);
            mo2kda.setKill(kill);
            mo2kda.setKda(kda);
            model.addAttribute("mo2kda", mo2kda);
         }else if(z==2) {
            int kill = 0;
            int death = 0;
            int assist = 0;
            int win = 0;
            Map<String, Object>map = model.asMap();
            ArrayList<ChampKDADTO> most3Kda = (ArrayList<ChampKDADTO>)map.get("kdarr3");
            int totalgame = most3Kda.size();
            for(int q =0; q<most3Kda.size(); q++) {
               kill += most3Kda.get(q).getKills();
               death += most3Kda.get(q).getDeaths();
               assist += most3Kda.get(q).getAssists();
               if(most3Kda.get(q).isWin() == true) {
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
            Most5KdaDTO mo3kda = new Most5KdaDTO();
            mo3kda.setWin(win);
            mo3kda.setGameCount(totalgame);
            mo3kda.setKill(kill);
            mo3kda.setAssist(assist);
            mo3kda.setDeath(death);
            mo3kda.setWinrate(winrate);
            mo3kda.setKill(kill);
            mo3kda.setKda(kda);
            model.addAttribute("mo3kda", mo3kda);
         }
               
      else if(z==3) {   
         int kill = 0;
         int death = 0;
         int assist = 0;
         int win = 0;
         Map<String, Object>map = model.asMap();
         ArrayList<ChampKDADTO> most4Kda = (ArrayList<ChampKDADTO>)map.get("kdarr4");
         int totalgame = most4Kda.size();
         for(int q =0; q<most4Kda.size(); q++) {
            kill += most4Kda.get(q).getKills();
            death += most4Kda.get(q).getDeaths();
            assist += most4Kda.get(q).getAssists();
            if(most4Kda.get(q).isWin() == true) {
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
         Most5KdaDTO mo4kda = new Most5KdaDTO();
         mo4kda.setWin(win);
         mo4kda.setGameCount(totalgame);
         mo4kda.setKill(kill);
         mo4kda.setAssist(assist);
         mo4kda.setDeath(death);
         mo4kda.setWinrate(winrate);
         mo4kda.setKill(kill);
         mo4kda.setKda(kda);
         model.addAttribute("mo4kda", mo4kda);
         
      }else {
         int kill = 0;
         int death = 0;
         int assist = 0;
         int win = 0;
         Map<String, Object>map = model.asMap();
         ArrayList<ChampKDADTO> most5Kda = (ArrayList<ChampKDADTO>)map.get("kdarr5");
         int totalgame = most5Kda.size();
         for(int q =0; q<most5Kda.size(); q++) {
            kill += most5Kda.get(q).getKills();
            death += most5Kda.get(q).getDeaths();
            assist += most5Kda.get(q).getAssists();
            if(most5Kda.get(q).isWin() == true) {
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
         Most5KdaDTO mo5kda = new Most5KdaDTO();
         mo5kda.setWin(win);
         mo5kda.setGameCount(totalgame);
         mo5kda.setKill(kill);
         mo5kda.setAssist(assist);
         mo5kda.setDeath(death);
         mo5kda.setWinrate(winrate);
         mo5kda.setKill(kill);
         mo5kda.setKda(kda);
         model.addAttribute("mo5kda", mo5kda);
      }
      }
   }

}