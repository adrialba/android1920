package com.example.firstfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private FragmentNombre nf;
    private ColorFragment cf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Gestionar los Fragments

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction():
        nf = new FragmentNombre();
        transaction.add(R.id.fr_nombre, nf);
        transaction.commit();
    }
}
