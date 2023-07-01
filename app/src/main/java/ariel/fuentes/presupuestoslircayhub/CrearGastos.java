package ariel.fuentes.presupuestoslircayhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.Manifest;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ariel.fuentes.presupuestoslircayhub.DB.DBGastos;

public class CrearGastos extends AppCompatActivity {

    private TextView Latitud, Longitud, Fecha;
    private EditText Nombre, Monto;
    private Button btnFecha, btnGuardar;
    private Spinner spicategorias;

    FloatingActionButton Volver;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_gastos);

        //Parte donde se implementara la extraccion de fecha
        Fecha=findViewById(R.id.numerofecha);
        Nombre=findViewById(R.id.nombregastos);
        Monto=findViewById(R.id.numeromonto);
        Latitud = (TextView)findViewById(R.id.numlatitud);
        Longitud = (TextView)findViewById(R.id.numlongitud);
        btnGuardar=findViewById(R.id.btnGuardar);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck == PackageManager.PERMISSION_DENIED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){

            } else {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                Latitud.setText(""+location.getLatitude());
                Longitud.setText(""+location.getLongitude());

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        spicategorias = (Spinner) findViewById(R.id.nomcategoria);

        ArrayAdapter<String> adaptspinner = new ArrayAdapter<>(this, R.layout.item_spiner , getResources().getStringArray(R.array.LisCategorias));
        spicategorias.setAdapter(adaptspinner);

        Button cancelarButton = findViewById(R.id.cancelar);
        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Regresar al fragmento anterior
            }
        });

        spicategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String categoriaSeleccionada = parent.getItemAtPosition(position).toString();
                actualizarImagenCategoria(categoriaSeleccionada);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Manejar caso de no selección, si es necesario
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombreGasto = Nombre.getText().toString();
                String fechaGasto = Fecha.getText().toString();
                int monto = Integer.valueOf(Monto.getText().toString());
                String latitud = Latitud.getText().toString();
                String longitud = Longitud.getText().toString();
                String categoria = spicategorias.getSelectedItem().toString();

                DBGastos dbGastos = new DBGastos(getApplicationContext());
                boolean checkInsertGasto = dbGastos.inserGastos(nombreGasto, monto, fechaGasto, latitud, longitud, categoria);
                if (checkInsertGasto) {
                    Toast.makeText(CrearGastos.this, "Gasto guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                    setResult(RESULT_OK); // Establecer el resultado como RESULT_OK
                    finish(); // Finalizar la actividad actual
                } else {
                    Toast.makeText(CrearGastos.this, "Error al guardar el gasto", Toast.LENGTH_LONG).show();
                }
            }
        });

        Volver = findViewById(R.id.Volver);
        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Regresar al fragmento anterior
            }
        });

    }

    private void limpiar(){
        Nombre.setText("");
        Monto.setText("");
        Latitud.setText("");
        Longitud.setText("");
        Fecha.setText("");
    }

    //metodo para la extraccion de fecha
    public void mostrarcalendario(View view){
        DatePickerDialog date = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Fecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        },2023,0,1);
        date.show();
    }

    private void actualizarImagenCategoria(String categoria) {
        ImageView imageView = findViewById(R.id.imagengastos);

        switch (categoria) {
            case "Arriendo o Hipoteca":
                imageView.setImageResource(R.drawable.cat_arriendo);
                break;
            case "Alimentación":
                imageView.setImageResource(R.drawable.cat_alimentacion);
                break;
            case "Transporte":
                imageView.setImageResource(R.drawable.cat_transporte);
                break;
            case "Servicios básicos":
                imageView.setImageResource(R.drawable.cat_servicios);
                break;
            case "Educación":
                imageView.setImageResource(R.drawable.cat_educacion);
                break;
            case "Deudas":
                imageView.setImageResource(R.drawable.cat_deudas);
                break;
            case "Ahorros e Inversiones":
                imageView.setImageResource(R.drawable.cat_ahorros);
                break;
            case "Otros":
                imageView.setImageResource(R.drawable.cat_otros);
                break;
            default:
                imageView.setImageResource(R.drawable.cat_otros);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Agrega cualquier otra lógica que necesites al regresar al fragmento de Gastos
    }
}
