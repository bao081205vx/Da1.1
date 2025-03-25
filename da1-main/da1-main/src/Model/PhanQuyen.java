/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class PhanQuyen {
    private int id;
    private String vaitro;
    private boolean xemsanpham;
    private boolean taohoadon;
    private boolean xemhoadon;
    private boolean quanlynhanvien;
    private boolean quanlysanpham;

    public PhanQuyen() {
    }

    public PhanQuyen(int id, String vaitro, boolean xemsanpham, boolean taohoadon, boolean xemhoadon, boolean quanlynhanvien, boolean quanlysanpham) {
        this.id = id;
        this.vaitro = vaitro;
        this.xemsanpham = xemsanpham;
        this.taohoadon = taohoadon;
        this.xemhoadon = xemhoadon;
        this.quanlynhanvien = quanlynhanvien;
        this.quanlysanpham = quanlysanpham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVaitro() {
        return vaitro;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }

    public boolean isXemsanpham() {
        return xemsanpham;
    }

    public void setXemsanpham(boolean xemsanpham) {
        this.xemsanpham = xemsanpham;
    }

    public boolean isTaohoadon() {
        return taohoadon;
    }

    public void setTaohoadon(boolean taohoadon) {
        this.taohoadon = taohoadon;
    }

    public boolean isXemhoadon() {
        return xemhoadon;
    }

    public void setXemhoadon(boolean xemhoadon) {
        this.xemhoadon = xemhoadon;
    }

    public boolean isQuanlynhanvien() {
        return quanlynhanvien;
    }

    public void setQuanlynhanvien(boolean quanlynhanvien) {
        this.quanlynhanvien = quanlynhanvien;
    }

    public boolean isQuanlysanpham() {
        return quanlysanpham;
    }

    public void setQuanlysanpham(boolean quanlysanpham) {
        this.quanlysanpham = quanlysanpham;
    }
    
}
