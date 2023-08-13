/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vo;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;


public class recepciongeneradorVo {
private	int id;
private	int idtiporeactivo;
private	Timestamp fechahora;
private	Timestamp fechahoraregistro;
private	Double actividad;
private String Reactivo;
private Time hora;
private Date fecha;
private Boolean segundaelucion1;
private int fila1;
private int columna1;
private Timestamp fechahorasegundaelucion1;
private Boolean segundaelucion2;
private int fila2;
private int columna2;
private Timestamp fechahorasegundaelucion2;
private Boolean segundaelucion3;
private int fila3;
private int columna3;
private Timestamp fechahorasegundaelucion3;
private Boolean segundaelucion4;
private int fila4;
private int columna4;
private Timestamp fechahorasegundaelucion4;
private Boolean segundaelucion5;
private int fila5;
private int columna5;
private Timestamp fechahorasegundaelucion5;
private Boolean segundaelucion6;
private int fila6;
private int columna6;
private Timestamp fechahorasegundaelucion6;
private Date fechasolicitud;
private Date fechaentrega;
    public recepciongeneradorVo() {
    }

    public recepciongeneradorVo(int id, int idtiporeactivo, Timestamp fechahora, Timestamp fechahoraregistro, Double actividad, String Reactivo, Time hora, Date fecha, Boolean segundaelucion1, int fila1, int columna1, Timestamp fechahorasegundaelucion1, Boolean segundaelucion2, int fila2, int columna2, Timestamp fechahorasegundaelucion2, Boolean segundaelucion3, int fila3, int columna3, Timestamp fechahorasegundaelucion3, Boolean segundaelucion4, int fila4, int columna4, Timestamp fechahorasegundaelucion4, Boolean segundaelucion5, int fila5, int columna5, Timestamp fechahorasegundaelucion5, Boolean segundaelucion6, int fila6, int columna6, Timestamp fechahorasegundaelucion6, Date fechasolicitud, Date fechaentrega) {
        this.id = id;
        this.idtiporeactivo = idtiporeactivo;
        this.fechahora = fechahora;
        this.fechahoraregistro = fechahoraregistro;
        this.actividad = actividad;
        this.Reactivo = Reactivo;
        this.hora = hora;
        this.fecha = fecha;
        this.segundaelucion1 = segundaelucion1;
        this.fila1 = fila1;
        this.columna1 = columna1;
        this.fechahorasegundaelucion1 = fechahorasegundaelucion1;
        this.segundaelucion2 = segundaelucion2;
        this.fila2 = fila2;
        this.columna2 = columna2;
        this.fechahorasegundaelucion2 = fechahorasegundaelucion2;
        this.segundaelucion3 = segundaelucion3;
        this.fila3 = fila3;
        this.columna3 = columna3;
        this.fechahorasegundaelucion3 = fechahorasegundaelucion3;
        this.segundaelucion4 = segundaelucion4;
        this.fila4 = fila4;
        this.columna4 = columna4;
        this.fechahorasegundaelucion4 = fechahorasegundaelucion4;
        this.segundaelucion5 = segundaelucion5;
        this.fila5 = fila5;
        this.columna5 = columna5;
        this.fechahorasegundaelucion5 = fechahorasegundaelucion5;
        this.segundaelucion6 = segundaelucion6;
        this.fila6 = fila6;
        this.columna6 = columna6;
        this.fechahorasegundaelucion6 = fechahorasegundaelucion6;
        this.fechasolicitud = fechasolicitud;
        this.fechaentrega = fechaentrega;
    }

    public int getId() {
        return id;
    }

    public int getIdtiporeactivo() {
        return idtiporeactivo;
    }

    public Timestamp getFechahora() {
        return fechahora;
    }

    public Timestamp getFechahoraregistro() {
        return fechahoraregistro;
    }

    public Double getActividad() {
        return actividad;
    }

    public String getReactivo() {
        return Reactivo;
    }

