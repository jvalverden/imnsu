/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Vo.inferenciaVo;
import resources.img.GeneraReport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class inferenciaDao {

    public static inferenciaVo getInferencia(boolean testudio, boolean interior, boolean estado, boolean fas, boolean fes,boolean s1,boolean s2) {
        inferenciaVo inferencia = null;
        String sql = "SELECT id, tipoestudio, interior, estado, fascedente, fdescendente,  sql,s1,s2,sql2  FROM motordeinferencia.inferencia where tipoestudio=? and  interior=? and estado=? and fascedente=? and fdescendente=? and s1=? and s2=? ";
        

        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);
            stm.setBoolean(1, testudio);
            stm.setBoolean(2, interior);
            stm.setBoolean(3, estado);
            stm.setBoolean(4, fas);
            stm.setBoolean(5, fes);
            stm.setBoolean(6, s1);
            stm.setBoolean(7, s2);
            rs = stm.executeQuery();
            while (rs.next()) {
                inferencia = new inferenciaVo(rs.getInt(1), rs.getBoolean(2), rs.getBoolean(3), rs.getBoolean(4), rs.getBoolean(5), rs.getBoolean(6), rs.getString(7),rs.getBoolean(8),rs.getBoolean(9),rs.getString(10));
            }

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(inferenciaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return inferencia;
    }

}
