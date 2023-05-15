package bean;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;

public class JTableDemo extends JFrame {
    Object rowData[][] = {
            new Object[] {"张三", 20, "男"},
            new Object[] {"李四", 24, "女"},
            new Object[] {"王五", 22, "男"},
            new Object[] {"赵六", 19, "女"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"},
            new Object[] {"狗剩", 19, "男"}
    };

    Object headData[] = {"姓名", "年龄", "性别"};

    public JTableDemo() throws HeadlessException {

        initFrame();
        initTable();

        setVisible(true);
    }

    private void initTable() {
        //创建表格
        JTable jTable = new JTable(new MyTabelModel());
        jTable.setRowHeight(50);
        jTable.setFont(new Font(null, 1, 40));
        jTable.getTableHeader().setFont(new Font(null, 1, 40));

        JTableHeader header = jTable.getTableHeader();
        jTable.getTableHeader().setPreferredSize(new Dimension(header.getWidth(),40));

        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        TableColumn column;
        for (int i = 0; i < 3; i++) {
            column = jTable.getColumnModel().getColumn(i);
            if(i == 0)
                column.setPreferredWidth(2);
        }


        JScrollPane jScrollPane = new JScrollPane(jTable);
        jScrollPane.setBounds(100,30,600,400);
        TitledBorder titledBorder = new TitledBorder("信息表格");
        titledBorder.setTitleColor(Color.red);
        titledBorder.setTitleFont(new Font(null, 1, 20));
        jScrollPane.setBorder(titledBorder);
        add(jScrollPane);
    }

    private void initFrame() {
        setBounds(100,100,800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
    }

    private class MyTabelModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return rowData.length;
        }

        @Override
        public int getColumnCount() {
            return headData.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return rowData[rowIndex][columnIndex];
        }


    }
}
