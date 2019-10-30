package com.example.eval_1.Figura1;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.eval_1.R;
public class SegundaActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    ImageView foto;
    Intent i;
    RadioGroup rg;
    int color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segunda_activity);
        i = getIntent();
        //Poner el listener longClick
        foto = findViewById(R.id.iv_foto);
        registerForContextMenu(foto);
        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_foto, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_saludar:
                Toast.makeText(this,getResources().getString(R.string.saludar, i.getStringExtra("nombre")) , Toast.LENGTH_LONG).show();
                break;
                default:
                   foto.setImageResource(R.drawable.indice1);
                   break;

        }
        return super.onContextItemSelected(item);
    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.rb0:
                color = Color.LTGRAY;
                break;
            case R.id.rb1:
                color = Color.CYAN;
                break;
            case R.id.rb2:
                color = Color.YELLOW;
        }
    }
    public void onPulsado(View view) {
        i.putExtra("color", color);
        setResult(RESULT_OK,i);
        finish();
    }
}