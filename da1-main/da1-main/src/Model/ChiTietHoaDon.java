/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;

/**
 *
 * @author ADMIN
 */
public class ChiTietHoaDon {
    private int idhoadon;
    private int idsanpham;
    private int soluong;
    private BigDecimal gia;
    private int idgiamgia;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int idhoadon, int idsanpham, int soluong, BigDecimal gia, int idgiamgia) {
        this.idhoadon = idhoadon;
        this.idsanpham = idsanpham;
        this.soluong = soluong;
        this.gia = gia;
        this.idgiamgia = idgiamgia;
    }

    public int getIdhoadon() {
        return idhoadon;
    }

    public void setIdhoadon(int idhoadon) {
        this.idhoadon = idhoadon;
    }

    public int getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public int getIdgiamgia() {
        return idgiamgia;
    }

    public void setIdgiamgia(int idgiamgia) {
        this.idgiamgia = idgiamgia;
    }
    
}
