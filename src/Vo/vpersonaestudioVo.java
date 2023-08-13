/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vo;

import java.sql.Time;
import java.sql.Timestamp;


public class vpersonaestudioVo {
private String nombre;
private String examen;
private Time horainyeccion;
private Double molibdeno;
private Double tecnecio;
private int fila;
private int columna;
private Time horainicio;
private Time horafin;
private Timestamp fechaconfirmada;

    public vpersonaestudioVo(String nombre, String examen, Time horainyeccion, Double molibdeno, Double tecnecio, int fila, int columna, Time horainicio, Time horafin, Timestamp fechaconfirmada) {
        this.nombre = nombre;
        this.examen = examen;
        this.horainyeccion = horainyeccion;
        this.molibdeno = molibdeno;
        this.tecnecio = tecnecio;
        this.fila = fila;
        this.columna = columna;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.fechaconfirmada = fechaconfirmada;
    }

    public String getNombre() {
        return nombre;
    }

    public String getExamen() {
        return examen;
    }

    public Time getHorainyeccion() {
        return horainyeccion;
    }

    public Double getMolibdeno() {
        return molibdeno;
    }

    public Double getTecnecio() {
        return tecnecio;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Time getHorainicio() {
        return horainicio;
    }

    public Time getHorafin() {
        return horafin;
    }

    public Timestamp getFechaconfirmada() {
        return fechaconfirmada;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public void setHorainyeccion(Time horainyeccion) {
        this.horainyeccion = horainyeccion;
    }

    public void setMolibdeno(Double molibdeno) {
        this.molibdeno = molibdeno;
    }

    public void setTecnecio(Double tecnecio) {
        this.tecnecio = tecnecio;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setHorainicio(Time horainicio) {
        this.horainicio = horainicio;
    }

    public void setHorafin(Time horafin) {
        this.horafin = horafin;
    }

    public void setFechaconfirmada(Timestamp fechaconfirmada) {
        this.fechaconfirmada = fechaconfirmada;
    }

}
