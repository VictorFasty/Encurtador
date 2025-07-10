package com.victornext.encurtador.Controller;

import com.victornext.encurtador.DTOs.UrlRequest;
import com.victornext.encurtador.model.Encurtador;
import com.victornext.encurtador.service.EncurtadorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class EncurtadorController {

    private final EncurtadorService service;


    @PostMapping("/encurtar")
    public ResponseEntity<String> encurtarURL(@RequestBody UrlRequest request) {
        String shortCode = service.encurtarURL(request.getUrl());
        return ResponseEntity.ok("http://localhost:8080/" + shortCode);
    }


    @GetMapping("/{shortcode}")
    public ResponseEntity<String> buscarUrlOriginal(@PathVariable String shortcode) {
        return service.buscarOriginalUrl(shortcode)
                .map(url -> ResponseEntity.ok("URL: " + url))
                .orElse(ResponseEntity.notFound().build());
    }

}
