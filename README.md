# Central de Erros - backend

Central de erros é uma api REST para centralizar logs e permitir sua categorização e busca. Foi desenvolvido em 2020 junto ao curso da Codenation Java em parceria com a CI&T. Os conhecimentos aprendidos durante as aulas foram testados e aplicados neste projeto.

O foco era no backend mas, foi criado também um frontend para facilitar a visualização e apresentação. Não conseguimos o finalizar por completo mas é interessante vizuzalisar como ficaria o produto final. 

<a  href="https://github.com/Neemias-S/Desafio-Final-Central-de-Erros-Front" target="_blank">Repositório Frontend</a>

## Descrição

**Objetivo:** Desenvolvimento de aplicação REST para cadastro e busca por logs.

**Contextualização:** Em um ecossistema de uma empresa existem várias aplicações, servidores, serviços de banco de dados, etc onde são disponibilizados logs que podem informar erros, problemas de conectividade, serviços inoperantes, etc. Estas informações normalmente ficam separadas, dificultando a tomada de decisões ou mesmo a analise de alguma métrica. Dessa forma é necessário um ambiente que centralize estes logs e que permita sua categorização e busca.

## Equipe backend

|Alexandre Silva Simedo Pacheco|Danilo Wilson Soares da Silva|Alexandre Fernando Gans|Neemias da Silva Souza|
| :--- | :--- | :--- | :--- |
|![Alexandre Silva Simedo Pacheco](https://avatars0.githubusercontent.com/u/32602250?s=96&v=4)|![Danilo Wilson Soares da Silva](https://avatars2.githubusercontent.com/u/41155020?s=96&v=4)|![Alexandre Fernando Gans](https://avatars0.githubusercontent.com/u/99565?s=96&v=4)|![Neemias da Silva Souza](https://avatars0.githubusercontent.com/u/43554887?s=96&v=4)|
|[Linkedin](https://linkedin.com) <br/>[GitHub](https://github.com) |[Linkedin](https://linkedin.com) <br/>[GitHub](https://github.com) |[Linkedin](https://www.linkedin.com/in/alexandregans/) <br/>[GitHub](https://github.com/gans) |[Linkedin](https://linkedin.com) </br>[GitHub](https://github.com/Neemias-S)|


## Instalação

### Pré-requisitos

* É necessário ter instalado o JDK 8, verifique se o Java está instalado com o comando java -version.
* Ferramenta de versionamento git instalada, alguns sistemas operacionais já vem por padrão com esta ferramenta.
* Ferramenta de build Maven

#### Banco de dados

As entidades do banco de dados são o Log, User e Ocurrence. O diagrama abaixo mostra as relações entre si.

![Diagrama_Banco_De_Dados](https://i.ibb.co/j3ZKSnJ/Anota-o-2020-07-12-130537.png)

Sendo os valores dos Enum:

```
Enum status {
  ARCHIVED
  ACTIVE
  EXCLUDED
}

Enum environment {
  PRODUCTION
  TEST
  DEVELOPMENT
}

Enum level {
  ERROR
  WARNING
  DEBUG
}
```

O diagrama pode ser encontrado clicando [aqui](https://dbdiagram.io/d/5f0b37470425da461f048ff3).


#### Docker

O banco de dados local roda utilizando o postgresql e os testes em h2.

Para facilitar o uso, foi feito um `docker-compose` para levantar o banco de dados e o pgAdmin.


[arquivo de compose do docker](https://github.com/Codenation-Squad3/desafio-final-central-de-erros/blob/master/docker-compose.yml)
| Step                       | Command                         |
| ---------------------------|:-------------------------------:|
| Setup Banco de dados       | ``$ docker-compose up --build`` |
| Rodar aplicação            | Rodar o Application             |

## Iniciar a aplicação

Para iniciar a aplicação execute os comandos abaixo:

```
git clone https://github.com/Codenation-Squad3/desafio-final-central-de-erros.git;
cd Desafio-Final-Central-de-Erros;
mvn spring-boot:run
```
Após a inicialização da api o processo estará disponível na portal 8080, você pode acessar o link abaixo para visualizar a documentação do Swagger e utilizar os métodos descritos.

```
http://localhost:8080/swagger-ui.html
```
## Arquitetura inicial da api
[link para descrição da arquitetura inicial](https://github.com/Codenation-Squad3/desafio-final-central-de-erros/blob/master/estrutura.md)

## Funcionalidades

* Cadastro de usuário
* Autenticação de usuário utilizando OAUTH2
* Cadastro de log
* Busca por logs

## Fluxo das tarefas

O desenvolvimento foi divido em sprints, onde eram planejadas as featureas a adicionar. Com o objetivo de organizar o processo de desenvovimento e avalisar o andamento das atividades foi utilizado a plataforma Trello.

## Organização do repositório

Com o objetivo de organizar e manter um código testável são criadas branches para commitar funcionalidades desenvolvidas. Após o termino do desenvolvimento é criado um pull request onde são feitos reviews. 
Também são realizados testes automáticos de integração com a ferramenta de CI do Github Actions, para efetuar o build e testes.
Após estes passos é commitado o desenvolvimento na branch master.

## Ci/CD

Para Continuous Integration foi utilizado o Github Actions para efetuar o build e testes automáticos, assim validar a pull request antes de review e merge com a branch master.
Cada commit na branch master é feito o deploy automático no Heroku,
