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


public class TableCellFormatterTextCompleto implements TableCellRenderer {
private Double v;
    JFormattedTextField ftfcampo;

    public TableCellFormatterTextCompleto() {
        ftfcampo = new JFormattedTextField();
        ftfcampo.setHorizontalAlignment(JTextField.RIGHT);
        ftfcampo.setFont(new Font("Tahoma", 1, 12));
        ftfcampo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       
        ftfcampo.setValue(value);
        if (isSelected) {
            ftfcampo.setBackground(table.getSelectionBackground());
        } else {    
                ftfcampo.setBackground(Color.BLUE);
        }    
                  
        return ftfcampo;
    }

    public void setV(Double v) {
        this.v = v;
    }
    

}
