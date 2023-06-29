package ariel.fuentes.presupuestoslircayhub.Menu_Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ariel.fuentes.presupuestoslircayhub.R;

public class IndicadoresFragment extends Fragment implements View.OnClickListener {

    private TextView text;
    private RequestQueue requestQueue;
    private Button btnUF;
    private Button btnDolar;
    private Button btnFeriado;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_indicadores, container, false);

        text = view.findViewById(R.id.TextHttp);
        text.setMovementMethod(new ScrollingMovementMethod());
        btnUF = view.findViewById(R.id.btnUF);
        btnDolar = view.findViewById(R.id.btnDolar);
        btnFeriado = view.findViewById(R.id.btnFeriado);

        btnUF.setOnClickListener(this);
        btnDolar.setOnClickListener(this);
        btnFeriado.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnUF:
                recuperarUF();
                break;
            case R.id.btnDolar:
                recuperarDolar();
                break;
            case R.id.btnFeriado:
                recuperarFeriado();
                break;
        }
    }

    private void recuperarUF() {
        text.setText("");
        String url = "https://mindicador.cl/api/uf";
        requestQueue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest requerimientoUF = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray serie = response.getJSONArray("serie");
                            for (int i = 0; i < serie.length(); i++) {
                                JSONObject object = serie.getJSONObject(i);
                                text.append("Fecha: " + object.getString("fecha") + "\n");
                                text.append("Valor: " + object.getDouble("valor") + "\n");
                                text.append(" \n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error de la solicitud aquí
                        text.setText("Error en la solicitud");
                    }
                });

        // Agregar la solicitud a la cola
        requestQueue.add(requerimientoUF);
    }


    private void recuperarDolar() {
        text.setText("");
        String url = "https://mindicador.cl/api/dolar";
        requestQueue = Volley.newRequestQueue(getActivity());

        JsonObjectRequest requerimientoUF = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray serie = response.getJSONArray("serie");
                            for (int i = 0; i < serie.length(); i++) {
                                JSONObject object = serie.getJSONObject(i);
                                text.append("Fecha: " + object.getString("fecha") + "\n");
                                text.append("Valor: " + object.getDouble("valor") + "\n");
                                text.append(" \n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error de la solicitud aquí
                        text.setText("Error en la solicitud");
                    }
                });

        // Agregar la solicitud a la cola
        requestQueue.add(requerimientoUF);
    }


    private void recuperarFeriado() {
        text.setText("");
        String url = "https://apis.digital.gob.cl/fl/feriados/2023";
        requestQueue = Volley.newRequestQueue(getActivity());

        JsonArrayRequest requerimiento = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int f = 0; f < response.length(); f++) {
                            try {
                                JSONObject object = new JSONObject(response.get(f).toString());
                                text.append("Nombre: " + object.getString("nombre") + "\n");
                                text.append("Comentarios: " + object.getString("comentarios") + "\n");
                                text.append("Fecha: " + object.getString("fecha") + "\n");
                                text.append("Irrenunciable: " + object.getString("irrenunciable") + "\n");
                                text.append("Tipo: " + object.getString("tipo") + "\n");
                                text.append(" \n");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error de la solicitud aquí
                        text.setText("Error en la solicitud");
                    }
                });

        // Agregar la solicitud a la cola
        requestQueue.add(requerimiento);
    }
}

