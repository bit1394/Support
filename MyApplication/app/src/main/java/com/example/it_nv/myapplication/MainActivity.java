package com.example.it_nv.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;
    Button btnAdd, btnRead;
    EditText et1, et2, etID;
    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnRead = findViewById(R.id.btnRead);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        etID = findViewById(R.id.etID);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        dbHelper = new DBHelper(this);

    }

    public void onClick(View v) {
        String res1 = et1.getText().toString();
        String res2 = et2.getText().toString();
        String resID = etID.getText().toString();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        switch (v.getId()) {
            case R.id.btnAdd:
                cv.put(DBHelper.KEY_LOGIN, res1);
                cv.put(DBHelper.KEY_PASSWORD, res2);

                db.insert(DBHelper.TABLE_BASE, null, cv);
                tv1.setText(resID);
                break;
            case R.id.btnRead:
                int idD = Integer.parseInt(resID);

                Cursor c = db.query(DBHelper.TABLE_BASE, new String[] {DBHelper.KEY_LOGIN, DBHelper.KEY_PASSWORD}, null, null, null, null, null);
                c.moveToPosition(idD);

                tv1.setText(c.getString(c.getColumnIndex(DBHelper.KEY_LOGIN)));
                tv2.setText(c.getString(c.getColumnIndex(DBHelper.KEY_PASSWORD)));
                c.close();
                break;
            default:
                break;
        }
    }
}
