package com.example.posapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Product extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4;
    Spinner spinner, spinner1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayList<String> titles1 = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    ArrayAdapter arrayAdapter1;
    Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ed1 = findViewById(R.id.product);
        ed2 = findViewById(R.id.productdes);
        spinner = findViewById(R.id.catid);
        spinner1 = findViewById(R.id.brandid);
        ed3 = findViewById(R.id.qty);
        ed4 = findViewById(R.id.price);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Product.this, MainActivity.class );
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insert();

            }
        });


        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

        final Cursor c = db.rawQuery("select * from category", null);
        int category = c.getColumnIndex("category");


        titles.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        spinner.setAdapter(arrayAdapter);

        final ArrayList<Cate> cate = new ArrayList<Cate>();

        if(c.moveToFirst()){
            do{
                Cate ca = new Cate();
                ca.category = c.getString(category);
                cate.add(ca);

                titles.add(c.getString(category));


            }while(c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
        }

        final Cursor b = db.rawQuery("select * from brand", null);
        int brand = b.getColumnIndex("brand");


        titles1.clear();

        arrayAdapter1 = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles1);
        spinner1.setAdapter(arrayAdapter1);

        final ArrayList<Brandc> bran = new ArrayList<Brandc>();

        if(b.moveToFirst()){
            do{
                Brandc ba = new Brandc();
                ba.brand = b.getString(brand);
                bran.add(ba);

                titles1.add(b.getString(brand));


            }while(b.moveToNext());

            arrayAdapter1.notifyDataSetChanged();
        }




    }

    public void insert(){

        try{
            String product = ed1.getText().toString();
            String productdescription = ed2.getText().toString();
            String category = spinner.getSelectedItem().toString();
            String brand = spinner1.getSelectedItem().toString();
            String qty = ed3.getText().toString();
            String price = ed4.getText().toString();


            SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS product(id INTEGER PRIMARY KEY AUTOINCREMENT, product VARCHAR, productdes VARCHAR,category VARCHAR, brand VARCHAR,qty VARCHAR,price VARCHAR)");

            String sql = "insert into product (product,productdes,category,brand,qty,price) values(?,?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1,product);
            statement.bindString(2,productdescription);
            statement.bindString(3,category);
            statement.bindString(4,brand);
            statement.bindString(5,qty);
            statement.bindString(6,price);


            statement.execute();
            Toast.makeText(this,"Product Created", Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed1.requestFocus();



        }
        catch(Exception e){
            Toast.makeText(this,"Product Failed", Toast.LENGTH_LONG).show();

        }


    }

















}