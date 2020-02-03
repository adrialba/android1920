package com.example.firstfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements NameFragment.OnBotonPulsadoListener, ColorFragment.OnInteractionListener{
    private NameFragment nf;
    private ColorFragment cf;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Gestionar los Fragments

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        nf = new NameFragment();
        transaction.add(R.id.panelIzq, nf); //Aqui se produce el attach
        if(grande()){
            cf = new ColorFragment().newInstance("");
            transaction.add(R.id.panelDrc, cf);
        }
        transaction.commit();
    }
    public boolean grande(){
        return findViewById(R.id.panelDrc)!=null;
    }

    @Override
    public void OnBotonPulsado(View v) {
        switch (v.getId()){
            case R.id.b1:
                /*
                Al pulsar el boton Aceptar de NameFragment, queremos pasar el nombre
                a ColorFragment. MainActivity se encarga de la comunicacion entre los fragments:
                recoge el valor del String y se lo pasa a ColorFragment
                 */
                EditText et_nombre = findViewById(R.id.et_nombre);
                //Toast.makeText(this, et_nombre.getText().toString(), Toast.LENGTH_SHORT).show();
                //Personalizar el fragment de color
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                cf = ColorFragment.newInstance(et_nombre.getText().toString());
                transaction.replace(R.id.panelDrc, cf);
                transaction.commit();
                break;
            default:
                finish();

        }
    }

    @Override
    public void OnChangeColor(View v) {
        switch (v.getId()){
            case R.id.b1:
                /*
                Al pulsar el boton Aceptar de NameFragment, queremos pasar el nombre
                a ColorFragment. MainActivity se encarga de la comunicacion entre los fragments:
                recoge el valor del String y se lo pasa a ColorFragment
                 */
                EditText et_nombre = findViewById(R.id.et_nombre);
                //Toast.makeText(this, et_nombre.getText().toString(), Toast.LENGTH_SHORT).show();
                //Personalizar el fragment de color
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                cf = ColorFragment.newInstance(et_nombre.getText().toString());
                transaction.replace(R.id.panelDrc, cf);
                transaction.commit();
                break;
            case R.id.b_aceptar:
                LinearLayout ll = findViewById(R.id.fr_color);
                ll.setBackgroundColor(color);
                break;
            default:
                finish();

        }
    }

    @Override
    public void OnColorElegido(int item) {
        switch (item){
            case R.id.b_azul:
                color = Color.CYAN;
                break;
            case R.id.b_rojo:
                color = Color.RED;
                break;
            case R.id.b_verde:
                color = Color.GREEN;
                break;
        }
    }
}
