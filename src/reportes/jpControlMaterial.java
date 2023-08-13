/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import Dao.configuracionDao;
import Dao.recepciongeneradorDao;
import Vo.PersonaVo;
import Vo.configuracionVo;
import Vo.recepciongeneradorVo;
import Vo.vagendatentativaVo;
import static imnsu.radiactividad.jpRecepcion.DateToCalendar;
import static imnsu.radiactividad.jpRecepcion.fijarNumero;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import resources.img.GeneraReport;
import resources.img.JInternalFrameView;
import resources.img.TableCellFormatterText;
import resources.img.TableCellFormatterTextCompleto;
import resources.img.formato;
import resources.img.hora;

/**
 *
 * @author CONSEJO
 */
public class jpControlMaterial extends javax.swing.JPanel {

    private final JInternalFrameView iframe;
    private int dia, mes, anno;
    private final formato ff = new formato();
    /**
     * ************
     */
    private final hora h1;
    private final PersonaVo persona = null;
    private final String fecha = "";
    private final String hora = "";
    private final recepciongeneradorVo recepcion = null;
    private javax.swing.JTable jtConstantes;
    private javax.swing.JTable jtHoraM;
    private javax.swing.JTable jtHoraT;
    private javax.swing.JTable jtMolibdeno;
    private javax.swing.JTable jtTecnecio;
    private configuracionVo configuracion;
    private int fila;
    private int columna;

