package com.example.firstfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ColorFragment extends Fragment {
    //Atributos
    //atributo heredado
    //Bundle args
    private String nombre;
    private OnInteractionListener mListener;
    //Constructor vacio
    public ColorFragment(){}

    //Pasando parametros
    /*
    Para garantizar que no hacemos muchas instancias de un Fragment:
        El constructor es obligatoriamente vacio
        Si queremos pasar parametros usamos el patron Factory
        y su metodo newInstance, que es estatico
     */
    public static ColorFragment newInstance(String nombre) {
        //Usado para pasarlo todo en grupo
        //Bundle args = new Bundle();
        ColorFragment cf = new ColorFragment();
        cf.nombre = nombre;
        return cf;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("nombre", nombre);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_color, container);
        //añadir eventos, etc.
        TextView tv_elige = result.findViewById(R.id.tvElige);
        tv_elige.setText(getResources().getString(R.string.tvElige));
        Button b_aceptar = result.findViewById(R.id.b_aceptar);
        b_aceptar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Redirigir el evento a la activity
                        mListener.OnChangeColor(v);
                    }
                }
        );
        Button b_salir = result.findViewById(R.id.b_salir);
        b_salir.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.OnChangeColor(v);
                    }
                }
        );
        RadioGroup rg = result.findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int i) {
                        mListener.OnColorElegido(i);
                    }
                }
        );
        return result;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null; //Liberar recursos
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnInteractionListener){
            mListener = (OnInteractionListener) context;
        }
    }
    public interface OnInteractionListener{
        void OnChangeColor(View v);
        void OnColorElegido(int item);
    }
}
