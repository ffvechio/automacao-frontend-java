package stepdefinition;

import com.github.javafaker.Faker;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pages.AddAccountPage;
import pages.LoginPage;

import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * A Classe AddAccount é responsável por executar
 * as ações de um cadastro de uma Conta.
 *
 * @author Felipe Bessa <ffvechio@gmail.com>
 * @version 1.0
 */
public class AddAccount {

    private LoginPage loginPage = new LoginPage(Hooks.driver);
    private AddAccountPage addAccount = new AddAccountPage(Hooks.driver);
    private Faker faker = new Faker();
    private String account;


    // Preenche os campos necessários para realizar o login

    @Dado("^preencho os campos para logar$")
    public void preencho_os_campos_para_logar(DataTable userCredentials) throws Throwable {
        List<List<String>> data = userCredentials.raw();
        loginPage.setFieldMail(data.get(0).get(0));
        loginPage.setFieldPassword(data.get(0).get(1));
    }

    // Clica no menu Contas

    @Quando("^clico no menu contas$")
    public void clico_no_menu_contas() throws Throwable {
        addAccount.submitBtnAccount();
    }

    // Clica no submenu Adicionar

    @Quando("^seleciono a opção adicionar$")
    public void seleciono_a_opção_adicionar() throws Throwable {
        addAccount.submitBtndAdd();
    }

    // Preenche o campo Nome da Conta

    @Quando("^preencho o campo nome \"([^\"]*)\"$")
    public void preencho_o_campo_nome(String accountName) throws Throwable {
        account = addAccount.setFieldName(accountName.equals("aleatório") ? faker.name().firstName() + faker.number().randomDigit() : accountName);

    }

    // Clica no botão Salvar Conta

    @Quando("^clico no botão salvar$")
    public void clico_no_botão_salvar() throws Throwable {
        addAccount.submitBtndSave();
    }

    // Compara com asserEquals se a mensagem exibida na tela é igual a mensagem documentada na feature

    @Então("^a aplicação exibe a mensagem \"([^\"]*)\"$")
    public void a_aplicação_exibe_a_mensagem(String mensagem) throws Throwable {
        assertEquals(mensagem, addAccount.getMessageSucess());
    }

    // Compara com assertEquals se conta adicionada é a mesma que está sendo exibida da lista de contas

    @Então("^valido se a conta foi adicionada com sucesso$")
    public void valido_se_a_conta_foi_adicionada_com_sucesso() throws Throwable {
        assertEquals(account, addAccount.getAccountAdd());
    }

    // Deleto a conta adicionada com objetivo de limpar a lista

    @Então("^deleto a conta$")
    public void deleto_a_conta() throws Throwable {
        addAccount.submitBtndDelete();
    }


}
