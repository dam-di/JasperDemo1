package damx.jasperdemo1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<String> handleError() {
        return new ResponseEntity<>("Error: ruta no válida", HttpStatus.NOT_FOUND);
    }
}