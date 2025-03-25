/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Model.SanPham;
import java.time.LocalDateTime;

/**
 *
 * @author ADMIN
 */
public class SanPhamRepository {
    private Connection conn;
    public SanPhamRepository(){
    conn = DbConnect.getConnection();
}
    public List<SanPham> getListNhanVien(){
        List<SanPham> list = new ArrayList<>();
        String select = "SELECT * FROM sanpham";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setId(rs.getInt(1));
                sp.setTen(rs.getString(2));
                sp.setMota(rs.getString(3));
                sp.setGia(rs.getBigDecimal(4));
                sp.setSoluongton(rs.getInt(5));
                sp.setHinhanh(rs.getString(6));
                sp.setTrangthai(rs.getBoolean(7));
                sp.setNgaytao(LocalDateTime.MAX);
                list.add(sp);
            }
        }catch(Exception e){
            System.out.println("e"+e);
        }
        return list;
    }
}
