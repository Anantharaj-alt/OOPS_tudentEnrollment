/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Code.dbConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class Programm extends javax.swing.JFrame {

    boolean flag = false;

    //For Validate NIc final Digit
    char NICv;

    //For Resutset And Statement
    Statement st;
    ResultSet rs;

    //For Assign Search Values
    String Name, Course, Duration, Level, Incharge;
    int RegNo;

    //For Add row to the table
    DefaultTableModel dtm;

    /**
     * Creates new form Programm
     */
    public Programm() {
        initComponents();
        getCurrentDate();
        getCurrentTime();
        autogen();

        //setSize(jPanel2.getWidth(), 200);
        jPanel3.setVisible(false);

        dtm = (DefaultTableModel) tblStudent.getModel();
    }

    public void getCurrentDate() {
        Date d = new Date();//create Date Object
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");//create SimpleDateFormat Object
        String date = sdf1.format(d);//assign current date to String variable "date"
        lblDate.setText(date);//set current date to label
    }

    public void getCurrentTime() {
        //create Timer object
        Timer t = new javax.swing.Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss a");//create SimpleDateFormat object
                Date d = new Date();//create Date Object
                String time = sdf2.format(d);//assign current time to String variable "time"
                LblTime.setText(time);//set current time to label
            }
        });
        t.start();//starts Timer Object
    }

    public void autogen() {
        try {
            Statement s = dbConnection.getConnection().createStatement();
            java.sql.ResultSet rs = s.executeQuery("SELECT MAX(RegNo) FROM Program");

            while (rs.next()) {
                int y = rs.getInt("MAX(RegNo)");

                y++;
                txtregno.setText("" + y);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void nullvalue() {
        txtname.setText("");
        txtduration.setText("");
        if (jRadioButton1.isSelected()) {
            txtIncharge.setText("Mr.Saman");
            txtlevel.setText("Under Graduate");
        } else if (jRadioButton2.isSelected()) {
            txtIncharge.setText("Mr.Kamal");
            txtlevel.setText("Post Graduate");
        } else {
            txtIncharge.setText("Mr.Nimal");
            txtlevel.setText("Certificate Program");
        }
    }

    public void valdate() {

        if ((!txtname.getText().equals("")) && (txtname.getText().length() > 1) && (txtname.getText().length() < 25)) {

            if (!txtduration.getText().equals("") && Double.parseDouble(txtduration.getText()) > 0 && Double.parseDouble(txtduration.getText()) <= 5) {
                if (!txtlevel.getText().equals("")) {

                    flag = true;

                } else {
                    JOptionPane.showMessageDialog(this, "Error! Please enter the Valid Level.", "", JOptionPane.ERROR_MESSAGE);

                }

            } else {
                JOptionPane.showMessageDialog(this, "Error! Please enter the Valid Duration 0-5.", "", JOptionPane.ERROR_MESSAGE);

            }

        } else {
            JOptionPane.showMessageDialog(this, "Error! Please enter the Valid Name.", "", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void Search() {
        try {
            st = dbConnection.getConnection().createStatement();
            rs = st.executeQuery("Select * from Program where RegNo ='" + txtregno.getText() + "'");

            if (rs.next()) {

                //SET VALUES
                Name = rs.getString("Name");
                Duration = rs.getString("Duration");
                Level = rs.getString("Level");
                Incharge = rs.getString("Incharge");

                //ASSIGN Values
                txtname.setText(Name);
                txtduration.setText(Duration);
                txtlevel.setText(Level);
                txtIncharge.setText(Incharge);
            } else {
                JOptionPane.showMessageDialog(this, "Student Not Found", "", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Searched Student Failure More Details :" + e);

        }
    }

    public void Update() {
        try {
            st = dbConnection.getConnection().createStatement();
            st.executeUpdate("Update Program set Name = '" + txtname.getText() + "',Duration = '" + txtduration.getText() + "',Level = '" + txtlevel.getText() + "',Incharge = '" + txtIncharge.getText() + "' where RegNo = '" + txtregno.getText() + "'");
            JOptionPane.showMessageDialog(this, "Program updated successfully.");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Program cannot be updated more Detail" + e);
        }

    }

    public void Delete() {
        try {
            st = dbConnection.getConnection().createStatement();
            st.executeUpdate("Delete from Program where RegNo = '" + txtregno.getText() + "'");

            txtregno.setText("");
            nullvalue();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Program Cannot be deleted, more details :" + e);
        }

    }

    public void ViewAll() {
        try {
            st = dbConnection.getConnection().createStatement();
            rs = st.executeQuery("Select * from Program");

            while (rs.next()) {

                Vector v = new Vector();

                v.add(rs.getInt("RegNo"));
                v.add(rs.getString("Name"));
                v.add(rs.getString("Duration"));
                v.add(rs.getString("Level"));
                v.add(rs.getString("Incharge"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "NO Data " + e);
        }
    }

    public void ViewByLevel(String L) {
        try {
            st = dbConnection.getConnection().createStatement();
            rs = st.executeQuery("Select * from Program where level='" + L + "'");

            while (rs.next()) {

                Vector v = new Vector();

                v.add(rs.getInt("RegNo"));
                v.add(rs.getString("Name"));
                v.add(rs.getString("Duration"));
                v.add(rs.getString("Level"));
                v.add(rs.getString("Incharge"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "NO Data " + e);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        LblTime = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtregno = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        txtduration = new javax.swing.JTextField();
        txtIncharge = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStudent = new javax.swing.JTable();
        txtlevel = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();
        btnsave = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btndelate = new javax.swing.JButton();
        btnviewstu = new javax.swing.JButton();
        btnremovetable = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        btnviewstu1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        LblTime.setBackground(new java.awt.Color(102, 102, 0));
        LblTime.setFont(new java.awt.Font("SketchFlow Print", 1, 14)); // NOI18N
        LblTime.setForeground(new java.awt.Color(102, 102, 0));

        lblDate.setBackground(new java.awt.Color(102, 102, 0));
        lblDate.setFont(new java.awt.Font("SketchFlow Print", 1, 14)); // NOI18N
        lblDate.setForeground(new java.awt.Color(102, 102, 0));

        jLabel6.setFont(new java.awt.Font("SketchFlow Print", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 0));
        jLabel6.setText("Syntax Educational Institute");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Under Graduate");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Post Graduate");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Certificate Program");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(LblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student INFO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Sitka Small", 0, 11), new java.awt.Color(102, 0, 102))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 0));
        jLabel1.setText("Program ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 0));
        jLabel2.setText("Name :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 0));
        jLabel3.setText("Duration :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 0));
        jLabel4.setText("Level :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 0));
        jLabel5.setText("Incharge :");

        txtIncharge.setEditable(false);

        tblStudent.setBackground(new java.awt.Color(255, 255, 204));
        tblStudent.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblStudent.setForeground(new java.awt.Color(102, 102, 0));
        tblStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Program ID", "Name", "Duration", "Level", "Incharge"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentMouseClicked(evt);
            }
        });
        tblStudent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblStudentKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblStudent);

        txtlevel.setEditable(false);

        btnsearch.setBackground(new java.awt.Color(102, 102, 0));
        btnsearch.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnsearch.setText("Search");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
            }
        });

        btnsave.setBackground(new java.awt.Color(102, 102, 0));
        btnsave.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnsave.setText("Save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btnupdate.setBackground(new java.awt.Color(102, 102, 0));
        btnupdate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btndelate.setBackground(new java.awt.Color(102, 102, 0));
        btndelate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btndelate.setText("Delete");
        btndelate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelateActionPerformed(evt);
            }
        });

        btnviewstu.setBackground(new java.awt.Color(102, 102, 0));
        btnviewstu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnviewstu.setText("View All ");
        btnviewstu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnviewstuActionPerformed(evt);
            }
        });

        btnremovetable.setBackground(new java.awt.Color(102, 102, 0));
        btnremovetable.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnremovetable.setText("Remove From Table");
        btnremovetable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnremovetableActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 0));
        jLabel8.setText("Level :");

        jComboBox1.setBackground(new java.awt.Color(102, 102, 0));
        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Under Graduate", "Post Graduate", "Certificate Program" }));

        btnviewstu1.setBackground(new java.awt.Color(102, 102, 0));
        btnviewstu1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnviewstu1.setText("View");
        btnviewstu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnviewstu1ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(102, 102, 0));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setText("Log out");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(102, 102, 0));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setText("Home");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(368, 368, 368)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnviewstu1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton6)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtname, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtduration, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtIncharge, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(txtregno, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnsearch))
                                            .addComponent(txtlevel))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(btnsave)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnupdate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btndelate)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnviewstu)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnremovetable)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtregno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(btnsearch))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtduration, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtlevel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIncharge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnviewstu1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsave)
                    .addComponent(btnupdate)
                    .addComponent(btndelate)
                    .addComponent(btnviewstu)
                    .addComponent(btnremovetable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        jPanel3.setVisible(true);
        nullvalue();
        autogen();


    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        jPanel3.setVisible(true);
        nullvalue();
        autogen();


    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        jPanel3.setVisible(true);
        nullvalue();
        autogen();


    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void tblStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentMouseClicked
        // TODO add your handling code here:
        String id = dtm.getValueAt(tblStudent.getSelectedRow(), 0).toString();
        txtregno.setText(id);
        Search();
    }//GEN-LAST:event_tblStudentMouseClicked

    private void tblStudentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblStudentKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblStudentKeyPressed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
        nullvalue();
        RegNo = Integer.parseInt(txtregno.getText());
        Search();
    }//GEN-LAST:event_btnsearchActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:

        valdate();
        if (flag) {

            int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to save this Program?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                try {
                    st = dbConnection.getConnection().createStatement();
                    st.executeUpdate("insert into Program values ('" + txtregno.getText() + "','" + txtname.getText() + "','" + txtduration.getText() + "','" + txtlevel.getText() + "','" + txtIncharge.getText() + "')");

                    JOptionPane.showMessageDialog(this, "Program saved successfully.");
                    nullvalue();
                    autogen();
                    flag = false;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "The Program cannot be saved. More details: " + e, "Error", JOptionPane.ERROR_MESSAGE);

                }

            } else if (dialogButton == JOptionPane.NO_OPTION) {

            } else {
                nullvalue();
            }
        }

    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        valdate();
        if (flag) {
            int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to Update this Program?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {

                Update();
                txtregno.setText("");
                nullvalue();
                flag = false;

            } else if (dialogResult == JOptionPane.NO_OPTION) {
                //Nothimg
            } else {

                nullvalue();
            }
        } else {

        }

    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndelateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelateActionPerformed
        // TODO add your handling code here:
        valdate();
        if (flag) {
            int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to Delete this Program?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {

                Delete();
                flag = false;
                JOptionPane.showMessageDialog(this, "Program deleted successfully.");

            } else if (dialogResult == JOptionPane.NO_OPTION) {
                //NOthing
            } else {
                txtregno.setText("");
                nullvalue();
            }
        }

    }//GEN-LAST:event_btndelateActionPerformed

    private void btnviewstuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnviewstuActionPerformed
        // TODO add your handling code here:
        //Null The Table First

        dtm.setRowCount(0);

        //viewing
        ViewAll();
        //

    }//GEN-LAST:event_btnviewstuActionPerformed

    private void btnremovetableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremovetableActionPerformed
        // TODO add your handling code here:
        dtm.setRowCount(0);
        txtregno.setText("");
        nullvalue();
    }//GEN-LAST:event_btnremovetableActionPerformed

    private void btnviewstu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnviewstu1ActionPerformed
        dtm.setRowCount(0);

        if (jComboBox1.getSelectedIndex() == 0) {

            ViewByLevel("" + jComboBox1.getSelectedItem());
        } else if (jComboBox1.getSelectedIndex() == 1) {

            ViewByLevel("" + jComboBox1.getSelectedItem());
        } else {

            ViewByLevel("" + jComboBox1.getSelectedItem());
        }

    }//GEN-LAST:event_btnviewstu1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        new MainMenu().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to Log-out from the system?", "Warning", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION) {
            try {

                new Login().setVisible(true);

                this.dispose();
            } catch (Exception ex) {
            }

        }

    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Programm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Programm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Programm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Programm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Programm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblTime;
    private javax.swing.JButton btndelate;
    private javax.swing.JButton btnremovetable;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton btnviewstu;
    private javax.swing.JButton btnviewstu1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JTable tblStudent;
    private javax.swing.JTextField txtIncharge;
    private javax.swing.JTextField txtduration;
    private javax.swing.JTextField txtlevel;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtregno;
    // End of variables declaration//GEN-END:variables
}
