package com.example.cancionesv0.control;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.cancionesv0.R;
import com.example.cancionesv0.modelo.Cancion;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    //ATRIBUTOS
    protected ListView lv;
    private ArrayList<Cancion> modelo; //modelo de datos


    public ListFragment(){}

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Instanciar el modelo
        modelo = new ArrayList<>();
        modelo.add(new Cancion("a", 2000));
        modelo.add(new Cancion("b", 2002));
        modelo.add(new Cancion("cccc", 2000));

        View result = inflater.inflate(R.layout.fragment_lista, container, false);
        //Rellenar la lista desde el modelo
        lv = result.findViewById(R.id.lista);
        ArrayAdapter<Cancion> aa = new ArrayAdapter<Cancion>();


        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
