package com.stori.challenge;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class StoriTestCases {

	WebDriver driver;
	
	static ExtentReports informes;
	 static ExtentTest test;
	 
	 @BeforeClass
	 public static void reporte() {
		 informes = new ExtentReports ("Reports/reporte.html", true);
		 test = informes.startTest("Pruebas Selenium");
	 }

	@Before
	public void caso1(){

		System.out.println("Abrir browser");
		System.setProperty("webdriver.chrome.driver", "//Users/macbook/Documents/workspace-spring-tool-suite-4-4.13.0.RELEASE/StoriChallenge/driver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://rahulshettyacademy.com/AutomationPractice/");

	}

	@Test
	public void caso2() {

		driver.findElement(By.xpath("/html/body/div[1]/div[2]/fieldset/input")).sendKeys("Me");
		wait(3);
		driver.findElement(By.xpath("/html/body/ul/li[6]/div")).click();
		wait(2);
		
		test.log(LogStatus.PASS, "Test 2 aprobado");

	}

	@Test
	public void caso3() {

		driver.findElement(By.xpath("/html/body/div[1]/div[3]/fieldset/select")).click();
		wait(2);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/fieldset/select/option[3]")).click();
		wait(3);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/fieldset/select")).click();
		wait(2);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/fieldset/select/option[4]")).click();
		
		test.log(LogStatus.PASS, "Test 3 aprobado");
	}
	
	@Test
	public void caso4() {
		
		driver.findElement(By.xpath("/html/body/div[2]/div[1]/fieldset/button")).click();
		wait(2);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		wait(2);
		String titulo = driver.findElement(By.xpath("/html/body/section[3]/div/div/div/div[5]/div/div[2]/div/div[2]/h3")).getText();
		Assert.assertEquals("30 DAY MONEY BACK GUARANTEE", titulo);
		
		test.log(LogStatus.PASS, "Test 4 aprobado");

	}
	
	@Test
	public void caso5() {
		
			driver.findElement(By.xpath("/html/body/div[2]/div[2]/fieldset/a")).click();
			 Set<String> handles = driver.getWindowHandles();
			 driver.switchTo().window(handles.toArray()[1].toString());
			 wait(3);
			 WebElement element = driver.findElement(By.xpath("/html/body/app-root/div/app-home/div/section[2]/div[2]/a"));
			 Actions actions = new Actions(driver);
			 actions.moveToElement(element);
			 actions.perform();
			 wait(3);
			 captura("viewAllCourses");
			 driver.switchTo().window(handles.toArray()[0].toString());
			 wait(2);
			 
			 test.log(LogStatus.PASS, "Test 5 aprobado");

	}
	
	@Test
	public void caso6() {

		driver.findElement(By.xpath("/html/body/div[2]/div[3]/fieldset/input[1]")).sendKeys("Stori Card");
		wait(2);
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/fieldset/input[2]")).click();
        // Switching to Alert        
		wait(2);
        Alert alert = driver.switchTo().alert();		
        // Capturing alert message.    
        String alertMessage= driver.switchTo().alert().getText();			
        // Displaying alert message	
        wait(2);
        System.out.println(alertMessage);	
        // Accepting alert		
        alert.accept();	
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/fieldset/input[1]")).sendKeys("Stori Card");
		wait(2);
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/fieldset/input[3]")).click();
		 // Switching to Alert        
		wait(2);
        driver.switchTo().alert();		
        // Capturing alert message.    
        String alertMessage2= driver.switchTo().alert().getText();			
        // Displaying alert message	
        wait(2);
        System.out.println(alertMessage2);	
		Assert.assertEquals("Hello Stori Card, Are you sure you want to confirm?", alertMessage2);
		 // Accepting alert		
        alert.accept();	
        
        test.log(LogStatus.PASS, "Test 6 aprobado");

	}
	
	@Test
	public void caso7() {
		
		WebElement tabla = driver.findElement(By.xpath("/html/body/div[3]/div[1]/fieldset/table"));
		WebElement tbody = tabla.findElement(By.tagName("tbody"));
		List<WebElement> tr = tbody.findElements(By.tagName("tr"));
		List<String> cursos = new ArrayList<String>();
		int contador =0;
		for (int i = 1; i < tr.size(); i++) {
			List<WebElement> td = tr.get(i).findElements(By.tagName("td"));
			if(td.get(2).getText().equals("25")) {
				contador++;
				cursos.add(td.get(1).getText());
			}
		}
		
		System.out.println("Total de cursos: " + contador);
		for (int i = 0; i < cursos.size(); i++) {
			System.out.println(cursos.get(i));
		}
		
		test.log(LogStatus.PASS, "Test 7 aprobado");

	}
	
	@Test
	public void caso8() {
		WebElement tabla = driver.findElement(By.xpath("/html/body/div[3]/div[2]/fieldset[2]/div[1]/table"));
		WebElement tbody = tabla.findElement(By.tagName("tbody"));
		wait(2);
		List<WebElement> tr = tbody.findElements(By.tagName("tr"));
		for (int i = 0; i < tr.size(); i++) {
			List<WebElement> td = tr.get(i).findElements(By.tagName("td"));
			if(td.get(1).getText().equals("Engineer")) {
				System.out.println(td.get(0).getText());
			}
		}
		
		test.log(LogStatus.PASS, "Test 8 aprobado");
	}
	
	@Test
	public void caso9() {
		driver.switchTo().frame(0);
		String texto = driver.findElement(By.xpath("/html/body/app-root/div/app-home/div/section[4]/div/div/div/div[2]/ul/li[2]")).getText();
		System.out.println(texto);
		
		test.log(LogStatus.PASS, "Test 9 aprobado");
	}
	

	@After
	public void cierraNavegador() {
		wait(2);
		driver.quit();
		informes.endTest(test);
		informes.flush();

	}

	public static void wait (int segundos) {

		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void scroll () {
		
		try {
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 750)","");
			
		} catch (Exception e) {
			
			System.out.println(e);
			
		}
		
	}
	
	public void captura(String nombre) {
	    TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);

        // creating a destination file
        File destination = new File(nombre + ".png");
        try {
            FileUtils.copyFile(file, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
