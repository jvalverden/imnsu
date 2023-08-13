package resources.img;


import java.io.File;
import java.io.IOException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.engine.util.*;
import java.sql.*;
import java.net.*;
import java.security.CodeSource;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

public final class GeneraReport {

    public static Connection conn;
    public static Connection conex = null;
 
    private URL in;
    private URL in2;
    private final Map parametros;
    private final Map parametros2;
    public JasperReport reporte;
    public JasperReport reporte2;

    public GeneraReport(String file) {
        //sin ofuscacion
//        String ruta = "Reportes/Compilados/" + file;
        //con ofuscacion
        String ruta = "reporte/" + file;
        String ruta2 = "/reportes/compilados/blanko.jasper";
        in = this.getClass().getResource(ruta);
        in2 = this.getClass().getResource(ruta2);
        try {

            reporte = (JasperReport) JRLoader.loadObject(in);
            reporte2 = (JasperReport) JRLoader.loadObject(in2);

        } catch (Exception e) {
//            System.out.println("no se encontro ruta del reporte "+e);
        }
        parametros = new HashMap();
        parametros2 = new HashMap();
    }

public List<String> listFiles4JAR(Class clazz, String pack, String ext, boolean includeSubPackage) {

        
        CodeSource src = clazz.getProtectionDomain().getCodeSource();
        List<String> list = new ArrayList<String>();
        String extension = ext.contains(".") ? ext : "." + ext;
        pack = pack.replace('.', '/');
 
        int numFolders = 0;
        if( !includeSubPackage ){
            numFolders = pack.split("/").length;
        }
         System.out.println("n->>"+numFolders);
        try{
 
            if(src != null){
 
                String location = src.getLocation().getPath().replaceAll("%20", " ").replaceAll("!/", "");
                URL jarUrl = new URL(location);
 
                ZipInputStream zip = new ZipInputStream(jarUrl.openStream());
                ZipEntry ze = null;
 
                while((ze = zip.getNextEntry()) != null){
                    String entryName = ze.getName();
 
                    if(entryName.startsWith(pack)
                            && (entryName.endsWith(extension.toLowerCase())
                            || entryName.endsWith(extension.toUpperCase()))){
                         
                        if( !includeSubPackage ){
                             
                            if( numFolders == entryName.split("/").length-1 ){
                                list.add(entryName);
                            }
                            continue;
                        }
                         
                        list.add(entryName);
                    }
                }
 
            }
 
        }catch(IOException ex){
            Logger.getAnonymousLogger().log(Level.SEVERE, ext);
        }
 
        return list;
    }
    
    
public void ArchivosEnCarpeta(){         
    String dir1=System.getProperty("user.dir");         
    File dir = new File(dir1);                          
    System.out.println ("Archivos en la Carpeta: "+dir.getPath()+":");         
    String[] ficheros = dir.list();                          
    if (ficheros == null){
           System.out.println("No hay ficheros en el directorio especificado");         
    }
    else{             
        for (int x=0; x<ficheros.length;x++)
            {
                    System.out.println( ficheros[x]);    	
            }
        }


        System.out.println ("Recursivo:");         
        System.out.println ("Archivos en la Carpeta: "+dir.getPath()+":");         
        listarDirectorio(dir, "");     
    }           
public void listarDirectorio(File f, String separador)     
{         
    File[] ficheros = f.listFiles();                                   
    for (int x=0;x<ficheros.length;x++){
        if(ficheros[x].getName().length()>6){
            if((ficheros[x].getName().substring(ficheros[x].getName().length()-6,ficheros[x].getName().length()).compareTo("jasper")==0)){
                System.out.println(separador +ficheros[x].getPath()+" -- "+ ficheros[x].getName());                           
            }
        }
        if (ficheros[x].isDirectory()){                 
                String nuevo_separador;                 
                nuevo_separador = separador + " ";                 
                listarDirectorio(ficheros[x],nuevo_separador);             
            }         
    }           
}
    



       public static void conectarbd() {

        try {

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://"+imnsu.imnsu.ip+":"+imnsu.imnsu.puerto+"/"+imnsu.imnsu.bd, imnsu.imnsu.ubd, imnsu.imnsu.pbd);
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Class Not Found Exception: " + cE.toString());
        }

    }


    public static void cerrarBD() {
        try {
            conn.close();
        } catch (SQLException E) {
            System.out.println("Excepciï¿½n del SQL: " + E.getMessage());
            System.out.println("Estado del SQL: " + E.getSQLState());
            System.out.println("Error del Proveedor: " + E.getErrorCode());
        }

    }
        

//    public static void cerrarBD2(){
//   		try {
//      		conex.close();
// 		  }catch (SQLException E) {
//      		System.out.println("Excepciï¿½n del SQL: " + E.getMessage());
//      		System.out.println("Estado del SQL: " + E.getSQLState());
//      		System.out.println("Error del Proveedor: " + E.getErrorCode());
//   		  }
//
//    }

