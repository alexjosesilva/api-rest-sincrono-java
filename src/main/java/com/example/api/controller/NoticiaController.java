package com.example.api.controller;

import com.example.api.model.Noticia;
import com.example.api.service.NoticiaService;

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
    public List<Noticia> listarNoticias() {
        return service.buscarNoticias();
    }
}