/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Vo.decaimientoMVo;
import Vo.recepciongeneradorVo;
import resources.img.GeneraReport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class decaimientoMDao {

    public static ArrayList<decaimientoMVo> getListadecaimiento(recepciongeneradorVo recepcion) {
        ArrayList<decaimientoMVo> decaimientoArrayList = new ArrayList<decaimientoMVo>();
        decaimientoMVo decaimiento = null;
        String sql = "SELECT id, fechahora1, dia1, fechahora2, dia2, fechahora3, dia3, fechahora4, " +
"       dia4, fechahora5, dia5, fechahora6, dia6, fechahora7, dia7, fechahora8, " +
"       dia8, fechahora9, dia9, fechahora10, dia10, fechahora11, dia11, " +
"       fechahora12, dia12, fechahora13, dia13, fechahora14, dia14, idrecepciongenerador FROM radioactividad.decaimientom where idrecepciongenerador=?";
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);            
            stm.setInt(1, recepcion.getId());
            rs = stm.executeQuery();
            while (rs.next()) {
                decaimiento = new decaimientoMVo(rs.getInt(1), rs.getTimestamp(2), rs.getDouble(3), rs.getTimestamp(4), rs.getDouble(5), rs.getTimestamp(6), rs.getDouble(7), rs.getTimestamp(8), rs.getDouble(9), rs.getTimestamp(10),rs.getDouble(11),rs.getTimestamp(12),rs.getDouble(13),rs.getTimestamp(14),rs.getDouble(15),rs.getTimestamp(16),rs.getDouble(17),rs.getTimestamp(18),rs.getDouble(19),rs.getTimestamp(20),rs.getDouble(21),rs.getTimestamp(22),rs.getDouble(23),rs.getTimestamp(24),rs.getDouble(25),rs.getTimestamp(26),rs.getDouble(27),rs.getTimestamp(28),rs.getDouble(29),rs.getInt(30));
                decaimientoArrayList.add(decaimiento);
            }

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(decaimientoMDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return decaimientoArrayList;
    }

    public static void insertDecaimiento(decaimientoMVo decaimiento) {
        String sql = "INSERT INTO radioactividad.decaimientom(" +
"            fechahora1, dia1, fechahora2, dia2, fechahora3, dia3, fechahora4, " +
"            dia4, fechahora5, dia5, fechahora6, dia6, fechahora7, dia7, fechahora8, " +
"            dia8, fechahora9, dia9, fechahora10, dia10, fechahora11, dia11, " +
"            fechahora12, dia12, fechahora13, dia13, fechahora14, dia14, idrecepciongenerador)" +
"    VALUES (?, ?, ?, ?, ?, ?, ?, " +
"            ?, ?, ?, ?, ?, ?, ?, ?, " +
"            ?, ?, ?, ?, ?, ?, ?, " +
"            ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stm = null;

        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);
            stm.setTimestamp(1, decaimiento.getFechahora1());
            stm.setDouble(2, decaimiento.getDia1());
            stm.setTimestamp(3, decaimiento.getFechahora2());
            stm.setDouble(4, decaimiento.getDia2());
            stm.setTimestamp(5, decaimiento.getFechahora3());
            stm.setDouble(6, decaimiento.getDia3());
            stm.setTimestamp(7, decaimiento.getFechahora4());
            stm.setDouble(8, decaimiento.getDia4());
            stm.setTimestamp(9, decaimiento.getFechahora5());
            stm.setDouble(10, decaimiento.getDia5());
            stm.setTimestamp(11, decaimiento.getFechahora6());
            stm.setDouble(12, decaimiento.getDia6());
            stm.setTimestamp(13, decaimiento.getFechahora7());
            stm.setDouble(14, decaimiento.getDia7());
            stm.setTimestamp(15, decaimiento.getFechahora8());
            stm.setDouble(16, decaimiento.getDia8());
            stm.setTimestamp(17, decaimiento.getFechahora9());
            stm.setDouble(18, decaimiento.getDia9());
            stm.setTimestamp(19, decaimiento.getFechahora10());
            stm.setDouble(20, decaimiento.getDia10());
            stm.setTimestamp(21, decaimiento.getFechahora11());
            stm.setDouble(22, decaimiento.getDia11());
            stm.setTimestamp(23, decaimiento.getFechahora12());
            stm.setDouble(24, decaimiento.getDia12());
            stm.setTimestamp(25, decaimiento.getFechahora13());
            stm.setDouble(26, decaimiento.getDia13());
            stm.setTimestamp(27, decaimiento.getFechahora14());
            stm.setDouble(28, decaimiento.getDia14());
            stm.setInt(29, decaimiento.getIdrecepciongenerador());
            stm.executeUpdate();

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(decaimientoMDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
