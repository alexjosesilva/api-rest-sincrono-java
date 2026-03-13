package com.example.api.service;

import com.example.api.model.Noticia;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticiaService {

    private static final String URL = "https://servicodados.ibge.gov.br/api/v3/noticias";

    public List<Noticia> buscarNoticias() throws JSONException {

        RestTemplate restTemplate = new RestTemplate();
        List<Noticia> lista = new ArrayList<>();

        String response = restTemplate.getForObject(URL, String.class);

        if (response == null || response.trim().isEmpty()) {
            return lista;
        }

        JSONObject json = new JSONObject(response);
        JSONArray items = json.optJSONArray("items");

        if (items != null) {
            for (int i = 0; i < items.length() && i < 10; i++) {
                JSONObject obj = items.getJSONObject(i);
                String id = obj.optString("id", "sem-id");
                String titulo = obj.optString("titulo", "sem título");
                lista.add(new Noticia(id, titulo));
            }
        }

        return lista;
    }
}