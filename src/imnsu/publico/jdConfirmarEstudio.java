/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imnsu.publico;

import Dao.configuracionDao;
import Dao.personaDao;
import Dao.recepciongeneradorDao;
import Dao.tipoEstudioDao;
import Vo.PersonaVo;
import Vo.configuracionVo;
import Vo.personatipoestudioVo;
import Vo.recepciongeneradorVo;
import Vo.tipoEstudioVo;
import Vo.vpersonaestudioVo;
import static imnsu.radiactividad.jpRecepcion.DateToCalendar;
import java.awt.Component;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sourceforge.jFuzzyLogic.FIS;
import resources.img.ArrayListComboBoxModel;
import resources.img.TableCellFormatterText;
import resources.img.TableCellFormatterTextCompleto;
import resources.img.hora;

public class jdConfirmarEstudio extends javax.swing.JDialog {

    private final hora h1;
    private final PersonaVo persona;
    private final String fecha;
    private final String hora;
    private final recepciongeneradorVo recepcion;
    private org.jdesktop.swingx.JXTable jxtlistaRecepcion;
    private javax.swing.JTable jtConstantes;
    private javax.swing.JTable jtHoraM;
    private javax.swing.JTable jtHoraT;
    private javax.swing.JTable jtMolibdeno;
    private javax.swing.JTable jtTecnecio;
    private configuracionVo configuracion;
    private int fila;
    private int columna;
    

    /**
     * Creates new form jdConfirmarEstudio
     * @param parent
     * @param modal
     * @param persona
     * @param fecha
     * @param hora
     */
    public jdConfirmarEstudio(java.awt.Frame parent, boolean modal, PersonaVo persona, String fecha, String hora) {
        super(parent, modal);
        h1 = new hora();        
        initComponents();
        this.configuracion = configuracionDao.getListaConconfiguracion();
        setTablas();
        this.persona = persona;
        this.setSize(500, 500);
        this.setLocationRelativeTo(parent);
        this.fecha = fecha;
        this.hora = hora;
        jxhTitulo.setTitle("Confirmar estudio para: " + this.persona.getPaterno() + " " + this.persona.getMaterno() + " " + this.persona.getNombre());
        jxhTitulo.setDescription("En Fecha:" + this.fecha + " y Hora:" + this.hora);
        setCbTipoEstudio();
        recepcion = recepciongeneradorDao1.getRecepcion(this.fecha);
        if (recepcion == null) {
            JOptionPane.showMessageDialog(new JFrame(), "No existe una recepcion para esta Fecha: " + this.fecha);
       //     System.out.println(recepcion);
        } 
        else {
            
            //System.out.println(recepcion);
            setTablaListaRecepcion(recepcion);
            //    calcularActividad();
            this.columna = recepciongeneradorDao1.getColumna(recepcion, fecha);
            this.fila = getFila(hora);
            //jlMolibdeno.setText("Molibdeno: " + jtMolibdeno.getModel().getValueAt(fila, columna).toString());
            
            //jlTecnecio.setText("Tecnecio: " + (jtTecnecio.getModel().getValueAt(fila, columna).toString()));
                    String label=jtTecnecio.getModel().getValueAt(fila, columna).toString();
        if(label.length()>7)
        {
            String trim = label.substring(0, 6).trim();
        jlTecnecio.setText("Tecnecio: "+trim);
        }
        else {   jlTecnecio.setText("Tecnecio: " + (jtTecnecio.getModel().getValueAt(fila, columna).toString()));
    }
            
            
        }
        String[] parts = hora.split("-");
        String h1 = parts[0];
        setTablaListaPersonaEstudios(fecha, h1);
        this.h1.setConfimarestudio(this);
        if (recepcion != null) {
        this.h1.setHoraTecnecio();
        }
    }
    public void setTecnecio(String hora){
        this.fila = getFila(hora);
         String label;
 //       System.out.println("getfila-"+getFila(hora));
//        System.out.println("fila-"+fila);
 //       System.out.println("columna-"+columna);
        label=jtTecnecio.getModel().getValueAt(fila, columna).toString();
        if(label.length()>7)
        {
            String trim = label.substring(0, 6).trim();
        jlTecnecio.setText("Tecnecio: "+trim);
        }
        else {   jlTecnecio.setText("Tecnecio: " + (jtTecnecio.getModel().getValueAt(fila, columna).toString()));
    }
    }
    private void setTablaListaPersonaEstudios(String fecha,String hora) {
        eliminartodaslasfilastabla(jtListaPersonas);
        Vector v;
        for (vpersonaestudioVo vpersonaestudio : vpersonaestudioDao1.getListaPersonaEstudio(fecha, hora)) {
            v = new Vector();
           // System.out.println("hola"+vpersonaestudio.getNombre());
            v.add(vpersonaestudio.getNombre());
            v.add(vpersonaestudio.getExamen());
            v.add(vpersonaestudio.getHorainyeccion());
            v.add(vpersonaestudio);
            agregar_fila_tabla(v, jtListaPersonas);
        }
        if (jtListaPersonas.getRowCount() > 0) {
            jtListaPersonas.setRowSelectionInterval(0, 0);
            
        }
    }
    private int getFila(String hora) {
        String s = hora.substring(0, 5).trim();
//       System.out.println("s-ssss->"+s);
//        System.out.println("sred-"+s.substring(1,5));
        String s2 = "";
        int col = 0;
        for (int i = 0; i < jtHoraM.getRowCount(); i++) {
            s2 = jtHoraM.getModel().getValueAt(i, 0).toString().substring(0, 5).trim();
 //           System.out.println("s2horam--->"+s2);
 //           if (s2.substring(0, 1).compareTo("0") == 0) {
 //               s2 = s2.substring(1, 5);
 //             System.out.println("s2--->"+s2);
 //         }

            if (s2.compareTo(s) == 0) {
                col = i;
                //System.out.println("col-"+col);
                break;
            }
        }
        return col;
        
    }

    private void eliminarfilastabla(javax.swing.JTable tabla, int i) {
        ((DefaultTableModel) tabla.getModel()).removeRow(i);
    }

    private void eliminartodaslasfilastabla(javax.swing.JTable tabla) {
        while (tabla.getRowCount() > 0) {
            ((DefaultTableModel) tabla.getModel()).removeRow(tabla.getRowCount() - 1);
        }
    }

    public void agregar_fila_tabla(Vector fila, javax.swing.JTable tabla) {
        ((DefaultTableModel) tabla.getModel()).addRow(fila);
    }

