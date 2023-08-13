/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imnsu;

import Vo.PersonaVo;
import Vo.usuarioVo;
import imnsu.publico.jPPersona;
import imnsu.publico.jdAcercade;
import imnsu.publico.jdClave;
import imnsu.publico.jdIngreso;
import imnsu.publico.jdProgramacion;
import imnsu.publico.jpUsuario;
import imnsu.radiactividad.jpRecepcion;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import reportes.jpAgendaProgramada;
import reportes.jpControlMaterial;
import resources.img.JInternalFrameView;
import resources.img.ingreso;
import javax.swing.JOptionPane;
import resources.img.InactivityListener;

public class imnsu extends javax.swing.JFrame {
    public static usuarioVo usuario;
    public static JDesktopPane jdesktop = new JDesktopPane();
    public static String ip;
    public static String bd;
    public static String ubd;
    public static String pbd;
    public static String puerto;


    /**
     * Creates new form imnsu
     * @throws javax.swing.UnsupportedLookAndFeelException
     */
    public imnsu() throws UnsupportedLookAndFeelException {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(imnsu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(imnsu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(imnsu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(imnsu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }        
        new ScreenSplash().animar();
        ingreso in = new ingreso();
        in.ingreso = false;
        jdIngreso dialog = new jdIngreso(new javax.swing.JFrame(), true, in);
        dialog.show();
        Action accion= new AbstractAction(){
        @Override
        public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(new JFrame(), "Su sesion expiró, ingrese nuevamente sus datos o reinicie el sistema!!!", "Alerta!!!", 0);

        in.ingreso = false;
        dialog.show();
        if(in.usuario.getUsuario().compareTo(imnsu.usuario.getUsuario())==0){
        imnsu.usuario=in.usuario;}
        else{
        JOptionPane.showMessageDialog(new JFrame(), "El nombre de usuario difiere de la sesion anterior. Se cerrará el sistema!!!", "Alerta!!!", 0);
        System.exit(0);
        }
                }
            };
        imnsu.usuario=in.usuario;
        if (in.ingreso) {            
            initComponents();
            setPermisos();

            this.setTitle("Sistema IMNSU v. 1.0");
            this.setSize(500, 500);
            jLUsuario.setText("*** Usuario***->"+usuario.getNombrecompleto());

            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(imnsu.EXIT_ON_CLOSE);
            this.setExtendedState(MAXIMIZED_BOTH);
            jdesktop = jDesktopPaneMenu;
            jlImagen.setBounds(((int) this.getLocation().getX()), ((int) this.getLocation().getY() / 16), 800, 800);
            InactivityListener listener= new InactivityListener(dialog, accion, 1);
            listener.start();
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    try {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(imnsu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }
        }
        else{
            System.exit(0);
        }
    }
private void setPermisos(){
    jmiPersona.setEnabled(imnsu.usuario.isOp1());
    jmiListapersona.setEnabled(imnsu.usuario.isOp2());
    jmiRecepcion.setEnabled(imnsu.usuario.isOp3());
    jmiContra.setEnabled(imnsu.usuario.isOp4());
    jmiAdmPermisos.setEnabled(imnsu.usuario.isOp5());
    jmiAgenda.setEnabled(imnsu.usuario.isOp6());
    jmiControl.setEnabled(imnsu.usuario.isOp7());
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPaneMenu = new javax.swing.JDesktopPane();
        jlImagen = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLUsuario = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jmiPersona = new javax.swing.JMenuItem();
        jmiRecepcion = new javax.swing.JMenuItem();
        jmiListapersona = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jmiContra = new javax.swing.JMenuItem();
        jmiAdmPermisos = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jmiAgenda = new javax.swing.JMenuItem();
        jmiControl = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlImagen.setForeground(new java.awt.Color(255, 255, 255));
        jlImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/img/imnsu.png"))); // NOI18N

        javax.swing.GroupLayout jDesktopPaneMenuLayout = new javax.swing.GroupLayout(jDesktopPaneMenu);
        jDesktopPaneMenu.setLayout(jDesktopPaneMenuLayout);
        jDesktopPaneMenuLayout.setHorizontalGroup(
            jDesktopPaneMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneMenuLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jlImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
        );
        jDesktopPaneMenuLayout.setVerticalGroup(
            jDesktopPaneMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneMenuLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jlImagen)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jDesktopPaneMenu.setLayer(jlImagen, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(jDesktopPaneMenu, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jProgressBar1, java.awt.BorderLayout.EAST);
        jPanel1.add(jLUsuario, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jMenu3.setText("Archivo");

        jmiPersona.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jmiPersona.setMnemonic('P');
        jmiPersona.setText("Persona");
        jmiPersona.setToolTipText("");
        jmiPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPersonaActionPerformed(evt);
            }
        });
        jMenu3.add(jmiPersona);

        jmiRecepcion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jmiRecepcion.setText("Recepción de Material");
        jmiRecepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRecepcionActionPerformed(evt);
            }
        });
        jMenu3.add(jmiRecepcion);

