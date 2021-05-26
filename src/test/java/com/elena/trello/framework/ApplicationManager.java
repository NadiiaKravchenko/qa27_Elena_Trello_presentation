package com.elena.trello.framework;

import com.elena.trello.tests.TestBase;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApplicationManager{
   // WebDriver wd;
    EventFiringWebDriver wd;
    UserHelper user;
    BoardHelper board;
    HeaderHelper header;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {

        if(browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
        } else if (browser.equals(BrowserType.CHROME)){
            wd = new EventFiringWebDriver(new ChromeDriver());
        }else if (browser.equals(BrowserType.IE)){
            wd = new EventFiringWebDriver(new InternetExplorerDriver());
        }

        wd.register(new TestBase.MyListener());

        wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wd.manage().window().maximize();
        wd.navigate().to("https://trello.com/");
        user = new UserHelper(wd);
        board = new BoardHelper(wd);
        header = new HeaderHelper(wd);
    }

    public void stop() {
        wd.quit();
    }

    public UserHelper getUser() {
        return user;
    }

    public BoardHelper getBoard() {
        return board;
    }

    public HeaderHelper getHeader() {
        return header;
    }

    public String getCurrentURL() {
       return wd.getCurrentUrl();
    }

    public void goURL(String url) {
        wd.get(url);
    }

    public void makeScreenshot(long time){
        File tmp = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("Screenshots/screenshot-"+ time +".png");


        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
