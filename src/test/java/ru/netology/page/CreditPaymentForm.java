package ru.netology.page;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
public class CreditPaymentForm {
    private SelenideElement heading = $$("h3").find(exactText("Оплата по карте"));
    private SelenideElement cardNumberField = $(byText("Номер карты")).parent().$("[class=\"input__control\"]");
    private SelenideElement monthField = $(byText("Месяц")).parent().$("[class=\"input__control\"]");
    private SelenideElement yearField = $(byText("Год")).parent().$("[class=\"input__control\"]");
    private SelenideElement cardHolderField = $(byText("Владелец")).parent().$("[class=\"input__control\"]");
    private SelenideElement cvvField = $(byText("CVC/CVV")).parent().$("[class=\"input__control\"]");
    private SelenideElement approvedPayment = $(byText("Операция одобрена Банком.")).parent().$("[class=\"notification__content\"]");
    private SelenideElement failurePayment = $(byText("Ошибка! Банк отказал в проведении операции.")).parent().$("[class=\"notification__content\"]");
    private SelenideElement wrongFormatError = $(byText("Неверный формат"));
    private ElementsCollection wrongFormat4Error = $$(byText("Неверный формат"));
    private SelenideElement cardExpirationDateError = $(byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpiredError = $(byText("Истёк срок действия карты"));
    private SelenideElement requiredFieldError = $(byText("Поле обязательно для заполнения"));

    private SelenideElement cancelField = $$("[class=\"icon-button__text\"]").first();
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

    public CreditPaymentForm() {
        heading.shouldBe(visible);
    }

    public void inputData(DataHelper.Card card) {
        cardNumberField.setValue(card.getCardNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        cardHolderField.setValue(card.getCardHolder());
        cvvField.setValue(card.getCvv());
        continueButton.click();
    }

    public void waitApprovedMessage() {
        approvedPayment.shouldBe(visible);
        cancelField.click();
    }

    public void waitFailureMessage() {
        failurePayment.shouldBe(visible);
    }

    public void waitWrongFormatMessage() {
        wrongFormatError.shouldBe(visible);
    }

    public void waitExpirationDateErrorMessage() {
        cardExpirationDateError.shouldBe(visible);
    }

    public void waitExpiredErrorMessage() {
        cardExpiredError.shouldBe(visible);
    }

}
