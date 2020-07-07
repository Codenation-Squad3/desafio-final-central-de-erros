# Levantamento da estrutura da central de erros

## Considerações iniciais
As instruções do projeto final informa que sejam criados endpoints para gravar e listar logs. Também existe uma camada de segurança onde será utilizando um token de validação.

Os wireframes, conforme especificado na descrição do desafio, ilustram funcionalidades básicas e o time tem liberdade para definir detalhes.

## Entidades

| usuario   |
|-----------|
| nome      |
| email     |
| password  |
| datahora  |

| logs        | ocorrências | 
|-------------|-------------|
| titulo      |  datahora   |
| origem      |  log        |
| descricao   |  usuario    |
| datahora    |             |
| usuario     |             |
| status      |             |
| ambiente    |             |
| level       |             |

### level (enum)
Informa qual o tipo ou type do log e.g. [error, warning ,debug, etc]

### ambiente (enum)
Qual ambiente está sendo utilizado [producao, homologação, desenvolvimento]

### status (enum)
Indica o atual status de um log, como arquivado, ativo, excluído

### origem
Informa qual serviço, server, api, etc de onde veio o log e.g. [127.0.0.1, app1.server.com, service1.server.com, etc]

## Identificação do log
Os logs podem ser adicionados vária vezes com os mesmos dados de titulo, descrição e origem, dessa forma foi criado a seguinte forma para identificar logs repetidos:

md5(titulo + descricao + origem)
