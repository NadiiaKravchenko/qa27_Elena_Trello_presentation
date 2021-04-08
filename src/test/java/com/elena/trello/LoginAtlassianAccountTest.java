package com.elena.trello;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginAtlassianAccountTest {
    WebDriver wd;

    @BeforeMethod
    public  void setUp(){
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wd.manage().window().maximize();
        wd.navigate().to("https://trello.com/");
    }

    @Test
    public void testAtlassianLogin() throws InterruptedException {
        clickLoginButton();
        fillLoginForm();
        confirmLogin();
    }

    private void confirmLogin() {
        wd.findElement(By.cssSelector("#login-submit")).click();
    }

    private void fillLoginForm() throws InterruptedException {
        wd.findElement(By.cssSelector("#user")).click();
        wd.findElement(By.cssSelector("#user")).clear();
        wd.findElement(By.cssSelector("#user")).sendKeys("rochman.elena@gmail.com");
Thread.sleep(2000);
        wd.findElement(By.cssSelector("#login")).click();

        //
        wd.findElement(By.cssSelector("#password")).click();
        wd.findElement(By.cssSelector("#password")).clear();
        wd.findElement(By.cssSelector("#password")).sendKeys("12345.com");

    }

    private void clickLoginButton() {
        wd.findElement(By.cssSelector("[href='/login']")).click();
    }

    @AfterMethod
    public void tearDown(){
        wd.quit();
    }
}
