package ru.netology.test;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardPaymentTest {
    //public static String url = System.getProperty("app.url");

    @BeforeEach
    public void openPage() {
        open("http://localhost:8080/");
    }

    @AfterEach
    public void cleanBase() {
        SQLHelper.clearDB();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void PositiveScriptValidCardApproved() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getValidApprovedCard());
        payment.waitApprovedMessage();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }
}
