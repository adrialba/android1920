package com.example.cancionesv0.vista;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cancionesv0.R;
import com.example.cancionesv0.modelo.DBHandler;
public class MyAdapter extends CursorAdapter {
    private Activity activity = null;
    private DBHandler dbh = null;
    private Cursor modelo = null;
    public MyAdapter(Activity context, Cursor c) {
        super(context, c, false);
        this.activity = context;
        this.dbh = new DBHandler(activity);
        this.modelo = c;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        //Hay que instanciar una fila nueva
        View fila = activity.getLayoutInflater().inflate(R.layout.layout_fila, parent, false);
        //Hay que hacer findViewById para rellenar el ViewHolder
        //solo cuando se instancia la fila
        ViewHolder vh = new ViewHolder();
        vh.titulo = fila.findViewById(R.id.tv_fila_titulo);
        vh.autor = fila.findViewById(R.id.tv_fila_autor);
        vh.iv_icon = fila.findViewById(R.id.ico_fila);
        vh.ll_texto = fila.findViewById(R.id.ll_fila_texto);
        fila.setTag(vh);
        populateFrom(vh, cursor, dbh);
        return fila;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //Recicla la vista recupedando su ViewHolder
        ViewHolder vh = (ViewHolder) view.getTag();
        populateFrom(vh, cursor, dbh);
    }

    public void populateFrom(ViewHolder vh, Cursor c, DBHandler db){
        int anio = c.getInt(3);
        vh.titulo.setText(db.getTitulo(c)+", "+anio);
        vh.autor.setText(c.getString(2));
        if(anio<2000){
            vh.ll_texto.setBackgroundColor(Color.YELLOW);
        }else {
            vh.ll_texto.setBackgroundColor(Color.CYAN);
        }
    }

    /*
    Añadimos la clase privada ViewHolder que MEMORIZA los id´s de cada nueva fila(vista).
    De esta forma, cada fila tiene un par de numeros memorizados en forma de TAGS y se evitan
    los findViewById constantes. Especie de mapa(key, valor)
     */
    static class ViewHolder{
        public TextView titulo;
        public TextView autor;
        public LinearLayout ll_texto;
        public ImageView iv_icon;
    }
}
