package ariel.fuentes.presupuestoslircayhub.Entidades;

import java.util.Date;

public class Gastos {

    String Nombre;
    int Valor;
    Date Fecha;
    String Latitud;
    String Longitud;
    String Categoria;

    public Gastos(String nombre, int valor, Date fecha, String latitud, String longitud, String categoria) {
        Nombre = nombre;
        Valor = valor;
        Fecha = fecha;
        Latitud = latitud;
        Longitud = longitud;
        Categoria = categoria;
    }

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

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
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
