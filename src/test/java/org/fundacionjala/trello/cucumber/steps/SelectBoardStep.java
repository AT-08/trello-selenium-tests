package org.fundacionjala.trello.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import org.fundacionjala.trello.pages.board.Boards;
import org.fundacionjala.trello.pages.board.BoardsDrawer;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * Step definitions for Delete dashboard.
 */
public class SelectBoardStep {

    @Autowired
    private Boards boards;

    @Autowired
    private BoardsDrawer boardsDrawer;

    /**
     * Select Board by title.
     *
     * @param titleBoard input String.
     */
    @Given("I select board {string}")
    public void iSelectBoard(final String titleBoard) {
        boards.clickBtnHeaderBoard();
        boardsDrawer.clickBoardInBoardsDrawer(titleBoard);
    }

    /**
     * Verify if board exists .
     *
     * @param titleBoard title Board.
     */
    @Then("I don't should see the board {string}")
    public void iDonTShouldSeeTheBoard(final String titleBoard) {
        boards.clickBtnHeaderBoard();
        assertFalse(boardsDrawer.doIsExistBoard(titleBoard));
    }

    /**
     * Verify if board is close.
     *
     * @param titleBoard title Board.
     */
    @Then("The {string} board should be close")
    public void theBoardShouldBeClose(final String titleBoard) {
        boards.clickBtnHeaderBoard();
        assertEquals(boardsDrawer.getTitle().split("\n")[0], String.format("%s is closed.", titleBoard));
    }
}

