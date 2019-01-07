package com.example.maureen.sqllite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DeleteActivity extends AppCompatActivity {
EditText idInput;
Button deletebtn;
SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

   idInput= (EditText)findViewById(R.id.txtdelete);
   deletebtn =(Button)findViewById(R.id.BtnDelete);

   db= openOrCreateDatabase("voterDB",MODE_PRIVATE,null);

    }

    public void Dele(View view) {


        if (idInput.getText().toString().trim().length()==0){
            errorMessage("Empty ID","Kindly type in an ID..");
        }
        Cursor cursor = db.rawQuery("SELECT * FROM voterreg WHERE idNo = '"+idInput.getText()+"'", null);
        if (cursor.moveToFirst()){
            db.execSQL("DELETE FROM voterreg WHERE idNo = '"+idInput.getText()+"'");

            errorMessage("DELETED","Record Deleted Succesfully");
            clear();

        }
    }

    private void clear() {
        idInput.setText("");
    }

    private void errorMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}
