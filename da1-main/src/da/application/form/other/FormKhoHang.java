/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package da.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import da.component.MyList;
import da.model.KhuVucKho;
import da.model.KhoHang;
import da.model.NhapKho;
import da.model.NhaCC;
import da.model.NhanVien;
import da.repository.KhoHangRepository;
import da.repository.NhapKhoRespository;
import da.repository.NhaCungCapRepository;
import da.service.NhanVienService;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import raven.toast.Notifications;

/**
 *
 * @author ADMIN
 */
public class FormKhoHang extends javax.swing.JPanel {
    private final KhoHangRepository repository;
    private final NhapKhoRespository nhapKhoRespository;
   
    /**
     * Creates new form FormKhoHang
     */
    public FormKhoHang() {
        initComponents();
        repository = new KhoHangRepository();
        nhapKhoRespository = new NhapKhoRespository();
        applyTableStyle(tblKhuVuc);
        applyTableStyle(tbNhapKho);
        loadKhuVucKhoData(repository.searchKhuVucKhoByName(""));
        loadNhapKho(nhapKhoRespository.getListNhapKho());
    }
    
    private void applyTableStyle(JTable table) {

        cmdSearch.setIcon(new FlatSVGIcon("da/icon/svg/search.svg", 0.35f));
        cmdAdd.setIcon(new FlatSVGIcon("da/icon/svg/add.svg", 0.35f));
        cmdDelete.setIcon(new FlatSVGIcon("da/icon/svg/delete.svg", 0.35f));
        cmdUpdate.setIcon(new FlatSVGIcon("da/icon/svg/edit.svg", 0.35f));
        btnDeleteNhapKho.setIcon(new FlatSVGIcon("da/icon/svg/details.svg", 0.35f));
        btnupdateNhapKho.setIcon(new FlatSVGIcon("da/icon/svg/edit.svg", 0.35f));
        btnaddnk.setIcon(new FlatSVGIcon("da/icon/svg/delete.svg", 0.35f));
        cmdExcel1.setIcon(new FlatSVGIcon("da/icon/svg/print.svg", 0.35f));
        btnLamMoi.setIcon(new FlatSVGIcon("da/icon/svg/reset.svg", 0.35f));
        lblFilter1.setIcon(new FlatSVGIcon("da/icon/svg/filter.svg", 0.35f));

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
                        case 3 -> label.setHorizontalAlignment(SwingConstants.TRAILING);
                        default -> label.setHorizontalAlignment(SwingConstants.LEADING);
                    }
                }
                return com;
            }
        };
    }
    
    
    private void applyListStyle(int idKhuVucKho) {
        List<KhoHang> khList = repository.getProductsByKhuVucKho(idKhuVucKho); // Cần triển khai trong KhoHangRepository
        myList1.removeAll();
        if (khList.isEmpty()) {
            JLabel emptyLabel = new JLabel("Không có sản phẩm nào trong khu vực kho này.");
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            myList1.setLayout(new BorderLayout());
            myList1.add(emptyLabel, BorderLayout.CENTER);
        } else {
            MyList<KhoHang> list = new MyList<>();
            for (KhoHang kh : khList) {
                list.addItem(kh);
            }
            JScrollPane scrollPane = new JScrollPane(list);
            myList1.setLayout(new BorderLayout());
            myList1.add(scrollPane, BorderLayout.CENTER);
        }
    }
    
    public void loadKhuVucKhoData(ArrayList<KhuVucKho> list) {
        DefaultTableModel tblModel = (DefaultTableModel) tblKhuVuc.getModel();
        tblModel.setRowCount(0);
        for (KhuVucKho kvk : list) {
            Object[] rowData = {
                kvk.getId(),
                kvk.getTenKhuVuc(),
                kvk.getMoTa(),
            };
            tblModel.addRow(rowData);
        }
    }
    public void loadNhapKho(List<NhapKho> list) {
        DefaultTableModel tblModel = (DefaultTableModel) tbNhapKho.getModel();
        tblModel.setRowCount(0);
        for (NhapKho nk : list) {
            Object[] rowData = {
                nk.getId(),
                nk.getManhap(),
                nk.getNhacungcap(),
                nk.getNhanvien(),
                nk.getSanPham(),
                nk.getSoLuong(),
                nk.getKhuvuckho(),
                nk.getNgaynhap(),
                nk.getTongtien()
            };
            tblModel.addRow(rowData);
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        materialTabbed2 = new da.component.MaterialTabbed();
        jPanel5 = new javax.swing.JPanel();
        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        cmdAdd = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();
        cmdUpdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhuVuc = new javax.swing.JTable();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        myList1 = new da.component.MyList<>();
        jPanel4 = new javax.swing.JPanel();
        crazyPanel5 = new raven.crazypanel.CrazyPanel();
        panelTransparent2 = new da.component.PanelTransparent();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        crazyPanel16 = new raven.crazypanel.CrazyPanel();
        jTextField3 = new javax.swing.JTextField();
        crazyPanel17 = new raven.crazypanel.CrazyPanel();
        jComboBox4 = new javax.swing.JComboBox<>();
        crazyPanel18 = new raven.crazypanel.CrazyPanel();
        jComboBox5 = new javax.swing.JComboBox<>();
        crazyPanel19 = new raven.crazypanel.CrazyPanel();
        jTextField4 = new javax.swing.JTextField();
        crazyPanel6 = new raven.crazypanel.CrazyPanel();
        crazyPanel7 = new raven.crazypanel.CrazyPanel();
        txtSearch1 = new javax.swing.JTextField();
        lblFilter1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnaddnk = new javax.swing.JButton();
        btnupdateNhapKho = new javax.swing.JButton();
        btnDeleteNhapKho = new javax.swing.JButton();
        cmdExcel1 = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbNhapKho = new javax.swing.JTable();

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            null
        ));

        crazyPanel2.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "JTextField.placeholderText=Search;background:@background",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 200"
            }
        ));
        crazyPanel2.add(txtSearch);

        cmdSearch.setText("Search");
        cmdSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSearchActionPerformed(evt);
            }
        });
        crazyPanel2.add(cmdSearch);

        cmdAdd.setText("Add");
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        crazyPanel2.add(cmdAdd);

        cmdDelete.setText("Delete");
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });
        crazyPanel2.add(cmdDelete);

        cmdUpdate.setText("Update");
        cmdUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUpdateActionPerformed(evt);
            }
        });
        crazyPanel2.add(cmdUpdate);

        crazyPanel1.add(crazyPanel2);

        tblKhuVuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên Khu Vực", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhuVuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuVucMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhuVuc);

        crazyPanel1.add(jScrollPane1);

        crazyPanel3.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            null
        ));

        jLabel1.setText("Danh Sách Sản Phẩm đang có trong khu vực");
        crazyPanel3.add(jLabel1);

        myList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(myList1);

        crazyPanel3.add(jScrollPane4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(685, Short.MAX_VALUE)
                .addComponent(crazyPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(521, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        materialTabbed2.addTab("Danh sách ", jPanel5);

        crazyPanel5.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel5.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            null
        ));

        panelTransparent2.setPreferredSize(new java.awt.Dimension(235, 445));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Nhà Cung Cấp");
        panelTransparent2.add(jLabel3);
        jLabel3.setBounds(10, 16, 120, 20);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Nhân Viên Nhập");
        panelTransparent2.add(jLabel4);
        jLabel4.setBounds(10, 80, 120, 20);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Từ ngày");
        panelTransparent2.add(jLabel5);
        jLabel5.setBounds(10, 140, 70, 20);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Đến ngày");
        panelTransparent2.add(jLabel6);
        jLabel6.setBounds(10, 210, 90, 20);

        crazyPanel16.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "JTextField.placeholderText=Nhập ngày;background:@background"
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
        crazyPanel16.add(jTextField3);

        panelTransparent2.add(crazyPanel16);
        crazyPanel16.setBounds(0, 240, 210, 40);

        crazyPanel17.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "JTextField.placeholderText=Search;background:@background"
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

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        crazyPanel17.add(jComboBox4);

        panelTransparent2.add(crazyPanel17);
        crazyPanel17.setBounds(0, 40, 210, 40);

        crazyPanel18.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "JTextField.placeholderText=Search;background:@background"
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

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        crazyPanel18.add(jComboBox5);

        panelTransparent2.add(crazyPanel18);
        crazyPanel18.setBounds(0, 100, 210, 40);

        crazyPanel19.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "JTextField.placeholderText=Nhập ngày;background:@background"
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
        crazyPanel19.add(jTextField4);

        panelTransparent2.add(crazyPanel19);
        crazyPanel19.setBounds(0, 160, 210, 40);

        crazyPanel5.add(panelTransparent2);

        crazyPanel6.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel6.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            null
        ));

        crazyPanel7.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "JTextField.placeholderText=Search;background:@background",
                "",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel7.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[]push[][]",
            "",
            new String[]{
                "width 200"
            }
        ));
        crazyPanel7.add(txtSearch1);
        crazyPanel7.add(lblFilter1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        crazyPanel7.add(jComboBox1);

        btnaddnk.setText("Add new");
        btnaddnk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddnkActionPerformed(evt);
            }
        });
        crazyPanel7.add(btnaddnk);

        btnupdateNhapKho.setText("Update");
        btnupdateNhapKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateNhapKhoActionPerformed(evt);
            }
        });
        crazyPanel7.add(btnupdateNhapKho);

        btnDeleteNhapKho.setText("Delete");
        btnDeleteNhapKho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteNhapKhoActionPerformed(evt);
            }
        });
        crazyPanel7.add(btnDeleteNhapKho);

        cmdExcel1.setText("Excel");
        crazyPanel7.add(cmdExcel1);

        btnLamMoi.setText("Làm Mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        crazyPanel7.add(btnLamMoi);

        crazyPanel6.add(crazyPanel7);

        tbNhapKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Nhập kho", "Nhà cung cấp", "Nhân viên nhập", "Tên sản phẩm", "Số lượng", "Khu Vực Kho", "Ngày nhập", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbNhapKho);

        crazyPanel6.add(jScrollPane2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(crazyPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(crazyPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                    .addComponent(crazyPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
                .addContainerGap())
        );

        materialTabbed2.addTab("Nhập Kho", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(materialTabbed2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        JTextField txtTenKhuVuc = new JTextField();
        JTextArea txtMoTa = new JTextArea(5, 20);
        JScrollPane scrollMoTa = new JScrollPane(txtMoTa);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Tên Khu Vực:"), gbc);
        gbc.gridx = 1;
        panel.add(txtTenKhuVuc, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Mô Tả:"), gbc);
        gbc.gridx = 1;
        panel.add(scrollMoTa, gbc);

        while (true) {
            int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Thêm Khu Vực Kho",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );
            if (result != JOptionPane.OK_OPTION) {
                return;
            }
            String tenKhuVuc = txtTenKhuVuc.getText().trim();
            String moTa = txtMoTa.getText().trim();

            if (tenKhuVuc.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tên khu vực kho không được để trống!");
                continue;
            }
            if (tenKhuVuc.length() > 50) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tên khu vực không được vượt quá 50 kí tự!");
                continue;
            }
            if (moTa.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Mô tả không được để trống!");
                continue;
            }
            if (moTa.length() > 255) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Mô tả không được vượt quá 255 kí tự!");
                continue;
            }

            KhuVucKho khuVucKho = new KhuVucKho(0, tenKhuVuc, moTa);
            boolean isAdded = repository.addKhuVucKho(khuVucKho);
            if (isAdded) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Thêm khu vực kho thành công!");
                loadKhuVucKhoData(repository.getAllKhuVucKho());
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Thêm khu vực kho thất bại!");
            }
            return;
        }
    }//GEN-LAST:event_cmdAddActionPerformed

    private void cmdUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUpdateActionPerformed
        int selectedRow = tblKhuVuc.getSelectedRow();
        if (selectedRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn một khu vực để cập nhật!");
            return;
        }

        int id = (int) tblKhuVuc.getValueAt(selectedRow, 0);
        String currentTenKhuVuc = (String) tblKhuVuc.getValueAt(selectedRow, 1);
        String currentMoTa = (String) tblKhuVuc.getValueAt(selectedRow, 2);

        JTextField txtTenKhuVuc = new JTextField(currentTenKhuVuc);
        JTextArea txtMoTa = new JTextArea(currentMoTa, 5, 20);
        JScrollPane scrollMoTa = new JScrollPane(txtMoTa);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Tên Khu Vực:"), gbc);
        gbc.gridx = 1;
        panel.add(txtTenKhuVuc, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Mô Tả:"), gbc);
        gbc.gridx = 1;
        panel.add(scrollMoTa, gbc);

        while (true) {
            int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Cập nhật Khu Vực Kho",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );
            if (result != JOptionPane.OK_OPTION) {
                return;
            }

            String tenKhuVuc = txtTenKhuVuc.getText().trim();
            String moTa = txtMoTa.getText().trim();

            if (tenKhuVuc.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tên khu vực không được để trống!");
                txtTenKhuVuc.requestFocus();
                continue;
            }
            if (tenKhuVuc.length() > 50) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tên khu vực không được vượt quá 50 ký tự!");
                txtTenKhuVuc.requestFocus();
                continue;
            }
            if (moTa.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Mô tả không được để trống!");
                txtMoTa.requestFocus();
                continue;
            }
            if (moTa.length() > 255) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Mô tả không được vượt quá 255 ký tự!");
                txtMoTa.requestFocus();
                continue;
            }

            KhuVucKho updatedKhuVucKho = new KhuVucKho(id, tenKhuVuc, moTa);
            boolean isUpdated = repository.updateKhuVucKho(updatedKhuVucKho);
            if (isUpdated) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Cập nhật khu vực kho thành công!");
                loadKhuVucKhoData(repository.getAllKhuVucKho());
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Cập nhật khu vực kho thất bại!");
            }
            return;
        }
    }//GEN-LAST:event_cmdUpdateActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        int selectedRow = tblKhuVuc.getSelectedRow();
        if (selectedRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn một khu vực để xóa!");
            return;
        }

        int id = (int) tblKhuVuc.getValueAt(selectedRow, 0);
        String tenKhuVuc = (String) tblKhuVuc.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc chắn muốn xóa khu vực kho \"" + tenKhuVuc + "\"?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        boolean isDeleted = repository.deleteKhuVucKho(id);
        if (isDeleted) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xóa khu vực kho thành công!");
            loadKhuVucKhoData(repository.getAllKhuVucKho());
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Xóa khu vực kho thất bại!");
        }
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void cmdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchActionPerformed
        String keyword = txtSearch.getText().trim();
        loadKhuVucKhoData(repository.searchKhuVucKhoByName(keyword));
    }//GEN-LAST:event_cmdSearchActionPerformed

    private void tblKhuVucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuVucMouseClicked
        int selectedRow = tblKhuVuc.getSelectedRow();
        if (selectedRow != -1) {
            int idKhuVucKho = (int) tblKhuVuc.getValueAt(selectedRow, 0);
            applyListStyle(idKhuVucKho);
        }
    }//GEN-LAST:event_tblKhuVucMouseClicked

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void btnaddnkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddnkActionPerformed
        // TODO add your handling code here:
    NhanVienService nhanVienService = new NhanVienService();
    NhaCungCapRepository nhaCCRepository = new NhaCungCapRepository();
    List<NhanVien> nhanVienList = nhanVienService.getAllNhanVien();
    List<NhaCC> nhaCCList = nhaCCRepository.getAllNhaCungCap();
    JTextField txtMaNhap = new JTextField();
    JComboBox<NhaCC> cbNhaCungCap = new JComboBox<>(new DefaultComboBoxModel<>(nhaCCList.toArray(new NhaCC[0])));
    JComboBox<NhanVien> cbNhanVien = new JComboBox<>(new DefaultComboBoxModel<>(nhanVienList.toArray(new NhanVien[0])));
    JTextField txtNgayNhap = new JTextField();
    JTextField txtTongTien = new JTextField();
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    txtNgayNhap.setText(today.format(formatter));
    cbNhaCungCap.setRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof NhaCC nhaCC) {
                setText(nhaCC.getTen());
            }
            return this;
        }
    });
    cbNhanVien.setRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof NhanVien nhanVien) {
                setText(nhanVien.getHo() + " " + nhanVien.getTen());
            }
            return this;
        }
    });
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(new JLabel("Mã Nhập Kho:"), gbc);
    gbc.gridx = 1;
    panel.add(txtMaNhap, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(new JLabel("Nhà Cung Cấp:"), gbc);
    gbc.gridx = 1;
    panel.add(cbNhaCungCap, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    panel.add(new JLabel("Nhân Viên Nhập:"), gbc);
    gbc.gridx = 1;
    panel.add(cbNhanVien, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    panel.add(new JLabel("Ngày Nhập (dd/MM/yyyy):"), gbc);
    gbc.gridx = 1;
    panel.add(txtNgayNhap, gbc);

    gbc.gridx = 0;
    gbc.gridy = 4;
    panel.add(new JLabel("Tổng Tiền:"), gbc);
    gbc.gridx = 1;
    panel.add(txtTongTien, gbc);
    while (true) {
        int result = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Thêm Phiếu Nhập Kho",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }
        String maNhap = txtMaNhap.getText().trim();
        NhaCC nhaCungCap = (NhaCC) cbNhaCungCap.getSelectedItem();
        NhanVien nhanVien = (NhanVien) cbNhanVien.getSelectedItem();
        String ngayNhapStr = txtNgayNhap.getText().trim();
        String tongTienStr = txtTongTien.getText().trim();
        if (maNhap.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Mã nhập kho không được để trống!");
            txtMaNhap.requestFocus();
            continue;
        }
        if (maNhap.length() > 20) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Mã nhập kho không được vượt quá 20 ký tự!");
            txtMaNhap.requestFocus();
            continue;
        }
        if (nhaCungCap == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn nhà cung cấp!");
            cbNhaCungCap.requestFocus();
            continue;
        }
        if (nhanVien == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn nhân viên!");
            cbNhanVien.requestFocus();
            continue;
        }
        if (ngayNhapStr.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Ngày nhập không được để trống!");
            txtNgayNhap.requestFocus();
            continue;
        }
        Timestamp ngayNhap = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            Date parsedDate = sdf.parse(ngayNhapStr);
            ngayNhap = new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Ngày nhập không đúng định dạng (dd/MM/yyyy)!");
            txtNgayNhap.requestFocus();
            continue;
        }
        double tongTien;
        try {
            tongTien = Double.parseDouble(tongTienStr);
            if (tongTien < 0) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tổng tiền không được âm!");
                txtTongTien.requestFocus();
                continue;
            }
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Tổng tiền phải là số hợp lệ!");
            txtTongTien.requestFocus();
            continue;
        }
        NhapKho nhapKho = new NhapKho();
        nhapKho.setManhap(maNhap);
        nhapKho.setNhacungcap(nhaCungCap.getTen());
        nhapKho.setNhanvien(nhanVien.getHo() + " " + nhanVien.getTen());
        nhapKho.setNgaynhap(ngayNhap);
        nhapKho.setTongtien(tongTien);
        boolean isAdded = nhapKhoRespository.addNhapKho(nhapKho, nhaCungCap.getId(), nhanVien.getId());
        if (isAdded) {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Thêm phiếu nhập kho thành công!");
            loadNhapKho(nhapKhoRespository.getListNhapKho());
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Thêm phiếu nhập kho thất bại!");
        }
        return;
    }
    }//GEN-LAST:event_btnaddnkActionPerformed

    private void btnupdateNhapKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateNhapKhoActionPerformed
        // TODO add your handling code here:
        int selectedRow = tbNhapKho.getSelectedRow();
    if (selectedRow == -1) {
        Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn một phiếu nhập kho để cập nhật!");
        return;
    }
    int nhapKhoId = (int) tbNhapKho.getValueAt(selectedRow, 0);

    NhapKho selectedNhapKho = null;
    List<NhapKho> nhapKhoList = nhapKhoRespository.getListNhapKho();
    for (NhapKho nk : nhapKhoList) {
        if (nk.getId() == nhapKhoId) {
            selectedNhapKho = nk;
            break;
        }
    }
    if (selectedNhapKho == null) {
        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Không tìm thấy phiếu nhập kho!");
        return;
    }
    NhanVienService nhanVienService = new NhanVienService();
    NhaCungCapRepository nhaCCRepository = new NhaCungCapRepository();
    List<NhanVien> nhanVienList = nhanVienService.getAllNhanVien();
    List<NhaCC> nhaCCList = nhaCCRepository.getAllNhaCungCap();
    NhaCC currentNhaCC = null;
    for (NhaCC ncc : nhaCCList) {
        if (ncc.getTen().equals(selectedNhapKho.getNhacungcap())) {
            currentNhaCC = ncc;
            break;
        }
    }

    NhanVien currentNhanVien = null;
    for (NhanVien nv : nhanVienList) {
        if ((nv.getHo() + " " + nv.getTen()).equals(selectedNhapKho.getNhanvien())) {
            currentNhanVien = nv;
            break;
        }
    }
    JComboBox<NhaCC> cbNhaCungCap = new JComboBox<>(new DefaultComboBoxModel<>(nhaCCList.toArray(new NhaCC[0])));
    JComboBox<NhanVien> cbNhanVien = new JComboBox<>(new DefaultComboBoxModel<>(nhanVienList.toArray(new NhanVien[0])));
    cbNhaCungCap.setSelectedItem(currentNhaCC);
    cbNhanVien.setSelectedItem(currentNhanVien);
    cbNhaCungCap.setRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof NhaCC nhaCC) {
                setText(nhaCC.getTen());
            }
            return this;
        }
    });

    cbNhanVien.setRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof NhanVien nhanVien) {
                setText(nhanVien.getHo() + " " + nhanVien.getTen());
            }
            return this;
        }
    });
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(new JLabel("Nhà Cung Cấp:"), gbc);
    gbc.gridx = 1;
    panel.add(cbNhaCungCap, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(new JLabel("Nhân Viên Nhập:"), gbc);
    gbc.gridx = 1;
    panel.add(cbNhanVien, gbc);
    while (true) {
        int result = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Cập Nhật Phiếu Nhập Kho",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        NhaCC nhaCungCap = (NhaCC) cbNhaCungCap.getSelectedItem();
        NhanVien nhanVien = (NhanVien) cbNhanVien.getSelectedItem();

        if (nhaCungCap == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn nhà cung cấp!");
            cbNhaCungCap.requestFocus();
            continue;
        }
        if (nhanVien == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn nhân viên!");
            cbNhanVien.requestFocus();
            continue;
        }

        boolean isUpdated = nhapKhoRespository.updateNhapKho(nhapKhoId, nhaCungCap.getId(), nhanVien.getId());
        if (isUpdated) {
            selectedNhapKho.setNhacungcap(nhaCungCap.getTen());
            selectedNhapKho.setNhanvien(nhanVien.getHo() + " " + nhanVien.getTen());

            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Cập nhật phiếu nhập kho thành công!");
            loadNhapKho(nhapKhoRespository.getListNhapKho());
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Cập nhật phiếu nhập kho thất bại!");
        }
        return;
    }
    }//GEN-LAST:event_btnupdateNhapKhoActionPerformed

    private void btnDeleteNhapKhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteNhapKhoActionPerformed
        // TODO add your handling code here:
        int selectedRow = tbNhapKho.getSelectedRow();
    if (selectedRow == -1) {
        Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui lòng chọn một phiếu nhập kho để xóa!");
        return;
    }
    int nhapKhoId = (int) tbNhapKho.getValueAt(selectedRow, 0);

    NhapKho selectedNhapKho = null;
    List<NhapKho> nhapKhoList = nhapKhoRespository.getListNhapKho();
    for (NhapKho nk : nhapKhoList) {
        if (nk.getId() == nhapKhoId) {
            selectedNhapKho = nk;
            break;
        }
    }
    if (selectedNhapKho == null) {
        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Không tìm thấy phiếu nhập kho!");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(
        null,
        "Bạn có chắc chắn muốn xóa phiếu nhập kho '" + selectedNhapKho.getManhap() + "' không?","Xác Nhận Xóa",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
    );

    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }
    boolean isDeleted = nhapKhoRespository.deleteNhapKho(nhapKhoId);
    if (isDeleted) {
        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xóa phiếu nhập kho thành công!");
        loadNhapKho(nhapKhoRespository.getListNhapKho());
    } else {
        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Xóa phiếu nhập kho thất bại!");
    }
    }//GEN-LAST:event_btnDeleteNhapKhoActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
    tbNhapKho.clearSelection();

    loadNhapKho(nhapKhoRespository.getListNhapKho());
    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Đã làm mới bảng phiếu nhập kho!");
    }//GEN-LAST:event_btnLamMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteNhapKho;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnaddnk;
    private javax.swing.JButton btnupdateNhapKho;
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton cmdExcel1;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JButton cmdUpdate;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel16;
    private raven.crazypanel.CrazyPanel crazyPanel17;
    private raven.crazypanel.CrazyPanel crazyPanel18;
    private raven.crazypanel.CrazyPanel crazyPanel19;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel5;
    private raven.crazypanel.CrazyPanel crazyPanel6;
    private raven.crazypanel.CrazyPanel crazyPanel7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel lblFilter1;
    private da.component.MaterialTabbed materialTabbed2;
    private da.component.MyList<String> myList1;
    private da.component.PanelTransparent panelTransparent2;
    private javax.swing.JTable tbNhapKho;
    private javax.swing.JTable tblKhuVuc;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSearch1;
    // End of variables declaration//GEN-END:variables
}
