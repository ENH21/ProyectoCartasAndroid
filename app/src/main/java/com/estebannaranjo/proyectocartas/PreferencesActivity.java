package com.estebannaranjo.proyectocartas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
/**
 * Actividad que gestiona los ajustes de la aplicacion
 */
public class PreferencesActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PreferenceFR());
        toolbar = findViewById(R.id.appbar);
        toolbar.setTitle("Ajustes");
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}