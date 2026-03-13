package com.example.api.service;

import com.example.api.model.Noticia;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticiaService {

    private final String URL = "https://servicodados.ibge.gov.br/api/v3/noticias";

    public List<Noticia> buscarNoticias() {

        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(URL, String.class);

        JSONObject json = new JSONObject(response);

        JSONArray items = json.getJSONArray("items");

        List<Noticia> lista = new ArrayList<>();

        for (int i = 0; i < items.length() && i < 10; i++) {

            JSONObject obj = items.getJSONObject(i);

            String id = obj.getString("id");
            String titulo = obj.getString("titulo");

            lista.add(new Noticia(id, titulo));
        }

        return lista;
    }
}