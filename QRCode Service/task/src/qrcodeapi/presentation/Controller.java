package qrcodeapi.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import qrcodeapi.business.Service;

import java.awt.image.BufferedImage;
import java.util.Map;

@RestController
public class Controller {
    private final Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }


    @GetMapping("/api/health")
    @ResponseStatus(HttpStatus.OK)
    public void method1() {}

    @GetMapping( "/api/qrcode")
    public ResponseEntity<?> getImage(
            @RequestParam(value = "size", defaultValue = "250") int size,
            @RequestParam( value = "type", defaultValue = "png") String type,
            @RequestParam("contents") String contents,
            @RequestParam(value = "correction", defaultValue = "L") String correction
    ) {

        String errorMsg = service.checkParams(size, type, contents, correction);
        if (!"no errors".equals(errorMsg)) {
            return  ResponseEntity
                    .badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("error", errorMsg));
        }

        BufferedImage bufferedImage = service.createImage(size, contents, correction);
        return ResponseEntity
                .ok()
                .contentType(service.getMediaType(type))
                .body(bufferedImage);
    }

}
