import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;
import java.time.Year;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyWebFormAutomationTest2 {
    WebDriver driver;

    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");

    }





    @Test
    public void formAutomation() throws InterruptedException {
        driver.get("https://demo.wpeverest.com/user-registration/guest-registration-form/");
        driver.findElement(By.id("first_name")).sendKeys("Shakhawat");
        driver.findElement(By.id("last_name")).sendKeys("Hossain");
        driver.findElement(By.id("user_pass")).sendKeys("6511ASHs@011");
        driver.findElement(By.id("user_email")).sendKeys("shakhawatpi@gmail.com");
        driver.findElement(By.id("radio_1665627729_Male")).click();
        driver.findElement(By.id("input_box_1665629217")).sendKeys("Bangladeshi");
        List<WebElement> phoneField = driver.findElements(By.id("phone_1665627880"));
        phoneField.get(1).sendKeys("01990150241");

        String year="2024";
        String day = "19";

        Utils.scroll(driver,200);
        driver.findElement(By.xpath("//input[@class='ur-flatpickr-field regular-text without_icon flatpickr-input']")).click();


        while(true){
            String curYear = driver.findElement(By.className("cur-year")).getAttribute("value");

            if(curYear.contains(year)){
                break;
            }

            driver.findElement(By.className("flatpickr-prev-month")).click();

        }
        Select dropmonth = new Select(driver.findElement(By.className("flatpickr-monthDropdown-months")));

        dropmonth.selectByVisibleText("May");

        List<WebElement> alldates = driver.findElements(By.xpath("//div[@class='dayContainer']//span"));
        for(WebElement date:alldates){
            if (date.getText().contains(day)){
                date.click();
                break;
            }
        }

        Select select= new Select(driver.findElement(By.id("country_1665629257")));
        select.selectByVisibleText("Bangladesh");



        Utils.scroll(driver, 500);
        Thread.sleep(3000);
        Utils.scroll(driver, 500);
        driver.findElement(By.id("privacy_policy_1665633140")).click();
        List <WebElement> submitbtns = driver.findElements(By.cssSelector("[type=submit]"));
        submitbtns.get(2).click();
        String actuallResult = driver.findElement(By.xpath("//div[@id='ur-submit-message-node']//ul")).getText();
        Assertions.assertTrue(actuallResult.contains("User successfully registered"));




    }

    //@AfterAll
    public void tearDown(){
        driver.quit();
    }



}
