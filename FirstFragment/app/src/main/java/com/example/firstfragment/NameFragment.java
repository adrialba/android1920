package com.example.firstfragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class NameFragment extends Fragment {
    //Atributo que tiene referencia a la activity
    private OnBotonPulsadoListener mListener; //aun no tiene un valor, ahora mismo es null
    public NameFragment(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflar el Layout del Fragment
        View result = inflater.inflate(R.layout.fragment_nombre, container, false);
        //Inicializar lo que haga falta
        EditText et_nombre = result.findViewById(R.id.et_nombre);
        Button b_aceptar = result.findViewById(R.id.b1);
        b_aceptar.setOnClickListener(
                new View.OnClickListener(){ //Cuando se produzac un click, se la pasa
                    // a la activity, primero debes asegurarte que tiene un metodo y se la mandas a ella
                    @Override
                    public void onClick(View v) { //Cuando sereibo un click que viene de una vista
                        //Hay que redirigir el click hacia el metodo de la Activity
                        mListener.OnBotonPulsado(v);

                    }
                }
        );
        Button b_salir = result.findViewById(R.id.b2);
        b_salir.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        mListener.OnBotonPulsado(v);
                    }
                }
        );
        //devolver la vista completa
        return result;
    }
    @Override
    public void onAttach(Context context) {
        //Le engancho mis vistas, si estas implementando la interfaz: OnBotonPulsadoListener
        super.onAttach(context);
        /*
        Como el Fragment no tiene acceso a UIThread, todos los eventos manejados en
        OnCreateView deben ser redirigidos a la Activity, que es CONTEXTO de ejecucion del fragment
         */
        if(context instanceof OnBotonPulsadoListener){
            mListener = (OnBotonPulsadoListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
    public interface OnBotonPulsadoListener{
        //Define el metodo callback que gestionara el evento
        //La Activity debera implementar esta interfaz y por tanto el metodo para
        //dar respuesta al evento
        void OnBotonPulsado(View v);
    }
}
