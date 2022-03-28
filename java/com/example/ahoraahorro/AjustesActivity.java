package com.example.ahoraahorro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AjustesActivity extends AppCompatActivity {

    EditText et_efectivo, et_tarjeta, et_meta, et_ingreso, et_presupuesto;
    TextView tv_ahorros_total, tv_ajustes_proyeccion;
    Spinner spinner_periodicidad;
    Button btn_guardar, btn_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        et_efectivo = findViewById(R.id.et_efectivo);
        et_tarjeta = findViewById(R.id.et_tarjeta);
        et_meta = findViewById(R.id.et_meta);
        spinner_periodicidad = findViewById(R.id.spinner_periodicidad);
        et_ingreso = findViewById(R.id.et_ingreso);
        et_presupuesto = findViewById(R.id.et_presupuesto);
        btn_guardar = findViewById(R.id.btn_guardar);
        btn_cancelar = findViewById(R.id.btn_cancelar);
        tv_ahorros_total = findViewById(R.id.tv_ahorros_total);
        tv_ajustes_proyeccion = findViewById(R.id.tv_ajustes_proyeccion);

        //Spinner periodicidad
        ArrayAdapter<CharSequence> spinner_adapter = ArrayAdapter.createFromResource(AjustesActivity.this, R.array.periodicidad, android.R.layout.simple_spinner_item);
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_periodicidad.setAdapter(spinner_adapter);

        //Match data from table ajustes in DB
        DataBaseHelper dataBaseHelper = new DataBaseHelper(AjustesActivity.this);
        AjustesModel ajustesModel = dataBaseHelper.getAjustesFromDB();

        et_efectivo.setHint(et_efectivo.getHint() +" ($" + String.valueOf(ajustesModel.getAhorros_efectivo()) + ")");
        et_tarjeta.setHint(et_tarjeta.getHint() +" ($" + String.valueOf(ajustesModel.getAhorros_tarjeta()) + ")");
        double total = ajustesModel.getAhorros_efectivo() + ajustesModel.getAhorros_tarjeta();
        tv_ahorros_total.setText(String.valueOf(total));
        et_meta.setHint(et_meta.getHint() +" ($" + String.valueOf(ajustesModel.getMeta()) + ")");
        et_ingreso.setHint(et_ingreso.getHint() +" ($" + String.valueOf(ajustesModel.getIngreso()) + ")");
        spinner_periodicidad.setSelection(ajustesModel.getId_periodicidad() - 1);
        et_presupuesto.setHint(et_presupuesto.getHint() +" ($" + String.valueOf(ajustesModel.getPresupuesto()) + ")");

        et_efectivo.setText(String.valueOf(ajustesModel.getAhorros_efectivo()));
        et_tarjeta.setText(String.valueOf(ajustesModel.getAhorros_tarjeta()));
        et_meta.setText(String.valueOf(ajustesModel.getMeta()));
        et_ingreso.setText(String.valueOf(ajustesModel.getIngreso()));
        spinner_periodicidad.setSelection(ajustesModel.getId_periodicidad() - 1);
        et_presupuesto.setText(String.valueOf(ajustesModel.getPresupuesto()));

        //meta - total / (ingreso - presupuesto)
        double proyeccion = (ajustesModel.getMeta() - total) / (ajustesModel.getIngreso() - ajustesModel.getPresupuesto());
        tv_ajustes_proyeccion.setText("Según tus ajustes, alcanzarás\ntu meta en " + (int)Math.ceil(proyeccion) + " periodos");

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double ahorros_efectivo = Double.parseDouble(et_efectivo.getText().toString());
                double ahorros_tarjeta = Double.parseDouble(et_tarjeta.getText().toString());
                double meta = Double.parseDouble(et_meta.getText().toString());
                double ingreso = Double.parseDouble(et_ingreso.getText().toString());
                int id_periodicidad = spinner_periodicidad.getSelectedItemPosition() + 1;
                double presupuesto= Double.parseDouble(et_presupuesto.getText().toString());

                AjustesModel ajustesModel = new AjustesModel(ahorros_efectivo, ahorros_tarjeta, meta, ingreso, presupuesto, id_periodicidad);

                DataBaseHelper dataBaseHelper = new DataBaseHelper(AjustesActivity.this);
                //dataBaseHelper.actualizarAjustes(ahorros_efectivo,ahorros_tarjeta,meta,ingreso,id_periodicidad,presupuesto);
                boolean success = dataBaseHelper.actualizarAjustes(ajustesModel);
                //Toast.makeText(AjustesActivity.this, ajustesModel.toString(), Toast.LENGTH_LONG).show();

                finish();
            }
        });
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}