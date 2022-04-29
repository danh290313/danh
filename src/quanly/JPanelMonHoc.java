/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanly;

import core.ChuyenNganh;
import core.MonHoc;
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
import java.util.Stack;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static quanly.JPanelChuyenNganh.stack;
import static quanly.JPanelChuyenNganh.strings;

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
        strings = new Stack<>();
        stack = new Stack<>();
        jLabelMaMH.setText(taoMaMH());
        jLabelMaMH.setForeground(Color.RED);
    }
    
    DefaultTableModel model = new DefaultTableModel();
    public static Stack<String> strings = null;
    public static Stack<MonHoc> stack = null;
    
    
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
            JOptionPane.showMessageDialog(this, ex.toString());
        }
    }
    
    public void timKiemmh(String s)
    {   
        model = (DefaultTableModel) jTableDSMH.getModel();
        String sql = "select * from monhoc where mamh like N'%"+s+"%' or tenmh like N'%"+s+"%' or sotietlt like N'%"+s+"%' or sotietth like N'%"+s+"%' or sotinchi like N'%"+s+"%'";
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
            JOptionPane.showMessageDialog(this, ex.toString());
        }
        System.out.println("" + sql);
        
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
            JOptionPane.showMessageDialog(this, ex.toString());
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
    
    
    public MonHoc getMhTheoMa(String maMh)
    {
        MonHoc mh = new MonHoc();
        String sql = " SELECT * FROM monhoc WHERE mamh=N'" + maMh + "'";
        try {
            Connection con = DataBaseHelper.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                mh.setMaMH(rs.getString(1));
                mh.setTenMH(rs.getString(2)); 
                mh.setSoTietLT(Integer.parseInt(rs.getString(3))); 
                mh.setSoTietTH(Integer.parseInt(rs.getString(4))); 
                mh.setSoTinChi(Integer.parseInt(rs.getString(5))); 
                return mh;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
        return null;
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
        jButtonSua = new javax.swing.JButton();
        jButtonXoa = new javax.swing.JButton();
        jButtonKhoiPhuc = new javax.swing.JButton();
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
        jTextTimKiem = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

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

        jButtonLamMoi.setBackground(new java.awt.Color(0, 102, 204));
        jButtonLamMoi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLamMoi.setText("Làm Mới");
        jButtonLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLamMoiActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonLamMoi);

        jButtonThem.setBackground(new java.awt.Color(0, 102, 204));
        jButtonThem.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonThem.setForeground(new java.awt.Color(255, 255, 255));
        jButtonThem.setText("Thêm");
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonThem);

        jButtonSua.setBackground(new java.awt.Color(0, 102, 204));
        jButtonSua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonSua.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSua.setText("Sửa");
        jButtonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonSua);

        jButtonXoa.setBackground(new java.awt.Color(0, 102, 204));
        jButtonXoa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonXoa.setForeground(new java.awt.Color(255, 255, 255));
        jButtonXoa.setText("Xóa");
        jButtonXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXoaActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonXoa);

        jButtonKhoiPhuc.setBackground(new java.awt.Color(0, 102, 204));
        jButtonKhoiPhuc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonKhoiPhuc.setForeground(new java.awt.Color(255, 255, 255));
        jButtonKhoiPhuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/1restore.png"))); // NOI18N
        jButtonKhoiPhuc.setText("Khôi phục");
        jButtonKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKhoiPhucActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonKhoiPhuc);

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

        jTextMh.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
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

        jTextStLt.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jPanel3.add(jTextStLt);

        jLabel4.setBackground(new java.awt.Color(255, 204, 204));
        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel4.setText("Số tiết thực hành: ");
        jPanel3.add(jLabel4);

        jTextStTh.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jPanel3.add(jTextStTh);

        jLabel5.setBackground(new java.awt.Color(255, 204, 204));
        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel5.setText("Số tín chỉ: ");
        jPanel3.add(jLabel5);

        jTextSTc.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jTextSTc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextSTcActionPerformed(evt);
            }
        });
        jPanel3.add(jTextSTc);

        jTextTimKiem.setPreferredSize(new java.awt.Dimension(79, 13));
        jTextTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTimKiemActionPerformed(evt);
            }
        });
        jTextTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextTimKiemKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel12.setText("Tìm Kiếm:");
        jLabel12.setMaximumSize(new java.awt.Dimension(90, 22));
        jLabel12.setPreferredSize(new java.awt.Dimension(90, 13));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                        .addGap(187, 187, 187))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1006, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addGap(807, 807, 807))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(42, 42, 42)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTextTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
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
    
    public void themMh(String maMh, String tenMh,int solt,int soth,int sotinchi, boolean checkRestore)
    {
        
        String sql = "insert into monhoc values(?,?,?,?,?)";
        try (Connection con = DataBaseHelper.getConnection();
            PreparedStatement smt = con.prepareStatement(sql);) {
            smt.setString(1, maMh);
            smt.setString(2, tenMh);
            smt.setInt(3,  solt);
            smt.setInt(4, soth);
            smt.setInt(5, sotinchi);

            int kt = smt.executeUpdate();
            if (kt > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                if(checkRestore)
                {
                    strings.push("Them");
                    stack.push(new MonHoc(maMh,tenMh,solt,soth,sotinchi));
                }
            }
            initData();
            lamMoi();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
    }
    
    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        // TODO add your handling code here:
        if(jTextMh.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Tên Môn học không được để trống");
            jTextMh.grabFocus();
            return;
        }
        if(jTextSTc.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Số tín chỉ không được để trống");
            jTextSTc.grabFocus();
            return;
        }
        if(jTextStLt.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Số tiết không được để trống");
            jTextStLt.grabFocus();
            return;
        }
        if(jTextStTh.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Số tiết học không được để trống");
            jTextStTh.grabFocus();
            return;
        }
        
        themMh(taoMaMH(), jTextMh.getText(), Integer.parseInt(jTextStLt.getText()), Integer.parseInt(jTextStTh.getText()), Integer.parseInt(jTextSTc.getText()), true);
        
    }//GEN-LAST:event_jButtonThemActionPerformed
    

    public void xoaMh(String maMh,boolean checkRestore)
    {   
        if(checkRestore)
        {
            strings.push("Xoa");
            stack.push(getMhTheoMa(maMh));
        }
        
         String sql = "delete from monhoc where mamh=?";
            try (Connection con = DataBaseHelper.getConnection();
                PreparedStatement smt = con.prepareStatement(sql);) {
                smt.setString(1, maMh);

                int kt2 = smt.executeUpdate();
                if (kt2 > 0) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                }
                initData();
                lamMoi();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.toString());
            }
    }
    
    private void jButtonXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXoaActionPerformed
        // TODO add your handling code here:
        int kt = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không ?");
        if (kt == JOptionPane.CANCEL_OPTION) {
            return;
        } else if (kt == JOptionPane.OK_OPTION) {
            xoaMh(jLabelMaMH.getText(), true);
        }
    }//GEN-LAST:event_jButtonXoaActionPerformed
    
    public void suaMh(String maMh, String tenMh,int solt,int soth,int sotinchi, boolean checkRestore)
    {
        if(checkRestore)
           {
               strings.push("Sua");
               stack.push(getMhTheoMa(maMh));
           }
        
        String sql = "update monhoc set tenmh=?,sotietlt=?,sotietth=?,sotinchi=? where mamh = ?";
            try (Connection con = DataBaseHelper.getConnection();
                PreparedStatement smt = con.prepareStatement(sql);) {

                smt.setString(1, tenMh);
                smt.setInt(2, solt);
                smt.setInt(3, soth);
                smt.setInt(4, sotinchi);
                smt.setString(5, maMh);

                int kt2 = smt.executeUpdate();
                if (kt2 > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                }
                initData();
                lamMoi();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.toString());
            }
    }
    
    private void jButtonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaActionPerformed
        // TODO add your handling code here:
        
        if(jTextMh.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Tên Môn học không được để trống");
            jTextMh.grabFocus();
            return;
        }
        if(jTextSTc.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Số tín chỉ không được để trống");
            jTextSTc.grabFocus();
            return;
        }
        if(jTextStLt.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Số tiết không được để trống");
            jTextStLt.grabFocus();
            return;
        }
        if(jTextStTh.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Số tiết học không được để trống");
            jTextStTh.grabFocus();
            return;
        }
        int kt = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?");
        if (kt == JOptionPane.CANCEL_OPTION) {
            return;
        } else if (kt == JOptionPane.OK_OPTION) {
            suaMh(jLabelMaMH.getText(), jTextMh.getText(), Integer.parseInt(jTextStLt.getText()), Integer.parseInt(jTextStTh.getText()), Integer.parseInt(jTextSTc.getText()), true);
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

    private void jTextTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTimKiemActionPerformed

    private void jTextTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextTimKiemKeyReleased
        // TODO add your handling code here:
        timKiemmh(jTextTimKiem.getText());
    }//GEN-LAST:event_jTextTimKiemKeyReleased

    private void jButtonKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKhoiPhucActionPerformed
        // TODO add your handling code here:
        if (strings.isEmpty() && stack.isEmpty()) {
            return;
        } else {

            int kt = JOptionPane.showConfirmDialog(
                this, "Bạn có chắc chắn quay lại?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (kt == JOptionPane.YES_OPTION) {

                String check = strings.pop();
                MonHoc mh = stack.pop();

                if (check.equals("Them")) {

                    xoaMh(mh.getMaMH(),false);
                }
                else if (check.equals("Sua")) {
                    suaMh(mh.getMaMH(), mh.getTenMH(),mh.getSoTietLT(),mh.getSoTietTH(),mh.getSoTinChi(),false);
                }
                else if (check.equals("Xoa")) {
                    themMh(mh.getMaMH(), mh.getTenMH(),mh.getSoTietLT(),mh.getSoTietTH(),mh.getSoTinChi(),false);
                }

            }

        }
    }//GEN-LAST:event_jButtonKhoiPhucActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonKhoiPhuc;
    private javax.swing.JButton jButtonLamMoi;
    private javax.swing.JButton jButtonSua;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JButton jButtonXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTextField jTextTimKiem;
    // End of variables declaration//GEN-END:variables
}
