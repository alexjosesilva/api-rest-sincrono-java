# Generate the formatted README.md file using pypandoc as required
import pypandoc

readme_text = """
# 📰 API REST Síncrona Java — Notícias IBGE

Uma **API REST desenvolvida em Java com Spring Boot** que consome as notícias recentes da API oficial do IBGE e retorna um JSON contendo **ID e título das notícias**.

Este projeto demonstra **integração síncrona com API externa** utilizando:

- `RestTemplate`
- Parsing de JSON
- Estrutura simples de API REST

---

# 🚀 Funcionalidades

- Endpoint principal: `GET /noticias`
- Retorna as **10 notícias mais recentes do IBGE**
- Estrutura simples para demonstração de integração com API externa

### Campos retornados

| Campo | Descrição |
|------|-----------|
| id | Identificador da notícia |
| titulo | Título da notícia |

---

# 📦 Exemplo de Resposta

```json
[
  {
    "id": "46096",
    "titulo": "Ministro da Previdência Social visita sede do IBGE no Rio de Janeiro"
  },
  {
    "id": "46095",
    "titulo": "IBGE tem financiamento retomado para o SINAPI após novo decreto do Governo Federal"
  }
]