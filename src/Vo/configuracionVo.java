/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vo;
public class configuracionVo {
 private int id;
 private String descripcion;
 private Double n;
 private Double t2;
 private Double mt2; 
 private Double q; 

    public configuracionVo(int id, String descripcion, Double n, Double t2, Double mt2, Double q) {
        this.id = id;
        this.descripcion = descripcion;
        this.n = n;
        this.t2 = t2;
        this.mt2 = mt2;
        this.q = q;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getN() {
        return n;
    }

    public Double getT2() {
        return t2;
    }

    public Double getMt2() {
        return mt2;
    }

    public Double getQ() {
        return q;
    }

}
