package com.example.cancionesv0.control;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.example.cancionesv0.R;
import com.example.cancionesv0.modelo.Cancion;

public class MainActivity extends AppCompatActivity implements ListFragment.OnListaListener, DetailFragment.OnDetailListener {
    ListFragment lf;
    DetailFragment df;
    Cancion cancion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lf = new ListFragment();
        cancion = new Cancion();
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fr_lista, lf);
        transaction.commit();
        if (grande()) {
            transaction.add(R.id.fr_detalle, new EmptyFragment());
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.ctx_fila, menu);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(lf.aa.getItem(info.position).getTitulo());
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Cancion c = lf.aa.getItem(info.position);
        switch(item.getItemId()){
            case R.id.fila_borrar:
                lf.aa.remove(c);
                break;
            case R.id.fila_editar:
                //Cargar el fragment con los datos de la cancion
                df = DetailFragment.newInstance(c);
                cargarFragmentDetalle(df);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private boolean grande() {
        return (findViewById(R.id.fr_detalle) != null);
    }
    @Override
    public void OnFragmentInteraction(View v) {
        //Si estamos en grande, cargar el fragment del form
        if (grande()) {
            df = new DetailFragment();
            cargarFragmentDetalle(df);
        }
    }
    @Override
    public void onAceptar() {
        //Insertamos un dato de prueba en el ADAPTER
        EditText autor = findViewById(R.id.et_autor);
        EditText titulo = findViewById(R.id.et_titulo);
        EditText anio = findViewById(R.id.et_anio);
        cancion = new Cancion();

        cancion.setAutor(autor.getText().toString());
        cancion.setTitulo(titulo.getText().toString());
        cancion.setAnio(Integer.parseInt(anio.getText().toString()));
        lf.aa.add(cancion);
        if(DetailFragment.c!=null){ //EDITANDO
            int position = lf.aa.getPosition(DetailFragment.c);
            lf.aa.remove(DetailFragment.c);
            lf.aa.insert(cancion, position);

        } else {
            lf.aa.add(cancion);
        }

        df = new DetailFragment();
        cargarFragmentDetalle(df);
    }

    @Override
    public void onTipoSelected(int item) {
        switch (item) {
            case R.id.rb0:
                cancion.setTipo(Cancion.Tipo.TRANQUILA);
                break;
            case R.id.rb1:
                cancion.setTipo(Cancion.Tipo.ANIMADA);
                break;
            case R.id.rb2:
                cancion.setTipo(Cancion.Tipo.FIESTA);
        }
    }
    private void cargarFragmentDetalle(DetailFragment df) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fr_detalle, df);
        transaction.commit();
    }
}
