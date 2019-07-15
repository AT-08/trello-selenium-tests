package org.fundacionjala.trello.cucumber.steps.ui;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fundacionjala.core.Environment;
import org.fundacionjala.core.ui.Commons;
import org.fundacionjala.core.ui.driver.SharedDriver;
import org.fundacionjala.trello.pages.team.SelectedTeam;
import org.fundacionjala.trello.pages.team.TabMembers;

import static org.testng.Assert.assertEquals;


/**
 * Class to run testing add list to an existing board.
 */
public class MemberSteps {

    private static final Environment ENVIRONMENT = Environment.getInstance();
    private SelectedTeam selectedTeam;
    private TabMembers tabMembers;

    /**
     * Class constructor.
     *
     * @param sharedDriver sharedDriver.
     * @param team type SelectedTeam.
     * @param tabMembers TabMembers.
     */
    public MemberSteps(final SharedDriver sharedDriver, final SelectedTeam team, final TabMembers tabMembers) {
        this.selectedTeam = team;
        this.tabMembers = tabMembers;
    }

    /**
     * Method to create a new list into board create above.
     */
    @Given("I select tab members")
    public void iSelectTabMembers() {
        selectedTeam.openTabMembers();
    }

    /**
     * Method to create a new list into board create above.
     *
     * @param member type String.
     */
    @When("I invite a member to the Team:")
    public void iInviteToMemberToTeam(final String member) {
        String accountKey = String.format("$['credentials']['%s']['username']", Commons.getUserFromKey(member));
        tabMembers.clickInviteButton(ENVIRONMENT.getValue(accountKey));
    }

    /**
     * Method to create a new list into board create above.
     *
     * @param member type String.
     */
    @Then("I should see to the member {string} in the tab members")
    public void iShouldSeeToTheMemberInTheTabMembers(final String member) {
        String accountKey = String.format("$['credentials']['%s']['username']", Commons.getUserFromKey(member));
        assertEquals(tabMembers.getMemberAccount(ENVIRONMENT.getValue(accountKey)),
                String.format("@%s", ENVIRONMENT.getValue(accountKey)));
    }
}
