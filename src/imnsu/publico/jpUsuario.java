/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imnsu.publico;

import Vo.PersonaVo;
import Vo.usuarioVo;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import resources.img.JInternalFrameView;


public class jpUsuario extends javax.swing.JPanel {
    private final JInternalFrameView iframe;
    /**
     * Creates new form jpUsuario
     * @param iframe
     */
    public jpUsuario(JInternalFrameView iframe) {
        initComponents();
        this.iframe=iframe;
        setTablaListaUsuario();
    }

    private void setTablaListaUsuario() {
        eliminartodaslasfilastabla(jxtlistaUsuario);
        Vector v;
        for (usuarioVo usuario : usuarioDao1.getListaUsuarios()) {
            v = new Vector();
            v.add(usuario.getNombrecompleto());
            v.add(usuario.getUsuario());
            v.add(usuario.isOp1());
            v.add(usuario.isOp2());
            v.add(usuario.isOp3());
            v.add(usuario.isOp4());
            v.add(usuario.isOp5());
            v.add(usuario.isOp6());
            v.add(usuario.isOp7());
            v.add(usuario.isOp8());
            v.add(usuario.isOp9());
            v.add(usuario.isOp10());
            v.add(usuario);
            agregar_fila_tabla(v, jxtlistaUsuario);
        }
        if (jxtlistaUsuario.getRowCount() > 0) {
            jxtlistaUsuario.setRowSelectionInterval(0, 0);
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

        usuarioDao1 = new Dao.usuarioDao();
        jXHeader1 = new org.jdesktop.swingx.JXHeader();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jxtlistaUsuario = new org.jdesktop.swingx.JXTable();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jXHeader1.setTitle("Administracion de Usuario y Permisos");
        add(jXHeader1, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jxtlistaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Usuario", "Persona", "L.Personas", "R.Material", "C.Contraseña", "Adm.de Permisos", "A.Programada", "C.Material", "op8", "op9", "op10", "obj"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jxtlistaUsuario.getTableHeader().setReorderingAllowed(false);
        jxtlistaUsuario.setVisibleRowCount(5);
        jScrollPane1.setViewportView(jxtlistaUsuario);
        if (jxtlistaUsuario.getColumnModel().getColumnCount() > 0) {
            jxtlistaUsuario.getColumnModel().getColumn(9).setMinWidth(0);
            jxtlistaUsuario.getColumnModel().getColumn(9).setPreferredWidth(0);
            jxtlistaUsuario.getColumnModel().getColumn(9).setMaxWidth(0);
            jxtlistaUsuario.getColumnModel().getColumn(10).setMinWidth(0);
            jxtlistaUsuario.getColumnModel().getColumn(10).setPreferredWidth(0);
            jxtlistaUsuario.getColumnModel().getColumn(10).setMaxWidth(0);
            jxtlistaUsuario.getColumnModel().getColumn(11).setMinWidth(0);
            jxtlistaUsuario.getColumnModel().getColumn(11).setPreferredWidth(0);
            jxtlistaUsuario.getColumnModel().getColumn(11).setMaxWidth(0);
            jxtlistaUsuario.getColumnModel().getColumn(12).setMinWidth(0);
            jxtlistaUsuario.getColumnModel().getColumn(12).setPreferredWidth(0);
            jxtlistaUsuario.getColumnModel().getColumn(12).setMaxWidth(0);
        }

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jButton3.setText("Nuevo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);

        jButton1.setText("Editar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setText("Cerrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        add(jPanel2, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.iframe.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        usuarioVo usuario=null;
        jdUsuario dialog =new jdUsuario(new JFrame(), true,usuario, false);
        dialog.show();
        setTablaListaUsuario();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        usuarioVo usuario=(usuarioVo)jxtlistaUsuario.getModel().getValueAt(jxtlistaUsuario.getSelectedRow(),12);
        jdUsuario dialog =new jdUsuario(new JFrame(), true,usuario, true);
        dialog.show();
        setTablaListaUsuario();        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXHeader jXHeader1;
    private org.jdesktop.swingx.JXTable jxtlistaUsuario;
    private Dao.usuarioDao usuarioDao1;
    // End of variables declaration//GEN-END:variables
}