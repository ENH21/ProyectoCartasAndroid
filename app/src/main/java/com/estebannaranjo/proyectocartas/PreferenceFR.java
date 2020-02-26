package com.estebannaranjo.proyectocartas;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class PreferenceFR extends PreferenceFragmentCompat {

    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferencias, rootKey);
    }
}