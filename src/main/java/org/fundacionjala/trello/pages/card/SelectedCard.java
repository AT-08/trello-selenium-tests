package org.fundacionjala.trello.pages.card;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Map;

/**
 * this class represent a selected card page.
 */
public class SelectedCard extends AbstractPage {

    @FindBy(css = ".js-archive-card")
    private WebElement archiveCardButton;

    @FindBy(css = ".js-delete-card")
    private WebElement deleteCardButton;

    @FindBy(css = ".js-confirm.full.negate")
    private WebElement confirmDeleteCardButton;

    @FindBy(css = ".js-search-mem.js-autofocus")
    private WebElement searchMemberInputText;

    @FindBy(css = ".js-change-card-members")
    private WebElement membersButton;

    @FindBy(css = ".js-remove-member")
    private WebElement removeFromCardButton;

    @FindBy(css = ".js-edit-labels")
    private WebElement labelsCardButton;

    @FindBy(css = ".pop-over-header-close-btn")
    private WebElement closePanelLabel;

    @FindBy(css = ".js-move-card")
    private WebElement moveCardButton;

    @FindBy(css = ".js-close-window")
    private WebElement closeWindowsCardbutton;

    private By cardName = By.cssSelector(".js-card-name");


    /**
     * this method return value card.
     *
     * @return type String.
     */
    public String getValue() {
        return action.getValue(cardName);
    }

    /**
     * Method for archive the card.
     */
    public void archiveCard() {
        action.click(archiveCardButton);
    }

    /**
     * Method for delete card.
     */
    public void deleteCard() {
        action.click(deleteCardButton);
        action.click(confirmDeleteCardButton);
    }

    /**
     * Method for delete card.
     */
    public void closeWindowsCardbutton() {
        action.click(closeWindowsCardbutton);
    }

    /**
     * Method for assing member to a card.
     *
     * @param memberName Input string of member name.
     */
    public void assignMemberToCard(final String memberName) {
        action.click(membersButton);
        action.setValue(searchMemberInputText, memberName);
        By member = By.xpath(String.format("//*[contains(text(),'%s')]/ancestor::a", memberName));
        action.click(driver.findElement(member));
    }

    /**
     * Method for remove a member to a card.
     *
     * @param memberName Input string of member name.
     */
    public void removeMember(final String memberName) {
        By member = By.xpath(String
                .format("//*[contains(@class,'js-card-detail-members')]//*[contains(@title,'%s')]", memberName));
        action.click(driver.findElement(member));
        action.click(removeFromCardButton);
    }

    /**
     * Method for verify if member exist in the card.
     *
     * @param memberName Input string of member name.
     * @return boolean type.
     */
    public boolean verifyMemberExist(final String memberName) {
        By member = By.xpath(String
                .format("//*[contains(@class,'js-card-detail-members')]//*[contains(@title,'%s')]", memberName));
        return action.isExistingSelector(member);
    }

    /**
     * Method for label change to a card.
     *
     * @param data Input Map of color.
     */
    public void changeLabel(final Map<String, String> data) {
        action.click(labelsCardButton);
        By labelColorButton = By.xpath(String
                .format("//*[contains(@class,'od-selectable card-label-%s')]", data.get("Color")));
        action.click(driver.findElement(labelColorButton));
        action.click(closePanelLabel);
    }

    /**
     * Method for verify if new label exist in the card.
     *
     * @param color Input string of label.
     * @return boolean type.
     */
    public boolean getverifyLabelExist(final String color) {
        By element = By.cssSelector(String.format(".card-label-%s", color));
        return action.isExistingSelector(element);
    }


    /**
     * Method to change list to another board.
     *
     * @param card type String board destination.
     */
    public void moveCard(final String card) {
        action.click(moveCardButton);
        Select dropdown = new Select(driver.findElement(By.cssSelector("select.js-select-list")));
        dropdown.selectByVisibleText(card);
        By moveButton = By.xpath("//*[@class='primary wide js-submit']");
        action.waitVisibility(driver.findElement(moveButton));
        action.click(driver.findElement(moveButton));
    }

    /**
     * This Method verify to move card.
     *
     * @param listName list name.
     * @param cardName card name.
     * @return true or false
     */

    public boolean verifyMove(final String listName, final String cardName) {
        final String ancestor = "ancestor::div[contains(@class, 'js-list-content')]";
        final String descendent = "descendant::span[contains(text()";
        By element = By.xpath(String.format("//textarea[@aria-label='%s']/%s/%s, '%s')]",
                listName, ancestor, descendent, cardName));
        action.waitVisibility(driver.findElement(element));
        return action.isExistingSelector(element);


    }
}
