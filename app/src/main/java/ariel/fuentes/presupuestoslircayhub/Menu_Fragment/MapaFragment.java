package ariel.fuentes.presupuestoslircayhub.Menu_Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ariel.fuentes.presupuestoslircayhub.DB.DBGastos;
import ariel.fuentes.presupuestoslircayhub.Entidades.Gastos;
import ariel.fuentes.presupuestoslircayhub.R;

public class MapaFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Map<Marker, Gastos> markerGastosMap;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mapa, container, false);

        FrameLayout mapContainer = rootView.findViewById(R.id.map_container);
        SupportMapFragment mapFragment = new SupportMapFragment();
        getChildFragmentManager().beginTransaction()
                .replace(R.id.map_container, mapFragment)
                .commit();

        markerGastosMap = new HashMap<>();

        mapFragment.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Verificar los permisos de ubicación
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Habilitar la capa de ubicación
            mMap.setMyLocationEnabled(true);

            // Obtener la ubicación actual del dispositivo
            FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                // Obtener las coordenadas de la ubicación actual
                                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

                                // Mover la cámara al marcador de ubicación actual con un zoom de 15
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 20));
                            }
                        }
                    });
        } else {
            // Solicitar permisos de ubicación al usuario si no están otorgados
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }

        // Obtener los gastos desde la base de datos
        DBGastos dbGastos = new DBGastos(getActivity());
        ArrayList<Gastos> listaGastos = dbGastos.mostrardatos();

        // Agregar marcadores en el mapa para cada gasto
        for (Gastos gasto : listaGastos) {
            LatLng ubicacion = new LatLng(Double.parseDouble(gasto.getLatitud()), Double.parseDouble(gasto.getLongitud()));
            MarkerOptions markerOptions = new MarkerOptions().position(ubicacion).title(gasto.getNombre());
            Marker marker = mMap.addMarker(markerOptions);

            // Asociar el gasto con el marcador
            markerGastosMap.put(marker, gasto);

            // Establecer el icono según la categoría del gasto
            Bitmap icon = getMarkerIconFromDrawable(gasto.getCategoria());
            if (icon != null) {
                marker.setIcon(BitmapDescriptorFactory.fromBitmap(icon));
            }
        }

        // Configurar el listener para los marcadores
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Obtener el gasto asociado al marcador
                Gastos gasto = markerGastosMap.get(marker);
                if (gasto != null) {
                    // Mostrar los detalles del gasto según sea necesario
                    // Por ejemplo, abrir una ventana de información con los detalles del gasto
                }
                return true;
            }
        });
    }

    private Bitmap getMarkerIconFromDrawable(String categoria) {
        int drawableId;
        switch (categoria) {
            case "Seleciones una categoria":
                drawableId = R.drawable.cat_ninguna;
                break;
            case "Arriendo o Hipoteca":
                drawableId = R.drawable.cat_arriendo;
                break;
            case "Alimentación":
                drawableId = R.drawable.cat_alimentacion;
                break;
            case "Transporte":
                drawableId = R.drawable.cat_transporte;
                break;
            case "Servicios básicos":
                drawableId = R.drawable.cat_servicios;
                break;
            case "Educación":
                drawableId = R.drawable.cat_educacion;
                break;
            case "Deudas":
                drawableId = R.drawable.cat_deudas;
                break;
            case "Ahorros e Inversiones":
                drawableId = R.drawable.cat_ahorros;
                break;
            case "Otros":
                drawableId = R.drawable.cat_otros;
                break;
            default:
                drawableId = R.drawable.cat_ninguna;
                break;
        }

        return BitmapFactory.decodeResource(getResources(), drawableId);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso de ubicación otorgado
                onMapReady(mMap);
            }
        }
    }
}

