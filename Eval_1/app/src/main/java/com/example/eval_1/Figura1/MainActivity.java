package com.example.eval_1.Figura1;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.eval_1.R;
public class MainActivity extends AppCompatActivity {
    LinearLayout ll;
    int color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = findViewById(R.id.ll_main);
        if(savedInstanceState!=null){
            color = savedInstanceState.getInt("color");
            ll.setBackgroundColor(color);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("color", color);
    }
    //Ejercicio 2
    public void onPulsado(View v) {
        switch(v.getId()){
            case R.id.b1:
                segunda();
                break;
            default:
                finish();
        }
    }
    private void segunda() {
        //Lanza la segunda actividad
        Intent i = new Intent(this, SegundaActivity.class);
        EditText et_nombre = findViewById(R.id.et_nombre);
        i.putExtra("nombre", et_nombre.getText().toString());
        startActivityForResult(i,0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK)
            ll.setBackgroundColor(data.getIntExtra("color", Color.WHITE));

    }
}