    private void setTablaListaRecepcion(recepciongeneradorVo recepcion) {
        eliminartodaslasfilastabla(jxtlistaRecepcion);
        Vector v;
        v = new Vector();
        v.add(recepcion.getReactivo());
        v.add(recepcion.getFecha());
        v.add(recepcion.getHora());
        v.add(recepcion.getActividad());
        v.add(recepcion);
        agregar_fila_tabla(v, jxtlistaRecepcion);
        if (jxtlistaRecepcion.getRowCount() > 0) {
            jxtlistaRecepcion.setRowSelectionInterval(0, 0);
            calcularActividad();
        }
    }

    private void calcularActividad() {
        setTablaHora(jtHoraM);
        setTablaHora(jtHoraT);
        setTablaModelo(jtMolibdeno);
        setTablaModelo(jtTecnecio);
        calcularActividadMolibdeno();
        calcularActividadTecnecio();
        recepciongeneradorVo recepcion = (recepciongeneradorVo) jxtlistaRecepcion.getModel().getValueAt(jxtlistaRecepcion.getSelectedRow(), 4);
        if (recepcion.getSegundaelucion1()) {
            jtMolibdeno.setRowSelectionInterval(recepcion.getFila1(), recepcion.getFila1());
            jtMolibdeno.setColumnSelectionInterval(recepcion.getColumna1(), recepcion.getColumna1());
            segundaElucion(1);
        }
        if (recepcion.getSegundaelucion2()) {
            jtMolibdeno.setRowSelectionInterval(recepcion.getFila2(), recepcion.getFila2());
            jtMolibdeno.setColumnSelectionInterval(recepcion.getColumna2(), recepcion.getColumna2());
            segundaElucion(2);
        }
        if (recepcion.getSegundaelucion3()) {
            jtMolibdeno.setRowSelectionInterval(recepcion.getFila3(), recepcion.getFila3());
            jtMolibdeno.setColumnSelectionInterval(recepcion.getColumna3(), recepcion.getColumna3());
            segundaElucion(3);
        }
        if (recepcion.getSegundaelucion4()) {
            jtMolibdeno.setRowSelectionInterval(recepcion.getFila4(), recepcion.getFila4());
            jtMolibdeno.setColumnSelectionInterval(recepcion.getColumna4(), recepcion.getColumna4());
            segundaElucion(4);
        }
        if (recepcion.getSegundaelucion5()) {
            jtMolibdeno.setRowSelectionInterval(recepcion.getFila5(), recepcion.getFila5());
            jtMolibdeno.setColumnSelectionInterval(recepcion.getColumna5(), recepcion.getColumna5());
            segundaElucion(5);
        }
        if (recepcion.getSegundaelucion6()) {
            jtMolibdeno.setRowSelectionInterval(recepcion.getFila6(), recepcion.getFila6());
            jtMolibdeno.setColumnSelectionInterval(recepcion.getColumna6(), recepcion.getColumna6());
            segundaElucion(6);
        }

    }

    private void calcularActividadMolibdeno() {
        recepciongeneradorVo recepcion = (recepciongeneradorVo) jxtlistaRecepcion.getModel().getValueAt(jxtlistaRecepcion.getSelectedRow(), 4);
        int s = recepcion.getHora().getHours() - 7;
        Double r;// = recepcion.getActividad() * Math.exp(configuracion.getN() / configuracion.getMt2() * (24 - configuracion.getQ()));
        int Nn = -1;
        int Nx = 24;
        int Sx = 0;
        boolean sw = true;
        //if(s==1)sw=false;
        for (int j = 0; j < jtMolibdeno.getRowCount(); j++) {
            //  sw = true;
            Sx = 0;
            Nn = Nn + 1;
            for (int i = 0; i < jtMolibdeno.getColumnCount(); i++) {
                if (i == 0) {
                    if (j == s) {
                        r = recepcion.getActividad();
                        sw = false;
                    } else {
                        if (sw) {
                            r = 0.0;
                        } else {
                            r = recepcion.getActividad() * Math.exp(configuracion.getN() / configuracion.getMt2() * (Nn - s));
                        }
                    }
                } else {
                    Sx = Nx * i + Nn;
                    r = recepcion.getActividad() * Math.exp(configuracion.getN() / configuracion.getMt2() * (Sx - s));
                }
                jtMolibdeno.getModel().setValueAt(fijarNumero(r, 5), j, i);
                if (j == 0) {
                    jtTecnecio.getModel().setValueAt(fijarNumero(r, 5)*0.8796, 0, i);
                }
            }

        }

    }

    public static double fijarNumero(double numero, int digitos) {
        double resultado;
        digitos = 3;
        resultado = numero * Math.pow(10, digitos);
        resultado = Math.round(resultado);
        resultado = resultado / Math.pow(10, digitos);
        return resultado;
    }

