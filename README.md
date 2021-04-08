# kafka-streams
Exemplo de implementação do kafka stream


1- Navergar até a pasta do arquivo docker-compose.yml
2- Rodar comando docker-compose up -d
3- Validar se foi tudo criado e está UP com o comando docker-compose ps
4- Abrir o projeto cliente-producer
5- Subir a aplicação SpringApplication
6- Abrir o projeto cadastro-consumer
7- Subir a aplicação KstreamApplication (Essa aplicação faz join de dois tópicos)
8- Subir a aplicação KafkaStreamApplicationGenericAvroSerde (Essa aplicação lê todos os tópicos, para facilitar os testes)
8- O Arquivo kafka-stream.postman_collection.json contém os exemplos das chamada


Teste de Join
Produzir um clientId igual Id do recurso cliente
