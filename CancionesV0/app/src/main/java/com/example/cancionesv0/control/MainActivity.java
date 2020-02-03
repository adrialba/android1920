package com.example.cancionesv0.control;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.example.cancionesv0.R;
import com.example.cancionesv0.modelo.Cancion;
public class MainActivity extends AppCompatActivity implements ListFragment.OnListaListener, DetailFragment.OnDetailListener {
    //constantes
    private static final int INSERCION = 0;
    private static final int EDICION = 1;
    private ListFragment lf;
    private DetailFragment df;
    //private EmptyFragment ef;
    private Cancion cancion;
    private long rowId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lf = new ListFragment();
        cancion = new Cancion();
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fr_lista, lf);
        cancion = new Cancion();
        if (grande()) {
            transaction.add(R.id.fr_detalle, new EmptyFragment());
        }
        transaction.commit();

        rowId = -(savedInstanceState==null? -1: savedInstanceState.getLong("rowId"));
    //Al empezar la aplicacion no estamos en ninguna cancion
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.ctx_fila, menu);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(lf.aa.getItem(info.position).getTitulo());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Si estamos posicionados en una cancion, la guardamos
        if(rowId!=-1) outState.putLong("rowId", rowId);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Cancion c = lf.aa.getItem(info.position);
        switch(item.getItemId()){
            case R.id.fila_borrar:
                lf.aa.remove(c);
                if((grande())) cargarFragmentDetalle(new EmptyFragment());
                break;
            case R.id.fila_editar:
                if(grande()) {
                    //Cargar el fragment con los datos de la cancion
                    df = DetailFragment.newInstance(c);
                    cargarFragmentDetalle(df);
                } else {// Abrir el forn con un intent y pasarle los datos para editar
                    //Abrir el intent con el Detail de form
                    Intent i = new Intent(this, DetailFragment.class);
                    //Pasarle los datos de la cancion seleccionados
                    i.putExtra("autor", cancion.getAutor());
                    i.putExtra("titulo", cancion.getTitulo());
                    i.putExtra("anio", cancion.getAnio());
                    i.putExtra("tipo", cancion.getTipo().toString());
                    startActivityForResult(i, EDICION);
                }
                break;
            default: break;
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
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fr_detalle, df);
            transaction.commit();
        }else{
            //abrir un intent con el Fragment de form vacio
            Intent i = new Intent(this, DetailFragment.class);
            startActivityForResult(i, INSERCION);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INSERCION) {
            cancion = new Cancion(
                    data.getStringExtra("autor"),
                    data.getStringExtra("titulo"),
                    data.getIntExtra("anio", 2000),
                    Cancion.Tipo.valueOf(data.getStringExtra("tipo"))
            );
            if (requestCode == INSERCION) {
                lf.aa.add(cancion);
            } else {//EDICION
                int pos = data.getIntExtra("pos", 0);
                //cancion es un atributo global que se relleno al hacer longClick
                lf.aa.remove(cancion);
                //Recuperar los nuevos datos
                cancion = new Cancion(
                        data.getStringExtra("autor"),
                        data.getStringExtra("titulo"),
                        data.getIntExtra("anio", 2000),
                        Cancion.Tipo.valueOf(data.getStringExtra("tipo"))
                );
                lf.aa.insert(cancion, pos);
            }
        }
    }
    @Override
    public void onAceptar() {
        //Insertamos un dato de prueba en el ADAPTER
        EditText autor = findViewById(R.id.et_autor);
        EditText titulo = findViewById(R.id.et_titulo);
        EditText anio = findViewById(R.id.et_anio);
        cancion = new Cancion(); //Nueva para insertar

        //Datos nuevos
        cancion.setAutor(autor.getText().toString());
        cancion.setTitulo(titulo.getText().toString());
        cancion.setAnio(Integer.parseInt(anio.getText().toString()));
        //Insertamos
        if(DetailFragment.c!=null){ //EDITANDO
            //Posicion de la antigua y añadir en la posicion de la antigua la nueva cancion
                //int position = lf.aa.getPosition(DetailFragment.c);
            //Primero sacar la posicion del objeto
                //lf.aa.remove(lf.aa.getItem(position));
                //lf.aa.insert(cancion, position);
        } else {
                //lf.aa.add(cancion);
            ListFragment.dbh.insertar(cancion);
            actualizarLista();
        }

        df = new DetailFragment();
        cargarFragmentDetalle(df);
    }

    private void actualizarLista() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fr_lista, new ListFragment());
        transaction.commit();
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
