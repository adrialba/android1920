package com.example.colores_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    //Atributos: widgets que interactuan
    LinearLayout ll;
    Button b_aceptar, b_salir;
    RadioGroup rg;
    int color = Color.WHITE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Gestionamos las referencias a los objetos del layout y ponemos los listener donde correspondan
        ll = findViewById(R.id.ll);
        b_aceptar = findViewById(R.id.b_aceptar);
        b_aceptar.setOnClickListener(this);
        b_salir = findViewById(R.id.b_salir);
        b_salir.setOnClickListener(this);
        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
        if (savedInstanceState!=null) {
            color = savedInstanceState.getInt("color");
            ll.setBackgroundColor(color);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case(R.id.b_aceptar):
                ll.setBackgroundColor(color);
                break;
            case(R.id.b_salir):
                finish();
        }
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case(R.id.rb0):
                color = Color.YELLOW;
                break;
            case (R.id.rb1):
                color = Color.BLUE;
                break;
            case (R.id.rb2):
                color = Color.LTGRAY;
                break;
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("color", color);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState!=null){
            color = savedInstanceState.getInt("color");
            ll.setBackgroundColor(color);
        }
    }
}
