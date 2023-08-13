/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Vo.recepciongeneradorVo;
import resources.img.GeneraReport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class recepciongeneradorDao {

    public static recepciongeneradorVo getRecepcion(String fecha) {
        recepciongeneradorVo recepcion = null;
        String sql = " SELECT a.id, idtiporeactivo, a.fechahora, a.fechahoraregistro, a.actividad,b.descripcion as  reactivo , a.fechahora::time as hora,  a.fechahora::date as fecha,segundaelucion1, fila1, "
                + " columna1,  fechahorasegundaelucion1,segundaelucion2, fila2, columna2,fechahorasegundaelucion2,segundaelucion3, fila3, columna3,  fechahorasegundaelucion3,segundaelucion4, fila4,"
                + " columna4,  fechahorasegundaelucion4,segundaelucion5, fila5, columna5,  fechahorasegundaelucion5,segundaelucion6, fila6, columna6,  fechahorasegundaelucion6  fechahorasegundaelucion2,fechasolicitud,fechaentrega "
                + " FROM radioactividad.recepciongenerador a join radioactividad.tiporeactivo b on a.idtiporeactivo=b.id where a.id in"
                + " (select id from (select id,fechahora,(CAST(fechahora::character varying  AS DATE) + CAST('13 days' AS INTERVAL))::timestamp as fechafin "
                + " from radioactividad.recepciongenerador where fechahora::date<='" + fecha + "'::date order by fechahora desc limit 1) w where w.fechahora::date<='" + fecha + "'::date and w.fechafin::date>='" + fecha + "'::date "
                + " ) "
                + " order by a.id desc ";
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);

            rs = stm.executeQuery();
            while (rs.next()) {
                recepcion = new recepciongeneradorVo(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getDouble(5), rs.getString(6), rs.getTime(7), rs.getDate(8), rs.getBoolean(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12),
                        rs.getBoolean(13), rs.getInt(14), rs.getInt(15),
                        rs.getTimestamp(16), rs.getBoolean(17), rs.getInt(18),
                        rs.getInt(19), rs.getTimestamp(20), rs.getBoolean(21), rs.getInt(22),
                        rs.getInt(23), rs.getTimestamp(24), rs.getBoolean(25), rs.getInt(26), rs.getInt(27), rs.getTimestamp(28), rs.getBoolean(29), rs.getInt(30), rs.getInt(31), rs.getTimestamp(32),rs.getDate(33),rs.getDate(34));

            }

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(recepciongeneradorDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return recepcion;
    }

    public static ArrayList<recepciongeneradorVo> getListaRecepcion() {
        ArrayList<recepciongeneradorVo> recepcionArrayList = new ArrayList<recepciongeneradorVo>();
        recepciongeneradorVo recepcion = null;
        String sql = "SELECT a.id, idtiporeactivo, a.fechahora, a.fechahoraregistro, a.actividad,b.descripcion as  reactivo , a.fechahora::time as hora,  a.fechahora::date as fecha,segundaelucion1, fila1, columna1,  fechahorasegundaelucion1,segundaelucion2, fila2, columna2,fechahorasegundaelucion2,segundaelucion3, fila3, columna3,  fechahorasegundaelucion3,segundaelucion4, fila4, columna4,  fechahorasegundaelucion4,segundaelucion5, fila5, columna5,  fechahorasegundaelucion5,segundaelucion6, fila6, columna6,  fechahorasegundaelucion6,fechasolicitud,fechaentrega FROM radioactividad.recepciongenerador a join radioactividad.tiporeactivo b on a.idtiporeactivo=b.id order by a.id desc";
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);
//            stm.setString(1, '%'+paterno+'%');
//            stm.setString(2, '%'+materno+'%');
//            stm.setString(3, '%'+nombre+'%');
            rs = stm.executeQuery();
            while (rs.next()) {
                recepcion = new recepciongeneradorVo(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3), rs.getTimestamp(4), rs.getDouble(5), rs.getString(6), rs.getTime(7), rs.getDate(8), rs.getBoolean(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12),
                        rs.getBoolean(13), rs.getInt(14), rs.getInt(15),
                        rs.getTimestamp(16), rs.getBoolean(17), rs.getInt(18),
                        rs.getInt(19), rs.getTimestamp(20), rs.getBoolean(21), rs.getInt(22),
                        rs.getInt(23), rs.getTimestamp(24), rs.getBoolean(25), rs.getInt(26), rs.getInt(27), rs.getTimestamp(28), rs.getBoolean(29), rs.getInt(30), rs.getInt(31), rs.getTimestamp(32),rs.getDate(33),rs.getDate(34));
                recepcionArrayList.add(recepcion);
            }

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(recepciongeneradorDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return recepcionArrayList;
    }

    public static void insertRecepcion(recepciongeneradorVo recepcion) {
        String sql = "INSERT INTO radioactividad.recepciongenerador(idtiporeactivo, fechahora, fechahoraregistro, actividad,fechasolicitud,fechaentrega)   VALUES ( ?, ?, now(), ?,?,?);";
        PreparedStatement stm = null;

        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);
            stm.setInt(1, recepcion.getIdtiporeactivo());
            stm.setTimestamp(2, recepcion.getFechahora());
            stm.setDouble(3, recepcion.getActividad());
            stm.setDate(4, new java.sql.Date(recepcion.getFechasolicitud().getTime()));
            stm.setDate(5, new java.sql.Date(recepcion.getFechaentrega().getTime()));
            stm.executeUpdate();
            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(recepciongeneradorDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void setSegundaElucion(recepciongeneradorVo recepcion, String numero, int fila, int columna) {
        String sql = "update radioactividad.recepciongenerador set segundaelucion" + numero + "=true,fila" + numero + "=?, columna" + numero + "=?, fechahorasegundaelucion" + numero + "=now() where id=?;";
        PreparedStatement stm = null;

        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);
            stm.setInt(1, fila);
            stm.setInt(2, columna);
            stm.setInt(3, recepcion.getId());
            stm.executeUpdate();
            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(recepciongeneradorDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void setSegundaElucionPasoAtras(recepciongeneradorVo recepcion, String numero, int fila, int columna) {
        String sql = "update radioactividad.recepciongenerador set segundaelucion" + numero + "=false,fila" + numero + "=?, columna" + numero + "=?, fechahorasegundaelucion" + numero + "=now() where id=?;";
        PreparedStatement stm = null;
 //       System.out.println("sql->"+sql);
 //       System.out.println("1->"+fila);
 //       System.out.println("2->"+columna);
//        System.out.println("3->"+recepcion.getId());
        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);
            stm.setInt(1, fila);
            stm.setInt(2, columna);
            stm.setInt(3, recepcion.getId());
            stm.executeUpdate();
            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(recepciongeneradorDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public static int getNumeroelucion(recepciongeneradorVo recepcion) {
        try {
            GeneraReport.conectarbd();
            int id = -999;
            String ssql = "select * from radioactividad.getsegundaelucion(?)";
  //          System.out.println("sql->"+ssql);
  //          System.out.println("-->"+recepcion.getId());
            PreparedStatement stmt = GeneraReport.conn.prepareStatement(ssql);
            stmt.setInt(1, recepcion.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt(1);
                }
            }
            GeneraReport.cerrarBD();
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(recepciongeneradorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public static int getColumna(recepciongeneradorVo recepcion,String fecha) {
        try {
            GeneraReport.conectarbd();
            int id = -999;
            String ssql = "select to_date('"+fecha+"'::date::character varying,'yyyy-mm-dd') - to_date(fechahora::date::character varying,'yyyy-mm-dd')  from radioactividad.recepciongenerador where id=?";
            //System.out.println("sql->"+ssql);
            PreparedStatement stmt = GeneraReport.conn.prepareStatement(ssql);
            stmt.setInt(1, recepcion.getId());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt(1);
                }
            }
            GeneraReport.cerrarBD();
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(recepciongeneradorDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
//        public static void updatePersona(PersonaVo persona) {
//        String sql = "update persona set nombre=?, paterno=?, materno=?, edad=?, sexo=?, idtipoestado=? where id=?;";
//        PreparedStatement stm = null;
//
//        try {
//            GeneraReport.conectarbd();
//            stm = GeneraReport.conn.prepareStatement(sql);
//            stm.setString(1, persona.getNombre());
//            stm.setString(2, persona.getPaterno());
//            stm.setString(3, persona.getMaterno());
//            stm.setInt(4, persona.getEdad());
//            stm.setString(5, persona.getSexo());
//            stm.setInt(6, persona.getIdtipoestado());
//            stm.setInt(7, persona.getId());
//            stm.executeUpdate();
//
//            GeneraReport.cerrarBD();
//        } catch (SQLException ex) {
//            Logger.getLogger(recepciongeneradorDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
}
