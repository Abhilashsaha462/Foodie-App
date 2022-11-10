package UserDefinedException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "This User Not Found in Database or Credentials are wrong!")
public class UserNotFoundException extends Exception{


}
