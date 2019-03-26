package selenium.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import selenium.config.WebDriverConfig;

public class GoogleTestingApp {

    public static final String SEARCHFIELD_NAME = "q";
    public static final String SEARCHFIELD_XPATH = "//html/body/main/section/form/fieldset/input";

    // <input class="gLFyf gsfi" maxlength="2048" name="q" type="text" jsaction="paste:puy29d" aria-autocomplete="both" aria-haspopup="false" autocapitalize="off" autocomplete="off" autocorrect="off" role="combobox" spellcheck="false" title="Szukaj" value="" aria-label="Szukaj">

    public static void main(String[] args) {


        WebDriver driver = WebDriverConfig.getDriver(WebDriverConfig.CHROME);
        driver.get("https://www.google.com");

        // Przechwytywanie kontrolek na podstawie identyfikatora
        WebElement searchField = driver.findElement(By.name(SEARCHFIELD_NAME));

        // Wyszukiwanie elementów strony za pomocą XPath
        searchField = driver.findElement(By.name(SEARCHFIELD_XPATH));

        // Wyrażenia XPath-Position
        /*input[5]
        oznacza to po prostu "znajdź piąty z kolei input w całym dokumencie HTML".
                Rozwiązanie to odporne jest na wadę rozwiązania XPath-Absolute,
                polegającą na tym, że ktoś może "pod spód" dodać np. dodatkowego diva.
                Nie jest jednak odporne na to, że autor strony wstawi jakiś dodatkowy input
                przed interesującym nas obiektem (i wtedy nasz input stanie się szóstym...)
        */
        searchField.sendKeys("Kodilla");
        searchField.submit();


    }
}
