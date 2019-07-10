# language: pt
# encoding UTF-8
Funcionalidade: Cadastrar Novo Usuário na apicação do Sr Barriga

  Contexto: Evidenciar Teste;
    Dado que utilizo o word para evidências com as informações de execução
      | Homologação | Cadastrar Novo Usuário | Cadastrar Novo Usuário | Cadastrar Novo Usuário com sucesso | Cadastrar Novo Usuário com sucesso | FELIPE BESSA | 01 | 01 |

  @execute @chrome-same-session @generate-word
  Esquema do Cenario: CT - CADASTRO DE USUÁRIO - Cadastrar Novo Usuário com sucesso.

    Dado que estou na página de cadastro do SrBarriga através da url "urlCadastro"
    Quando preencho o campo nome com o valor <NOME>
    E preencho o campo email com o valor <EMAIL>
    E preencho o campo senha com o valor <SENHA>
    E clico no botão Cadastrar
    Então a aplicação exibe a mensagem de sucesso <MENSAGEM>

    Exemplos:
      | NOME        | EMAIL       | SENHA        | MENSAGEM                       |
      | "aleatório" | "aleatório" | "A1B2C3D4E5" | "Usuário inserido com sucesso" |


  @execute @chrome-same-session @generate-word
  Esquema do Cenario: CT - CADASTRO DE USUÁRIO - Cadastrar Novo Usuário com erro.

    Dado que estou na página de cadastro do SrBarriga através da url "urlCadastro"
    Quando preencho o campo nome com o valor <NOME>
    E preencho o campo email com o valor <EMAIL>
    E preencho o campo senha com o valor <SENHA>
    E clico no botão Cadastrar
    Então a aplicação exibe a mensagem de erro <MENSAGEM>

    Exemplos:
      | NOME        | EMAIL             | SENHA        | MENSAGEM                         |
      | "Felipe"    | "bessa@email.com" | "A1B2C3D4E5" | "Endereço de email já utilizado" |
      | ""          | "bessa@email.com" | "A1B2C3D4E5" | "Nome é um campo obrigatório"    |
      | "aleatório" | ""                | "A1B2C3D4E5" | "Email é um campo obrigatório"   |
      | "aleatório" | "bessa@email.com" | ""           | "Senha é um campo obrigatório"   |

