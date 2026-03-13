package com.example.api.service;

import com.example.api.model.Noticia;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticiaService {

    private static final Logger logger = LoggerFactory.getLogger(NoticiaService.class);

    private final String URL = "https://servicodados.ibge.gov.br/api/v3/noticias?qtd=10";  // ← ADICIONEI ?qtd=10 aqui!

    public List<Noticia> buscarNoticias() {
        List<Noticia> noticias = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        try {
            logger.info("Chamando API IBGE: {}", URL);
            String response = restTemplate.getForObject(URL, String.class);

            if (response == null || response.trim().isEmpty()) {
                logger.warn("Resposta da API veio vazia");
                return noticias;
            }

            logger.debug("Resposta recebida (primeiros 200 chars): {}", response.substring(0, Math.min(200, response.length())));

            JSONObject json = new JSONObject(response);
            JSONArray items = json.optJSONArray("items");

            if (items == null) {
                logger.warn("Campo 'items' não encontrado na resposta");
                return noticias;
            }

            int maxItens = Math.min(items.length(), 10);
            for (int i = 0; i < maxItens; i++) {
                JSONObject item = items.optJSONObject(i);
                if (item == null) continue;

                String id = item.optString("id", "sem-id");
                String titulo = item.optString("titulo", "Título indisponível");

                noticias.add(new Noticia(id, titulo));
            }

            logger.info("Extraídas {} notícias com sucesso", noticias.size());

        } catch (RestClientException e) {
            logger.error("Erro de rede ao chamar API IBGE", e);
        } catch (JSONException e) {
            logger.error("Erro ao parsear JSON da API IBGE", e);
        } catch (Exception e) {
            logger.error("Erro inesperado ao buscar notícias", e);
        }

        return noticias;
    }
}