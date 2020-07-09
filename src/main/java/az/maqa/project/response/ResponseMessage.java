package az.maqa.project.response;


public class ResponseMessage {

	private int successCode;
	private String message;

	public ResponseMessage(String message, int code) {
		this.message = message;
		this.successCode = code;
	}

	public String getMessage() {
		return message;
	}

	public int getSuccessCode() {
		return successCode;
	}


	

}
