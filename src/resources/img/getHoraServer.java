/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package resources.img;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex Roberto Thompson Perez
 */
public class getHoraServer {
   public java.sql.Timestamp getHora(){
       java.sql.Timestamp d=null;
       ResultSet rs = null;
       PreparedStatement stm = null;
       try {
           GeneraReport.conectarbd();
//           System.out.println("sql->"+" select dirfisica,gestion,webservice,codigoinstitucion,dirservlet,idplataforma,idcorredor,idtipounidadcamara,ciudad,departamento,zonificacionmultiple from configuracion where id="+id);
           stm = GeneraReport.conn.prepareStatement(" select now()::timestamp ");
           rs = stm.executeQuery();
           while (rs.next()){
               d=rs.getTimestamp(1); 
            }
           GeneraReport.cerrarBD();
       } catch (SQLException ex) {
           Logger.getLogger(getHoraServer.class.getName()).log(Level.SEVERE, null, ex);
       }
    return d;
   }
   

   
}