    public void establecer_parametros(String param, String valor) {
        try {
            parametros.put(param, valor);
        } catch (Exception e) {
            System.out.println("El Sistema Fallo al colocar el reporte 1.");
            System.out.println(e);
        }
    }

    public void establecer_fechas(String param, java.sql.Date valor) {
        try {
            parametros.put(param, valor);

        } catch (Exception e) {
            System.out.println("El Sistema Fallo al colocar el reporte 1.");
            System.out.println(e);
        }
    }

    public void establecer_fechasT(String param, java.sql.Timestamp valor) {
        try {
            parametros.put(param, valor);

        } catch (Exception e) {
            System.out.println("El Sistema Fallo al colocar el reporte 1.");
            System.out.println(e);
        }
    }

    public void establecer_int(String param, Object valor) {
        try {
            parametros.put(param, valor);

        } catch (Exception e) {
            System.out.println("El Sistema Fallo al colocar el reporte 1.");
            System.out.println(e);
        }
    }

    public void establecer_boolean(String param, Boolean valor) {
        try {
            parametros.put(param, valor);

        } catch (Exception e) {
            System.out.println("El Sistema Fallo al colocar el reporte 1.");
            System.out.println(e);
        }
    }

    public void establecer_parametro(String param, Object valor) {
        try {
            parametros.put(param, valor);
        } catch (Exception e) {
            System.out.println("El Sistema Fallo al establecer el parametro.");
            System.out.println(e);
        }
    }

    public void establecer_subreporte(String param, JasperReport valor) {
        try {
            parametros.put(param, valor);
        } catch (Exception e) {
            System.out.println("El Sistema Fallo al colocar el reporte(establecer_detalle).");
            System.out.println(e);
        };
    }

