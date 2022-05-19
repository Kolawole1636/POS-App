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
import android.widget.Spinner;
import android.widget.Toast;

public class Productedit extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5;
    Spinner spinner, spinner1;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productedit);

        ed1 = findViewById(R.id.productid);
        ed2 = findViewById(R.id.product);
        ed3 = findViewById(R.id.productdes);
        spinner = findViewById(R.id.catid);
        spinner1 = findViewById(R.id.brandid);
        ed4 = findViewById(R.id.qty);
        ed5 = findViewById(R.id.price);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b3 = findViewById(R.id.btn3);


        Intent i = getIntent();
        String id = i.getStringExtra("id").toString();
        String product = i.getStringExtra("product").toString();
        String des = i.getStringExtra("productdes").toString();
        String category = i.getStringExtra("category").toString();
        String brand = i.getStringExtra("brand").toString();
        String qty = i.getStringExtra("qty").toString();
        String price = i.getStringExtra("price").toString();

        ed1.setText(id);
        ed2.setText(product);
        ed3.setText(des);
        ed4.setText(qty);
        ed5.setText(price);




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edit();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });





        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Productedit.this, MainActivity.class );
                startActivity(i);

            }
        });


    }

    public void edit(){

        try{
            String productid = ed1.getText().toString();
            String product = ed2.getText().toString();
            String productdescription = ed3.getText().toString();
            String category = spinner.getSelectedItem().toString();
            String brand = spinner1.getSelectedItem().toString();
            String qty = ed4.getText().toString();
            String price = ed5.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

            String sql = "update product set product=?,productdes=?,category=?,brand=?,qty=?,price=? where id =?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,product);
            statement.bindString(2,productdescription);
            statement.bindString(3,category);
            statement.bindString(4,brand);
            statement.bindString(5,qty);
            statement.bindString(6,price);
            statement.bindString(7,productid);


            statement.execute();
            Toast.makeText(this,"Product Updated", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);



        }
        catch(Exception e){
            Toast.makeText(this,"Product Failed", Toast.LENGTH_LONG).show();

        }


    }

    public void delete(){

        try{
            String productid = ed1.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

            String sql = "delete from product where id = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,productid);
            statement.execute();
            Toast.makeText(this,"Product Deleted", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);



        }
        catch(Exception e){
            Toast.makeText(this,"Product Failed", Toast.LENGTH_LONG).show();
        }

    }








}