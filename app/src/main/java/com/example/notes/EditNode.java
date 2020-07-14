package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;

import static com.example.notes.MainActivity.arrayAdapter;
import static com.example.notes.MainActivity.arrayList;

public class EditNode extends AppCompatActivity {
    EditText editText;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_node);
        editText = findViewById(R.id.editText);
        position=getIntent().getExtras().getInt("pos");

        if(position==-1)
        {
            arrayList.add("");
            position=arrayList.size()-1;
        }
        editText.setText(arrayList.get(position));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayList.set(position,String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("NotesApp", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<String>(arrayList);
                sharedPreferences.edit().putStringSet("Notes",set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }
}
