/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package da.repository;

import da.model.NhaCC;
import da.util.connectDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ADMIN
 */
public class NhaCungCapRepository {
    private Connection conn;

    public NhaCungCapRepository() {
        conn = connectDB.getConnection();
    }
    public List<NhaCC> getAllNhaCungCap() {
        List<NhaCC> list = new ArrayList<>();
        String select = "SELECT id, tenNCC, diaChi, soDienThoai, email FROM NhaCungCap";
        try {
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhaCC nhaCC = new NhaCC();
                nhaCC.setId(rs.getInt("id"));
                nhaCC.setTen(rs.getString("tenNCC"));
                nhaCC.setDiaChi(rs.getString("diaChi"));
                nhaCC.setSdt(rs.getString("soDienThoai"));
                nhaCC.setEmail(rs.getString("email"));
                list.add(nhaCC);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }
}
