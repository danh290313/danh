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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author danh
 */
public class JPanelDoAnTotNghiep extends javax.swing.JPanel {

    /**
     * Creates new form JPanelDoAnTotNghiep
     */
    public JPanelDoAnTotNghiep() {
        initComponents();
        initData();
        initboxGv();
    }
    DefaultTableModel model = new DefaultTableModel();
    public void initData()
    {   
        model = (DefaultTableModel) jTableDoAn.getModel();
        String sql = "select * from doantotnghiep";
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
                vt.add(rs.getString(6));
                vt.add(rs.getString(7));
                model.addRow(vt);
            }
            jTableDoAn.setModel(model);
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void initboxGv() {
        String sql = "select magv from giangvien";
        try (Connection con = DataBaseHelper.getConnection();
                PreparedStatement smt = con.prepareStatement(sql);) {
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                jComboBoxGVHD.addItem(rs.getString(1));
                jComboBoxGVPB.addItem(rs.getString(1));
            }          
            

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void lamMoi() {
        jTextDiemHD.setText("");
        jTextDiemPB.setText("");
        jTextAreaNDDoAn.setText("");
        jComboBoxGVHD.setSelectedIndex(-1);
        jComboBoxGVPB.setSelectedIndex(-1);
        jTableDoAn.setRowSelectionAllowed(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelMaDa = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDoAn = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxGVHD = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jComboBoxGVPB = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextDiemHD = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextDiemPB = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaNDDoAn = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButtonLamMoi = new javax.swing.JButton();
        jButtonSua = new javax.swing.JButton();

        setOpaque(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Đồ Án Tốt Nghiệp");
        jPanel1.add(jLabel1);

        jLabel4.setText("Mã Đồ Án: ");

        jLabelMaDa.setBackground(new java.awt.Color(204, 255, 0));

        jScrollPane1.setBackground(new java.awt.Color(255, 204, 204));

        jTableDoAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Đồ Án", "Điểm HD", "Điểm PB", "Nội dung", "Mã SV", "Mã GVHD", "Mã GVPB"
            }
        ));
        jTableDoAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDoAnMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableDoAn);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(2, 2, 20, 0));

        jLabel13.setText("Mã Giáo Viên hội đồng:");
        jPanel2.add(jLabel13);

        jPanel2.add(jComboBoxGVHD);

        jLabel14.setText("Mã Giáo Viên phản biện:");
        jPanel2.add(jLabel14);

        jPanel2.add(jComboBoxGVPB);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(2, 2, 20, 0));

        jLabel7.setText("Điểm hội đồng:");
        jPanel3.add(jLabel7);
        jPanel3.add(jTextDiemHD);

        jLabel8.setText("Điểm phản biện:");
        jPanel3.add(jLabel8);
        jPanel3.add(jTextDiemPB);

        jTextAreaNDDoAn.setColumns(20);
        jTextAreaNDDoAn.setRows(5);
        jScrollPane2.setViewportView(jTextAreaNDDoAn);

        jLabel2.setText("Nội dung Đồ Án:");

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(1, 2, 20, 0));

        jButtonLamMoi.setBackground(new java.awt.Color(0, 102, 204));
        jButtonLamMoi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLamMoi.setText("Làm Mới");
        jButtonLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLamMoiActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonLamMoi);

        jButtonSua.setBackground(new java.awt.Color(0, 102, 204));
        jButtonSua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonSua.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSua.setText("Sửa");
        jButtonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonSua);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(31, 31, 31)
                                .addComponent(jLabelMaDa, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(33, 33, 33))
            .addGroup(layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelMaDa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
    }// </editor-fold>//GEN-END:initComponents
    
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
    private void jTableDoAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDoAnMouseClicked
        // TODO add your handling code here:
        int row = jTableDoAn.getSelectedRow();
        try
        {
            if(row>=0)
            {
                jLabelMaDa.setText(jTableDoAn.getValueAt(row, 0).toString().toUpperCase());
                jTextDiemHD.setText(jTableDoAn.getValueAt(row, 1).toString().toUpperCase());
                jTextDiemPB.setText(jTableDoAn.getValueAt(row, 2).toString());
                jTextAreaNDDoAn.setText(jTableDoAn.getValueAt(row, 3).toString());
                if(jTableDoAn.getValueAt(row, 5).toString().trim().equals(""))
                    jComboBoxGVHD.setSelectedIndex(-1);
                else
                jComboBoxGVHD.setSelectedItem(jTableDoAn.getValueAt(row, 5).toString());

                if(jTableDoAn.getValueAt(row, 6).toString().trim().equals(""))
                    jComboBoxGVPB.setSelectedIndex(-1);
                else
                jComboBoxGVPB.setSelectedItem(jTableDoAn.getValueAt(row, 6).toString());

            }
        }
        catch(Exception ex)
        {
           ex.printStackTrace();
        }
            

        

    }//GEN-LAST:event_jTableDoAnMouseClicked

    private void jButtonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaActionPerformed
        //        // TODO add your handling code here:

        int kt = JOptionPane.showConfirmDialog(this, "ban chan chan muon sua khong");
        if (kt == JOptionPane.CANCEL_OPTION) {
            return;
        } else if (kt == JOptionPane.OK_OPTION) {
            String sql = "update doantotnghiep set diemhd=?, diempb=?,noidung=?, magvhd=?, magvPb=? where mada = ?";
            try (Connection con = DataBaseHelper.getConnection();
                PreparedStatement smt = con.prepareStatement(sql);) {

                smt.setString(1, jTextDiemHD.getText());
                smt.setString(2, jTextDiemPB.getText());
                smt.setString(3, jTextAreaNDDoAn.getText());
                smt.setString(4, jComboBoxGVHD.getSelectedItem().toString());
                smt.setString(5, jComboBoxGVPB.getSelectedItem().toString());
                smt.setString(6, jLabelMaDa.getText());
                
                

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

    private void jButtonLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLamMoiActionPerformed
        // TODO add your handling code here:
       lamMoi();
    }//GEN-LAST:event_jButtonLamMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLamMoi;
    private javax.swing.JButton jButtonSua;
    private javax.swing.JComboBox<String> jComboBoxGVHD;
    private javax.swing.JComboBox<String> jComboBoxGVPB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelMaDa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableDoAn;
    private javax.swing.JTextArea jTextAreaNDDoAn;
    private javax.swing.JTextField jTextDiemHD;
    private javax.swing.JTextField jTextDiemPB;
    // End of variables declaration//GEN-END:variables
}