package com.elena.trello.framework;

import com.elena.trello.model.Board;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BoardHelper extends HelperBase{

    public BoardHelper(WebDriver wd) {
        super(wd);
    }

    public void submitBoardCreationForm() {
        if(isElementPresent(By.xpath("//button[@class='_3TTqkG5muwOzqZ _3Ik0JLsERwh6Ui _3HfJ71CiQ9nm2y']"))){
            click(By.xpath("//button[@class='_3TTqkG5muwOzqZ _3Ik0JLsERwh6Ui _3HfJ71CiQ9nm2y']"));
        }
        //click(By.cssSelector("._21upOlzpUQJcdT.gkv95EhjCrfcEU"));
        click(By.cssSelector("[data-test-id='create-board-submit-button']"));
    }

    public void fillBoardCreationForm(Board board) {
        type(By.xpath("//input[@data-test-id='create-board-title-input']"), board.getBoardName());

        selectBoardVisibility(board.getVisibility());
    }

    public void selectBoardVisibility(String visibility) {
        click(By.xpath("//*[@aria-label='DownIcon']/../.."));
        click(By.xpath("//*[@aria-label='" + visibility + "']/../.."));
    }

    public void initBoardCreationFromBoardsList() {
        click(By.xpath("//*[@data-test-id='create-board-tile']"));
    }

    public boolean isThereABoard() {
        return isElementPresent(By.cssSelector(".boards-page-board-section-list-item"));
    }

    public void createBoard() {
        initBoardCreationFromBoardsList();
        fillBoardCreationForm(new Board().setBoardName("board" + TimeUnit.SECONDS).setVisibility("PublicIcon"));

        submitBoardCreationForm();
    }

    public void openFirstBoard() {
        click(By.cssSelector(".boards-page-board-section-list-item"));
    }

    public void openMenu() {
        if(!isElementPresent(By.cssSelector(".js-open-more"))){
            click(By.cssSelector(".icon-back"));
        }

    }

    public void openExtendedMenu() {
        click(By.cssSelector(".js-open-more"));
    }

    public void deleteBoard() {
        click(By.cssSelector(".js-close-board"));
        click(By.cssSelector(".js-confirm"));
        click(By.cssSelector(".js-delete"));
        click(By.cssSelector(".js-confirm"));
    }

    public int getBoardsCount() {
        return wd.findElements(By.cssSelector(".boards-page-board-section-list-item")).size()-1;
    }
}
