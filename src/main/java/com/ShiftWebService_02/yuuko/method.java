package com.ShiftWebService_02.yuuko;

import java.text.DateFormat;

public class method {

    protected static boolean isMonthAndDay(int year, int month, int day) {
        //intがたをString型に変換し、2000-mm-dd型に変換する
        String date = String.join("-",
                Integer.toString(year),
                Integer.toString(month),
                Integer.toString(day));
        if (date == null || date.length() != 10) {
            System.out.println("不正な日付が入力されています");
            return false;
        }
        DateFormat df = DateFormat.getDateInstance();
        // falseにすることで存在しない日付を指定された場合、Exception を発生させる
        df.setLenient(false);
        try {
            df.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
