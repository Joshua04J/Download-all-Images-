package Scripts;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;


public class images {

	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String Link = "https://www.google.com/search?q=forest&source=lnms&tbm=isch&sa=X&ved=2ahUKEwi27tf73Or2AhWx4zgGHeFRCUIQ_AUoAXoECAIQAw&biw=1536&bih=708&dpr=1.25";
		
		System.setProperty("webdriver.chrome.driver", "F:\\sele\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(Link);
		Thread.sleep(1500);
		
		
		URL imageURL = null;
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		// js.executeScript("window.scrollBy(0,1000)");
		boolean end = true;
	
		List<WebElement> imgs =  driver.findElements(By.tagName("img"));
		System.out.println("Images found : " + imgs.size());
		int err = 0, countF = 0;
		for (WebElement img  : imgs) {
			String j = img.getAttribute("src");
	            try {
	                //generate url
	                imageURL = new URL(j);
	                //read url and retrieve image
	                BufferedImage bufferedImage = ImageIO.read(new URL(j));
	                File outputfile = new File("./Images/Image "+ countF +".png");
	                ImageIO.write(bufferedImage, "png", outputfile);
	                countF = countF+1; 
	            } catch (IOException e) {
	                e.printStackTrace();
	                err = err+1;
	            }
	          //  js.executeScript("window.scrollBy(0,1000)");
		}
		
		System.out.println("Code Executed Successfully, images downloaded : " + countF+ "& Failed : "+err);
	driver.quit();
	}

}
