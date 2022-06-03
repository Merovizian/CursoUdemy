package com.ifes.ericgiobini.classesemetodosnapratica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ifes.ericgiobini.classesemetodosnapratica.Classes.Animal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Obama obama = new Obama();
        Eric eric = new Eric();
        obama.direitosDeveres();
        obama.ganharEleicao();
        eric.ganharEleicao();




    }

}
