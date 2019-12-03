package com.care.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.dto.ChampKDADTO;
import com.care.dto.Most5KdaDTO;

@Service
public class MatchingKdaImpl implements Services {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		float fwinrate = 0;
		for(int z=0; z<3; z++) {
			if(z==0) {			
			int kill = 0;
			int death = 0;
			int assist = 0;
			int win = 0;	
			int totalgame = 0;
			int championId = 0;
				Map<String, Object>map = model.asMap();

				ArrayList<ChampKDADTO> most1Kda = (ArrayList<ChampKDADTO>)map.get("MselectChamp1");
								
				if(!most1Kda.isEmpty()) {
					totalgame = most1Kda.size();
					championId = most1Kda.get(0).getChampionId();
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
				mo1kda.setChampionId(championId);
				mo1kda.setWin(win);
				mo1kda.setGameCount(totalgame);
				mo1kda.setKill(kill);
				mo1kda.setAssist(assist);
				mo1kda.setDeath(death);
				mo1kda.setWinrate(winrate);
				mo1kda.setKill(kill);
				mo1kda.setKda(kda);
				model.addAttribute("selectChamp1", mo1kda);
				}else {
					System.out.println("전적없음");
				}
			}
			else if(z==1) {	
				int kill = 0;
				int death = 0;
				int assist = 0;
				int win = 0;
				int totalgame = 0;
				int championId = 0;
				Map<String, Object>map = model.asMap();
			
				ArrayList<ChampKDADTO> most2Kda = (ArrayList<ChampKDADTO>)map.get("MselectChamp2");
				
				if(!most2Kda.isEmpty()) {
					totalgame = most2Kda.size();
					championId = most2Kda.get(0).getChampionId();
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
				mo2kda.setChampionId(championId);
				mo2kda.setWin(win);
				mo2kda.setGameCount(totalgame);
				mo2kda.setKill(kill);
				mo2kda.setAssist(assist);
				mo2kda.setDeath(death);
				mo2kda.setWinrate(winrate);
				mo2kda.setKill(kill);
				mo2kda.setKda(kda);
				model.addAttribute("selectChamp2", mo2kda);
				}else {
					System.out.println("전적없음");
				}
			}else if(z==2) {
				int kill = 0;
				int death = 0;
				int assist = 0;
				int win = 0;
				int totalgame = 0;
				int championId = 0;
				Map<String, Object>map = model.asMap();
			
				ArrayList<ChampKDADTO> most3Kda = (ArrayList<ChampKDADTO>)map.get("MselectChamp3");
			
				
				if(!most3Kda.isEmpty()) {
					totalgame = most3Kda.size();
					championId = most3Kda.get(0).getChampionId();
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
				fwinrate = (float)win/totalgame*100;
				String winrate =  String.format("%.2f", fwinrate);
				Most5KdaDTO mo3kda = new Most5KdaDTO();
				mo3kda.setChampionId(championId);
				mo3kda.setWin(win);
				mo3kda.setGameCount(totalgame);
				mo3kda.setKill(kill);
				mo3kda.setAssist(assist);
				mo3kda.setDeath(death);
				mo3kda.setWinrate(winrate);
				mo3kda.setKill(kill);
				mo3kda.setKda(kda);
				model.addAttribute("selectChamp3", mo3kda);
			}else {
				System.out.println("전적없음!!");
			}
			}
				
		}
	}

}
