import org.example.pages.MainPage;
import org.example.tests.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class FAQTest extends BaseTest {

    private final int questionIndex;
    private final String expectedAnswerText;

    public FAQTest(int questionIndex, String expectedAnswerText) {
        this.questionIndex = questionIndex;
        this.expectedAnswerText = expectedAnswerText;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getFAQData() {
        return Arrays.asList(new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат."},
                {2, "Допустим, вы оформляете заказ на 8 мая."},
                {3, "Только начиная с завтрашнего дня."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку"},
                {5, "Самокат приезжает к вам с полной зарядкой."},
                {6, "Да, пока самокат не привезли."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Test
    public void testAccordionQuestion() {
        MainPage mainPage = new MainPage(driver, this);
        mainPage.waitForLoad();

        mainPage.clickQuestion(questionIndex);

        boolean isVisible = mainPage.isAnswerVisible(questionIndex);
        assertTrue("Ответ должен быть видимым", isVisible);

        String actualAnswerText = mainPage.getAnswerText(questionIndex);
        assertTrue("Текст ответа должен содержать: " + expectedAnswerText,
                actualAnswerText.contains(expectedAnswerText));
    }
}