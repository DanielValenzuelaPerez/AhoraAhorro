package com.example.ahoraahorro;

public class ResumenModel {
    private int id_perido;
    private double g_efectivo;
    private double g_tarjeta;
    private double ingresos;
    private double presupuesto;
    private double sobr_presu;
    private double i_efectivo;
    private double i_tarjeta;
    private double i_total;
    private double meta;
    private double f_efectivo;
    private double f_tarjeta;
    private double f_total;
    private double s_efectivo;
    private double s_tarjeta;
    private double s_total;
    private double diferencia;
    private double ganancia;
    private double proyeccion1;
    private double proyeccion2;

    @Override
    public String toString() {
        return "ResmuenModel{" +
                "\nid_perido=" + id_perido +
                "\ng_efectivo=" + g_efectivo +
                "\ng_tarjeta=" + g_tarjeta +
                "\ningresos=" + ingresos +
                "\npresupuesto=" + presupuesto +
                "\nsobr_presu=" + sobr_presu +
                "\ni_efectivo=" + i_efectivo +
                "\ni_tarjeta=" + i_tarjeta +
                "\ni_total=" + i_total +
                "\nmeta=" + meta +
                "\nf_efectivo=" + f_efectivo +
                "\nf_tarjeta=" + f_tarjeta +
                "\nf_total=" + f_total +
                "\ns_efectivo=" + s_efectivo +
                "\ns_tarjeta=" + s_tarjeta +
                "\ns_total=" + s_total +
                "\ndiferencia=" + diferencia +
                "\nganancia=" + ganancia +
                "\nproyeccion1=" + proyeccion1 +
                "\nproyeccion2=" + proyeccion2 +
                '}';
    }

    public ResumenModel() {
    }

    public ResumenModel(int id_perido, double g_efectivo, double g_tarjeta, double ingresos, double presupuesto, double sobr_presu, double i_efectivo, double i_tarjeta, double i_total, double meta, double f_efectivo, double f_tarjeta, double f_total, double s_efectivo, double s_tarjeta, double s_total, double diferencia, double ganancia, double proyeccion1, double proyeccion2) {
        this.id_perido = id_perido;
        this.g_efectivo = g_efectivo;
        this.g_tarjeta = g_tarjeta;
        this.ingresos = ingresos;
        this.presupuesto = presupuesto;
        this.sobr_presu = sobr_presu;
        this.i_efectivo = i_efectivo;
        this.i_tarjeta = i_tarjeta;
        this.i_total = i_total;
        this.meta = meta;
        this.f_efectivo = f_efectivo;
        this.f_tarjeta = f_tarjeta;
        this.f_total = f_total;
        this.s_efectivo = s_efectivo;
        this.s_tarjeta = s_tarjeta;
        this.s_total = s_total;
        this.diferencia = diferencia;
        this.ganancia = ganancia;
        this.proyeccion1 = proyeccion1;
        this.proyeccion2 = proyeccion2;
    }

    public int getId_perido() {
        return id_perido;
    }

    public void setId_perido(int id_perido) {
        this.id_perido = id_perido;
    }

    public double getG_efectivo() {
        return g_efectivo;
    }

    public void setG_efectivo(double g_efectivo) {
        this.g_efectivo = g_efectivo;
    }

    public double getG_tarjeta() {
        return g_tarjeta;
    }

    public void setG_tarjeta(double g_tarjeta) {
        this.g_tarjeta = g_tarjeta;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public double getSobr_presu() {
        return sobr_presu;
    }

    public void setSobr_presu(double sobr_presu) {
        this.sobr_presu = sobr_presu;
    }

    public double getI_efectivo() {
        return i_efectivo;
    }

    public void setI_efectivo(double i_efectivo) {
        this.i_efectivo = i_efectivo;
    }

    public double getI_tarjeta() {
        return i_tarjeta;
    }

    public void setI_tarjeta(double i_tarjeta) {
        this.i_tarjeta = i_tarjeta;
    }

    public double getI_total() {
        return i_total;
    }

    public void setI_total(double i_total) {
        this.i_total = i_total;
    }

    public double getMeta() {
        return meta;
    }

    public void setMeta(double meta) {
        this.meta = meta;
    }

    public double getF_efectivo() {
        return f_efectivo;
    }

    public void setF_efectivo(double f_efectivo) {
        this.f_efectivo = f_efectivo;
    }

    public double getF_tarjeta() {
        return f_tarjeta;
    }

    public void setF_tarjeta(double f_tarjeta) {
        this.f_tarjeta = f_tarjeta;
    }

    public double getF_total() {
        return f_total;
    }

    public void setF_total(double f_total) {
        this.f_total = f_total;
    }

    public double getS_efectivo() {
        return s_efectivo;
    }

    public void setS_efectivo(double s_efectivo) {
        this.s_efectivo = s_efectivo;
    }

    public double getS_tarjeta() {
        return s_tarjeta;
    }

    public void setS_tarjeta(double s_tarjeta) {
        this.s_tarjeta = s_tarjeta;
    }

    public double getS_total() {
        return s_total;
    }

    public void setS_total(double s_total) {
        this.s_total = s_total;
    }

    public double getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(double diferencia) {
        this.diferencia = diferencia;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public double getProyeccion1() {
        return proyeccion1;
    }

    public void setProyeccion1(double proyeccion1) {
        this.proyeccion1 = proyeccion1;
    }

    public double getProyeccion2() {
        return proyeccion2;
    }

    public void setProyeccion2(double proyeccion2) {
        this.proyeccion2 = proyeccion2;
    }
}
