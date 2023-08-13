/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Vo.PersonaVo;
import Vo.vpersonaestudioVo;
import resources.img.GeneraReport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class vpersonaestudioDao {

    public static ArrayList<vpersonaestudioVo> getListaPersonaEstudio(String fecha, String  hora) {
        ArrayList<vpersonaestudioVo> vpersonaestudioArrayList = new ArrayList<vpersonaestudioVo>();
        vpersonaestudioVo vpersonaestudio = null;
        String sql = "select nombre ,examen,horainyeccion,molibdeno,tecnecio,fila,columna,horainicio,horafin,fechaconfirmada from vpersonaestudio where fechaconfirmada::date ='"+fecha+"'::date and '"+hora+"'>=horainicio and '"+hora+"'<horafin ";// where habilitado=? ";
        //System.out.println("sql->"+sql);
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);            

            rs = stm.executeQuery();
            while (rs.next()) {
                vpersonaestudio = new vpersonaestudioVo(rs.getString(1),rs.getString(2),rs.getTime(3),rs.getDouble(4),rs.getDouble(5),rs.getInt(6),rs.getInt(7),rs.getTime(8),rs.getTime(9),rs.getTimestamp(10));
                vpersonaestudioArrayList.add(vpersonaestudio);
            }

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(vpersonaestudioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vpersonaestudioArrayList;
    }

    public static ArrayList<vpersonaestudioVo> getListaPersonaEstudioP(PersonaVo persona) {
        ArrayList<vpersonaestudioVo> vpersonaestudioArrayList = new ArrayList<vpersonaestudioVo>();
        vpersonaestudioVo vpersonaestudio = null;
        String sql = "select nombre ,examen,horainyeccion,molibdeno,tecnecio,fila,columna,horainicio,horafin,fechaconfirmada from vpersonaestudio where idpersona=? ";// where habilitado=? ";
        System.out.println("sql->"+sql);
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);            
            stm.setInt(1, persona.getId());

            rs = stm.executeQuery();
            while (rs.next()) {
                vpersonaestudio = new vpersonaestudioVo(rs.getString(1),rs.getString(2),rs.getTime(3),rs.getDouble(4),rs.getDouble(5),rs.getInt(6),rs.getInt(7),rs.getTime(8),rs.getTime(9),rs.getTimestamp(10));
                vpersonaestudioArrayList.add(vpersonaestudio);
            }

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(vpersonaestudioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vpersonaestudioArrayList;
    }

    
}
