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
    void positiveScriptValidCardApproved() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getValidApprovedCard());
        payment.waitApprovedMessage();
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }
    @Test
    void positiveScriptValidCardDeclined() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getValidDeclinedCard());
        payment.waitFailureMessage();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }
    @Test
    void buyNegativeNumberCard15Symbols() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getNumberCard15Figures());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void buyNegativeCardNotInDatabase() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getCardNotInDatabase());
        payment.waitFailureMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyNegativeNoCardNumber() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getNoCardNumber());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyNegativeMonthPastPeriod() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getCardPastPeriod());
        payment.waitExpirationDateErrorMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyNegativeMonthMonthOver12() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getCardMonthOver12());
        payment.waitExpirationDateErrorMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyNegativeCardYearOverThisYearOn6() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getCardYearOverThisYearOn6());
        payment.waitExpirationDateErrorMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyNegativeCardPeriodNoMonth() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getCardNoMonth());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyNegativeCardPeriodNoYear() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getCardNoYear());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void buyNegativeCardCVVTwoFigures() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getCardCvv2Symbol());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyNegativeCardNoCVV() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getCardWithNoCvv());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyNegativeCardHolder1Letter() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getCardHolder1Letter());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyNegativeCardHolderCirillic() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getCardHolderCirillic());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyNegativeCardHolderWithFigures() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getCardHolderWithFigures());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyNegativeCardHolderWithSymbols() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getCardHolderWithSymbols());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyNegativeCardNoHolder() {
        val startPage = new MainPage();
        val payment = startPage.goToCardPaymentForm();
        payment.inputData(DataHelper.getCardWithNoHolder());
        payment.waitRequiredFieldError();
        assertEquals("0", SQLHelper.getOrderCount());
    }





}
