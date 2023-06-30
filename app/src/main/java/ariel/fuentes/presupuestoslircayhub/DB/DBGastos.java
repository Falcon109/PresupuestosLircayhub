package ariel.fuentes.presupuestoslircayhub.DB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import ariel.fuentes.presupuestoslircayhub.Entidades.Gastos;

public class DBGastos extends DBHelper {

    private Context context;
    private SQLiteDatabase db;

    public DBGastos(@Nullable Context context){
        super(context);
        this.context = context;
    }
    public Boolean inserGastos(String nombregasto, Integer valor, String fechagastos, Integer latitud,  String longitud, String categoria){
        SQLiteDatabase db = this.getWritableDatabase();

        DBHelper dbhelper = new DBHelper(context);
        this.db = dbhelper.getWritableDatabase();

        ContentValues contentValue = new ContentValues();
        contentValue.put("nombregasto", nombregasto);
        contentValue.put("valor", valor);
        contentValue.put("fechagastos", fechagastos);
        contentValue.put("latitud", latitud);
        contentValue.put("longitud", longitud);
        contentValue.put("categoria", categoria);
        long result = db.insert(TABLE_GASTOS,null,contentValue);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    @SuppressLint("Recycle")
    public ArrayList<Gastos> mostrardatos() {

        DBHelper dbhelper = new DBHelper(context);
        this.db = dbhelper.getWritableDatabase();

        ArrayList<Gastos> Listproductos = new ArrayList<>();
        Gastos gastos = new Gastos();
        Cursor cursorproduct;

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_GASTOS, null);
        while (cursor.moveToNext()) { // Recorrer los resultados de la consulta
            int id = cursor.getInt(0);
            String nombgasto = cursor.getString(1);
            int valorgast = Integer.parseInt(cursor.getString(2));
            String fechgast = cursor.getString(3);
            String latit = cursor.getString(4);
            String longt = cursor.getString(6);
            String categ = cursor.getString(5);

            gastos = new Gastos(id,nombgasto,valorgast,fechgast,latit,longt,categ); // Crear un objeto Producto
            Listproductos.add(gastos); // Agregar el objeto a la lista de productos
        }

        cursor.close(); // Cerrar el cursor
        db.close(); // Cerrar la conexión con la base de datos
        return Listproductos;
    }

    public Gastos verdatos(int ID) {

        DBHelper dbhelper = new DBHelper(context);
        this.db = dbhelper.getWritableDatabase();

        Gastos gastos = null;
        Cursor cursorgastos;

        cursorgastos = db.rawQuery("SELECT * FROM " + TABLE_GASTOS +" WHERE id = '" + ID + "' LIMIT 1", null);

        while (cursorgastos.moveToNext()) { // Recorrer los resultados de la consulta
            gastos = new Gastos();
            gastos.setId(cursorgastos.getInt(0));
            gastos.setNombre(cursorgastos.getString(1));
            gastos.setValor(Integer.parseInt(cursorgastos.getString(2)));
            gastos.setFecha( cursorgastos.getString(3));
            gastos.setLatitud( cursorgastos.getString(4));
            gastos.setLongitud( cursorgastos.getString(5));
            gastos.setCategoria( cursorgastos.getString(6));
        }
        cursorgastos.close(); // Cerrar el cursor
        return gastos;
    }

    public boolean eliminarGastos(int ID) {

        boolean ready;
        DBHelper dbhelper = new DBHelper(context);
        this.db = dbhelper.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_GASTOS + " WHERE id= '" + ID + "'");
            ready = true;
        } catch (Exception ex) {
            ex.toString();
            ready = false;
        }finally {
            db.close();
        }

        return ready;
    }

    public boolean editProducto(int ID, Editable nombregasto, Editable valor, Editable fechagastos, Editable latitud, Editable longitud, Editable categoria) {

        boolean ready = false;
        DBHelper dbhelper = new DBHelper(context);
        this.db = dbhelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + TABLE_GASTOS + " SET nombregasto = '" + nombregasto+ "', valor ='" + valor + "', fechagastos ='" + fechagastos + "', latitud ='" + latitud +"', longitud ='" + longitud + "', categoria ='" + categoria + "' WHERE id= '" + ID + "'");
            ready = true;
        } catch (Exception ex) {
            ex.toString();
            ready = false;
        }finally {
            db.close();
        }
        return ready;
    }

}