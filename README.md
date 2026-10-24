# Atividade Guiada — API CRUD de Tarefas com Spring Boot

Projeto desenvolvido para a disciplina **Frameworks 2**

## Objetivo

Desenvolver uma API REST que permita cadastrar, listar, consultar, atualizar e excluir tarefas utilizando **Java**, **Spring Boot 4.1.1** e **Maven**. Os dados são armazenados em memória, sem necessidade de banco de dados.

## Tecnologias

- Java 17 (ou superior)
- Spring Boot 4.1.1
- Spring Web
- Maven
- JUnit 5 para testes automatizados
- Postman para requisições HTTP

## Como executar

1. Instale o **JDK 17 ou superior** e o **Maven 3.6.3 ou superior**, ou importe o projeto no IntelliJ IDEA como um projeto Maven.
2. Abra o terminal na pasta deste projeto.
3. Execute:

   ```bash
   mvn spring-boot:run
   ```

4. A API estará disponível em `http://localhost:8080/tasks`.

Alternativamente, gere um arquivo JAR e execute-o:

```bash
mvn clean package
java -jar target/task-api-0.0.1-SNAPSHOT.jar
```

## Endpoints

| Método | Rota | Descrição | Sucesso |
| --- | --- | --- | --- |
| GET | `/tasks` | Lista todas as tarefas | 200 |
| GET | `/tasks/{id}` | Retorna uma tarefa por ID | 200 |
| POST | `/tasks` | Cria uma tarefa | 201 |
| PUT | `/tasks/{id}` | Atualiza uma tarefa existente | 200 |
| DELETE | `/tasks/{id}` | Exclui uma tarefa | 204 |

Ao consultar, atualizar ou excluir uma tarefa que não existe, a API responde com **404 Not Found**.

### Exemplo: cadastrar tarefa

Requisição:

```http
POST http://localhost:8080/tasks
Content-Type: application/json
```

Corpo JSON:

```json
{
  "title": "Casa",
  "description": "Organizar as tarefas de casa",
  "dueDate": "2026-12-31T18:00:00"
}
```

Exemplo de resposta (`201 Created`):

```json
{
  "id": 1,
  "title": "Casa",
  "description": "Organizar as tarefas de casa",
  "dueDate": "2026-12-31T18:00:00"
}
```

O identificador (`id`) é gerado automaticamente pela API.

**Atenção ao campo `dueDate`:** como o modelo usa `LocalDateTime`, a data precisa conter também um horário, por exemplo, `2026-12-31T18:00:00`. A atividade traz exemplos de apenas data; aqui foi utilizado o formato compatível com o tipo Java solicitado.

### Exemplo: atualizar tarefa

```http
PUT http://localhost:8080/tasks/1
Content-Type: application/json
```

```json
{
  "title": "Trabalho",
  "description": "Organizar as tarefas do trabalho",
  "dueDate": "2026-12-25T14:30:00"
}
```

### Outras requisições

```http
GET http://localhost:8080/tasks
GET http://localhost:8080/tasks/1
DELETE http://localhost:8080/tasks/1
```

## Como testar

**Postman:** importe o arquivo `postman/Task_API_CRUD.postman_collection.json`, inicie a aplicação e execute a coleção na ordem. O primeiro teste cria uma tarefa e guarda o ID para os passos seguintes (consulta, listagem, atualização, exclusão e verificação do 404).

**Testes automatizados:** execute:

```bash
mvn test
```

Os testes cobrem criação, listagem, busca, atualização, exclusão e resposta para IDs inexistentes.

## Observações

- As tarefas ficam armazenadas apenas na memória. Ao parar ou reiniciar a aplicação, elas são perdidas.
- Os pacotes foram organizados em `model`, `repository` e `controller`, com nomes consistentes em todas as classes.
- O endpoint de criação contém a anotação `@PostMapping`, necessária para receber requisições POST.
- A atividade não solicita persistência em banco de dados, autenticação nem interface gráfica.
