package ariel.fuentes.presupuestoslircayhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ariel.fuentes.presupuestoslircayhub.Menu_Fragment.FirstFragment;
import ariel.fuentes.presupuestoslircayhub.Menu_Fragment.FourthFragment;
import ariel.fuentes.presupuestoslircayhub.Menu_Fragment.SecondFragment;
import ariel.fuentes.presupuestoslircayhub.Menu_Fragment.ThirdFragment;

public class Menu extends AppCompatActivity {

    FirstFragment MenuAjustes = new FirstFragment();
    SecondFragment MenuMapa = new SecondFragment();
    ThirdFragment MenuAnalisis = new ThirdFragment();
    FourthFragment MenuGastos = new FourthFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(MenuAjustes);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.MenuAjustes:
                    loadFragment(MenuAjustes);
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