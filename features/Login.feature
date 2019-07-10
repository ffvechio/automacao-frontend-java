# language: pt
# encoding UTF-8
Funcionalidade: Logar na apliação do Sr Barriga

  Contexto: Evidenciar Teste;
    Dado que utilizo o word para evidências com as informações de execução
      | Homologação | Logar na Aplicação do Sr barriga | Logar na Aplicação do Sr barriga | Logar na Aplicação do Sr barriga com sucesso | Logar na Aplicação do Sr barriga com sucesso | Felipe Bessa | 01 | 01 |

  @execute @chrome-same-session @generate-word
  Esquema do Cenario: CT - LOGIN - Logar na Aplicação do Sr barriga com sucesso.

    Dado que estou na página de login através da url "urlLogin"
    Quando preencho o campo email <EMAIL>
    E preencho o campo senha <SENHA>
    E clico no botão Entrar
    Então a aplicação exibe a mensagem <MENSAGEM> de bem vindo
    E clico no botão Sair

    Exemplos:
      | EMAIL             | SENHA        | MENSAGEM             |
      | "bessa@email.com" | "A1B2C3D4E5" | "Bem vindo, Felipe!" |
