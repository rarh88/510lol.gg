package com.care.dto;

public class League_expDTO {
   private String queueType;
   private String summonerName;
   private int wins;
   private boolean veteran;
   private int losses;
   private String rank;
   private String tier;
   private String leagueId;
   private int leaguePoints;
   public String getQueueType() {
      return queueType;
   }
   public void setQueueType(String queueType) {
      this.queueType = queueType;
   }
   public String getSummonerName() {
      return summonerName;
   }
   public void setSummonerName(String summonerName) {
      this.summonerName = summonerName;
   }
   public int getWins() {
      return wins;
   }
   public void setWins(int wins) {
      this.wins = wins;
   }
   public boolean isVeteran() {
      return veteran;
   }
   public void setVeteran(boolean veteran) {
      this.veteran = veteran;
   }
   public int getLosses() {
      return losses;
   }
   public void setLosses(int losses) {
      this.losses = losses;
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
   public String getLeagueId() {
      return leagueId;
   }
   public void setLeagueId(String leagueId) {
      this.leagueId = leagueId;
   }
   public int getLeaguePoints() {
      return leaguePoints;
   }
   public void setLeaguePoints(int leaguePoints) {
      this.leaguePoints = leaguePoints;
   }
   
   
   public League_expDTO(String queueType, String summonerName, int wins, boolean veteran, int losses, String rank, String tier, String leagueId, int leaguePoints) {
      this.queueType = queueType;
      this.summonerName = summonerName;
      this.wins = wins;
      this.veteran = veteran;
      this.losses = losses;
      this.rank = rank;
      this.tier = tier;
      this.leagueId = leagueId;
      this.leaguePoints = leaguePoints;
   }
}