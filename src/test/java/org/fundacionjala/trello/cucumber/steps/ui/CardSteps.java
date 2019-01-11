package org.fundacionjala.trello.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fundacionjala.core.Environment;
import org.fundacionjala.core.ui.Commons;
import org.fundacionjala.trello.pages.board.SelectedBoard;
import org.fundacionjala.trello.pages.card.SelectedCard;

import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


/**
 * Step definitions for the Dashboard Creation.
 */
public class CardSteps {

    private static final Environment ENVIRONMENT = Environment.getInstance();
    private SelectedBoard board;
    private SelectedCard card;

    private String member = "Member";
    private String readJsonUsername = "$['credentials']['%s']['username']";
    /**
     * Constructor CardSteps.
     *
     * @param board SelectedDashBoard.
     */
    public CardSteps(final SelectedBoard board) {
        this.board = board;
    }

    /**
     * Creation of a card with a specs.
     *
     * @param data input String.
     */
    @Given("I create a card with a:")
    public void iCreateACardWith(final String data) {
        board.clickAddCard(data);
    }

    /**
     * Creation of list with a specs.
     *
     * @param value input String.
     */
    @When("I create another lists:")
    public void iCreateAnotherLists(final String value) {
        board.addList(value);
    }

    /**
     * Method verify card new.
     *
     * @param expected input String.
     */
    @Then("I should see the card {string}")
    public void iShouldSeeTheCard(final String expected) {
        assertEquals(card.getValue(), expected);
    }

    /**
     * Method for open card new created.
     *
     * @param dataTable Input dataTable.
     */
    @When("I select the card:")
    public void iSelectTheCard(final Map<String, String> dataTable) {
        card = board.selectCard(dataTable.get("List Name"), dataTable.get("Card Name"));
    }

    /**
     * Stepdef for archive the card.
     */
    @When("I archive the card")
    public void iArchiveTheCard() {
        card.archiveCard();
    }

    /**
     * Stepdef for delete the card.
     */
    @And("I delete the card")
    public void iDeleteTheCard() {
        card.deleteCard();
    }

    /**
     * Stepdef for verify if the card was deleted.
     */
    @Then("I expect the card doesn't appear")
    public void iExpectTheCardDoesnTAppear() {
        // WIP
    }

    /**
     * Stepdef for assign a member to the card.
     *
     * @param data Input dataTable.
     */
    @When("I assign a member to the card:")
    public void iAssignAMemberToTheCard(final Map<String, String> data) {
        String accountMember = Commons.getUserFromKey(data.get(member));
        String accountKey = String.format(readJsonUsername, accountMember);
        card.assignMemberToCard(ENVIRONMENT.getValue(accountKey));
    }

    /**
     * Stepdef for verify if a member was invited to te card.
     *
     * @param data Input dataTable.
     */
    @Then("I see the member in the card")
    public void iSeeTheMemberInTheCard(final Map<String, String> data) {
        // WIP
    }

    /**
     * Method for delete a member in the card.
     *
     * @param data Input dataTable.
     */
    @When("I remove from card to user:")
    public void iRemoveFromCardToUser(final Map<String, String> data) {
        String accountMember = Commons.getUserFromKey(data.get(member));
        String accountKey = String.format(readJsonUsername, accountMember);
        card.removeMember(ENVIRONMENT.getValue(accountKey));
    }

    /**
     * Method for verify if the member to be delete.
     *
     * @param data Input dataTable.
     */
    @Then("I should see the card without the user:")
    public void iShouldSeeTheCardWithoutTheUser(final Map<String, String> data) {
        String accountMember = Commons.getUserFromKey(data.get(member));
        String accountKey = String.format(readJsonUsername, accountMember);
        assertFalse(card.verifyMemberExist(ENVIRONMENT.getValue(accountKey)));
    }

    /**
     * Method for change label.
     *
     * @param data Input dataTable.
     */
    @When("I change label to:")
    public void iChangeLabelTo(final Map<String, String> data) {
        card.changeLabel(data);
    }

    /**
     * Method for verify change label.
     *
     * @param color String type.
     */
    @Then("I should see the card with label {string}")
    public void iShouldSeeTheCardWithLabel(final String color) {
        assertTrue(card.getverifyLabelExist(color));
    }
}
