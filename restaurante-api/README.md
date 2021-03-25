# Desafio-Java-API
> API criada para desafio.

A API desenvolvida dentro do escopo do projeto permite a gestão de um restaurante por meio do controle de pedidos.

A API foi desenvoldia utilizando as tecnologias:
Linguagem JAVA com framework Spring.
H2 Banco de dados (em memória).

## Instalação e execução

Crie um clone ou baixe um .zip da aplicação.

Importe a aplicação com sua IDE de preferência. Após a importação execute a seguinte classe:
```sh
RestauranteApiApplication.class
```

Caso prefira execute por linha de comando, abrindo o terminal e execute o comando:
```sh
mvnw spring-boot:run
```
obs.: Certifique-se de estar com a variavel de ambiente JAVA_HOME devidamente configurada.

## Banco de dados

A aplicação utiliza o banco dados H2 direto em memória, que é executado automaticamente junto com a aplicação.

Para acessar o console do H2, digite a seguinte url:
```sh
http://localhost:8080/api/v1/h2
```
obs: O banco está sendo executado em memória, ou seja, sempre que a aplicação parar, os dados serão perdidos. Para efeito de testes o banco será levemente populado com a configuração prévia do arquivo data.sql.

## Contato

Otto Victor
ottovfcb159@gmail.com
[Github](https://github.com/ottovfcb)