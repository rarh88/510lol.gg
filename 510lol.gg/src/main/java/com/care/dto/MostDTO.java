package com.care.dto;

public class MostDTO {
   private String nick;
   private int champ;
   private int prechamp;
   private int total;
   private String winrate;
   private String kda;
   
   
   public int getPrechamp() {
   return prechamp;
}
public void setPrechamp(int prechamp) {
   this.prechamp = prechamp;
}
public String getNick() {
      return nick;
   }
   public void setNick(String nick) {
      this.nick = nick;
   }
   public int getChamp() {
      return champ;
   }
   public void setChamp(int champ) {
      this.champ = champ;
   }
   public int getTotal() {
      return total;
   }
   public void setTotal(int total) {
      this.total = total;
   }
   public String getWinrate() {
      return winrate;
   }
   public void setWinrate(String winrate) {
      this.winrate = winrate;
   }
   public String getKda() {
      return kda;
   }
   public void setKda(String kda) {
      this.kda = kda;
   }
   
}