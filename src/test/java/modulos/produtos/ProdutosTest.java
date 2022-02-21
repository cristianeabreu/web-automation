package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produto")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach() {
        // Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver_win32\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        // Vou maximizar a tela
        this.navegador.manage().window().maximize();

        // Vou definir um tempo de espera padrao de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegar para a pagina da Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");

    }

    @Test
    @DisplayName("Nao e permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {
      // Fazer login
        new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto();

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

    }

    @Test
    @DisplayName("Nao e permitido registrar um produto com valor maior que 7000,00")
    public void testNaoEPermitidoRegistrarProdutoComValorMaiorQueSeteMil() {
        // Fazer login
        new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto();

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

    }

    @Test
    @DisplayName("Validar se um produto foi cadastrado com sucesso")
    public void testValidarSeUmProdutoFoiCadastradoComSucesso() {
         // Fazer login
        new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto();

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
    }

    @AfterEach
    public void afterEach() {
        // Vou fechar o navegador
        navegador.quit();
    }
}
