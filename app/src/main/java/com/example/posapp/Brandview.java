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

public class Brandview extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brandview);

        lst1 = findViewById(R.id.lst1);
        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

        final Cursor c = db.rawQuery("select * from brand", null);
        int id = c.getColumnIndex("id");
        int brand = c.getColumnIndex("brand");
        int branddes = c.getColumnIndex("branddes");

        titles.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final ArrayList<Brandc> brande = new ArrayList<Brandc>();

        if(c.moveToFirst()){
            do{
                Brandc br = new Brandc();
                br.id = c.getString(id);
                br.brand = c.getString(brand);
                br.des = c.getString(branddes);
                brande.add(br);

                titles.add(c.getString(id) + "  " + c.getString(brand) + "  " + c.getString(branddes));


            }while(c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                String aaa = titles.get(i).toString();
                Brandc br = brande.get((i));

                Intent intent = new Intent(getApplicationContext(), BrandEdit.class);
                intent.putExtra("id", br.id);
                intent.putExtra("brand", br.brand);
                intent.putExtra("branddes", br.des);
                startActivity(intent);
            }
        });










    }
}