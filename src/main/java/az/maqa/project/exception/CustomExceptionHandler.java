package az.maqa.project.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception e, WebRequest webRequest) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionConstants.INTERNAL_SERVER_ERROR, e.getMessage(),
				webRequest.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e, WebRequest webRequest) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionConstants.USER_NOT_FOUND, e.getMessage(),
				webRequest.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidRequestData.class)
	public final ResponseEntity<Object> handleInvalidRequestDataException(InvalidRequestData e, WebRequest webRequest) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionConstants.INVALID_REQUEST_DATA, e.getMessage(),
				webRequest.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionConstants.VALIDATION_FAILED, "Validation Failed",
				ex.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	
}
