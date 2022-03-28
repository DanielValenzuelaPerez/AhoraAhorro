package com.example.ahoraahorro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ArqueoActivity extends AppCompatActivity {

    //TextView test;
    TextView tv_periodo, tv_i_total, tv_i_efectivo, tv_i_tarjeta, tv_g_efectivo, tv_g_tarjeta, tv_g_total, tv_presupuesto, tv_ingresos, tv_s_efectivo, tv_s_tarjeta, tv_s_total, tv_f_total, tv_ganancia, tv_diferencia, tv_proyeccion;
    EditText efectivo;
    EditText tarjeta;
    Button btn_actualizar_arqueo;
    Button btn_confirmar_arqueo;
    Button btn_cancelar_arqueo;

    DataBaseHelper dataBaseHelper = new DataBaseHelper(ArqueoActivity.this);
    ResumenModel resumenModel;

    protected void actualizar_resumen(){
        dataBaseHelper = new DataBaseHelper(ArqueoActivity.this);
        dataBaseHelper.updateResumen();
        resumenModel = dataBaseHelper.getResumen();

        efectivo.setText(String.valueOf(resumenModel.getF_efectivo()));
        tarjeta.setText(String.valueOf(resumenModel.getF_tarjeta()));
        tv_periodo.setText("Periodo " + String.valueOf(resumenModel.getId_perido()));
        tv_i_total.setText("Iniciaste el periodo con $" + String.valueOf(resumenModel.getI_total()));
        tv_i_efectivo.setText("$" + String.valueOf(resumenModel.getI_efectivo()));
        tv_i_tarjeta.setText("$" + String.valueOf(resumenModel.getI_tarjeta()));
        tv_g_total.setText("Gastos de $" + String.valueOf(resumenModel.getG_efectivo() + resumenModel.getG_tarjeta()));
        tv_g_efectivo.setText("$" + String.valueOf(resumenModel.getG_efectivo()));
        tv_g_tarjeta.setText("$" + String.valueOf(resumenModel.getG_tarjeta()));
        tv_presupuesto.setText("Presupuesto $" + String.valueOf(resumenModel.getPresupuesto()));
        tv_ingresos.setText("Ingresos $" + String.valueOf(resumenModel.getIngresos()));
        tv_s_efectivo.setText("$" + String.valueOf(resumenModel.getS_efectivo()));
        tv_s_tarjeta.setText("$" + String.valueOf(resumenModel.getS_tarjeta()));
        tv_s_total.setText("$" + String.valueOf(resumenModel.getS_total()));
        efectivo.setText(String.valueOf(resumenModel.getF_efectivo()));
        tarjeta.setText(String.valueOf(resumenModel.getF_tarjeta()));
        tv_f_total.setText(String.valueOf(Double.valueOf(String.valueOf(efectivo.getText())) + Double.valueOf(String.valueOf(tarjeta.getText()))));
        tv_diferencia.setText("Diferencia de $" + String.valueOf(resumenModel.getDiferencia()));
        tv_ganancia.setText("Ganancia de $" + String.valueOf(resumenModel.getGanancia()));
        tv_proyeccion.setText("A este ritmo alcanzarás \ntu meta en " + String.valueOf((int)Math.ceil(resumenModel.getProyeccion2())) + " periodos");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arqueo);

        //test = findViewById(R.id.textView);
        tv_periodo = findViewById(R.id.tv_periodo);
        tv_i_total = findViewById(R.id.tv_i_total);
        tv_i_efectivo = findViewById(R.id.tv_i_efectivo);
        tv_i_tarjeta = findViewById(R.id.tv_i_tarjeta);
        tv_g_efectivo = findViewById(R.id.tv_g_efectivo);
        tv_g_tarjeta = findViewById(R.id.tv_g_tarjeta);
        tv_g_total = findViewById(R.id.tv_g_total);
        tv_presupuesto = findViewById(R.id.tv_presupuesto);
        tv_ingresos = findViewById(R.id.tv_ingresos);
        tv_s_efectivo = findViewById(R.id.tv_s_efectivo);
        tv_s_tarjeta = findViewById(R.id.tv_s_tarjeta);
        tv_s_total = findViewById(R.id.tv_s_total);
        tv_f_total = findViewById(R.id.tv_f_total);
        tv_ganancia = findViewById(R.id.tv_ganancia);
        tv_diferencia = findViewById(R.id.tv_diferencia);
        tv_proyeccion = findViewById(R.id.tv_proyeccion);
        efectivo = findViewById(R.id.et_cnt_efectivo);
        tarjeta = findViewById(R.id.et_cnt_tarjeta);
        btn_actualizar_arqueo = findViewById(R.id.btn_actualizar_arqueo);
        btn_confirmar_arqueo = findViewById(R.id.btn_confirmar_arqueo);
        btn_cancelar_arqueo = findViewById(R.id.btn_cancelar_arqueo);

        //Crear DetallesPeriodoModel
        //dataBaseHelper = new DataBaseHelper(ArqueoActivity.this);
        //dataBaseHelper.updateResumen();
        //resumenModel = dataBaseHelper.getResumen();

        actualizar_resumen();

        btn_actualizar_arqueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.updateResumen(Double.parseDouble(efectivo.getText().toString()), Double.parseDouble(tarjeta.getText().toString()));
                actualizar_resumen();
            }
        });
        btn_confirmar_arqueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.updateResumen(Double.parseDouble(efectivo.getText().toString()), Double.parseDouble(tarjeta.getText().toString()));
                dataBaseHelper.nuevoPeriodo();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        btn_cancelar_arqueo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Imprimir
        //test.setText(resumenModel.toString());
    }
}