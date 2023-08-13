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
import Vo.tipoReactivoVo;


public class tipoReactivoDao {
   public ArrayList<tipoReactivoVo> comboTipoReactivo(){
   ArrayList<tipoReactivoVo> listareactivoVo= new ArrayList<>();    
   String sql="select id,descripcion from radioactividad.tiporeactivo order by descripcion ";
   ResultSet rs = null;
   PreparedStatement stm = null;
   tipoReactivoVo tiporeactivo=null;
            GeneraReport.conectarbd();
       try {
           stm = GeneraReport.conn.prepareStatement(sql);

           rs = stm.executeQuery();
           while (rs.next()){
               tiporeactivo=new tipoReactivoVo(rs.getInt(1),rs.getString(2));
               listareactivoVo.add(tiporeactivo);
            }
           GeneraReport.cerrarBD();
           
       } catch (SQLException ex) {
           Logger.getLogger(tipoReactivoDao.class.getName()).log(Level.SEVERE, null, ex);
       }
    return listareactivoVo;
   }

   public tipoReactivoVo getTipoEstado(int id){
   String sql="select id,descripcion from tiporeactivo where id=? ";
   ResultSet rs = null;
   PreparedStatement stm = null;
   tipoReactivoVo tiporeactivo=null;
            GeneraReport.conectarbd();
       try {
           stm = GeneraReport.conn.prepareStatement(sql);
           stm.setInt(1, id);
           rs = stm.executeQuery();
           while (rs.next()){
               tiporeactivo=new tipoReactivoVo(rs.getInt(1),rs.getString(2));               
            }
           GeneraReport.cerrarBD();
           
       } catch (SQLException ex) {
           Logger.getLogger(tipoReactivoDao.class.getName()).log(Level.SEVERE, null, ex);
       }
    return tiporeactivo;
   }
   
}
    
