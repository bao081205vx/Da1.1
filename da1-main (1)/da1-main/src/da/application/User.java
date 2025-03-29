package da.application;

public class User {
    private String username;
    private String role;
    private boolean xemSanPham;
    private boolean taoHoaDon;
    private boolean xemHoaDon;
    private boolean quanLyNhanVien;
    private boolean quanLySanPham;

    public User() {
    }
    

    public User(String username, String role, boolean xemSanPham, boolean taoHoaDon, boolean xemHoaDon, 
                boolean quanLyNhanVien, boolean quanLySanPham) {
        this.username = username;
        this.role = role;
        this.xemSanPham = xemSanPham;
        this.taoHoaDon = taoHoaDon;
        this.xemHoaDon = xemHoaDon;
        this.quanLyNhanVien = quanLyNhanVien;
        this.quanLySanPham = quanLySanPham;
    }

    public String getUsername() { return username; }
    public String getRole() { return role; }
    public boolean canXemSanPham() { return xemSanPham; }
    public boolean canTaoHoaDon() { return taoHoaDon; }
    public boolean canXemHoaDon() { return xemHoaDon; }
    public boolean canQuanLyNhanVien() { return quanLyNhanVien; }
    public boolean canQuanLySanPham() { return quanLySanPham; }
}