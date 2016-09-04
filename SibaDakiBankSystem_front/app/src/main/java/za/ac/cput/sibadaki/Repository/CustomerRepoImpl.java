package za.ac.cput.sibadaki.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import za.ac.cput.sibadaki.Domain.Customer;
import za.ac.cput.sibadaki.config.DBConstants;


/**
 * Created by User on 2016/04/25.
 */
public abstract class CustomerRepoImpl extends SQLiteOpenHelper implements CustomerRepository {

    public static final String TABLE_NAME = "Customer";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "cust_id";
    public static final String COLUMN_NAME = "cust_name";
    public static final String COLUMN_LNAME = "cust_lname";
    //public static final String COLUMN_ADDRESS = "address";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_LNAME + " TEXT NOT NULL);";
    //+ COLUMN_ADDRESS + " TEXT NOT NULL );";


    public CustomerRepoImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }


    @Override
    public Customer findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_LNAME,},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Customer customer = new Customer.Builder()
                    //.custId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .surName(cursor.getString(cursor.getColumnIndex(COLUMN_LNAME)))
                            //.address(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)))
                    .build();

            return customer;
        } else {
            return null;
        }
    }

    @Override
    public Customer save(Customer entity) throws SQLException {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNo());
        values.put(COLUMN_NAME, entity.getName().toString());
        values.put(COLUMN_LNAME, entity.getSurName());
        // values.put(COLUMN_ADDRESS, entity.getAddress());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Customer insertedEntity = new Customer.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;

    }

    @Override
    public Customer update(Customer entity) throws SQLException {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getIdNo());
        values.put(COLUMN_NAME, entity.getName().toString());
        values.put(COLUMN_LNAME, entity.getSurName());
        //values.put(COLUMN_ADDRESS, entity.getAddress());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getIdNo())}
        );
        return entity;
    }

    @Override
    public Customer delete(Customer entity) throws SQLException {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getIdNo())});
        return entity;
    }

    @Override
    public Set<Customer> findAll() throws SQLException {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Customer> customers = new HashSet();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                final Customer customer = new Customer.Builder()
                        //.idNo(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .surName(cursor.getString(cursor.getColumnIndex(COLUMN_LNAME)))
                                //.address(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)))
                        .build();
                customers.add(customer);
            } while (cursor.moveToNext());
        }
        return customers;
    }

    @Override
    public int deleteAll() throws SQLException {
        open();
        int rowsDeleted = db.delete(TABLE_NAME, null, null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}