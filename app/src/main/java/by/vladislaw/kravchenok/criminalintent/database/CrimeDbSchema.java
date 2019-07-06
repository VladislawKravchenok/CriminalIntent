package by.vladislaw.kravchenok.criminalintent.database;

/**
 * Created by Vladislaw Kravchenok on 04.07.2019.
 */
public class CrimeDbSchema {
    public static final class CrimeTable{
        public static final String NAME = "crime";
        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
        }
    }
}
