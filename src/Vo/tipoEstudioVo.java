/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vo;


public class tipoEstudioVo {
private int id;
private String descripcion;
private String radiofarmaco;
private String dosis;
private String protocolo;

    public tipoEstudioVo(int id, String descripcion, String radiofarmaco, String dosis, String protocolo) {
        this.id = id;
        this.descripcion = descripcion;
        this.radiofarmaco = radiofarmaco;
        this.dosis = dosis;
        this.protocolo = protocolo;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getRadiofarmaco() {
        return radiofarmaco;
    }

    public String getDosis() {
        return dosis;
    }

    
    public String getProtocolo() {
        return protocolo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setRadiofarmaco(String radiofarmaco) {
        this.radiofarmaco = radiofarmaco;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }


    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }


}
