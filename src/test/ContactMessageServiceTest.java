package test;

import java.util.Random;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactMessageServiceTest extends AbstractWebDriverTest {

    WebDriver driver;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","C:/eclipse-java-kepler-SR1-win32-x86_64/WebDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://automationpractice.com/index.php");
    }

    @After
    public void shutDown(){
        driver.quit();
    }

    @Test
    public void sendValidMessage() throws InterruptedException{
        click(By.xpath("//*[@id=\"contact-link\"]"));
        //connect = new DBConnect();
        // String test = connect.getData();
        //String[] array = test.split(";");
        Select selectDrop = new Select(driver.findElement(By.xpath("//*[@id=\"id_contact\"]")));
        int count = selectDrop.getOptions().size();
        String id = String.valueOf(random(count));
        System.out.println(id);
        selectByValue(driver.findElement(By.xpath("//*[@id=\"id_contact\"]")), id);
        type(By.xpath("//*[@id=\"email\"]"), "test@test.com");
        type(By.xpath("//*[@id=\"id_order\"]"), "12345-01");
        uploadFileUsingSendKeys();
        type(By.xpath("//*[@id=\"message\"]"), "Text message");
        click(By.xpath("//*[@id=\"submitMessage\"]/span"));
        Thread.sleep(1000);
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/p")).getText().contains("Your message has been"));
        Thread.sleep(2000);

    }

    @Test
    public void sendMessageWithoutDropDown() throws InterruptedException {

        click(By.xpath("//*[@id=\"contact-link\"]"));
        type(By.xpath("//*[@id=\"email\"]"), "test@test.com");
        type(By.xpath("//*[@id=\"id_order\"]"), "1234-01");
        uploadFileUsingSendKeys();
        type(By.xpath("//*[@id=\"message\"]"), "Hi, there!");
        click(By.xpath("//*[@id=\"submitMessage\"]/span"));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/ol/li")).getText().contains("Please select a subject from the list provided."));
        Thread.sleep(2000);

    }

    @Test
    public void sendMessageWithoutEmail() throws InterruptedException {

        click(By.xpath("//*[@id=\"contact-link\"]"));
        Select selectDrop = new Select(driver.findElement(By.xpath("//*[@id=\"id_contact\"]")));
        int count = selectDrop.getOptions().size();
        String id = String.valueOf(random(count));
        System.out.println(id);
        selectByValue(driver.findElement(By.xpath("//*[@id=\"id_contact\"]")), id);
        type(By.xpath("//*[@id=\"id_order\"]"), "1234-01");
        uploadFileUsingSendKeys();
        type(By.xpath("//*[@id=\"message\"]"), "Hi, there!");
        click(By.xpath("//*[@id=\"submitMessage\"]/span"));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/ol/li")).getText().contains("Invalid email address."));
        Thread.sleep(2000);

    }

    @Test
    public void sendMessageWithBlankMessage() throws InterruptedException {

        click(By.xpath("//*[@id=\"contact-link\"]"));
        Select selectDrop = new Select(driver.findElement(By.xpath("//*[@id=\"id_contact\"]")));
        int count = selectDrop.getOptions().size();
        String id = String.valueOf(random(count));
        System.out.println(id);
        selectByValue(driver.findElement(By.xpath("//*[@id=\"id_contact\"]")), id);
        type(By.xpath("//*[@id=\"email\"]"), "test@test.com");
        type(By.xpath("//*[@id=\"id_order\"]"), "1234-01");
        uploadFileUsingSendKeys();
        click(By.xpath("//*[@id=\"submitMessage\"]/span"));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/ol/li")).getText().contains("The message cannot be blank."));
        Thread.sleep(2000);

    }

    public void type(By by, String value){
        driver.findElement(by).sendKeys(value);
    }

    public void click(By by) {
        driver.findElement(by).click();
    }

    public void selectByValue(WebElement element, String value) {
        Select selectElement = new Select(element);
        selectElement.selectByValue(value);
    }

    public int random(int count){
        if (count == 1) {
            count++;
        }
        Random random = new Random();
        return random.nextInt(count);
    }

    public void uploadFileUsingSendKeys() throws InterruptedException {
        String filePath = "C:\\text file.txt";
        WebElement fileInput = driver.findElement(By.id("fileUpload"));
        fileInput.sendKeys(filePath);
        Thread.sleep(1000);
    }

//    public void collectionTest(){
//        List<WebElement> links = driver.findElements(By.tagName("a"));
//
//        for (WebElement link : links) {
//            if(link.getText().equals("")) {
//                link.click();
//                break;
//            }
//        }
//    }

}
