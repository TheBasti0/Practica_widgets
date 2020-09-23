package com.urrutia.practica_widgets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etNombre;
    private Button btnValorar;
    private RadioGroup radioGroup;
    private CheckBox cbRecomendado;
    private RatingBar ratingBarV;
    private boolean NombreOK = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNombre = findViewById(R.id.etNombre);
        btnValorar = findViewById(R.id.btnValorar);
        radioGroup = findViewById(R.id.radioGroup);
        cbRecomendado = findViewById(R.id.cbRecomendacion);
        ratingBarV = findViewById(R.id.ratingBarV);
        // Estas opciones estan desabilitadas para no poder usarse antes de seleccionar una opcion
        ratingBarV.setEnabled(false);
        btnValorar.setEnabled(false);
        cbRecomendado.setEnabled(false);

        // Utilizar el radioGroup para poder valorar las diferentes opciones presentes
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rbSi){
                    ratingBarV.setEnabled(true);
                    cbRecomendado.setEnabled(true);
                }else{
                    ratingBarV.setRating(0);
                    ratingBarV.setEnabled(false);
                    cbRecomendado.setChecked(false);
                    cbRecomendado.setEnabled(false);
                }
                if(NombreOK == true){
                    btnValorar.setEnabled(true);
                }

            }
        });

        etNombre.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Toast.makeText(MainActivity.this,"Texto : "+ etNombre.getText().toString(),Toast.LENGTH_SHORT).show();

                if(etNombre.getText().toString().length() < 3){
                    etNombre.setError("El nombre es muy corto");
                }else{
                    NombreOK = true;
                }

                return false;
            }
        });
    }
}