    private void calcularActividadTecnecio() {
        Double c = 0.0;
        recepciongeneradorVo recepcion = (recepciongeneradorVo) jxtlistaRecepcion.getModel().getValueAt(jxtlistaRecepcion.getSelectedRow(), 4);
        int s = recepcion.getHora().getHours() - 7;
        Double r = recepcion.getActividad() * Math.exp(configuracion.getN() / configuracion.getMt2() * (24 - configuracion.getQ()));
        boolean sw = true;

        if (s == 1) {
            c = Double.valueOf(jtConstantes.getModel().getValueAt(22, 2).toString());
            Double r2 = c * Double.valueOf(jtTecnecio.getModel().getValueAt(0, 1).toString());
            jtTecnecio.getModel().setValueAt(fijarNumero(r2, 5), 0, 1);
        }
        if (s == 2) {
            c = Double.valueOf(jtConstantes.getModel().getValueAt(21, 2).toString());
            Double r2 = c * Double.valueOf(jtTecnecio.getModel().getValueAt(0, 1).toString());
            jtTecnecio.getModel().setValueAt(fijarNumero(r2, 5), 0, 1);
        }
        if (s == 3) {
            c = Double.valueOf(jtConstantes.getModel().getValueAt(20, 2).toString());
            Double r2 = c * Double.valueOf(jtTecnecio.getModel().getValueAt(0, 1).toString());
            jtTecnecio.getModel().setValueAt(fijarNumero(r2, 5), 0, 1);
        }
        if (s == 4) {
            c = Double.valueOf(jtConstantes.getModel().getValueAt(19, 2).toString());
            Double r2 = c * Double.valueOf(jtTecnecio.getModel().getValueAt(0, 1).toString());
            jtTecnecio.getModel().setValueAt(fijarNumero(r2, 5), 0, 1);
        }
        if (s == 5) {
            c = Double.valueOf(jtConstantes.getModel().getValueAt(18, 2).toString());
            Double r2 = c * Double.valueOf(jtTecnecio.getModel().getValueAt(0, 1).toString());
            jtTecnecio.getModel().setValueAt(fijarNumero(r2, 5), 0, 1);
        }
        if (s == 6) {
            c = Double.valueOf(jtConstantes.getModel().getValueAt(17, 2).toString());
            Double r2 = c * Double.valueOf(jtTecnecio.getModel().getValueAt(0, 1).toString());
            jtTecnecio.getModel().setValueAt(fijarNumero(r2, 5), 0, 1);
        }
        if (s == 7) {
            c = Double.valueOf(jtConstantes.getModel().getValueAt(16, 2).toString());
            Double r2 = c * Double.valueOf(jtTecnecio.getModel().getValueAt(0, 1).toString());
            jtTecnecio.getModel().setValueAt(fijarNumero(r2, 5), 0, 1);
        }
        if (s == 8) {
            c = Double.valueOf(jtConstantes.getModel().getValueAt(15, 2).toString());
            Double r2 = c * Double.valueOf(jtTecnecio.getModel().getValueAt(0, 1).toString());
            jtTecnecio.getModel().setValueAt(fijarNumero(r2, 5), 0, 1);
        }
        if (s == 9) {
            c = Double.valueOf(jtConstantes.getModel().getValueAt(14, 2).toString());
            Double r2 = c * Double.valueOf(jtTecnecio.getModel().getValueAt(0, 1).toString());
            jtTecnecio.getModel().setValueAt(fijarNumero(r2, 5), 0, 1);
        }
        if (s == 10) {
            c = Double.valueOf(jtConstantes.getModel().getValueAt(13, 2).toString());
            Double r2 = c * Double.valueOf(jtTecnecio.getModel().getValueAt(0, 1).toString());
            jtTecnecio.getModel().setValueAt(fijarNumero(r2, 5), 0, 1);
        }
        if (s == 11) {
            c = Double.valueOf(jtConstantes.getModel().getValueAt(12, 2).toString());
            Double r2 = c * Double.valueOf(jtTecnecio.getModel().getValueAt(0, 1).toString());
            jtTecnecio.getModel().setValueAt(fijarNumero(r2, 5), 0, 1);
        }
        if (s == 0) {
            sw = false;
        }
        for (int j = 1; j < jtTecnecio.getRowCount(); j++) {

            for (int i = 0; i < jtTecnecio.getColumnCount(); i++) {
                if (i == 0) {
                    if (j == s) {
                        r = recepcion.getActividad();
                        sw = false;
                    } else {
                        if (sw) {
                            r = 0.0;
                        } else {
                            r = recepcion.getActividad() * Math.exp(configuracion.getN() / configuracion.getT2() * (j - s));
                        }
                    }
                } else {

                    r = Double.parseDouble(jtTecnecio.getModel().getValueAt(0, i).toString()) * Math.exp(configuracion.getN() / configuracion.getT2() * j);
                }
                jtTecnecio.getModel().setValueAt(fijarNumero(r, 5), j, i);
            }
        }

    }

    private void recalculoActividadtecnecio(int fila,int columna) {
        recepciongeneradorVo recepcion = (recepciongeneradorVo) jxtlistaRecepcion.getModel().getValueAt(jxtlistaRecepcion.getSelectedRow(), 4);
        Double r = recepcion.getActividad() * Math.exp(configuracion.getN() / configuracion.getMt2() * (24 - configuracion.getQ()));
        int cont=0;
        for (int j = fila+1; j < jtTecnecio.getRowCount(); j++) {
            cont++;
                    r = Double.parseDouble(jtTecnecio.getModel().getValueAt(fila, columna).toString())* Math.exp(configuracion.getN() / configuracion.getT2() * cont);
                jtTecnecio.getModel().setValueAt(fijarNumero(r, 5), j, columna);
        }
    }
    
