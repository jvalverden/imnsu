/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Vo.PersonaVo;
import Vo.usuarioVo;
import resources.img.GeneraReport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class usuarioDao {

    public static ArrayList<usuarioVo> getListaUsuarios() {
        ArrayList<usuarioVo> usuarioArrayList = new ArrayList<usuarioVo>();
        usuarioVo usuario = null;
        String sql = "SELECT id, nombrecompleto, usuario, password, op1, op2, op3, op4, op5,  op6, op7, op8, op9, op10 FROM usuario order by id; ";
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                usuario = new usuarioVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9), rs.getBoolean(10), rs.getBoolean(11), rs.getBoolean(12), rs.getBoolean(13), rs.getBoolean(14));
                usuarioArrayList.add(usuario);
            }

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuarioArrayList;
    }

    public static void insertUsuario(usuarioVo usuario) {
        String sql = "INSERT INTO usuario(nombrecompleto, usuario, password, op1, op2, op3, op4, op5, op6, op7, op8, op9, op10) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stm = null;

        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);
            stm.setString(1, usuario.getNombrecompleto());
            stm.setString(2, usuario.getUsuario());
            stm.setString(3, usuario.getPassword());
            stm.setBoolean(4, usuario.isOp1());
            stm.setBoolean(5, usuario.isOp2());
            stm.setBoolean(6, usuario.isOp3());
            stm.setBoolean(7, usuario.isOp4());
            stm.setBoolean(8, usuario.isOp5());
            stm.setBoolean(9, usuario.isOp6());
            stm.setBoolean(10, usuario.isOp7());
            stm.setBoolean(11, usuario.isOp8());
            stm.setBoolean(12, usuario.isOp9());
            stm.setBoolean(13, usuario.isOp10());

            stm.executeUpdate();

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void updateusuario(usuarioVo usuario) {
        String sql = "UPDATE usuario SET  nombrecompleto=?, usuario=?, password=?, op1=?, op2=?,  op3=?, op4=?, op5=?, op6=?, op7=?, op8=?, op9=?, op10=?  WHERE id=?;";
        PreparedStatement stm = null;

        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);
            stm.setString(1, usuario.getNombrecompleto());
            stm.setString(2, usuario.getUsuario());
            stm.setString(3, usuario.getPassword());
            stm.setBoolean(4, usuario.isOp1());
            stm.setBoolean(5, usuario.isOp2());
            stm.setBoolean(6, usuario.isOp3());
            stm.setBoolean(7, usuario.isOp4());
            stm.setBoolean(8, usuario.isOp5());
            stm.setBoolean(9, usuario.isOp6());
            stm.setBoolean(10, usuario.isOp7());
            stm.setBoolean(11, usuario.isOp8());
            stm.setBoolean(12, usuario.isOp9());
            stm.setBoolean(13, usuario.isOp10());
            stm.setInt(14, usuario.getId());

            stm.executeUpdate();

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDao.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(usuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static usuarioVo getUsuario(String usr) {
        usuarioVo usuario = null;
        String sql = "SELECT id, nombrecompleto, usuario, password, op1, op2, op3, op4, op5,  op6, op7, op8, op9, op10 FROM usuario where trim(usuario) ilike trim(?) order by id; ";
        ResultSet rs = null;
        PreparedStatement stm = null;
        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);
            stm.setString(1, usr);
            rs = stm.executeQuery();

            while (rs.next()) {
                usuario = new usuarioVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9), rs.getBoolean(10), rs.getBoolean(11), rs.getBoolean(12), rs.getBoolean(13), rs.getBoolean(14));
            }

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(usuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

}
