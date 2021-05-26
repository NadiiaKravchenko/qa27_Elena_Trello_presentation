package com.elena.trello.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderHelper extends HelperBase{
    public HeaderHelper(WebDriver wd) {
        super(wd);
    }

    public void goHome() {
        click(By.cssSelector("[data-test-id='header-home-button']"));
    }

    public boolean buttonPlusPresent() {
        return isElementPresent(By.cssSelector("[data-test-id='header-create-menu-button']"));
    }
}
