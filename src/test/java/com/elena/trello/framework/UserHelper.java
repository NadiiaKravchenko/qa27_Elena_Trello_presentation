package com.elena.trello.framework;

import com.elena.trello.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends  HelperBase{


    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void confirmLogin() {
        click(By.cssSelector("#login-submit"));
    }

    public void fillLoginForm(User user) throws InterruptedException {
        type(By.cssSelector("#user"), user.getEmail());
        Thread.sleep(2000);
        click(By.cssSelector("#login"));
        type(By.cssSelector("#password"), user.getPassword());
    }

    public void clickLoginButton() {
        click(By.cssSelector("[href='/login']"));
    }

    public boolean isAvatarPresent() {
        return isElementPresent(By.cssSelector("[data-test-id=header-member-menu-button]"));
    }

    public void logout() {
        //click on avatar
        //click logout
        //confirm logout

    }
}
