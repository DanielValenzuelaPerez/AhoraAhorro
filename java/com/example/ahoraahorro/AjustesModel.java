package com.example.ahoraahorro;

public class AjustesModel {
    private double ahorros_efectivo;
    private double ahorros_tarjeta;
    private double meta;
    private int id_periodo;
    private double ingreso;
    private double presupuesto;
    private int id_periodicidad;
    private String string_periodicidad;

    @Override
    public String toString() {

        return "Periodo: " + id_periodo +
                "\nEfectivo: " + ahorros_efectivo +
                "\nTarjeta: " + ahorros_tarjeta +
                "\nMeta: " + meta +
                "\nIngreso " + string_periodicidad +"(" + id_periodicidad +"): " + ingreso +
                "\nPresupuesto "+ string_periodicidad +"(" + id_periodicidad +"): " + presupuesto;
    }

    public AjustesModel() {
    }

    public AjustesModel(double ahorros_efectivo, double ahorros_tarjeta, double meta, double ingreso, double presupuesto, int id_periodicidad) {
        this.ahorros_efectivo = ahorros_efectivo;
        this.ahorros_tarjeta = ahorros_tarjeta;
        this.meta = meta;
        this.ingreso = ingreso;
        this.presupuesto = presupuesto;
        this.id_periodicidad = id_periodicidad;
    }

    public AjustesModel(double ahorros_efectivo, double ahorros_tarjeta, double meta, int id_periodo, double ingreso, double presupuesto, int id_periodicidad, String string_periodicidad) {
        this.ahorros_efectivo = ahorros_efectivo;
        this.ahorros_tarjeta = ahorros_tarjeta;
        this.meta = meta;
        this.id_periodo = id_periodo;
        this.ingreso = ingreso;
        this.presupuesto = presupuesto;
        this.id_periodicidad = id_periodicidad;
        this.string_periodicidad = string_periodicidad;
    }

    public double getAhorros_efectivo() {
        return ahorros_efectivo;
    }

    public void setAhorros_efectivo(double ahorros_efectivo) {
        this.ahorros_efectivo = ahorros_efectivo;
    }

    public double getAhorros_tarjeta() {
        return ahorros_tarjeta;
    }

    public void setAhorros_tarjeta(double ahorros_tarjeta) {
        this.ahorros_tarjeta = ahorros_tarjeta;
    }

    public double getMeta() {
        return meta;
    }

    public void setMeta(double meta) {
        this.meta = meta;
    }

    public int getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(int id_periodo) {
        this.id_periodo = id_periodo;
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public int getId_periodicidad() {
        return id_periodicidad;
    }

    public void setId_periodicidad(int id_periodicidad) {
        this.id_periodicidad = id_periodicidad;
    }

    public String getString_periodicidad() {
        return string_periodicidad;
    }

    public void setString_periodicidad(String string_periodicidad) {
        this.string_periodicidad = string_periodicidad;
    }
}
