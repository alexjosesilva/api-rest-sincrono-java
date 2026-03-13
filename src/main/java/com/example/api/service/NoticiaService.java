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

    private static final String URL = "https://servicodados.ibge.gov.br/api/v3/noticias/?qtd=10";

    public List<Noticia> buscarNoticias() {
        RestTemplate restTemplate = new RestTemplate();
        List<Noticia> lista = new ArrayList<>();

        try {
            String response = restTemplate.getForObject(URL, String.class);

            if (response == null || response.trim().isEmpty()) {
                System.out.println("Resposta vazia da API do IBGE");
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
            } else {
                System.out.println("Campo 'items' não encontrado na resposta");
            }

        } catch (Exception e) {
            System.err.println("Erro ao buscar notícias: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }
}
