package com.example.posapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CatEdit extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    Button b1,b2,b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_edit);

        ed1 = findViewById(R.id.catid);
        ed2 = findViewById(R.id.category);
        ed3 = findViewById(R.id.categorydes);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);

        Intent i = getIntent();
        String id = i.getStringExtra("id").toString();
        String category = i.getStringExtra("category").toString();
        String des = i.getStringExtra("catdes").toString();

        ed1.setText(id);
        ed2.setText(category);
        ed3.setText(des);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                delete();

            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(CatEdit.this, MainActivity.class );
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edit();
            }
        });


    }

    public void edit()
    {
        try{
            String catid = ed1.getText().toString();
            String categoryname = ed2.getText().toString();
            String catdescription = ed3.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

            String sql = "update category set category =?, catdes =? where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,categoryname);
            statement.bindString(2,catdescription);
            statement.bindString(3,catid);
            statement.execute();
            Toast.makeText(this,"Category Updated", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);



        }
        catch(Exception e){
            Toast.makeText(this,"Category Failed", Toast.LENGTH_LONG).show();
        }

    }

    public void delete(){

        try{
            String catid = ed1.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

            String sql = "delete from category where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,catid);
            statement.execute();
            Toast.makeText(this,"Category Deleted", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);



        }
        catch(Exception e){
            Toast.makeText(this,"Category Failed", Toast.LENGTH_LONG).show();
        }

    }






}