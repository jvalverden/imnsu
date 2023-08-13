/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vo;

import java.sql.Time;
import java.sql.Timestamp;

public class personatipoestudioVo {
private int id;
private int idpersona;
private int idtipoestudio;
private Timestamp fechahoraestudio;
private Time horainyeccion;    
private int idrecepciongenerador;
private float molibdeno;
private float tecnecio;
private int fila;
private int columna;
private Time horainicio;    
private Time horafin;    
private Timestamp fechaconfirmada;
    public personatipoestudioVo() {
    }

    public personatipoestudioVo(int id, int idpersona, int idtipoestudio, Timestamp fechahoraestudio, Time horainyeccion, int idrecepciongenerador, float molibdeno, float tecnecio, int fila, int columna, Time horainicio, Time horafin, Timestamp fechaconfirmada) {
        this.id = id;
        this.idpersona = idpersona;
        this.idtipoestudio = idtipoestudio;
        this.fechahoraestudio = fechahoraestudio;
        this.horainyeccion = horainyeccion;
        this.idrecepciongenerador = idrecepciongenerador;
        this.molibdeno = molibdeno;
        this.tecnecio = tecnecio;
        this.fila = fila;
        this.columna = columna;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.fechaconfirmada = fechaconfirmada;
    }

    public int getId() {
        return id;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public int getIdtipoestudio() {
        return idtipoestudio;
    }

    public Timestamp getFechahoraestudio() {
        return fechahoraestudio;
    }

    public Time getHorainyeccion() {
        return horainyeccion;
    }

    public int getIdrecepciongenerador() {
        return idrecepciongenerador;
    }

    public float getMolibdeno() {
        return molibdeno;
    }

    public float getTecnecio() {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public void setIdtipoestudio(int idtipoestudio) {
        this.idtipoestudio = idtipoestudio;
    }

    public void setFechahoraestudio(Timestamp fechahoraestudio) {
        this.fechahoraestudio = fechahoraestudio;
    }

    public void setHorainyeccion(Time horainyeccion) {
        this.horainyeccion = horainyeccion;
    }

    public void setIdrecepciongenerador(int idrecepciongenerador) {
        this.idrecepciongenerador = idrecepciongenerador;
    }

    public void setMolibdeno(float molibdeno) {
        this.molibdeno = molibdeno;
    }

    public void setTecnecio(float tecnecio) {
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
