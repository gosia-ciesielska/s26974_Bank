package pl.pjatk.s26974_Bank.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class AccountExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<String> handleIllegalArgumentException() {
        return ResponseEntity.badRequest().body("Invalid account data");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<String> handleNoSuchElementException() {
        return ResponseEntity.badRequest().body("No such account");
    }
}
