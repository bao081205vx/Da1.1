package da.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import da.model.HoaDon;
import da.model.NhanVien;
import da.service.HoaDonService;
import da.service.NhanVienService;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import raven.datetime.component.date.DatePicker;
import raven.modal.component.ModalBorderAction;
import raven.modal.component.SimpleModalBorder;

public class PaymentForm extends JPanel {
    private NhanVienService nhanVienService = new NhanVienService();
    private HoaDonService hoaDonService = new HoaDonService();
    private JComboBox cboNhanVien;
    private HoaDonForm hoaDonForm;

    public PaymentForm(HoaDonForm hoaDonForm) {
        this.hoaDonForm = hoaDonForm;
        try {
            System.out.println("Starting PaymentForm.init()");
            init();
            System.out.println("Completed PaymentForm.init()");
            System.out.println("Starting PaymentForm.initNV()");
            initNV();
            System.out.println("Completed PaymentForm.initNV()");
        } catch (Exception e) {
            System.err.println("Error in PaymentForm constructor: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public void initNV() {
        try {
            DefaultComboBoxModel<String> boxModel = new DefaultComboBoxModel<>();
            boxModel.addElement("-- Chọn nhân viên --");
            ArrayList<NhanVien> NVSet = nhanVienService.getAllNhanVien();
            System.out.println("NhanVien list size: " + (NVSet != null ? NVSet.size() : "null"));
            for (NhanVien nv : NVSet) {
                if (nv != null) {
                    boxModel.addElement(nv.getHo() + " " + nv.getTen());
                }
            }
            cboNhanVien.setModel(boxModel);
        } catch (Exception e) {
            System.err.println("Error in initNV: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    private void init() {
        setLayout(new MigLayout("wrap 2,fillx,insets n 35 n 35", "[fill,200]"));

        JLabel lbContactDetail = new JLabel("Thông tin hóa đơn");
        lbContactDetail.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +2;");
        add(lbContactDetail, "gapy 10 10,span 2");

        add(new JLabel("Hình thức thanh toán"), "span 2");
        JComboBox comboPaymentType = new JComboBox();
        comboPaymentType.addItem("Chuyển khoản ngân hàng");
        comboPaymentType.addItem("Tiền mặt");
        comboPaymentType.addItem("Thanh toán di động");
        comboPaymentType.addItem("Cổng thanh toán trực tuyến");
        comboPaymentType.addItem("Thẻ tín dụng/ghi nợ");
        add(comboPaymentType, "gapy n 5,span 2");

        add(new JLabel("Mã Hóa Đơn"));
        add(new JLabel("Tên Khách Hàng"));

        JTextField txtName = new JTextField();
        JTextField txtEmail = new JTextField();
        txtName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mã hóa đơn");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "tên khách hàng");
        add(txtName);
        add(txtEmail);

        JLabel lbRequestDetail = new JLabel("Yêu cầu chi tiết");
        lbRequestDetail.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +2;");
        add(lbRequestDetail, "gapy 10 10,span 2");

        add(new JLabel("Ngày"));

        JFormattedTextField dateEditor = new JFormattedTextField();
        DatePicker datePicker = new DatePicker();
        try {
            datePicker.setEditor(dateEditor);
            datePicker.setDateSelectionAble((date) -> true);
            datePicker.setSelectedDate(LocalDate.now());
        } catch (Exception e) {
            System.err.println("Error initializing DatePicker: " + e.getMessage());
            e.printStackTrace();
        }
        add(dateEditor, "wrap");

        add(new JLabel("Nhân viên bán hàng"), "gapy 5,span 2");
        cboNhanVien = new JComboBox();
        add(cboNhanVien, "Span 2");

        add(new JLabel("Trạng thái"), "gapy 5,span 2");
        JComboBox comboAccount = new JComboBox();
        comboAccount.addItem("Chưa thanh toán");
        comboAccount.addItem("Đã thanh toán");
        add(comboAccount, "Span 2");

        add(new JLabel("Ghi chú"), "gapy 5,span 2");
        JTextArea textArea = new JTextArea(3, 20);
        textArea.setText("Cảm ơn quý khách đã chọn mua hàng của chúng tôi!");
        textArea.setEnabled(false);
        textArea.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:1,1,1,1;" +
                "font:-1;" +
                "background:null;");
        add(textArea, "gapy 5 5,span 2");

        JButton cmdCancel = new JButton("Cancel");
        JButton cmdPayment = new JButton("Thêm Hóa Đơn") {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };

        cmdCancel.addActionListener(actionEvent -> {
            ModalBorderAction modalAction = ModalBorderAction.getModalBorderAction(this);
            if (modalAction != null) {
                modalAction.doAction(SimpleModalBorder.CANCEL_OPTION);
            } else {
                // Fallback: Find and close the parent dialog
                JDialog dialog = findParentDialog();
                if (dialog != null) {
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Hủy bỏ thao tác.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        cmdPayment.addActionListener(actionEvent -> {
            String maHoaDon = txtName.getText().trim();
            String tenKhachHang = txtEmail.getText().trim();
            String trangThai = comboAccount.getSelectedItem().toString();
            LocalDate ngayTao = datePicker.getSelectedDate();
            int idNguoiDung = 0;
            String nhanVienName = cboNhanVien.getSelectedItem() != null ? cboNhanVien.getSelectedItem().toString() : "";

            if (maHoaDon.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã Hóa Đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (tenKhachHang.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Tên Khách Hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (nhanVienName.equals("-- Chọn nhân viên --")) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn Nhân viên bán hàng", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (ngayTao == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn Ngày", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ArrayList<NhanVien> nhanViens = nhanVienService.getAllNhanVien();
            for (NhanVien nv : nhanViens) {
                String fullName = nv.getHo() + " " + nv.getTen();
                if (fullName.equals(nhanVienName)) {
                    idNguoiDung = nv.getId();
                    break;
                }
            }
            if (idNguoiDung == 0) {
                JOptionPane.showMessageDialog(this, "Nhân viên không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHoaDon(maHoaDon);
            hoaDon.setTenKhachHang(tenKhachHang);
            hoaDon.setTrangThai(trangThai);
            hoaDon.setIdNguoiDung(idNguoiDung);
            hoaDon.setNgayTao(Timestamp.valueOf(ngayTao.atStartOfDay()));

            boolean success = hoaDonService.addHoaDon(hoaDon);
            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm Hóa Đơn thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                ModalBorderAction modalAction = ModalBorderAction.getModalBorderAction(this);
                if (modalAction != null) {
                    modalAction.doAction(SimpleModalBorder.OK_OPTION);
                } else {
                    // Fallback: Close the parent dialog
                    JDialog dialog = findParentDialog();
                    if (dialog != null) {
                        dialog.dispose();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Thêm Hóa Đơn thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(cmdCancel, "grow 0");
        add(cmdPayment, "grow 0, al trailing");
    }

    private JDialog findParentDialog() {
        java.awt.Container parent = this;
        while (parent != null && !(parent instanceof JDialog)) {
            parent = parent.getParent();
        }
        return (JDialog) parent;
    }
}