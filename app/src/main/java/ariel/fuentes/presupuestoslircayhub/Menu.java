package ariel.fuentes.presupuestoslircayhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ariel.fuentes.presupuestoslircayhub.Menu_Fragment.GastosFragment;
import ariel.fuentes.presupuestoslircayhub.Menu_Fragment.IndicadoresFragment;
import ariel.fuentes.presupuestoslircayhub.Menu_Fragment.InicioFragment;
import ariel.fuentes.presupuestoslircayhub.Menu_Fragment.MapaFragment;

public class Menu extends AppCompatActivity {
    InicioFragment MenuInicio = new InicioFragment();
    MapaFragment MenuMapa = new MapaFragment();
    IndicadoresFragment MenuAnalisis = new IndicadoresFragment();
    GastosFragment MenuGastos = new GastosFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(MenuInicio);

        FloatingActionButton floatingActionButton = findViewById(R.id.configuraciones);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.MenuInicio:
                    loadFragment(MenuInicio);
                    return true;
                case R.id.MenuMapa:
                    loadFragment(MenuMapa);
                    return true;
                case R.id.MenuAnalisis:
                    loadFragment(MenuAnalisis);
                    return true;
                case R.id.MenuGastos:
                    loadFragment(MenuGastos);
                    return true;
            }
            return false;
        }
    };

    public void loadFragment (Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_conrainer, fragment);
        transaction.commit();
    }

}