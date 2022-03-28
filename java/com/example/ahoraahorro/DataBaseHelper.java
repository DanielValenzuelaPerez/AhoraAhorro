package com.example.ahoraahorro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.RecoverySystem;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context) {
        super(context, "ahoraahorro.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREAR TABLAS
        String crearTablaPeriodo = "CREATE TABLE periodo(id_periodo INTEGER PRIMARY KEY AUTOINCREMENT, fecha_periodo DATETIME);";
        db.execSQL(crearTablaPeriodo);

        String crearTablaCategoria = "CREATE TABLE categoria(id_categoria INTEGER PRIMARY KEY AUTOINCREMENT, tipo_movimiento TEXT);";
        db.execSQL(crearTablaCategoria);

        String crearTablaMovimientos = "CREATE TABLE movimientos(\n" +
                "id_movimiento INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "cantidad REAL,\n" +
                "id_periodo INTEGER,\n" +
                "notas TEXT,\n" +
                "id_categoria INTEGER,\n" +
                "fecha_movimiento DATETIME,\n" +
                "metodo BOOLEAN,\n" +
                "FOREIGN KEY(id_periodo) REFERENCES periodo(id_periodo),\n" +
                "FOREIGN KEY(id_categoria) REFERENCES categoria(id_categoria)\n" +
                ");";
        db.execSQL(crearTablaMovimientos);

        String crearTablaPeriodicidadDePago = "CREATE TABLE periodicidaddepago( id_periodicidad INTEGER PRIMARY KEY AUTOINCREMENT, tipo_periodicidad TEXT);";
        db.execSQL(crearTablaPeriodicidadDePago);

        String crearTablaAjustes = "CREATE TABLE ajustes(\n" +
                "ahorros_efectivo REAL,\n" +
                "ahorros_tarjeta REAL,\n" +
                "meta REAL,\n" +
                "id_periodo INTEGER,\n" +
                "ingreso REAL,\n" +
                "presupuesto REAL,\n" +
                "id_periodicidad INTEGER,\n" +
                "FOREIGN KEY(id_periodo) REFERENCES periodo(id_periodo),\n" +
                "FOREIGN KEY(id_periodicidad) REFERENCES periodicidaddepago(id_periodicidad)\n" +
                ");";
        db.execSQL(crearTablaAjustes);

        String crearTablaResumen = "CREATE TABLE resumen(\n" +
                "id_periodo INTEGER,\n" +
                "g_efectivo REAL,\n" +
                "g_tarjeta REAL,\n" +
                "ingresos REAL,\n" +
                "presupuesto REAL,\n" +
                "sobr_presu REAL,\n" +
                "i_efectivo REAL,\n" +
                "i_tarjeta REAL,\n" +
                "i_total REAL,\n" +
                "meta REAL,\n" +
                "f_efectivo REAL,\n" +
                "f_tarjeta REAL,\n" +
                "f_total REAL,\n" +
                "s_efectivo REAL,\n" +
                "s_tarjeta REAL,\n" +
                "s_total REAL,\n" +
                "diferencia REAL,\n" +
                "ganancia REAL,\n" +
                "proyeccion1 REAL,\n" +
                "proyeccion2 REAL,\n" +
                "FOREIGN KEY(id_periodo) REFERENCES periodo(id_periodo)\n" +
                ");";
        db.execSQL(crearTablaResumen);

        //POPULAR TABLAS
        String popularCategoria = "INSERT INTO categoria(tipo_movimiento) VALUES ('Otros'), ('Comida'), ('Transporte'), ('Esenciales'), ('Ingresos'), ('Innecesarios'), ('Imprevistos'), ('Renta'), ('Luz'), ('Gas'), ('Regalo');";
        db.execSQL(popularCategoria);
        String popularPeriodicidadDePago = "INSERT INTO periodicidaddepago(tipo_periodicidad) VALUES ('Diario'), ('Semanal'), ('Quincenal'), ('Mensual');";
        db.execSQL(popularPeriodicidadDePago);
        String popularPeriodo = "INSERT INTO periodo(fecha_periodo) VALUES(DATETIME('now', 'localtime'));";
        db.execSQL(popularPeriodo);
        String popularAjustes = "INSERT INTO ajustes(ahorros_efectivo, ahorros_tarjeta, meta, id_periodo, ingreso, presupuesto, id_periodicidad) VALUES ( 0,0,0,(SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1),0,0,2);";
        db.execSQL(popularAjustes);
        String popularResumen = "INSERT INTO resumen(id_periodo) VALUES ((SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1));";
        db.execSQL(popularResumen);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean agregarMovimiento(MovimientosModel movimientosModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("cantidad", movimientosModel.getCantidad());
        cv.put("id_periodo", movimientosModel.getId_periodo());
        cv.put("notas", movimientosModel.getNotas());
        cv.put("id_categoria", movimientosModel.getId_categoria());
        cv.put("fecha_movimiento", movimientosModel.getFecha_movimiento());
        cv.put("metodo", movimientosModel.isMetodo());

        long movimientos = db.insert("movimientos", null, cv);
        if(movimientos == -1) return false;

        String queryString = "UPDATE movimientos " +
                "SET id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1), " +
                "fecha_movimiento = (SELECT strftime('%d / %m / %Y', DATETIME('now', 'localtime'))) " +
                "WHERE " +
                "id_movimiento = (SELECT id_movimiento FROM movimientos ORDER BY id_movimiento DESC LIMIT 1);";
        db.execSQL(queryString);

        return true;
    }

    public List<MovimientosModel> getMovimientos(){
        List<MovimientosModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM movimientos WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1);";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            do{
                int id_movimiento = cursor.getInt(0);
                double cantidad = cursor.getDouble(1);
                int id_periodo = cursor.getInt(2);
                String notas = cursor.getString(3);
                int id_categoria = cursor.getInt(4);
                String string_categoria = "error, couldn't get category";
                String fecha_movimiento = cursor.getString(5);
                boolean metodo = cursor.getInt(6) == 1 ? true : false;

                String qry_getCategoria = "SELECT tipo_movimiento FROM categoria WHERE id_categoria = " + id_categoria +";";
                Cursor cursor_categoria = db.rawQuery(qry_getCategoria, null);
                if(cursor_categoria.moveToFirst())
                        string_categoria = cursor.getString(0);
                cursor_categoria.close();

                MovimientosModel movimientosModel = new MovimientosModel(id_movimiento, cantidad, id_periodo, notas, id_categoria, string_categoria, fecha_movimiento, metodo);
                returnList.add(movimientosModel);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public boolean actualizarAjustes(AjustesModel ajustesModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("ahorros_efectivo", ajustesModel.getAhorros_efectivo());
        cv.put("ahorros_tarjeta", ajustesModel.getAhorros_tarjeta());
        cv.put("meta", ajustesModel.getMeta());
        cv.put("ingreso", ajustesModel.getIngreso());
        cv.put("presupuesto", ajustesModel.getPresupuesto());
        cv.put("id_periodicidad", ajustesModel.getId_periodicidad());

        int currentPeriod = -1;
        String qry_getCurrentPeriod = "SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1;";
        Cursor cursor = db.rawQuery(qry_getCurrentPeriod, null);
        if(cursor.moveToFirst())
            currentPeriod = cursor.getInt(0);
        cursor.close();

        int success = db.update("ajustes", cv, "id_periodo = ?", new String[]{String.valueOf(currentPeriod)});

        db.close();
        if(success == 1) return true;
        return false;
    }

    public AjustesModel getAjustesFromDB(){
        AjustesModel ajustesModel;

        String queryString = "SELECT *, (SELECT tipo_periodicidad FROM periodicidaddepago WHERE id_periodicidad = (SELECT id_periodicidad FROM ajustes WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1))) FROM ajustes WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1);";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){
            double ahorros_efectivo = cursor.getDouble(0);
            double ahorros_tarjeta = cursor.getDouble(1);
            double meta = cursor.getDouble(2);
            int id_periodo = cursor.getInt(3);
            double ingreso = cursor.getDouble(4);
            double presupuesto = cursor.getDouble(5);
            int id_periodicidad = cursor.getInt(6);
            String string_periodiciad = cursor.getString(7);

            ajustesModel = new AjustesModel(ahorros_efectivo, ahorros_tarjeta, meta, id_periodo, ingreso, presupuesto, id_periodicidad, string_periodiciad);
        }
        else
            ajustesModel = new AjustesModel(-1,-1,-1,-1,-1,-1,-1, "");
        cursor.close();
        db.close();

        return ajustesModel;
    }

    public void updateResumen(){
        String qry_updateResumen = "UPDATE resumen SET\n" +
                "g_efectivo = (SELECT TOTAL(cantidad) FROM movimientos WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) AND metodo = 1 AND id_categoria != 5),\n" +
                "g_tarjeta = (SELECT TOTAL(cantidad) FROM movimientos WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) AND metodo = 0 AND id_categoria != 5),\n" +
                "ingresos = (SELECT TOTAL(cantidad) FROM movimientos WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) AND id_categoria = 5),\n" +
                "presupuesto = (SELECT presupuesto FROM ajustes WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1)),\n" +
                "sobr_presu = (SELECT (SELECT presupuesto FROM ajustes WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1)) - TOTAL(cantidad) FROM movimientos WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) AND id_categoria != 5),\n" +
                "i_efectivo = (SELECT ahorros_efectivo FROM ajustes WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1)),\n" +
                "i_tarjeta = (SELECT ahorros_tarjeta FROM ajustes WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1)),\n" +
                "i_total = (SELECT ahorros_efectivo + ahorros_tarjeta FROM ajustes WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1)),\n" +
                "meta = (SELECT meta FROM ajustes WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1)),\n" +
                "s_efectivo = (SELECT (SELECT ahorros_efectivo FROM ajustes WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1)) - TOTAL(cantidad) + (SELECT TOTAL(cantidad) FROM movimientos WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) AND metodo = 1 AND id_categoria = 5) FROM movimientos WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) AND metodo = 1 AND id_categoria != 5),\n" +
                "s_tarjeta = (SELECT (SELECT ahorros_tarjeta FROM ajustes WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1)) - TOTAL(cantidad) + (SELECT TOTAL(cantidad) FROM movimientos WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) AND metodo = 0 AND id_categoria = 5) FROM movimientos WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) AND metodo = 0 AND id_categoria != 5),\n" +
                "s_total = (SELECT (SELECT ahorros_tarjeta + ahorros_efectivo FROM ajustes WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1)) - TOTAL(cantidad) + (SELECT TOTAL(cantidad) FROM movimientos WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) AND id_categoria = 5) FROM movimientos WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) AND id_categoria != 5),\n" +
                "proyeccion1 = (SELECT (meta - (ahorros_efectivo + ahorros_tarjeta)) / (ingreso - presupuesto) FROM ajustes WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1))" +
                "WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1);";
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL(qry_updateResumen);
        db.close();
    }

    public void updateResumen(double f_efectivo, double f_tarjeta){
        String qry_updateResumen = "UPDATE resumen SET\n" +
                "f_efectivo = " + f_efectivo + " ,\n" +
                "f_tarjeta = " + f_tarjeta + ",\n" +
                "f_total = (" + f_efectivo + " + " + f_tarjeta + "),\n" +
                "diferencia = (" + f_efectivo + " + " + f_tarjeta + ") - (SELECT s_total FROM resumen WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1)),\n" +
                "ganancia = (" + f_efectivo + " + " + f_tarjeta + ") - (SELECT i_total FROM resumen WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1)),\n" +
                "proyeccion2 = (SELECT (meta - (" + (f_efectivo + f_tarjeta) + ")) FROM resumen WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1)) / ((" + f_efectivo + " + " + f_tarjeta + ") - (SELECT i_total FROM resumen WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1)))\n" +
                "WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1);\n";
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL(qry_updateResumen);
        db.close();
    }

    public ResumenModel getResumen(){
        ResumenModel resumenModel;

        String qryGetResumen = "SELECT * FROM resumen WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1);";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(qryGetResumen, null);

        if(cursor.moveToFirst()){
            resumenModel = new ResumenModel(
                    cursor.getInt(0),
                    cursor.getDouble(1),
                    cursor.getDouble(2),
                    cursor.getDouble(3),
                    cursor.getDouble(4),
                    cursor.getDouble(5),
                    cursor.getDouble(6),
                    cursor.getDouble(7),
                    cursor.getDouble(8),
                    cursor.getDouble(9),
                    cursor.getDouble(10),
                    cursor.getDouble(11),
                    cursor.getDouble(12),
                    cursor.getDouble(13),
                    cursor.getDouble(14),
                    cursor.getDouble(15),
                    cursor.getDouble(16),
                    cursor.getDouble(17),
                    cursor.getDouble(18),
                    cursor.getDouble(19));
        }
        else
            resumenModel = new ResumenModel();

        cursor.close();
        db.close();
        return resumenModel;
    }

    public void nuevoPeriodo(){
        SQLiteDatabase db = this.getWritableDatabase();

        String nuevo_periodo = "INSERT INTO periodo(fecha_periodo) VALUES(DATETIME('now', 'localtime'));";
        db.execSQL(nuevo_periodo);

        String nuevos_ajustes = "INSERT INTO ajustes(ahorros_efectivo, ahorros_tarjeta, meta, id_periodo, ingreso, presupuesto, id_periodicidad) VALUES \n" +
                "((SELECT f_efectivo FROM resumen WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) - 1),\n" +
                "(SELECT f_tarjeta FROM resumen WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) - 1),\n" +
                "(SELECT meta FROM resumen WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) - 1),\n" +
                "(SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1),\n" +
                "(SELECT ingreso FROM ajustes WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) - 1),\n" +
                "(SELECT presupuesto FROM ajustes WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) - 1),\n" +
                "(SELECT id_periodicidad FROM ajustes WHERE id_periodo = (SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1) - 1));";
        db.execSQL(nuevos_ajustes);

        String nuevo_resumen = "INSERT INTO resumen(id_periodo) VALUES ((SELECT id_periodo FROM periodo ORDER BY id_periodo DESC LIMIT 1));";
        db.execSQL(nuevo_resumen);
    }
}
