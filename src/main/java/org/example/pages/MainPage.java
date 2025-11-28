package org.example.pages;

import org.example.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;
    private final BaseTest baseTest;

    //аккордеон вопросы о важном
    private final By[] questions = {
            By.id("accordion__heading-0"),  //сколько это стоит?
            By.id("accordion__heading-1"),  //хочу сразу несколько самокатов
            By.id("accordion__heading-2"),  //как рассчитывается время аренды?
            By.id("accordion__heading-3"),  //можно ли заказать самокат прямо на сегодня?
            By.id("accordion__heading-4"),  //можно ли продлить заказ?
            By.id("accordion__heading-5"),  //вы привозите зарядку?
            By.id("accordion__heading-6"),  //можно ли отменить заказ?
            By.id("accordion__heading-7")   //я живу за мкадом, привезёте?
    };

    private final By[] answers = {
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7")
    };

    //кнопки заказа
    private final By orderButtonTop = By.xpath(".//button[text()='Заказать']");
    private final By orderButtonBottom = By.xpath(".//div[@class='Home_FinishButton__1_cWm']//button");


    public MainPage(WebDriver driver, BaseTest baseTest) {
        this.driver = driver;
        this.baseTest = baseTest;
    }

    public void waitForLoad() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(orderButtonTop));
    }

    //методы для работы с аккордеоном
    public void clickQuestion(int index) {
        WebElement question = driver.findElement(questions[index]);
        baseTest.scrollToElement(question);
        question.click();
    }

    public boolean isAnswerVisible(int index) {
        String hiddenAttr = driver.findElement(answers[index]).getAttribute("hidden");
        return hiddenAttr == null || !hiddenAttr.equals("true");
    }

    public String getAnswerText(int index) {
        return driver.findElement(answers[index]).getText();
    }

    //методы для кнопок заказа
    public void clickTopOrderButton() {
        WebElement button = driver.findElement(orderButtonTop);
        baseTest.scrollToElement(button);
        button.click();
    }

    public void clickBottomOrderButton() {
        WebElement button = driver.findElement(orderButtonBottom);
        baseTest.scrollToElement(button);
        button.click();
    }
}