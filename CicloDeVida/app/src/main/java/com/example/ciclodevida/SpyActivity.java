package com.example.ciclodevida;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

public class SpyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getLocalClassName(), "onCreate");
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
