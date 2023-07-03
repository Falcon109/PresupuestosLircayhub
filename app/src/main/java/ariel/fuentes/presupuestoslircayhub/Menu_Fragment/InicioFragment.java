package ariel.fuentes.presupuestoslircayhub.Menu_Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import ariel.fuentes.presupuestoslircayhub.R;

public class InicioFragment extends Fragment {

    private TextView textViewPresupuestoTotal;
    private TextView textViewResumenArriendo;
    private TextView textViewResumenAlimentacion;
    private TextView textViewResumenTransporte;
    private TextView textViewResumenSerBasicos;
    private TextView textViewResumenEducacion;
    private TextView textViewResumenDeudas;
    private TextView textViewResumenAhorros;
    private TextView textViewResumenOtros;

    public InicioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        textViewPresupuestoTotal = view.findViewById(R.id.PresupuestoTotal);
        textViewResumenArriendo = view.findViewById(R.id.ResumenArriendo);
        textViewResumenAlimentacion = view.findViewById(R.id.ResumenAlimentacion);
        textViewResumenTransporte = view.findViewById(R.id.ResumenTransporte);
        textViewResumenSerBasicos = view.findViewById(R.id.ResumenSerBasicos);
        textViewResumenEducacion = view.findViewById(R.id.ResumenEducacion);
        textViewResumenDeudas = view.findViewById(R.id.ResumenDeudas);
        textViewResumenAhorros = view.findViewById(R.id.ResumenAhorros);
        textViewResumenOtros = view.findViewById(R.id.ResumenOtros);

        // Obtener la instancia de SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());

        // Obtener los valores de las preferencias y establecer los textos de los TextViews
        String presupuestoTotal = sharedPreferences.getString("presupuestoTotal", "0");
        String resumenArriendo = sharedPreferences.getString("PresuArriedo", "0");
        String resumenAlimentacion = sharedPreferences.getString("PresuAlimentacion", "0");
        String resumenTransporte = sharedPreferences.getString("PresuTransporte", "0");
        String resumenSerBasico = sharedPreferences.getString("PresuServBasicos", "0");
        String resumenEducacion = sharedPreferences.getString("PresuEducacion", "0");
        String resumenDeudas = sharedPreferences.getString("PresuDeudas", "0");
        String resumenAhorro = sharedPreferences.getString("PresuAhorro", "0");
        String resumenOtros = sharedPreferences.getString("PresuOtros", "0");



        textViewPresupuestoTotal.setText("Presupuesto Total: "+ presupuestoTotal);
        textViewResumenArriendo.setText(resumenArriendo + " / ");
        textViewResumenAlimentacion.setText(resumenAlimentacion + " / ");
        textViewResumenTransporte.setText(resumenTransporte + " / ");
        textViewResumenSerBasicos.setText(resumenSerBasico + " / ");
        textViewResumenEducacion.setText(resumenEducacion + " / ");
        textViewResumenDeudas.setText(resumenDeudas + " / ");
        textViewResumenAhorros.setText(resumenAhorro + " / ");
        textViewResumenOtros.setText(resumenOtros + " / ");

        return view;
    }
}