    private void segundaElucion(int numero) {
        recepciongeneradorVo recepcion = (recepciongeneradorVo) jxtlistaRecepcion.getModel().getValueAt(jxtlistaRecepcion.getSelectedRow(), 4);

        int s = 0;
        int columna = -1;
        int fila = -1;
        switch (numero) {
            case 1: {
                columna = recepcion.getColumna1();
                fila = recepcion.getFila1();
                break;
            }
            case 2: {
                columna = recepcion.getColumna2();
                fila = recepcion.getFila2();
                break;
            }
            case 3: {
                columna = recepcion.getColumna3();
                fila = recepcion.getFila3();
                break;
            }
            case 4: {
                columna = recepcion.getColumna4();
                fila = recepcion.getFila4();
                break;
            }
            case 5: {
                columna = recepcion.getColumna5();
                fila = recepcion.getFila5();
                break;
            }
            case 6: {
                columna = recepcion.getColumna6();
                fila = recepcion.getFila6();
                break;
            }
            default:
                break;
        }
//        System.out.println("Columna->" + columna);
//        System.out.println("Fila->" + fila);

        //s=jtMolibdeno.getSelectedRow() - (recepcion.getHora().getHours() - 7) -1;
        if (jtTecnecio.getModel().getValueAt(0, columna).toString().trim().compareTo("0.0") == 0) {
            s = fila - (recepcion.getHora().getHours() - 7) - 1;
        } else {
            s = fila - 1;
        }
//        System.out.println("s*->" + s);

        if (this.jtTecnecio.getRowCount() > 0) {
            int[] filasselect = jtMolibdeno.getSelectedRows();
            Double v = (Double) jtMolibdeno.getModel().getValueAt(fila, columna);
//            System.out.println("mobil->" + v);
            Double vc = 0.0;
            vc = v;
            v = (Double.valueOf(jtConstantes.getModel().getValueAt(s, 2).toString()));
//            System.out.println("constantes->" + v);
            vc = vc * v;
            Double v2 = 0.0;
            Double v3 = vc;
            jtTecnecio.getModel().setValueAt(vc, jtMolibdeno.getSelectedRow(), columna);

            ///cambiar aki mobildeno
            s = jtTecnecio.getRowCount() - jtMolibdeno.getSelectedRow() + 11;
//            System.out.println("s->" + s);
            v = (Double.valueOf(jtConstantes.getModel().getValueAt(s, 2).toString()));
            vc = (Double) jtMolibdeno.getModel().getValueAt(0, jtMolibdeno.getSelectedColumn() + 1);
            vc = vc * v;
            jtTecnecio.getModel().setValueAt(vc, 0, jtMolibdeno.getSelectedColumn() + 1);
            for (int i = 1; i < jtTecnecio.getRowCount(); i++) {
                v2 = vc * Math.exp(configuracion.getN() / configuracion.getT2() * i);
                jtTecnecio.getModel().setValueAt(v2, i, jtMolibdeno.getSelectedColumn() + 1);

            }
            for (int i : filasselect) {

                TableCellFormatterText cellrender = new TableCellFormatterText();
                cellrender.setV(v3);
                this.jtTecnecio.getColumnModel().getColumn(jtMolibdeno.getSelectedColumn()).setCellRenderer(cellrender);
                TableCellFormatterTextCompleto cellrendercomple = new TableCellFormatterTextCompleto();
                this.jtTecnecio.getColumnModel().getColumn(jtMolibdeno.getSelectedColumn() + 1).setCellRenderer(cellrendercomple);
                disableReordenableColumnas(jtTecnecio);
            }
            recalculoActividadtecnecio(fila,columna);
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "No Existe Expedientes.");
        }
        
    }

    private void disableReordenableColumnas(javax.swing.JTable tabla) {
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setRowSorter(null);
        tabla.setAutoCreateRowSorter(false);
    }

    private String getDia(int op, String s) {
        String dia = "";
        switch (op) {
            case 0:
                dia = "Dom." + s;
                break;
            case 1:
                dia = "Lun." + s;
                break;
            case 2:
                dia = "Mar." + s;
                break;
            case 3:
                dia = "Mie." + s;
                break;
            case 4:
                dia = "Jue." + s;
                break;
            case 5:
                dia = "Vie." + s;
                break;
            case 6:
                dia = "Sab." + s;
                break;

        }
        return dia;
    }

    private void setTablaModelo(javax.swing.JTable tabla) {
        recepciongeneradorVo recepcion = (recepciongeneradorVo) jxtlistaRecepcion.getValueAt(jxtlistaRecepcion.getSelectedRow(), 4);
        Calendar f1 = DateToCalendar(recepcion.getFecha());
        f1.add(Calendar.DATE, 0);
        Calendar f2 = DateToCalendar(recepcion.getFecha());
        f2.add(Calendar.DATE, 1);
        Calendar f3 = DateToCalendar(recepcion.getFecha());
        f3.add(Calendar.DATE, 2);
        Calendar f4 = DateToCalendar(recepcion.getFecha());
        f4.add(Calendar.DATE, 3);
        Calendar f5 = DateToCalendar(recepcion.getFecha());
        f5.add(Calendar.DATE, 4);
        Calendar f6 = DateToCalendar(recepcion.getFecha());
        f6.add(Calendar.DATE, 5);
        Calendar f7 = DateToCalendar(recepcion.getFecha());
        f7.add(Calendar.DATE, 6);
        Calendar f8 = DateToCalendar(recepcion.getFecha());
        f8.add(Calendar.DATE, 7);
        Calendar f9 = DateToCalendar(recepcion.getFecha());
        f9.add(Calendar.DATE, 8);
        Calendar f10 = DateToCalendar(recepcion.getFecha());
        f10.add(Calendar.DATE, 9);
        Calendar f11 = DateToCalendar(recepcion.getFecha());
        f11.add(Calendar.DATE, 10);
        Calendar f12 = DateToCalendar(recepcion.getFecha());
        f12.add(Calendar.DATE, 11);
        Calendar f13 = DateToCalendar(recepcion.getFecha());
        f13.add(Calendar.DATE, 12);
        Calendar f14 = DateToCalendar(recepcion.getFecha());
        f14.add(Calendar.DATE, 13);
        String d1 = getDia((f1.getTime()).getDay(), "" + String.valueOf(f1.getTime().getDate()) + "-" + String.valueOf(f1.getTime().getMonth() + 1));
        String d2 = getDia((f2.getTime()).getDay(), "" + String.valueOf(f2.getTime().getDate()) + "-" + String.valueOf(f2.getTime().getMonth() + 1));
        String d3 = getDia((f3.getTime()).getDay(), "" + String.valueOf(f3.getTime().getDate()) + "-" + String.valueOf(f3.getTime().getMonth() + 1));
        String d4 = getDia((f4.getTime()).getDay(), "" + String.valueOf(f4.getTime().getDate()) + "-" + String.valueOf(f4.getTime().getMonth() + 1));
        String d5 = getDia((f5.getTime()).getDay(), "" + String.valueOf(f5.getTime().getDate()) + "-" + String.valueOf(f5.getTime().getMonth() + 1));
        String d6 = getDia((f6.getTime()).getDay(), "" + String.valueOf(f6.getTime().getDate()) + "-" + String.valueOf(f6.getTime().getMonth() + 1));
        String d7 = getDia((f7.getTime()).getDay(), "" + String.valueOf(f7.getTime().getDate()) + "-" + String.valueOf(f7.getTime().getMonth() + 1));
        String d8 = getDia((f8.getTime()).getDay(), "" + String.valueOf(f8.getTime().getDate()) + "-" + String.valueOf(f8.getTime().getMonth() + 1));
        String d9 = getDia((f9.getTime()).getDay(), "" + String.valueOf(f9.getTime().getDate()) + "-" + String.valueOf(f9.getTime().getMonth() + 1));
        String d10 = getDia((f10.getTime()).getDay(), "" + String.valueOf(f10.getTime().getDate()) + "-" + String.valueOf(f10.getTime().getMonth() + 1));
        String d11 = getDia((f11.getTime()).getDay(), "" + String.valueOf(f11.getTime().getDate()) + "-" + String.valueOf(f11.getTime().getMonth() + 1));
        String d12 = getDia((f12.getTime()).getDay(), "" + String.valueOf(f12.getTime().getDate()) + "-" + String.valueOf(f12.getTime().getMonth() + 1));
        String d13 = getDia((f13.getTime()).getDay(), "" + String.valueOf(f13.getTime().getDate()) + "-" + String.valueOf(f13.getTime().getMonth() + 1));
        String d14 = getDia((f14.getTime()).getDay(), "" + String.valueOf(f14.getTime().getDate()) + "-" + String.valueOf(f14.getTime().getMonth() + 1));
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
                },
                new String[]{
                    d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

    }

    private void setTablaHora(javax.swing.JTable tabla) {
        //recepciongeneradorVo recepcion = (recepciongeneradorVo) jxtlistaRecepcion.getValueAt(jxtlistaRecepcion.getSelectedRow(), 4);

        tabla.getModel().setValueAt(new Time(7, 0, 0), 0, 0);
        tabla.getModel().setValueAt(new Time(8, 0, 0), 1, 0);
        tabla.getModel().setValueAt(new Time(9, 0, 0), 2, 0);
        tabla.getModel().setValueAt(new Time(10, 0, 0), 3, 0);
        tabla.getModel().setValueAt(new Time(11, 0, 0), 4, 0);
        tabla.getModel().setValueAt(new Time(12, 0, 0), 5, 0);
        tabla.getModel().setValueAt(new Time(13, 0, 0), 6, 0);
        tabla.getModel().setValueAt(new Time(14, 0, 0), 7, 0);
        tabla.getModel().setValueAt(new Time(15, 0, 0), 8, 0);
        tabla.getModel().setValueAt(new Time(16, 0, 0), 9, 0);
        tabla.getModel().setValueAt(new Time(17, 0, 0), 10, 0);
        tabla.getModel().setValueAt(new Time(18, 0, 0), 11, 0);

    }

    private void setTablas() {
        jtConstantes = new javax.swing.JTable();
        jxtlistaRecepcion = new org.jdesktop.swingx.JXTable();
        jxtlistaRecepcion.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Tipo Reactivo", "Fecha", "Hora", "Actividad", "Obj"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jxtlistaRecepcion.getTableHeader().setReorderingAllowed(false);
        jxtlistaRecepcion.setVisibleRowCount(5);
        jxtlistaRecepcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                //   jxtlistaRecepcionMouseReleased(evt);
            }
        });
        jxtlistaRecepcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                // jxtlistaRecepcionKeyReleased(evt);
            }
        });

        if (jxtlistaRecepcion.getColumnModel().getColumnCount() > 0) {
            jxtlistaRecepcion.getColumnModel().getColumn(1).setMinWidth(80);
            jxtlistaRecepcion.getColumnModel().getColumn(1).setPreferredWidth(80);
            jxtlistaRecepcion.getColumnModel().getColumn(1).setMaxWidth(80);
            jxtlistaRecepcion.getColumnModel().getColumn(2).setMinWidth(60);
            jxtlistaRecepcion.getColumnModel().getColumn(2).setPreferredWidth(60);
            jxtlistaRecepcion.getColumnModel().getColumn(2).setMaxWidth(60);
            jxtlistaRecepcion.getColumnModel().getColumn(3).setMinWidth(60);
            jxtlistaRecepcion.getColumnModel().getColumn(3).setPreferredWidth(60);
            jxtlistaRecepcion.getColumnModel().getColumn(3).setMaxWidth(60);
            jxtlistaRecepcion.getColumnModel().getColumn(4).setMinWidth(0);
            jxtlistaRecepcion.getColumnModel().getColumn(4).setPreferredWidth(0);
            jxtlistaRecepcion.getColumnModel().getColumn(4).setMaxWidth(0);
        }
        jtConstantes = new javax.swing.JTable();
        jxtlistaRecepcion.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Tipo Reactivo", "Fecha", "Hora", "Actividad", "Obj"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jxtlistaRecepcion.getTableHeader().setReorderingAllowed(false);
        jxtlistaRecepcion.setVisibleRowCount(5);
        jxtlistaRecepcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                // jxtlistaRecepcionMouseReleased(evt);
            }
        });
        jxtlistaRecepcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                // jxtlistaRecepcionKeyReleased(evt);
            }
        });
