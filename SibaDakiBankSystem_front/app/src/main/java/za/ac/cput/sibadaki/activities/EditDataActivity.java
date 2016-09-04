package za.ac.cput.sibadaki.activities;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import za.ac.cput.sibadaki.adapters.SQLiteHelper;
import za.ac.cput.sibadaki.sibadakibanksystem.R;

/**
 * Created by User on 2016/09/03.
 */
public class EditDataActivity extends Activity {

    Button nextbutton, previousbutton, updatebutton, deletebutton;

    EditText id, name, surname;

    SQLiteDatabase SQLITEDATABASE, SQLITEDATABASE2 ;

    String GetSQliteQuery, UpdateRecordQuery, DeleteQuery ;

    Cursor cursor, cursorCheckDataIsEmptyOrNot ;

    TextView idtextview;

    Boolean CheckEditTextEmpty;

    String GetID,GetName,GetSurname ;

    int UserID ;

    String ConvertUserID ;

    SQLiteHelper SQLITEHELPER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        nextbutton = (Button)findViewById(R.id.button1);
        previousbutton = (Button)findViewById(R.id.button2);
        updatebutton = (Button)findViewById(R.id.button3);
        deletebutton = (Button)findViewById(R.id.button4);

        id = (EditText)findViewById(R.id.editText1);
        name = (EditText)findViewById(R.id.editText2);
        surname = (EditText)findViewById(R.id.editText3);

        idtextview = (TextView)findViewById(R.id.textview1);

        GetSQliteQuery = "SELECT * FROM customerTable" ;

        SQLITEDATABASE = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);

        cursor = SQLITEDATABASE.rawQuery(GetSQliteQuery, null);

        cursor.moveToFirst();

        GetSQLiteDatabaseRecords();


        nextbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!cursor.isLast()){

                    cursor.moveToNext();
                }

                GetSQLiteDatabaseRecords();

            }
        });

        previousbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!cursor.isFirst()){

                    cursor.moveToPrevious();
                }
                GetSQLiteDatabaseRecords();

            }
        });

        updatebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                //GetID = id.getText().toString();
                GetName = name.getText().toString();
                GetSurname = surname.getText().toString();

                ConvertUserID = idtextview.getText().toString();

                UserID = Integer.parseInt(ConvertUserID);

                UpdateRecordQuery = "UPDATE customerTable SET name='" + GetID + "', name='" + GetName + "', surname='" + GetSurname + "' WHERE id=" + UserID + ";";

                CheckEditTextIsEmptyOrNot( GetName,GetID, GetName);

                if (CheckEditTextEmpty == false) {

                    SQLITEDATABASE.execSQL(UpdateRecordQuery);

                    cursor = SQLITEDATABASE.rawQuery(GetSQliteQuery, null);

                    cursor.moveToPosition(UserID);

                    Toast.makeText(EditDataActivity.this,"Data Updated Successfully", Toast.LENGTH_LONG).show();

                }else {

                    Toast.makeText(EditDataActivity.this,"Please Fill All the Fields", Toast.LENGTH_LONG).show();

                }


            }
        });

        deletebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ConvertUserID = idtextview.getText().toString();

                UserID = Integer.parseInt(ConvertUserID);

                DeleteQuery = "DELETE FROM demoTable WHERE id=" + UserID + ";";

                SQLITEDATABASE.execSQL(DeleteQuery);

                Toast.makeText(EditDataActivity.this, "Record Deleted Successfully", Toast.LENGTH_LONG).show();

                cursor = SQLITEDATABASE.rawQuery(GetSQliteQuery, null);

            }
        });
    }

    public void GetSQLiteDatabaseRecords(){

        idtextview.setText(cursor.getString(0));

        name.setText(cursor.getString(1).toString());

        id.setText(cursor.getString(2).toString());

        surname.setText(cursor.getString(3).toString());
    }

    public void CheckEditTextIsEmptyOrNot(String Name,String PhoneNumber, String subject ){

        if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(PhoneNumber) || TextUtils.isEmpty(subject)){

            CheckEditTextEmpty = true ;

        }
        else {
            CheckEditTextEmpty = false ;
        }
    }

}
