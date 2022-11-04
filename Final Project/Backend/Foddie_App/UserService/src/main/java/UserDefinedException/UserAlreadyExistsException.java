package UserDefinedException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User which is trying to reach ALREADY EXISTS in database!")
public class UserAlreadyExistsException extends Exception{


}
