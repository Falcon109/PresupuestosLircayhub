package ariel.fuentes.presupuestoslircayhub.Entidades;

import java.util.Date;

public class Gastos {

    int id;
    String Nombre;
    int Valor;
    String Fecha;
    String Latitud;
    String Longitud;
    String Categoria;

    public Gastos(int Id, String nombre, int valor, String fecha, String latitud, String longitud, String categoria) {
        id = Id;
        Nombre = nombre;
        Valor = valor;
        Fecha = fecha;
        Latitud = latitud;
        Longitud = longitud;
        Categoria = categoria;
    }

    public Gastos() {

    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getValor() {
        return Valor;
    }

    public void setValor(int valor) {
        Valor = valor;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }
}