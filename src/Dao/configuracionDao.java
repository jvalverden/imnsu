/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Vo.configuracionVo;
import resources.img.GeneraReport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class configuracionDao {

    public static configuracionVo getListaConconfiguracion() {
        configuracionVo configuracion = null;
        String sql = "select id,descripcion,n,t2,mt2,q  from radioactividad.configuracion limit 1";// where habilitado=? ";
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                configuracion = new configuracionVo(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6));
            }

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(configuracionDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return configuracion;
    }

}
