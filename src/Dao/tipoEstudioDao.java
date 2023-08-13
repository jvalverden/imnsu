/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;




import resources.img.GeneraReport;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Vo.tipoEstudioVo;


public class tipoEstudioDao {
   public ArrayList<tipoEstudioVo> comboTipoEstudio(){
   ArrayList<tipoEstudioVo> listaTipoestudioVo= new ArrayList<>();    
   String sql="select id,descripcion,radiofarmaco,dosis,protocolo from tipoestudio order by descripcion ";
   ResultSet rs = null;
   PreparedStatement stm = null;
   tipoEstudioVo tipoestado=null;
            GeneraReport.conectarbd();
       try {
           stm = GeneraReport.conn.prepareStatement(sql);

           rs = stm.executeQuery();
           while (rs.next()){
               tipoestado=new tipoEstudioVo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
               listaTipoestudioVo.add(tipoestado);
            }
           GeneraReport.cerrarBD();
           
       } catch (SQLException ex) {
           Logger.getLogger(tipoEstudioDao.class.getName()).log(Level.SEVERE, null, ex);
       }
    return listaTipoestudioVo;
   }

   public tipoEstudioVo getTipoEstudio(int id){
   String sql="select * from tipoestudio where id=? ";
   ResultSet rs = null;
   PreparedStatement stm = null;
   tipoEstudioVo tipoestado=null;
            GeneraReport.conectarbd();
       try {
           stm = GeneraReport.conn.prepareStatement(sql);
           stm.setInt(1, id);
           rs = stm.executeQuery();
           while (rs.next()){
               tipoestado=new tipoEstudioVo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));               
            }
           GeneraReport.cerrarBD();
           
       } catch (SQLException ex) {
           Logger.getLogger(tipoEstudioDao.class.getName()).log(Level.SEVERE, null, ex);
       }
    return tipoestado;
   }
   
}
    
