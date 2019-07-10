package stepdefinition;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import frontutils.PropertiesHelper;
import pages.LoginPage;

import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * A Classe Login é responsável por executar
 * as ações de um Acesso de Usuário.
 *
 * @author Felipe Bessa <ffvechio@gmail.com>
 * @version 1.0
 */
public class Login {

    private Properties properties = new PropertiesHelper().getProperties();
    private LoginPage loginPage;

    public Login() {
        loginPage = new LoginPage(Hooks.driver);
    }

    // Acessa a URL de login da aplicação do Sr Barriga

    @Dado("^que estou na página de login através da url \"([^\"]*)\"$")
    public void que_estou_na_página_de_login_através_da_url(String url) throws Throwable {
        loginPage.setUrl(properties.getProperty(url));
    }

    // Preenche o campo E-mail na tela de Login

    @Quando("^preencho o campo email \"([^\"]*)\"$")
    public void preencho_o_campo_email(String email) throws Throwable {
        loginPage.setFieldMail(email);
    }

    // Preenche o campo Senha na tela de Login

    @Quando("^preencho o campo senha \"([^\"]*)\"$")
    public void preencho_o_campo_senha(String password) throws Throwable {
        loginPage.setFieldPassword(password);
    }

    // Clica no botão Entrar na tela de Login

    @Quando("^clico no botão Entrar$")
    public void clico_no_botão_Entrar() throws Throwable {
        loginPage.submitFieldLogin();
    }

    // Compara com assertEquals se a mensagem exibida na tela é a mesma que foi documentada no arquivo .feature

    @Então("^a aplicação exibe a mensagem \"([^\"]*)\" de bem vindo$")
    public void a_aplicação_exibe_a_mensagem_de_bem_vindo(String mensagem) throws Throwable {
        assertEquals(mensagem, loginPage.getMessageLoginSuccess());
    }

    // Clica no botão Sair no menu principal

    @Então("^clico no botão Sair$")
    public void clico_no_botão_Sair() throws Throwable {
        loginPage.submitFieldLogout();
    }

}
