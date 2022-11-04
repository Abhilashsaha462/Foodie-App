package UserDefinedException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "The Menu which is asked to search isn't present in database!")
public class RestaurantNotFoundException extends Exception{


}
