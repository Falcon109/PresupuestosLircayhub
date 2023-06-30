package ariel.fuentes.presupuestoslircayhub.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "gastos.db";
    public String TABLE_GASTOS = "t_gastos";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_GASTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombregasto TEXT NOT NULL," +
                "monto NUMERIC NOT NULL," +
                "fechagastos TEXT NOT NULL," +
                "latitud TEXT NOT NULL," +
                "longitud TEXT NOT NULL," +
                "categoria TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE "+this.TABLE_GASTOS);
        onCreate(db);
    }

}
