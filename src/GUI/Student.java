/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Code.dbConnection;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Abu Da Best
 */
public class Student extends javax.swing.JFrame {

    //For Validate;
    boolean flag = false;

    //For Validate NIc final Digit
    char NICv;

    //For Resutset And Statement
    Statement st;
    ResultSet rs;

    //For Assign Search Values
    String Name, Course, NIC;
    int Mobile, RegNo;

    //For Add row to the table
    DefaultTableModel dtm;

    /**
     * Creates new form Home
     */
    public Student() {
        initComponents();
        getCurrentDate();
        getCurrentTime();
        autogen();
        //setSize(jPanel2.getWidth(), 200);
        jPanel2.setVisible(false);

        dtm = (DefaultTableModel) tblStudent.getModel();

    }

    public String SelectLevel() {
        if (jRadioButton1.isSelected()) {
            return "Under Graduate";
        } else if (jRadioButton2.isSelected()) {
            return "Post Graduate";
        } else {
            return "Certificate Program";
        }

    }

    public boolean ValidateNIC() {
        if (txtnic.getText().length() == 10) {
            if (txtnic.getText().charAt(9) == 'V') {
                return true;
            } else {
                return false;
            }
        } else if (txtnic.getText().length() == 12) {
            return true;

        } else {
            return false;
        }

    }

    private void STLoadCombo(String L) {
        try {

            st = dbConnection.getConnection().createStatement();
            rs = st.executeQuery("Select Name from Program Where level = '" + L + "'");

            Vector v = new Vector();
            //v.add("");
            while (rs.next()) {

                v.add(rs.getString("Name"));
            }
            txtadd.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            e.printStackTrace();
        }
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
            java.sql.ResultSet rs = s.executeQuery("SELECT MAX(RegNo) FROM Students");

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
        txtnic.setText("");
        txtadd.setSelectedIndex(0);
        txtmobile.setText("");
    }

