package co.edu.unicauca.distribuidos.core.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControladorExcepciones {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> controlarExcepcion(final HttpServletRequest req, final ResponseStatusException ex)
    {
        return new ResponseEntity<>(ex.getReason(), HttpStatus.NOT_FOUND);
    }
}
