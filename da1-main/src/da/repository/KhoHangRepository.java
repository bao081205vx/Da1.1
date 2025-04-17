/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da.repository;

import da.model.KhuVucKho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import da.model.KhoHang;
import da.util.connectDB;
import da.model.SanPham;

public class KhoHangRepository {
    private Connection conn;

    public KhoHangRepository() {
        conn = connectDB.getConnection();
    }

    // Thêm khu vực kho
    public boolean addKhuVucKho(KhuVucKho khuVucKho) {
        String sql = "INSERT INTO KhuVucKho (tenKhuVuc, moTa) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, khuVucKho.getTenKhuVuc());
            stmt.setString(2, khuVucKho.getMoTa());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật khu vực kho
    public boolean updateKhuVucKho(KhuVucKho khuVucKho) {
        String sql = "UPDATE KhuVucKho SET tenKhuVuc = ?, moTa = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, khuVucKho.getTenKhuVuc());
            stmt.setString(2, khuVucKho.getMoTa());
            stmt.setInt(3, khuVucKho.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa khu vực kho
    public boolean deleteKhuVucKho(int id) {
        String sql = "DELETE FROM KhuVucKho WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<KhuVucKho> searchKhuVucKhoByName(String keyword) {
        ArrayList<KhuVucKho> list = new ArrayList<>();
        String sql = "SELECT * FROM KhuVucKho WHERE tenKhuVuc LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                KhuVucKho kvk = new KhuVucKho();
                kvk.setId(rs.getInt("id"));
                kvk.setTenKhuVuc(rs.getString("tenKhuVuc"));
                kvk.setMoTa(rs.getString("moTa"));
                list.add(kvk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy tất cả khu vực kho
    public ArrayList<KhuVucKho> getAllKhuVucKho() {
        ArrayList<KhuVucKho> list = new ArrayList<>();
        String sql = "SELECT * FROM KhuVucKho";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                KhuVucKho kvk = new KhuVucKho();
                kvk.setId(rs.getInt("id"));
                kvk.setTenKhuVuc(rs.getString("tenKhuVuc"));
                kvk.setMoTa(rs.getString("moTa"));
                list.add(kvk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhoHang> getProductsByKhuVucKho(int idKhuVucKho) {
    List<KhoHang> list = new ArrayList<>();
    String sql = "SELECT kh.id, kh.idSanPham, kh.idKhuVucKho, kh.ngayNhap, sp.soluongton, sp.tenSP, sp.hinhAnh " +
                 "FROM KhoHang kh " +
                 "JOIN SanPham sp ON kh.idSanPham = sp.id " +
                 "WHERE kh.idKhuVucKho = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idKhuVucKho);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            KhoHang khoHang = new KhoHang();
            khoHang.setId(rs.getInt("id"));
            khoHang.setIdSanPham(rs.getInt("idSanPham"));
            khoHang.setIdKhuVucKho(rs.getInt("idKhuVucKho"));
            khoHang.setSoLuongTon(rs.getInt("soLuongTon"));
            khoHang.setNgayNhap(rs.getTimestamp("ngayNhap"));
            khoHang.setTenSP(rs.getString("tenSP"));
            khoHang.setHinhAnh(rs.getString("hinhAnh"));
            list.add(khoHang);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
    public boolean addProductToKhoHang(KhoHang khoHang) {
    KhoHang existing = findKhoHangBySanPhamAndKhuVuc(khoHang.getIdSanPham(), khoHang.getIdKhuVucKho());
    if (existing != null) {
        existing.setSoLuongTon(existing.getSoLuongTon() + khoHang.getSoLuongTon());
        return updateKhoHang(existing);
    } else {
        return insertKhoHang(khoHang);
    }
}

private KhoHang findKhoHangBySanPhamAndKhuVuc(int idSanPham, int idKhuVucKho) {
    String sql = "SELECT * FROM KhoHang WHERE idSanPham = ? AND idKhuVucKho = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, idSanPham);
        stmt.setInt(2, idKhuVucKho);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            KhoHang khoHang = new KhoHang();
            khoHang.setId(rs.getInt("id"));
            khoHang.setIdSanPham(rs.getInt("idSanPham"));
            khoHang.setIdKhuVucKho(rs.getInt("idKhuVucKho"));
            khoHang.setSoLuongTon(rs.getInt("soLuongTon"));
            khoHang.setNgayNhap(rs.getTimestamp("ngayNhap"));
            return khoHang;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

private boolean updateKhoHang(KhoHang khoHang) {
    String sql = "UPDATE KhoHang SET soLuongTon = ?, ngayNhap = ? WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, khoHang.getSoLuongTon());
        stmt.setTimestamp(2, khoHang.getNgayNhap() != null ? khoHang.getNgayNhap() : new Timestamp(System.currentTimeMillis()));
        stmt.setInt(3, khoHang.getId());
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

private boolean insertKhoHang(KhoHang khoHang) {
    String sql = "INSERT INTO KhoHang (idSanPham, idKhuVucKho, soLuongTon, ngayNhap) VALUES (?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, khoHang.getIdSanPham());
        stmt.setInt(2, khoHang.getIdKhuVucKho());
        stmt.setInt(3, khoHang.getSoLuongTon());
        stmt.setTimestamp(4, khoHang.getNgayNhap() != null ? khoHang.getNgayNhap() : new Timestamp(System.currentTimeMillis()));
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}