package ariel.fuentes.presupuestoslircayhub.Menu_Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import ariel.fuentes.presupuestoslircayhub.Adaptador_Gastos;
import ariel.fuentes.presupuestoslircayhub.CrearGastos;
import ariel.fuentes.presupuestoslircayhub.DB.DBGastos;
import ariel.fuentes.presupuestoslircayhub.Entidades.Gastos;
import ariel.fuentes.presupuestoslircayhub.R;

public class GastosFragment extends Fragment {

    private RecyclerView recyclerView;
    private Adaptador_Gastos adaptadorGastos;

    private static final int REQUEST_CODE_CREAR_GASTOS = 1;
    private static final int REQUEST_CODE_EDITAR_GASTOS = 2;
    private static final int RESULT_CODE_MODIFICACION = 3; // Nuevo código de resultado


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gastos, container, false);

        recyclerView = view.findViewById(R.id.ListaGastos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Gastos> listaGastos = obtenerListaGastos();
        adaptadorGastos = new Adaptador_Gastos(getActivity(), listaGastos);
        recyclerView.setAdapter(adaptadorGastos);

        FloatingActionButton agregarGastosButton = view.findViewById(R.id.AgregarGastos);
        agregarGastosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IrCrearGastos();
            }
        });

        return view;
    }

    private ArrayList<Gastos> obtenerListaGastos() {
        DBGastos dbGastos = new DBGastos(getActivity());
        return dbGastos.mostrardatos();
    }

    public void IrCrearGastos() {
        Intent intent = new Intent(getActivity(), CrearGastos.class);
        startActivityForResult(intent, REQUEST_CODE_CREAR_GASTOS);
    }

    private void actualizarListaGastos() {
        ArrayList<Gastos> listaGastos = obtenerListaGastos();
        adaptadorGastos.actualizarLista(listaGastos);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CREAR_GASTOS && resultCode == Activity.RESULT_OK) {
            actualizarListaGastos();
        } else if (requestCode == REQUEST_CODE_EDITAR_GASTOS && resultCode == RESULT_CODE_MODIFICACION) {
            actualizarListaGastos();
        }
    }

}
