/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da.model;

import java.math.BigDecimal;
import java.sql.*;
/**
 *
 * @author ADMIN
 */
public class HoaDon {
    private int id;
    private String maHoaDon;
    private int idNguoiDung;
    private String tenKhachHang;
    private BigDecimal tongTien;
    private String trangThai;
    private Timestamp ngayTao;

    public HoaDon() {
    }

    public HoaDon(int id, String maHoaDon, int idNguoiDung, String tenKhachHang, BigDecimal tongTien, String trangThai, Timestamp ngayTao) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.idNguoiDung = idNguoiDung;
        this.tenKhachHang = tenKhachHang;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }

    
}
