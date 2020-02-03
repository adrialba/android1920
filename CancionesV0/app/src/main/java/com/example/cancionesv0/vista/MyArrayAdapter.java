package com.example.cancionesv0.vista;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cancionesv0.R;
import com.example.cancionesv0.modelo.Cancion;
import java.util.ArrayList;
import java.util.List;
public class MyArrayAdapter extends ArrayAdapter {
    /*
    Una clase Adapter debe tener: Una referencia a alguna activity
    que gestione el UIThread para visualizar las vistas generadas
    y una referencia a algun modelo de datos en el cual basarse
    para generar las filas-vistas
    Ademas, utilizara algun tipo de layout para generar esas filas
     */
    //ATRIBUTOS
    private Activity activity = null;
    private ArrayList<Cancion> modelo = null;
    private int layout;
    public MyArrayAdapter(Context context, int layout, ArrayList<Cancion> modelo) {
        super(context, layout, modelo);
        this.activity = (Activity) context;
        this.modelo = modelo;
        this.layout = layout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View fila = convertView;
        ViewHolder vh = new ViewHolder();
        if(fila==null){
            fila = activity.getLayoutInflater().inflate(layout, null);
            //Ademas hay que instanciar su Holder, rellenarlo y adjudicarselo
                //Si es una fila nueva hay que rellenarlo y adjudicarselo
            vh.titulo = fila.findViewById(R.id.tv_fila_titulo);
            vh.autor = fila.findViewById(R.id.tv_fila_autor);
            vh.ll_texto = fila.findViewById(R.id.ll_fila_texto);
            fila.setTag(vh);
        } else {
            vh = (ViewHolder) fila.getTag();
        }
        //Rellenar la fila con los datos de la cancion
        Cancion c = modelo.get(position);
        vh.titulo.setText(c.getTitulo());
        vh.autor.setText(c.getAutor());
        //(TextView) fila.findViewById(R.id.fila_tv_anio)).setText(c.getTitulo);
        if(c.getAnio()<2000){
            vh.ll_texto.setBackgroundColor(Color.YELLOW); //Asigno un color dependiendo del año
        } else {
            vh.ll_texto.setBackgroundColor(Color.CYAN);
        }
        //findViewById del icono para poner la imagen que corresponda
        return fila;
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