    public Time getHora() {
        return hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public Boolean getSegundaelucion1() {
        return segundaelucion1;
    }

    public int getFila1() {
        return fila1;
    }

    public int getColumna1() {
        return columna1;
    }

    public Timestamp getFechahorasegundaelucion1() {
        return fechahorasegundaelucion1;
    }

    public Boolean getSegundaelucion2() {
        return segundaelucion2;
    }

    public int getFila2() {
        return fila2;
    }

    public int getColumna2() {
        return columna2;
    }

    public Timestamp getFechahorasegundaelucion2() {
        return fechahorasegundaelucion2;
    }

    public Boolean getSegundaelucion3() {
        return segundaelucion3;
    }

    public int getFila3() {
        return fila3;
    }

    public int getColumna3() {
        return columna3;
    }

    public Timestamp getFechahorasegundaelucion3() {
        return fechahorasegundaelucion3;
    }

    public Boolean getSegundaelucion4() {
        return segundaelucion4;
    }

    public int getFila4() {
        return fila4;
    }

    public int getColumna4() {
        return columna4;
    }

    public Timestamp getFechahorasegundaelucion4() {
        return fechahorasegundaelucion4;
    }

    public Boolean getSegundaelucion5() {
        return segundaelucion5;
    }

    public int getFila5() {
        return fila5;
    }

    public int getColumna5() {
        return columna5;
    }

    public Timestamp getFechahorasegundaelucion5() {
        return fechahorasegundaelucion5;
    }

    public Boolean getSegundaelucion6() {
        return segundaelucion6;
    }

    public int getFila6() {
        return fila6;
    }

    public int getColumna6() {
        return columna6;
    }

    public Timestamp getFechahorasegundaelucion6() {
        return fechahorasegundaelucion6;
    }

    public Date getFechasolicitud() {
        return fechasolicitud;
    }

    public Date getFechaentrega() {
        return fechaentrega;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdtiporeactivo(int idtiporeactivo) {
        this.idtiporeactivo = idtiporeactivo;
    }

    public void setFechahora(Timestamp fechahora) {
        this.fechahora = fechahora;
    }

    public void setFechahoraregistro(Timestamp fechahoraregistro) {
        this.fechahoraregistro = fechahoraregistro;
    }

    public void setActividad(Double actividad) {
        this.actividad = actividad;
    }

    public void setReactivo(String Reactivo) {
        this.Reactivo = Reactivo;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setSegundaelucion1(Boolean segundaelucion1) {
        this.segundaelucion1 = segundaelucion1;
    }

    public void setFila1(int fila1) {
        this.fila1 = fila1;
    }

    public void setColumna1(int columna1) {
        this.columna1 = columna1;
    }

    public void setFechahorasegundaelucion1(Timestamp fechahorasegundaelucion1) {
        this.fechahorasegundaelucion1 = fechahorasegundaelucion1;
    }

    public void setSegundaelucion2(Boolean segundaelucion2) {
        this.segundaelucion2 = segundaelucion2;
    }

    public void setFila2(int fila2) {
        this.fila2 = fila2;
    }

    public void setColumna2(int columna2) {
        this.columna2 = columna2;
    }

    public void setFechahorasegundaelucion2(Timestamp fechahorasegundaelucion2) {
        this.fechahorasegundaelucion2 = fechahorasegundaelucion2;
    }

    public void setSegundaelucion3(Boolean segundaelucion3) {
        this.segundaelucion3 = segundaelucion3;
    }

    public void setFila3(int fila3) {
        this.fila3 = fila3;
    }

    public void setColumna3(int columna3) {
        this.columna3 = columna3;
    }

    public void setFechahorasegundaelucion3(Timestamp fechahorasegundaelucion3) {
        this.fechahorasegundaelucion3 = fechahorasegundaelucion3;
    }

    public void setSegundaelucion4(Boolean segundaelucion4) {
        this.segundaelucion4 = segundaelucion4;
    }

    public void setFila4(int fila4) {
        this.fila4 = fila4;
    }

    public void setColumna4(int columna4) {
        this.columna4 = columna4;
    }

    public void setFechahorasegundaelucion4(Timestamp fechahorasegundaelucion4) {
        this.fechahorasegundaelucion4 = fechahorasegundaelucion4;
    }

    public void setSegundaelucion5(Boolean segundaelucion5) {
        this.segundaelucion5 = segundaelucion5;
    }

    public void setFila5(int fila5) {
        this.fila5 = fila5;
    }

    public void setColumna5(int columna5) {
        this.columna5 = columna5;
    }

    public void setFechahorasegundaelucion5(Timestamp fechahorasegundaelucion5) {
        this.fechahorasegundaelucion5 = fechahorasegundaelucion5;
    }

    public void setSegundaelucion6(Boolean segundaelucion6) {
        this.segundaelucion6 = segundaelucion6;
    }

    public void setFila6(int fila6) {
        this.fila6 = fila6;
    }

    public void setColumna6(int columna6) {
        this.columna6 = columna6;
    }

    public void setFechahorasegundaelucion6(Timestamp fechahorasegundaelucion6) {
        this.fechahorasegundaelucion6 = fechahorasegundaelucion6;
    }

    public void setFechasolicitud(Date fechasolicitud) {
        this.fechasolicitud = fechasolicitud;
    }

    public void setFechaentrega(Date fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

}