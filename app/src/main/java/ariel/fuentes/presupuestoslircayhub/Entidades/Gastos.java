package ariel.fuentes.presupuestoslircayhub.Entidades;

import java.util.Date;

public class Gastos {

    int id;
    String Nombre;
    int Monto;
    String Fecha;
    String Latitud;
    String Longitud;
    String Categoria;

    public Gastos(int Id, String nombre, int monto, String fecha, String latitud, String longitud, String categoria) {
        id = Id;
        Nombre = nombre;
        Monto = monto;
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

    public int getMonto() {
        return Monto;
    }

    public void setMonto(int monto) {
        Monto = monto;
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