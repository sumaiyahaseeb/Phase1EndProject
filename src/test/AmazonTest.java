package test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		
		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("samsung mobile");
		WebElement submit = driver.findElement(By.id("nav-search-submit-button"));
		submit.click();
		
	
		List<WebElement> product_name = driver
				.findElements(By.xpath("//div[@class='a-section']//h2//span"));
		List<WebElement> pricesymbol = driver
				.findElements(By.xpath("//div[@data-component-type='s-search-result']//div[contains(@class,'price')]//span[@class='a-price-symbol']"));
		List<WebElement> product_price = driver
				.findElements(By.xpath("//div[@data-component-type='s-search-result']//div[contains(@class,'price')]//span[@class='a-price-whole']"));
		
		for(int i=0;i<product_name.size();i++) {
			System.out.println("product names and prices are :" +product_name.get(i).getText() +"  " +pricesymbol.get(i).getText() +"" +product_price.get(i).getText());
		}
		TakesScreenshot tsobj =(TakesScreenshot) driver;
		File fileobj = tsobj.getScreenshotAs(OutputType.FILE);
		File screenshotobj =new File("amazon.png");
		FileUtils.copyFile(fileobj, screenshotobj);
		driver.close();
	}
	
}
