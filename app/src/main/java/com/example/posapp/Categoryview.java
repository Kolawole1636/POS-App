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

public class Categoryview extends AppCompatActivity {

    ListView lst1;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryview);

        lst1 = findViewById(R.id.lst1);
        SQLiteDatabase db = openOrCreateDatabase("superpos", Context.MODE_PRIVATE,null);

        final Cursor c = db.rawQuery("select * from category", null);
        int id = c.getColumnIndex("id");
        int category = c.getColumnIndex("category");
        int catdes = c.getColumnIndex("catdes");

        titles.clear();

        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);
        lst1.setAdapter(arrayAdapter);

        final ArrayList<Cate> cate = new ArrayList<Cate>();

        if(c.moveToFirst()){
            do{
                Cate ca = new Cate();
                ca.id = c.getString(id);
                ca.category = c.getString(category);
                ca.des = c.getString(catdes);
                cate.add(ca);

                titles.add(c.getString(id) + "  " + c.getString(category) + "  " + c.getString(catdes));


            }while(c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            lst1.invalidateViews();
        }

        lst1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                String aaa = titles.get(i).toString();
                Cate ca = cate.get((i));

                Intent intent = new Intent(getApplicationContext(), CatEdit.class);
                intent.putExtra("id", ca.id);
                intent.putExtra("category", ca.category);
                intent.putExtra("catdes", ca.des);
                startActivity(intent);
            }
        });






    }
}