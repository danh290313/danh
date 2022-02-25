/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanly;

import doan.DataBaseHelper;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hyipd
 */
public class JPanelMonHoc extends javax.swing.JPanel {

    /**
     * Creates new form JPanelMonHoc
     */
    public JPanelMonHoc() {
        initComponents();
        initData();
        jLabelMaMH.setText(taoMaMH());
        jLabelMaMH.setForeground(Color.RED);
    }
    
    DefaultTableModel model = new DefaultTableModel();
    
    
    
    public void initData()
    {   
        model = (DefaultTableModel) jTableDSMH.getModel();
        String sql = "select * from monhoc";
        model.setRowCount(0);
        try(Connection con = DataBaseHelper.getConnection();
                Statement smt = con.createStatement();)
        {   
            Vector vt;
            ResultSet rs = smt.executeQuery(sql);
            while(rs.next())
            {
                vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                vt.add(rs.getString(4));
                vt.add(rs.getString(5));
                model.addRow(vt);
            }
            jTableDSMH.setModel(model);
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public String taoMaMH()
    {
        String sql = "select mamh from monhoc";
        int max =0;
        try(Connection con = DataBaseHelper.getConnection();
                Statement smt = con.createStatement();
                 )
        {   
            ResultSet rs = smt.executeQuery(sql);
            while(rs.next())
            {   
                int ma = Integer.parseInt(rs.getString(1).substring(2,rs.getString(1).length()).trim());
                if(ma> max)
                    max = ma;
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "MH" + String.valueOf(max +1);
    }
    
    public void lamMoi()
    {
        jTextMh.setText("");
        jTextStLt.setText("");
        jTextStTh.setText("");
        jTextSTc.setText("");
    }
    
    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#FFFDE4"), 0, getHeight(), Color.decode("#005AA7"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDSMH = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonLamMoi = new javax.swing.JButton();
        jButtonThem = new javax.swing.JButton();
        jButtonXoa = new javax.swing.JButton();
        jButtonSua = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabelMaMH = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextMh = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextStLt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextStTh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextSTc = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 204, 204));
        setPreferredSize(new java.awt.Dimension(1050, 700));

        jScrollPane1.setBackground(new java.awt.Color(255, 204, 204));

        jTableDSMH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Môn Học", "Tên Môn Học", "Số tiết lý thuyết", "Số tiết thưc hành", "Số tín chỉ"
            }
        ));
        jTableDSMH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDSMHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableDSMH);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Môn Học");
        jPanel1.add(jLabel1);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        jButtonLamMoi.setBackground(new java.awt.Color(255, 204, 204));
        jButtonLamMoi.setText("Làm Mới");
        jButtonLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLamMoiActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonLamMoi);

        jButtonThem.setBackground(new java.awt.Color(255, 204, 204));
        jButtonThem.setText("Thêm");
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonThem);

        jButtonXoa.setBackground(new java.awt.Color(255, 204, 204));
        jButtonXoa.setText("Xóa");
        jButtonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonXoa);

        jButtonSua.setBackground(new java.awt.Color(255, 204, 204));
        jButtonSua.setText("Sửa");
        jButtonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonSua);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(0, 2, 10, 0));

        jLabel6.setBackground(new java.awt.Color(255, 204, 204));
        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel6.setText("Mã Môn Học: ");
        jPanel3.add(jLabel6);

        jLabelMaMH.setBackground(new java.awt.Color(255, 204, 204));
        jPanel3.add(jLabelMaMH);

        jLabel2.setBackground(new java.awt.Color(255, 204, 204));
        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel2.setText("Tên Môn Học: ");
        jPanel3.add(jLabel2);

        jTextMh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextMhActionPerformed(evt);
            }
        });
        jPanel3.add(jTextMh);

        jLabel3.setBackground(new java.awt.Color(255, 204, 204));
        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel3.setText("Số tiết lý thuyết: ");
        jPanel3.add(jLabel3);
        jPanel3.add(jTextStLt);

        jLabel4.setBackground(new java.awt.Color(255, 204, 204));
        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel4.setText("Số tiết thực hành: ");
        jPanel3.add(jLabel4);
        jPanel3.add(jTextStTh);

        jLabel5.setBackground(new java.awt.Color(255, 204, 204));
        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel5.setText("Số tín chỉ: ");
        jPanel3.add(jLabel5);

        jTextSTc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextSTcActionPerformed(evt);
            }
        });
        jPanel3.add(jTextSTc);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLamMoiActionPerformed
        // TODO add your handling code here:
        lamMoi();
    }//GEN-LAST:event_jButtonLamMoiActionPerformed

    private void jTextMhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextMhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextMhActionPerformed

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        // TODO add your handling code here:

        String sql = "insert into monhoc values(?,?,?,?,?)";
        try (Connection con = DataBaseHelper.getConnection();
            PreparedStatement smt = con.prepareStatement(sql);) {
            smt.setString(1, taoMaMH());
            smt.setString(2, jTextMh.getText());
            smt.setInt(3, Integer.parseInt(jTextStLt.getText()) );
            smt.setInt(4, Integer.parseInt(jTextStTh.getText()));
            smt.setInt(5, Integer.parseInt(jTextSTc.getText()));

            int kt = smt.executeUpdate();
            if (kt > 0) {
                JOptionPane.showMessageDialog(this, "insert thanh cong");
            }
            initData();
            lamMoi();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButtonThemActionPerformed

    private void jButtonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaActionPerformed
        // TODO add your handling code here:
        int kt = JOptionPane.showConfirmDialog(this, "ban co muon xoa khong");
        if (kt == JOptionPane.CANCEL_OPTION) {
            return;
        } else if (kt == JOptionPane.OK_OPTION) {
            String sql = "delete from monhoc where mamh=?";
            try (Connection con = DataBaseHelper.getConnection();
                PreparedStatement smt = con.prepareStatement(sql);) {
                smt.setString(1, jLabelMaMH.getText());

                int kt2 = smt.executeUpdate();
                if (kt2 > 0) {
                    JOptionPane.showMessageDialog(this, "xoa thanh cong");
                }
                initData();
                lamMoi();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButtonXoaActionPerformed

    private void jButtonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaActionPerformed
        // TODO add your handling code here:
        int kt = JOptionPane.showConfirmDialog(this, "ban chan chan muon sua khong");
        if (kt == JOptionPane.CANCEL_OPTION) {
            return;
        } else if (kt == JOptionPane.OK_OPTION) {
            String sql = "update monhoc set tenmh=?,sotietlt=?,sotietth=?,sotinchi=? where mamh = ?";
            try (Connection con = DataBaseHelper.getConnection();
                PreparedStatement smt = con.prepareStatement(sql);) {

                smt.setString(1, jTextMh.getText());
                smt.setInt(2, Integer.parseInt(jTextStLt.getText()));
                smt.setInt(3, Integer.parseInt(jTextStTh.getText()));
                smt.setInt(4, Integer.parseInt(jTextSTc.getText()));
                smt.setString(5, jLabelMaMH.getText());

                int kt2 = smt.executeUpdate();
                if (kt2 > 0) {
                    JOptionPane.showMessageDialog(this, "update thanh cong");
                }
                initData();
                lamMoi();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.toString());
            }
        }
    }//GEN-LAST:event_jButtonSuaActionPerformed

    private void jTableDSMHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDSMHMouseClicked
        // TODO add your handling code here:
        int row = jTableDSMH.getSelectedRow();
        if(row>=0)
        {
            jLabelMaMH.setText(jTableDSMH.getValueAt(row, 0).toString().toUpperCase());
            jTextMh.setText(jTableDSMH.getValueAt(row, 1).toString());
            jTextStLt.setText(jTableDSMH.getValueAt(row, 2).toString());
            jTextStTh.setText(jTableDSMH.getValueAt(row, 3).toString());
            jTextSTc.setText(jTableDSMH.getValueAt(row, 4).toString());
        }
    }//GEN-LAST:event_jTableDSMHMouseClicked

    private void jTextSTcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextSTcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextSTcActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLamMoi;
    private javax.swing.JButton jButtonSua;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JButton jButtonXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelMaMH;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDSMH;
    private javax.swing.JTextField jTextMh;
    private javax.swing.JTextField jTextSTc;
    private javax.swing.JTextField jTextStLt;
    private javax.swing.JTextField jTextStTh;
    // End of variables declaration//GEN-END:variables
}
