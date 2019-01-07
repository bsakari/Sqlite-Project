package com.example.maureen.sqllite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

    EditText mName, mEmail, mId;
    Button mSave, mView, mSearch,mDelete;

    //DB Variable
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mName =(EditText) findViewById(R.id.name);
        mEmail= (EditText)findViewById(R.id.email);
        mId =(EditText) findViewById(R.id.id);
        mSave=(Button)findViewById(R.id.save);
        mView= (Button)findViewById(R.id.view);
        mSearch= (Button)findViewById(R.id.update);
        mDelete=(Button)findViewById(R.id.delete);
        //Method for creating a database(Create/View)

        db= openOrCreateDatabase("voterDB", MODE_PRIVATE,null);
        // Query to create a TABLE with three columns

        db.execSQL("CREATE TABLE IF NOT EXISTS voterreg(name VARCHAR, email VARCHAR, idNo VARCHAR )");

   //save data to DB



    }

    public void saving(View view) {
        //Check if user has filled in all the values

        if (mName.getText().toString().trim().length()==0){
           errorMessage("NAME ERROR ","Kindly fill in your name ") ;
        }else if (mEmail.getText().toString().length()==0) {
            errorMessage("EMAIL ERROr", "Please enter your email Address");
        }else if (mId.getText().toString().length()==0){
            errorMessage("ID ERROR","Kindly enter your ID");
        }

        else {
            //insert data to DB
            db.execSQL("INSERT INTO voterreg VALUES('"+mName.getText()+"','"+mEmail.getText()+"','"+mId.getText()+"')");
            errorMessage("QUERY SUCCESS","Data was succefully saves");
            clear();
        }

    }
//clear the editext after saving
    private void clear() {
        mName.setText("");
        mId.setText("");
        mEmail.setText("");
    }
    //Erroe message display

    private void errorMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
// Query nad view record inthe DB
    public void viewing(View view) {
        Cursor cursor = db.rawQuery("SELECT * FROM voterreg",null);

        //check if there is any records
        if (cursor.getCount()==0){
            errorMessage("NO RECORDS ","Sorry, No records found");
        }
        //use Buffer to append the records
        StringBuffer buffer = new StringBuffer();

        while (cursor.moveToNext()){
            buffer.append("Name is:"+cursor.getString(0));
            buffer.append("\n");
            buffer.append("Email is:"+cursor.getString(1));
            buffer.append("\n");
            buffer.append("ID is:"+cursor.getString(2));
            buffer.append("\n");
        }
        errorMessage("DATABASE RECORDS ", buffer.toString());
    }

    public void Delete(View view) {

        Intent intent = new Intent(getApplicationContext(),DeleteActivity.class);
        startActivity(intent);

    }
}
