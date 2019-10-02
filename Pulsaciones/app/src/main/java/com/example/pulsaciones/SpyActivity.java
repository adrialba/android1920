package com.example.pulsaciones;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SpyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getLocalClassName(), "onCreate");
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(getLocalClassName(),"onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(getLocalClassName(),"onResume");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(getLocalClassName(),"onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(getLocalClassName(),"onSaveInstanceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(getLocalClassName(),"onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(getLocalClassName(),"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(getLocalClassName(),"onStop");
    }

}
