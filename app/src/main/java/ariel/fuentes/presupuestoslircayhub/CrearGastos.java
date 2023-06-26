package ariel.fuentes.presupuestoslircayhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

        Button cancelarButton = findViewById(R.id.cancelar);
        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Regresar al fragmento anterior
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Agrega cualquier otra lógica que necesites al regresar al fragmento de Gastos
    }

}