package ariel.fuentes.presupuestoslircayhub.Menu_Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ariel.fuentes.presupuestoslircayhub.CrearGastos;
import ariel.fuentes.presupuestoslircayhub.R;
public class GastosFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gastos, container, false);

        FloatingActionButton agregarGastosButton = view.findViewById(R.id.AgregarGastos);
        agregarGastosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IrCrearGastos();
            }
        });

        return view;
    }

    public void IrCrearGastos() {
        Intent intent = new Intent(getActivity(), CrearGastos.class);
        startActivity(intent);
    }

}