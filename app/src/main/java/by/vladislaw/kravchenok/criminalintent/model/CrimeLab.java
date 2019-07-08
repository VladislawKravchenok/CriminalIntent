package by.vladislaw.kravchenok.criminalintent.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import by.vladislaw.kravchenok.criminalintent.database.CrimeBaseHelper;
import by.vladislaw.kravchenok.criminalintent.database.CrimeCursorWrapper;
import by.vladislaw.kravchenok.criminalintent.database.CrimeDbSchema.CrimeTable;

/**
 * Created by Vladislaw Kravchenok on 25.06.2019.
 */
public class CrimeLab {

    private static CrimeLab sCrimeLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public void addCrime(Crime c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeTable.NAME, null, values);
    }

    public void updateCrime(Crime c) {
        String uuidString = c.getId().toString();
        ContentValues values = getContentValues(c);
        mDatabase.update(CrimeTable.NAME,
                values,
                CrimeTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public void removeCrime(Crime c) {
        String uuidString = c.getId().toString();
        mDatabase.delete(CrimeTable.NAME, CrimeTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    private CrimeCursorWrapper queryCrime(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
    }


    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursor = queryCrime(CrimeTable.Cols.UUID + " = ?", new String[]{id.toString()});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public int getCrimeIndex(UUID id, List<Crime> crimes) {
        for (int i = 0; i < crimes.size(); i++) {
            if (crimes.get(i).getId().equals(id)) {
                return i;
            }
        }
        return 0;
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeTable.Cols.SUSPECT, crime.getSuspect());
        values.put(CrimeTable.Cols.NUMBER, crime.getNumber());
        return values;
    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrime(null, null);

        cursor.moveToFirst();
        try {
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return crimes;
    }
}
