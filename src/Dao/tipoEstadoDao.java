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
import Vo.tipoEstadoVo;


public class tipoEstadoDao {
   public ArrayList<tipoEstadoVo> comboTipoEstado(){
   ArrayList<tipoEstadoVo> listaTipoestadoVo= new ArrayList<>();    
   String sql="select id,descripcion from tipoestado order by descripcion ";
   ResultSet rs = null;
   PreparedStatement stm = null;
   tipoEstadoVo tipoestado=null;
            GeneraReport.conectarbd();
       try {
           stm = GeneraReport.conn.prepareStatement(sql);

           rs = stm.executeQuery();
           while (rs.next()){
               tipoestado=new tipoEstadoVo(rs.getInt(1),rs.getString(2));
               listaTipoestadoVo.add(tipoestado);
            }
           GeneraReport.cerrarBD();
           
       } catch (SQLException ex) {
           Logger.getLogger(tipoEstadoDao.class.getName()).log(Level.SEVERE, null, ex);
       }
    return listaTipoestadoVo;
   }

   public tipoEstadoVo getTipoEstado(int id){
   String sql="select id,descripcion from tipoestado where id=? ";
   ResultSet rs = null;
   PreparedStatement stm = null;
   tipoEstadoVo tipoestado=null;
            GeneraReport.conectarbd();
       try {
           stm = GeneraReport.conn.prepareStatement(sql);
           stm.setInt(1, id);
           rs = stm.executeQuery();
           while (rs.next()){
               tipoestado=new tipoEstadoVo(rs.getInt(1),rs.getString(2));               
            }
           GeneraReport.cerrarBD();
           
       } catch (SQLException ex) {
           Logger.getLogger(tipoEstadoDao.class.getName()).log(Level.SEVERE, null, ex);
       }
    return tipoestado;
   }
   
}
    
