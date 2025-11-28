import org.example.pages.MainPage;
import org.example.tests.BaseTest;
import org.junit.Test;
import static org.junit.Assert.*;

public class FAQTest extends BaseTest {

    @Test
    public void testAccordionFunctionality() {
        MainPage mainPage = new MainPage(driver, this);
        mainPage.waitForLoad();

        for (int i = 0; i < 8; i++) {
            mainPage.clickQuestion(i);

            
            boolean isVisible = mainPage.isAnswerVisible(i);
            assertTrue(isVisible);

            String answerText = mainPage.getAnswerText(i);
            assertFalse(answerText.isEmpty());
        }
    }
}