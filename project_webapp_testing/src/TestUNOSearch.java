import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.time.Duration;

public class TestUNOSearch {
   private WebDriver driver;
   private String baseUrl;
   private boolean acceptNextAlert = true;
   private StringBuffer verificationErrors = new StringBuffer();
   JavascriptExecutor js;

   @Before
   public void setUp() throws Exception {
      System.setProperty("webdriver.chrome.driver", "C:\\Users\\thema\\Downloads\\project_webapp_testing\\project_webapp_testing\\lib\\win\\chromedriver.exe");
      driver = new ChromeDriver();
      baseUrl = "https://www.unomaha.edu/";
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
      js = (JavascriptExecutor) driver;
   }

   @Test
   public void testUNOSearch() throws Exception {
      driver.get("https://www.unomaha.edu/");
      driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Search UNO'])[1]/following::*[name()='svg'][1]")).click();
      driver.findElement(By.id("search-input")).click();
      driver.findElement(By.id("search-input")).clear();
      Thread.sleep(2000);
      driver.findElement(By.id("search-input")).sendKeys("computer science");
      Thread.sleep(2000);
      driver.findElement(By.xpath("//input[@value='SEARCH']")).click();
   }

   @After
   public void tearDown() throws Exception {
      driver.quit();
      String verificationErrorString = verificationErrors.toString();
      if (!"".equals(verificationErrorString)) {
         fail(verificationErrorString);
      }
   }

   private boolean isElementPresent(By by) {
      try {
         driver.findElement(by);
         return true;
      } catch (NoSuchElementException e) {
         return false;
      }
   }

   private boolean isAlertPresent() {
      try {
         driver.switchTo().alert();
         return true;
      } catch (NoAlertPresentException e) {
         return false;
      }
   }

   private String closeAlertAndGetItsText() {
      try {
         Alert alert = driver.switchTo().alert();
         String alertText = alert.getText();
         if (acceptNextAlert) {
            alert.accept();
         }
         else {
            alert.dismiss();
         }
         return alertText;
      } finally {
         acceptNextAlert = true;
      }
   }
}