    public void generar_reporte_sin_conex() {
        try {

            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros);
            JasperViewer.viewReport(imp, false);

        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte");
        };
    }

    public void generar_reporte_impresora() {
        try {

            conectarbd();
            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            JasperPrintManager.printReport(imp, false);
            // JasperViewer.viewReport(imp,false);
            cerrarBD();
        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte - "+e);
        }
    }

    public byte[] generar_byte(){
        byte[] output=null;
        try {

            conectarbd();
            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            output = JasperExportManager.exportReportToPdf(imp); 
//            JasperPrintManager.printReport(imp, false);

            cerrarBD();
        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte - "+e);
        }
        return output;
    }

    
    
    public void generar_reporte_hojaporhoja(int hoja) {
        conectarbd();
        int j = 0;
        try {

            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            List pages = imp.getPages();
            parametros2.put("matricula", "123");
            JasperPrint imp2 = JasperFillManager.fillReport(reporte2, parametros2, conn);
            j = pages.size();
            for (int k = (pages.size()); k > -1; k--) {
                if (hoja == k) {
                    JRPrintPage object = ((JRPrintPage) pages.get(k));
                    imp2.addPage(object);
                    break;
                }
            }
            JasperViewer.viewReport(imp2, false);
            //JasperPrintManager.printReport(imp, false);

        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte");
        }
        cerrarBD();

    }

    public void generar_reporte_hojaporhoja2(int hoja) {
        conectarbd();
        int j = 0;
        try {

            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            List pages = imp.getPages();
            parametros2.put("matricula", "123");
            JasperPrint imp2 = JasperFillManager.fillReport(reporte2, parametros2, conn);
            j = pages.size();
            for (int k = (pages.size()); k > -1; k--) {
                if (hoja == k) {
                    JRPrintPage object = (JRPrintPage) pages.get(k);
                    imp2.addPage(object);
                    break;
                }
            }
            JasperPrintManager.printReport(imp2, false);

        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte");
        };
        cerrarBD();

    }

    public int generar_reporte_imparPU2() {
        conectarbd();
        int j = 0;
        try {

            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            List pages = imp.getPages();
            parametros2.put("matricula", "123");
            JasperPrint imp2 = JasperFillManager.fillReport(reporte2, parametros2, conn);
            j = pages.size();
            for (int k = (pages.size() - 1); k > -1; k--) {
                if (((k + 1) % 2) != 0) {
                    JRPrintPage object = (JRPrintPage) pages.get(k);
                    imp2.addPage(object);
                }
            }
            JasperPrintManager.printReport(imp, false);

        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte");
        }
        cerrarBD();
        return j;
    }

    public int generar_reporte_entrada() {
        conectarbd();
        int j = 0;
        try {

            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            List pages = imp.getPages();
            parametros2.put("matricula", "123");
            JasperPrint imp2 = JasperFillManager.fillReport(reporte2, parametros2, conn);
            j = pages.size();
            for (int k = (pages.size() - 1); k > -1; k--) {
                if (((k + 1) % 2) != 0) {
                    JRPrintPage object = (JRPrintPage) pages.get(k);
                    imp2.addPage(object);
                }
            }
       //     JasperViewer.viewReport(imp2,false);
            //JasperPrintManager.printReport(imp, false);

        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte");
        }
        cerrarBD();
        return j;
    }

    public int generar_reporte_parPU2() {
        conectarbd();
        int j = 0;
        try {

            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            List pages = imp.getPages();
            parametros2.put("matricula", "123");
            JasperPrint imp2 = JasperFillManager.fillReport(reporte2, parametros2, conn);
            j = pages.size();
            for (int k = (pages.size() - 1); k > -1; k--) {
                if (Boolean.valueOf(((k + 1) % 2) == 0)) {
                    JRPrintPage object = (JRPrintPage) pages.get(k);
                    imp2.addPage(object);
                }
            }
            JasperPrintManager.printReport(imp2, false);

        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte");
        }
        cerrarBD();
        return j;
    }

    public int generar_reporte_parPU() {
        conectarbd();
        int j = 0;
        try {

            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            List pages = imp.getPages();
            parametros2.put("matricula", "123");
            JasperPrint imp2 = JasperFillManager.fillReport(reporte2, parametros2, conn);
            j = pages.size();
            for (int k = (pages.size() - 1); k > -1; k--) {
                if (((k + 1) % 2) == 0) {
                    JRPrintPage object = (JRPrintPage) pages.get(k);
                    imp2.addPage(object);
                }
            }
            JasperViewer.viewReport(imp2, false);
            //JasperPrintManager.printReport(imp, false);

        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte");
        }
        cerrarBD();
        return j;
    }

    public int generar_reporte_impar() {
        conectarbd();
        int j = 0;
        try {

            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            List pages = imp.getPages();
            parametros2.put("matricula", "123");
            JasperPrint imp2 = JasperFillManager.fillReport(reporte2, parametros2, conn);
            j = pages.size();
            for (int k = 0; k < (pages.size()); k++) {
                if (((k + 1) % 2) != 0) {
                    JRPrintPage object = (JRPrintPage) pages.get(k);
                    imp2.addPage(object);
                }
            }
            JasperViewer.viewReport(imp2, false);
            //JasperPrintManager.printReport(imp, false);

        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte");
        }
        cerrarBD();
        return j;
    }

    public int generar_reporte_impar2() {
        conectarbd();
        int j = 0;
        try {

            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            List pages = imp.getPages();
            parametros2.put("matricula", "123");
            JasperPrint imp2 = JasperFillManager.fillReport(reporte2, parametros2, conn);
            j = pages.size();
            for (int k = 0; k < (pages.size()); k++) {
                if (((k + 1) % 2) != 0) {
                    JRPrintPage object = (JRPrintPage) pages.get(k);
                    imp2.addPage(object);
                }
            }
            JasperPrintManager.printReport(imp, false);

        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte");
        }
        cerrarBD();
        return j;
    }

    public int generar_reporte_par2() {
        int j = 0;
        try {
            conectarbd();
            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            List pages = imp.getPages();
            parametros2.put("matricula", "123");
            JasperPrint imp2 = JasperFillManager.fillReport(reporte2, parametros2, conn);
            j = pages.size();
            for (int k = 0; k < (pages.size()); k++) {
                if (((k + 1) % 2) == 0) {
                    JRPrintPage object = (JRPrintPage) pages.get(k);
                    imp2.addPage(object);
                }
            }
            JasperPrintManager.printReport(imp2, false);

        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte");
        }
        cerrarBD();
        return j;
    }

    public JasperPrint generar_reporte_juntar() {
        JasperPrint imp = null;
        try {
            conectarbd();
            imp = JasperFillManager.fillReport(reporte, parametros, conn);
            //JasperViewer.viewReport(imp,false);
            cerrarBD();
        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte " + e);
        }
        return imp;

    }

    public JasperPrint generar_blanco() {
        JasperPrint imp = null;
        try {
            conectarbd();
            imp = JasperFillManager.fillReport(reporte, parametros, conn);
            //JasperViewer.viewReport(imp,false);
            cerrarBD();
        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte " + e);
        }
        return imp;

    }

    public int generar_reporte_par() {
        int j = 0;
        try {
            conectarbd();
            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            List pages = imp.getPages();
            parametros2.put("matricula", "123");
            JasperPrint imp2 = JasperFillManager.fillReport(reporte2, parametros2, conn);
            j = pages.size();
            for (int k = 0; k < (pages.size()); k++) {
                if (((k + 1) % 2) == 0) {
                    JRPrintPage object = (JRPrintPage) pages.get(k);
                    imp2.addPage(object);
                }
            }
            JasperViewer.viewReport(imp2, false);
            //JasperPrintManager.printReport(imp, false);

        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte");
        }
        cerrarBD();
        return j;
    }

    public void generar_reporte_RTF(String name) {
        try {

            String path = null;
            try {
                path = new java.io.File(".").getCanonicalPath();
            } catch (IOException ex) {
                Logger.getLogger(GeneraReport.class.getName()).log(Level.SEVERE, null, ex);
            }
            conectarbd();
            path = path + "\\tmp\\";
            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            java.io.File loDestFile = new java.io.File(path + name + ".rtf");

            net.sf.jasperreports.engine.export.JRRtfExporter loRtfExp = new net.sf.jasperreports.engine.export.JRRtfExporter();
            loRtfExp.setParameter(net.sf.jasperreports.engine.JRExporterParameter.JASPER_PRINT, imp);
            loRtfExp.setParameter(net.sf.jasperreports.engine.JRExporterParameter.OUTPUT_FILE, loDestFile);
            loRtfExp.exportReport();
            cerrarBD();
        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte " + e);
        }
    }

    public void generar_reporte_Excel(String name) {
        try {

            conectarbd();

            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            java.io.File loDestFile = new java.io.File(name + ".xlsx");

//    net.sf.jasperreports.engine.export.JRRtfExporter loRtfExp = new net.sf.jasperreports.engine.export.JRRtfExporter();
//    loRtfExp.setParameter(net.sf.jasperreports.engine.JRExporterParameter.JASPER_PRINT, imp);
//    loRtfExp.setParameter(net.sf.jasperreports.engine.JRExporterParameter.OUTPUT_FILE, loDestFile);
//    loRtfExp.exportReport();
            JRXlsxExporter Xlsxexporter = new JRXlsxExporter();
            Xlsxexporter.setParameter(JRExporterParameter.JASPER_PRINT, imp);
            Xlsxexporter.setParameter(JRExporterParameter.OUTPUT_FILE, loDestFile);
            Xlsxexporter.exportReport();
            cerrarBD();
        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte " + e);
        }
    }

    public void imprimir_ya_generado(JasperPrint imp) {
        JasperViewer.viewReport(imp, false);
    }

    public void generar_reporte() {
        try {
            /*  parametros.put("PEMPRESA", "SENAPE");
             parametros.put("PDIRECCION", "HUGO ESTRADA #94");
             parametros.put("PTELEFONO", "2220081");
             parametros.put("PNIT","1019655024");
             parametros.put("PEMAIL","senapelp@senape.gov.bo");*/
            conectarbd();
           // System.out.println("para->>"+parametros);
            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            JasperViewer.viewReport(imp, false);
            cerrarBD();
        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte " + e.getLocalizedMessage());
        };
    }



    public int getPaginas() {
        try {

            conectarbd();
            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);

            cerrarBD();
            return imp.getPages().size();
        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte " + e);
        }
        return -999;
    }

    public void generar_reporte2() {
        try {
            conectarbd();
            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
            JasperPrintManager.printReport(imp, false);
            cerrarBD();
        } catch (Exception e) {
            System.out.println("El sistema fallo al tratar de generar el reporte " + e);
        }
    }

//    public void generar_reporteCarro() {
//        try {
//            conectarbd();
//            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
//            JRPrintServiceExporter jrprintServiceExporter = new JRPrintServiceExporter();
//            jrprintServiceExporter.setParameter(JRExporterParameter.JASPER_PRINT, imp);
//            jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, jfMenuPlataforma.impCarro);
//            jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
//            jrprintServiceExporter.exportReport();
//            cerrarBD();
//        } catch (Exception e) {
//            System.out.println("El sistema fallo al tratar de generar el reporte " + e);
//        }
//    }

//    public void generar_reporteTiket() {
//        try {
//            conectarbd();
//            JasperPrint imp = JasperFillManager.fillReport(reporte, parametros, conn);
//            JRPrintServiceExporter jrprintServiceExporter = new JRPrintServiceExporter();
//            jrprintServiceExporter.setParameter(JRExporterParameter.JASPER_PRINT, imp);
//            jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, jfMenuPlataforma.impTiket);
//            jrprintServiceExporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
//            jrprintServiceExporter.exportReport();
//            cerrarBD();
//        } catch (Exception e) {
//            System.out.println("El sistema fallo al tratar de generar el reporte xx " + e);
//        }
//    }

}
