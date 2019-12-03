package com.care.dto;

public class SearchDTO {
   private String queuetype;
   private String nick;
   private int total;
   private String winrate;
   private String rank;
   private String tier;
   private int score;
   private int most1;
   private int most2;
   private int most3;
   private int most4;
   private int most5;
   private int profileiconid;
   private String accountid;
   private String uptime;

   
   public String getAccountid() {
      return accountid;
   }
   public void setAccountid(String accountid) {
      this.accountid = accountid;
   }
   public String getQueuetype() {
      return queuetype;
   }
   public void setQueuetype(String queuetype) {
      this.queuetype = queuetype;
   }
   public String getNick() {
      return nick;
   }
   public void setNick(String nick) {
      this.nick = nick;
   }
   public int getTotal() {
      return total;
   }
   public void setTotal(int total) {
      this.total = total;
   }
   public String getRank() {
      return rank;
   }
   public void setRank(String rank) {
      this.rank = rank;
   }
   public String getTier() {
      return tier;
   }
   public void setTier(String tier) {
      this.tier = tier;
   }
   public int getScore() {
      return score;
   }
   public void setScore(int score) {
      this.score = score;
   }
   public int getMost1() {
      return most1;
   }
   public void setMost1(int most1) {
      this.most1 = most1;
   }
   public int getMost2() {
      return most2;
   }
   public void setMost2(int most2) {
      this.most2 = most2;
   }
   public int getMost3() {
      return most3;
   }
   public void setMost3(int most3) {
      this.most3 = most3;
   }
   public int getMost4() {
      return most4;
   }
   public void setMost4(int most4) {
      this.most4 = most4;
   }
   public int getMost5() {
      return most5;
   }
   public void setMost5(int most5) {
      this.most5 = most5;
   }
   public String getWinrate() {
      return winrate;
   }
   public void setWinrate(String winrate) {
      this.winrate = winrate;
   }
   public int getProfileiconid() {
      return profileiconid;
   }
   public void setProfileiconid(int profileiconid) {
      this.profileiconid = profileiconid;
   }
   public String getUptime() {
      return uptime;
   }
   public void setUptime(String uptime) {
      this.uptime = uptime;
   }
   
   
}