package com.example.cancionesv0.control;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.fragment.app.Fragment;
import com.example.cancionesv0.R;
import com.example.cancionesv0.modelo.Cancion;

public class DetailFragment extends Fragment {
    private OnDetailListener onDetailListener = null;
    protected static Cancion c;
    public DetailFragment() {}
    public static DetailFragment newInstance(Cancion c){
        DetailFragment fragment = new DetailFragment();
        DetailFragment.c = c;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_form, container, false);
        if (c != null) cargarCancion(result);
        RadioGroup rg = result.findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int i) {
                        onDetailListener.onTipoSelected(i);
                    }
                }
        );
        Button b_aceptar = result.findViewById(R.id.b_aceptar);
        b_aceptar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onDetailListener.onAceptar();
                    }
                }
        );
        return result;
    }

    private void cargarCancion(View result) {
        ((EditText)result.findViewById(R.id.et_titulo)).setText(c.getTitulo());
        ((EditText)result.findViewById(R.id.et_autor)).setText(c.getAutor());
        ((EditText)result.findViewById(R.id.et_anio)).setText(String.valueOf(c.getAnio()));
        ((RadioGroup)result.findViewById(R.id.rg)).check(getIdFromTipo(c.getTipo()));
    }

    private int getIdFromTipo(Cancion.Tipo tipo) {

        switch(tipo.ordinal()){
            case 0: return R.id.rb0;
            case 1: return R.id.rb1;
            default : return R.id.rb2;
       }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onDetailListener = (OnDetailListener) context;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        onDetailListener = null;
    }
    public interface OnDetailListener {
        void onAceptar();
        void onTipoSelected(int item);

    }
}
