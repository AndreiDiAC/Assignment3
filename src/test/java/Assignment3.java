import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
public class Assignment3 {


    WebDriver driver;

    @BeforeClass
    public void BeforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://qa3.vytrack.com/");
    }

    @BeforeMethod
    public void BeforeMethod() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void Test1_Vehicle_Information() throws InterruptedException {
        WebElement userName = driver.findElement(By.xpath("//input[@type='text']"));
        userName.sendKeys("user3");

        WebElement userPassword = driver.findElement(By.xpath("//input[@type='password']"));
        userPassword.sendKeys("UserUser123");

        Thread.sleep(1000);
        WebElement logInButton = driver.findElement(By.xpath("//button[@type='submit']"));
        logInButton.click();

        Thread.sleep(3000);

        WebElement fleetHeader = driver.findElement(By.xpath("//div[@id='main-menu']/ul/li[1]/a/span"));
        fleetHeader.click();

        Thread.sleep(2000);

        WebElement vehicle = driver.findElement(By.xpath("//div[@id='main-menu']/ul/li[1]/div/div/ul/li[3]/a/span"));
        vehicle.click();


        Thread.sleep(3000);

        String actualTitle = driver.getTitle();
        String expectedTitle = "Car - Entities - System";

        Assert.assertTrue(actualTitle.contains(expectedTitle));


    }

        @Test
        public void Test2_General_Information() throws InterruptedException {
            Thread.sleep(2000);
            for (int f = 1; f <= 2; f++) {


                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                for (int i = 1; i <= 25; i++) {
                    WebElement car = driver.findElement(By.xpath("//tbody[@class='grid-body']/tr[" + i + "]"));
                    car.click();

                    String expectedHeader = "General Information";
                    String actualHeader = driver.findElement(By.xpath("//h5[@class='user-fieldset']")).getText();

                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    Assert.assertEquals(actualHeader, expectedHeader, "Failed on line " + i);
                    Thread.sleep(1000);
                    driver.navigate().back();

                }

                Thread.sleep(2000);
                WebElement nextArrow = driver.findElement(By.xpath("//i[@class='fa-chevron-right hide-text']"));
                nextArrow.click();
                Thread.sleep(2000);
            }

        }


        @Test
        public void Test3_Event_Test() throws InterruptedException {
            Thread.sleep(2000);

            WebElement car = driver.findElement(By.xpath("//tbody[@class='grid-body']/tr[1]"));
            car.click();
            Thread.sleep(3000);


            WebElement addEventButton = driver.findElement(By.xpath("//a[@data-bound-component='oroui/js/app/components/widget-component']"));
            addEventButton.click();
            Thread.sleep(2000);

            String sendKey = "Event2";
            driver.findElement(By.xpath("//input[@data-name='field__title']")).sendKeys(sendKey);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(3000);

            String findEvent = driver.findElement(By.xpath("//div[@class='items list-box list-shaped']/div/div/div/div[4]/div")).getText();
            System.out.println(findEvent);

            Thread.sleep(3000);

            Assert.assertEquals(findEvent,sendKey);

        }

    @Test
    public void Test4_Reset_Settings() throws InterruptedException {
        Thread.sleep(2000);
        WebElement userName = driver.findElement(By.xpath("//i[@class='fa-caret-down']"));
        userName.click();

        Thread.sleep(2000);
        WebElement myConfiguration = driver.findElement(By.xpath("//ul[@role='menu']/li[2]"));
        myConfiguration.click();

        Thread.sleep(2000);
        WebElement firstCheckBox = driver.findElement(By.xpath("//input[@data-ftid='localization_oro_locale___locale_use_parent_scope_value']"));
        firstCheckBox.click();

        Thread.sleep(2000);
        WebElement locateElem = driver.findElement(By.xpath("//select[@data-ftid='localization_oro_locale___locale_value']"));
        locateElem.click();
        Select select = new Select(locateElem);
        select.selectByVisibleText("Croatian");

        String actualCountry = select.getFirstSelectedOption().getText();
        String expectedCountry = "Croatian";

        Assert.assertEquals(actualCountry,expectedCountry);




        Thread.sleep(2000);
        WebElement saveButton = driver.findElement(By.xpath("//button[@type='submit']"));
        saveButton.click();


        Thread.sleep(4000);

        WebElement resetButton = driver.findElement(By.xpath("//button[@type='reset']"));
        resetButton.click();

        Thread.sleep(2000);
        WebElement acceptAlert = driver.findElement(By.xpath("//a[@class='btn ok btn-primary']"));
        acceptAlert.click();

        Thread.sleep(2000);

        WebElement save = driver.findElement(By.xpath("//button[@type='submit']"));
        save.click();

        Thread.sleep(2000);
        WebElement firstBox = driver.findElement(By.xpath("//input[@data-ftid='localization_oro_locale___locale_use_parent_scope_value']"));
        firstBox.click();

        Thread.sleep(2000);
        WebElement locateElement = driver.findElement(By.xpath("//select[@data-ftid='localization_oro_locale___locale_value']"));
        Select select1 = new Select(locateElement);
        String actualResult = select1.getFirstSelectedOption().getText();
        String expectedResult = "English";

        Assert.assertEquals(actualResult,expectedResult);







//        List<WebElement> checkBoxList = driver.findElements(By.xpath("//input[@value='1']"));
//        Thread.sleep(3000);
//        System.out.println(checkBoxList.size());
    }

    }

