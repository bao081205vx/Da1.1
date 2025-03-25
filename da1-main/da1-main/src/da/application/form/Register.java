
package da.application.form;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import da.component.PasswordStrengthStatus;
import da.manager.FormsManager;
import raven.toast.Notifications;


public class Register extends JPanel {
    public Register() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
        txtFirstName = new JTextField();
        txtLastName = new JTextField();
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        txtConfirmPassword = new JPasswordField();
        cmdRegister = new JButton("Sign Up");

        cmdRegister.addActionListener(e -> {
            if (isMatchPassword()) {
                //  Do something here
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Passwords don't match. Try again!");
            }
        });
        passwordStrengthStatus = new PasswordStrengthStatus();

        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "[fill,360]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%)");

        txtFirstName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tên");
        txtLastName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Họ");
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên tài khoản hoặc email của bạn");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "\"Nhập mật khẩu của bạn");
        txtConfirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập lại mật khẩu của bạn");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "showRevealButton:true");
        txtConfirmPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "showRevealButton:true");

        cmdRegister.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]background:darken(@background,10%);" +
                "[dark]background:lighten(@background,10%);" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0");

        JLabel lbTitle = new JLabel("Chào mừng bạn đến với chúng tôi!");
        JLabel description = new JLabel("Hãy tham gia cùng chúng tôi để mua sắm trang phục mới. Đăng ký ngay và bắt đầu mua sắm!");
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");

        passwordStrengthStatus.initPasswordField(txtPassword);

        panel.add(lbTitle);
        panel.add(description);
        panel.add(new JLabel("Họ Tên"), "gapy 10");
        panel.add(txtFirstName, "split 2");
        panel.add(txtLastName);
        panel.add(new JSeparator(), "gapy 5 5");
        panel.add(new JLabel("Tài khoản hoặc email"));
        panel.add(txtUsername);
        panel.add(new JLabel("Mật Khẩu"), "gapy 8");
        panel.add(txtPassword);
        panel.add(passwordStrengthStatus, "gapy 0");
        panel.add(new JLabel("Xác nhận mật khẩu"), "gapy 0");
        panel.add(txtConfirmPassword);
        panel.add(cmdRegister, "gapy 20");
        panel.add(createLoginLabel(), "gapy 10");
        add(panel);
    }


    private Component createLoginLabel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.putClientProperty(FlatClientProperties.STYLE, "" +
                "background:null");
        JButton cmdLogin = new JButton("<html><a href=\"#\">Đăng nhập tại đây</a></html>");
        cmdLogin.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:3,3,3,3");
        cmdLogin.setContentAreaFilled(false);
        cmdLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdLogin.addActionListener(e -> {
            FormsManager.getInstance().showForm(new Login());
        });
        JLabel label = new JLabel("Bạn đã có tài khoản?");
        label.putClientProperty(FlatClientProperties.STYLE, "" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,30%)");
        panel.add(label);
        panel.add(cmdLogin);
        return panel;
    }

    public boolean isMatchPassword() {
        String password = String.valueOf(txtPassword.getPassword());
        String confirmPassword = String.valueOf(txtConfirmPassword.getPassword());
        return password.equals(confirmPassword);
    }

    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JButton cmdRegister;
    private PasswordStrengthStatus passwordStrengthStatus;    
}
