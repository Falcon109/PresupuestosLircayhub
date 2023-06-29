package ariel.fuentes.presupuestoslircayhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CrearGastos extends AppCompatActivity {

    private TextView Latitud, Longitud, Fecha;
    private Button btnFecha;
    private Spinner spicategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_gastos);

        //Parte donde se implementara la extraccion de fecha
        Fecha=findViewById(R.id.numerofecha);

        Latitud = (TextView)findViewById(R.id.latitudeTextView);
        Longitud = (TextView)findViewById(R.id.longitudeTextView);

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

        spicategorias = (Spinner) findViewById(R.id.spicategorias);

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

    private List<String> obtenerDias() {
        List<String> dias = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            dias.add(String.valueOf(i));
        }
        return dias;
    }

    private void actualizarImagenCategoria(String categoria) {
        ImageView imageView = findViewById(R.id.imagendeproducto);

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