    public void valdate() {

        if ((!txtname.getText().equals("")) && (txtname.getText().length() > 2) && (txtname.getText().length() < 25)) {

            if ((ValidateNIC())) {
                if (txtadd.getSelectedIndex() >= 0) {

                    if (txtmobile.getText().length() == 10) {
                        flag = true;
                    } else {
                        JOptionPane.showMessageDialog(this, "Error! Please enter the Valid Mobile Number.", "", JOptionPane.ERROR_MESSAGE);

                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Error! Please enter the Valid Course.", "", JOptionPane.ERROR_MESSAGE);

                }

            } else {
                JOptionPane.showMessageDialog(this, "Error! Please enter the Valid NIC.", "", JOptionPane.ERROR_MESSAGE);

            }

        } else {
            JOptionPane.showMessageDialog(this, "Error! Please enter the Valid Name.", "", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void Search() {
        try {
            st = dbConnection.getConnection().createStatement();
            rs = st.executeQuery("Select * from Students where RegNo ='" + txtregno.getText() + "'");

            if (rs.next()) {

                //SET VALUES
                Name = rs.getString("Name");
                NIC = rs.getString("NIC");
                Course = rs.getString("Course");
                Mobile = rs.getInt("Mobile");

                //ASSIGN Values
                txtname.setText(Name);
                txtnic.setText(NIC);
                txtadd.setSelectedItem(Course);
                txtmobile.setText("0" + Mobile);
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
            st.executeUpdate("Update Students set Name = '" + txtname.getText() + "',NIC = '" + txtnic.getText() + "',Course = '" + txtadd.getSelectedItem() + "',Mobile = '" + txtmobile.getText() + "' where RegNo = '" + txtregno.getText() + "'");
            JOptionPane.showMessageDialog(this, "Student updated successfully.");
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Student cannot be updated more Detail" + e);
        }

    }

    public void Delete() {
        try {
            st = dbConnection.getConnection().createStatement();
            st.executeUpdate("Delete from Students where RegNo = '" + txtregno.getText() + "'");

            txtregno.setText("");
            nullvalue();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Student Cannot be deleted, more details :" + e);
        }

    }

    public void ViewAll() {
        try {
            st = dbConnection.getConnection().createStatement();
            rs = st.executeQuery("Select * from Students");

            while (rs.next()) {

                Vector v = new Vector();

                v.add(rs.getInt("RegNo"));
                v.add(rs.getString("Name"));
                v.add(rs.getString("NIC"));
                v.add(rs.getString("Course"));
                v.add(rs.getInt("Mobile"));

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtregno = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        txtnic = new javax.swing.JTextField();
        txtmobile = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStudent = new javax.swing.JTable();
        btnsave = new javax.swing.JButton();
        btnsearch = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btndelate = new javax.swing.JButton();
        btnviewstu = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btnremovetable = new javax.swing.JButton();
        txtadd = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        LblTime = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Student INFO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Sitka Small", 0, 11), new java.awt.Color(102, 0, 102))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 0));
        jLabel1.setText("Reg NO :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 0));
        jLabel2.setText("Name :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 0));
        jLabel3.setText("NIC :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 0));
        jLabel4.setText("Course :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 0));
        jLabel5.setText("Mobile :");

        txtnic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnicKeyTyped(evt);
            }
        });

        txtmobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtmobileKeyTyped(evt);
            }
        });

        tblStudent.setBackground(new java.awt.Color(255, 255, 204));
        tblStudent.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        tblStudent.setForeground(new java.awt.Color(102, 102, 0));
        tblStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reg No", "Name", "NIC", "Course", "Mobile No"
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

        btnsave.setBackground(new java.awt.Color(102, 102, 0));
        btnsave.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnsave.setText("Save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btnsearch.setBackground(new java.awt.Color(102, 102, 0));
        btnsearch.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnsearch.setText("Search");
        btnsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsearchActionPerformed(evt);
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
        btnviewstu.setText("View All Students");
        btnviewstu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnviewstuActionPerformed(evt);
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

        jButton7.setBackground(new java.awt.Color(102, 102, 0));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setText("Log out");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
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

        txtadd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BSC", "MSC.PHD", "DIP" }));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 0));
        jLabel7.setText("123456789V or 123456789123");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmobile, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtregno, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnsearch))
                    .addComponent(txtnic, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtadd, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(btnviewstu)
                        .addGap(10, 10, 10)
                        .addComponent(btnremovetable))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(btnsave)
                .addGap(18, 18, 18)
                .addComponent(btnupdate)
                .addGap(18, 18, 18)
                .addComponent(btndelate))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(568, 568, 568)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton7))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnviewstu)
                            .addComponent(btnremovetable)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnsearch)
                                    .addComponent(txtregno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtnic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtadd, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel2)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel3)
                                .addGap(75, 75, 75)))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtmobile, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnsave)
                    .addComponent(btnupdate)
                    .addComponent(btndelate))
                .addGap(59, 59, 59)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6)
                    .addComponent(jButton7)))
        );

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(LblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentMouseClicked
        // TODO add your handling code here:
        String id = dtm.getValueAt(tblStudent.getSelectedRow(), 0).toString();
        txtregno.setText(id);
        Search();

    }//GEN-LAST:event_tblStudentMouseClicked

    private void tblStudentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblStudentKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tblStudentKeyPressed

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

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:

        valdate();
        if (flag) {

            int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to save this Student?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                try {
                    st = dbConnection.getConnection().createStatement();
                    st.executeUpdate("insert into students values ('" + txtregno.getText() + "','" + txtname.getText() + "','" + txtnic.getText() + "','" + txtadd.getSelectedItem() + "','" + txtmobile.getText() + "','" + SelectLevel() + "')");

                    JOptionPane.showMessageDialog(this, "Student saved successfully.");
                    nullvalue();
                    autogen();
                    flag = false;

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(rootPane, "The Customer cannot be saved. More details: " + e, "Error", JOptionPane.ERROR_MESSAGE);

                }

            } else if (dialogButton == JOptionPane.NO_OPTION) {

            } else {
                nullvalue();
            }
        }


    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsearchActionPerformed
        // TODO add your handling code here:
        nullvalue();
        RegNo = Integer.parseInt(txtregno.getText());
        Search();

    }//GEN-LAST:event_btnsearchActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        valdate();
        if (flag) {
            int dialogButton = JOptionPane.YES_NO_CANCEL_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to Update this Student?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {

                Update();
                txtregno.setText("");
                nullvalue();
                flag=false;

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
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to Delete this Student?", "Warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {

                Delete();
                flag =false;
                JOptionPane.showMessageDialog(this, "Student deleted successfully.");

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
        nullvalue();
    }//GEN-LAST:event_btnremovetableActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        jPanel2.setVisible(true);
        nullvalue();
        autogen();
        STLoadCombo("Under Graduate");

    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        jPanel2.setVisible(true);
        nullvalue();
        autogen();
        STLoadCombo("Post Graduate");
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        jPanel2.setVisible(true);
        nullvalue();
        autogen();
        STLoadCombo("Certificate Program");
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void txtmobileKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmobileKeyTyped
        char c = evt.getKeyChar();
        if (!(c >= '0' && c <= '9')) {
            if (!(c == KeyEvent.VK_BACK_SPACE && c == KeyEvent.VK_DELETE) && c != KeyEvent.VK_SPACE) {
                evt.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }//GEN-LAST:event_txtmobileKeyTyped

    private void txtnicKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnicKeyTyped
        char c = evt.getKeyChar();
        if (!(c >= '0' && c <= '9')) {
            if (!(c == KeyEvent.VK_BACK_SPACE && c == KeyEvent.VK_DELETE) && c != KeyEvent.VK_SPACE && c != KeyEvent.VK_V) {
                evt.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }//GEN-LAST:event_txtnicKeyTyped

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
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Student.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Student().setVisible(true);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JTable tblStudent;
    private javax.swing.JComboBox txtadd;
    private javax.swing.JTextField txtmobile;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtnic;
    private javax.swing.JTextField txtregno;
    // End of variables declaration//GEN-END:variables
}
