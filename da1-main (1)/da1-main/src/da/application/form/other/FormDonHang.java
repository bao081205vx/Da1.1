/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package da.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ADMIN
 */
public class FormDonHang extends javax.swing.JPanel {

    /**
     * Creates new form FormDonHang
     */
    public FormDonHang() {
        initComponents();
        applyListStyle(jList1);
        applyTableStyle(jTable1);
        applyTableStyle(jTable1);
        applyTableStyle(jTable2);
    }
    
    private void applyTableStyle(JTable table) {

        cmdUpdate.setIcon(new FlatSVGIcon("da/icon/svg/edit.svg", 0.35f));
        cmdDelete.setIcon(new FlatSVGIcon("da/icon/svg/delete.svg", 0.35f));
        cmdExcel.setIcon(new FlatSVGIcon("da/icon/svg/print.svg", 0.35f));
        cmdAdd.setIcon(new FlatSVGIcon("da/icon/svg/add.svg", 0.35f));
        cmdQR.setIcon(new FlatSVGIcon("da/icon/svg/qr.svg", 0.35f));

        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("da/icon/svg/search.svg", 0.35f));
        //  Change scroll style
        JScrollPane scroll = (JScrollPane) table.getParent().getParent();
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Table.background;"
                + "track:$Table.background;"
                + "trackArc:999");

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

        //  To Create table alignment
        table.getTableHeader().setDefaultRenderer(getAlignmentCellRender(table.getTableHeader().getDefaultRenderer(), true));
        table.setDefaultRenderer(Object.class, getAlignmentCellRender(table.getDefaultRenderer(Object.class), false));
    }

    private TableCellRenderer getAlignmentCellRender(TableCellRenderer oldRender, boolean header) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (com instanceof JLabel label) {
                    switch (column) {
                        case 0, 4 -> label.setHorizontalAlignment(SwingConstants.CENTER);
                        case 2, 3 -> label.setHorizontalAlignment(SwingConstants.TRAILING);
                        default -> label.setHorizontalAlignment(SwingConstants.LEADING);
                    }
                    if (header == false) {
                        if (column == 4) {
                            if (Double.parseDouble(value.toString()) > 0) {
                                com.setForeground(new Color(17, 182, 60));
                                label.setText("+" + value);
                            } else {
                                com.setForeground(new Color(202, 48, 48));
                            }
                        } else {
                            if (isSelected) {
                                com.setForeground(table.getSelectionForeground());
                            } else {
                                com.setForeground(table.getForeground());
                            }
                        }
                    }
                }
                return com;
            }
        };
    }
    
    
        private void applyListStyle(JList<String> list) {
            list.setFixedCellHeight(50); // Đặt chiều cao cố định cho mỗi item

            list.setCellRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5)); // Padding trên, trái, dưới, phải

            // Kiểm tra giao diện hiện tại của Swing
            boolean isDarkMode = UIManager.getLookAndFeel().getName().toLowerCase().contains("dark");

            // Màu nền xen kẽ cho Light Mode & Dark Mode
            Color evenColor = isDarkMode ? new Color(50, 50, 50) : new Color(220, 220, 220);
            Color oddColor = list.getBackground(); // Giữ nguyên màu nền mặc định

            if (index % 2 == 0) {
                label.setBackground(evenColor);
            } else {
                label.setBackground(oddColor);
            }

            // Giữ màu khi item được chọn
            if (isSelected) {
                label.setBackground(list.getSelectionBackground());
                label.setForeground(list.getSelectionForeground());
            }

            return label;
        }
    });
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        materialTabbed1 = new da.component.MaterialTabbed();
        jPanel1 = new javax.swing.JPanel();
        crazyPanel9 = new raven.crazypanel.CrazyPanel();
        panelTransparent1 = new da.component.PanelTransparent();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        crazyPanel15 = new raven.crazypanel.CrazyPanel();
        txtMa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        crazyPanel16 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        crazyPanel17 = new raven.crazypanel.CrazyPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        crazyPanel18 = new raven.crazypanel.CrazyPanel();
        txtMa2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        crazyPanel19 = new raven.crazypanel.CrazyPanel();
        txtMa3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        crazyPanel20 = new raven.crazypanel.CrazyPanel();
        txtMa4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        crazyPanel21 = new raven.crazypanel.CrazyPanel();
        txtMa5 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        crazyPanel22 = new raven.crazypanel.CrazyPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        crazyPanel23 = new raven.crazypanel.CrazyPanel();
        cmdAdd = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();
        cmdExcel = new javax.swing.JButton();
        cmdUpdate = new javax.swing.JButton();
        cmdQR = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        crazyPanel13 = new raven.crazypanel.CrazyPanel();
        panelTransparent2 = new da.component.PanelTransparent();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        crazyPanel24 = new raven.crazypanel.CrazyPanel();
        cmdUpdate5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1107, 504));

        crazyPanel9.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel9.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            null
        ));

        panelTransparent1.setPreferredSize(new java.awt.Dimension(467, 457));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên", "Số lượng"
            }
        ));
        jScrollPane7.setViewportView(jTable1);

        panelTransparent1.add(jScrollPane7);
        jScrollPane7.setBounds(0, 50, 350, 210);

        crazyPanel15.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "JTextField.placeholderText=Nhập Tên;background:@background"
            }
        ));
        crazyPanel15.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 200"
            }
        ));

        txtMa.setPreferredSize(new java.awt.Dimension(90, 22));
        txtMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaActionPerformed(evt);
            }
        });
        crazyPanel15.add(txtMa);

        panelTransparent1.add(crazyPanel15);
        crazyPanel15.setBounds(480, 40, 180, 36);

        jLabel1.setText("Mã Sản Phẩm");
        panelTransparent1.add(jLabel1);
        jLabel1.setBounds(370, 20, 100, 16);

        jLabel2.setText("Tên");
        panelTransparent1.add(jLabel2);
        jLabel2.setBounds(490, 20, 40, 16);

        crazyPanel16.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "JTextField.placeholderText=Search;background:@background"
            }
        ));
        crazyPanel16.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 200"
            }
        ));

        txtSearch.setPreferredSize(new java.awt.Dimension(90, 22));
        crazyPanel16.add(txtSearch);

        panelTransparent1.add(crazyPanel16);
        crazyPanel16.setBounds(0, 10, 210, 36);

        crazyPanel17.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel17.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 200"
            }
        ));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        crazyPanel17.add(jComboBox1);

        panelTransparent1.add(crazyPanel17);
        crazyPanel17.setBounds(520, 160, 120, 36);

        jLabel3.setText("Size");
        panelTransparent1.add(jLabel3);
        jLabel3.setBounds(370, 80, 60, 16);

        crazyPanel18.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "JTextField.placeholderText=Nhập Số lượng tồn;background:@background"
            }
        ));
        crazyPanel18.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 200"
            }
        ));

        txtMa2.setPreferredSize(new java.awt.Dimension(90, 22));
        crazyPanel18.add(txtMa2);

        panelTransparent1.add(crazyPanel18);
        crazyPanel18.setBounds(360, 160, 160, 36);

        jLabel4.setText("Giá");
        panelTransparent1.add(jLabel4);
        jLabel4.setBounds(490, 80, 40, 16);

        crazyPanel19.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "JTextField.placeholderText=Nhập Mã;background:@background"
            }
        ));
        crazyPanel19.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 200"
            }
        ));

        txtMa3.setPreferredSize(new java.awt.Dimension(90, 22));
        crazyPanel19.add(txtMa3);

        panelTransparent1.add(crazyPanel19);
        crazyPanel19.setBounds(360, 40, 120, 36);

        jLabel5.setText("Số lượng tồn");
        panelTransparent1.add(jLabel5);
        jLabel5.setBounds(370, 200, 80, 16);

        crazyPanel20.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "JTextField.placeholderText=Nhập Giá;background:@background"
            }
        ));
        crazyPanel20.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 200"
            }
        ));

        txtMa4.setPreferredSize(new java.awt.Dimension(90, 22));
        crazyPanel20.add(txtMa4);

        panelTransparent1.add(crazyPanel20);
        crazyPanel20.setBounds(480, 100, 110, 36);

        jLabel6.setText("Số Lượng");
        panelTransparent1.add(jLabel6);
        jLabel6.setBounds(370, 140, 60, 16);

        crazyPanel21.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "JTextField.placeholderText=Nhập Số lượng tồn;background:@background"
            }
        ));
        crazyPanel21.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 200"
            }
        ));

        txtMa5.setPreferredSize(new java.awt.Dimension(90, 22));
        crazyPanel21.add(txtMa5);

        panelTransparent1.add(crazyPanel21);
        crazyPanel21.setBounds(360, 220, 150, 36);

        jLabel7.setText("Màu Sắc");
        panelTransparent1.add(jLabel7);
        jLabel7.setBounds(530, 140, 70, 16);

        crazyPanel22.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel22.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 200"
            }
        ));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        crazyPanel22.add(jComboBox2);

        panelTransparent1.add(crazyPanel22);
        crazyPanel22.setBounds(360, 100, 120, 36);

        crazyPanel23.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel23.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 200"
            }
        ));

        cmdAdd.setText("Add new");
        crazyPanel23.add(cmdAdd);

        cmdDelete.setText("Delete");
        crazyPanel23.add(cmdDelete);

        cmdExcel.setText("Excel");
        cmdExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExcelActionPerformed(evt);
            }
        });
        crazyPanel23.add(cmdExcel);

        cmdUpdate.setText("Update");
        crazyPanel23.add(cmdUpdate);

        cmdQR.setText("QR code");
        crazyPanel23.add(cmdQR);

        panelTransparent1.add(crazyPanel23);
        crazyPanel23.setBounds(0, 270, 500, 37);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "ID", "Mã", "Tên", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTable2);

        panelTransparent1.add(jScrollPane8);
        jScrollPane8.setBounds(0, 320, 710, 200);

        crazyPanel9.add(panelTransparent1);

        crazyPanel13.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel13.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            null
        ));

        panelTransparent2.setPreferredSize(new java.awt.Dimension(139, 500));

        jList1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setFixedCellHeight(40);
        jScrollPane3.setViewportView(jList1);

        panelTransparent2.add(jScrollPane3);
        jScrollPane3.setBounds(0, 0, 280, 332);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 51, 51));
        jLabel8.setText("Tổng Tiền:");
        panelTransparent2.add(jLabel8);
        jLabel8.setBounds(40, 350, 110, 22);

        crazyPanel24.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "JTextField.placeholderText=Search;background:@background",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel24.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 200"
            }
        ));

        cmdUpdate5.setText("Tạo Hóa Đơn");
        crazyPanel24.add(cmdUpdate5);

        panelTransparent2.add(crazyPanel24);
        crazyPanel24.setBounds(30, 380, 210, 40);

        crazyPanel13.add(panelTransparent2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(crazyPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(crazyPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(crazyPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
            .addComponent(crazyPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );

        materialTabbed1.addTab("Bán tại quầy", jPanel1);

        jButton1.setText("jButton1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(373, 373, 373)
                .addComponent(jButton1)
                .addContainerGap(654, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(jButton1)
                .addContainerGap(273, Short.MAX_VALUE))
        );

        materialTabbed1.addTab("Bán online", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(materialTabbed1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void cmdExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdExcelActionPerformed

    private void txtMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton cmdExcel;
    private javax.swing.JButton cmdQR;
    private javax.swing.JButton cmdUpdate;
    private javax.swing.JButton cmdUpdate5;
    private raven.crazypanel.CrazyPanel crazyPanel13;
    private raven.crazypanel.CrazyPanel crazyPanel15;
    private raven.crazypanel.CrazyPanel crazyPanel16;
    private raven.crazypanel.CrazyPanel crazyPanel17;
    private raven.crazypanel.CrazyPanel crazyPanel18;
    private raven.crazypanel.CrazyPanel crazyPanel19;
    private raven.crazypanel.CrazyPanel crazyPanel20;
    private raven.crazypanel.CrazyPanel crazyPanel21;
    private raven.crazypanel.CrazyPanel crazyPanel22;
    private raven.crazypanel.CrazyPanel crazyPanel23;
    private raven.crazypanel.CrazyPanel crazyPanel24;
    private raven.crazypanel.CrazyPanel crazyPanel9;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private da.component.MaterialTabbed materialTabbed1;
    private da.component.PanelTransparent panelTransparent1;
    private da.component.PanelTransparent panelTransparent2;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMa2;
    private javax.swing.JTextField txtMa3;
    private javax.swing.JTextField txtMa4;
    private javax.swing.JTextField txtMa5;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
