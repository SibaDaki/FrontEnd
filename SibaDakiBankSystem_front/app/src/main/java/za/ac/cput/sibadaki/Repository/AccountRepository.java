package za.ac.cput.sibadaki.Repository;

import android.database.sqlite.SQLiteDatabase;

import za.ac.cput.sibadaki.Domain.Account;

/**
 * Created by User on 2016/09/03.
 */
public interface AccountRepository extends Repository<Account, Long>
        {
            void onCreate(SQLiteDatabase db);

            void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);
        }
