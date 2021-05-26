package com.elena.trello.tests;

import com.elena.trello.model.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTests extends  TestBase{
    @BeforeMethod
    public void ensurePreconditions() throws InterruptedException {
        if(!app.getUser().isAvatarPresent()){
            app.getUser().clickLoginButton();
            app.getUser().fillLoginForm(new User()
                    .withEmail("rochman.elena@gmail.com").withPassword("12345.com"));
            app.getUser().confirmLogin();
        }
        if(!app.getBoard().isThereABoard()){

            app.getBoard().createBoard();
        }
    }

    @Test
    public void testBoardDeletion(){

        int count = app.getBoard().getBoardsCount();
       while (count > 1) {
            app.getBoard().openFirstBoard();
            app.getBoard().openMenu();
            app.getBoard().openExtendedMenu();
            app.getBoard().deleteBoard();
            app.getHeader().goHome();
           count = app.getBoard().getBoardsCount();
       }
    }
}
