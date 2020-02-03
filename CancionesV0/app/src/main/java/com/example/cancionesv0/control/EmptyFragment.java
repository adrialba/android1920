package com.example.cancionesv0.control;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.example.cancionesv0.R;

public class EmptyFragment extends Fragment {
    public EmptyFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_vacio, container, false);
        return result;
    }
}
