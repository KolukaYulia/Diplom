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

public class CreditPaymentTest {
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
    void positiveCreditScriptValidCardApproved() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getValidApprovedCard());
        payment.waitApprovedMessage();
        assertEquals("APPROVED", SQLHelper.getCreditRequestStatus());
    }
    @Test
    void positiveCreditScriptValidCardDeclined() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getValidDeclinedCard());
        payment.waitFailureMessage();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }

    @Test
    void buyCreditNegativeNumberCard15Symbols() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getNumberCard15Figures());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyCreditNegativeCardNotInDatabase() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getCardNotInDatabase());
        payment.waitFailureMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyCreditNegativeNoCardNumber() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getNoCardNumber());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyCreditNegativeMonthPastPeriod() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getCardPastPeriod());
        payment.waitExpirationDateErrorMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyCreditNegativeMonthMonthOver12() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getCardMonthOver12());
        payment.waitExpirationDateErrorMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyCreditNegativeCardYearOverThisYearOn6() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getCardYearOverThisYearOn6());
        payment.waitExpirationDateErrorMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyCreditNegativeCardPeriodNoMonth() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getCardNoMonth());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyCreditNegativeCardPeriodNoYear() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getCardNoYear());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }

    @Test
    void buyCreditNegativeCardCVVTwoFigures() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getCardCvv2Symbol());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyCreditNegativeCardNoCVV() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getCardWithNoCvv());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyCreditNegativeCardHolder1Letter() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getCardHolder1Letter());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyCreditNegativeCardHolderCirillic() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getCardHolderCirillic());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyCreditNegativeCardHolderWithFigures() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getCardHolderWithFigures());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyCreditNegativeCardHolderWithSymbols() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getCardHolderWithSymbols());
        payment.waitWrongFormatMessage();
        assertEquals("0", SQLHelper.getOrderCount());
    }
    @Test
    void buyCreditNegativeCardNoHolder() {
        val startPage = new MainPage();
        val payment = startPage.goToCreditPaymentForm();
        payment.inputData(DataHelper.getCardWithNoHolder());
        payment.waitRequiredFieldError();
        assertEquals("0", SQLHelper.getOrderCount());
    }

}