    /**
     * **************
     */
    /**
     * Creates new form jpAgendaProgramada
     *
     * @param iframe
     */
    public jpControlMaterial(JInternalFrameView iframe) {
        h1 = new hora();
        initComponents();
        this.configuracion = configuracionDao.getListaConconfiguracion();
        setTablas();
        this.iframe = iframe;
        dia = (new Date()).getDate();
        mes = (new Date()).getMonth();
        anno = (new Date()).getYear();
////        jxmvCalendario.setSelectionDate(new Date(anno, mes, dia)); 
        setTablaListaRecepcion();

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

    private void setTablaListaRecepcion() {
        eliminartodaslasfilastabla(jxtlistaRecepcion);
        Vector v;
        for (recepciongeneradorVo recepcion : recepciongeneradorDao.getListaRecepcion()) {
            v = new Vector();
            v.add(recepcion.getReactivo());
            v.add(recepcion.getFecha());
            v.add(recepcion.getHora());
            v.add(recepcion.getActividad());
            v.add(recepcion);
            agregar_fila_tabla(v, jxtlistaRecepcion);
//            agregar_fila_tabla(v, jXTable1);

        }

        if (jxtlistaRecepcion.getRowCount() > 0) {
            jxtlistaRecepcion.setRowSelectionInterval(0, 0);
            calcularActividad();
        }
    }

    private int getFila(String hora) {
        String s = hora.substring(0, 5).trim();
        String s2 = "";
        int col = 0;
        for (int i = 0; i < jtHoraM.getRowCount(); i++) {
            s2 = jtHoraM.getModel().getValueAt(i, 0).toString().substring(0, 5).trim();
            if (s2.substring(0, 1).compareTo("0") == 0) {
                s2 = s2.substring(1, 5);
            }
            if (s2.compareTo(s) == 0) {
                col = i;
                break;
            }
        }
        return col;
    }

//    private void setTablaListaRecepcion(recepciongeneradorVo recepcion) {
//        eliminartodaslasfilastabla(jXTable1);
//        Vector v;
//        v = new Vector();
//        v.add(recepcion.getReactivo());
//        v.add(recepcion.getFecha());
//        v.add(recepcion.getHora());
//        v.add(recepcion.getActividad());
//        v.add(recepcion);
//        agregar_fila_tabla(v, jXTable1);
//        if (jXTable1.getRowCount() > 0) {
//            jXTable1.setRowSelectionInterval(0, 0);
//            calcularActividad();
//        }
//    }
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

    private void recalculoActividadtecnecio(int fila, int columna) {
        recepciongeneradorVo recepcion = (recepciongeneradorVo) jxtlistaRecepcion.getModel().getValueAt(jxtlistaRecepcion.getSelectedRow(), 4);
        Double r = recepcion.getActividad() * Math.exp(configuracion.getN() / configuracion.getMt2() * (24 - configuracion.getQ()));
        int cont = 0;
        for (int j = fila + 1; j < jtTecnecio.getRowCount(); j++) {
            cont++;
            r = Double.parseDouble(jtTecnecio.getModel().getValueAt(fila, columna).toString()) * Math.exp(configuracion.getN() / configuracion.getT2() * cont);
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
            recalculoActividadtecnecio(fila, columna);
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
//        jxtlistaRecepcion = new org.jdesktop.swingx.JXTable();
//        jxtlistaRecepcion.setModel(new javax.swing.table.DefaultTableModel(
//                new Object[][]{},
//                new String[]{
//                    "Tipo Reactivo", "Fecha", "Hora", "Actividad", "Obj"
//                }
//        ) {
//            boolean[] canEdit = new boolean[]{
//                false, false, false, false, false
//            };
//
//            public boolean isCellEditable(int rowIndex, int columnIndex) {
//                return canEdit[columnIndex];
//            }
//        });
//        jxtlistaRecepcion.getTableHeader().setReorderingAllowed(false);
//        jxtlistaRecepcion.setVisibleRowCount(5);
//        jxtlistaRecepcion.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseReleased(java.awt.event.MouseEvent evt) {
//                //   jxtlistaRecepcionMouseReleased(evt);
//            }
//        });
//        jxtlistaRecepcion.addKeyListener(new java.awt.event.KeyAdapter() {
//            public void keyReleased(java.awt.event.KeyEvent evt) {
//                // jxtlistaRecepcionKeyReleased(evt);
//            }
//        });
//
//        if (jxtlistaRecepcion.getColumnModel().getColumnCount() > 0) {
//            jxtlistaRecepcion.getColumnModel().getColumn(1).setMinWidth(80);
//            jxtlistaRecepcion.getColumnModel().getColumn(1).setPreferredWidth(80);
//            jxtlistaRecepcion.getColumnModel().getColumn(1).setMaxWidth(80);
//            jxtlistaRecepcion.getColumnModel().getColumn(2).setMinWidth(60);
//            jxtlistaRecepcion.getColumnModel().getColumn(2).setPreferredWidth(60);
//            jxtlistaRecepcion.getColumnModel().getColumn(2).setMaxWidth(60);
//            jxtlistaRecepcion.getColumnModel().getColumn(3).setMinWidth(60);
//            jxtlistaRecepcion.getColumnModel().getColumn(3).setPreferredWidth(60);
//            jxtlistaRecepcion.getColumnModel().getColumn(3).setMaxWidth(60);
//            jxtlistaRecepcion.getColumnModel().getColumn(4).setMinWidth(0);
//            jxtlistaRecepcion.getColumnModel().getColumn(4).setPreferredWidth(0);
//            jxtlistaRecepcion.getColumnModel().getColumn(4).setMaxWidth(0);
//        }
//        jtConstantes = new javax.swing.JTable();
//        jxtlistaRecepcion.setModel(new javax.swing.table.DefaultTableModel(
//                new Object[][]{},
//                new String[]{
//                    "Tipo Reactivo", "Fecha", "Hora", "Actividad", "Obj"
//                }
//        ) {
//            boolean[] canEdit = new boolean[]{
//                false, false, false, false, false
//            };
//
//            public boolean isCellEditable(int rowIndex, int columnIndex) {
//                return canEdit[columnIndex];
//            }
//        });
//        jxtlistaRecepcion.getTableHeader().setReorderingAllowed(false);
//        jxtlistaRecepcion.setVisibleRowCount(5);
//        jxtlistaRecepcion.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseReleased(java.awt.event.MouseEvent evt) {
//                // jxtlistaRecepcionMouseReleased(evt);
//            }
//        });
//        jxtlistaRecepcion.addKeyListener(new java.awt.event.KeyAdapter() {
//            public void keyReleased(java.awt.event.KeyEvent evt) {
//                // jxtlistaRecepcionKeyReleased(evt);
//            }
//        });
////        jScrollPane1.setViewportView(jxtlistaRecepcion);
//        if (jxtlistaRecepcion.getColumnModel().getColumnCount() > 0) {
//            jxtlistaRecepcion.getColumnModel().getColumn(1).setMinWidth(80);
//            jxtlistaRecepcion.getColumnModel().getColumn(1).setPreferredWidth(80);
//            jxtlistaRecepcion.getColumnModel().getColumn(1).setMaxWidth(80);
//            jxtlistaRecepcion.getColumnModel().getColumn(2).setMinWidth(60);
//            jxtlistaRecepcion.getColumnModel().getColumn(2).setPreferredWidth(60);
//            jxtlistaRecepcion.getColumnModel().getColumn(2).setMaxWidth(60);
//            jxtlistaRecepcion.getColumnModel().getColumn(3).setMinWidth(60);
//            jxtlistaRecepcion.getColumnModel().getColumn(3).setPreferredWidth(60);
//            jxtlistaRecepcion.getColumnModel().getColumn(3).setMaxWidth(60);
//            jxtlistaRecepcion.getColumnModel().getColumn(4).setMinWidth(0);
//            jxtlistaRecepcion.getColumnModel().getColumn(4).setPreferredWidth(0);
//            jxtlistaRecepcion.getColumnModel().getColumn(4).setMaxWidth(0);
//        }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vagendatentativaDao1 = new Dao.vagendatentativaDao();
        jXHeader1 = new org.jdesktop.swingx.JXHeader();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jxtlistaRecepcion = new org.jdesktop.swingx.JXTable();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jXHeader1.setTitle("CONTROL DE MATERIAL RADIACTIVO");
        add(jXHeader1, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.BorderLayout());

        jxtlistaRecepcion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo Reactivo", "Fecha", "Hora", "Actividad", "Obj"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jxtlistaRecepcion.getTableHeader().setReorderingAllowed(false);
        jxtlistaRecepcion.setVisibleRowCount(5);
        jxtlistaRecepcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jxtlistaRecepcionMouseReleased(evt);
            }
        });
        jxtlistaRecepcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jxtlistaRecepcionKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jxtlistaRecepcion);
        if (jxtlistaRecepcion.getColumnModel().getColumnCount() > 0) {
            jxtlistaRecepcion.getColumnModel().getColumn(4).setMinWidth(0);
            jxtlistaRecepcion.getColumnModel().getColumn(4).setPreferredWidth(0);
            jxtlistaRecepcion.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        jPanel8.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jButton3.setText("Generar Reporte Agenda Tentativa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        jButton1.setText("Generar Reporte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        add(jPanel2, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        GeneraReport r_bien = new GeneraReport("controlmaterial.jasper");
//        Double c = 0.0;
        recepciongeneradorVo recepcion = (recepciongeneradorVo) jxtlistaRecepcion.getModel().getValueAt(jxtlistaRecepcion.getSelectedRow(), 4);
        int s = recepcion.getHora().getHours() - 7;
        Double r = recepcion.getActividad() * Math.exp(configuracion.getN() / configuracion.getMt2() * (24 - configuracion.getQ()));
        r_bien.establecer_int("id", recepcion.getId());
        r_bien.establecer_parametro("p1", String.valueOf(fijarNumero(Double.parseDouble(this.jtTecnecio.getValueAt(s, 0).toString()), 5)));
        r_bien.establecer_parametro("p2", String.valueOf(fijarNumero(Double.parseDouble(this.jtTecnecio.getValueAt(0, 1).toString()), 5)));
        r_bien.establecer_parametro("p3", String.valueOf(fijarNumero(Double.parseDouble(this.jtTecnecio.getValueAt(0, 2).toString()), 5)));
        r_bien.establecer_parametro("p4", String.valueOf(fijarNumero(Double.parseDouble(this.jtTecnecio.getValueAt(0, 3).toString()), 5)));
        r_bien.establecer_parametro("p5", String.valueOf(fijarNumero(Double.parseDouble(this.jtTecnecio.getValueAt(0, 4).toString()), 5)));
        r_bien.establecer_parametro("p6", String.valueOf(fijarNumero(Double.parseDouble(this.jtTecnecio.getValueAt(0, 5).toString()), 5)));
        r_bien.establecer_parametro("p7", String.valueOf(fijarNumero(Double.parseDouble(this.jtTecnecio.getValueAt(0, 6).toString()), 5)));
        r_bien.establecer_parametro("p8", String.valueOf(fijarNumero(Double.parseDouble(this.jtTecnecio.getValueAt(0, 7).toString()), 5)));
        r_bien.establecer_parametro("p9", String.valueOf(fijarNumero(Double.parseDouble(this.jtTecnecio.getValueAt(0, 8).toString()), 5)));
        r_bien.establecer_parametro("p10", String.valueOf(fijarNumero(Double.parseDouble(this.jtTecnecio.getValueAt(0, 9).toString()), 5)));
        r_bien.establecer_parametro("p11", String.valueOf(fijarNumero(Double.parseDouble(this.jtTecnecio.getValueAt(0, 10).toString()), 5)));
        r_bien.establecer_parametro("p12", String.valueOf(fijarNumero(Double.parseDouble(this.jtTecnecio.getValueAt(0, 11).toString()), 5)));
        r_bien.establecer_parametro("p13", String.valueOf(fijarNumero(Double.parseDouble(this.jtTecnecio.getValueAt(0, 12).toString()), 5)));
        r_bien.establecer_parametro("p14", String.valueOf(fijarNumero(Double.parseDouble(this.jtTecnecio.getValueAt(0, 13).toString()), 5)));
        if (recepcion.getSegundaelucion1()) {
            if (recepcion.getColumna1() == 0) {
                r_bien.establecer_parametro("c1", "Con Segunda Elución");
            }
            if (recepcion.getColumna1() == 1) {
                r_bien.establecer_parametro("c2", "Con Segunda Elución");
            }
            if (recepcion.getColumna1() == 2) {
                r_bien.establecer_parametro("c3", "Con Segunda Elución");
            }
            if (recepcion.getColumna1() == 3) {
                r_bien.establecer_parametro("c4", "Con Segunda Elución");
            }
            if (recepcion.getColumna1() == 4) {
                r_bien.establecer_parametro("c5", "Con Segunda Elución");
            }
            if (recepcion.getColumna1() == 5) {
                r_bien.establecer_parametro("c6", "Con Segunda Elución");
            }
            if (recepcion.getColumna1() == 6) {
                r_bien.establecer_parametro("c7", "Con Segunda Elución");
            }
            if (recepcion.getColumna1() == 7) {
                r_bien.establecer_parametro("c8", "Con Segunda Elución");
            }
            if (recepcion.getColumna1() == 8) {
                r_bien.establecer_parametro("c9", "Con Segunda Elución");
            }
            if (recepcion.getColumna1() == 9) {
                r_bien.establecer_parametro("c10", "Con Segunda Elución");
            }
            if (recepcion.getColumna1() == 10) {
                r_bien.establecer_parametro("c11", "Con Segunda Elución");
            }
            if (recepcion.getColumna1() == 11) {
                r_bien.establecer_parametro("c12", "Con Segunda Elución");
            }
            if (recepcion.getColumna1() == 12) {
                r_bien.establecer_parametro("c13", "Con Segunda Elución");
            }
            if (recepcion.getColumna1() == 13) {
                r_bien.establecer_parametro("c14", "Con Segunda Elución");
            }
        }

        if (recepcion.getSegundaelucion2()) {
            if (recepcion.getColumna2() == 0) {
                r_bien.establecer_parametro("c1", "Con Segunda Elución");
            }
            if (recepcion.getColumna2() == 1) {
                r_bien.establecer_parametro("c2", "Con Segunda Elución");
            }
            if (recepcion.getColumna2() == 2) {
                r_bien.establecer_parametro("c3", "Con Segunda Elución");
            }
            if (recepcion.getColumna2() == 3) {
                r_bien.establecer_parametro("c4", "Con Segunda Elución");
            }
            if (recepcion.getColumna2() == 4) {
                r_bien.establecer_parametro("c5", "Con Segunda Elución");
            }
            if (recepcion.getColumna2() == 5) {
                r_bien.establecer_parametro("c6", "Con Segunda Elución");
            }
            if (recepcion.getColumna2() == 6) {
                r_bien.establecer_parametro("c7", "Con Segunda Elución");
            }
            if (recepcion.getColumna2() == 7) {
                r_bien.establecer_parametro("c8", "Con Segunda Elución");
            }
            if (recepcion.getColumna2() == 8) {
                r_bien.establecer_parametro("c9", "Con Segunda Elución");
            }
            if (recepcion.getColumna2() == 9) {
                r_bien.establecer_parametro("c10", "Con Segunda Elución");
            }
            if (recepcion.getColumna2() == 10) {
                r_bien.establecer_parametro("c11", "Con Segunda Elución");
            }
            if (recepcion.getColumna2() == 11) {
                r_bien.establecer_parametro("c12", "Con Segunda Elución");
            }
            if (recepcion.getColumna2() == 12) {
                r_bien.establecer_parametro("c13", "Con Segunda Elución");
            }
            if (recepcion.getColumna2() == 13) {
                r_bien.establecer_parametro("c14", "Con Segunda Elución");
            }
        }

        if (recepcion.getSegundaelucion3()) {
            if (recepcion.getColumna3() == 0) {
                r_bien.establecer_parametro("c1", "Con Segunda Elución");
            }
            if (recepcion.getColumna3() == 1) {
                r_bien.establecer_parametro("c2", "Con Segunda Elución");
            }
            if (recepcion.getColumna3() == 2) {
                r_bien.establecer_parametro("c3", "Con Segunda Elución");
            }
            if (recepcion.getColumna3() == 3) {
                r_bien.establecer_parametro("c4", "Con Segunda Elución");
            }
            if (recepcion.getColumna3() == 4) {
                r_bien.establecer_parametro("c5", "Con Segunda Elución");
            }
            if (recepcion.getColumna3() == 5) {
                r_bien.establecer_parametro("c6", "Con Segunda Elución");
            }
            if (recepcion.getColumna3() == 6) {
                r_bien.establecer_parametro("c7", "Con Segunda Elución");
            }
            if (recepcion.getColumna3() == 7) {
                r_bien.establecer_parametro("c8", "Con Segunda Elución");
            }
            if (recepcion.getColumna3() == 8) {
                r_bien.establecer_parametro("c9", "Con Segunda Elución");
            }
            if (recepcion.getColumna3() == 9) {
                r_bien.establecer_parametro("c10", "Con Segunda Elución");
            }
            if (recepcion.getColumna3() == 10) {
                r_bien.establecer_parametro("c11", "Con Segunda Elución");
            }
            if (recepcion.getColumna3() == 11) {
                r_bien.establecer_parametro("c12", "Con Segunda Elución");
            }
            if (recepcion.getColumna3() == 12) {
                r_bien.establecer_parametro("c13", "Con Segunda Elución");
            }
            if (recepcion.getColumna3() == 13) {
                r_bien.establecer_parametro("c14", "Con Segunda Elución");
            }
        }
        if (recepcion.getSegundaelucion4()) {
            if (recepcion.getColumna4() == 0) {
                r_bien.establecer_parametro("c1", "Con Segunda Elución");
            }
            if (recepcion.getColumna4() == 1) {
                r_bien.establecer_parametro("c2", "Con Segunda Elución");
            }
            if (recepcion.getColumna4() == 2) {
                r_bien.establecer_parametro("c3", "Con Segunda Elución");
            }
            if (recepcion.getColumna4() == 3) {
                r_bien.establecer_parametro("c4", "Con Segunda Elución");
            }
            if (recepcion.getColumna4() == 4) {
                r_bien.establecer_parametro("c5", "Con Segunda Elución");
            }
            if (recepcion.getColumna4() == 5) {
                r_bien.establecer_parametro("c6", "Con Segunda Elución");
            }
            if (recepcion.getColumna4() == 6) {
                r_bien.establecer_parametro("c7", "Con Segunda Elución");
            }
            if (recepcion.getColumna4() == 7) {
                r_bien.establecer_parametro("c8", "Con Segunda Elución");
            }
            if (recepcion.getColumna4() == 8) {
                r_bien.establecer_parametro("c9", "Con Segunda Elución");
            }
            if (recepcion.getColumna4() == 9) {
                r_bien.establecer_parametro("c10", "Con Segunda Elución");
            }
            if (recepcion.getColumna4() == 10) {
                r_bien.establecer_parametro("c11", "Con Segunda Elución");
            }
            if (recepcion.getColumna4() == 11) {
                r_bien.establecer_parametro("c12", "Con Segunda Elución");
            }
            if (recepcion.getColumna4() == 12) {
                r_bien.establecer_parametro("c13", "Con Segunda Elución");
            }
            if (recepcion.getColumna4() == 13) {
                r_bien.establecer_parametro("c14", "Con Segunda Elución");
            }
        }
        if (recepcion.getSegundaelucion5()) {
            if (recepcion.getColumna5() == 0) {
                r_bien.establecer_parametro("c1", "Con Segunda Elución");
            }
            if (recepcion.getColumna5() == 1) {
                r_bien.establecer_parametro("c2", "Con Segunda Elución");
            }
            if (recepcion.getColumna5() == 2) {
                r_bien.establecer_parametro("c3", "Con Segunda Elución");
            }
            if (recepcion.getColumna5() == 3) {
                r_bien.establecer_parametro("c4", "Con Segunda Elución");
            }
            if (recepcion.getColumna5() == 4) {
                r_bien.establecer_parametro("c5", "Con Segunda Elución");
            }
            if (recepcion.getColumna5() == 5) {
                r_bien.establecer_parametro("c6", "Con Segunda Elución");
            }
            if (recepcion.getColumna5() == 6) {
                r_bien.establecer_parametro("c7", "Con Segunda Elución");
            }
            if (recepcion.getColumna5() == 7) {
                r_bien.establecer_parametro("c8", "Con Segunda Elución");
            }
            if (recepcion.getColumna5() == 8) {
                r_bien.establecer_parametro("c9", "Con Segunda Elución");
            }
            if (recepcion.getColumna5() == 9) {
                r_bien.establecer_parametro("c10", "Con Segunda Elución");
            }
            if (recepcion.getColumna5() == 10) {
                r_bien.establecer_parametro("c11", "Con Segunda Elución");
            }
            if (recepcion.getColumna5() == 11) {
                r_bien.establecer_parametro("c12", "Con Segunda Elución");
            }
            if (recepcion.getColumna5() == 12) {
                r_bien.establecer_parametro("c13", "Con Segunda Elución");
            }
            if (recepcion.getColumna5() == 13) {
                r_bien.establecer_parametro("c14", "Con Segunda Elución");
            }
        }
        if (recepcion.getSegundaelucion6()) {
            if (recepcion.getColumna6() == 0) {
                r_bien.establecer_parametro("c1", "Con Segunda Elución");
            }
            if (recepcion.getColumna6() == 1) {
                r_bien.establecer_parametro("c2", "Con Segunda Elución");
            }
            if (recepcion.getColumna6() == 2) {
                r_bien.establecer_parametro("c3", "Con Segunda Elución");
            }
            if (recepcion.getColumna6() == 3) {
                r_bien.establecer_parametro("c4", "Con Segunda Elución");
            }
            if (recepcion.getColumna6() == 4) {
                r_bien.establecer_parametro("c5", "Con Segunda Elución");
            }
            if (recepcion.getColumna6() == 5) {
                r_bien.establecer_parametro("c6", "Con Segunda Elución");
            }
            if (recepcion.getColumna6() == 6) {
                r_bien.establecer_parametro("c7", "Con Segunda Elución");
            }
            if (recepcion.getColumna6() == 7) {
                r_bien.establecer_parametro("c8", "Con Segunda Elución");
            }
            if (recepcion.getColumna6() == 8) {
                r_bien.establecer_parametro("c9", "Con Segunda Elución");
            }
            if (recepcion.getColumna6() == 9) {
                r_bien.establecer_parametro("c10", "Con Segunda Elución");
            }
            if (recepcion.getColumna6() == 10) {
                r_bien.establecer_parametro("c11", "Con Segunda Elución");
            }
            if (recepcion.getColumna6() == 11) {
                r_bien.establecer_parametro("c12", "Con Segunda Elución");
            }
            if (recepcion.getColumna6() == 12) {
                r_bien.establecer_parametro("c13", "Con Segunda Elución");
            }
            if (recepcion.getColumna6() == 13) {
                r_bien.establecer_parametro("c14", "Con Segunda Elución");
            }
        }

//            r_bien.establecer_fechas("f2", new java.sql.Date(jxmvCalendario.getSelection().last().getTime()));
        r_bien.generar_reporte();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jxtlistaRecepcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jxtlistaRecepcionKeyReleased
        // TODO add your handling code here:
        calcularActividad();
    }//GEN-LAST:event_jxtlistaRecepcionKeyReleased

    private void jxtlistaRecepcionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jxtlistaRecepcionMouseReleased
        // TODO add your handling code here:
        calcularActividad();
    }//GEN-LAST:event_jxtlistaRecepcionMouseReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        GeneraReport r_bien = new GeneraReport("agendatentativa.jasper");
//        Double c = 0.0;
        recepciongeneradorVo recepcion = (recepciongeneradorVo) jxtlistaRecepcion.getModel().getValueAt(jxtlistaRecepcion.getSelectedRow(), 4);
//        int s = recepcion.getHora().getHours() - 7;
        r_bien.establecer_int("id", recepcion.getId());
        ArrayList<vagendatentativaVo> vagedatentativaArrayList = vagendatentativaDao1.getListaPersonaEstudio();
        if (vagedatentativaArrayList.size() > 0) {
            r_bien.establecer_parametro("p1", ((vagendatentativaVo) vagedatentativaArrayList.get(0)).getNombre());
            r_bien.establecer_parametro("c1", ((vagendatentativaVo) vagedatentativaArrayList.get(0)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 1) {
            r_bien.establecer_parametro("p2", ((vagendatentativaVo) vagedatentativaArrayList.get(1)).getNombre());
            r_bien.establecer_parametro("c2", ((vagendatentativaVo) vagedatentativaArrayList.get(1)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 2) {
            r_bien.establecer_parametro("p3", ((vagendatentativaVo) vagedatentativaArrayList.get(2)).getNombre());
            r_bien.establecer_parametro("c3", ((vagendatentativaVo) vagedatentativaArrayList.get(2)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 3) {
            r_bien.establecer_parametro("p4", ((vagendatentativaVo) vagedatentativaArrayList.get(3)).getNombre());
            r_bien.establecer_parametro("c4", ((vagendatentativaVo) vagedatentativaArrayList.get(3)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 4) {
            r_bien.establecer_parametro("p5", ((vagendatentativaVo) vagedatentativaArrayList.get(4)).getNombre());
            r_bien.establecer_parametro("c5", ((vagendatentativaVo) vagedatentativaArrayList.get(4)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 5) {
            r_bien.establecer_parametro("p6", ((vagendatentativaVo) vagedatentativaArrayList.get(5)).getNombre());
            r_bien.establecer_parametro("c6", ((vagendatentativaVo) vagedatentativaArrayList.get(5)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 6) {
            r_bien.establecer_parametro("p7", ((vagendatentativaVo) vagedatentativaArrayList.get(6)).getNombre());
            r_bien.establecer_parametro("c7", ((vagendatentativaVo) vagedatentativaArrayList.get(6)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 7) {
            r_bien.establecer_parametro("p8", ((vagendatentativaVo) vagedatentativaArrayList.get(7)).getNombre());
            r_bien.establecer_parametro("c8", ((vagendatentativaVo) vagedatentativaArrayList.get(7)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 8) {
            r_bien.establecer_parametro("p9", ((vagendatentativaVo) vagedatentativaArrayList.get(8)).getNombre());
            r_bien.establecer_parametro("c9", ((vagendatentativaVo) vagedatentativaArrayList.get(8)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 9) {
            r_bien.establecer_parametro("p10", ((vagendatentativaVo) vagedatentativaArrayList.get(9)).getNombre());
            r_bien.establecer_parametro("c10", ((vagendatentativaVo) vagedatentativaArrayList.get(9)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 10) {
            r_bien.establecer_parametro("p11", ((vagendatentativaVo) vagedatentativaArrayList.get(10)).getNombre());
            r_bien.establecer_parametro("c11", ((vagendatentativaVo) vagedatentativaArrayList.get(10)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 11) {
            r_bien.establecer_parametro("p12", ((vagendatentativaVo) vagedatentativaArrayList.get(11)).getNombre());
            r_bien.establecer_parametro("c12", ((vagendatentativaVo) vagedatentativaArrayList.get(11)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 12) {
            r_bien.establecer_parametro("p13", ((vagendatentativaVo) vagedatentativaArrayList.get(12)).getNombre());
            r_bien.establecer_parametro("c13", ((vagendatentativaVo) vagedatentativaArrayList.get(12)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 13) {
            r_bien.establecer_parametro("p14", ((vagendatentativaVo) vagedatentativaArrayList.get(13)).getNombre());
            r_bien.establecer_parametro("c14", ((vagendatentativaVo) vagedatentativaArrayList.get(13)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 14) {
            r_bien.establecer_parametro("p15", ((vagendatentativaVo) vagedatentativaArrayList.get(14)).getNombre());
            r_bien.establecer_parametro("c15", ((vagendatentativaVo) vagedatentativaArrayList.get(14)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 15) {
            r_bien.establecer_parametro("p16", ((vagendatentativaVo) vagedatentativaArrayList.get(15)).getNombre());
            r_bien.establecer_parametro("c16", ((vagendatentativaVo) vagedatentativaArrayList.get(15)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 16) {
            r_bien.establecer_parametro("p17", ((vagendatentativaVo) vagedatentativaArrayList.get(16)).getNombre());
            r_bien.establecer_parametro("c17", ((vagendatentativaVo) vagedatentativaArrayList.get(16)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 17) {
            r_bien.establecer_parametro("p18", ((vagendatentativaVo) vagedatentativaArrayList.get(17)).getNombre());
            r_bien.establecer_parametro("c18", ((vagendatentativaVo) vagedatentativaArrayList.get(17)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 18) {
            r_bien.establecer_parametro("p19", ((vagendatentativaVo) vagedatentativaArrayList.get(18)).getNombre());
            r_bien.establecer_parametro("c19", ((vagendatentativaVo) vagedatentativaArrayList.get(18)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 19) {
            r_bien.establecer_parametro("p20", ((vagendatentativaVo) vagedatentativaArrayList.get(19)).getNombre());
            r_bien.establecer_parametro("c20", ((vagendatentativaVo) vagedatentativaArrayList.get(19)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 20) {
            r_bien.establecer_parametro("p21", ((vagendatentativaVo) vagedatentativaArrayList.get(20)).getNombre());
            r_bien.establecer_parametro("c21", ((vagendatentativaVo) vagedatentativaArrayList.get(20)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 21) {
            r_bien.establecer_parametro("p22", ((vagendatentativaVo) vagedatentativaArrayList.get(21)).getNombre());
            r_bien.establecer_parametro("c22", ((vagendatentativaVo) vagedatentativaArrayList.get(21)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 22) {
            r_bien.establecer_parametro("p23", ((vagendatentativaVo) vagedatentativaArrayList.get(22)).getNombre());
            r_bien.establecer_parametro("c23", ((vagendatentativaVo) vagedatentativaArrayList.get(22)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 23) {
            r_bien.establecer_parametro("p24", ((vagendatentativaVo) vagedatentativaArrayList.get(23)).getNombre());
            r_bien.establecer_parametro("c24", ((vagendatentativaVo) vagedatentativaArrayList.get(23)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 24) {
            r_bien.establecer_parametro("p25", ((vagendatentativaVo) vagedatentativaArrayList.get(24)).getNombre());
            r_bien.establecer_parametro("c25", ((vagendatentativaVo) vagedatentativaArrayList.get(24)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 25) {
            r_bien.establecer_parametro("p26", ((vagendatentativaVo) vagedatentativaArrayList.get(25)).getNombre());
            r_bien.establecer_parametro("c26", ((vagendatentativaVo) vagedatentativaArrayList.get(25)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 26) {
            r_bien.establecer_parametro("p27", ((vagendatentativaVo) vagedatentativaArrayList.get(26)).getNombre());
            r_bien.establecer_parametro("c27", ((vagendatentativaVo) vagedatentativaArrayList.get(26)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 27) {
            r_bien.establecer_parametro("p28", ((vagendatentativaVo) vagedatentativaArrayList.get(27)).getNombre());
            r_bien.establecer_parametro("c28", ((vagendatentativaVo) vagedatentativaArrayList.get(27)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 28) {
            r_bien.establecer_parametro("p29", ((vagendatentativaVo) vagedatentativaArrayList.get(28)).getNombre());
            r_bien.establecer_parametro("c29", ((vagendatentativaVo) vagedatentativaArrayList.get(28)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 29) {
            r_bien.establecer_parametro("p30", ((vagendatentativaVo) vagedatentativaArrayList.get(29)).getNombre());
            r_bien.establecer_parametro("c30", ((vagendatentativaVo) vagedatentativaArrayList.get(29)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 30) {
            r_bien.establecer_parametro("p31", ((vagendatentativaVo) vagedatentativaArrayList.get(30)).getNombre());
            r_bien.establecer_parametro("c31", ((vagendatentativaVo) vagedatentativaArrayList.get(30)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 31) {
            r_bien.establecer_parametro("p32", ((vagendatentativaVo) vagedatentativaArrayList.get(31)).getNombre());
            r_bien.establecer_parametro("c32", ((vagendatentativaVo) vagedatentativaArrayList.get(31)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 32) {
            r_bien.establecer_parametro("p33", ((vagendatentativaVo) vagedatentativaArrayList.get(32)).getNombre());
            r_bien.establecer_parametro("c33", ((vagendatentativaVo) vagedatentativaArrayList.get(32)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 33) {
            r_bien.establecer_parametro("p34", ((vagendatentativaVo) vagedatentativaArrayList.get(33)).getNombre());
            r_bien.establecer_parametro("c34", ((vagendatentativaVo) vagedatentativaArrayList.get(33)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 34) {
            r_bien.establecer_parametro("p35", ((vagendatentativaVo) vagedatentativaArrayList.get(34)).getNombre());
            r_bien.establecer_parametro("c35", ((vagendatentativaVo) vagedatentativaArrayList.get(34)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 35) {
            r_bien.establecer_parametro("p36", ((vagendatentativaVo) vagedatentativaArrayList.get(35)).getNombre());
            r_bien.establecer_parametro("c36", ((vagendatentativaVo) vagedatentativaArrayList.get(35)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 36) {
            r_bien.establecer_parametro("p37", ((vagendatentativaVo) vagedatentativaArrayList.get(36)).getNombre());
            r_bien.establecer_parametro("c37", ((vagendatentativaVo) vagedatentativaArrayList.get(36)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 37) {
            r_bien.establecer_parametro("p38", ((vagendatentativaVo) vagedatentativaArrayList.get(37)).getNombre());
            r_bien.establecer_parametro("c38", ((vagendatentativaVo) vagedatentativaArrayList.get(37)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 38) {
            r_bien.establecer_parametro("p39", ((vagendatentativaVo) vagedatentativaArrayList.get(38)).getNombre());
            r_bien.establecer_parametro("c39", ((vagendatentativaVo) vagedatentativaArrayList.get(38)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 39) {
            r_bien.establecer_parametro("p40", ((vagendatentativaVo) vagedatentativaArrayList.get(39)).getNombre());
            r_bien.establecer_parametro("c40", ((vagendatentativaVo) vagedatentativaArrayList.get(39)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 40) {
            r_bien.establecer_parametro("p41", ((vagendatentativaVo) vagedatentativaArrayList.get(40)).getNombre());
            r_bien.establecer_parametro("c41", ((vagendatentativaVo) vagedatentativaArrayList.get(40)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 41) {
            r_bien.establecer_parametro("p42", ((vagendatentativaVo) vagedatentativaArrayList.get(41)).getNombre());
            r_bien.establecer_parametro("c42", ((vagendatentativaVo) vagedatentativaArrayList.get(41)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 42) {
            r_bien.establecer_parametro("p43", ((vagendatentativaVo) vagedatentativaArrayList.get(42)).getNombre());
            r_bien.establecer_parametro("c43", ((vagendatentativaVo) vagedatentativaArrayList.get(42)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 43) {
            r_bien.establecer_parametro("p44", ((vagendatentativaVo) vagedatentativaArrayList.get(43)).getNombre());
            r_bien.establecer_parametro("c44", ((vagendatentativaVo) vagedatentativaArrayList.get(43)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 44) {
            r_bien.establecer_parametro("p45", ((vagendatentativaVo) vagedatentativaArrayList.get(44)).getNombre());
            r_bien.establecer_parametro("c45", ((vagendatentativaVo) vagedatentativaArrayList.get(44)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 45) {
            r_bien.establecer_parametro("p46", ((vagendatentativaVo) vagedatentativaArrayList.get(45)).getNombre());
            r_bien.establecer_parametro("c46", ((vagendatentativaVo) vagedatentativaArrayList.get(45)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 46) {
            r_bien.establecer_parametro("p47", ((vagendatentativaVo) vagedatentativaArrayList.get(46)).getNombre());
            r_bien.establecer_parametro("c47", ((vagendatentativaVo) vagedatentativaArrayList.get(46)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 47) {
            r_bien.establecer_parametro("p48", ((vagendatentativaVo) vagedatentativaArrayList.get(47)).getNombre());
            r_bien.establecer_parametro("c48", ((vagendatentativaVo) vagedatentativaArrayList.get(47)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 48) {
            r_bien.establecer_parametro("p49", ((vagendatentativaVo) vagedatentativaArrayList.get(48)).getNombre());
            r_bien.establecer_parametro("c49", ((vagendatentativaVo) vagedatentativaArrayList.get(48)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 49) {
            r_bien.establecer_parametro("p50", ((vagendatentativaVo) vagedatentativaArrayList.get(49)).getNombre());
            r_bien.establecer_parametro("c50", ((vagendatentativaVo) vagedatentativaArrayList.get(49)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 50) {
            r_bien.establecer_parametro("p51", ((vagendatentativaVo) vagedatentativaArrayList.get(50)).getNombre());
            r_bien.establecer_parametro("c51", ((vagendatentativaVo) vagedatentativaArrayList.get(50)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 51) {
            r_bien.establecer_parametro("p52", ((vagendatentativaVo) vagedatentativaArrayList.get(51)).getNombre());
            r_bien.establecer_parametro("c52", ((vagendatentativaVo) vagedatentativaArrayList.get(51)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 52) {
            r_bien.establecer_parametro("p53", ((vagendatentativaVo) vagedatentativaArrayList.get(52)).getNombre());
            r_bien.establecer_parametro("c53", ((vagendatentativaVo) vagedatentativaArrayList.get(52)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 53) {
            r_bien.establecer_parametro("p54", ((vagendatentativaVo) vagedatentativaArrayList.get(53)).getNombre());
            r_bien.establecer_parametro("c54", ((vagendatentativaVo) vagedatentativaArrayList.get(53)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 54) {
            r_bien.establecer_parametro("p55", ((vagendatentativaVo) vagedatentativaArrayList.get(54)).getNombre());
            r_bien.establecer_parametro("c55", ((vagendatentativaVo) vagedatentativaArrayList.get(54)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 55) {
            r_bien.establecer_parametro("p56", ((vagendatentativaVo) vagedatentativaArrayList.get(55)).getNombre());
            r_bien.establecer_parametro("c56", ((vagendatentativaVo) vagedatentativaArrayList.get(55)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 56) {
            r_bien.establecer_parametro("p57", ((vagendatentativaVo) vagedatentativaArrayList.get(56)).getNombre());
            r_bien.establecer_parametro("c57", ((vagendatentativaVo) vagedatentativaArrayList.get(56)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 57) {
            r_bien.establecer_parametro("p58", ((vagendatentativaVo) vagedatentativaArrayList.get(57)).getNombre());
            r_bien.establecer_parametro("c58", ((vagendatentativaVo) vagedatentativaArrayList.get(57)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 58) {
            r_bien.establecer_parametro("p59", ((vagendatentativaVo) vagedatentativaArrayList.get(58)).getNombre());
            r_bien.establecer_parametro("c59", ((vagendatentativaVo) vagedatentativaArrayList.get(58)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 59) {
            r_bien.establecer_parametro("p60", ((vagendatentativaVo) vagedatentativaArrayList.get(59)).getNombre());
            r_bien.establecer_parametro("c60", ((vagendatentativaVo) vagedatentativaArrayList.get(59)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 60) {
            r_bien.establecer_parametro("p61", ((vagendatentativaVo) vagedatentativaArrayList.get(60)).getNombre());
            r_bien.establecer_parametro("c61", ((vagendatentativaVo) vagedatentativaArrayList.get(60)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 61) {
            r_bien.establecer_parametro("p62", ((vagendatentativaVo) vagedatentativaArrayList.get(61)).getNombre());
            r_bien.establecer_parametro("c62", ((vagendatentativaVo) vagedatentativaArrayList.get(61)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 62) {
            r_bien.establecer_parametro("p63", ((vagendatentativaVo) vagedatentativaArrayList.get(62)).getNombre());
            r_bien.establecer_parametro("c63", ((vagendatentativaVo) vagedatentativaArrayList.get(62)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 63) {
            r_bien.establecer_parametro("p64", ((vagendatentativaVo) vagedatentativaArrayList.get(63)).getNombre());
            r_bien.establecer_parametro("c64", ((vagendatentativaVo) vagedatentativaArrayList.get(63)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 64) {
            r_bien.establecer_parametro("p65", ((vagendatentativaVo) vagedatentativaArrayList.get(64)).getNombre());
            r_bien.establecer_parametro("c65", ((vagendatentativaVo) vagedatentativaArrayList.get(64)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 65) {
            r_bien.establecer_parametro("p66", ((vagendatentativaVo) vagedatentativaArrayList.get(65)).getNombre());
            r_bien.establecer_parametro("c66", ((vagendatentativaVo) vagedatentativaArrayList.get(65)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 66) {
            r_bien.establecer_parametro("p67", ((vagendatentativaVo) vagedatentativaArrayList.get(66)).getNombre());
            r_bien.establecer_parametro("c67", ((vagendatentativaVo) vagedatentativaArrayList.get(66)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 67) {
            r_bien.establecer_parametro("p68", ((vagendatentativaVo) vagedatentativaArrayList.get(67)).getNombre());
            r_bien.establecer_parametro("c68", ((vagendatentativaVo) vagedatentativaArrayList.get(67)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 68) {
            r_bien.establecer_parametro("p69", ((vagendatentativaVo) vagedatentativaArrayList.get(68)).getNombre());
            r_bien.establecer_parametro("c69", ((vagendatentativaVo) vagedatentativaArrayList.get(68)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 69) {
            r_bien.establecer_parametro("p70", ((vagendatentativaVo) vagedatentativaArrayList.get(69)).getNombre());
            r_bien.establecer_parametro("c70", ((vagendatentativaVo) vagedatentativaArrayList.get(69)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 70) {
            r_bien.establecer_parametro("p71", ((vagendatentativaVo) vagedatentativaArrayList.get(70)).getNombre());
            r_bien.establecer_parametro("c71", ((vagendatentativaVo) vagedatentativaArrayList.get(70)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 71) {
            r_bien.establecer_parametro("p72", ((vagendatentativaVo) vagedatentativaArrayList.get(71)).getNombre());
            r_bien.establecer_parametro("c72", ((vagendatentativaVo) vagedatentativaArrayList.get(71)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 72) {
            r_bien.establecer_parametro("p73", ((vagendatentativaVo) vagedatentativaArrayList.get(72)).getNombre());
            r_bien.establecer_parametro("c73", ((vagendatentativaVo) vagedatentativaArrayList.get(72)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 73) {
            r_bien.establecer_parametro("p74", ((vagendatentativaVo) vagedatentativaArrayList.get(73)).getNombre());
            r_bien.establecer_parametro("c74", ((vagendatentativaVo) vagedatentativaArrayList.get(73)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 74) {
            r_bien.establecer_parametro("p75", ((vagendatentativaVo) vagedatentativaArrayList.get(74)).getNombre());
            r_bien.establecer_parametro("c75", ((vagendatentativaVo) vagedatentativaArrayList.get(74)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 75) {
            r_bien.establecer_parametro("p76", ((vagendatentativaVo) vagedatentativaArrayList.get(75)).getNombre());
            r_bien.establecer_parametro("c76", ((vagendatentativaVo) vagedatentativaArrayList.get(75)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 76) {
            r_bien.establecer_parametro("p77", ((vagendatentativaVo) vagedatentativaArrayList.get(76)).getNombre());
            r_bien.establecer_parametro("c77", ((vagendatentativaVo) vagedatentativaArrayList.get(76)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 77) {
            r_bien.establecer_parametro("p78", ((vagendatentativaVo) vagedatentativaArrayList.get(77)).getNombre());
            r_bien.establecer_parametro("c78", ((vagendatentativaVo) vagedatentativaArrayList.get(77)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 78) {
            r_bien.establecer_parametro("p79", ((vagendatentativaVo) vagedatentativaArrayList.get(78)).getNombre());
            r_bien.establecer_parametro("c79", ((vagendatentativaVo) vagedatentativaArrayList.get(78)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 79) {
            r_bien.establecer_parametro("p80", ((vagendatentativaVo) vagedatentativaArrayList.get(79)).getNombre());
            r_bien.establecer_parametro("c80", ((vagendatentativaVo) vagedatentativaArrayList.get(79)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 80) {
            r_bien.establecer_parametro("p81", ((vagendatentativaVo) vagedatentativaArrayList.get(80)).getNombre());
            r_bien.establecer_parametro("c81", ((vagendatentativaVo) vagedatentativaArrayList.get(80)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 81) {
            r_bien.establecer_parametro("p82", ((vagendatentativaVo) vagedatentativaArrayList.get(81)).getNombre());
            r_bien.establecer_parametro("c82", ((vagendatentativaVo) vagedatentativaArrayList.get(81)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 82) {
            r_bien.establecer_parametro("p83", ((vagendatentativaVo) vagedatentativaArrayList.get(82)).getNombre());
            r_bien.establecer_parametro("c83", ((vagendatentativaVo) vagedatentativaArrayList.get(82)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 83) {
            r_bien.establecer_parametro("p84", ((vagendatentativaVo) vagedatentativaArrayList.get(83)).getNombre());
            r_bien.establecer_parametro("c84", ((vagendatentativaVo) vagedatentativaArrayList.get(83)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 84) {
            r_bien.establecer_parametro("p85", ((vagendatentativaVo) vagedatentativaArrayList.get(84)).getNombre());
            r_bien.establecer_parametro("c85", ((vagendatentativaVo) vagedatentativaArrayList.get(84)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 85) {
            r_bien.establecer_parametro("p86", ((vagendatentativaVo) vagedatentativaArrayList.get(85)).getNombre());
            r_bien.establecer_parametro("c86", ((vagendatentativaVo) vagedatentativaArrayList.get(85)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 86) {
            r_bien.establecer_parametro("p87", ((vagendatentativaVo) vagedatentativaArrayList.get(86)).getNombre());
            r_bien.establecer_parametro("c87", ((vagendatentativaVo) vagedatentativaArrayList.get(86)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 87) {
            r_bien.establecer_parametro("p88", ((vagendatentativaVo) vagedatentativaArrayList.get(87)).getNombre());
            r_bien.establecer_parametro("c88", ((vagendatentativaVo) vagedatentativaArrayList.get(87)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 88) {
            r_bien.establecer_parametro("p89", ((vagendatentativaVo) vagedatentativaArrayList.get(88)).getNombre());
            r_bien.establecer_parametro("c89", ((vagendatentativaVo) vagedatentativaArrayList.get(88)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 89) {
            r_bien.establecer_parametro("p90", ((vagendatentativaVo) vagedatentativaArrayList.get(89)).getNombre());
            r_bien.establecer_parametro("c90", ((vagendatentativaVo) vagedatentativaArrayList.get(89)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 90) {
            r_bien.establecer_parametro("p91", ((vagendatentativaVo) vagedatentativaArrayList.get(90)).getNombre());
            r_bien.establecer_parametro("c91", ((vagendatentativaVo) vagedatentativaArrayList.get(90)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 91) {
            r_bien.establecer_parametro("p92", ((vagendatentativaVo) vagedatentativaArrayList.get(91)).getNombre());
            r_bien.establecer_parametro("c92", ((vagendatentativaVo) vagedatentativaArrayList.get(91)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 92) {
            r_bien.establecer_parametro("p93", ((vagendatentativaVo) vagedatentativaArrayList.get(92)).getNombre());
            r_bien.establecer_parametro("c93", ((vagendatentativaVo) vagedatentativaArrayList.get(92)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 93) {
            r_bien.establecer_parametro("p94", ((vagendatentativaVo) vagedatentativaArrayList.get(93)).getNombre());
            r_bien.establecer_parametro("c94", ((vagendatentativaVo) vagedatentativaArrayList.get(93)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 94) {
            r_bien.establecer_parametro("p95", ((vagendatentativaVo) vagedatentativaArrayList.get(94)).getNombre());
            r_bien.establecer_parametro("c95", ((vagendatentativaVo) vagedatentativaArrayList.get(94)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 95) {
            r_bien.establecer_parametro("p96", ((vagendatentativaVo) vagedatentativaArrayList.get(95)).getNombre());
            r_bien.establecer_parametro("c96", ((vagendatentativaVo) vagedatentativaArrayList.get(95)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 96) {
            r_bien.establecer_parametro("p97", ((vagendatentativaVo) vagedatentativaArrayList.get(96)).getNombre());
            r_bien.establecer_parametro("c97", ((vagendatentativaVo) vagedatentativaArrayList.get(96)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 97) {
            r_bien.establecer_parametro("p98", ((vagendatentativaVo) vagedatentativaArrayList.get(97)).getNombre());
            r_bien.establecer_parametro("c98", ((vagendatentativaVo) vagedatentativaArrayList.get(97)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 98) {
            r_bien.establecer_parametro("p99", ((vagendatentativaVo) vagedatentativaArrayList.get(98)).getNombre());
            r_bien.establecer_parametro("c99", ((vagendatentativaVo) vagedatentativaArrayList.get(98)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 99) {
            r_bien.establecer_parametro("p100", ((vagendatentativaVo) vagedatentativaArrayList.get(99)).getNombre());
            r_bien.establecer_parametro("c100", ((vagendatentativaVo) vagedatentativaArrayList.get(99)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 100) {
            r_bien.establecer_parametro("p101", ((vagendatentativaVo) vagedatentativaArrayList.get(100)).getNombre());
            r_bien.establecer_parametro("c101", ((vagendatentativaVo) vagedatentativaArrayList.get(100)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 101) {
            r_bien.establecer_parametro("p102", ((vagendatentativaVo) vagedatentativaArrayList.get(101)).getNombre());
            r_bien.establecer_parametro("c102", ((vagendatentativaVo) vagedatentativaArrayList.get(101)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 102) {
            r_bien.establecer_parametro("p103", ((vagendatentativaVo) vagedatentativaArrayList.get(102)).getNombre());
            r_bien.establecer_parametro("c103", ((vagendatentativaVo) vagedatentativaArrayList.get(102)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 103) {
            r_bien.establecer_parametro("p104", ((vagendatentativaVo) vagedatentativaArrayList.get(103)).getNombre());
            r_bien.establecer_parametro("c104", ((vagendatentativaVo) vagedatentativaArrayList.get(103)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 104) {
            r_bien.establecer_parametro("p105", ((vagendatentativaVo) vagedatentativaArrayList.get(104)).getNombre());
            r_bien.establecer_parametro("c105", ((vagendatentativaVo) vagedatentativaArrayList.get(104)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 105) {
            r_bien.establecer_parametro("p106", ((vagendatentativaVo) vagedatentativaArrayList.get(105)).getNombre());
            r_bien.establecer_parametro("c106", ((vagendatentativaVo) vagedatentativaArrayList.get(105)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 106) {
            r_bien.establecer_parametro("p107", ((vagendatentativaVo) vagedatentativaArrayList.get(106)).getNombre());
            r_bien.establecer_parametro("c107", ((vagendatentativaVo) vagedatentativaArrayList.get(106)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 107) {
            r_bien.establecer_parametro("p108", ((vagendatentativaVo) vagedatentativaArrayList.get(107)).getNombre());
            r_bien.establecer_parametro("c108", ((vagendatentativaVo) vagedatentativaArrayList.get(107)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 108) {
            r_bien.establecer_parametro("p109", ((vagendatentativaVo) vagedatentativaArrayList.get(108)).getNombre());
            r_bien.establecer_parametro("c109", ((vagendatentativaVo) vagedatentativaArrayList.get(108)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 109) {
            r_bien.establecer_parametro("p110", ((vagendatentativaVo) vagedatentativaArrayList.get(109)).getNombre());
            r_bien.establecer_parametro("c110", ((vagendatentativaVo) vagedatentativaArrayList.get(109)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 110) {
            r_bien.establecer_parametro("p111", ((vagendatentativaVo) vagedatentativaArrayList.get(110)).getNombre());
            r_bien.establecer_parametro("c111", ((vagendatentativaVo) vagedatentativaArrayList.get(110)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 111) {
            r_bien.establecer_parametro("p112", ((vagendatentativaVo) vagedatentativaArrayList.get(111)).getNombre());
            r_bien.establecer_parametro("c112", ((vagendatentativaVo) vagedatentativaArrayList.get(111)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 112) {
            r_bien.establecer_parametro("p113", ((vagendatentativaVo) vagedatentativaArrayList.get(112)).getNombre());
            r_bien.establecer_parametro("c113", ((vagendatentativaVo) vagedatentativaArrayList.get(112)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 113) {
            r_bien.establecer_parametro("p114", ((vagendatentativaVo) vagedatentativaArrayList.get(113)).getNombre());
            r_bien.establecer_parametro("c114", ((vagendatentativaVo) vagedatentativaArrayList.get(113)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 114) {
            r_bien.establecer_parametro("p115", ((vagendatentativaVo) vagedatentativaArrayList.get(114)).getNombre());
            r_bien.establecer_parametro("c115", ((vagendatentativaVo) vagedatentativaArrayList.get(114)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 115) {
            r_bien.establecer_parametro("p116", ((vagendatentativaVo) vagedatentativaArrayList.get(115)).getNombre());
            r_bien.establecer_parametro("c116", ((vagendatentativaVo) vagedatentativaArrayList.get(115)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 116) {
            r_bien.establecer_parametro("p117", ((vagendatentativaVo) vagedatentativaArrayList.get(116)).getNombre());
            r_bien.establecer_parametro("c117", ((vagendatentativaVo) vagedatentativaArrayList.get(116)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 117) {
            r_bien.establecer_parametro("p118", ((vagendatentativaVo) vagedatentativaArrayList.get(117)).getNombre());
            r_bien.establecer_parametro("c118", ((vagendatentativaVo) vagedatentativaArrayList.get(117)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 118) {
            r_bien.establecer_parametro("p119", ((vagendatentativaVo) vagedatentativaArrayList.get(118)).getNombre());
            r_bien.establecer_parametro("c119", ((vagendatentativaVo) vagedatentativaArrayList.get(118)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 119) {
            r_bien.establecer_parametro("p120", ((vagendatentativaVo) vagedatentativaArrayList.get(119)).getNombre());
            r_bien.establecer_parametro("c120", ((vagendatentativaVo) vagedatentativaArrayList.get(119)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 120) {
            r_bien.establecer_parametro("p121", ((vagendatentativaVo) vagedatentativaArrayList.get(120)).getNombre());
            r_bien.establecer_parametro("c121", ((vagendatentativaVo) vagedatentativaArrayList.get(120)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 121) {
            r_bien.establecer_parametro("p122", ((vagendatentativaVo) vagedatentativaArrayList.get(121)).getNombre());
            r_bien.establecer_parametro("c122", ((vagendatentativaVo) vagedatentativaArrayList.get(121)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 122) {
            r_bien.establecer_parametro("p123", ((vagendatentativaVo) vagedatentativaArrayList.get(122)).getNombre());
            r_bien.establecer_parametro("c123", ((vagendatentativaVo) vagedatentativaArrayList.get(122)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 123) {
            r_bien.establecer_parametro("p124", ((vagendatentativaVo) vagedatentativaArrayList.get(123)).getNombre());
            r_bien.establecer_parametro("c124", ((vagendatentativaVo) vagedatentativaArrayList.get(123)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 124) {
            r_bien.establecer_parametro("p125", ((vagendatentativaVo) vagedatentativaArrayList.get(124)).getNombre());
            r_bien.establecer_parametro("c125", ((vagendatentativaVo) vagedatentativaArrayList.get(124)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 125) {
            r_bien.establecer_parametro("p126", ((vagendatentativaVo) vagedatentativaArrayList.get(125)).getNombre());
            r_bien.establecer_parametro("c126", ((vagendatentativaVo) vagedatentativaArrayList.get(125)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 126) {
            r_bien.establecer_parametro("p127", ((vagendatentativaVo) vagedatentativaArrayList.get(126)).getNombre());
            r_bien.establecer_parametro("c127", ((vagendatentativaVo) vagedatentativaArrayList.get(126)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 127) {
            r_bien.establecer_parametro("p128", ((vagendatentativaVo) vagedatentativaArrayList.get(127)).getNombre());
            r_bien.establecer_parametro("c128", ((vagendatentativaVo) vagedatentativaArrayList.get(127)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 128) {
            r_bien.establecer_parametro("p129", ((vagendatentativaVo) vagedatentativaArrayList.get(128)).getNombre());
            r_bien.establecer_parametro("c129", ((vagendatentativaVo) vagedatentativaArrayList.get(128)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 129) {
            r_bien.establecer_parametro("p130", ((vagendatentativaVo) vagedatentativaArrayList.get(129)).getNombre());
            r_bien.establecer_parametro("c130", ((vagendatentativaVo) vagedatentativaArrayList.get(129)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 130) {
            r_bien.establecer_parametro("p131", ((vagendatentativaVo) vagedatentativaArrayList.get(130)).getNombre());
            r_bien.establecer_parametro("c131", ((vagendatentativaVo) vagedatentativaArrayList.get(130)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 131) {
            r_bien.establecer_parametro("p132", ((vagendatentativaVo) vagedatentativaArrayList.get(131)).getNombre());
            r_bien.establecer_parametro("c132", ((vagendatentativaVo) vagedatentativaArrayList.get(131)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 132) {
            r_bien.establecer_parametro("p133", ((vagendatentativaVo) vagedatentativaArrayList.get(132)).getNombre());
            r_bien.establecer_parametro("c133", ((vagendatentativaVo) vagedatentativaArrayList.get(132)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 133) {
            r_bien.establecer_parametro("p134", ((vagendatentativaVo) vagedatentativaArrayList.get(133)).getNombre());
            r_bien.establecer_parametro("c134", ((vagendatentativaVo) vagedatentativaArrayList.get(133)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 134) {
            r_bien.establecer_parametro("p135", ((vagendatentativaVo) vagedatentativaArrayList.get(134)).getNombre());
            r_bien.establecer_parametro("c135", ((vagendatentativaVo) vagedatentativaArrayList.get(134)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 135) {
            r_bien.establecer_parametro("p136", ((vagendatentativaVo) vagedatentativaArrayList.get(135)).getNombre());
            r_bien.establecer_parametro("c136", ((vagendatentativaVo) vagedatentativaArrayList.get(135)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 136) {
            r_bien.establecer_parametro("p137", ((vagendatentativaVo) vagedatentativaArrayList.get(136)).getNombre());
            r_bien.establecer_parametro("c137", ((vagendatentativaVo) vagedatentativaArrayList.get(136)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 137) {
            r_bien.establecer_parametro("p138", ((vagendatentativaVo) vagedatentativaArrayList.get(137)).getNombre());
            r_bien.establecer_parametro("c138", ((vagendatentativaVo) vagedatentativaArrayList.get(137)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 138) {
            r_bien.establecer_parametro("p139", ((vagendatentativaVo) vagedatentativaArrayList.get(138)).getNombre());
            r_bien.establecer_parametro("c139", ((vagendatentativaVo) vagedatentativaArrayList.get(138)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 139) {
            r_bien.establecer_parametro("p140", ((vagendatentativaVo) vagedatentativaArrayList.get(139)).getNombre());
            r_bien.establecer_parametro("c140", ((vagendatentativaVo) vagedatentativaArrayList.get(139)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 140) {
            r_bien.establecer_parametro("p141", ((vagendatentativaVo) vagedatentativaArrayList.get(140)).getNombre());
            r_bien.establecer_parametro("c141", ((vagendatentativaVo) vagedatentativaArrayList.get(140)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 141) {
            r_bien.establecer_parametro("p142", ((vagendatentativaVo) vagedatentativaArrayList.get(141)).getNombre());
            r_bien.establecer_parametro("c142", ((vagendatentativaVo) vagedatentativaArrayList.get(141)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 142) {
            r_bien.establecer_parametro("p143", ((vagendatentativaVo) vagedatentativaArrayList.get(142)).getNombre());
            r_bien.establecer_parametro("c143", ((vagendatentativaVo) vagedatentativaArrayList.get(142)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 143) {
            r_bien.establecer_parametro("p144", ((vagendatentativaVo) vagedatentativaArrayList.get(143)).getNombre());
            r_bien.establecer_parametro("c144", ((vagendatentativaVo) vagedatentativaArrayList.get(143)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 144) {
            r_bien.establecer_parametro("p145", ((vagendatentativaVo) vagedatentativaArrayList.get(144)).getNombre());
            r_bien.establecer_parametro("c145", ((vagendatentativaVo) vagedatentativaArrayList.get(144)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 145) {
            r_bien.establecer_parametro("p146", ((vagendatentativaVo) vagedatentativaArrayList.get(145)).getNombre());
            r_bien.establecer_parametro("c146", ((vagendatentativaVo) vagedatentativaArrayList.get(145)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 146) {
            r_bien.establecer_parametro("p147", ((vagendatentativaVo) vagedatentativaArrayList.get(146)).getNombre());
            r_bien.establecer_parametro("c147", ((vagendatentativaVo) vagedatentativaArrayList.get(146)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 147) {
            r_bien.establecer_parametro("p148", ((vagendatentativaVo) vagedatentativaArrayList.get(147)).getNombre());
            r_bien.establecer_parametro("c148", ((vagendatentativaVo) vagedatentativaArrayList.get(147)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 148) {
            r_bien.establecer_parametro("p149", ((vagendatentativaVo) vagedatentativaArrayList.get(148)).getNombre());
            r_bien.establecer_parametro("c149", ((vagendatentativaVo) vagedatentativaArrayList.get(148)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 149) {
            r_bien.establecer_parametro("p150", ((vagendatentativaVo) vagedatentativaArrayList.get(149)).getNombre());
            r_bien.establecer_parametro("c150", ((vagendatentativaVo) vagedatentativaArrayList.get(149)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 150) {
            r_bien.establecer_parametro("p151", ((vagendatentativaVo) vagedatentativaArrayList.get(150)).getNombre());
            r_bien.establecer_parametro("c151", ((vagendatentativaVo) vagedatentativaArrayList.get(150)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 151) {
            r_bien.establecer_parametro("p152", ((vagendatentativaVo) vagedatentativaArrayList.get(151)).getNombre());
            r_bien.establecer_parametro("c152", ((vagendatentativaVo) vagedatentativaArrayList.get(151)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 152) {
            r_bien.establecer_parametro("p153", ((vagendatentativaVo) vagedatentativaArrayList.get(152)).getNombre());
            r_bien.establecer_parametro("c153", ((vagendatentativaVo) vagedatentativaArrayList.get(152)).getTipoestudiosolicitado());
        }
        if (vagedatentativaArrayList.size() > 153) {
            r_bien.establecer_parametro("p154", ((vagendatentativaVo) vagedatentativaArrayList.get(153)).getNombre());
            r_bien.establecer_parametro("c154", ((vagendatentativaVo) vagedatentativaArrayList.get(153)).getTipoestudiosolicitado());
        }
               
        r_bien.generar_reporte();

    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXHeader jXHeader1;
    private org.jdesktop.swingx.JXTable jxtlistaRecepcion;
    private Dao.vagendatentativaDao vagendatentativaDao1;
    // End of variables declaration//GEN-END:variables
}
