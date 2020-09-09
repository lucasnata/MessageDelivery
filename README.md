# Messsage Delivery
Projeto para controle de envio de mensagens em diversas plataformas.
Construído com desafio de seleção do Luiza Labs.

### Requisitos
Para compilação e execução da aplicação, são necessárias as ferramentas:
- [Maven](https://maven.apache.org/install.html)
- [Docker](https://docs.docker.com/get-docker/) e [Docker-Compose](https://docs.docker.com/compose/install/)

### Instruções

Utilizando um terminal ou ferramenta de GIT, fazer uma cópia do repositório:
```
git clone https://github.com/lucasnata/MessageDelivery.git 
```

Dentro do diretório do projeto, executar o comando Maven:
```
mvn clean package
```

Para gerar a imagem Docker do projeto, rodar o seguinte comando no terminal:
```
docker build -t lucasnata/message-delivery .
```

Em seguida rodar o comando do docker-compose, para subir o Banco de Dados e Aplicação:
```
docker-compose up -d
```

A aplicação estará disponível na seguinte url:
```
http://localhost:8080/message
```

### Endpoints disponíveis

| Funcionalidade | Resquest | URL | Body Envio(Exemplo) | Response Code(Exemplo)| Response(Exemplo)|
| --- | --- | --- | --- | --- | --- |
| Cadastro de Nova Mensagem | POST | http://localhost:8080/message | {"dateTimeSchedule": "2020-09-06T02:10:43.511Z","recipient": "meu@email.com","message": "Alguma mensagem interresante","messageType": "EMAIL"} | 200 |{"dateTimeSchedule": "2020-09-06T02:10:43.511Z","recipient": "meu@email.com","message": "Alguma mensagem interresante","messageType": "EMAIL"} |
| Busca de Status de Mensagem por ID | GET | http://localhost:8080/message/{id}/status | --- | 200 | {"id": 2, "status": "SCHEDULED"}|
| Deleta Mensagem | DELETE | http://localhost:8080/message/{id} | --- | 200 | {"id": 2, "status": "DELETED" } |
| Todas as Mensagem | GET | http://localhost:8080/message | --- | 200 | [{"id": 2, "status": "SCHEDULED"}...|

#### Cadastro de Nova Mensagem
Ao utilizar o endpoint de cadastro a resposta vem com:
- Http Status - 200
- No header é retorna a chave "Location" com a url de consulta ao item adicionado

#### Busca de Status de Mensagem por ID
O endpoint retorna apenas o id e status da mensagem

#### Excluir Mensagem
O endpoint de consulta status retorna a mensagem com o status DELETED

### Swagger
Com a aplicação rodando em localhost, pode-se utilizar o Swagger para testar as requisições:
```
http://localhost:8080/swagger-ui.html
``` 

### Mensageria RabbitMQ
Envio das mensagens para uma fila do RabittMQ, garantindo maior desacoplamento, 
para uso das ferramentas que irão garantir a entrega das mensagens.

## Ferramentas Utilizadas
- [Spring Boot](https://spring.io/projects/spring-boot) Framework de Desenvolvimento Java
- [MySQL](https://www.mysql.com) Banco de Dados Relacional
- [Hibernate](https://hibernate.org) Framework para Persistência de Banco de Dados
- [Docker](https://www.docker.com) Ferramenta de containers, que facilitam a publicação das aplicações
- [JUnit](https://junit.org/junit5/) Ferramenta de Testes
- [H2 Database Engine](https://www.h2database.com/html/main.html) Banco de dados em memória(para uso nos testes)
- [RabbitMQ](https://www.rabbitmq.com/getstarted.html) Ferramenta de mensageria

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)