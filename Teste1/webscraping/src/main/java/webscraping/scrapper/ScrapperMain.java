package webscraping.scrapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

public class ScrapperMain {

    public static void main(String[] args) throws InterruptedException {

    //OBS: caso a página demore a carregar, execute o código novamente.

        String url = "https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss";

        System.setProperty("webdriver.chrome.driver","webscraping/src/main/resources/chromedriver.exe");
        ChromeDriver driver;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

        driver.get(url);
        System.out.println("URL Atual: " + driver.getCurrentUrl());

        // Aceitar cookies
        WebElement aceitoButton = driver.findElement(By.xpath("//*[@id=\"lgpd-cookie-banner-janela\"]/div[2]/button"));
        if (aceitoButton != null) {
            aceitoButton.click();
            Thread.sleep(2000);
        }

        // Rolar a página até o elemento
        // Coloquei um elemento abaixo do 'element' apenas para ficar melhor visualmente
        WebElement h2 = driver.findElement(By.xpath("//*[@id=\"parent-fieldname-text\"]/h2[2]"));
        Coordinates coordinate = ((Locatable)h2).getCoordinates();
        coordinate.onPage();
        coordinate.inViewPort();
        Thread.sleep(2000);

        WebElement element = driver.findElement(By.xpath("//*[@id=\"parent-fieldname-text\"]/p[3]/a"));
        element.click();

        driver.getCurrentUrl();
        System.out.println("URL Atual: " + driver.getCurrentUrl());

        // Rolar a página até o Componente Organizacional
        WebElement componenteOrganizacional = driver.findElement(
                By.xpath("//*[@id=\"parent-fieldname-text\"]/div/table/tbody/tr[1]/td[1]"));
        coordinate = ((Locatable)componenteOrganizacional).getCoordinates();
        coordinate.onPage();
        coordinate.inViewPort();

        Thread.sleep(3000);

        // Abrir o PDF
        WebElement visualizarButton = driver.findElement(
                By.xpath("//*[@id=\"parent-fieldname-text\"]/div/table/tbody/tr[1]/td[3]/a"));
        visualizarButton.click();
    }
}
