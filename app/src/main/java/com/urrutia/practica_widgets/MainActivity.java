package com.urrutia.practica_widgets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private boolean RadioOK = false;
    private String RbSelect;

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

        btnValorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString().trim();
                float rating = ratingBarV.getRating();
                boolean recomienda = cbRecomendado.isChecked();

                if(RbSelect == "SI"){
                    String LoRecomienda;
                    if(recomienda==true){
                        LoRecomienda="SI";
                    }else{
                        LoRecomienda = "NO";
                    }
                    Toast.makeText(MainActivity.this, "Nombre: "+nombre+"\nConoce Firefox: "+RbSelect+
                            "\nRating: "+rating+"\nRecomiendas Firefox"+LoRecomienda,Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(MainActivity.this, "Nombre: "+nombre+"\nConoce Firefox: "+RbSelect,
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        // Utilizar el radioGroup para poder valorar las diferentes opciones presentes
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.rbSi){
                    ratingBarV.setEnabled(true);
                    cbRecomendado.setEnabled(true);
                    RbSelect = "SI";
                }else {
                    ratingBarV.setRating(0);
                    ratingBarV.setEnabled(false);
                    cbRecomendado.setChecked(false);
                    cbRecomendado.setEnabled(false);
                    RbSelect = "NO";
                }
                RadioOK = true;
                if(NombreOK == true){
                    btnValorar.setEnabled(true);
                }

            }
        });
        etNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Toast.makeText(MainActivity.this, "Texto: "+charSequence,Toast.LENGTH_SHORT).show();


                if(charSequence.toString().trim().length() < 3){
                    etNombre.setError("El Nombre Es Muy Corto");
                    NombreOK = false;
                    btnValorar.setEnabled(false);
                }else{
                    NombreOK = true;
                    if(RadioOK == true){
                        btnValorar.setEnabled(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}