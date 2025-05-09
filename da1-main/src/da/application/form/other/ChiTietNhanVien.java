/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package da.application.form.other;

import da.application.Application;
import da.model.NhanVien;
import da.service.NhanVienService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ADMIN
 */
public class ChiTietNhanVien extends javax.swing.JPanel {
        NhanVienService service = new NhanVienService();
        private NhanVien nhanVien;

    /**
     * Creates new form AddNhanVien
     */
    public ChiTietNhanVien(NhanVien nhanVien) {
        initComponents();
        initUI();
        initChucVu();
        initTrangThai();
        populateForm(nhanVien);
    }
    
        public void initChucVu() {
        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
        boxModel.addElement("-- Chọn chức vụ --");

        HashSet<String> chucVuSet = service.getAllChucVu();  // Lấy danh sách các chức vụ
        for (String chucVu : chucVuSet) {
            if (chucVu != null) {
                boxModel.addElement(chucVu);  // Thêm vào ComboBoxModel
            }
        }
        cboChucVu.setModel(boxModel);  // Gán model cho ComboBox
    }

    public void initTrangThai() {
        DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
        boxModel.addElement("-- Chọn trạng thái --");

        HashSet<String> trangThaiSet = service.getAllTrangThai();  // Lấy danh sách các trạng thái
        for (String trangThai : trangThaiSet) {
            if (trangThai != null) {
                boxModel.addElement(trangThai);  // Thêm vào ComboBoxModel
            }
        }
        cboTrangThai.setModel(boxModel);  // Gán model cho ComboBox
    }


    
    private void initUI() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (datePicker1 != null) {
            datePicker1.setEditor(txtNgaySinh);

            // Lắng nghe sự kiện khi người dùng chọn ngày
            datePicker1.addDateSelectionListener(event -> {
                LocalDate date = datePicker1.getSelectedDate();
                if (date != null) {
                    txtNgaySinh.setText(date.format(df));  // Chuyển LocalDate thành String
                }
            });

            datePicker1.setDateSelectionAble(date -> date != null && !date.isAfter(LocalDate.now()));
        }

