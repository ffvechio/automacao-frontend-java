package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
/**
 * A Classe Runner do Cucumber é responsável por gerenciar
 * a execução dos testes.
 *
 * @author Felipe Bessa <ffvechio@gmail.com>
 * @version 1.0
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"features"},
        glue = {"stepdefinition"},
        tags = {"@execute"},
        monochrome= true,
        plugin = {"pretty", "html:Reports/cucumber"})
public class RunnerTest {
}