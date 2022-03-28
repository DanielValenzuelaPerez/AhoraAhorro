package com.example.ahoraahorro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MovimientosArrayAdapter extends ArrayAdapter<MovimientosModel> {
    Context context;
    int resource;

    public MovimientosArrayAdapter(@NonNull Context context, int resource, @NonNull List<MovimientosModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id_movimiento = getItem(position).getId_movimiento();
        double cantidad = getItem(position).getCantidad();
        int id_periodo = getItem(position).getId_periodo();
        String notas = getItem(position).getNotas();
        int id_categoria = getItem(position).getId_categoria();
        String string_categoria = getItem(position).getString_categoria();
        String fecha = getItem(position).getFecha_movimiento();
        boolean metodo = getItem(position).isMetodo();

        int[] img_metodo = { R.drawable.credit_card, R.drawable.cash_icon};
        int[] img_categoria = {R.drawable.otros, R.drawable.comida, R.drawable.transporte, R.drawable.esenciales, R.drawable.pago, R.drawable.innecesarios, R.drawable.imprevistos, R.drawable.renta, R.drawable.luz, R.drawable.gas, R.drawable.regalo};

        MovimientosModel movimientosModel = new MovimientosModel(id_movimiento, cantidad, id_periodo, notas, id_categoria, string_categoria, fecha, metodo);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        ImageView tvTipo = (ImageView) convertView.findViewById(R.id.tipo_pago_lv);
        TextView tvFecha = (TextView) convertView.findViewById(R.id.fecha_lv);
        TextView tvCantidad = (TextView) convertView.findViewById(R.id.movimiento_lv);
        ImageView tvCategoria = (ImageView) convertView.findViewById(R.id.categoria_lv);

        tvTipo.setImageResource(img_metodo[metodo ? 1 : 0]);
        tvFecha.setText(fecha);
        if(id_categoria == 5) tvCantidad.setText("(" + String.valueOf(cantidad) + ")");
        else tvCantidad.setText(String.valueOf(cantidad));
        tvCategoria.setImageResource(img_categoria[id_categoria-1]);

        return convertView;
    }
}
