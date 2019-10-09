package com.example.misfotos;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button b_saludar, b_cambiar;
    private LinearLayout ll_botones;
    private ImageView iv_foto;
;    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarLayour();
        registerForContextMenu(iv_foto); //Listener longClick
        registerForContextMenu(ll_botones);
    }
    private void iniciarLayour() {
        //Aqui ponemos los findViewById, los listeners...
        b_cambiar = findViewById(R.id.b_cambiar);
        b_cambiar.setOnClickListener(this);
        b_saludar = findViewById(R.id.b_saludar);
        b_saludar.setOnClickListener(this);
        iv_foto = findViewById(R.id.iv_foto);
        ll_botones = findViewById(R.id.ll_botones);
        ll_botones.setOnClickListener(this);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //infla el Layout correspondiente a un menu contextual
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()){
            case R.id.iv_foto:
                getMenuInflater().inflate(R.menu.ctx_foto, menu);
                break;
            case R.id.ll_botones:
                getMenuInflater().inflate(R.menu.ctx_fondo, menu);
                break;
        }

    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_cambiar:
                cambiar();
                break;
            case R.id.menu_saludar:
                saludar();
                break;
            case R.id.menu_salir:
                finish();
        }
        return true;
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.b_cambiar:
                cambiar();
                break;
            case R.id.b_saludar:
                saludar();
                break;
        }
    }
    private void saludar() {
        Toast.makeText(this, "HOLA", Toast.LENGTH_LONG).show();
    }
    private void cambiar() {
        iv_foto.setImageDrawable(
                getResources().getDrawable(R.drawable.indice)
        );
        iv_foto.setImageDrawable(
                ResourcesCompat.getDrawable(getResources(), R.drawable.indice, null)
        );
        //iv_foto.setImageDrawable(R.drawable.indice);
    }
}