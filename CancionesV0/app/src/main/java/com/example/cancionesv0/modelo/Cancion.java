package com.example.cancionesv0.modelo;

public class Cancion {
    public enum Tipo{TRANQUILA, ANIMADA, FIESTA};
    private long id;
    private String titulo;
    private String autor;
    private Tipo tipo;
    private int anio;

    public Cancion(String titulo, String autor, Tipo tipo, int anio) {
        this.titulo = titulo;
        this.autor = autor;
        this.tipo = tipo;
        this.anio = anio;
    }

    public Cancion(String titulo, int anio) {
        this(titulo, "anonimo", Tipo.FIESTA, anio);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return titulo.toUpperCase()+"\n"+autor;
    }
}
