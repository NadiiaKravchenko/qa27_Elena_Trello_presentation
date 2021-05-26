package com.elena.trello.tests;

import com.elena.trello.model.Board;
import com.elena.trello.model.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BoardCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> boards() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader =  new BufferedReader(new FileReader(new File("src/test/resources/boards.csv")));
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[]{new Board()
                    .setBoardName(split[0])
                    .setVisibility(split[1])});
            line = reader.readLine();
        }

        return  list.iterator();
    }


    @BeforeMethod
    public void preconditions() throws InterruptedException {
        if(!app.getUser().isAvatarPresent()){
            app.getUser().clickLoginButton();
            app.getUser().fillLoginForm(new User()
                    .withEmail("rochman.elena@gmail.com").withPassword("12345.com"));
            app.getUser().confirmLogin();
        }
        if(!app.getHeader().buttonPlusPresent()){
            app.getHeader().goHome();
        }

    }

    @Test
    public void testBoardCreation() {
        app.getBoard().initBoardCreationFromBoardsList();
        app.getBoard().fillBoardCreationForm(new Board()
                .setBoardName("111" + System.currentTimeMillis())
                .setVisibility("PublicIcon"));

        app.getBoard().submitBoardCreationForm();
    }


    @Test(dataProvider = "boards")
    public void testBoardCreationDataProvider(Board board) {
        app.getBoard().initBoardCreationFromBoardsList();
        app.getBoard().fillBoardCreationForm(board);

        app.getBoard().submitBoardCreationForm();
    }



}
