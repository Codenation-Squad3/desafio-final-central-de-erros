# language: pt
@AutenticacaoTeste
Funcionalidade: Testar as operações básicas de login
  O sistema deve prover operações de login de forma correta.
  Seguindo as seguintes restrições:
  1) Email deve ser válido e único
  2) Senha deve ter um tamanho mínimo de 6 caracteres, sendo pelo menos 1 caracter especial.

  Esquema do Cenario: Testar cadastro válido
    Dado que ainda não foi feito nenhum cadastro
    Quando insiro um email "<email>"
    E insiro uma senha "<senha>"
    Quando o usuário realiza a solicitação de cadastro
    Entao ele deve ser cadastrado com sucesso

    Exemplos:
      | email |senha|
      |emailvalido@teste.com| 12345@  |
      |outroemailvalido@teste.com | senhagrande123456890!@#$%^&*()-+=  |

  Esquema do Cenario: Testar cadastro dados inválido
    Dado que ainda não foi feito nenhum cadastro
    Quando insiro um email "<email>"
    E insiro uma senha "<senha>"
    Quando o usuário realiza a solicitação de cadastro
    Entao ele não deve ser cadastrado

    Exemplos:
      | email |senha|
      |emailinvalidoe| 12345@  |
      |emailvalido@teste.com | 123  |
      |emailinvalido         | 123  |

  Esquema do Cenario: Testar cadastro já realizado
    Dado que já foi feito um cadastro para um usuário de email "<email>"
    Quando insiro um email "<email>"
    E insiro uma senha "<senha>"
    Quando o usuário realiza a solicitação de cadastro
    Entao ele não deve ser cadastrado

    Exemplos:
      | email |senha|
      |emailjacadastrado@teste.com| 12345@  |