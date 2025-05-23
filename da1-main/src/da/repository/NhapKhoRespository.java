/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da.repository;
import da.model.NhapKho;
import java.sql.*;
import da.util.connectDB;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class NhapKhoRespository {
    private Connection conn;

    public NhapKhoRespository() {
        conn = connectDB.getConnection();
    }
    public List<NhapKho> getListNhapKho(){
    List<NhapKho> list = new ArrayList<>();
    String select = "SELECT " +
        "nk.id, " +
        "nk.maNhap, " +
        "ncc.tenNCC AS tenNhaCungCap, " +
        "nv.ho + ' ' + nv.ten AS tenNhanVien, " +
        "nk.ngayNhap, " +
        "nk.tongTien, " +
        "sp.tensp AS tenSanPham, " +
        "nk.soLuong, " +
        "kvk.tenKhuVuc AS tenKhuVucKho " +
        "FROM NhapKho nk " +
        "JOIN NhaCungCap ncc ON nk.idNhaCungCap = ncc.id " +
        "JOIN NhanVien nv ON nk.idNhanVien = nv.id " +
        "JOIN SanPham sp ON nk.idSanPham = sp.id " +
        "JOIN KhuVucKho kvk ON nk.idKhuVucKho = kvk.id";

    try {
        PreparedStatement ps = conn.prepareStatement(select);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            NhapKho nk = new NhapKho();
            nk.setId(rs.getInt("id"));
            nk.setManhap(rs.getString("maNhap"));
            nk.setNhacungcap(rs.getString("tenNhaCungCap"));
            nk.setNhanvien(rs.getString("tenNhanVien"));
            nk.setSanPham(rs.getString("tenSanPham"));
            nk.setSoLuong(rs.getInt("soLuong"));
            nk.setKhuvuckho(rs.getString("tenKhuVucKho"));
            nk.setNgaynhap(rs.getTimestamp("ngayNhap"));
            nk.setTongtien(rs.getDouble("tongTien"));
            list.add(nk);
        }
    } catch(Exception e){
        System.out.println("Lỗi khi truy vấn NhapKho: " + e.getMessage());
    }

    return list;
}

    public boolean addNhapKho(NhapKho nhapKho) {
    String insert = "INSERT INTO NhapKho (maNhap, idNhaCungCap, idNhanVien, ngayNhap, tongTien, idSanPham, idKhuVucKho, soLuong) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        conn.setAutoCommit(false);
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setString(1, nhapKho.getManhap());
        ps.setInt(2, nhapKho.getIdNhaCungCap());
        ps.setInt(3, nhapKho.getIdNhanVien());
        ps.setTimestamp(4, nhapKho.getNgaynhap());
        ps.setDouble(5, nhapKho.getTongtien());
        ps.setInt(6, nhapKho.getIdsanpham());
        ps.setInt(7, nhapKho.getIdkhuvuc());
        ps.setInt(8, nhapKho.getSoLuong());
        int rowsAffected = ps.executeUpdate();
        conn.commit();
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Error adding NhapKho: " + e.getMessage());
        try {
            conn.rollback();
        } catch (SQLException rollbackEx) {
            System.out.println("Rollback failed: " + rollbackEx.getMessage());
        }
        return false;
    } finally {
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Failed to reset AutoCommit: " + e.getMessage());
        }
    }
}
    public boolean updateNhapKho(int id, int idNhaCungCap, int idNhanVien) {
    String update = "UPDATE NhapKho SET idNhaCungCap = ?, idNhanVien = ? WHERE id = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(update);
        ps.setInt(1, idNhaCungCap);
        ps.setInt(2, idNhanVien);
        ps.setInt(3, id);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Error updating NhapKho: " + e.getMessage());
        return false;
    }
}
    public boolean deleteNhapKho(int id) {
    String delete = "DELETE FROM NhapKho WHERE id = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(delete);
        ps.setInt(1, id);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        System.out.println("Error deleting NhapKho: " + e.getMessage());
        return false;
    }
}
}
