package desafio.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:caracteristicas", tags = "@AutenticacaoTeste",
        glue = "desafio.bdd.passos", monochrome = true, dryRun = false, plugin = {"pretty"})
public class AutenticacaoTeste {
}
