/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;




import Vo.personatipoestudioVo;
import resources.img.GeneraReport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class personaTipoEstudioDao {
   public static ArrayList<personatipoestudioVo> listaPersonatipoEstudio(){
   ArrayList<personatipoestudioVo> listapersonatipoestudioVo= new ArrayList<>();    
   String sql="SELECT id, idpersona, idtipoestudio, fechahoraestudio, horainyeccion, idrecepciongenerador, molibdeno, tecnecio, fila, columna, horainicio, horafin,fechaconfirmada   FROM personatipoestudio; ";
   ResultSet rs = null;
   PreparedStatement stm = null;
   personatipoestudioVo personatipoestudio=null;
            GeneraReport.conectarbd();
       try {
           stm = GeneraReport.conn.prepareStatement(sql);

           rs = stm.executeQuery();
           while (rs.next()){
               personatipoestudio=new personatipoestudioVo(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTime(5),rs.getInt(6),rs.getFloat(7),rs.getFloat(8),rs.getInt(9),rs.getInt(10),rs.getTime(11),rs.getTime(12),rs.getTimestamp(13));
               listapersonatipoestudioVo.add(personatipoestudio);
            }
           GeneraReport.cerrarBD();
           
       } catch (SQLException ex) {
           Logger.getLogger(personaTipoEstudioDao.class.getName()).log(Level.SEVERE, null, ex);
       }
    return listapersonatipoestudioVo;
   }

   public static ArrayList<personatipoestudioVo> listaPersonatipoEstudioFechaHora(String fecha,String hora){
   ArrayList<personatipoestudioVo> listapersonatipoestudioVo= new ArrayList<>();    
   String sql="SELECT a.id,a.idpersona,a.idtipoestudio,a.fechahoraestudio,a.horainyeccion,a.idrecepciongenerador,a.molibdeno, a.tecnecio, a.fila, a.columna, a.horainicio,a.horafin,a.fechaconfirmada " +
"  FROM personatipoestudio a   " +
"  join persona b on a.idpersona=b.id " +
"  where b.fechaconfirmada::date='"+fecha+"'::date and '"+hora+"'::time>=horainicio and '"+hora+"'::time<horafin limit 1;";
   ResultSet rs = null;
   PreparedStatement stm = null;
   personatipoestudioVo personatipoestudio=null;
            GeneraReport.conectarbd();
       try {
           stm = GeneraReport.conn.prepareStatement(sql);

           rs = stm.executeQuery();
           while (rs.next()){
               personatipoestudio=new personatipoestudioVo(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getTimestamp(4), rs.getTime(5),rs.getInt(6),rs.getFloat(7),rs.getFloat(8),rs.getInt(9),rs.getInt(10),rs.getTime(11),rs.getTime(12),rs.getTimestamp(13));
               listapersonatipoestudioVo.add(personatipoestudio);
            }
           GeneraReport.cerrarBD();
           
       } catch (SQLException ex) {
           Logger.getLogger(personaTipoEstudioDao.class.getName()).log(Level.SEVERE, null, ex);
       }
    return listapersonatipoestudioVo;
   }
   
   
    public static void insertPersonaTipoestudio(personatipoestudioVo personatipoestudio) {
        String sql = "INSERT INTO personatipoestudio(idpersona, idtipoestudio,  horainyeccion, idrecepciongenerador, molibdeno, tecnecio, fila, columna,horainicio, horafin,fechaconfirmada)   VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,?,?,?);";
        PreparedStatement stm = null;

        try {
            GeneraReport.conectarbd();
            stm = GeneraReport.conn.prepareStatement(sql);
            stm.setInt(1, personatipoestudio.getIdpersona());
            stm.setInt(2, personatipoestudio.getIdtipoestudio());
            stm.setTime(3, personatipoestudio.getHorainyeccion());
            stm.setInt(4, personatipoestudio.getIdrecepciongenerador());
            stm.setFloat(5, personatipoestudio.getMolibdeno());
            stm.setFloat(6, personatipoestudio.getTecnecio());
            stm.setInt(7, personatipoestudio.getFila());
            stm.setInt(8, personatipoestudio.getColumna());
            stm.setTime(9, personatipoestudio.getHorainicio());
            stm.setTime(10, personatipoestudio.getHorafin());
            stm.setTimestamp(11, personatipoestudio.getFechaconfirmada());
            stm.executeUpdate();

            GeneraReport.cerrarBD();
        } catch (SQLException ex) {
            Logger.getLogger(personaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
}
    
