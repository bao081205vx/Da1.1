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
public class HoaDon {
    private int id;
    private BigDecimal tongtien;
    private String trangthai;
    private String phuongthuc;
    private LocalDateTime ngaytao;
    private int idnhanvien;

    public HoaDon() {
    }

    public HoaDon(int id, BigDecimal tongtien, String trangthai, String phuongthuc, LocalDateTime ngaytao, int idnhanvien) {
        this.id = id;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
        this.phuongthuc = phuongthuc;
        this.ngaytao = ngaytao;
        this.idnhanvien = idnhanvien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTongtien() {
        return tongtien;
    }

    public void setTongtien(BigDecimal tongtien) {
        this.tongtien = tongtien;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getPhuongthuc() {
        return phuongthuc;
    }

    public void setPhuongthuc(String phuongthuc) {
        this.phuongthuc = phuongthuc;
    }

    public LocalDateTime getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(LocalDateTime ngaytao) {
        this.ngaytao = ngaytao;
    }

    public int getIdnhanvien() {
        return idnhanvien;
    }

    public void setIdnhanvien(int idnhanvien) {
        this.idnhanvien = idnhanvien;
    }
    
}
