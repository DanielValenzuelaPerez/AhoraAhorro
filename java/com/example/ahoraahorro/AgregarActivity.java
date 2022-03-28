package com.example.ahoraahorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

public class AgregarActivity extends AppCompatActivity {

    EditText et_cantidad;
    ToggleButton tg_metodo;
    EditText et_notas;
    Spinner sp_agregar_categoria;
    Button btn_agregar_agregar;
    Button btn_agregar_cancelar;

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        et_cantidad = findViewById(R.id.et_cantidad);
        tg_metodo = findViewById(R.id.tg_metodo);
        et_notas = findViewById(R.id.et_notas);
        sp_agregar_categoria = findViewById(R.id.sp_categoria);
        btn_agregar_agregar = findViewById(R.id.btn_agregar_agregar);
        btn_agregar_cancelar = findViewById(R.id.btn_agregar_cancelar);

        dataBaseHelper = new DataBaseHelper(AgregarActivity.this);

        //Spinner categoría
        ArrayAdapter<CharSequence> spinner_adapter = ArrayAdapter.createFromResource(AgregarActivity.this, R.array.categorias, android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_agregar_categoria.setAdapter(spinner_adapter);

        btn_agregar_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovimientosModel movimientosModel;
                try {
                    movimientosModel = new MovimientosModel(Double.parseDouble(et_cantidad.getText().toString()),  et_notas.getText().toString(), sp_agregar_categoria.getSelectedItemPosition() + 1, tg_metodo.isChecked());
                    Toast.makeText(AgregarActivity.this, movimientosModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    movimientosModel = new MovimientosModel(-1, "error", -1, false);
                    Toast.makeText(AgregarActivity.this, "Error al crear movimiento", Toast.LENGTH_SHORT).show();
                }
                //DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                boolean successAgregarMovimiento = dataBaseHelper.agregarMovimiento(movimientosModel);
                Toast.makeText(AgregarActivity.this, successAgregarMovimiento ? "Movimiento agregado" : "Error al agregar movimiento", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btn_agregar_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}