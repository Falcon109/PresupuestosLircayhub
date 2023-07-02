package ariel.fuentes.presupuestoslircayhub.Entidades;

public class Presupuestos {

    int Total;
    int Arriendo;
    int Alimentacion;
    int Transporte;
    int SerBasicos;
    int Deudas;
    int Ahorro;
    String Fecha;

    public Presupuestos(int total, int arriendo, int alimentacion, int transporte, int serbasicos, int deudas, int ahorro, String fecha) {
        Total = total;
        Arriendo = arriendo;
        Alimentacion = alimentacion;
        Transporte = transporte;
        SerBasicos = serbasicos;
        Deudas = deudas;
        Ahorro = ahorro;
        Fecha = fecha;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public int getArriendo() {
        return Arriendo;
    }

    public void setArriendo(int arriendo) {
        Arriendo = arriendo;
    }

    public int getAlimentacion() {
        return Alimentacion;
    }

    public void setAlimentacion(int alimentacion) {
        Alimentacion = alimentacion;
    }

    public int getTransporte() {
        return Transporte;
    }

    public void setTransporte(int transporte) {
        Transporte = transporte;
    }

    public int getSerBasicos() {
        return SerBasicos;
    }

    public void setSerBasicos(int serBasicos) {
        SerBasicos = serBasicos;
    }

    public int getDeudas() {
        return Deudas;
    }

    public void setDeudas(int deudas) {
        Deudas = deudas;
    }

    public int getAhorro() {
        return Ahorro;
    }

    public void setAhorro(int ahorro) {
        Ahorro = ahorro;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
