package com.care.dto;

public class userDTO {
	private int profileiconid;
	private String name;
	private String puuid;
	private long summonerLevel; 
	private long revisionDate;
	private String id;
	private String accountid;
	public userDTO(int profileiconid,String name, String puuid,long summonerLevel, long revisionDate, String id, String accountid) {
		this.profileiconid = profileiconid;
		this.name = name;
		this.puuid = puuid;
		this.summonerLevel = summonerLevel;
		this.revisionDate = revisionDate;
		this.id = id;
		this.accountid = accountid;
		
	}
	
	
	public int getProfileiconid() {
		return profileiconid;
	}
	public void setProfileiconid(int profileiconid) {
		this.profileiconid = profileiconid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPuuid() {
		return puuid;
	}
	public void setPuuid(String puuid) {
		this.puuid = puuid;
	}
	public long getSummonerLevel() {
		return summonerLevel;
	}
	public void setSummonerLevel(long summonerLevel) {
		this.summonerLevel = summonerLevel;
	}
	public long getRevisionDate() {
		return revisionDate;
	}
	public void setRevisionDate(long revisionDate) {
		this.revisionDate = revisionDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
	

	
}
