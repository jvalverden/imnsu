/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.img;

import imnsu.publico.jdConfirmarEstudio;
import java.util.Calendar;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.text.DateFormatter;

public class hora extends javax.swing.JPanel {

    private JSpinner spinner;

    private jdConfirmarEstudio confimarestudio;

    public javax.swing.JPanel createAndShowGUI() {
        getHoraServer hora = new getHoraServer();
        Calendar calendar = Calendar.getInstance();
        Timestamp Fechaserver = hora.getHora();
//        calendar.set(Calendar.HOUR_OF_DAY, (new Date()).getHours()); // 24 == 12 PM == 00:00:00
//        calendar.set(Calendar.MINUTE, (new Date()).getMinutes());
//        calendar.set(Calendar.SECOND, (new Date()).getSeconds());
        calendar.set(Calendar.HOUR_OF_DAY, Fechaserver.getHours()); // 24 == 12 PM == 00:00:00
        calendar.set(Calendar.MINUTE, Fechaserver.getMinutes());
        //calendar.set(Calendar.SECOND, Fechaserver.getSeconds());

        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(calendar.getTime());

        spinner = new JSpinner(model);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
        DateFormatter formatter = (DateFormatter) editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false); // this makes what you want
        formatter.setOverwriteMode(true);

        spinner.setEditor(editor);
        spinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                setHoraTecnecio();
            }
        });
//        JPanel content = new JPanel();
        this.add(spinner);

        return this;
    }

    public void setHoraTecnecio() {
        confimarestudio.setTecnecio(calculoTam(String.valueOf(((Date) getHora()).getHours())) + ":00");//+calculoTam(String.valueOf(((Date)getHora()).getMinutes())));
    //    System.out.println("hora-"+calculoTam(String.valueOf(((Date) getHora()).getHours())) + ":00");
    }

    private String calculoTam(String s) {
        if (s.length() == 1) {
            //s = s;
            s = "0" + s;
            
       //     System.out.println("s--"+s);
        }
        return s;
    }

    public void setConfimarestudio(jdConfirmarEstudio confimarestudio) {
        this.confimarestudio = confimarestudio;
    }

    public jdConfirmarEstudio getConfimarestudio() {
        return confimarestudio;
    }

    public Object getHora() {
        return spinner.getValue();
    }

    public void setHora(java.sql.Timestamp fecha) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, fecha.getHours()); // 24 == 12 PM == 00:00:00
        calendar.set(Calendar.MINUTE, fecha.getMinutes());
        calendar.set(Calendar.SECOND, fecha.getSeconds());

        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(calendar.getTime());
        spinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm:ss");
        DateFormatter formatter = (DateFormatter) editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false); // this makes what you want
        formatter.setOverwriteMode(true);

        spinner.setEditor(editor);

        this.removeAll();
        this.add(spinner);
    }
}
