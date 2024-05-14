package co.uos.customer.infra.exception;

import co.uos.customer.infra.ServiceResponse;
import co.uos.customer.util.exception.CustomException;
import co.uos.customer.util.exception.ResourceNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ServiceResponse> customException(CustomException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ServiceResponse.builder()
                        .message(ex.getMessage())
                        .error(ex.getMessage())
                        .data(null)
                        .success(false)
                        .build());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ServiceResponse> dataNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ServiceResponse.builder()
                        .message(ex.getMessage())
                        .error(ex.getMessage())
                        .data(null)
                        .success(false)
                        .build());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> runtimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ServiceResponse.builder()
                        .message("Teknik bir hata ile karşılaşıldı!")
                        .error(ex.getMessage())
                        .data(null)
                        .success(false)
                        .build());
    }
}
