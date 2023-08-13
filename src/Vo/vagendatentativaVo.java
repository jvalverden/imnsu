/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package Vo;

import java.sql.Timestamp;

public class vagendatentativaVo {

    private int id;
    private String nombre;
    private int edad;
    private String sexo;
    private Timestamp fechainicioespera;
    private Timestamp fechatentativa;
    private int idtipoestudiosolicitado;
    private String tipoestado;
    private String procedencia;
    private boolean interior;
    private int idor;
    private int idtipoestado;
    private String tipoestudiosolicitado;

    public vagendatentativaVo() {
    }

    public vagendatentativaVo(int id, String nombre, int edad, String sexo, Timestamp fechainicioespera, Timestamp fechatentativa, int idtipoestudiosolicitado, String tipoestado, String procedencia, boolean interior, int idor, int idtipoestado, String tipoestudiosolicitado) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.fechainicioespera = fechainicioespera;
        this.fechatentativa = fechatentativa;
        this.idtipoestudiosolicitado = idtipoestudiosolicitado;
        this.tipoestado = tipoestado;
        this.procedencia = procedencia;
        this.interior = interior;
        this.idor = idor;
        this.idtipoestado = idtipoestado;
        this.tipoestudiosolicitado = tipoestudiosolicitado;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public Timestamp getFechainicioespera() {
        return fechainicioespera;
    }

    public Timestamp getFechatentativa() {
        return fechatentativa;
    }

    public int getIdtipoestudiosolicitado() {
        return idtipoestudiosolicitado;
    }

    public String getTipoestado() {
        return tipoestado;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public boolean isInterior() {
        return interior;
    }

    public int getIdor() {
        return idor;
    }

    public int getIdtipoestado() {
        return idtipoestado;
    }

    public String getTipoestudiosolicitado() {
        return tipoestudiosolicitado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setFechainicioespera(Timestamp fechainicioespera) {
        this.fechainicioespera = fechainicioespera;
    }

    public void setFechatentativa(Timestamp fechatentativa) {
        this.fechatentativa = fechatentativa;
    }

    public void setIdtipoestudiosolicitado(int idtipoestudiosolicitado) {
        this.idtipoestudiosolicitado = idtipoestudiosolicitado;
    }

    public void setTipoestado(String tipoestado) {
        this.tipoestado = tipoestado;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public void setInterior(boolean interior) {
        this.interior = interior;
    }

    public void setIdor(int idor) {
        this.idor = idor;
    }

    public void setIdtipoestado(int idtipoestado) {
        this.idtipoestado = idtipoestado;
    }

    public void setTipoestudiosolicitado(String tipoestudiosolicitado) {
        this.tipoestudiosolicitado = tipoestudiosolicitado;
    }
    
}
