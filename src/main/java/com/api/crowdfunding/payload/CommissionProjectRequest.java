package com.api.crowdfunding.payload;



public class CommissionProjectRequest {
	
    
    private Long amount;
    
    private String date_created;    

    private Long timestamp;

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	} 
    

}
