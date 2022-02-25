package sinhvien;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import doan.DataBaseHelper;
import doan.DataBaseHelper;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Tung
 */
public class PasswordChange extends javax.swing.JFrame {

    /**
     * Creates new form PasswordChange
     */
    
    boolean flag1 = false, flag2 = false, flag3 = false;

    public PasswordChange(String masv) {
        initComponents();
        ImageIcon img = new ImageIcon("image//change_pass.png");
        this.setIconImage(img.getImage());
        lbName.setText(masv.toUpperCase());
        lbName.setForeground(Color.red);
        btnOK.setEnabled(false);
    }

    private PasswordChange() {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbPassCu = new javax.swing.JLabel();
        lbPassNew = new javax.swing.JLabel();
        lbConfirmPass = new javax.swing.JLabel();
        btnOK = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lbName = new javax.swing.JLabel();
        txtPassCu = new javax.swing.JPasswordField();
        txtPassNew = new javax.swing.JPasswordField();
        txtConfirmPass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Đổi mật khẩu");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 51, 0));
        jLabel1.setText("Mật khẩu cũ:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 51, 0));
        jLabel2.setText("Mật khẩu mới:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 51, 0));
        jLabel3.setText("Nhập lại mật khẩu:");

        lbPassCu.setForeground(new java.awt.Color(255, 0, 0));
        lbPassCu.setText(" ");

        lbPassNew.setForeground(new java.awt.Color(255, 0, 0));
        lbPassNew.setText(" ");

        lbConfirmPass.setForeground(new java.awt.Color(255, 0, 0));
        lbConfirmPass.setText(" ");

        btnOK.setText("Đồng ý");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        jButton2.setText("Làm mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lbName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbName.setText("Trống");

        txtPassCu.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPassCuCaretUpdate(evt);
            }
        });

        txtPassNew.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtPassNewCaretUpdate(evt);
            }
        });

        txtConfirmPass.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtConfirmPassCaretUpdate(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("Mã sinh viên: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtConfirmPass)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbConfirmPass)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnOK)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2)))
                                .addGap(0, 174, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbPassNew)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtPassNew)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(42, 42, 42)
                        .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassCu)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbPassCu)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPassCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPassCu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPassNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPassNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbConfirmPass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOK)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        txtPassCu.setText("");
        txtPassNew.setText("");
        txtConfirmPass.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        if (flag1 == true && flag2 == true && flag3 == true) {
            try {
                Connection con = DataBaseHelper.getConnection();
                PreparedStatement smt = con.prepareStatement("Update sinhvien set matkhau=? where masv=?");
                smt.setString(1, txtConfirmPass.getText());
                smt.setString(2, lbName.getText());
                smt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công.");
                this.setVisible(false);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại!");
        }
    }//GEN-LAST:event_btnOKActionPerformed

    private void txtPassCuCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPassCuCaretUpdate
        if (txtPassCu.getText().trim().equals("")) {
            lbPassCu.setText(" ");
            btnOK.setEnabled(false);
        } else {
            try {
                Connection con = DataBaseHelper.getConnection();
                PreparedStatement smt = con.prepareStatement("Select * from sinhvien where masv=? and matkhau=? ");
                smt.setString(1, lbName.getText());
                smt.setString(2, txtPassCu.getText());
                ResultSet  rs = smt.executeQuery();
                while (true) {
                    if (!rs.next()) {
                        lbPassCu.setText("Sai mật khẩu.");
                        lbPassCu.setForeground(Color.red);
                        flag1 = false;
                        btnOK.setEnabled(false);
                        return;
                    } else {
                        lbPassCu.setText("Đúng mật khẩu.");
                        lbPassCu.setForeground(Color.blue);
                        flag1 = true;
                        btnOK.setEnabled(false);
                        break;
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
            }
        }
    }//GEN-LAST:event_txtPassCuCaretUpdate

    private void txtPassNewCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtPassNewCaretUpdate
        if (txtPassNew.getText().trim().equals("")) {
            lbPassNew.setText(" ");
            btnOK.setEnabled(false);
        } else if (txtPassNew.getText().equals(txtPassCu.getText())) {
            lbPassNew.setText("Mật khẩu mới phải khác mật khẩu cũ.");
            lbPassNew.setForeground(Color.red);
            btnOK.setEnabled(false);
        } else {
            char x;
            for (int i = 0; i < txtPassNew.getText().length(); i++) {
                x = txtPassNew.getText().charAt(i);
                if (x == ' ') {
                    lbPassNew.setText("Mật khẩu không thể chứa khoảng trắng.");
                    lbPassNew.setForeground(Color.red);
                    flag2 = false;
                    btnOK.setEnabled(false);
                    return;
                }
            }
            while (true) {
                if (txtPassNew.getText().length() < 6 || txtPassNew.getText().length() > 18) {
                    lbPassNew.setText("Độ dài mật khẩu trong khoảng 6-18 kí tự.");
                    lbPassNew.setForeground(Color.red);
                    flag2 = false;
                    btnOK.setEnabled(false);
                    return;
                } else {
                    lbPassNew.setText("Mật khẩu hợp lệ.");
                    lbPassNew.setForeground(Color.blue);
                    flag2 = true;
                    btnOK.setEnabled(false);
                    break;
                }
            }
        }
    }//GEN-LAST:event_txtPassNewCaretUpdate

    private void txtConfirmPassCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtConfirmPassCaretUpdate
        if (txtConfirmPass.getText().trim().equals("")) {
            lbConfirmPass.setText(" ");
            btnOK.setEnabled(false);
        } else {
            while (true) {
                if (!txtConfirmPass.getText().equals(txtPassNew.getText())) {
                    lbConfirmPass.setText("Nhập lại mật khẩu phải giống mật khẩu.");
                    lbConfirmPass.setForeground(Color.red);
                    flag3 = false;
                    btnOK.setEnabled(false);
                    return;
                } else {
                    lbConfirmPass.setText("Hợp lệ.");
                    lbConfirmPass.setForeground(Color.blue);
                    flag3 = true;
                    btnOK.setEnabled(true);
                    break;
                }
            }
        }
    }//GEN-LAST:event_txtConfirmPassCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbConfirmPass;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPassCu;
    private javax.swing.JLabel lbPassNew;
    private javax.swing.JPasswordField txtConfirmPass;
    private javax.swing.JPasswordField txtPassCu;
    private javax.swing.JPasswordField txtPassNew;
    // End of variables declaration//GEN-END:variables
}
