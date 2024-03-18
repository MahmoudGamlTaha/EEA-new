package com.backend.EEA.common;

public class NumberToArabicText {
    private static final String[] ones = {"", "واحد", "اثنان", "ثلاثة", "أربعة", "خمسة", "ستة", "سبعة", "ثمانية", "تسعة"};
    private static final String[] tens = {"", "عشرة", "عشرون", "ثلاثون", "أربعون", "خمسون", "ستون", "سبعون", "ثمانون", "تسعون"};
    private static final String[] hundreds = {"", "مئة", "مئتان", "ثلاثمئة", "أربعمئة", "خمسمئة", "ستمئة", "سبعمئة", "ثمانمئة", "تسعمئة"};
    private static final String[] thousand = {"", "الف", "ألفان", "ثلاثة ألاف", "أربعة ألاف", "خمسة ألاف", "ستة ألاف", "سبعةألاف", "ثمانية ألاف", "تسعة ألاف"};

    public static String convertToArabicText(Double numberx) {
        int number = (int) numberx.intValue();
        if (number == 0) {
            return "صفر";
        }

        StringBuilder result = new StringBuilder();

        if (number < 0) {
            result.append("سالب ");
            number = -number;
        }

        if (number >= 1000000) {
            result.append(convertToArabicText(numberx / 1000000)).append(" مليون ");
            number %= 1000000;
        }

        if (number >= 1000) {
            result.append(convertToArabicText(numberx / 1000)).append(" ألف ");
            number %= 1000;
        }

        if (number >= 100) {
            result.append(hundreds[number / 100]).append(" و ");
            number %= 100;
        }

        if (number >= 10 && number <= 99) {
            result.append(tens[number / 10]).append(" ");
            number %= 10;
        }

        if (number > 0) {
            result.append(ones[number]).append(" و ");
        }

        return result.toString().trim();
    }
}