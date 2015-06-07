/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coffeecia.java.UI;

import coffeecia.java.CoffeeCIA_MAIN;
import coffeecia.java.tools.Ticket;
import coffeecia.java.tools.TicketDB_File;
import coffeecia.java.tools.XMLTitleList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author alatnet
 */
public class MainFrame extends javax.swing.JFrame {
    final JFileChooser fc = new JFileChooser();
    private TicketDB_File ticketDBFile;
    private TitlesTableModel tTableModel = new TitlesTableModel();
    private boolean ready = false;
    private XMLTitleList xmlTitles = null;
    /**
     * Creates new form mainFram
     */
    public MainFrame() {
        initComponents();
        
        //logging
        Logger.getGlobal().addHandler(new Handler() {

            @Override
            public void publish(LogRecord record) {
                txtAreaLog.append(record.getLevel() + ": " + record.getMessage() + "\n");
                txtAreaLog.setCaretPosition(txtAreaLog.getText().length());
            }

            @Override
            public void flush() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void close() throws SecurityException {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        Logger.getGlobal().log(Level.INFO, "Logging Initialized.");
        
        File titleXML = new File(CoffeeCIA_MAIN.titleXML);
        if (titleXML.exists()){
            lblTicketStatus.setText("Reading Title List from XML File.");
            Thread xmlThread = new Thread(() -> {
                xmlTitles = new XMLTitleList(titleXML);
                try {
                    xmlTitles.open();
                    tTableModel.setXMLTitles(xmlTitles);
                } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                tTableModel = new TitlesTableModel(xmlTitles);
                tblTitles.setModel(tTableModel);
                
                lblTicketStatus.setText("Ready");
                this.ready = true;
            });
            xmlThread.start();
        }else ready = true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaLog = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_numTick = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_dupTick = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_uniqueTick = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_sysTick = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_numETick = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbl_dupETick = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbl_uniqueETick = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbl_notETick = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_openFile = new javax.swing.JButton();
        lblTicketStatus = new javax.swing.JLabel();
        progTicketDB = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTitles = new javax.swing.JTable();
        btnDownload = new javax.swing.JButton();
        btnSelectFilter = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CoffeeCIA");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Log"));

        txtAreaLog.setColumns(20);
        txtAreaLog.setRows(5);
        jScrollPane1.setViewportView(txtAreaLog);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Ticket Info"));

        jLabel1.setText("Number of Tickets:");

        lbl_numTick.setText("0");

        jLabel3.setText("Number of Titles with Duplicates:");

        lbl_dupTick.setText("0");

        jLabel5.setText("Number of Unique Title Tickets:");

        lbl_uniqueTick.setText("0");

        jLabel7.setText("Number of System Tickets (ignored):");

        lbl_sysTick.setText("0");

        jLabel9.setText("Number of eShop Tickets:");

        lbl_numETick.setText("0");

        jLabel11.setText("Number of eShop Tickets with Duplicates:");

        lbl_dupETick.setText("0");

        jLabel13.setText("Number of Unique eShop Title Tickets:");

        lbl_uniqueETick.setText("0");

        jLabel15.setText("Number of (non system) Tickets NOT from your eShop:");

        lbl_notETick.setText("0");

        jLabel17.setText("^^ can be installed CIAs, preinstalled games/apps etc... things not from YOUR eshop");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_numTick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_dupTick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_uniqueTick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_sysTick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_numETick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_dupETick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_uniqueETick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_notETick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbl_numTick))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbl_dupTick))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbl_uniqueTick))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbl_sysTick))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lbl_numETick))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lbl_dupETick))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lbl_uniqueETick))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lbl_notETick))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ticket.db"));

        btn_openFile.setText("Open File");
        btn_openFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        lblTicketStatus.setText("Ready");

        progTicketDB.setStringPainted(true);

        jLabel2.setText("Status:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_openFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTicketStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(progTicketDB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_openFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTicketStatus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progTicketDB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel2);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Titles"));

        tblTitles.setModel(tTableModel);
        jScrollPane2.setViewportView(tblTitles);

        btnDownload.setText("Download Selected");
        btnDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        btnSelectFilter.setText("Select Filter");
        btnSelectFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btnSelectFilter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDownload))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDownload)
                    .addComponent(btnSelectFilter))
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        if (!ready) return;
        switch(evt.getActionCommand()){
            case "Open File":
                if (!ticketFileOpen) if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)  this.openFile(fc.getSelectedFile());
                break;
            case "Select Filter":
                break;
            case "Download Selected":
                tTableModel.execute();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_buttonActionPerformed

    //open the file and load the ticket database.
    private boolean openFileDone = false;
    private boolean ticketFileOpen = false;
    private int duplicateTicks = 0;
    private int duplicateETicks = 0;
    private int sysTicks = 0;
    private void openFile(File file){
        tTableModel.clear();
        lbl_numTick.setText("0");
        lbl_dupTick.setText("0");
        lbl_uniqueTick.setText("0");
        lbl_sysTick.setText("0");
        lbl_numETick.setText("0");
        lbl_dupETick.setText("0");
        lbl_uniqueETick.setText("0");
        lbl_notETick.setText("0");
            
        Logger.getGlobal().log(Level.INFO, "Prepping to open ticket database...");
        this.ticketFileOpen = true;
        this.ticketDBFile = new TicketDB_File(file);
        final Object sync=new Object();
        
        //this runnable's only task is to handle the ticket database loading and parsing.
        Runnable worker = () -> {
            synchronized(sync){ openFileDone = false; }
            Logger.getGlobal().log(Level.INFO, "Opening ticket database...");
            ticketDBFile.open();
            Logger.getGlobal().log(Level.INFO, "Ticket database parsed.");
            synchronized(sync){ openFileDone = true; }
            ticketFileOpen = false;
            lblTicketStatus.setText("Ticket Database Parsed.");
            //tTableModel.fireTableDataChanged();
        };
        
        //this runnable is dealing with updating the progress of the ticket database loading.
        Runnable ui_worker = () -> {
            lbl_numTick.setText("0");
            lblTicketStatus.setText("Reading Ticket Database - 0/"+ticketDBFile.getSize() + " Bytes read.");
            while (ticketDBFile.getSize() == -1){ Thread.yield(); }
            
            progTicketDB.setMinimum(0);
            progTicketDB.setMaximum((int) ticketDBFile.getSize());
            
            lblTicketStatus.setText("Reading Ticket Database - 0/"+ticketDBFile.getSize() + " Bytes read.");
            
            int numTicks = ticketDBFile.numTickets();
            int pos = 0;
            while (true){
                synchronized(sync){ if (openFileDone) break; }
                if (progTicketDB.getValue() != ticketDBFile.getPos()){
                    pos = (int) ticketDBFile.getPos();
                    progTicketDB.setValue(pos);
                    lblTicketStatus.setText("Reading Ticket Database - " + pos + "/" + ticketDBFile.getSize() + " Bytes read.");
                    if (numTicks != ticketDBFile.numTickets()){
                        numTicks = ticketDBFile.numTickets();
                        lbl_numTick.setText(Integer.toString(numTicks));
                    }
                }
                Thread.yield();
            }
            openFileDone = true;
            
            Logger.getGlobal().log(Level.INFO, "Listing tickets...");
            ArrayList<Ticket> tickets = ticketDBFile.getTickets();
            HashMap<String,Integer> X = new HashMap<>();
            HashMap<String,Integer> X2 = new HashMap<>();
            ArrayList<String> eshop = new ArrayList<>();
            ArrayList<String> not_eshop = new ArrayList<>();
            
            tickets.stream().forEach((t) -> {
                tTableModel.addEntry(t);
                
                X.put(t.titleIDStr, X.getOrDefault(t.titleIDStr, 0)+1);
                
                switch (t.type){
                    case System:
                    case DSiSystemApp:
                    case DSiSystemDataArchives:
                        sysTicks++;
                        break;
                }
                
                if (t.commonkeyIndex == 0){
                    eshop.add(t.titleIDStr);
                    X2.put(t.titleIDStr, X2.getOrDefault(t.titleIDStr, 0)+1);
                    if (t.consoleIDStr.equals("00000000")) not_eshop.add(t.titleIDStr);
                }
            }); //update the title table
            
            X.forEach((id,num) ->{ if (num>1) duplicateTicks++; });
            X2.forEach((id,num) ->{ if (num>1) duplicateETicks++; });
            
            lbl_dupTick.setText(Integer.toString(duplicateTicks));
            lbl_uniqueTick.setText(Integer.toString(X.size()));
            lbl_sysTick.setText(Integer.toString(sysTicks));
            lbl_numETick.setText(Integer.toString(eshop.size()));
            lbl_dupETick.setText(Integer.toString(duplicateETicks));
            lbl_uniqueETick.setText(Integer.toString(X2.size()));
            lbl_notETick.setText(Integer.toString(not_eshop.size()));
        };
        
        new Thread(ui_worker).start();
        new Thread(worker).start();
    }
    
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDownload;
    private javax.swing.JButton btnSelectFilter;
    private javax.swing.JButton btn_openFile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblTicketStatus;
    private javax.swing.JLabel lbl_dupETick;
    private javax.swing.JLabel lbl_dupTick;
    private javax.swing.JLabel lbl_notETick;
    private javax.swing.JLabel lbl_numETick;
    private javax.swing.JLabel lbl_numTick;
    private javax.swing.JLabel lbl_sysTick;
    private javax.swing.JLabel lbl_uniqueETick;
    private javax.swing.JLabel lbl_uniqueTick;
    private javax.swing.JProgressBar progTicketDB;
    private javax.swing.JTable tblTitles;
    private javax.swing.JTextArea txtAreaLog;
    // End of variables declaration//GEN-END:variables
}