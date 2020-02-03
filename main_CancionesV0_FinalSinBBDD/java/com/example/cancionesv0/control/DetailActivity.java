package com.example.cancionesv0.control;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.example.cancionesv0.R;
import com.example.cancionesv0.modelo.Cancion;
public class DetailActivity  extends AppCompatActivity implements DetailFragment.OnDetailListener {
    private DetailFragment df;
    private Intent i;
    private Cancion.Tipo t = Cancion.Tipo.FIESTA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        //Consultar en intent para ver si es Insercion o Edicion
        i = getIntent();
        if(i.getStringExtra("titulo")==null) df = new DetailFragment(); //INSERCION
        else { //EDICION
            Cancion c = new Cancion(
                    i.getStringExtra("autor"),
                    i.getStringExtra("titulo"),
                    i.getIntExtra("anio", 2000),
                    Cancion.Tipo.valueOf(i.getStringExtra("tipo"))
            );
            df = DetailFragment.newInstance(c);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fr_detalle, df);
        transaction.commit();
    }
    @Override
    public void onAceptar() {
        //Enviar los datos del formulario a traves del Intent
        EditText autor = findViewById(R.id.et_autor);
        i.putExtra("autor", autor.getText().toString());
        EditText titulo = findViewById(R.id.et_titulo);
        i.putExtra("titulo", titulo.getText().toString());
        EditText anyo = findViewById(R.id.et_anio);
        i.putExtra("anio", anyo.getText().toString());
        i.putExtra("tipo", t.toString());

        setResult(RESULT_OK, i);
        finish();
    }
    @Override
    public void onTipoSelected(int item) {
        switch (item){
            case R.id.rb0:
                t = Cancion.Tipo.TRANQUILA;
                break;
            case R.id.rb1:
                t = Cancion.Tipo.ANIMADA;
                break;
            case R.id.rb2:
                t = Cancion.Tipo.FIESTA;
                break;
        }
    }
}