        if (datePicker2 != null) {
            datePicker2.setEditor(txtNgayLam);

            // Lắng nghe sự kiện khi người dùng chọn ngày
            datePicker2.addDateSelectionListener(event -> {
                LocalDate date = datePicker2.getSelectedDate();
                if (date != null) {
                    txtNgayLam.setText(date.format(df));  // Chuyển LocalDate thành String
                }
            });

            datePicker2.setDateSelectionAble(date -> date != null && !date.isAfter(LocalDate.now()));
        }
    }

    
    public void populateForm(NhanVien nhanVien) {
        if (nhanVien != null) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            // Điền thông tin vào các trường văn bản
            txtMa.setText(nhanVien.getMaNV());
            txtHo.setText(nhanVien.getHo());
            txtTen.setText(nhanVien.getTen());
            txtSDT.setText(nhanVien.getSdt());
            txtEmail.setText(nhanVien.getEmail());
            txtDiaChi.setText(nhanVien.getDiaChi());

            // Điền giới tính vào combo box
            cboGioiTinh.setSelectedItem(nhanVien.isGioiTinh() ? "Nam" : "Nữ");

            // Điền ngày sinh và ngày vào làm
            try {
                if (nhanVien.getNgaySinh() != null && !nhanVien.getNgaySinh().isEmpty()) {
                    // Đảm bảo định dạng ngày tháng khớp với định dạng dự kiến
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Định dạng hiện tại trong cơ sở dữ liệu
                    LocalDate ngaySinh = LocalDate.parse(nhanVien.getNgaySinh(), inputFormatter);
                    datePicker1.setSelectedDate(ngaySinh);
                    txtNgaySinh.setText(ngaySinh.format(df));
                }
                if (nhanVien.getNgayVaoLam() != null && !nhanVien.getNgayVaoLam().isEmpty()) {
                    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Định dạng hiện tại trong cơ sở dữ liệu
                    LocalDate ngayVaoLam = LocalDate.parse(nhanVien.getNgayVaoLam(), inputFormatter);
                    datePicker2.setSelectedDate(ngayVaoLam);
                    txtNgayLam.setText(ngayVaoLam.format(df));
                }
            } catch (Exception e) {
                // Debug thông tin lỗi
                System.err.println("Error parsing dates:");
                e.printStackTrace();
            }

            // Điền chức vụ và trạng thái vào combo box
            cboChucVu.setSelectedItem(nhanVien.getChucVu());
            cboTrangThai.setSelectedItem(nhanVien.getTrangThai());
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

        datePicker1 = new raven.datetime.component.date.DatePicker();
        datePicker2 = new raven.datetime.component.date.DatePicker();
        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        txtHo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cboGioiTinh = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JFormattedTextField();
        jLabel17 = new javax.swing.JLabel();
        txtNgayLam = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        cboChucVu = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        cmdSave = new javax.swing.JButton();

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20;[light]background:shade(@background,2%);[dark]background:tint(@background,2%)",
            new String[]{
                "font:bold +10",
                "font:bold +1",
                "",
                "",
                "showClearButton:true;JTextField.placeholderText=Tên",
                "showClearButton:true;JTextField.placeholderText=Họ",
                "",
                "showClearButton:true;JTextField.placeholderText=Số điện thoại",
                "",
                "showClearButton:true;JTextField.placeholderText=example@gmail.com",
                "",
                "font:bold +1",
                "",
                "",
                "showClearButton:true;JTextField.placeholderText=Mã Nhân Viên",
                "",
                "showClearButton:true;JTextField.placeholderText=Địa chỉ nơi ở",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
            }
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap 2,fillx,insets 25",
            "[grow 0,trail]15[fill]",
            "",
            new String[]{
                "wrap,al lead",
                "wrap,al lead",
                "wrap,al lead",
                "",
                "split 2",
                "",
                "",
                "",
                "",
                "",
                "span 2,grow 1",
                "wrap,al lead",
                "wrap,al lead",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "span 2,al trail"
            }
        ));

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Chi Tiết Nhân Viên");
        crazyPanel1.add(jLabel1);

        jLabel2.setText("Thông tin liên lạc");
        crazyPanel1.add(jLabel2);

        jLabel3.setText("Được sử dụng để theo dõi nhân viên");
        crazyPanel1.add(jLabel3);

        jLabel4.setText("Họ Tên");
        crazyPanel1.add(jLabel4);

        txtTen.setEnabled(false);
        crazyPanel1.add(txtTen);

        txtHo.setEnabled(false);
        crazyPanel1.add(txtHo);

        jLabel5.setText("SĐT");
        crazyPanel1.add(jLabel5);

        txtSDT.setEnabled(false);
        crazyPanel1.add(txtSDT);

        jLabel6.setText("Email");
        crazyPanel1.add(jLabel6);

        txtEmail.setEnabled(false);
        crazyPanel1.add(txtEmail);
        crazyPanel1.add(jSeparator1);

        jLabel7.setText("Thông tin nhân viên");
        crazyPanel1.add(jLabel7);

        jLabel8.setText("Được sử dụng để quản lý nhân viên");
        crazyPanel1.add(jLabel8);

        jLabel9.setText("Mã Nhân Viên");
        crazyPanel1.add(jLabel9);

        txtMa.setEnabled(false);
        crazyPanel1.add(txtMa);

        jLabel10.setText("Địa Chỉ");
        crazyPanel1.add(jLabel10);

        txtDiaChi.setEnabled(false);
        crazyPanel1.add(txtDiaChi);

        jLabel11.setText("Giới Tính");
        crazyPanel1.add(jLabel11);

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboGioiTinh.setEnabled(false);
        crazyPanel1.add(cboGioiTinh);

        jLabel16.setText("Ngày Sinh");
        crazyPanel1.add(jLabel16);

        txtNgaySinh.setEnabled(false);
        crazyPanel1.add(txtNgaySinh);

        jLabel17.setText("Ngày Vào Làm");
        crazyPanel1.add(jLabel17);

        txtNgayLam.setEnabled(false);
        crazyPanel1.add(txtNgayLam);

        jLabel18.setText("Chức Vụ");
        crazyPanel1.add(jLabel18);

        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboChucVu.setEnabled(false);
        crazyPanel1.add(cboChucVu);

        jLabel19.setText("Trạng thái");
        crazyPanel1.add(jLabel19);

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTrangThai.setEnabled(false);
        crazyPanel1.add(cboTrangThai);

        cmdSave.setText("Save");
        cmdSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveActionPerformed(evt);
            }
        });
        crazyPanel1.add(cmdSave);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 601, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                    .addGap(26, 26, 26)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        Application.showForm(new FormNhanVien());
    }//GEN-LAST:event_cmdSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JButton cmdSave;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.datetime.component.date.DatePicker datePicker1;
    private raven.datetime.component.date.DatePicker datePicker2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHo;
    private javax.swing.JTextField txtMa;
    private javax.swing.JFormattedTextField txtNgayLam;
    private javax.swing.JFormattedTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables

}
