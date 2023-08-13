
package resources.img;



import java.util.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.MaskFormatter;

public class formato {
    private Locale[] locales = {
                new Locale("fr","FR"),
                new Locale("de","DE"),
                new Locale("en","US"),
                new Locale("es","ES"),
            };
    private NumberFormat nf; 
    private DecimalFormat df;
    private NumberFormat nfi; 
    private DecimalFormat dfi;    
    /**
     * Creates a new instance of formato
     */
    public formato() {
          nf= NumberFormat.getNumberInstance(locales[2]);
          df = (DecimalFormat)nf;
          df.applyPattern("#.00");
    }
    
    public formato(int x) {
          nf= NumberFormat.getNumberInstance(locales[2]);
          df = (DecimalFormat)nf;
          df.applyPattern("########################################");//################
    }
    public DecimalFormat getFormatoDecimal(){
        return df;
    }
    
    public DecimalFormat getFormatoEntero(){
          nfi= NumberFormat.getNumberInstance(locales[2]);
          dfi = (DecimalFormat)nfi;
          dfi.applyPattern("################");        
        return dfi;
    }
    public MaskFormatter getFormatoMatricula() throws ParseException{
    MaskFormatter mascara = new MaskFormatter("*.**.#.##.#######");
   return mascara;
    }

   public MaskFormatter getFormatoWang() {
    MaskFormatter mascara = null;
        try {
            mascara = new MaskFormatter("########");
        } catch (ParseException ex) {
            Logger.getLogger(formato.class.getName()).log(Level.SEVERE, null, ex);
        }
   return mascara;
    }
    
   public MaskFormatter getLetra() {
    MaskFormatter mascara = null;
        try {
            mascara = new MaskFormatter("************************************************************");
            mascara.setValidCharacters(java.awt.event.KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_TAB)+"()ABCDEFGHIJKLMÑNOPQRSTUVWXYZÁÉÍÓÚÑÄËÏÖÜÇ ");
           // mascara.setInvalidCharacters("$%&/().;:,-*+/ºª1234567890|");
        } catch (ParseException ex) {
            Logger.getLogger(formato.class.getName()).log(Level.SEVERE, null, ex);
        }
   return mascara;
    }    
    public String setFormatDecimal(double num){
        return df.format(num);
        
    }
   public MaskFormatter getLetra2() {
    MaskFormatter mascara = null;
        try {
            mascara = new MaskFormatter("************************************************************************************************************************************************************************************************************************************************");
            mascara.setInvalidCharacters(java.awt.event.KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_TAB)+"'");
           // mascara.setInvalidCharacters("$%&/().;:,-*+/ºª1234567890|");
        } catch (ParseException ex) {
            Logger.getLogger(formato.class.getName()).log(Level.SEVERE, null, ex);
        }
   return mascara;
    }
   public MaskFormatter getFormatoFecha() {
    MaskFormatter mascara = null;
        try {
            mascara = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            Logger.getLogger(formato.class.getName()).log(Level.SEVERE, null, ex);
        }
   return mascara;
    }
   
}
