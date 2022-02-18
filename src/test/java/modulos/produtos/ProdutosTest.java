package modulos.produtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produto")
public class ProdutosTest {
    @Test
    @DisplayName("Nao e permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {
        // Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();

        // Vou maximizar a tela
        navegador.manage().window().maximize();

        // Vou definir um tempo de espera padrao de 5 segundos
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegar para a pagina da Lojinha Web
        navegador.get("http://165.227.93.41/lojinha-web/v2/");

        // Fazer login
        navegador.findElement(By.cssSelector("label[for='usuario']")).click();
        navegador.findElement(By.id("usuario")).sendKeys("admin");

        navegador.findElement(By.cssSelector("label[for='senha']")).click();
        navegador.findElement(By.id("senha")).sendKeys("admin");

        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        // Vou para tela de registro de produto
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        // Vou preencher dados do produto e o valor sera igual a zero
        navegador.findElement((By.id("produtonome"))).sendKeys("Playstation 4");
        navegador.findElement(By.id("produtovalor")).sendKeys("000");
        navegador.findElement(By.id("produtocores")).sendKeys("verde, azul, branco");

        // Vou submeter o formulario
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        // Vou validar que a mensagem de erro foi apresentada
        // <div class="toast rounded" style="top: 0px; opacity: 1;">O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00</div>
        String mensagemToast = navegador.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToast);

        // Vou fechar o navegador
        navegador.quit();

    }

    @Test
    @DisplayName("Nao e permitido registrar um produto com valor maior que 7000,00")
    public void testNaoEPermitidoRegistrarProdutoComValorMaiorQueSeteMil() {
        // Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();

        // Vou maximizar a tela
        navegador.manage().window().maximize();

        // Vou definir um tempo de espera padrao de 5 segundos
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegar para a pagina da Lojinha Web
        navegador.get("http://165.227.93.41/lojinha-web/v2/");

        // Fazer login
        navegador.findElement(By.cssSelector("label[for='usuario']")).click();
        navegador.findElement(By.id("usuario")).sendKeys("admin");

        navegador.findElement(By.cssSelector("label[for='senha']")).click();
        navegador.findElement(By.id("senha")).sendKeys("admin");

        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        // Vou para tela de registro de produto
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        // Vou preencher dados do produto e o valor sera igual a zero
        navegador.findElement((By.id("produtonome"))).sendKeys("Playstation 4");
        navegador.findElement(By.id("produtovalor")).sendKeys("700001");
        navegador.findElement(By.id("produtocores")).sendKeys("verde, azul, branco");

        // Vou submeter o formulario
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        // Vou validar que a mensagem de erro foi apresentada
        // <div class="toast rounded" style="top: 0px; opacity: 1;">O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00</div>
        String mensagemToast = navegador.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToast);

        // Vou fechar o navegador
        navegador.quit();

    }

    @Test
    @DisplayName("Validar se um produto foi cadastrado com sucesso")
    public void testValidarSeUmProdutoFoiCadastradoComSucesso() {
        // Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();

        // Vou maximizar a tela
        navegador.manage().window().maximize();

        // Vou definir um tempo de espera padrao de 5 segundos
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegar para a pagina da Lojinha Web
        navegador.get("http://165.227.93.41/lojinha-web/v2/");

        // Fazer login
        navegador.findElement(By.cssSelector("label[for='usuario']")).click();
        navegador.findElement(By.id("usuario")).sendKeys("admin");

        navegador.findElement(By.cssSelector("label[for='senha']")).click();
        navegador.findElement(By.id("senha")).sendKeys("admin");

        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        // Vou para tela de registro de produto
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        // Vou preencher dados do produto e o valor sera igual a zero
        navegador.findElement((By.id("produtonome"))).sendKeys("Playstation 4");
        navegador.findElement(By.id("produtovalor")).sendKeys("999");
        navegador.findElement(By.id("produtocores")).sendKeys("verde, azul, branco");

        // Vou submeter o formulario
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        // Vou validar que a mensagem de sucesso foi apresentada
        // <div class="toast rounded" style="top: 0px; opacity: 1;">Produto adicionado com sucesso</div>
        String mensagemToast = navegador.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("Produto adicionado com sucesso", mensagemToast);

        // Vou fechar o navegador
        navegador.quit();

    }

}
