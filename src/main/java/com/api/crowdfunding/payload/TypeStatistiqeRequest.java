package com.api.crowdfunding.payload;

import java.util.Date;

public class TypeStatistiqeRequest {
	
	   private int year;   
	   
  	    
	    private int month;
	    
	    
	    private String type_statistique;


		public int getYear() {
			return year;
		}


		public void setYear(int year) {
			this.year = year;
		}


		public int getMonth() {
			return month;
		}


		public void setMonth(int month) {
			this.month = month;
		}


		public String getType_statistique() {
			return type_statistique;
		}


		public void setType_statistique(String type_statistique) {
			this.type_statistique = type_statistique;
		}	   

}
