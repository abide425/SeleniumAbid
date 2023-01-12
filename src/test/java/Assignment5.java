import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class Assignment5 {

    WebDriver driver;


    @BeforeClass
    void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/login");
        driver.manage().window().maximize();

    }

    @Test
    void test() throws InterruptedException {

        //Login to Bookstore app
        driver.findElement(By.cssSelector("#userName")).sendKeys("Martin425");
        driver.findElement(By.cssSelector("#password")).sendKeys("Martin425@");
        Thread.sleep(1000);

        // Click on login button
        driver.findElement(By.cssSelector("#login")).click();

        //Assert that the user is landed on the Profile page
        String expectedTitle = "ToolsQA";
        String actualTittle = driver.getTitle();
        Assert.assertEquals(actualTittle, expectedTitle);
        Thread.sleep(3000);

        // Scrolling down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,650)", "");

        // Click on the Book Store
        driver.findElement(By.cssSelector("#gotoStore")).click();
        Thread.sleep(2000);

        // Selecting Book
        driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[2]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]")).click();


        // Scrolling down
        js.executeScript("window.scrollBy(0,650)", "");
        Thread.sleep(2000);

        //Select a book add to your collection
        driver.findElement(By.xpath("//button[contains(text(),'Add To Your Collection')]")).click();
        Thread.sleep(2000);

        //click "OK" to dismiss alert

        driver.switchTo().alert().dismiss();
        Thread.sleep(1000);

        //scrolling down
        js.executeScript("window.scrollBy(0,350)", "");

        //click profile tab
        driver.findElement(By.cssSelector(".element-group:last-child #item-3")).click();
        Thread.sleep(1000);

        //confirm the Book that was added is added to the collection.
        By bookCollection = new By.ByXPath("//div[contains(text(),'Richard E. Silverman')]");
        WebElement bookCollectionEL = driver.findElement(bookCollection);
        String name = bookCollectionEL.getText();
        Assert.assertTrue(name.contains("Richard"));
        Thread.sleep(3000);

        //click delete
        driver.findElement(By.cssSelector("[title=\"Delete\"]")).click();
        Thread.sleep(1000);

        //click ok
        driver.findElement(By.cssSelector("#closeSmallModal-ok")).click();
        Thread.sleep(2000);

        //click "ok" to dismiss alert
        driver.switchTo().alert().dismiss();
        Thread.sleep(1000);

    }

    @AfterClass
    void done() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}

