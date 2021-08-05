<h1 align="center">Kafka Stream</h1>

Descrição
=================
<p align="center">Exemplo de implementação de um micro serviço de stream utilizando a biblioteca Kafka Stream e Spring Boot.</p>

<p align="center">
  <img alt="GitHub branch checks state" src="https://img.shields.io/github/checks-status/deyviddfs/kafka-streams/main">
  <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/deyviddfs/kafka-streams">
  <img alt="GitHub contributors" src="https://img.shields.io/github/contributors/deyviddfs/kafka-streams">
  <img alt="Twitter URL" src="https://img.shields.io/twitter/url?style=social&url=https%3A%2F%2Ftwitter.com%2Fdeyviddfs">
</p>

Índice
=================
<!--ts-->
- [Descrição](#descrição)
- [Índice](#índice)
- [Como Usar](#como-usar)
- [Testes](#testes)
- [Status](#status)
<!--te-->


Como Usar
=================

#Pré Requesitos
* Java 14
* Docker instalado

#Subindo Aplicação
* Clonar repositório
* Navegar até a pasta do arquivo docker-compose.yml
* Rodar comando <b>docker-compose up -d</b>
* Validar se foi tudo criado e está UP com o comando <b>docker-compose ps</b>
* Abrir o projeto cliente-producer
* Subir as duas aplicações através da classe SpringApplication
* Abrir o projeto cadastro-consumer
  * Se subir a aplicação KstreamApplication, o resultado será uma aplicação que faz join de dois tópicos.
  * Se subir a aplicação KafkaStreamApplicationGenericAvroSerde, o resultado será uma aplicação lê todos os tópicos, para facilitar os teste.



Testes
=================
* 1- Fazer uma requisição POST para o endpoint cliente
    <p>curl --location --request POST 'http://localhost:8080/cliente/' \
    --form 'id="1"' \
    --form 'nome="Bruno Siqueira"' \
    --form 'idade="39"'</p>

* Fazer uma segunda requisição POST para o endpoint cliente/email
  <p>curl --location --request POST 'http://localhost:8080/cliente/email' \ --form 'clienteId="1"' \ --form 'email="email@email.com"'</p>

* Resultado esperado é produzir uma mensagem unificando os dados das duas requisições, ou seja os dados do cliente com um e-mail (Somente se <b>clientId</b> for igual <b>Id</b> do recurso cliente), no tópico STREAMS.CUSTOMER.EMAIL, uma forma de validar é exeutando o seguinte comando: 
  <p>docker exec -ti broker ../../bin/kafka-console-consumer --bootstrap-server localhost:9092 --topic STREAMS.CUSTOMER.EMAIL --from-beginning</p>

* 2 - O Arquivo kafka-stream.postman_collection.json contém os exemplos das chamada


Status
=================
<h4 align="center"> 
	🚧  kafka - Em construção...  🚧
</h4>
