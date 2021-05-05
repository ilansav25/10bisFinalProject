package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.xml.LaunchSuite.ExistingSuite;

public class Main extends Base {

	public Main(WebDriver driver) {
		super(driver);
	}

	// start registration
	public boolean register() throws InterruptedException {

		click(By.xpath("//button[text()='הרשמה']"));
		if (isExist(By.id("modal-title")))
			return true;
		else
			return false;

	}
	
	// start login
		public boolean login() throws InterruptedException {
			
			//Click connection
			
			Thread.sleep(3000);
			click(By.xpath("//button[text()='התחברות']"));
			
			
			Thread.sleep(3000);
			if (isExist(By.xpath("//*[@id=\"modal-title\"][text()='איזה כיף שחזרת אלינו!']")))
				return true;
			else
				return false;

		}

	// select account
	public boolean selectAccount() throws InterruptedException {

		click(By.cssSelector("#other > div.question.first-question > div"));
		click(By.xpath(
				"//div[@class='dropdown custom-select step-one open']//li[@data-value='freelancer-or-smb'][@class='option']"));
		return true;
	}
	
	// select account
	public boolean searchClickRest(String rest) throws InterruptedException {

		// type rest name
		typeText(By.cssSelector("input.SearchDropdown__SearchInput-p71rkk-6"), rest);
		Thread.sleep(1000);
		click(By.xpath("//*[@id=\"searchResults\"]/div/a[1]/div/div[1]/div"));
		String name = getText(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/section/header/div/div[2]/div[1]/h1"));
		
		if(name.equals(rest))
			return true;
		else
			return false;
	}
	
	// select meal
	public boolean mealOrder() throws InterruptedException {

		// click on meal
		click(By.xpath("//*[@id=\"menu-body-wrapper\"]/div[2]/div/section[1]/div/div[2]/div[3]/button"));
		Thread.sleep(3000);
		click(By.xpath("//*[@id=\"modals\"]/div/div/div/div/div/div[4]/div/button"));
		Thread.sleep(3000);
		click(By.xpath("//*[@id=\"menu-body-wrapper\"]/div[2]/div/section[2]/div/div[2]/div[3]/button"));
		Thread.sleep(3000);
		click(By.xpath("//*[@id=\"modals\"]/div/div/div/div/div/div[4]/div/button"));
		Thread.sleep(3000);
		click(By.xpath("//*[@id=\"menu-body-wrapper\"]/div[2]/aside/div/div[2]/div[1]/button"));
			return true;
	}
	
	// select meal
	public boolean MinimalMealOrder(String errorMsg) throws InterruptedException {

		// click on meal
		click(By.xpath("//*[@id=\"menu-body-wrapper\"]/div[2]/div/section[1]/div/div[2]/div[3]/button"));
		Thread.sleep(3000);
		click(By.xpath("//*[@id=\"modals\"]/div/div/div/div/div/div[4]/div/button"));
		Thread.sleep(3000); 
		click(By.xpath("//*[@id=\"menu-body-wrapper\"]/div[2]/aside/div/div[2]/div[1]/button"));
		String Msg = getText(By.xpath("//*[@id=\"modal-content\"]/div[1]"));	
		
		if(Msg.equals(errorMsg))
		return true;
		else
			return false;
	}
}
