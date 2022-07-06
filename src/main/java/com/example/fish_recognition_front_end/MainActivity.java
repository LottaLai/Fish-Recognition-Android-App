package com.example.fish_recognition_front_end;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.fish_recognition_front_end.ui.favorites.FavoritesFragment;
import com.example.fish_recognition_front_end.ui.home.HomeFragment;
import com.example.fish_recognition_front_end.ui.recipes.RecipesFragment;
import com.example.fish_recognition_front_end.ui.recognition.RecognitionsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fish_recognition_front_end.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;
    private BottomNavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_favorites, R.id.navigation_recipes, R.id.navigation_logout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupWithNavController(navView, navController);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        navView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.navigation_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.navigation_favorites:
                    replaceFragment(new FavoritesFragment());
                    break;
                case R.id.navigation_recipes:
                    replaceFragment(new RecipesFragment());
                    break;
                case R.id.navigation_logout:
                    break;
            }
            return true;
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.takePhoto.getVisibility() == View.INVISIBLE) {
                    binding.takePhoto.setVisibility(View.VISIBLE);
                } else {
                    binding.takePhoto.setVisibility(View.INVISIBLE);
                }
                if (binding.selectPhoto.getVisibility() == View.INVISIBLE) {
                    binding.selectPhoto.setVisibility(View.VISIBLE);
                } else {
                    binding.selectPhoto.setVisibility(View.INVISIBLE);
                }
            }
        });

        binding.selectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navView.setSelectedItemId(R.id.navigation_recognitions);
                replaceFragment(new RecognitionsFragment());
            }
        });

        binding.takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navView.setSelectedItemId(R.id.navigation_recognitions);
                replaceFragment(new RecognitionsFragment());
            }
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_main, fragment);
        transaction.commit();
    }
}