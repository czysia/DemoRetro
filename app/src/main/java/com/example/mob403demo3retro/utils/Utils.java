package com.example.mob403demo3retro.utils;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Utils {

    public String convertMoney(int money) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(0);
        format.setCurrency(Currency.getInstance(localeVN));

        return format.format(money);
    }
}
