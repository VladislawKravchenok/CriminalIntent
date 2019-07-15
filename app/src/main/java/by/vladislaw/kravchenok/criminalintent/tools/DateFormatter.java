package by.vladislaw.kravchenok.criminalintent.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Vladislaw Kravchenok on 26.06.2019.
 */
public class DateFormatter {

    private static final String TIME_FORMAT = "EEEE, MMM d, yyyy";

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(format(date));
    }

    public static String format(Date date) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        return dateFormat.format(date);
    }
}

