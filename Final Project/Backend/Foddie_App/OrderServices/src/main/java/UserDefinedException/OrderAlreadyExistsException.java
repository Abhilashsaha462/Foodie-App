package UserDefinedException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "This Order has already been placed! please check Order-Id")
public class OrderAlreadyExistsException extends Exception{


}
