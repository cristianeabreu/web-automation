package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListaDeProdutosPage {
    private WebDriver navegador;

    public ListaDeProdutosPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public formularioDeAdicaoDeProdutosPage acessarFormularioAdicaoNovoProduto() {
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        return new formularioDeAdicaoDeProdutosPage(navegador);
    }
}
