
import org.example.pages.MainPage;
import org.example.pages.OrderPage;
import org.example.tests.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String date;
    private final String period;
    private final String color;
    private final String comment;
    private final String buttonType;

    public OrderTest(String name, String surname, String address, String phone,
                     String date, String period, String color, String comment, String buttonType) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.color = color;
        this.comment = comment;
        this.buttonType = buttonType;
    }

    @Parameterized.Parameters(name = "Тест заказа: {0} {1} через {8} кнопку")
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                //первый набор данных - через верхнюю кнопку
                {"Иван", "Петров", "Москва, ул. Ленина, 1", "+79123456789",
                        "15.12.2024", "сутки", "black", "Позвоните за час", "top"},

                //второй набор данных - через нижнюю кнопку
                {"Мария", "Сидорова", "Санкт-Петербург, Невский пр., 100", "+79987654321",
                        "20.12.2024", "двое суток", "grey", "Код домофона 123", "bottom"}
        });
    }

    @Test
    public void testOrderCreation() {
        MainPage mainPage = new MainPage(driver, this);
        mainPage.waitForLoad();

        //выбор точки входа в заказ
        if ("top".equals(buttonType)) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        OrderPage orderPage = new OrderPage(driver, this);
        orderPage.waitForLoadFirstPage();

        //заполнение первой страницы
        orderPage.fillFirstPage(name, surname, address, phone);

        //заполнение второй страницы
        orderPage.waitForLoadSecondPage();
        orderPage.fillSecondPage(date, period, color, comment);

        orderPage.clickYesButton();
        boolean isSuccess = orderPage.isSuccessMessageVisible();
        assertTrue("Должно появиться сообщение об оформлении заказа", isSuccess);
    }

}