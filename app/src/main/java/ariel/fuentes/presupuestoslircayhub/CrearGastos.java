package ariel.fuentes.presupuestoslircayhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class CrearGastos extends AppCompatActivity {

    private Spinner spicategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_gastos);

        spicategorias = (Spinner) findViewById(R.id.spicategorias);

        ArrayAdapter<String> adaptspinner = new ArrayAdapter<>(this, R.layout.item_spiner , getResources().getStringArray(R.array.LisCategorias));
        spicategorias.setAdapter(adaptspinner);

    }
}