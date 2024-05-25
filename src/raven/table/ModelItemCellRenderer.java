package raven.table;

import com.raven.model.ModelItem;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ModelItemCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value instanceof ModelItem) {
            ModelItem item = (ModelItem) value;
            setText(String.valueOf((int) item.getItemID()));
            
        } else {
            setText("");
        }

        return this;
    }
}
