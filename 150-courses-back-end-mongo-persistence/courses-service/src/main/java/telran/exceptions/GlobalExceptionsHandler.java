package telran.exceptions;

import javax.validation.ConstraintViolationException;

import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {
	static Logger LOG = LoggerFactory.getLogger(GlobalExceptionsHandler.class);

	@ExceptionHandler(value = { ConstraintViolationException.class, MethodArgumentNotValidException.class,
			WrongInputDataException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	String getResponseBadRequest(Exception e) {
		return exceptionResponse(e);
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	String getResponseNotFound(Exception e) {
		return exceptionResponse(e);
	}
	@ExceptionHandler(value=RuntimeException.class) 
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR) 
	String getResponseRuntime(Exception e)
	{
		return exceptionResponse(e);
	}
	

	private String exceptionResponse(Exception e) {
		LOG.error(e.toString());
		return e.getMessage();
	}
}
