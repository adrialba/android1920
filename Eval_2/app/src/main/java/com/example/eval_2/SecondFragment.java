package com.example.eval_2;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {
    private String nombre;
    public SecondFragment(){}
    public static SecondFragment newInstance(String nombre){
        SecondFragment sf = new SecondFragment();
        sf.nombre = nombre;
        return sf;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result =  inflater.inflate(R.layout.second_fragment, container, false);
        TextView tv_elige = result.findViewById(R.id.tv_segundo);
        tv_elige.setText(getResources().getString(R.string.tv_escribe_nombre));
        return result;
    }
}
