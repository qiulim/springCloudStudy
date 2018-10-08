package cn.qiulim.restservice.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String s) {

        super(s);
    }
}
