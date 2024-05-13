package CAS;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class App {
	WebDriver driver;
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void DriverSetUp(String br, String url) {
		if (br.equals("chrome")) 
		{
			driver = new ChromeDriver();
		} 
		else if (br.equals("edge")) {
			driver = new EdgeDriver();
		} else if (br.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.get(url);

	}

	@Test(priority = 1)
	public void Test1() throws InterruptedException {
		Thread.sleep(15000);
		driver.findElement(By.xpath("//button[@id='O365_MainLink_Me']")).click();
		Thread.sleep(5000);
		System.out.println();
		System.out.println("User Information -->");
		System.out.println("====================");
		System.out.println(driver.findElement(By.id("mectrl_currentAccount_primary")).getText());
		System.out.println(driver.findElement(By.id("mectrl_currentAccount_secondary")).getText());
		System.out.println();
		driver.findElement(By.xpath("//button[@id='mectrl_main_trigger']")).click();
	}

	@Test(priority = 2)
	public void news() throws InterruptedException 
	{
		List<WebElement> ele = driver.findElements(By.xpath("//a[@data-automation-id='newsItemTitle']"));
		int n = ele.size();
		System.out.println("====================");
		System.out.println(n+" News element found!");
		System.out.println("====================");
		System.out.println();
		Actions a = new Actions(driver);
		for (int i=0;i<n;i++) 
		{
			List<WebElement> e = driver.findElements(By.xpath("//a[@data-automation-id='newsItemTitle']"));
			WebElement element = e.get(i);
			a.moveToElement(element).build().perform();
			Thread.sleep(5000);
			System.out.println("NEWS-" + (i+1) + " -->");
			System.out.println("=====================================================================================");
			if(element.isDisplayed())
				System.out.println("News element is displayed !");
			else
				System.out.println("News element is not displayed !");
			System.out.println();
			String s1 = element.getText();
			System.out.println("News header -->" + s1);
			System.out.println();
			String s2 = element.getAttribute("title");
			System.out.println("News tooltip -->" + s2);
			System.out.println();
			if (s1.equals(s2))
				System.out.println("News header matched with tooltip!");
			else
				System.out.println("News header didn't match with tooltip!");
			System.out.println();
			
			element.click();
			Thread.sleep(10000);
			
			System.out.println("News text -->");
			System.out.println("-------------");
			List<WebElement> s = driver.findElements(By.xpath("//div[@data-sp-feature-tag='Rich Text Editor']/div[1]/p"));
			int count=0;
			for(WebElement e1:s) 
			{
		    	System.out.println(e1.getText());
		    	count++;
		    	if(count>12) {
		    		break;}
		    }
			System.out.println("-------------------------------------------------------------------");
			System.out.println();
			if(driver.findElement(By.xpath("//button[@name='Share']")).isDisplayed())
				System.out.println("Share option is displayed.");
			else
				System.out.println("Share option is not displayed.");
			System.out.println();
			driver.findElement(By.xpath("//button[@name='Share']")).click();
			List<WebElement> shareList=driver.findElements(By.xpath("//li[@role='presentation']/button/div/div/span[1]"));
			System.out.println("Options -->");
			System.out.println("-----------");
			for(WebElement p:shareList)
			{
				System.out.println(p.getText());
			}
			System.out.println("------------------------");
			
			
			if(i==4)
				break;
			
			
			
			a.moveToElement(driver.findElement(By.xpath("//div[@aria-haspopup='dialog']"))).build().perform();
			Thread.sleep(10000);
			System.out.println();
			if(driver.findElement(By.xpath("//div[@data-log-name='Person']/button/div")).isDisplayed())
			System.out.println("Associate details is displayed!");
			else
			System.out.println("Associate details is not displayed!");
			System.out.println();
			System.out.println("Associate details --> ");
			System.out.println("---------------------");
			s1=driver.findElement(By.xpath("//h1[@data-log-name='DisplayName']")).getText();
			s2=driver.findElement(By.xpath("//h1[@data-log-name='DisplayName']/parent::div/following-sibling::div[1]")).getText();
			System.out.println(s1);
			System.out.println(s2);
			
			
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			WebElement like=driver.findElement(By.xpath("//*[@id='CommentsWrapper']"));
			js.executeScript("arguments[0].scrollIntoView();", like);
			a.moveToElement(like).build().perform();
			Thread.sleep(5000);
			String str1 = driver.findElement(By.xpath("//div[@data-sp-feature-tag='Comments']/div/aside/div[1]/button/span/span/span")).getText();
			String str2 = driver.findElement(By.xpath("//div[@data-sp-feature-tag='Comments']/div/aside/div[4]/button/span/span/span")).getText();
			String[] arr = str1.split(" ", 2);
	        s1=arr[0];
	        arr = str2.split(" ", 2);
	        s2=arr[0];
	        System.out.println("-----------------");
	        System.out.println("No of likes : "+s1);
	        System.out.println("No of views : "+s2);
	        System.out.println("-----------------");
	        System.out.println();
	        System.out.println();
			//Thread.sleep(10000);
			driver.navigate().back();
			Thread.sleep(5000);
			
			
		}
	}

	@AfterClass
	public void Close() {
		driver.quit();
	}
}
