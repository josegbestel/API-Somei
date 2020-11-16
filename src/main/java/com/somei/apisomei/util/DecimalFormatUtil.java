package com.somei.apisomei.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class DecimalFormatUtil {

    public static Float format(Float valor){
        DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        df.setMaximumFractionDigits(2);

        String number = df.format(valor);
        System.out.println("number1: " + number);
        number = number.replaceAll("\\.", "");
        number = number.replaceAll(",", "\\.");
        System.out.println("number2: " + number);
        return Float.parseFloat(number);
    }

    public static Double format(Double valor){
        DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        df.setMaximumFractionDigits(2);

        String number = df.format(valor);
//        System.out.println("number1: " + number);
        number = number.replaceAll("\\.", "");
        number = number.replaceAll(",", "\\.");
//        System.out.println("number2: " + number);
        return Double.parseDouble(number);
    }
}
