/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.img;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;


public class JInternalFrameView extends JInternalFrame {

    private JPanel view;

    public JInternalFrameView(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
        super(title, resizable, closable, maximizable, iconifiable);
        initLocal();
    }

    public JInternalFrameView(String title, boolean resizable, boolean closable, boolean maximizable) {
        super(title, resizable, closable, maximizable);
        initLocal();
    }

    public JInternalFrameView(String title, boolean resizable, boolean closable) {
        super(title, resizable, closable);
        initLocal();
    }

    public JInternalFrameView(String title, boolean resizable) {
        super(title, resizable);
        initLocal();
    }

    public JInternalFrameView(String title) {
        super(title);
        initLocal();
    }

    public JInternalFrameView() {
        initLocal();
    }

    public JPanel getView() {
        return view;
    }

    public void setView(JPanel view) {
        this.view = view;
    }

    public void addView(JPanel pane) {
        this.add(pane);
        this.view = pane;
    }
    /**
     * Variable de control de vistas 1: Persona 2:
     */

    private int control;
    private boolean lista;
    private boolean seleccion;

    public int getControl() {
        return control;
    }

    public void setControl(int control) {

        this.control = control;
    }

    public boolean isLista() {
        return lista;
    }

    public void setLista(boolean lista) {
        this.lista = lista;
    }

    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }

    private void initLocal() {

    }

}
