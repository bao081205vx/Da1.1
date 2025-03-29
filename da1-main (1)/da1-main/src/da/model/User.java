package da.model;

public class User {
    private String tenDangNhap;
    private String hoTen;
    private String vaiTro;
    private boolean xemSanPham;
    private boolean taoHoaDon;
    private boolean xemHoaDon;
    private boolean quanLyNhanVien;
    private boolean quanLySanPham;

    public User(String tenDangNhap, String hoTen, String vaiTro, boolean xemSanPham, 
                boolean taoHoaDon, boolean xemHoaDon, boolean quanLyNhanVien, boolean quanLySanPham) {
        this.tenDangNhap = tenDangNhap;
        this.hoTen = hoTen;
        this.vaiTro = vaiTro;
        this.xemSanPham = xemSanPham;
        this.taoHoaDon = taoHoaDon;
        this.xemHoaDon = xemHoaDon;
        this.quanLyNhanVien = quanLyNhanVien;
        this.quanLySanPham = quanLySanPham;
    }

    // Getter methods
    public String getTenDangNhap() { return tenDangNhap; }
    public String getHoTen() { return hoTen; }
    public String getVaiTro() { return vaiTro; }
    public boolean canXemSanPham() { return xemSanPham; }
    public boolean canTaoHoaDon() { return taoHoaDon; }
    public boolean canXemHoaDon() { return xemHoaDon; }
    public boolean canQuanLyNhanVien() { return quanLyNhanVien; }
    public boolean canQuanLySanPham() { return quanLySanPham; }
}