package ariel.fuentes.presupuestoslircayhub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Apartado_Gastos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartadogastos);
    }

    public void IrCrearGastos(View clic) {
        Intent IrCrearGastos = new Intent(this, CrearGastos.class);
        startActivity(IrCrearGastos);
    }

}