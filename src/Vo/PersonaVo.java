/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vo;

import java.sql.Timestamp;
import java.util.Date;


public class PersonaVo {
private	int id;
private	String nombre;
private	String paterno;
private	String materno;
private	int edad;
private	String sexo;
private	int idtipoestado;
private	Timestamp fechahoraregistro;
private String tipoestado;
private String sex;
private String telefono;
private String telefonoresponsable;
private String medicoremitente;
private String instituicionprocedente;
private String procedencia;
private boolean interior;
private boolean enespera;
private Timestamp fechainicioespera;
private Date fecha;
private Timestamp fechatentativa;
private int idtipoestudiosolicitado;

    public PersonaVo() {
    }

    public PersonaVo(int id, String nombre, String paterno, String materno, int edad, String sexo, int idtipoestado, Timestamp fechahoraregistro, String tipoestado, String sex, String telefono, String telefonoresponsable, String medicoremitente, String instituicionprocedente, String procedencia, boolean interior, boolean enespera, Timestamp fechainicioespera, Date fecha, Timestamp fechatentativa, int idtipoestudiosolicitado) {
        this.id = id;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.edad = edad;
        this.sexo = sexo;
        this.idtipoestado = idtipoestado;
        this.fechahoraregistro = fechahoraregistro;
        this.tipoestado = tipoestado;
        this.sex = sex;
        this.telefono = telefono;
        this.telefonoresponsable = telefonoresponsable;
        this.medicoremitente = medicoremitente;
        this.instituicionprocedente = instituicionprocedente;
        this.procedencia = procedencia;
        this.interior = interior;
        this.enespera = enespera;
        this.fechainicioespera = fechainicioespera;
        this.fecha = fecha;
        this.fechatentativa = fechatentativa;
        this.idtipoestudiosolicitado = idtipoestudiosolicitado;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public int getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public int getIdtipoestado() {
        return idtipoestado;
    }

    public Timestamp getFechahoraregistro() {
        return fechahoraregistro;
    }

    public String getTipoestado() {
        return tipoestado;
    }

    public String getSex() {
        return sex;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTelefonoresponsable() {
        return telefonoresponsable;
    }

    public String getMedicoremitente() {
        return medicoremitente;
    }

    public String getInstituicionprocedente() {
        return instituicionprocedente;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public boolean isInterior() {
        return interior;
    }

    public boolean isEnespera() {
        return enespera;
    }

    public Timestamp getFechainicioespera() {
        return fechainicioespera;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public Timestamp getFechatentativa() {
        return fechatentativa;
    }

    public int getIdtipoestudiosolicitado() {
        return idtipoestudiosolicitado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setIdtipoestado(int idtipoestado) {
        this.idtipoestado = idtipoestado;
    }

    public void setFechahoraregistro(Timestamp fechahoraregistro) {
        this.fechahoraregistro = fechahoraregistro;
    }

    public void setTipoestado(String tipoestado) {
        this.tipoestado = tipoestado;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTelefonoresponsable(String telefonoresponsable) {
        this.telefonoresponsable = telefonoresponsable;
    }

    public void setMedicoremitente(String medicoremitente) {
        this.medicoremitente = medicoremitente;
    }

    public void setInstituicionprocedente(String instituicionprocedente) {
        this.instituicionprocedente = instituicionprocedente;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public void setInterior(boolean interior) {
        this.interior = interior;
    }

    public void setEnespera(boolean enespera) {
        this.enespera = enespera;
    }

    public void setFechainicioespera(Timestamp fechainicioespera) {
        this.fechainicioespera = fechainicioespera;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public void setFechatentativa(Timestamp fechatentativa) {
        this.fechatentativa = fechatentativa;
    }

    public void setIdtipoestudiosolicitado(int idtipoestudiosolicitado) {
        this.idtipoestudiosolicitado = idtipoestudiosolicitado;
    }

}
