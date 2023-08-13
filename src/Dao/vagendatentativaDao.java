/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Vo.PersonaVo;
import Vo.vagendatentativaVo;
import Vo.vpersonaestudioVo;
import resources.img.GeneraReport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class vagendatentativaDao {

    public static ArrayList<vagendatentativaVo> getListaPersonaEstudio() {
        ArrayList<vagendatentativaVo> vagedatentativaArrayList = new ArrayList<vagendatentativaVo>();
        vagendatentativaVo vagendatentativa = null;
        String sql = "select * from vagendatentativa ";// where habilitado=? ";
//        System.out.println("sql->"+sql);
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);            

            rs = stm.executeQuery();
            while (rs.next()) {
                vagendatentativa = new vagendatentativaVo(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getTimestamp(5),rs.getTimestamp(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getBoolean(10),rs.getInt(11),rs.getInt(12),rs.getString(13));
                vagedatentativaArrayList.add(vagendatentativa);
            }

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(vagendatentativaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vagedatentativaArrayList;
    }

    
}
