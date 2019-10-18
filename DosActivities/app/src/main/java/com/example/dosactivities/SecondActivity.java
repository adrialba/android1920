package com.example.dosactivities;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class SecondActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    //Atributos: widgets que interactuan
    LinearLayout ll;
    Button b_aceptar, b_salir;
    RadioGroup rg;
    TextView tv_pregunta;
    Intent i;
    int color = Color.WHITE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        i = getIntent();
        String nombre = i.getStringExtra("nombre");

        iniciarLayout();
        tv_pregunta.setText(getResources().getString(R.string.pregunta_color, nombre.toUpperCase()));

        if (savedInstanceState!=null) {
            color = savedInstanceState.getInt("color");
            ll.setBackgroundColor(color);
        }
    }

    private void iniciarLayout() {
        //Gestionamos las referencias a los objetos del layout
        // y ponemos los listener donde correspondan
        ll = findViewById(R.id.ll);
        tv_pregunta = findViewById(R.id.textView);
        b_aceptar = findViewById(R.id.b_aceptar);
        b_aceptar.setOnClickListener(this);
        b_salir = findViewById(R.id.b_salir);
        b_salir.setOnClickListener(this);
        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case(R.id.b_aceptar):
                ll.setBackgroundColor(color);
                volver();
                break;
            case(R.id.b_salir):
                finish();
        }
    }

    private void volver() {
        i.putExtra("color", color);
        setResult(RESULT_OK, i);
        finish();
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