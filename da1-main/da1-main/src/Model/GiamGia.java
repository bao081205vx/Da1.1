/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author ADMIN
 */
public class GiamGia {
    private int id;
    private int idsanpham;
    private int phantramgiam;
    private LocalDateTime ngaybatdau;
    private LocalDateTime ngayketthuc;
    private LocalDateTime ngaytao;

    public GiamGia() {
    }

    public GiamGia(int id, int idsanpham, int phantramgiam, LocalDateTime ngaybatdau, LocalDateTime ngayketthuc, LocalDateTime ngaytao) {
        this.id = id;
        this.idsanpham = idsanpham;
        this.phantramgiam = phantramgiam;
        this.ngaybatdau = ngaybatdau;
        this.ngayketthuc = ngayketthuc;
        this.ngaytao = ngaytao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdsanpham() {
        return idsanpham;
    }

    public void setIdsanpham(int idsanpham) {
        this.idsanpham = idsanpham;
    }

    public int getPhantramgiam() {
        return phantramgiam;
    }

    public void setPhantramgiam(int phantramgiam) {
        this.phantramgiam = phantramgiam;
    }

    public LocalDateTime getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(LocalDateTime ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public LocalDateTime getNgayketthuc() {
        return ngayketthuc;
    }

    public void setNgayketthuc(LocalDateTime ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }

    public LocalDateTime getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(LocalDateTime ngaytao) {
        this.ngaytao = ngaytao;
    }
    
}
