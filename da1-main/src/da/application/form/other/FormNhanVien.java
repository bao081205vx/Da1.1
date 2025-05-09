
package da.application.form.other;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import da.application.Application;
import da.model.NhanVien;
import da.service.NhanVienService;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import raven.toast.Notifications;


public class FormNhanVien extends javax.swing.JPanel {
    NhanVienService service = new NhanVienService();

   
    public FormNhanVien() {
        initComponents();
        applyTableStyle(tblNhanVien);
        loadNhanVienData(service.searchNhanVien(""));
    }


    private void applyTableStyle(JTable table) {
        cmdAdd.setIcon(new FlatSVGIcon("da/icon/svg/add.svg", 0.35f));
        cmdUpdate.setIcon(new FlatSVGIcon("da/icon/svg/edit.svg", 0.35f));
        cmdDelete.setIcon(new FlatSVGIcon("da/icon/svg/delete.svg", 0.35f));
        cmdExcel.setIcon(new FlatSVGIcon("da/icon/svg/print.svg", 0.35f));
        cmdDetails.setIcon(new FlatSVGIcon("da/icon/svg/details.svg", 0.35f));
        cmdSearch.setIcon(new FlatSVGIcon("da/icon/svg/search.svg", 0.35f));
        
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
                // Căn chỉnh cột
                    switch (column) {
                        case 0, 2, 3 -> label.setHorizontalAlignment(SwingConstants.CENTER);
                        case 1 -> label.setHorizontalAlignment(SwingConstants.TRAILING);
                        default -> label.setHorizontalAlignment(SwingConstants.LEADING);
                    }
                    // Nếu không phải header
                    if (!header) {
                        if (column == 4 && value instanceof Number) {
                            double numericValue = ((Number) value).doubleValue();
                            if (numericValue > 0) {
                                label.setForeground(new Color(17, 182, 60));
                                label.setText("+" + numericValue);
                            } else {
                                label.setForeground(new Color(202, 48, 48));
                            }
                        } else {
                        // Xử lý màu sắc khi chọn hàng
                            if (isSelected) {
                                label.setForeground(table.getSelectionForeground());
                            } else {
                                label.setForeground(table.getForeground());
                            }
                        }
                    }
                }
                return com;
            }
        };
    }
    
    public void loadNhanVienData(ArrayList<NhanVien> list) {
        DefaultTableModel tblModel = (DefaultTableModel) tblNhanVien.getModel();
        tblModel.setRowCount(0);
        int index = 1; // Bắt đầu từ 1 (hoặc 0 tùy theo yêu cầu)
        for (NhanVien nhanVien : list) {
            Object[] thongTinNhanVien = {
                index++,// Chỉ mục (Index) thay vì ID
                nhanVien.getId(),
                nhanVien.getMaNV(),
                nhanVien.getHo() + " " + nhanVien.getTen(),
                nhanVien.isGioiTinh()?"Nam":"Nữ",
                nhanVien.getNgaySinh(),
                nhanVien.getSdt(),
                nhanVien.getEmail()
            };
            tblModel.addRow(thongTinNhanVien);
        }
    }
    
    public void searchNV(){
        String keyword = txtSearch.getText().trim();
        loadNhanVienData(service.searchNhanVien(keyword));
    }
    
    public void update() {
        int selectedRow = tblNhanVien.getSelectedRow();
        if (selectedRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui Lòng chọn nhân viên!");
            return;
        }

        // Lấy ID nhân viên từ cột thứ 2 (cột có chỉ số 1)
        Object idObj = tblNhanVien.getValueAt(selectedRow, 1); // Lấy ID từ cột thứ 2 (cột có chỉ số 1)

        if (idObj == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "ID nhân viên không hợp lệ!");
            return;
        }

        int id = 0;
        try {
            id = Integer.parseInt(idObj.toString()); // Chuyển đổi giá trị sang kiểu Integer
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "ID nhân viên không hợp lệ!");
            return;
        }

        // Lấy đối tượng NhanVien từ cơ sở dữ liệu hoặc danh sách nhân viên (giả sử có một phương thức tìm nhân viên theo ID)
        NhanVien nhanVien = service.getNhanVienById(id); // Phương thức lấy nhân viên theo ID từ service hoặc từ database

        // Kiểm tra nếu không có nhân viên tương ứng với ID
        if (nhanVien == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Nhân viên không tồn tại!");
            return;
        }

        // Chuyển sang form UpdateNhanVien và truyền thông tin nhân viên
        Application.showForm(new UpdateNhanVien(nhanVien));
    }
    
    public void chiTiet() {
        int selectedRow = tblNhanVien.getSelectedRow();
        if (selectedRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui Lòng chọn nhân viên!");
            return;
        }

        // Lấy ID nhân viên từ cột thứ 2 (cột có chỉ số 1)
        Object idObj = tblNhanVien.getValueAt(selectedRow, 1); // Lấy ID từ cột thứ 2 (cột có chỉ số 1)

        if (idObj == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "ID nhân viên không hợp lệ!");
            return;
        }

        int id = 0;
        try {
            id = Integer.parseInt(idObj.toString()); // Chuyển đổi giá trị sang kiểu Integer
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "ID nhân viên không hợp lệ!");
            return;
        }

        // Lấy đối tượng NhanVien từ cơ sở dữ liệu hoặc danh sách nhân viên (giả sử có một phương thức tìm nhân viên theo ID)
        NhanVien nhanVien = service.getNhanVienById(id); // Phương thức lấy nhân viên theo ID từ service hoặc từ database

        // Kiểm tra nếu không có nhân viên tương ứng với ID
        if (nhanVien == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Nhân viên không tồn tại!");
            return;
        }

        // Chuyển sang form UpdateNhanVien và truyền thông tin nhân viên
        Application.showForm(new ChiTietNhanVien(nhanVien));
    }
    
    public void deleteNhanVien() {
        int selectedRow = tblNhanVien.getSelectedRow();
        if (selectedRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "Vui Lòng chọn nhân viên cần xóa!");
            return;
        }

        // Lấy ID nhân viên từ cột thứ 2 (cột có chỉ số 1)
        Object idObj = tblNhanVien.getValueAt(selectedRow, 1); // Lấy ID từ cột thứ 2 (cột có chỉ số 1)

        if (idObj == null) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "ID nhân viên không hợp lệ!");
            return;
        }

        int id = 0;
        try {
            id = Integer.parseInt(idObj.toString()); // Chuyển đổi giá trị sang kiểu Integer
        } catch (NumberFormatException e) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "ID nhân viên không hợp lệ!");
            return;
        }

        // Xác nhận trước khi xóa
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận", javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            boolean isDeleted = service.deleteNhanVien(id); // Gọi phương thức xóa nhân viên từ service

            if (isDeleted) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xóa nhân viên thành công!");
                loadNhanVienData(service.searchNhanVien("")); // Load lại danh sách nhân viên
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Xóa nhân viên thất bại!");
            }
        }
    }
    
    private void exportNhanVienToExcelFromDB() {
        JFileChooser jFileChooser = new JFileChooser();
        if (jFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File saveFile = new File(jFileChooser.getSelectedFile().getAbsolutePath() + ".xlsx");

            ArrayList<NhanVien> nhanVienList = service.getAllNhanVien();

            try (Workbook wb = new XSSFWorkbook(); FileOutputStream out = new FileOutputStream(saveFile)) {
                Sheet sheet = wb.createSheet("Danh Sách Nhân Viên");

                // Column headers
                String[] headers = {"STT", "ID", "ID Vai Trò", "Mã Nhân Viên", "Họ", "Tên", "Email", "Địa Chỉ", 
                                    "Chức Vụ", "Ngày Vào Làm", "Giới Tính", "Ngày Sinh", "SĐT", "Trạng Thái"};
                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < headers.length; i++) {
                    rowCol.createCell(i).setCellValue(headers[i]);
                }

                // Data rows
                int rowIndex = 1;
                for (NhanVien nv : nhanVienList) {
                    Row row = sheet.createRow(rowIndex++);
                    row.createCell(0).setCellValue(rowIndex - 1); // STT
                    row.createCell(1).setCellValue(nv.getId());
                    row.createCell(2).setCellValue(nv.getIdVaiTro());
                    row.createCell(3).setCellValue(nv.getMaNV());
                    row.createCell(4).setCellValue(nv.getHo());
                    row.createCell(5).setCellValue(nv.getTen());
                    row.createCell(6).setCellValue(nv.getEmail());
                    row.createCell(7).setCellValue(nv.getDiaChi());
                    row.createCell(8).setCellValue(nv.getChucVu());
                    row.createCell(9).setCellValue(nv.getNgayVaoLam());
                    row.createCell(10).setCellValue(nv.isGioiTinh() ? "Nam" : "Nữ");
                    row.createCell(11).setCellValue(nv.getNgaySinh());
                    row.createCell(12).setCellValue(nv.getSdt());
                    row.createCell(13).setCellValue(nv.getTrangThai());
                }

                wb.write(out);
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "Xuất file thành công!");
            } catch (IOException e) {
                e.printStackTrace();
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Lỗi khi xuất file!");
            }
        }
    }
    



    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        txtSearch = new javax.swing.JTextField();
        cmdSearch = new javax.swing.JButton();
        cmdAdd = new javax.swing.JButton();
        cmdUpdate = new javax.swing.JButton();
        cmdDelete = new javax.swing.JButton();
        cmdDetails = new javax.swing.JButton();
        cmdExcel = new javax.swing.JButton();
        cmdAddAcount = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();

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

        cmdAdd.setText("Add new");
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        crazyPanel2.add(cmdAdd);

        cmdUpdate.setText("Update");
        cmdUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUpdateActionPerformed(evt);
            }
        });
        crazyPanel2.add(cmdUpdate);

        cmdDelete.setText("Delete");
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });
        crazyPanel2.add(cmdDelete);

        cmdDetails.setText("Chi tiết");
        cmdDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDetailsActionPerformed(evt);
            }
        });
        crazyPanel2.add(cmdDetails);

        cmdExcel.setText("Excel");
        cmdExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdExcelActionPerformed(evt);
            }
        });
        crazyPanel2.add(cmdExcel);

        cmdAddAcount.setText("Thêm Tài Khoản Nhân Viên");
        crazyPanel2.add(cmdAddAcount);

        crazyPanel1.add(crazyPanel2);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "ID", "Mã", "Họ Tên", "Giới tính", "Ngày Sinh", "SĐT", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        crazyPanel1.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1106, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSearchActionPerformed
        searchNV();
    }//GEN-LAST:event_cmdSearchActionPerformed

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        Application.showForm(new AddNhanVien());
    }//GEN-LAST:event_cmdAddActionPerformed

    private void cmdUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUpdateActionPerformed
        update();
    }//GEN-LAST:event_cmdUpdateActionPerformed

    private void cmdDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDetailsActionPerformed
        chiTiet();
    }//GEN-LAST:event_cmdDetailsActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
        deleteNhanVien();
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void cmdExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdExcelActionPerformed
        exportNhanVienToExcelFromDB();
    }//GEN-LAST:event_cmdExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdAdd;
    private javax.swing.JButton cmdAddAcount;
    private javax.swing.JButton cmdDelete;
    private javax.swing.JButton cmdDetails;
    private javax.swing.JButton cmdExcel;
    private javax.swing.JButton cmdSearch;
    private javax.swing.JButton cmdUpdate;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
