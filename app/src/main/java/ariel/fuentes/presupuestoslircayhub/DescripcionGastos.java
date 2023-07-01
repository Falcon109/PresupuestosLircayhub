package ariel.fuentes.presupuestoslircayhub;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ariel.fuentes.presupuestoslircayhub.DB.DBGastos;
import ariel.fuentes.presupuestoslircayhub.DB.DBHelper;
import ariel.fuentes.presupuestoslircayhub.Entidades.Gastos;
import ariel.fuentes.presupuestoslircayhub.Menu_Fragment.GastosFragment;

public class DescripcionGastos extends AppCompatActivity {

    private int IDGastos;
    private SQLiteDatabase DB;

    private Adaptador_Gastos adaptadorGastos;

    TextView Fecha, Categoria, Latitud, Longitud;
    EditText Nombre, Monto;
    Button Guardar;
    ImageView IconCategoria;
    FloatingActionButton Editar, Eliminar, Volver;

    int id = 0;
    Gastos gastos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descirpcion_gastos);

        IconCategoria = findViewById(R.id.imagengastos);
        Nombre = findViewById(R.id.nombregastos);
        Monto = findViewById(R.id.numeromonto);
        Fecha = findViewById(R.id.numerofecha);
        Categoria = findViewById(R.id.nomcategoria);
        Latitud = findViewById(R.id.numlatitud);
        Longitud = findViewById(R.id.numlongitud);

        Guardar = findViewById(R.id.btnGuardar);
        Editar = findViewById(R.id.btnEditar);
        Eliminar = findViewById(R.id.btnEliminar);

        Nombre.setEnabled(false);
        Monto.setEnabled(false);
        Guardar.setVisibility(View.INVISIBLE);

        //ver producto
        if(savedInstanceState==null){
            Bundle extra = getIntent().getExtras();
            if (extra==null){
                id = Integer.parseInt(null);
            }else {
                id = extra.getInt("ID");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DBGastos dbGastos = new DBGastos(this);
        gastos = dbGastos.verdatos(id);

        if (gastos!= null){
            Nombre.setText(gastos.getNombre());
            Monto.setText(String.valueOf(gastos.getMonto())); // Convierte el monto a String
            Fecha.setText(gastos.getFecha());
            Categoria.setText(gastos.getCategoria());
            Latitud.setText(gastos.getLatitud());
            Longitud.setText(gastos.getLongitud());
        }

        // Cambiar el ícono según la categoría
        switch (gastos.getCategoria()) {
            case "Arriendo o Hipoteca":
                IconCategoria.setImageResource(R.drawable.cat_arriendo);
                break;
            case "Alimentación":
                IconCategoria.setImageResource(R.drawable.cat_alimentacion);
                break;
            case "Transporte":
                IconCategoria.setImageResource(R.drawable.cat_transporte);
                break;
            case "Servicios básicos":
                IconCategoria.setImageResource(R.drawable.cat_servicios);
                break;
            case "Educación":
                IconCategoria.setImageResource(R.drawable.cat_educacion);
                break;
            case "Deudas":
                IconCategoria.setImageResource(R.drawable.cat_deudas);
                break;
            case "Ahorros e Inversiones":
                IconCategoria.setImageResource(R.drawable.cat_ahorros);
                break;
            case "Otros":
                IconCategoria.setImageResource(R.drawable.cat_otros);
                break;
            default:
                IconCategoria.setImageResource(R.drawable.cat_otros);
                break;
        }

        final boolean[] ready = {false};

        Editar.setOnClickListener(view -> {

            Editar.setVisibility(View.INVISIBLE);
            Eliminar.setVisibility(View.INVISIBLE);
            Guardar.setVisibility(View.VISIBLE);

            Nombre.setEnabled(true);
            Monto.setEnabled(true);

            Guardar.setOnClickListener(view1 -> {
                if(!Nombre.getText().toString().equals("")&&!Monto.getText().toString().equals("")){

                    Editable nom = (Nombre.getText());
                    Editable mon = (Monto.getText());

                    ready[0] = dbGastos.editProducto(id,nom,mon);

                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

                    if (ready[0]) {
                        Toast.makeText(DescripcionGastos.this, "Modificacion realizada", Toast.LENGTH_SHORT).show();
                        Nombre.setEnabled(false);
                        Monto.setEnabled(false);
                        Guardar.setVisibility(View.INVISIBLE);
                        Editar.setVisibility(View.VISIBLE);
                        Eliminar.setVisibility(View.VISIBLE);

                    }else {
                        Toast.makeText(DescripcionGastos.this, "Error al modificar su Producto", Toast.LENGTH_SHORT).show();
                        Nombre.setEnabled(false);
                        Monto.setEnabled(false);
                        Guardar.setVisibility(View.INVISIBLE);
                        Editar.setVisibility(View.VISIBLE);
                        Eliminar.setVisibility(View.VISIBLE);
                    }
                }else {
                    Toast.makeText(DescripcionGastos.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_SHORT).show();
                    Nombre.setEnabled(false);
                    Monto.setEnabled(false);
                    Guardar.setVisibility(View.INVISIBLE);
                    Editar.setVisibility(View.VISIBLE);
                    Eliminar.setVisibility(View.VISIBLE);
                }
            });

        });
        DBHelper dbHelper = new DBHelper(this);
        DB = dbHelper.getWritableDatabase();

        Eliminar.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(DescripcionGastos.this);
            builder.setMessage("¿Desea eliminar este producto?").setPositiveButton("Si", (dialog, i) -> {
                if(dbGastos.eliminarGastos(id)){
                    setResult(Activity.RESULT_OK); // Envía el resultado al fragmento
                    finish();
                }
            }).setNegativeButton("No", (dialog, i) -> {

            }).show();
        });

        Volver = findViewById(R.id.Volver);
        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Regresar al fragmento anterior
            }
        });

    }
}