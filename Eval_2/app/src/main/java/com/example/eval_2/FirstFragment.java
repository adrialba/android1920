package com.example.eval_2;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {
    private OnBotonPulsadoListener mListener;
    public FirstFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.first_fragment, container, false);
        EditText et_nombre = result.findViewById(R.id.et_nombre);
        Button b_aceptar = result.findViewById(R.id.b1);
        b_aceptar.setOnClickListener(
              new View.OnClickListener(){
                  @Override
                  public void onClick(View v) {
                      mListener.OnBotonPulsado(v);
                  }
              }
        );
        return result;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnBotonPulsadoListener){
            mListener = (OnBotonPulsadoListener) context;
        }else{
            throw new RuntimeException(context.toString()+" must implement OnBotonPulsadoListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnBotonPulsadoListener{
        void OnBotonPulsado(View v);
    }
}
