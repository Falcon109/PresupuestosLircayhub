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
    public boolean inserGastos(String nombreGasto, int monto, String fechaGasto, String latitud, String longitud, String categoria) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nombregasto", nombreGasto);
        contentValues.put("monto", monto);
        contentValues.put("fechagastos", fechaGasto);
        contentValues.put("latitud", latitud);
        contentValues.put("longitud", longitud);
        contentValues.put("categoria", categoria);

        long result = db.insert(TABLE_GASTOS, null, contentValues);
        db.close();

        return result != -1;
    }

    public ArrayList<Gastos> mostrardatos() {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Gastos> listaGastos = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_GASTOS, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nombreGasto = cursor.getString(1);
            int monto = cursor.getInt(2);
            String fechaGasto = cursor.getString(3);
            String latitud = cursor.getString(4);
            String longitud = cursor.getString(5);
            String categoria = cursor.getString(6);

            Gastos gasto = new Gastos(id, nombreGasto, monto, fechaGasto, latitud, longitud, categoria);
            listaGastos.add(gasto);
        }

        cursor.close();
        db.close();

        return listaGastos;
    }


    public Gastos obtenerGastoPorID(int ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Gastos gastos = null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_GASTOS + " WHERE id = '" + ID + "' LIMIT 1", null);

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            String nombreGasto = cursor.getString(1);
            int monto = cursor.getInt(2);
            String fechaGasto = cursor.getString(3);
            String latitud = cursor.getString(4);
            String longitud = cursor.getString(5);
            String categoria = cursor.getString(6);

            gastos = new Gastos(id, nombreGasto, monto, fechaGasto, latitud, longitud, categoria);
        }

        cursor.close();
        db.close();

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

    public boolean editProducto(int ID, Editable nombregasto, Editable monto) {

        boolean ready = false;
        DBHelper dbhelper = new DBHelper(context);
        this.db = dbhelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + TABLE_GASTOS + " SET nombregasto = '" + nombregasto+ "', monto ='" + monto + "'");
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