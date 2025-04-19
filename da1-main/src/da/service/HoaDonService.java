/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da.service;
import da.model.HoaDon;
import java.sql.*;
import da.util.connectDB;
import java.lang.System.Logger;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 *
 * @author ADMIN
 */
public class HoaDonService {
    private Connection conn;

    public HoaDonService() {
        conn = connectDB.getConnection();
    }

    /**
     * Adds a new invoice to the HoaDon table, calculates total from GioHang, and clears the cart.
     * @param hoaDon The invoice to add
     * @return true if successful, false otherwise
     */
    public boolean addHoaDon(HoaDon hoaDon) {
        // Input validation
    String sqlTongTien = "SELECT SUM(tongTien) FROM GioHang WHERE idNguoiDung = ?";
    String sqlInsert = "INSERT INTO HoaDon (maHoaDon, tenKhachHang, tongTien, trangThai, ngayTao) VALUES (?, ?, ?, ?, GETDATE())";
    String sqlDelete = "DELETE FROM GioHang WHERE idNguoiDung = ?";

    try  {
        conn.setAutoCommit(false); // Bắt đầu transaction

        // 1. Tính tổng tiền từ giỏ hàng
        BigDecimal tongTien = BigDecimal.ZERO;
        try (PreparedStatement ps = conn.prepareStatement(sqlTongTien)) {
            ps.setInt(1, hoaDon.getIdNguoiDung());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tongTien = rs.getBigDecimal(1);
                if (tongTien == null) tongTien = BigDecimal.ZERO;
            }
        }

        // 2. Thêm hóa đơn mới
        try (PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
            ps.setString(1, hoaDon.getMaHoaDon());
            ps.setString(2, hoaDon.getTenKhachHang());
            ps.setBigDecimal(3, tongTien);
            ps.setString(4, hoaDon.getTrangThai()); // "Chưa thanh toán" hoặc "Đã thanh toán"
            ps.executeUpdate();
        }

        // 3. Xóa giỏ hàng của người dùng
        try (PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
            ps.setInt(1, hoaDon.getIdNguoiDung());
            ps.executeUpdate();
        }

        conn.commit(); // Hoàn tất giao dịch
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    /**
     * Retrieves all invoices from the HoaDon table.
     * @return List of HoaDon objects
     */
    public List<HoaDon> getListHoaDon() {
        List<HoaDon> hoaDonList = new ArrayList<>();
        String sql = "SELECT id, maHoaDon, tenKhachHang, tongTien, trangThai, ngayTao FROM HoaDon";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(rs.getInt("id"));
                hoaDon.setMaHoaDon(rs.getString("maHoaDon"));
                hoaDon.setTenKhachHang(rs.getString("tenKhachHang"));
                hoaDon.setTongTien(rs.getBigDecimal("tongTien"));
                hoaDon.setTrangThai(rs.getString("trangThai"));
                hoaDon.setNgayTao(rs.getTimestamp("ngayTao"));
                hoaDonList.add(hoaDon);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching HoaDon: " + e.getMessage());
            e.printStackTrace();
        }

        return hoaDonList;
    }
    public ArrayList<HoaDon> searchHoaDon(String keyword) {
        ArrayList<HoaDon> result = new ArrayList<>();
        String query = "SELECT id, maHoaDon, tenKhachHang, ngayTao, tongTien FROM HoaDon WHERE maHoaDon LIKE ? OR tenKhachHang LIKE ?";
        
        try (
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String maHoaDon = rs.getString("maHoaDon");
                String tenKhachHang = rs.getString("tenKhachHang");
                Date ngayTao = rs.getDate("ngayTao");
                BigDecimal tongTien = rs.getBigDecimal("tongTien");
                result.add(new HoaDon());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
