# Api Rest Star Wars
## _Desafio B2W Backend_
Essa API Rest tem como objetivo atender ao desafio da B2W, para desenvolvedor backend. A API utiliza os dados dos planetas que tem aparição nos filmes Star Wars, obtidas pela API pública do Star Wars: https://swapi.dev/about.

### API hospedada no Heroku

- Link da documentação da API: https://api-desafio-sw.herokuapp.com/swagger-ui.html#/

### Tecnologias
No projeto foram essas as tecnologias utilizadas:
- Java 8
- Spring Boot
- Spring Data MongoDB
- MongoDB
- Maven
- Docker
- Lombok
- JUnit 5
- Mockito / PowerMockito
- Swagger 2

### Estrutura

| Descrição | Endpoints | Metodo | Dado/Parametro |
| ------ | ------ | ------- | ------- |
| Adicionar um planeta | http://localhost:8080/planets/ | POST | Planet{name, climate, terrain} |
| Listar planetas | http://localhost:8080/planets/list | GET | --------- |
| Buscar por nome | http://localhost:8080/planets/?name= | GET | String
| Buscar por ID | http://localhost:8080/planets/{id} | GET | String
| Remover planeta | http://localhost:8080/planets/{id} | DELETE | String

Planet
```
{
    "id": "604fb09dd995ef1e39a6bad0",
    "name": "Naboo",
    "climate": "temperate",
    "terrain": "grassy hills, swamps, forests, mountains",
    "countFilms": 4
}
```

### Execução

#### *Clonando Projeto e Instalando as Dependências

```sh
git clone https://github.com/wesleylazarini/api-star-wars.git
cd  api-star-wars
mvn clean install
```
Após executar esses comandos, tamem será executado todos os testes da aplicação.

#### *Executando via Docker Compose

Primeiro altere o profile no arquivo "application.properties", de 'prod' para 'dev'.

```sh
docker-compose up
```
A API estará disponivel em http://localhost:8080/
A documentação da API estará disponivel em http://localhost:8080/swagger-ui.html#/
O MongoDB estará disponivel na porta 27017
O client do MongoDB estará disponivel em http://localhost:3030/

** Para facilitar a execução dos serviços, utilize a pagina de documentação(Swagger) ou utilize essa url ``` http://localhost:8080/v2/api-docs ``` para importar a estrutura no [Postman].

## License

MIT

   [Postman]:<https://www.postman.com/>