package com.example.posapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Productview extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productview);

        lst1 = findViewById(R.id.lst1);
        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

        final Cursor c = db.rawQuery("select * from product", null);
        int id = c.getColumnIndex("id");
        int product = c.getColumnIndex("product");
        int productdes = c.getColumnIndex("productdes");
        int category = c.getColumnIndex("category");
        int brand = c.getColumnIndex("brand");
        int qty = c.getColumnIndex("qty");
        int price = c.getColumnIndex("price");

        titles.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final ArrayList<Prod> prod = new ArrayList<Prod>();

        if(c.moveToFirst()){
            do{
                Prod pr = new Prod();
                pr.id = c.getString(id);
                pr.product = c.getString(product);
                pr.productdes = c.getString(productdes);
                pr.category = c.getString(category);
                pr.brand = c.getString(brand);
                pr.qty = c.getString(qty);
                pr.price = c.getString(price);
                prod.add(pr);

                titles.add(c.getString(id) + " " + c.getString(product) + "  " + c.getString(productdes) + "  " + c.getString(category) + "  " + c.getString(brand)+ "  " + c.getString(qty)+  "  " + c.getString(price));


            }while(c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }


        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                String aaa = titles.get(i).toString();
                Prod pr = prod.get((i));

                Intent intent = new Intent(getApplicationContext(), Productedit.class);
                intent.putExtra("id", pr.id);
                intent.putExtra("product", pr.product);
                intent.putExtra("productdes", pr.productdes);
                intent.putExtra("category", pr.category);
                intent.putExtra("brand", pr.brand);
                intent.putExtra("qty", pr.qty);
                intent.putExtra("price", pr.price);
                startActivity(intent);
            }
        });






















    }
}