package az.maqa.project.exception;


public class ExceptionResponse {

	private Integer code;
	private String message;
	private String details;

	public ExceptionResponse(Integer code, String message, String details) {
		super();
		this.code = code;
		this.message = message;
		this.details = details;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
