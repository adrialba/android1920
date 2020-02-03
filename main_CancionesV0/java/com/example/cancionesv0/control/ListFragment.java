package com.example.cancionesv0.control;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.cancionesv0.R;
import com.example.cancionesv0.modelo.Cancion;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    //ATRIBUTOS
    protected ListView lv;
    protected ArrayList<Cancion> modelo; //modelo de datos
    protected ArrayAdapter<Cancion> aa;
    private OnListaListener onListaListener;


    public ListFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Instanciar el modelo
        modelo = new ArrayList<>();
        modelo.add(new Cancion("a", 2000));
        modelo.add(new Cancion("b", 2002));
        modelo.add(new Cancion("cccc", 2000));

        View result = inflater.inflate(R.layout.fragment_lista, container, false);
        //Rellenar la lista desde el modelo
        lv = result.findViewById(R.id.lista);
         aa = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                modelo
        );
        lv.setAdapter(aa);
        //getActivity().registerForContextMenu(lv);
        ((Activity)onListaListener).registerForContextMenu(lv);
        Button b_insertar = result.findViewById(R.id.b_insertar);
        b_insertar.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                   onListaListener.OnFragmentInteraction(v);
              }
          });
        return result;
    }
    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        onListaListener = (OnListaListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onListaListener = null;

    }

    public interface OnListaListener {
        void OnFragmentInteraction(View v);
    }
}
