package com.care.dto;

public class MsgDTO {
   private int mnumber;
   private String msender;
   private String mreceiver;
   private String mcontent;
   private String mdate;
   private int mst;
   private int stnum;
   private int endnum;
   
   public String getMsender() {
      return msender;
   }
   public void setMsender(String msender) {
      this.msender = msender;
   }
   public String getMreceiver() {
      return mreceiver;
   }
   public void setMreceiver(String mreceiver) {
      this.mreceiver = mreceiver;
   }
   public String getMcontent() {
      return mcontent;
   }
   public void setMcontent(String mcontent) {
      this.mcontent = mcontent;
   }
   public String getMdate() {
      return mdate;
   }
   public void setMdate(String mdate) {
      this.mdate = mdate;
   }
   public int getMst() {
      return mst;
   }
   public void setMst(int mst) {
      this.mst = mst;
   }
public int getMnumber() {
   return mnumber;
}
public void setMnumber(int mnumber) {
   this.mnumber = mnumber;
}
public int getStnum() {
   return stnum;
}
public void setStnum(int stnum) {
   this.stnum = stnum;
}
public int getEndnum() {
   return endnum;
}
public void setEndnum(int endnum) {
   this.endnum = endnum;
}

   
   
}