package com.example.api.controller;

import com.example.api.model.Noticia;
import com.example.api.service.NoticiaService;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

    private final NoticiaService service;

    public NoticiaController(NoticiaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Noticia>> listarNoticias() {
        try {
            List<Noticia> noticias = service.buscarNoticias();
            return ResponseEntity.ok(noticias);
        } catch (JSONException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}