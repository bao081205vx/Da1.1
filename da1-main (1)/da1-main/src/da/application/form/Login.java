package da.application.form;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import da.application.Application;

import da.application.User;
import da.manager.FormsManager;
import raven.toast.Notifications;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JPanel {

    public Login() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        chRememberMe = new JCheckBox("Remember me");
        cmdLogin = new JButton("Login");
        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%)");

        txtPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "showRevealButton:true");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0");

        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên tài khoản hoặc email của bạn");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập mật khẩu của bạn");

        JLabel lbTitle = new JLabel("Chào mừng trở lại!");
        JLabel description = new JLabel("Vui lòng đăng nhập để truy cập tài khoản của bạn");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");

        panel.add(lbTitle);
        panel.add(description);
        panel.add(new JLabel("Tài Khoản"), "gapy 8");
        panel.add(txtUsername);
        panel.add(new JLabel("Mật Khẩu"), "gapy 8");
        panel.add(txtPassword);
        panel.add(chRememberMe, "grow 0");
        panel.add(cmdLogin, "gapy 10");
        panel.add(createSignupLabel(), "gapy 10");
        add(panel);
        cmdLogin.addActionListener(this::cmdLoginActionPerformed);
    }

    private Component createSignupLabel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        JButton cmdRegister = new JButton("<html><a href=\"#\">Tạo tài khoản</a></html>");
        cmdRegister.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:3,3,3,3");
        cmdRegister.setContentAreaFilled(false);
        cmdRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdRegister.addActionListener(e -> {
            FormsManager.getInstance().showForm(new Register());
        });
        JLabel label = new JLabel("Chưa có tài khoản ?");
        label.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");
        panel.add(label);
        panel.add(cmdRegister);
        return panel;
    }

    private void cmdLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.ERROR, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        try (Connection conn = DbConnect.getConnection()) {
            String sql = "SELECT t.tendangnhap, p.vaitro, p.xem_sanpham, p.tao_hoadon, p.xem_hoadon, p.quanly_nhanvien, p.quanly_sanpham " +
                         "FROM taikhoan t JOIN phanquyen p ON t.phanquyen_id = p.id " +
                         "WHERE t.tendangnhap = ? AND t.matkhau = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(
                    rs.getString("tendangnhap"),
                    rs.getString("vaitro"),
                    rs.getBoolean("xem_sanpham"),
                    rs.getBoolean("tao_hoadon"),
                    rs.getBoolean("xem_hoadon"),
                    rs.getBoolean("quanly_nhanvien"),
                    rs.getBoolean("quanly_sanpham")
                );
                Application.setCurrentUser(user); // Lưu thông tin người dùng
                Application.login(); // Chuyển sang MainForm
                Notifications.getInstance().show(Notifications.Type.SUCCESS, "Đăng nhập thành công!");
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Tên đăng nhập hoặc mật khẩu không đúng!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi kết nối cơ sở dữ liệu!");
        }
    }

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JCheckBox chRememberMe;
    private JButton cmdLogin;
}