package resources.img;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Objects;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;


public class TableCellFormatterText implements TableCellRenderer {
private Double v;
    JFormattedTextField ftfcampo;

    public TableCellFormatterText() {
        ftfcampo = new JFormattedTextField();
        ftfcampo.setHorizontalAlignment(JTextField.RIGHT);
        ftfcampo.setFont(new Font("Tahoma", 1, 12));
        ftfcampo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        int fila=table.getSelectedRow();
//        int columna=table.getSelectedColumn();
        Boolean sw=false;
        
        ftfcampo.setValue(value);
        //JLabel cell
        if (isSelected) {
            ftfcampo.setBackground(table.getSelectionBackground());
        } else {    
            if(Objects.equals(v, (Double)value)) sw=true;
            if(sw) 
                ftfcampo.setBackground(Color.RED);
            else 
                ftfcampo.setBackground(null);
        }    
                  
        return ftfcampo;
    }

    public void setV(Double v) {
        this.v = v;
    }
    

}