//        jScrollPane1.setViewportView(jxtlistaRecepcion);
        if (jxtlistaRecepcion.getColumnModel().getColumnCount() > 0) {
            jxtlistaRecepcion.getColumnModel().getColumn(1).setMinWidth(80);
            jxtlistaRecepcion.getColumnModel().getColumn(1).setPreferredWidth(80);
            jxtlistaRecepcion.getColumnModel().getColumn(1).setMaxWidth(80);
            jxtlistaRecepcion.getColumnModel().getColumn(2).setMinWidth(60);
            jxtlistaRecepcion.getColumnModel().getColumn(2).setPreferredWidth(60);
            jxtlistaRecepcion.getColumnModel().getColumn(2).setMaxWidth(60);
            jxtlistaRecepcion.getColumnModel().getColumn(3).setMinWidth(60);
            jxtlistaRecepcion.getColumnModel().getColumn(3).setPreferredWidth(60);
            jxtlistaRecepcion.getColumnModel().getColumn(3).setMaxWidth(60);
            jxtlistaRecepcion.getColumnModel().getColumn(4).setMinWidth(0);
            jxtlistaRecepcion.getColumnModel().getColumn(4).setPreferredWidth(0);
            jxtlistaRecepcion.getColumnModel().getColumn(4).setMaxWidth(0);
        }
        jtConstantes.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"1", "9.51", "0.0951", "10.91848", "0.109185"},
                    {"2", "18.08", "0.1808", "20.75775", "0.207577"},
                    {"3", "25.79", "0.2579", "29.60964", "0.0296096"},
                    {"4", "32.74", "0.3274", "37.58898", "0.37589"},
                    {"5", "39", "0.39", "44.77612", "0.447761"},
                    {"6", "44.63", "0.4463", "51.23995", "0.512399"},
                    {"7", "49.71", "0.4971", "57.07233", "0.570723"},
                    {"8", "54.28", "0.5428", "62.31917", "0.623191"},
                    {"9", "58.4", "0.584", "67.04937", "0.670493"},
                    {"10", "62.11", "0.6211", "71.30884", "0.713088"},
                    {"11", "65.8", "0.658", "75.54535", "0.755453"},
                    {"12", "68.46", "0.6846", "78.59931", "0.785993"},
                    {"13", "71.5", "0.715", "82.08955", "0.820895"},
                    {"14", "73.61", "0.7361", "84.51206", "0.845120"},
                    {"15", "75.7", "0.757", null, null},
                    {"16", "77.79", "0.7779", "89.31114", "0.893111"},
                    {"17", "79.48", "0.7948", "0", "0"},
                    {"18", "81.17", "0.8117", "93.19173", "0.931917"},
                    {"19", "82.545", "0.82545", null, null},
                    {"20", "83.92", "0.8392", "96.34902", "0.963490"},
                    {"21", "85.035", "0.85035", null, null},
                    {"22", "86.15", "0.8615", "98.9093", "0.989093"},
                    {"23", "87.1", "0.871", "100", "1"},
                    {"24", "87.96", "0.8796", null, null},
                    {"36", "93.19", "0.9319", null, null},
                    {"48", "95.08", "0.9508", null, null},
                    {"60", "95.55", "0.9555", null, null},
                    {"72", "95.6", "0.956", null, null}
                },
                new String[]{
                    "Horas desde la Primera", "%", "F", "%", "F"
                }
        ));
        jtConstantes.getTableHeader().setReorderingAllowed(false);

        jtMolibdeno = new javax.swing.JTable();
        jtMolibdeno.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
                },
                new String[]{
                    "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jtMolibdeno.getTableHeader().setReorderingAllowed(false);

        if (jtMolibdeno.getColumnModel().getColumnCount() > 0) {
            jtMolibdeno.getColumnModel().getColumn(5).setPreferredWidth(0);
            jtMolibdeno.getColumnModel().getColumn(6).setPreferredWidth(0);
            jtMolibdeno.getColumnModel().getColumn(12).setPreferredWidth(0);
            jtMolibdeno.getColumnModel().getColumn(13).setPreferredWidth(0);
        }
        jtTecnecio = new javax.swing.JTable();
        jtTecnecio.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
                },
                new String[]{
                    "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jtTecnecio.getTableHeader().setReorderingAllowed(false);

        if (jtTecnecio.getColumnModel().getColumnCount() > 0) {
            jtTecnecio.getColumnModel().getColumn(5).setPreferredWidth(0);
            jtTecnecio.getColumnModel().getColumn(6).setPreferredWidth(0);
            jtTecnecio.getColumnModel().getColumn(12).setPreferredWidth(0);
            jtTecnecio.getColumnModel().getColumn(13).setPreferredWidth(0);
        }
        jtHoraM = new javax.swing.JTable();
        jtHoraT = new javax.swing.JTable();
        jtHoraM.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null},
                    {null},
                    {null},
                    {null},
                    {null},
                    {null},
                    {null},
                    {null},
                    {null},
                    {null},
                    {null},
                    {null}
                },
                new String[]{
                    "Hora"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jtHoraM.getTableHeader().setReorderingAllowed(false);

        jtHoraT.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null},
                    {null},
                    {null},
                    {null},
                    {null},
                    {null},
                    {null},
                    {null},
                    {null},
                    {null},
                    {null},
                    {null}
                },
                new String[]{
                    "Hora"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jtHoraT.getTableHeader().setReorderingAllowed(false);

    }

    private void setCbTipoEstudio() {
        jcbTipoEstudio.removeAll();
        ArrayListComboBoxModel model = new ArrayListComboBoxModel(tipoEstudioDao1.comboTipoEstudio());
        jcbTipoEstudio.setModel(model);
        DefaultListCellRenderer renderer = new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof tipoEstudioVo) {
                    tipoEstudioVo tipoestadoVo = (tipoEstudioVo) value;
                    setText(tipoestadoVo.getDescripcion());
                }
                return this;
            }
        };
        jcbTipoEstudio.setRenderer(renderer);
        jcbTipoEstudio.updateUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        tipoEstudioDao1 = new Dao.tipoEstudioDao();
        recepciongeneradorDao1 = new Dao.recepciongeneradorDao();
        personaTipoEstudioDao1 = new Dao.personaTipoEstudioDao();
        vpersonaestudioDao1 = new Dao.vpersonaestudioDao();
        jxhTitulo = new org.jdesktop.swingx.JXHeader();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jcbTipoEstudio = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jlRadio = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jlDosis = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jlProtocolo = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jpFecha = new javax.swing.JPanel();
        jpFecha.add(h1.createAndShowGUI());
        jPanel9 = new javax.swing.JPanel();
        jlTecnecio = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtListaPersonas = new javax.swing.JTable();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jxhTitulo.setDescriptionFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(jxhTitulo, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.GridLayout(8, 1));

        jPanel4.setLayout(new java.awt.BorderLayout());

        jcbTipoEstudio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbTipoEstudio.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jcbTipoEstudioPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jcbTipoEstudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTipoEstudioActionPerformed(evt);
            }
        });
        jcbTipoEstudio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jcbTipoEstudioKeyReleased(evt);
            }
        });
        jPanel4.add(jcbTipoEstudio, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Tipo Estudio: ");
        jPanel4.add(jLabel1, java.awt.BorderLayout.WEST);

        jPanel1.add(jPanel4);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jlRadio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlRadio.setText("Radiofármaco");
        jPanel11.add(jlRadio, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel11);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jlDosis.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlDosis.setText("Dosis (mCi)");
        jPanel10.add(jlDosis, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel10);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jlProtocolo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlProtocolo.setText("Protocolo:");
        jPanel5.add(jlProtocolo, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel5);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Hora de Inyección de Material:");
        jPanel7.add(jLabel2, java.awt.BorderLayout.WEST);
        jPanel7.add(jpFecha, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel7);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jlTecnecio.setText("Tecnecio:");
        jPanel9.add(jlTecnecio, java.awt.BorderLayout.CENTER);
        jlTecnecio.getAccessibleContext().setAccessibleName("Tecnecio");

        jPanel1.add(jPanel9);

        jButton1.setText("Confirmar Estudio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton1);

        jButton2.setText("Cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton2);

        jPanel1.add(jPanel8);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(0, 150));

        jtListaPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre ", "Estudio", "Hora Inyecion", "Obj"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtListaPersonas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtListaPersonas);
        if (jtListaPersonas.getColumnModel().getColumnCount() > 0) {
            jtListaPersonas.getColumnModel().getColumn(1).setPreferredWidth(120);
            jtListaPersonas.getColumnModel().getColumn(2).setMinWidth(80);
            jtListaPersonas.getColumnModel().getColumn(2).setPreferredWidth(80);
            jtListaPersonas.getColumnModel().getColumn(2).setMaxWidth(80);
            jtListaPersonas.getColumnModel().getColumn(3).setMinWidth(0);
            jtListaPersonas.getColumnModel().getColumn(3).setPreferredWidth(0);
            jtListaPersonas.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        double porcentaje=0;
        String fileName = "C:\\Users\\Nacho\\Documents\\imnsu\\build\\classes\\resources\\img\\fuzzy.fcl";
        FIS fis = FIS.load(fileName,true);
        // Error while loading?
        if( fis == null ) { 
            System.err.println("Can't load file: '" + fileName + "'");
            return;
        }
        tipoEstudioVo tipoestudio=(tipoEstudioVo)jcbTipoEstudio.getSelectedItem();
        System.out.println(tipoestudio.getId());
        switch(tipoestudio.getId()){
                
            case 1:
            // Set inputs
            fis.setVariable("estudio1", "edad", persona.getEdad());
            fis.setVariable("estudio1", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio1").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 2:
            // Set inputs
            fis.setVariable("estudio2_8_22", "edad", persona.getEdad());
            fis.setVariable("estudio2_8_22", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio2_8_22").getVariable("porcentaje").getLatestDefuzzifiedValue();
            fis.getFunctionBlock("estudio2_8_22").getVariable("porcentaje").chartDefuzzifier(true);
            fis.getFunctionBlock("estudio2_8_22").chart();

            break;   
            case 3:
            // Set inputs
            fis.setVariable("estudio3_5_13", "edad", persona.getEdad());
            fis.setVariable("estudio3_5_13", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio3_5_13").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;   
            case 4:
            // Set inputs
            fis.setVariable("estudio4", "edad", persona.getEdad());
            fis.setVariable("estudio4", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio4").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;   
            case 5:
            // Set inputs
            fis.setVariable("estudio3_5_13", "edad", persona.getEdad());
            fis.setVariable("estudio3_5_13", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio3_5_13").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;   
            case 6:
            // Set inputs
            fis.setVariable("estudio6_9_10_25", "edad", persona.getEdad());
            fis.setVariable("estudio6_9_10_25", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio6_9_10_25").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;   
            case 7:
            // Set inputs
            fis.setVariable("estudio7", "edad", persona.getEdad());
            fis.setVariable("estudio7", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio7").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;   
            case 8:
            // Set inputs
            fis.setVariable("estudio2_8_22", "edad", persona.getEdad());
            fis.setVariable("estudio2_8_22", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio2_8_22").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break; 
            case 9:
            // Set inputs
            fis.setVariable("estudio6_9_10_25", "edad", persona.getEdad());
            fis.setVariable("estudio6_9_10_25", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate();
            porcentaje=fis.getFunctionBlock("estudio6_9_10_25").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 10:
            // Set inputs
            fis.setVariable("estudio6_9_10_25", "edad", persona.getEdad());
            fis.setVariable("estudio6_9_10_25", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio6_9_10_25").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 11:
            // Set inputs
            fis.setVariable("estudio11", "edad", persona.getEdad());
            fis.setVariable("estudio11", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate();
            porcentaje=fis.getFunctionBlock("estudio11").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 12:
            // Set inputs
            fis.setVariable("estudio12_18", "edad", persona.getEdad());
            fis.setVariable("estudio12_18", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio12_18").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 13:
            // Set inputs
            fis.setVariable("estudio3_5_13", "edad", persona.getEdad());
            fis.setVariable("estudio3_5_13", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio3_5_13").getVariable("porcentaje").getLatestDefuzzifiedValue();
//            System.out.println(fis.getFunctionBlock("estudio3_5_13").getVariable("porcentaje").getLatestDefuzzifiedValue());
//            System.out.println(Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
//            System.out.println(persona.getEdad());
            break;
            case 14:
            // Set inputs
            fis.setVariable("estudio14_15_19_20_26", "edad", persona.getEdad());
            fis.setVariable("estudio14_15_19_20_26", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio14_15_19_20_26").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 15:
            // Set inputs
            fis.setVariable("estudio14_15_19_20_26", "edad", persona.getEdad());
            fis.setVariable("estudio14_15_19_20_26", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio14_15_19_20_26").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 16:
            // Set inputs
            fis.setVariable("estudio16_17", "edad", persona.getEdad());
            fis.setVariable("estudio16_17", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio16_17").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 17:
            // Set inputs
            fis.setVariable("estudio16_17", "edad", persona.getEdad());
            fis.setVariable("estudio16_17", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio16_17").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 18:
            // Set inputs
            fis.setVariable("estudio12_18", "edad", persona.getEdad());
            fis.setVariable("estudio12_18", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate();
            porcentaje=fis.getFunctionBlock("estudio12_18").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 19:
            // Set inputs
            fis.setVariable("estudio14_15_19_20_26", "edad", persona.getEdad());
            fis.setVariable("estudio14_15_19_20_26", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio14_15_19_20_26").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 20:
            // Set inputs
            fis.setVariable("estudio14_15_19_20_26", "edad", persona.getEdad());
            fis.setVariable("estudio14_15_19_20_26", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio14_15_19_20_26").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 21:
            // Set inputs
            fis.setVariable("estudio21_24", "edad", persona.getEdad());
            fis.setVariable("estudio21_24", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio21_24").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 22:
            // Set inputs
            fis.setVariable("estudio2_8_22", "edad", persona.getEdad());
            fis.setVariable("estudio2_8_22", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio2_8_22").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 23:
            // Set inputs
            fis.setVariable("estudio23", "edad", persona.getEdad());
            fis.setVariable("estudio23", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio23").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 24:
            // Set inputs
            fis.setVariable("estudio21_24", "edad", persona.getEdad());
            fis.setVariable("estudio21_24", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio21_24").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 25:
            // Set inputs
            fis.setVariable("estudio6_9_10_25", "edad", persona.getEdad());
            fis.setVariable("estudio6_9_10_25", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate();
            porcentaje=fis.getFunctionBlock("estudio6_9_10_25").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 26:
            // Set inputs
            fis.setVariable("estudio14_15_19_20_26", "edad", persona.getEdad());
            fis.setVariable("estudio14_15_19_20_26", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio14_15_19_20_26").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 27:
            // Set inputs
            fis.setVariable("estudio27_28", "edad", persona.getEdad());
            fis.setVariable("estudio27_28", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio27_28").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 28:
            // Set inputs
            fis.setVariable("estudio27_28", "edad", persona.getEdad());
            fis.setVariable("estudio27_28", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate();
            porcentaje=fis.getFunctionBlock("estudio27_28").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;
            case 29:
            // Set inputs
            fis.setVariable("estudio29", "edad", persona.getEdad());
            fis.setVariable("estudio29", "actividad", Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            // Evaluate
            fis.evaluate(); 
            porcentaje=fis.getFunctionBlock("estudio29").getVariable("porcentaje").getLatestDefuzzifiedValue();
            break;                                                                                                              
         }
        //System.out.println(porcentaje);

  
        
        if(75<=porcentaje){
        int op1 = JOptionPane.showConfirmDialog(new JFrame(), "La actividad radiactiva es demasiada para realizar el estudio, tomar en cuenta si existen estudios ya agendados para esta jornada.", "Advertencia!!!", JOptionPane.OK_CANCEL_OPTION);
        if (op1 == 0) {

        
            personatipoestudioVo personatipoestudio=new personatipoestudioVo();
            personatipoestudio.setIdpersona(persona.getId());
            personatipoestudio.setIdtipoestudio(tipoestudio.getId());
            personatipoestudio.setHorainyeccion(new Time(((java.util.Date)this.h1.getHora()).getTime()));
            personatipoestudio.setIdrecepciongenerador(this.recepcion.getId());
            personatipoestudio.setMolibdeno(Float.valueOf(jtMolibdeno.getModel().getValueAt(fila, columna).toString()));
            personatipoestudio.setTecnecio(Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            personatipoestudio.setFila(fila);
            personatipoestudio.setColumna(columna);
            String[] parts = this.hora.split("-");
            String part1 = parts[0].trim();
            parts = part1.split(":");
            int hh=Integer.valueOf(parts[0].trim());
            int mm=Integer.valueOf(parts[1].trim());
            Time hh1=new Time(hh, mm, 0);
            Time hh2=new Time(hh+1, mm, 0);
            personatipoestudio.setHorainicio(hh1);
            personatipoestudio.setHorafin(hh2);
            this.persona.setEnespera(false);
            parts = this.fecha.split("-");
            String dia = parts[0]; // 004
            String mes = parts[1]; 
            String anno = parts[2];
            Timestamp feccha=new Timestamp(Integer.valueOf(anno)-1900,Integer.valueOf(mes)-1,Integer.valueOf(dia), ((java.util.Date)this.h1.getHora()).getHours(), ((java.util.Date)this.h1.getHora()).getMinutes(), ((java.util.Date)this.h1.getHora()).getSeconds(),0);
            personatipoestudio.setFechaconfirmada(feccha);
            personaDao.updatePersonaEstudio(persona);
            personaTipoEstudioDao1.insertPersonaTipoestudio(personatipoestudio);
            dispose();
            }
        }
        else{
            //JOptionPane.showMessageDialog(new JFrame(),"La Actividad Actual no es suficiente para realizar el estudio!!!","Alerta!!!",0);
       if(50<=porcentaje && porcentaje<=75){
                   int op1 = JOptionPane.showConfirmDialog(new JFrame(), "La actividad radiactiva es ideal para realizar el estudio, tomar en cuenta si existen estudios ya agendados para esta jornada y caracteristicas del paciente.", "Advertencia!!!", JOptionPane.OK_CANCEL_OPTION);
        if (op1 == 0) {

        
            personatipoestudioVo personatipoestudio=new personatipoestudioVo();
            personatipoestudio.setIdpersona(persona.getId());
            personatipoestudio.setIdtipoestudio(tipoestudio.getId());
            personatipoestudio.setHorainyeccion(new Time(((java.util.Date)this.h1.getHora()).getTime()));
            personatipoestudio.setIdrecepciongenerador(this.recepcion.getId());
            personatipoestudio.setMolibdeno(Float.valueOf(jtMolibdeno.getModel().getValueAt(fila, columna).toString()));
            personatipoestudio.setTecnecio(Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            personatipoestudio.setFila(fila);
            personatipoestudio.setColumna(columna);
            String[] parts = this.hora.split("-");
            String part1 = parts[0].trim();
            parts = part1.split(":");
            int hh=Integer.valueOf(parts[0].trim());
            int mm=Integer.valueOf(parts[1].trim());
            Time hh1=new Time(hh, mm, 0);
            Time hh2=new Time(hh+1, mm, 0);
            personatipoestudio.setHorainicio(hh1);
            personatipoestudio.setHorafin(hh2);
            this.persona.setEnespera(false);
            parts = this.fecha.split("-");
            String dia = parts[0]; // 004
            String mes = parts[1]; 
            String anno = parts[2];
            Timestamp feccha=new Timestamp(Integer.valueOf(anno)-1900,Integer.valueOf(mes)-1,Integer.valueOf(dia), ((java.util.Date)this.h1.getHora()).getHours(), ((java.util.Date)this.h1.getHora()).getMinutes(), ((java.util.Date)this.h1.getHora()).getSeconds(),0);
            personatipoestudio.setFechaconfirmada(feccha);
            personaDao.updatePersonaEstudio(persona);
            personaTipoEstudioDao1.insertPersonaTipoestudio(personatipoestudio);
            dispose();
            }
       
       }
       else {
           if(25<=porcentaje && porcentaje<=50){
                   int op1 = JOptionPane.showConfirmDialog(new JFrame(), "La actividad radiactiva podría ser suficiente para realizar el estudio, tomar en cuenta si existen estudios ya agendados para esta jornada y caracteristicas del paciente.", "Advertencia!!!", JOptionPane.OK_CANCEL_OPTION);
        if (op1 == 0) {

        
            personatipoestudioVo personatipoestudio=new personatipoestudioVo();
            personatipoestudio.setIdpersona(persona.getId());
            personatipoestudio.setIdtipoestudio(tipoestudio.getId());
            personatipoestudio.setHorainyeccion(new Time(((java.util.Date)this.h1.getHora()).getTime()));
            personatipoestudio.setIdrecepciongenerador(this.recepcion.getId());
            personatipoestudio.setMolibdeno(Float.valueOf(jtMolibdeno.getModel().getValueAt(fila, columna).toString()));
            personatipoestudio.setTecnecio(Float.valueOf(jtTecnecio.getModel().getValueAt(fila, columna).toString()));
            personatipoestudio.setFila(fila);
            personatipoestudio.setColumna(columna);
            String[] parts = this.hora.split("-");
            String part1 = parts[0].trim();
            parts = part1.split(":");
            int hh=Integer.valueOf(parts[0].trim());
            int mm=Integer.valueOf(parts[1].trim());
            Time hh1=new Time(hh, mm, 0);
            Time hh2=new Time(hh+1, mm, 0);
            personatipoestudio.setHorainicio(hh1);
            personatipoestudio.setHorafin(hh2);
            this.persona.setEnespera(false);
            parts = this.fecha.split("-");
            String dia = parts[0]; // 004
            String mes = parts[1]; 
            String anno = parts[2];
            Timestamp feccha=new Timestamp(Integer.valueOf(anno)-1900,Integer.valueOf(mes)-1,Integer.valueOf(dia), ((java.util.Date)this.h1.getHora()).getHours(), ((java.util.Date)this.h1.getHora()).getMinutes(), ((java.util.Date)this.h1.getHora()).getSeconds(),0);
            personatipoestudio.setFechaconfirmada(feccha);
            personaDao.updatePersonaEstudio(persona);
            personaTipoEstudioDao1.insertPersonaTipoestudio(personatipoestudio);
            dispose();
            }
       
       }
       
       else{
           
           if(0<=porcentaje && porcentaje<=25){
           JOptionPane.showMessageDialog(new JFrame(), "No es posible agendar el estudio la actividad no es suficiente para la realización del estudio, se recomienda poner en espera al paciente para el siguiente generador.", "Advertencia!!!", 0 );        
            }
        }
       
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jcbTipoEstudioPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jcbTipoEstudioPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbTipoEstudioPopupMenuWillBecomeInvisible

    private void jcbTipoEstudioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcbTipoEstudioKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jcbTipoEstudioKeyReleased

    private void jcbTipoEstudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTipoEstudioActionPerformed
        // TODO add your handling code here:
          tipoEstudioVo tipoestudio=(tipoEstudioVo)jcbTipoEstudio.getSelectedItem();
        jlRadio.setText("Radiofármaco: "+tipoestudio.getRadiofarmaco());
        jlDosis.setText("Dosis (mCi): "+tipoestudio.getDosis()); 
        jlProtocolo.setText("Protocolo despues de inyección: " +tipoestudio.getProtocolo());
        
    }//GEN-LAST:event_jcbTipoEstudioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcbTipoEstudio;
    private javax.swing.JLabel jlDosis;
    private javax.swing.JLabel jlProtocolo;
    private javax.swing.JLabel jlRadio;
    private javax.swing.JLabel jlTecnecio;
    private javax.swing.JPanel jpFecha;
    private javax.swing.JTable jtListaPersonas;
    private org.jdesktop.swingx.JXHeader jxhTitulo;
    private Dao.personaTipoEstudioDao personaTipoEstudioDao1;
    private Dao.recepciongeneradorDao recepciongeneradorDao1;
    private Dao.tipoEstudioDao tipoEstudioDao1;
    private Dao.vpersonaestudioDao vpersonaestudioDao1;
    // End of variables declaration//GEN-END:variables
}
