package org.example.pages;

import org.example.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private final WebDriver driver;
    private final BaseTest baseTest;

    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By metroDropdownOption = By.xpath(".//div[@class='select-search__select']//button[1]");
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.xpath(".//div[text()='* Срок аренды']/..");
    private final By colorBlack = By.id("black");
    private final By colorGrey = By.id("grey");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//button[text()='Заказать' and @class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By confirmButton = By.xpath(".//button[text()='Да' and @class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By successMessage = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");

    public OrderPage(WebDriver driver, BaseTest baseTest) {
        this.driver = driver;
        this.baseTest = baseTest;
    }

    public void waitForLoadFirstPage() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(nameField));
    }

    public void waitForLoadSecondPage() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(dateField));
    }

    public void fillFirstPage(String name, String surname, String address, String phone) {
        fillField(nameField, name);
        fillField(surnameField, surname);
        fillField(addressField, address);

        WebElement metro = driver.findElement(metroField);
        baseTest.scrollToElement(metro);
        metro.click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(metroDropdownOption));
        driver.findElement(metroDropdownOption).click();

        fillField(phoneField, phone);

        WebElement next = driver.findElement(nextButton);
        baseTest.scrollToElement(next);
        next.click();
    }

    public void fillSecondPage(String date, String period, String color, String comment) {
        fillDate(date);
        selectRentalPeriod(period);

        if ("black".equals(color)) {
            clickWithScroll(colorBlack);
        } else if ("grey".equals(color)) {
            clickWithScroll(colorGrey);
        }

        fillField(commentField, comment);
        clickWithScroll(orderButton);
    }

    public void clickYesButton() {
        WebElement button = driver.findElement(confirmButton);
        button.click();
    }

    public boolean isSuccessMessageVisible() {
        return driver.findElement(successMessage).isDisplayed();
    }

    private void fillDate(String date) {
        WebElement dateInput = driver.findElement(dateField);
        baseTest.scrollToElement(dateInput);
        dateInput.click();
        dateInput.sendKeys(date);
        WebElement pageTitle = driver.findElement(By.xpath(".//div[contains(text(), 'Про аренду')]"));
        baseTest.scrollToElement(pageTitle);
        pageTitle.click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(rentalPeriodDropdown));
    }

    private void selectRentalPeriod(String period) {
        WebElement periodDropdown = driver.findElement(rentalPeriodDropdown);
        baseTest.scrollToElement(periodDropdown);
        periodDropdown.click();

        By periodOption = By.xpath(".//div[@class='Dropdown-option' and text()='" + period + "']");
        WebElement option = driver.findElement(periodOption);
        baseTest.scrollToElement(option);
        option.click();
    }

    private void fillField(By locator, String text) {
        WebElement field = driver.findElement(locator);
        baseTest.scrollToElement(field);
        field.sendKeys(text);
    }

    private void clickWithScroll(By locator) {
        WebElement element = driver.findElement(locator);
        baseTest.scrollToElement(element);
        element.click();
    }
}