package com.example.ahoraahorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Reference to all the controls on the layout
    TextView tv_ajustes;
    EditText et_gasto;
    Spinner spinner_categorias;
    ToggleButton tg_efectivo_tarjeta;
    Button btn_agregado_rapido;
    Button btn_agregado_persionalizado;
    ListView lv_movimientos;
    TextView tv_editar;
    Button btn_arqueo;

    MovimientosArrayAdapter movimientoArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_ajustes = findViewById(R.id.tv_ajustes);
        et_gasto = findViewById(R.id.et_gasto);
        spinner_categorias = findViewById(R.id.spinner_categoria);
        tg_efectivo_tarjeta = findViewById(R.id.tg_efectivo_tarjeta);
        btn_agregado_rapido = findViewById(R.id.btn_agregar);
        btn_agregado_persionalizado = findViewById(R.id.btn_agregar_personalizado);
        lv_movimientos = findViewById(R.id.lv_movimientos);
        tv_editar = findViewById(R.id.tv_editar);
        btn_arqueo = findViewById(R.id.btn_arqueo);

        //Spinner categoría
        ArrayAdapter<CharSequence> spinner_adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.categorias, android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_categorias.setAdapter(spinner_adapter);

        //Llenar lista de movimientos a partir de la base de datos
        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        ShowMovimientosOnListView(dataBaseHelper);

        //Button listeners
        btn_agregado_rapido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovimientosModel movimientosModel;
                try {
                    movimientosModel = new MovimientosModel(Double.parseDouble(et_gasto.getText().toString()), "", spinner_categorias.getSelectedItemPosition() + 1, tg_efectivo_tarjeta.isChecked());
                    Toast.makeText(MainActivity.this, movimientosModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    movimientosModel = new MovimientosModel(-1, "error", -1, false);
                    Toast.makeText(MainActivity.this, "Error al crear movimiento", Toast.LENGTH_SHORT).show();
                }

                et_gasto.setText("");
                boolean successAgregarMovimiento = dataBaseHelper.agregarMovimiento(movimientosModel);
                //Toast.makeText(MainActivity.this, successAgregarMovimiento ? "Movimiento agregado" : "Error al agregar movimiento", Toast.LENGTH_LONG).show();
                ShowMovimientosOnListView(dataBaseHelper);
            }
        });

        btn_agregado_persionalizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AgregarActivity.class);
                startActivityForResult(i, 222); //https://youtu.be/6GC3PxPrsuY
                //Toast.makeText(MainActivity.this, "Agregado personalizado", Toast.LENGTH_SHORT).show();
            }
        });

        btn_arqueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ArqueoActivity.class);
                startActivityForResult(i, 222);
                //Toast.makeText(MainActivity.this, "Arqueo", Toast.LENGTH_LONG).show();
            }
        });

        tv_ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AjustesActivity.class);
                startActivity(i);
                //Toast.makeText(MainActivity.this, "Ajustes", Toast.LENGTH_SHORT).show();
            }
        });
        tv_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Editar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ShowMovimientosOnListView(DataBaseHelper dataBaseHelper2) {
        movimientoArrayAdapter = new MovimientosArrayAdapter(MainActivity.this, R.layout.single_item, dataBaseHelper2.getMovimientos());
        lv_movimientos.setAdapter(movimientoArrayAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 222){
            if(resultCode == RESULT_OK){
                ShowMovimientosOnListView(dataBaseHelper);
            }
        }
    }
}