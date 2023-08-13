/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Vo.PersonaVo;
import Vo.inferenciaVo;
import Vo.tipoEstadoVo;
import Vo.tipoEstudioVo;
import resources.img.GeneraReport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class personaDao {

    public static ArrayList<PersonaVo> getListaPersonaInferencia(boolean testudio,tipoEstudioVo tipoestudio,boolean interior,boolean estado,tipoEstadoVo tipoestado,boolean fas,boolean fes,boolean s1,boolean s2) {
        ArrayList<PersonaVo> personaArrayList = new ArrayList<PersonaVo>();
        PersonaVo persona = null;
        inferenciaVo inferencia=inferenciaDao.getInferencia(testudio, interior, estado, fas, fes,s1,s2);
        String test="";
        String gtipo="";
        String inte="";
        String est="";
        if (inferencia.isTipoestudio()){
            test=" and a.idtipoestudiosolicitado="+tipoestudio.getId()+"  ";
        }
        if(s1){
            gtipo=inferencia.getSql2();
        }
        if(s2){
            gtipo=inferencia.getSql2();
        }
        if(inferencia.isInterior()){
            inte=" and interior=true ";
        }
        if (inferencia.isEstado()){
            est=" and a.idtipoestado="+tipoestado.getId()+"  ";
        }
        String sql = "select a.id,a.nombre,a.paterno,a.materno,a.edad,a.sexo,a.idtipoestado,a.fechahoraregistro,b.descripcion as tipoestado, case when sexo='M' then 'Masculino' else 'Femenino' end as sex,telefono,telefonoresponsable,medicoremitente,instituicionprocedente,procedencia,interior,enespera,fechainicioespera,fechainicioespera::date as fecha ,fechatentativa,idtipoestudiosolicitado"
                + " from persona a join tipoestado b on a.idtipoestado=b.id "
                + " where enespera=true  "+test+" "+gtipo+" "+inte+" "+est+" order by "+inferencia.getSql();// where habilitado=? ";
        //System.out.println(sql);
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);            
            rs = stm.executeQuery();
            while (rs.next()) {
                persona = new PersonaVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getTimestamp(8), rs.getString(9), rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getBoolean(16),rs.getBoolean(17),rs.getTimestamp(18),rs.getDate(19),rs.getTimestamp(20),rs.getInt(21));
                personaArrayList.add(persona);
            }

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(personaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return personaArrayList;
    }
    
    
    public static ArrayList<PersonaVo> getListaPersona(String nombre,String paterno,String materno,String espera) {
        ArrayList<PersonaVo> personaArrayList = new ArrayList<PersonaVo>();
        PersonaVo persona = null;
        String sql = "select a.id,a.nombre,a.paterno,a.materno,a.edad,a.sexo,a.idtipoestado,a.fechahoraregistro,b.descripcion as tipoestado, case when sexo='M' then 'Masculino' else 'Femenino' end as sex,telefono,telefonoresponsable,medicoremitente,instituicionprocedente,procedencia,interior,enespera,fechainicioespera,fechainicioespera::date as fecha,fechatentativa,idtipoestudiosolicitado from persona a join tipoestado b on a.idtipoestado=b.id where paterno ilike ? and materno ilike ? and nombre ilike ? "+espera+" order by paterno,materno,nombre";// where habilitado=? ";
        

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);            
            stm.setString(1, '%'+paterno+'%');
            stm.setString(2, '%'+materno+'%');
            stm.setString(3, '%'+nombre+'%');
            rs = stm.executeQuery();
            while (rs.next()) {
                persona = new PersonaVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getTimestamp(8), rs.getString(9), rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getBoolean(16),rs.getBoolean(17),rs.getTimestamp(18),rs.getDate(19), rs.getTimestamp(20),rs.getInt(21));
                personaArrayList.add(persona);
            }

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(personaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return personaArrayList;
    }

    public static void insertPersona(PersonaVo persona) {
        String sql = "INSERT INTO persona( nombre, paterno, materno, edad, sexo, idtipoestado, fechahoraregistro,telefono,telefonoresponsable, medicoremitente,instituicionprocedente,procedencia,interior,enespera, fechainicioespera,fechatentativa,idtipoestudiosolicitado)  VALUES ( ?, ?, ?, ?, ?,?, now(),?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement stm = null;

        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);
            stm.setString(1, persona.getNombre());
            stm.setString(2, persona.getPaterno());
            stm.setString(3, persona.getMaterno());
            stm.setInt(4, persona.getEdad());
            stm.setString(5, persona.getSexo());
            stm.setInt(6, persona.getIdtipoestado());
            stm.setString(7, persona.getTelefono());
            stm.setString(8, persona.getTelefonoresponsable());
            stm.setString(9, persona.getMedicoremitente());
            stm.setString(10, persona.getInstituicionprocedente());
            stm.setString(11, persona.getProcedencia());
            stm.setBoolean(12, persona.isInterior());
            stm.setBoolean(13, persona.isEnespera());
            stm.setTimestamp(14, persona.getFechainicioespera());
            stm.setTimestamp(15, persona.getFechatentativa());
            stm.setInt(16, persona.getIdtipoestudiosolicitado());
            
            stm.executeUpdate();

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(personaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
        public static void updatePersona(PersonaVo persona) {
        String sql = "update persona set nombre=?, paterno=?, materno=?, edad=?, sexo=?, idtipoestado=?,telefono=?,telefonoresponsable=?, medicoremitente=?,instituicionprocedente=?,procedencia=?,interior=?,enespera=?, fechainicioespera=?,fechatentativa=?,idtipoestudiosolicitado=? where id=?;";
        PreparedStatement stm = null;

        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);
            stm.setString(1, persona.getNombre());
            stm.setString(2, persona.getPaterno());
            stm.setString(3, persona.getMaterno());
            stm.setInt(4, persona.getEdad());
            stm.setString(5, persona.getSexo());
            stm.setInt(6, persona.getIdtipoestado());
            stm.setString(7, persona.getTelefono());
            stm.setString(8, persona.getTelefonoresponsable());
            stm.setString(9, persona.getMedicoremitente());
            stm.setString(10, persona.getInstituicionprocedente());
            stm.setString(11, persona.getProcedencia());
            stm.setBoolean(12, persona.isInterior());
            stm.setBoolean(13, persona.isEnespera());
            stm.setTimestamp(14, persona.getFechainicioespera());
            stm.setTimestamp(15, persona.getFechatentativa());
            stm.setInt(16, persona.getIdtipoestudiosolicitado());
            stm.setInt(17, persona.getId());
            stm.executeUpdate();

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(personaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        public static void updatePersonaEstudio(PersonaVo persona) {
        String sql = "update persona set enespera=? where id=?;";
        PreparedStatement stm = null;

        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);
            stm.setBoolean(1, persona.isEnespera());
//            stm.setTimestamp(2, persona.getFechaconfirmada());
            stm.setInt(2, persona.getId());
            stm.executeUpdate();

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(personaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        
}
