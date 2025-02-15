/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanly;

/**
 *
 * @author hyipd
 */
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.raven.swing.ScrollBar;
import static com.raven.swing.scroll.sroll;
import core.ChuyenNganh;
import dao.Provider;
import static dao.Provider.searchMaMonHoc;
import design.DataBaseHelper;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class JPanelChuyenNganh extends javax.swing.JPanel {

    /**
     * Creates new form JPanelChuyenNganh
     */
    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#1ccce0"), 0, getHeight(), Color.decode("#005AA7"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }
    TableModel model2 = new DefaultTableModel() {
      @Override
      public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
      }
    };

    JTable table2 = new JTable(model2);
    
    DefaultTableModel model = new DefaultTableModel();
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
    java.util.Date date = java.util.Calendar.getInstance().getTime();
   // public static Stack<> stack = null;
    public static Stack<String> strings = null;
    public static Stack<ChuyenNganh> stack = null;
   
    public JPanelChuyenNganh() {
        initComponents();
        initData();
        strings = new Stack<>();
        stack = new Stack<>();
        sroll(jScrollPane1);
        sroll(jScrollPane2);
        jLabelMacn.setText(Provider.taoMaCN());
        jLabelMacn.setForeground(Color.red);
        jLabelMacn.setBackground(Color.GREEN);
        listMh.setMultipleMode(true);
        listMhChon.setMultipleMode(true);
        jDatedoantotnghiep.setDate(date);
        vohieuButtonKhiChuaChon();
        loadDsMh();
        jTableDSCN.getTableHeader().setFont( new Font( "Tahoma" , Font.BOLD, 14));
        jTableDSmhcn.getTableHeader().setFont( new Font( "Tahoma" , Font.BOLD, 14));
    }
    
    
    
    
    public void loadDsMh()
    {   
        listMh.removeAll();
        try(
                PreparedStatement smt = DataBaseHelper.con.prepareStatement("{call DS_MH_CHUACHON(?)}");)
        {   
            
            smt.setString(1,jLabelMacn.getText());
            ResultSet rs = smt.executeQuery();
            
            while(rs.next())
            {
                listMh.add(rs.getString(1));
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
    }
    
//    public void loadDsMhChon()
//    {   
//        listMhChon.removeAll();
//        try(
//                PreparedStatement smt = DataBaseHelper.con.prepareStatement("{call DS_MHCHON(?)}");)
//        {   
//            
//            smt.setString(1,jLabelMacn.getText());
//            ResultSet rs = smt.executeQuery();
//            
//            while(rs.next())
//            {
//                listMhChon.add(rs.getString(1));
//            }
//            
//            
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(this, ex.toString());
//        }
//    }
    
    public boolean checkMhChon(String tenMh)
    {   

        try(
                PreparedStatement smt = DataBaseHelper.con.prepareStatement("{call DS_MHCHON(?)}");)
        {   
            
            smt.setString(1,jLabelMacn.getText());
            ResultSet rs = smt.executeQuery();
            
            while(rs.next())
            {
                if(rs.getString(1).equals(tenMh)) return true;
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
        return false;
    }


    public void initData() {
        model = (DefaultTableModel) jTableDSCN.getModel();
        String sql = "select * from chuyennganh order by cast(substring(MaCN,3,10) as int)";
        model.setRowCount(0);
        try (
                Statement smt = DataBaseHelper.con.createStatement();) {
            Vector vt;
            ResultSet rs = smt.executeQuery(sql);
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                model.addRow(vt);
            }
            jTableDSCN.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
    }
    
    public void initDataMhCn()
    {
        model = (DefaultTableModel) jTableDSmhcn.getModel();
        String sql = "select tenmh,hesogk,hesock from kehoach,monhoc where kehoach.mamh=monhoc.mamh and kehoach.macn='"+ jLabelMacn.getText()+"'";
        model.setRowCount(0);
        try (
                Statement smt = DataBaseHelper.con.createStatement();) {
            Vector vt;
            ResultSet rs = smt.executeQuery(sql);
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                vt.add(rs.getString(3));
                model.addRow(vt);
            }
            jTableDSmhcn.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
    }

   

    public void lamMoi() {
        jLabelMacn.setText(Provider.taoMaCN());
        jTextTenCn.setText("");
        //jTableDSCN.setRowSelectionAllowed(false);
        
        model = (DefaultTableModel) jTableDSmhcn.getModel();
        model.setRowCount(0);
        vohieuButtonKhiChuaChon();
        jButtonThem.setEnabled(true);
        loadDsMh();
      //  loadDsMhChon();
        listMhChon.removeAll();
        jTextHsTh.setText("");
        jTextHsck.setText("");
        initData();

    }
    
    public void lamMoiMhcn() {
        jTextHsTh.setText("");
        jTextHsck.setText("");
        jLabelTenMhcn.setText("");
    }
    
    public void vohieuButtonKhiChuaChon ()
    {
        jButtonSua.setEnabled (false);
        jButtonXoa.setEnabled (false);
        jButtonBack.setEnabled (false);
        jButtonGo.setEnabled (false);
        jButtonaddMhcn.setEnabled (false);
        jButtonsuamhcn.setEnabled (false);
        jButtonxoamhcn.setEnabled (false);
        jPanelKeHoach.setVisible (false);
        //listMh.setVisible(false);
    }
    
    public void KichHoatButtonKhiChon()
    {
        jButtonSua.setEnabled(true);
        jButtonXoa.setEnabled(true);
        jButtonBack.setEnabled(true);
        jButtonGo.setEnabled(true);
        jButtonaddMhcn.setEnabled(true);
        jPanelKeHoach.setEnabled(true);
    }

    private static float roundFloat(float f, int places) {
 
        BigDecimal bigDecimal = new BigDecimal(Float.toString(f));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.floatValue();
    }
    
    
    
    
    
     public String searchDiemChuan(String maCn) {
        String sql = "{CALL TIM_DIEMCHUANDA_THEOMACN(?)}";
        Float a = null;
        try(
                PreparedStatement smt = DataBaseHelper.con.prepareStatement(sql);)
        {   
            
            smt.setString(1,maCn);
            ResultSet rs = smt.executeQuery();  
            if(rs.next())
               return rs.getString(1);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
        return "";
    }
     
     public void timKiemCn(String s)
    {   
        model = (DefaultTableModel) jTableDSCN.getModel();
        String sql = "select * from chuyennganh where macn like N'%"+s+"%' or tencn like N'%"+s+"%'";
        model.setRowCount(0);
        try(
                Statement smt = DataBaseHelper.con.createStatement();)
        {   
            Vector vt;
            ResultSet rs = smt.executeQuery(sql);
            while (rs.next()) {
                vt = new Vector();
                vt.add(rs.getString(1));
                vt.add(rs.getString(2));
                model.addRow(vt);
            }
            jTableDSCN.setModel(model);
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
        
    }
     
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelMacn = new javax.swing.JLabel();
        jTextTenCn = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDSCN = new javax.swing.JTable();
        ngan = new javax.swing.JSeparator();
        jPanelKeHoach = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableDSmhcn = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextHsTh = new javax.swing.JTextField();
        jTextHsck = new javax.swing.JTextField();
        jButtonsuamhcn = new javax.swing.JButton();
        jButtonxoamhcn = new javax.swing.JButton();
        jLabelTenMhcn = new javax.swing.JLabel();
        jButtonaddMhcn = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButtonBack = new javax.swing.JButton();
        jButtonGo = new javax.swing.JButton();
        listMh = new java.awt.List();
        listMhChon = new java.awt.List();
        jLabel3 = new javax.swing.JLabel();
        jTextdaTn = new javax.swing.JTextField();
        jButtonLichSuDiemChuan = new javax.swing.JButton();
        jButtonDiemChuan = new javax.swing.JButton();
        jDatedoantotnghiep = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jButtonLamMoi = new javax.swing.JButton();
        jButtonThem = new javax.swing.JButton();
        jButtonSua = new javax.swing.JButton();
        jButtonXoa = new javax.swing.JButton();
        jButtonKhoiPhuc = new javax.swing.JButton();
        jTextTimKiem = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setPreferredSize(new java.awt.Dimension(1050, 700));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Tên chuyên ngành:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Mã chuyên ngành: ");

        jLabelMacn.setBackground(new java.awt.Color(204, 255, 0));
        jLabelMacn.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jTextTenCn.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jTextTenCn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTenCnActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Chuyên ngành");
        jPanel1.add(jLabel1);

        jTableDSCN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Mã chuyên ngành", "Tên chuyên ngành"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDSCN.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableDSCN.setOpaque(false);
        jTableDSCN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDSCNMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableDSCN);

        ngan.setName("adfs"); // NOI18N

        jPanelKeHoach.setBorder(javax.swing.BorderFactory.createTitledBorder("Kế Hoach"));
        jPanelKeHoach.setToolTipText("zvds");
        jPanelKeHoach.setName("Kế Hoạch"); // NOI18N
        jPanelKeHoach.setOpaque(false);

        jTableDSmhcn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên Môn Học", "Hệ số giữa kỳ", "Hệ số cuối kỳ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDSmhcn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDSmhcnMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableDSmhcn);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setText("Hệ số giữa kỳ:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setText("Hệ số cuối kỳ:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setText("Môn Học:");

        jTextHsTh.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jTextHsck.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jButtonsuamhcn.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButtonsuamhcn.setText("Sửa");
        jButtonsuamhcn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsuamhcnActionPerformed(evt);
            }
        });

        jButtonxoamhcn.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButtonxoamhcn.setText("Xóa");
        jButtonxoamhcn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonxoamhcnActionPerformed(evt);
            }
        });

        jLabelTenMhcn.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N

        jButtonaddMhcn.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButtonaddMhcn.setText("Add");
        jButtonaddMhcn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonaddMhcnActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setText("DS Môn Học");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel11.setText("DS Môn Được Chọn");

        jButtonBack.setText("<");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });

        jButtonGo.setText(">");
        jButtonGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGoActionPerformed(evt);
            }
        });

        listMh.setBackground(new java.awt.Color(204, 255, 255));
        listMh.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        listMh.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        listMh.setForeground(new java.awt.Color(255, 0, 0));
        listMh.setMultipleMode(true);
        listMh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listMhActionPerformed(evt);
            }
        });

        listMhChon.setBackground(new java.awt.Color(204, 255, 255));
        listMhChon.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        listMhChon.setForeground(new java.awt.Color(0, 153, 102));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel3.setText("Điểm chuẩn đồ án tốt nghiệp hiện tại:");

        jTextdaTn.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jTextdaTn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextdaTnFocusGained(evt);
            }
        });
        jTextdaTn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextdaTnActionPerformed(evt);
            }
        });
        jTextdaTn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextdaTnKeyPressed(evt);
            }
        });

        jButtonLichSuDiemChuan.setText("Lịch sử điểm chuẩn");
        jButtonLichSuDiemChuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLichSuDiemChuanActionPerformed(evt);
            }
        });

        jButtonDiemChuan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonDiemChuan.setForeground(new java.awt.Color(255, 0, 0));
        jButtonDiemChuan.setText("Xác nhận");
        jButtonDiemChuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDiemChuanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelKeHoachLayout = new javax.swing.GroupLayout(jPanelKeHoach);
        jPanelKeHoach.setLayout(jPanelKeHoachLayout);
        jPanelKeHoachLayout.setHorizontalGroup(
            jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKeHoachLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelKeHoachLayout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addGroup(jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelKeHoachLayout.createSequentialGroup()
                                .addComponent(listMh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonBack)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonGo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanelKeHoachLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(92, 92, 92)))
                        .addGroup(jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelKeHoachLayout.createSequentialGroup()
                                .addComponent(listMhChon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(52, 52, 52))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelKeHoachLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)))
                        .addComponent(jButtonaddMhcn)
                        .addGap(225, 225, 225))
                    .addComponent(jScrollPane2))
                .addContainerGap())
            .addGroup(jPanelKeHoachLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextdaTn, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jDatedoantotnghiep, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jButtonDiemChuan, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonLichSuDiemChuan))
            .addGroup(jPanelKeHoachLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabelTenMhcn, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextHsTh, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jTextHsck, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButtonxoamhcn)
                .addGap(18, 18, 18)
                .addComponent(jButtonsuamhcn)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelKeHoachLayout.setVerticalGroup(
            jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKeHoachLayout.createSequentialGroup()
                .addGroup(jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelKeHoachLayout.createSequentialGroup()
                        .addGroup(jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelKeHoachLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(listMh, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                    .addComponent(listMhChon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(12, 12, 12))
                            .addGroup(jPanelKeHoachLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonBack)
                                    .addComponent(jButtonGo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(jTextHsTh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextHsck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonsuamhcn)
                                .addComponent(jButtonxoamhcn))
                            .addComponent(jLabelTenMhcn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelKeHoachLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jButtonaddMhcn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jDatedoantotnghiep, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanelKeHoachLayout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addGroup(jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextdaTn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelKeHoachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonDiemChuan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonLichSuDiemChuan)))
                .addGap(15, 15, 15))
        );

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridLayout(1, 0, 20, 0));

        jButtonLamMoi.setBackground(new java.awt.Color(0, 102, 204));
        jButtonLamMoi.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/1refresh.png"))); // NOI18N
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
        jButtonThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
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
        jButtonSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/1edit.png"))); // NOI18N
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
        jButtonXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/1delete.png"))); // NOI18N
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
        jButtonKhoiPhuc.setText("Hoàn tác");
        jButtonKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKhoiPhucActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonKhoiPhuc);

        jTextTimKiem.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
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

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Tìm Kiếm:");
        jLabel12.setMaximumSize(new java.awt.Dimension(90, 22));
        jLabel12.setPreferredSize(new java.awt.Dimension(90, 13));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelMacn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextTenCn, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1009, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(31, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ngan)
                        .addGap(54, 54, 54))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelKeHoach, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMacn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextTenCn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(ngan, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelKeHoach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelKeHoach.getAccessibleContext().setAccessibleName("Kế");
    }// </editor-fold>//GEN-END:initComponents
    
    public void themCn(String maCn, String tenCn, boolean checkRestore)
    {
        String sql = "insert into chuyennganh values(?,?)";
        try (
            PreparedStatement smt = DataBaseHelper.con.prepareStatement(sql)) {    
            smt.setString(1, maCn);
            smt.setString(2, tenCn);

            int kt = smt.executeUpdate();
            if (kt > 0) {     
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                if(checkRestore)
                {
                    strings.push("Them");
                    stack.push(new ChuyenNganh(maCn, tenCn));
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
        boolean matchTen = jTextTenCn.getText().matches("[a-zaAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ]+");
        if ("".equals(jTextTenCn.getText()))
        {
            JOptionPane.showMessageDialog(this, "Tên chuyên ngành được để trống.");
        }
        else if(!matchTen) 
        {
        	JOptionPane.showMessageDialog(this, "Tên chuyên ngành sai định dạng.");
        }
        else
        {
            //JOptionPane.showMessageDialog(this, "Tên chuyên ngành không được để trống");
            themCn(Provider.taoMaCN(), jTextTenCn.getText(),true);
            jTextTenCn.grabFocus();
            return;
        }
        //themCn(Provider.taoMaCN(), jTextTenCn.getText(),true);
    }//GEN-LAST:event_jButtonThemActionPerformed
    
    public void xoaCn(String maCn,boolean checkRestore)
    {   
        if(checkRestore)
        {
            strings.push("Xoa");
            stack.push(Provider.getCnTheoMa(maCn));
        }
        
        String sql = "delete from chuyennganh where macn=?";
            try (
                PreparedStatement smt = DataBaseHelper.con.prepareStatement(sql);) {
                smt.setString(1, maCn);

                int kt2 = smt.executeUpdate();
                if (kt2 > 0) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                }
                initData();
                initDataMhCn();
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
            xoaCn(jLabelMacn.getText(),true);
        }
    }//GEN-LAST:event_jButtonXoaActionPerformed
    
    public void suaCn(String maCn, String tenCn, boolean checkRestore)
    {
        if(checkRestore)
           {
               strings.push("Sua");
               stack.push(Provider.getCnTheoMa(maCn));
           }
        
        String sql = "update chuyennganh set tencn=? where macn = ?";
        try (
            PreparedStatement smt = DataBaseHelper.con.prepareStatement(sql);) {

            smt.setString(1, tenCn);
            smt.setString(2, maCn);
 
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
        boolean matchTen = jTextTenCn.getText().matches("[a-zaAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆ fFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTu UùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ]+");
        if ("".equals(jTextTenCn.getText()))
        {
            JOptionPane.showMessageDialog(this, "Tên chuyên ngành được để trống.");
        }
        else if(!matchTen) 
        {
        	JOptionPane.showMessageDialog(this, "Tên chuyên ngành sai định dạng.");
        }
        else
        {
//        if(jTextTenCn.getText().equals(""))
//        {
//            JOptionPane.showMessageDialog(this, "Tên Chuyên Ngành không được để trống");
            //jTextTenCn.grabFocus();
//            return;
//        }
       
            int kt = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?");
            if (kt == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (kt == JOptionPane.OK_OPTION) {

                suaCn( jLabelMacn.getText(), jTextTenCn.getText(),true);
            }
        }
    }//GEN-LAST:event_jButtonSuaActionPerformed

    private void jButtonLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLamMoiActionPerformed
        // TODO add your handling code here:
        lamMoi();
        loadDsMh();
      //  loadDsMhChon();
        listMhChon.removeAll();
        //model = (DefaultTableModel) jTableDSmhcn.getModel();
       // model.setRowCount(0);
    }//GEN-LAST:event_jButtonLamMoiActionPerformed

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        // TODO add your handling code here:
        if(listMhChon.getSelectedIndexes()!=null)
        for (String a : listMhChon.getSelectedItems()) {
            listMh.add(a);
            listMhChon.remove(a);
        }

    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jButtonGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGoActionPerformed
        // TODO add your handling code here:

        if(listMh.getSelectedIndexes()!=null)
        for (String a : listMh.getSelectedItems()) {
            listMhChon.add(a);
            listMh.remove(a);
        }
        
        
        
    }//GEN-LAST:event_jButtonGoActionPerformed

    private void listMhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listMhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listMhActionPerformed

    private void jButtonaddMhcnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonaddMhcnActionPerformed
        // TODO add your handling code here:
        int row = jTableDSCN.getSelectedRow();
        
        if(row <0)
        {
            JOptionPane.showMessageDialog(this, "Chưa chọn chuyên ngành, thêm trước khi lập kế hoạch");
            return;
        }
        String sql2 = "insert into kehoach values(?,?,0,0)";
        try (
            PreparedStatement smt = DataBaseHelper.con.prepareStatement(sql2)) {
            int kt=0;
            for(String mh: listMhChon.getItems())
            {
                
                    if(checkMhChon(mh)==false)
                    {
                        smt.setString(1, jLabelMacn.getText());
                        smt.setString(2, searchMaMonHoc(mh));
                        kt = smt.executeUpdate();
                    }
                
            }
            
            if (kt > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            }
            initDataMhCn();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
        listMhChon.removeAll();
        
        
        
    }//GEN-LAST:event_jButtonaddMhcnActionPerformed

    private void jButtonxoamhcnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonxoamhcnActionPerformed
        // TODO add your handling code here:
        int kt = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không ?");
        if (kt == JOptionPane.CANCEL_OPTION) {
            return;
        } else if (kt == JOptionPane.OK_OPTION) {
            String sql = "delete from kehoach where macn=? and mamh=?";
            try (
                PreparedStatement smt = DataBaseHelper.con.prepareStatement(sql);) {
                smt.setString(1, jLabelMacn.getText());
                smt.setString(2,searchMaMonHoc(jLabelTenMhcn.getText()));
                int kt2 = smt.executeUpdate();
                if (kt2 > 0) {
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                }
                initDataMhCn();
                lamMoiMhcn();
                loadDsMh();
              //  loadDsMhChon();
              listMhChon.removeAll();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.toString());
            }
        }
    }//GEN-LAST:event_jButtonxoamhcnActionPerformed
    
    
    private void jButtonsuamhcnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsuamhcnActionPerformed
        // TODO add your handling code here:
        if(Float.parseFloat(jTextHsTh.getText())<0 || Float.parseFloat(jTextHsTh.getText())>90)
        {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập 0<= hệ số điểm <=90.");
            jTextHsTh.grabFocus();
            return;
        }
        if(Float.parseFloat(jTextHsck.getText())<0 || Float.parseFloat(jTextHsck.getText())>90)
        {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập 0<= hệ số điểm <=90.");
            jTextHsck.grabFocus();
            return;
        }
        
        int kt = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?");
         
        if (kt == JOptionPane.CANCEL_OPTION) {
            return;
        } else if (kt == JOptionPane.OK_OPTION) {
            String sql = "update kehoach set hesogk=?,hesock=? where macn = ? and mamh=?";
            try (
                PreparedStatement smt = DataBaseHelper.con.prepareStatement(sql);) {

                smt.setString(3, jLabelMacn.getText());
                smt.setString(4, searchMaMonHoc(jLabelTenMhcn.getText()));
                               
                smt.setFloat(1,  Float.valueOf(jTextHsTh.getText()) );
                smt.setFloat(2, Float.valueOf(jTextHsck.getText()) );
                int kt2 = smt.executeUpdate();
                if (kt2 > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                }
                initDataMhCn();
                lamMoiMhcn();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.toString());
            }
        }
    }//GEN-LAST:event_jButtonsuamhcnActionPerformed

    private void jTableDSCNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDSCNMouseClicked
        // TODO add your handling code here:
        int row = jTableDSCN.getSelectedRow();
        if(row>=0)
        {
            jLabelMacn.setText(jTableDSCN.getValueAt(row, 0).toString().toUpperCase());
            jTextTenCn.setText(jTableDSCN.getValueAt(row, 1).toString());
            jTextdaTn.setText(TOOL_TIP_TEXT_KEY);
            //new KeHoach(jLabelMacn.getText()).setVisible(true);
            initDataMhCn();
            loadDsMh();
            // loadDsMhChon();
            listMhChon.removeAll();
            KichHoatButtonKhiChon();
            jButtonThem.setEnabled(false);
            jPanelKeHoach.setVisible(true);
            jTextHsTh.setText("");
            jTextHsck.setText("");
            jTextdaTn.setText(searchDiemChuan(jLabelMacn.getText()).toString());
            
        }
    }//GEN-LAST:event_jTableDSCNMouseClicked

    private void jTableDSmhcnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDSmhcnMouseClicked
        // TODO add your handling code here:
        int row = jTableDSmhcn.getSelectedRow();
        try {
            if(row>=0)
            {
                jLabelTenMhcn.setText(jTableDSmhcn.getValueAt(row, 0).toString().toUpperCase());
                jTextHsTh.setText(jTableDSmhcn.getValueAt(row, 1).toString());
                jTextHsck.setText(jTableDSmhcn.getValueAt(row, 2).toString());
                jButtonsuamhcn.setEnabled(true);
                jButtonxoamhcn.setEnabled(true);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.toString());
        }
    }//GEN-LAST:event_jTableDSmhcnMouseClicked

    private void jTextdaTnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextdaTnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextdaTnActionPerformed

    private void jButtonLichSuDiemChuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLichSuDiemChuanActionPerformed
        // TODO add your handling code here:
        new  JFrameLichSuDiemChuan(jLabelMacn.getText()).setVisible(true);
    }//GEN-LAST:event_jButtonLichSuDiemChuanActionPerformed

    private void jButtonDiemChuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDiemChuanActionPerformed
        // TODO add your handling code here:
        String sql = "insert into diemchuandoantn values(?,?,?)";
        try( 
             PreparedStatement smt = DataBaseHelper.con.prepareStatement(sql);)
        {
            smt.setString(1, jLabelMacn.getText());
            smt.setString(2, ft.format(jDatedoantotnghiep.getDate()));
            smt.setFloat(3, Float.parseFloat(jTextdaTn.getText()));
            int kt = smt.executeUpdate();
            if(kt==1)
            {
                jButtonDiemChuan.setText("Đã Lưu");
                jButtonDiemChuan.setForeground(Color.GREEN);
            }
            
            
        } catch (Exception ex) {
            if(Integer.parseInt(jTextHsTh.getText())<0)
            {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập điểm > 0.");
                jTextHsTh.grabFocus();
                return;
            }
        }
    }//GEN-LAST:event_jButtonDiemChuanActionPerformed

    private void jTextdaTnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextdaTnKeyPressed
        // TODO add your handling code here:
        jButtonDiemChuan.setText("Xác nhận");
        jButtonDiemChuan.setForeground(Color.red);
    }//GEN-LAST:event_jTextdaTnKeyPressed

    private void jTextdaTnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextdaTnFocusGained
        // TODO add your handling code here:
        //jButtonDiemChuan.setText("Xác nhận");
    }//GEN-LAST:event_jTextdaTnFocusGained

    private void jTextTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTimKiemActionPerformed

    private void jTextTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextTimKiemKeyReleased
        // TODO add your handling code here:
        timKiemCn(jTextTimKiem.getText());
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
                ChuyenNganh cn = stack.pop();  
                
                if (check.equals("Them")) {

                    xoaCn(cn.getMacn(),false);
                } 
                else if (check.equals("Sua")) {
                    suaCn(cn.getMacn(), cn.getTencn(),false);
                } 
                else if (check.equals("Xoa")) {
                    themCn(cn.getMacn(),cn.getTencn(),false);
                }

            }
            
            
        }
    }//GEN-LAST:event_jButtonKhoiPhucActionPerformed

    private void jTextTenCnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTenCnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTenCnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonDiemChuan;
    private javax.swing.JButton jButtonGo;
    private javax.swing.JButton jButtonKhoiPhuc;
    private javax.swing.JButton jButtonLamMoi;
    private javax.swing.JButton jButtonLichSuDiemChuan;
    private javax.swing.JButton jButtonSua;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JButton jButtonXoa;
    private javax.swing.JButton jButtonaddMhcn;
    private javax.swing.JButton jButtonsuamhcn;
    private javax.swing.JButton jButtonxoamhcn;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDatedoantotnghiep;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelMacn;
    private javax.swing.JLabel jLabelTenMhcn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelKeHoach;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableDSCN;
    private javax.swing.JTable jTableDSmhcn;
    private javax.swing.JTextField jTextHsTh;
    private javax.swing.JTextField jTextHsck;
    private javax.swing.JTextField jTextTenCn;
    private javax.swing.JTextField jTextTimKiem;
    private javax.swing.JTextField jTextdaTn;
    private java.awt.List listMh;
    private java.awt.List listMhChon;
    private javax.swing.JSeparator ngan;
    // End of variables declaration//GEN-END:variables
}
