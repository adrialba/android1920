package com.example.pulsaciones;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

public class MainActivity extends SpyActivity {
    private int cont;
    private Button b_pulsar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //findViewById
        b_pulsar = findViewById(R.id.b_pulsar);
        //Inicializar contador
        if(savedInstanceState!=null){
            cont = savedInstanceState.getInt("cont");
            if(cont>0)
                b_pulsar.setText(getResources().getQuantityString(R.plurals.veces, cont, cont));
        } else {
            cont = 0;

        }
    }

    public void onPulsado(View view) {
        cont++;
        //Log.d(getLocalClassName(), ""+ cont);
        //b_pulsar.setText(R.string.saludar);

        b_pulsar.setText(
                getResources().getQuantityString(R.plurals.veces, cont, cont)
        );
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        if(cont>0) outState.putInt("cont", cont);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        cont = ((savedInstanceState==null)?
                0 : savedInstanceState.getInt("cont"));

    }
}
