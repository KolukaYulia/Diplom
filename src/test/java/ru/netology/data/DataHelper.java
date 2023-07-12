package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;



public class DataHelper {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Card {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvv;
    }
    public static Card getApprovedCard() {
        return new Card("4444444444444441", "12", "23", "Ivan Ivanov", "123");
    }

    public static Card getDeclinedCard() {
        return new Card("4444444444444442", "12", "23", "Iva Ivanov", "123");
    }

    public static Card getEmptyCard() {
        return new Card("", "", "", "", "");
    }

    public static String getMonth(){
        int shift = (int) (Math.random() * 10);
        return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getYear(int yearCount){
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("YY"));
    }

    public Card getNumberCard15Figures() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear(1);
        String cvv = faker.number().digits(3);
        String number = faker.number().digits(15);
        return new Card(number, month, year, holder, cvv);
    }
    public Card getNumberCard17Figures() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear(1);
        String cvv = faker.number().digits(3);
        String number = faker.number().digits(17);
        return new Card(number, month, year, holder, cvv);
    }

    public Card getCardNotInDatabase() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444444", month, year, holder, cvv);
    }

    public Card getCardWithSimbol() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear(1);
        String cvv = faker.number().digits(3);
        return new Card("№444444444444444", month, year, holder, cvv);
    }
    public Card getCardWithLetter() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear(1);
        String cvv = faker.number().digits(3);
        return new Card("a444444444444444", month, year, holder, cvv);
    }
    public Card getNoCardNumber() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear(1);
        String cvv = faker.number().digits(3);
        return new Card("", month, year, holder, cvv);
    }

    public Card getCardPastPeriod() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", "06", "23", holder, cvv);
    }

    public Card getCardMonthOver12() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", "13", year, holder, cvv);
    }

    public Card getCardYearOverThisYearOn6() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear(6);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvv);
    }
    public Card getCardNoPeriod() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", "", "", holder, cvv);
    }

    public Card getCardCvv2Symbol() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear(1);
        String cvv = faker.number().digits(2);
        return new Card("4444444444444441", month, year, holder, cvv);
    }

    public Card getCardCvv4Symbols() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear(1);
        String cvv = faker.number().digits(4);
        return new Card("4444444444444441", month, year, holder, cvv);
    }
    public Card getCardCvvWithLetter() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear(1);
        return new Card("4444444444444441", month, year, holder, "12a");
    }
    public Card getCardWithNoCvv() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear(1);
        return new Card("4444444444444441", month, year, holder, "");
    }

    public Card getCardHolder1Letter() {
        Faker faker = new Faker();
        String month = getMonth();
        String year = getYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "a", cvv);
    }
    public Card getCardHolder2Letters() {
        Faker faker = new Faker();
        String month = getMonth();
        String year = getYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "a b", cvv);
    }

    public Card getCardHolderCirillic() {
        Faker faker = new Faker(new Locale("ru"));
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getMonth();
        String year = getYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvv);
    }

    public Card getCardHolderWithFigures() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.number().digit();
        String month = getMonth();
        String year = getYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvv);
    }

    public Card getCardHolderWithSymbols() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " %$ * &";
        String month = getMonth();
        String year = getYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, holder, cvv);
    }
    public Card getCardWithNoHolder() {
        Faker faker = new Faker();
        String month = getMonth();
        String year = getYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "", cvv);
    }
}

