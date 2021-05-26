package com.elena.trello.tests;

import com.elena.trello.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginAtlassianAccountTest extends TestBase{


    @BeforeMethod
    public void preconditions(){
        if(app.getUser().isAvatarPresent()){
            app.getUser().logout();
           app.goURL("https://trello.com");
        }
    }

    @Test
    public void testloginAtlassian() throws InterruptedException {

        app.getUser().clickLoginButton();
        app.getUser().fillLoginForm(new User()
                .withEmail("rochman.elena@gmail.com")
                .withPassword("1234.com"));
        app.getUser().confirmLogin();
Thread.sleep(3000);
        Assert.assertTrue(
                app.getUser().isAvatarPresent(), "Avatar isn't present- user not logged in");

      //  Assert.assertEquals(wd.findElement(By.cssSelector("[data-test-id=header-member-menu-button]")).getAttribute("title"),"rochman.elena (rochmanelena)");

    }

    @Test
    public void negativeTestLogin() throws InterruptedException {
        app.getUser().clickLoginButton();
        app.getUser().fillLoginForm(new User().withEmail("rochman.elena@gmail.com").withPassword("12345.cOm"));
        app.getUser().confirmLogin();

    }

    @Test
    public void negativeTestLoginwithoutPassword() throws InterruptedException {
        app.getUser().clickLoginButton();
        app.getUser().fillLoginForm(new User().withEmail("rochman.elena@gmail.com"));
        app.getUser().confirmLogin();

    }

}
