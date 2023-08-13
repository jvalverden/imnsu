/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vo;

/**
 *
 * @author CONSEJO
 */
public class inferenciaVo {
private	int id;
private	boolean	tipoestudio;
private	boolean	interior;
private	boolean	estado;
private	boolean	fascedente;
private	boolean	fdescendente;
private	String	sql;
private boolean s1;
private boolean s2;
private String  sql2;
    public inferenciaVo() {
    }

    public inferenciaVo(int id, boolean tipoestudio, boolean interior, boolean estado, boolean fascedente, boolean fdescendente, String sql, boolean s1, boolean s2, String sql2) {
        this.id = id;
        this.tipoestudio = tipoestudio;
        this.interior = interior;
        this.estado = estado;
        this.fascedente = fascedente;
        this.fdescendente = fdescendente;
        this.sql = sql;
        this.s1 = s1;
        this.s2 = s2;
        this.sql2 = sql2;
    }

    public int getId() {
        return id;
    }

    public boolean isTipoestudio() {
        return tipoestudio;
    }

    public boolean isInterior() {
        return interior;
    }

    public boolean isEstado() {
        return estado;
    }

    public boolean isFascedente() {
        return fascedente;
    }

    public boolean isFdescendente() {
        return fdescendente;
    }

    public String getSql() {
        return sql;
    }

    public boolean isS1() {
        return s1;
    }

    public boolean isS2() {
        return s2;
    }

    public String getSql2() {
        return sql2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoestudio(boolean tipoestudio) {
        this.tipoestudio = tipoestudio;
    }

    public void setInterior(boolean interior) {
        this.interior = interior;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setFascedente(boolean fascedente) {
        this.fascedente = fascedente;
    }

    public void setFdescendente(boolean fdescendente) {
        this.fdescendente = fdescendente;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void setS1(boolean s1) {
        this.s1 = s1;
    }

    public void setS2(boolean s2) {
        this.s2 = s2;
    }

    public void setSql2(String sql2) {
        this.sql2 = sql2;
    }

}