        jmiListapersona.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jmiListapersona.setText("Lista personas en Espera de estudio");
        jmiListapersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiListapersonaActionPerformed(evt);
            }
        });
        jMenu3.add(jmiListapersona);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Salir");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Mantenimiento");

        jmiContra.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jmiContra.setText("Cambiar Contraseña");
        jmiContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiContraActionPerformed(evt);
            }
        });
        jMenu4.add(jmiContra);

        jmiAdmPermisos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jmiAdmPermisos.setText("Administracion de Permisos");
        jmiAdmPermisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAdmPermisosActionPerformed(evt);
            }
        });
        jMenu4.add(jmiAdmPermisos);

        jMenuBar1.add(jMenu4);

        jMenu1.setText("Reportes");

        jmiAgenda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jmiAgenda.setText("Agenda Programada");
        jmiAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAgendaActionPerformed(evt);
            }
        });
        jMenu1.add(jmiAgenda);

        jmiControl.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jmiControl.setText("Control de Material Radiactivo");
        jmiControl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiControlActionPerformed(evt);
            }
        });
        jMenu1.add(jmiControl);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Acerca de...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPersonaActionPerformed
        JInternalFrameView iframe = new JInternalFrameView("Registro de Personas", true, true, true, true);
        int x = 950, y = 490;
        iframe.setBounds(jDesktopPaneMenu.getWidth() / 2 - x / 2, jDesktopPaneMenu.getHeight() / 2 - y / 2, x, y);
        jDesktopPaneMenu.add(iframe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        iframe.addView(new jPPersona(iframe));
        iframe.setVisible(true);
        iframe.setLista(true);

    }//GEN-LAST:event_jmiPersonaActionPerformed

    private void jmiRecepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRecepcionActionPerformed
        JInternalFrameView iframe = new JInternalFrameView("Registro de Generador", true, true, true, true);
        int x = 1250, y = 400;
        iframe.setBounds(jDesktopPaneMenu.getWidth() / 2 - x / 2, jDesktopPaneMenu.getHeight() / 2 - y / 2, x, y);
        jDesktopPaneMenu.add(iframe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        iframe.addView(new jpRecepcion(iframe));
        iframe.setVisible(true);
        iframe.setLista(true);
    }//GEN-LAST:event_jmiRecepcionActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);

    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jmiListapersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiListapersonaActionPerformed
        // TODO add your handling code here:
        PersonaVo persona = null;//(PersonaVo) jxtlistaPersona.getValueAt(jxtlistaPersona.getSelectedRow(), 4);
        jdProgramacion dialog = new jdProgramacion(new JFrame(), true, "1", "1", true, persona, false);
        dialog.show();
    }//GEN-LAST:event_jmiListapersonaActionPerformed

    private void jmiAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAgendaActionPerformed
        // TODO add your handling code here:
        JInternalFrameView iframe = new JInternalFrameView("Registro de Personas", true, true, true, true);
        int x = 950, y = 500;
        iframe.setBounds(jDesktopPaneMenu.getWidth() / 2 - x / 2, jDesktopPaneMenu.getHeight() / 2 - y / 2, x, y);
        jDesktopPaneMenu.add(iframe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        iframe.addView(new jpAgendaProgramada(iframe));
        iframe.setVisible(true);
        iframe.setLista(true);
    }//GEN-LAST:event_jmiAgendaActionPerformed

    private void jmiAdmPermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAdmPermisosActionPerformed
        // TODO add your handling code here:
        JInternalFrameView iframe = new JInternalFrameView("Administración de Permisos", true, true, true, true);
        int x = 950, y = 490;
        iframe.setBounds(jDesktopPaneMenu.getWidth() / 2 - x / 2, jDesktopPaneMenu.getHeight() / 2 - y / 2, x, y);
        jDesktopPaneMenu.add(iframe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        iframe.addView(new jpUsuario(iframe));
        iframe.setVisible(true);
        iframe.setLista(true);
    }//GEN-LAST:event_jmiAdmPermisosActionPerformed

    private void jmiControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiControlActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        JInternalFrameView iframe = new JInternalFrameView("CONTROL DE MATERIAL RADIACTIVO", true, true, true, true);
        int x = 950, y = 490;
        iframe.setBounds(jDesktopPaneMenu.getWidth() / 2 - x / 2, jDesktopPaneMenu.getHeight() / 2 - y / 2, x, y);
        jDesktopPaneMenu.add(iframe, javax.swing.JLayeredPane.DEFAULT_LAYER);
        iframe.addView(new jpControlMaterial(iframe));
        iframe.setVisible(true);
        iframe.setLista(true);
    }//GEN-LAST:event_jmiControlActionPerformed

    private void jmiContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiContraActionPerformed
        // TODO add your handling code here:
        jdClave clave=new jdClave(this, true);
        clave.show();
    }//GEN-LAST:event_jmiContraActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        jdAcercade dialog = new jdAcercade(new JFrame(),true);
        dialog.show();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        //</editor-fold>

        /* Create and display the form */
        imnsu.ip=args[0];
        imnsu.bd=args[1];
        imnsu.ubd=args[2];
        imnsu.pbd=args[3];
        imnsu.puerto=args[4];
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new imnsu().setVisible(true);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(imnsu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPaneMenu;
    private javax.swing.JLabel jLUsuario;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel jlImagen;
    private javax.swing.JMenuItem jmiAdmPermisos;
    private javax.swing.JMenuItem jmiAgenda;
    private javax.swing.JMenuItem jmiContra;
    private javax.swing.JMenuItem jmiControl;
    private javax.swing.JMenuItem jmiListapersona;
    private javax.swing.JMenuItem jmiPersona;
    private javax.swing.JMenuItem jmiRecepcion;
    // End of variables declaration//GEN-END:variables
}
