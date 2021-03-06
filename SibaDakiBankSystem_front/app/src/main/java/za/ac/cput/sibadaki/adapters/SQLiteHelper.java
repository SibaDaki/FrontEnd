package za.ac.cput.sibadaki.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 2016/09/03.
 */
public class SQLiteHelper  extends SQLiteOpenHelper {

    static String DATABASE_NAME="DemoDataBase";

    public static final String KEY_ID="id";

    public static final String TABLE_NAME="customerTable";

    public static final String KEY_Name="name";

    public static final String KEY_Surname="phone_number";

  //  public static final String KEY_Subject="subject";

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_Name+" VARCHAR, "+KEY_Surname+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

}

