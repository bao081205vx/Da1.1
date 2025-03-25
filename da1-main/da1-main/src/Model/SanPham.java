/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author ADMIN
 */
public class SanPham {
    private int id;
    private String ten;
    private String mota;
    private BigDecimal gia;
    private int soluongton;
    private String hinhanh;
    private boolean trangthai;
    private LocalDateTime ngaytao;

    public SanPham() {
    }

    public SanPham(int id, String ten, String mota, BigDecimal gia, int soluongton, String hinhanh, boolean trangthai, LocalDateTime ngaytao) {
        this.id = id;
        this.ten = ten;
        this.mota = mota;
        this.gia = gia;
        this.soluongton = soluongton;
        this.hinhanh = hinhanh;
        this.trangthai = trangthai;
        this.ngaytao = ngaytao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    public LocalDateTime getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(LocalDateTime ngaytao) {
        this.ngaytao = ngaytao;
    }
    
}
