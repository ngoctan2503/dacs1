package raven.table;

import ConnectMySQL.MySQL;
import com.raven.model.ModelItem;
import java.awt.Component;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;

public class QtyCellEditor extends DefaultCellEditor {

    private EventCellInputChange event;
    private JSpinner input;
    private JTable table;
    private int row;
    private ModelItem item;

    public QtyCellEditor(EventCellInputChange event) {
        super(new JCheckBox());
        this.event = event;
        input = new JSpinner();
        SpinnerNumberModel numberModel = (SpinnerNumberModel) input.getModel();
        numberModel.setMinimum(1);
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) input.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) editor.getTextField().getFormatter();
        formatter.setCommitsOnValidEdit(true);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        input.addChangeListener((ChangeEvent e) -> {
            inputChange();
        });
    }

    
    
     @Override
public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    super.getTableCellEditorComponent(table, value, isSelected, row, column);
    this.table = table;
    this.row = row;
    try {
        this.item = (ModelItem) table.getValueAt(row, 0); 
        int qty = (int) value;
        input.setValue(qty);
        input.setEnabled(false);
        enable();
        
        
        
    } catch (Exception e) {
        e.printStackTrace(); 
    }
    return input;
}


    private void enable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    input.setEnabled(true);
                } catch (Exception e) {

                }
            }
        }).start();
    }
    

    @Override
    public Object getCellEditorValue() {
        return input.getValue();
    }

   private void inputChange() {
    try {
        int qty = Integer.parseInt(input.getValue().toString());
        if (qty != item.getQty()) {
            item.setQty(qty);
            item.setTotal(item.getPrice() * qty);
            DecimalFormat df = new DecimalFormat("#,##0.##");
            String formattedTotal = df.format(item.getTotal());
            table.setValueAt("$ " + formattedTotal, row, 6);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int i = table.getSelectedRow();
            if (i >= 0) {
                ModelItem o = (ModelItem) model.getValueAt(i, 0);
                double total = item.getTotal();
                MySQL.getConnection().updata(o.getItemID(), total, qty);
            }

            event.inputChanged();
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Giá trị nhập vào không hợp lệ. Vui lòng nhập số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
