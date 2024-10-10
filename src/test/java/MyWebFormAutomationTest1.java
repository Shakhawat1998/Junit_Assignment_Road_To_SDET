import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import utils.Utils;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyWebFormAutomationTest1 {
    WebDriver driver;

    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://www.digitalunite.com/practice-webform-learners");

    }

    @Test
    public void formAutomation() throws InterruptedException {

        driver.get("https://www.digitalunite.com/practice-webform-learners");



        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        Utils.scroll(driver,500);

        driver.findElement(By.id("edit-name")).sendKeys("Shakhawat Ridoy");
        Thread.sleep(1000);
        driver.findElement(By.id("edit-number")).sendKeys("01921506033");



        WebElement txtCalendarElem = driver.findElement(By.id("edit-date"));
        txtCalendarElem.sendKeys(Keys.CONTROL, "a");
        txtCalendarElem.sendKeys(Keys.BACK_SPACE);
        txtCalendarElem.sendKeys("19/11/1998");

        driver.findElement(By.id("edit-email")).sendKeys("shakhawatridoy@gmail.com");


        String relativePath="\\src\\test\\resources\\isbank.png";
        String absolutePath=System.getProperty("user.dir")+relativePath;
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(absolutePath);

        Thread.sleep(6000);

        Utils.scroll(driver,500);

        driver.findElement(By.id("edit-age")).click();

        driver.findElement(By.id("edit-submit")).click();

        String actualResult = driver.findElement(By.id("block-pagetitle-2")).getText();

        Assertions.assertTrue(actualResult.contains("Thank you for your submission!"));



    }

    @AfterAll
    public void tearDown(){
        driver.quit();
    }



}
