# language: pt
# encoding UTF-8
Funcionalidade: Adicionar Contas

  Contexto: Evidenciar Teste;
    Dado que estou na página de login através da url "urlLogin"
    E preencho os campos para logar
      | bessa@email.com | A1B2C3D4E5 |
    E clico no botão Entrar
    E que utilizo o word para evidências com as informações de execução
      | Homologação | Adicionar contas na aplicação do Sr barriga | Adicionar contas na aplicação do Sr barriga | Adicionar contas na aplicação do Sr barriga com sucesso | Adicionar contas na aplicação do Sr barriga com sucesso | FELIPE BESSA | 01 | 01 |

  @execute @chrome-same-session @generate-word
  Esquema do Cenario: CT - ADICIONAR CONTAS - Adicionar Contas com Sucesso.

    Quando clico no menu contas
    E seleciono a opção adicionar
    E preencho o campo nome <NOME>
    E clico no botão salvar
    Então a aplicação exibe a mensagem <MENSAGEM>
    E valido se a conta foi adicionada com sucesso
    E deleto a conta

    Exemplos:
      | NOME        | MENSAGEM                        |
      | "aleatório" | "Conta adicionada com sucesso!" |