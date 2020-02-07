package com.example.cancionesv0.modelo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class DBHandler {
    //Constantes
    //Version
        private static final int DB_VERSION = 1;
    //Nombre de la DB
        private static final String DB_NAME="Canciones";
    //Nombre de las tablas
        private static final String TABLE_NAME="canciones";
    //Nombre de columnas de la tabla
        public static final String KEY_ROWID="_id"; //columna con nombre obligatorio
        public static final String KEY_TITULO="titulo";
        public static final String KEY_AUTOR="autor";
        public static final String KEY_ANIO="anio";
        public static final String KEY_TIPO="tipo";
    //Sentencia de Creacion de tabla
        private static final String CREATE_TABLE=
            " create table "+ TABLE_NAME+ "("
            +KEY_ROWID+ " integer primary key autoincrement, "
            +KEY_TITULO+ " text, "
            +KEY_AUTOR+ " text, "
            +KEY_ANIO+ " text, "
            +KEY_TIPO+ " text); ";
        private final Context ctx;
    //Atributos de INSTANCIA
    private DBHElper dbh;
    private SQLiteDatabase db;
    //Constructor
    public DBHandler(Context ctx) {
        this.ctx = ctx;
    }
    public DBHandler abrir(){
        //Asignamos valor a los atributos
        dbh = new DBHElper(ctx);
        db = dbh.getWritableDatabase();
        return this;
    }
    public void cerrar(){dbh.close();}
    public long insertar(Cancion c){
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITULO, c.getTitulo());
        cv.put(KEY_AUTOR, c.getAutor());
        cv.put(KEY_ANIO, c.getAnio());
        cv.put(KEY_TIPO, c.getTipo().toString());
        Log.i(DB_NAME, "...insertando");
        return db.insert(TABLE_NAME, null, cv);
    }
    public boolean borrar(long rowId){
        return db.delete(TABLE_NAME, KEY_ROWID+"="+rowId, null)>0;
    }
    public boolean actualizar(long rowId, String titulo, String autor, int anio, String tipo){
        String[] rows = {rowId+""};
        ContentValues args = new ContentValues();
        args.put(KEY_TITULO, titulo);
        args.put(KEY_AUTOR, autor);
        args.put(KEY_ANIO, anio);
        args.put(KEY_TIPO, tipo);
        return db.update(TABLE_NAME,args, KEY_ROWID+"="+rowId, null)==1;
    }
    public Cursor listado(){
        String[] campos = {KEY_ROWID, KEY_TITULO, KEY_AUTOR, KEY_ANIO, KEY_TIPO};
        Cursor mCursor = db.query(TABLE_NAME, campos, null, null, null, null, null);
        if(mCursor!=null) mCursor.moveToFirst();
        return mCursor;
    }
    //Estos metodos encapsula el getString
    public String getTitulo(Cursor c){
        return c.getString(1);
    }
    public Cursor getItem(long rowId) {
        String[] campos = {KEY_ROWID, KEY_TITULO, KEY_AUTOR, KEY_ANIO, KEY_TIPO};
        Cursor c = db.query(TABLE_NAME, campos,
                KEY_ROWID+"="+rowId,
                null, null, null, null, null);
        if(c!=null) c.moveToFirst(); //Por si acaso hay mas de uno
        return c;
    }
    //Clase privada estatica
    public static class DBHElper extends SQLiteOpenHelper{

        public DBHElper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            Log.i(DB_NAME,"version.."+DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //Crea el esquema de tablas
            Log.i(DB_NAME, CREATE_TABLE);
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //Nothing done
        }
    }
}
