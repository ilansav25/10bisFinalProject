package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.xml.LaunchSuite.ExistingSuite;

public class Order extends Base {

	public Order(WebDriver driver) {
		super(driver);
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

}
