package com.example.ahoraahorro;

public class MovimientosModel {
    private int id_movimiento;
    private double cantidad;
    private int id_periodo;
    private String notas;
    private int id_categoria;
    private String string_categoria;
    private String fecha_movimiento;
    private boolean metodo;

    @Override
    public String toString() {
        return id_movimiento + " [" + (metodo ? "EFECTIVO" : "TARJETA") + "] \t| $" + cantidad + " (" + string_categoria + ")";
    }

    public MovimientosModel() {
    }

    public MovimientosModel(double cantidad, String notas, int id_categoria, boolean metodo) {
        this.cantidad = cantidad;
        this.notas = notas;
        this.id_categoria = id_categoria;
        this.metodo = metodo;
    }

    public MovimientosModel(int id_movimiento, double cantidad, int id_periodo, String notas, int id_categoria, String string_categoria, String fecha_movimiento, boolean metodo) {
        this.id_movimiento = id_movimiento;
        this.cantidad = cantidad;
        this.id_periodo = id_periodo;
        this.notas = notas;
        this.id_categoria = id_categoria;
        this.string_categoria = string_categoria;
        this.fecha_movimiento = fecha_movimiento;
        this.metodo = metodo;
    }

    public int getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(int id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(int id_periodo) {
        this.id_periodo = id_periodo;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getString_categoria() {
        return string_categoria;
    }

    public void setString_categoria(String string_categoria) {
        this.string_categoria = string_categoria;
    }

    public String getFecha_movimiento() {
        return fecha_movimiento;
    }

    public void setFecha_movimiento(String fecha_movimiento) {
        this.fecha_movimiento = fecha_movimiento;
    }

    public boolean isMetodo() {
        return metodo;
    }

    public void setMetodo(boolean metodo) {
        this.metodo = metodo;
    }
}
