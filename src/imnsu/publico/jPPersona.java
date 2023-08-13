/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imnsu.publico;

import Dao.personaDao;
import Vo.PersonaVo;
import Vo.vpersonaestudioVo;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import resources.img.JInternalFrameView;
import resources.img.ingreso;

public class jPPersona extends javax.swing.JPanel {

    private JInternalFrameView iframe;

    /**
     * Creates new form jPPersona
     *
     * @param iframe
     */
    public jPPersona(JInternalFrameView iframe) {
        initComponents();
        this.iframe = iframe;

        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getSource() instanceof JComponent && ((JComponent) e.getSource()).getName() == null) {
                    if (e.getID() == KeyEvent.KEY_TYPED) {
                        if (e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z') {
                            e.setKeyChar((char) (((int) e.getKeyChar()) - 32));
                        }
                    }
                }
                return false;
            }
        });
        setTablaListaPersona("", "", "", "");
    }

    private void cargardatos() {

        PersonaVo persona = (PersonaVo) jxtlistaPersona.getValueAt(jxtlistaPersona.getSelectedRow(), 4);
        jtfNombre.setEditable(false);
        jtfPaterno.setEditable(false);
        jtfMaterno.setEditable(false);
        jchOrigen.setEnabled(false);
        jtfOrigen.setEditable(false);
        jtfOrigen.setEditable(false);
        jtfTelefono.setEditable(false);
        jtffonoreponsalble.setEditable(false);
        jtfMedico.setEditable(false);
        jtfInstitucion.setEditable(false);
        jchEnespera.setEnabled(false);
        jxdpFechaEspera.setEditable(false);
        jxdpFechaTentativa.setEditable(false);

        jtfNombre.setText(persona.getNombre());
        jtfPaterno.setText(persona.getPaterno());
        jtfMaterno.setText(persona.getMaterno());
        /*            jftfEdad.setText(String.valueOf(persona.getEdad()));
         jcbTipoEstado.setSelectedItem(persona.getTipoestado());
         jcbSexo.setSelectedItem(persona.getSex());
         tipoEstadoVo tipoestado=tipoEstadoDao1.getTipoEstado(persona.getIdtipoestado());
         //jcbTipoEstado.setSelectedIndex(0);
         jcbTipoEstado.getModel().setSelectedItem(tipoestado);*/
        jchOrigen.setSelected(persona.isInterior());
        jtfOrigen.setEnabled(persona.isInterior());
        jtfOrigen.setText(persona.getProcedencia());
        jtfTelefono.setText(persona.getTelefono());
        jtffonoreponsalble.setText(persona.getTelefonoresponsable());
        jtfMedico.setText(persona.getMedicoremitente());
        jtfInstitucion.setText(persona.getInstituicionprocedente());
        jchEnespera.setSelected(persona.isEnespera());
        jxdpFechaEspera.setDate(persona.getFechainicioespera());
        jxdpFechaTentativa.setDate(persona.getFechatentativa());

    }

    private void setTablaListaPersona(String nombre, String paterno, String materno, String espera) {
        eliminartodaslasfilastabla(jxtlistaPersona);
        Vector v;
        for (PersonaVo persona : personaDao.getListaPersona(nombre, paterno, materno, espera)) {
            v = new Vector();
            v.add(persona.getPaterno() + " " + persona.getMaterno() + " " + persona.getNombre());
            v.add(persona.getSex());
            v.add(persona.getEdad());
            v.add(persona.getTipoestado());
            v.add(persona);
            agregar_fila_tabla(v, jxtlistaPersona);
        }
        if (jxtlistaPersona.getRowCount() > 0) {
            jxtlistaPersona.setRowSelectionInterval(0, 0);
            cargardatos();
            PersonaVo persona = (PersonaVo) jxtlistaPersona.getModel().getValueAt(jxtlistaPersona.getSelectedRow(), 4);
            setTablaListaPersonaEstudios(persona);
        }
    }

    private void setTablaListaPersonaEstudios(PersonaVo persona) {
        eliminartodaslasfilastabla(jtListaPersonasEstudio);
        Vector v;
        for (vpersonaestudioVo vpersonaestudio : vpersonaestudioDao1.getListaPersonaEstudioP(persona)) {
            v = new Vector();
            v.add(vpersonaestudio.getExamen());
            v.add(vpersonaestudio.getHorainyeccion());
            v.add(vpersonaestudio.getFechaconfirmada());
            v.add(vpersonaestudio);
            agregar_fila_tabla(v, jtListaPersonasEstudio);
        }
        if (jtListaPersonasEstudio.getRowCount() > 0) {
            jtListaPersonasEstudio.setRowSelectionInterval(0, 0);

        }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoEstadoDao1 = new Dao.tipoEstadoDao();
        vpersonaestudioDao1 = new Dao.vpersonaestudioDao();
        jXHeader1 = new org.jdesktop.swingx.JXHeader();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfNombre = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtfPaterno = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfMaterno = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jchOrigen = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        jtfOrigen = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jtfTelefono = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jtffonoreponsalble = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jtfMedico = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jtfInstitucion = new javax.swing.JTextField();
        jchEnespera = new javax.swing.JCheckBox();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jxdpFechaEspera = new org.jdesktop.swingx.JXDatePicker();
        jPanel22 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jxdpFechaTentativa = new org.jdesktop.swingx.JXDatePicker();
        jPanel11 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jxtlistaPersona = new org.jdesktop.swingx.JXTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtListaPersonasEstudio = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jXHeader1.setTitle("Registro de Personas");
        add(jXHeader1, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel10.setLayout(new java.awt.GridLayout(7, 1));

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel1.setText(" Nombre: ");
        jLabel1.setPreferredSize(new java.awt.Dimension(70, 0));
        jPanel4.add(jLabel1, java.awt.BorderLayout.WEST);
        jPanel4.add(jtfNombre, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel4);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel2.setText(" Paterno: ");
        jLabel2.setPreferredSize(new java.awt.Dimension(70, 0));
        jPanel5.add(jLabel2, java.awt.BorderLayout.WEST);
        jPanel5.add(jtfPaterno, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel5);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel3.setText(" Materno: ");
        jLabel3.setPreferredSize(new java.awt.Dimension(70, 0));
        jPanel6.add(jLabel3, java.awt.BorderLayout.WEST);
        jPanel6.add(jtfMaterno, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel6);

        jPanel12.setLayout(new java.awt.BorderLayout());

        jchOrigen.setText("Interior");
        jchOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchOrigenActionPerformed(evt);
            }
        });
        jPanel12.add(jchOrigen, java.awt.BorderLayout.WEST);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jtfOrigen.setText("Sucre");
        jtfOrigen.setEnabled(false);
        jPanel13.add(jtfOrigen, java.awt.BorderLayout.CENTER);

        jLabel7.setText("       Origen: ");
        jPanel13.add(jLabel7, java.awt.BorderLayout.WEST);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel8.setText("  Telefono: ");
        jPanel14.add(jLabel8, java.awt.BorderLayout.WEST);

        jtfTelefono.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtfTelefono.setPreferredSize(new java.awt.Dimension(90, 0));
        jPanel14.add(jtfTelefono, java.awt.BorderLayout.CENTER);

        jPanel13.add(jPanel14, java.awt.BorderLayout.EAST);

        jPanel12.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel12);

        jPanel15.setLayout(new java.awt.BorderLayout());

        jPanel16.setLayout(new java.awt.BorderLayout());

        jLabel9.setText("Responsable: ");
        jPanel16.add(jLabel9, java.awt.BorderLayout.WEST);

        jtffonoreponsalble.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtffonoreponsalble.setPreferredSize(new java.awt.Dimension(90, 0));
        jPanel16.add(jtffonoreponsalble, java.awt.BorderLayout.CENTER);

        jPanel15.add(jPanel16, java.awt.BorderLayout.WEST);

        jPanel17.setLayout(new java.awt.BorderLayout());

        jLabel10.setText("  Médico: ");
        jLabel10.setPreferredSize(new java.awt.Dimension(70, 0));
        jPanel17.add(jLabel10, java.awt.BorderLayout.WEST);
        jPanel17.add(jtfMedico, java.awt.BorderLayout.CENTER);

        jPanel15.add(jPanel17, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel15);

        jPanel18.setLayout(new java.awt.BorderLayout());

        jPanel19.setLayout(new java.awt.BorderLayout());

        jLabel11.setText("Institución: ");
        jLabel11.setPreferredSize(new java.awt.Dimension(70, 0));
        jPanel19.add(jLabel11, java.awt.BorderLayout.WEST);
        jPanel19.add(jtfInstitucion, java.awt.BorderLayout.CENTER);

        jPanel18.add(jPanel19, java.awt.BorderLayout.CENTER);

        jchEnespera.setSelected(true);
        jchEnespera.setText("En espera");
        jchEnespera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchEnesperaActionPerformed(evt);
            }
        });
        jPanel18.add(jchEnespera, java.awt.BorderLayout.EAST);

        jPanel10.add(jPanel18);

        jPanel20.setLayout(new java.awt.GridLayout(1, 0));

        jPanel21.setLayout(new java.awt.BorderLayout());

        jLabel12.setText("Fecha Inicio Espera: ");
        jPanel21.add(jLabel12, java.awt.BorderLayout.WEST);
        jPanel21.add(jxdpFechaEspera, java.awt.BorderLayout.CENTER);

        jPanel20.add(jPanel21);

        jPanel22.setLayout(new java.awt.BorderLayout());

        jLabel13.setText("Fecha Tentativa: ");
        jPanel22.add(jLabel13, java.awt.BorderLayout.WEST);
        jPanel22.add(jxdpFechaTentativa, java.awt.BorderLayout.CENTER);

        jPanel20.add(jPanel22);

        jPanel10.add(jPanel20);

        jPanel3.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setPreferredSize(new java.awt.Dimension(200, 0));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new java.awt.GridLayout(3, 1));

        jButton5.setText("Buscar Persona");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton5);

        jButton6.setText("Listar todo");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton6);

        jButton7.setText("Listar En espera");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton7);

        jPanel11.add(jPanel7, java.awt.BorderLayout.NORTH);

        jButton4.setText("Asignar Estudio");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton4, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel11, java.awt.BorderLayout.EAST);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jxtlistaPersona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Sexo", "Edad", "Condición", "Obj"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jxtlistaPersona.getTableHeader().setReorderingAllowed(false);
        jxtlistaPersona.setVisibleRowCount(5);
        jxtlistaPersona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jxtlistaPersonaMouseReleased(evt);
            }
        });
        jxtlistaPersona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jxtlistaPersonaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jxtlistaPersona);
        if (jxtlistaPersona.getColumnModel().getColumnCount() > 0) {
            jxtlistaPersona.getColumnModel().getColumn(1).setMinWidth(70);
            jxtlistaPersona.getColumnModel().getColumn(1).setPreferredWidth(70);
            jxtlistaPersona.getColumnModel().getColumn(1).setMaxWidth(70);
            jxtlistaPersona.getColumnModel().getColumn(2).setMinWidth(50);
            jxtlistaPersona.getColumnModel().getColumn(2).setPreferredWidth(50);
            jxtlistaPersona.getColumnModel().getColumn(2).setMaxWidth(50);
            jxtlistaPersona.getColumnModel().getColumn(3).setMinWidth(200);
            jxtlistaPersona.getColumnModel().getColumn(3).setPreferredWidth(200);
            jxtlistaPersona.getColumnModel().getColumn(3).setMaxWidth(200);
            jxtlistaPersona.getColumnModel().getColumn(4).setMinWidth(0);
            jxtlistaPersona.getColumnModel().getColumn(4).setPreferredWidth(0);
            jxtlistaPersona.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        jPanel8.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(350, 0));

        jtListaPersonasEstudio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Estudio", "Hora de Inyeccion", "Fecha", "Obj"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtListaPersonasEstudio.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jtListaPersonasEstudio);
        if (jtListaPersonasEstudio.getColumnModel().getColumnCount() > 0) {
            jtListaPersonasEstudio.getColumnModel().getColumn(1).setPreferredWidth(80);
            jtListaPersonasEstudio.getColumnModel().getColumn(2).setPreferredWidth(80);
            jtListaPersonasEstudio.getColumnModel().getColumn(3).setMinWidth(0);
            jtListaPersonasEstudio.getColumnModel().getColumn(3).setPreferredWidth(0);
            jtListaPersonasEstudio.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        jPanel8.add(jScrollPane3, java.awt.BorderLayout.EAST);

        jPanel1.add(jPanel8, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jButton3.setText("Cerrar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        add(jPanel2, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.iframe.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ingreso in=new ingreso();
        PersonaVo persona = new PersonaVo();
        in.persona=persona;
        jdPersona dialog = new jdPersona(new JFrame(), true, in, false);
        dialog.show();
        setTablaListaPersona(in.persona.getNombre(), in.persona.getPaterno(), in.persona.getMaterno(), "");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ingreso in=new ingreso();
        in.persona=(PersonaVo) jxtlistaPersona.getValueAt(jxtlistaPersona.getSelectedRow(), 4);
        jdPersona dialog = new jdPersona(new JFrame(), true, in, true);
        dialog.show();
        setTablaListaPersona(in.persona.getNombre(), in.persona.getPaterno(), in.persona.getMaterno(), "");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        setTablaListaPersona(jtfNombre.getText(), jtfPaterno.getText(), jtfMaterno.getText(), "");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        PersonaVo persona = (PersonaVo) jxtlistaPersona.getValueAt(jxtlistaPersona.getSelectedRow(), 4);
        jdProgramacion dialog = new jdProgramacion(new JFrame(), true, "1", "1", true, persona,true);
        dialog.show();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jchOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchOrigenActionPerformed
        // TODO add your handling code here:
        jtfOrigen.setEnabled(jchOrigen.isSelected());
    }//GEN-LAST:event_jchOrigenActionPerformed

    private void jchEnesperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchEnesperaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jchEnesperaActionPerformed

    private void jxtlistaPersonaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jxtlistaPersonaMouseReleased
        // TODO add your handling code here:
        cargardatos();
        PersonaVo persona = (PersonaVo) jxtlistaPersona.getModel().getValueAt(jxtlistaPersona.getSelectedRow(), 4);
        setTablaListaPersonaEstudios(persona);

    }//GEN-LAST:event_jxtlistaPersonaMouseReleased

    private void jxtlistaPersonaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jxtlistaPersonaKeyReleased
        // TODO add your handling code here:
        cargardatos();
        PersonaVo persona = (PersonaVo) jxtlistaPersona.getModel().getValueAt(jxtlistaPersona.getSelectedRow(), 4);
        setTablaListaPersonaEstudios(persona);

    }//GEN-LAST:event_jxtlistaPersonaKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        setTablaListaPersona("", "", "", "");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        setTablaListaPersona("", "", "", " and enespera=true ");
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private org.jdesktop.swingx.JXHeader jXHeader1;
    private javax.swing.JCheckBox jchEnespera;
    private javax.swing.JCheckBox jchOrigen;
    private javax.swing.JTable jtListaPersonasEstudio;
    private javax.swing.JTextField jtfInstitucion;
    private javax.swing.JTextField jtfMaterno;
    private javax.swing.JTextField jtfMedico;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JTextField jtfOrigen;
    private javax.swing.JTextField jtfPaterno;
    private javax.swing.JTextField jtfTelefono;
    private javax.swing.JTextField jtffonoreponsalble;
    private org.jdesktop.swingx.JXDatePicker jxdpFechaEspera;
    private org.jdesktop.swingx.JXDatePicker jxdpFechaTentativa;
    private org.jdesktop.swingx.JXTable jxtlistaPersona;
    private Dao.tipoEstadoDao tipoEstadoDao1;
    private Dao.vpersonaestudioDao vpersonaestudioDao1;
    // End of variables declaration//GEN-END:variables
}
