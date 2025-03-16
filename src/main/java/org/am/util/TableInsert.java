package org.am.util;

import java.lang.reflect.Field;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * A utility class that provides a method to insert data from a list into a JTable.
 * @param <T> the type of data to be inserted into the table
 */
public class TableInsert<T> {

    /**
     * Inserts data from a list into a JTable.
     * @param dataList the list of data to be inserted into the table
     * @param table the JTable object to insert the data into
     */
    public void insert(List<T> dataList, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        if (!dataList.isEmpty()) {
            T firstObject = dataList.get(0);
            Field[] fields = firstObject.getClass().getDeclaredFields();
            Object[] rowData = new Object[fields.length];
            for (Field field : fields) {
                model.addColumn(field.getName());
            }
            for (T data : dataList) {
                int i = 0;
                for (Field field : fields) {
                    field.setAccessible(true);
                    try {
                        rowData[i++] = field.get(data);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                model.addRow(rowData);
            }
        }
    }
}
