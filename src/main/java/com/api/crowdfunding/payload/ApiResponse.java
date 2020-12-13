package com.api.crowdfunding.payload;

/**
 * Created by rajeevkumarsingh on 19/08/17.
 */
public class ApiResponse {
    private Boolean success;
    private String message;
    private String champ;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
        this.champ = "";
        
    }
    
    public ApiResponse(Boolean success,String message,String _champ) {
        this.success = success;
        this.message = message;
        this.champ = _champ;
        
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public String getChamp() {
		return champ;
	}

	public void setChamp(String champ) {
		this.champ = champ;
	}
}
