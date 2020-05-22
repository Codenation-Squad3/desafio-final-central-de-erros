# Levantamento da estrutura da central de erros

## Considerações iniciais
As instruções do projeto final informa que sejam criados endpoints para gravar e listar logs. Também existe uma camada de segurança onde será utilizando um token de validação.

Os wireframes, conforme especificado na descrição do desafio, ilustram funcionalidades básicas e o time tem liberdade para definir detalhes.

## Entidades

| usuario   |
|-----------|
| email     |
| token     |
| nome      |

| logs        | event_log   | 
|-------------|-------------|
| titulo      |  datahora   |
| log         |  id log     |
| hash        |             |
| datahora    |             |
| ambiente    |             |
| origem      |             |
| level       |             |
| usuario     |             |
| level       |             |
| origem      |             |
| ambiente    |             |

### level (enum)
Informa qual o tipo ou type do log e.g. [error, warning ,debug, etc]

### origem (enum)
Informa qual serviço, server, api, etc de onde veio o log e.g. [127.0.0.1, app1.server.com, service1.server.com, etc]

### ambiente (enum)
Qual ambiente está sendo utilizado [producao, homologação, desenvolvimento]

### log/hash
O que determina as ocorrências ou eventos é uma implementação do método hash java a ser computado, e.g. md5(logs->log + level + origem + ambiente)

### log/datahora
retornar sempre a última datahora do event_log

### event_log
inclui datas de ocorrências para um mesmo log e serve para retornar a quantidade de ocorrências
