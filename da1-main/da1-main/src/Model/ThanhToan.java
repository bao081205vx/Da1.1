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
public class ThanhToan {
    private int id;
    private String phuongthuc;
    private String magiaodich;
    private BigDecimal sotien;
    private String trangthai;
    private LocalDateTime ngaytao;
    private int idhoadon;

    public ThanhToan() {
    }

    public ThanhToan(int id, String phuongthuc, String magiaodich, BigDecimal sotien, String trangthai, LocalDateTime ngaytao, int idhoadon) {
        this.id = id;
        this.phuongthuc = phuongthuc;
        this.magiaodich = magiaodich;
        this.sotien = sotien;
        this.trangthai = trangthai;
        this.ngaytao = ngaytao;
        this.idhoadon = idhoadon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhuongthuc() {
        return phuongthuc;
    }

    public void setPhuongthuc(String phuongthuc) {
        this.phuongthuc = phuongthuc;
    }

    public String getMagiaodich() {
        return magiaodich;
    }

    public void setMagiaodich(String magiaodich) {
        this.magiaodich = magiaodich;
    }

    public BigDecimal getSotien() {
        return sotien;
    }

    public void setSotien(BigDecimal sotien) {
        this.sotien = sotien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public LocalDateTime getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(LocalDateTime ngaytao) {
        this.ngaytao = ngaytao;
    }

    public int getIdhoadon() {
        return idhoadon;
    }

    public void setIdhoadon(int idhoadon) {
        this.idhoadon = idhoadon;
    }

    
    
}
