package org.fundacionjala.trello.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.fundacionjala.trello.pages.board.MemberMenu;

/**
 * Step definitions for the Member Menu.
 */
public class MemberMenuSteps {
    private MemberMenu memberMenu;

    /**
     * Contructor of MemberMenuSteps.
     * @param memberMenu menu Menber.
     */
    public MemberMenuSteps(final MemberMenu memberMenu) {
        this.memberMenu = memberMenu;
    }
    /**
     * Step to go open header member menu.
     */
    @And("I go to member menu")
    public void iGoToMemberMenu() {
        memberMenu.goMemberMenu();
    }

    /**
     * Step to change language of the account.
     * @param language String type.
     */
    @And("I change language to {string}")
    public void iChangeLanguageTo(final String language) {
        memberMenu.changeLocate(language);
    }

    /**
     * Step to verify the language change.
     * @param language String type.
     */
    @Then("I should see the page in {string}")
    public void iShouldSeeThePageIn(final String language) {
        //WIP
    }
}