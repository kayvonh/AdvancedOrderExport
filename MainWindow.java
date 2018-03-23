
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

public class MainWindow extends javax.swing.JFrame {

    String csvFilePath, outputFilePath, templateFilePath;       // absolute directory locations

    public MainWindow() {

        ArrayList<customer> customerList = new ArrayList<>();       // arraylist of unshipped customers
        initComponents();
        //Create for the CSV format option. 
        ButtonGroup storeList = new ButtonGroup();
        jWooCommerceButton.setSelected(true);
        storeList.add(jShopifyButton);
        storeList.add(jWooCommerceButton);
        storeList.add(jBigCartelButton);
        // Create ButtonGroup for the filename option.
        ButtonGroup fileNameGroup = new ButtonGroup();
        jButtonOrderNumber.setSelected(true);
        fileNameGroup.add(jButtonTime);
        fileNameGroup.add(jButtonName);
        fileNameGroup.add(jButtonOrderNumber);

    }

    public void PrintEnvelope(String fileName, customer tempCustomer) throws FileNotFoundException, IOException {
        try {
            // Open both the input template file and the output template file. 
            File envelope = new File(templateFilePath);
            File outEnvelope = new File(outputFilePath + "\\" + fileName + ".rtf");
            BufferedReader r = new BufferedReader(new FileReader(envelope));
            BufferedWriter w = new BufferedWriter(new FileWriter(outEnvelope));

            String currentLine; //current line of the template.rtf

            //allows us to easily iterate through our search terms. 
            String identifiers[] = {"#NAME", "#ADDRESS1", "#ADDRESS2", "#CITY", "#COUNTRY"};
            int currentIdentifier = 0;

            // Process: 
            // - Read each line of the input file
            // - Search for the current identifier (i.e. #NAME)
            //      If not in the line, paste the line in its entirety to the output file.
            //      If it is in the line, replace the identifier with the appropriate string from tempCustomer.
            // - Complete until we reach the EOF.
            // - Close file.
            while ((currentLine = r.readLine()) != null) {
                if (currentIdentifier < 5 && currentLine.contains(identifiers[currentIdentifier])) {
                    String tempString = currentLine.substring(0, currentLine.indexOf(identifiers[currentIdentifier]));
                    tempString += tempCustomer.printIdentifier(currentIdentifier);
                    tempString += currentLine.substring(currentLine.indexOf(identifiers[currentIdentifier]) + identifiers[currentIdentifier].length());

                    // If our printIdentifier is null, we just delete the line entirely. 
                    // For this to make any sense, consider an address with no secondary address.
                    // If we replace the identifier with our secondary address, we'd get an empty line.
                    // So, instead, we just delete the whole line and it works out fine. 
                    if (tempCustomer.printIdentifier(currentIdentifier) != null) {
                        w.write(tempString);
                    }
                    currentIdentifier++;
                } else {
                    w.write(currentLine);
                }
            }
            w.close();
            r.close();
        } catch (Exception e) {
            // Take care of passing errors to the calling function.
            if (e instanceof FileNotFoundException) {
                throw new FileNotFoundException();
            } else {
                throw new IOException();
            }
        }
    }
// Takes the global variables for the CSV file location, opens file, processes all members into customers. 
// storeVersion - The output of storeList.getSelected().toString().
// I.E, the store selected under "Settings".

    public ArrayList<customer> processCSV(String storeVersion) throws FileNotFoundException, IOException {
        ArrayList<customer> customerList = new ArrayList<>();

        if (storeVersion.compareTo("BigCartel") == 0) {
            File f;
            BufferedReader b;
            try {
                // open our files and set up a reader...
                f = new File(csvFilePath);
                b = new BufferedReader(new FileReader(f));
                String curLine;  // current line of the CSV, iterates 
                b.readLine();    //dump the very first line as this is the header to our file. 

                //iterate  through the file line by line until we find the EOF
                while ((curLine = b.readLine()) != null) {
                    customer temp = new customer();
                    temp.fromBigCartel(curLine);
                    if (!temp.shippedStatus) {  // Only add to our list if they're unshipped. 
                        jDebugConsole.setText(jDebugConsole.getText() + temp.orderNumber + "\n");
                        customerList.add(temp);
                    }
                }
                b.close();
            } catch (Exception e) {     //handle errors, pass to calling function 
                if (e instanceof FileNotFoundException) {
                    throw new FileNotFoundException();
                } else {
                    throw new IOException();
                }
            }
        } else if (storeVersion.compareTo("Shopify") == 0) {
            File f;
            BufferedReader b;
            try {
                // open our files and set up a reader...
                f = new File(csvFilePath);
                b = new BufferedReader(new FileReader(f));
                String curLine;  // current line of the CSV, iterates 
                b.readLine();    //dump the very first line as this is the header to our file. 

                //iterate  through the file line by line until we find the EOF
                while ((curLine = b.readLine()) != null) {
                    customer temp = new customer();
                    temp.fromShopify(curLine);
                    if (!temp.shippedStatus) {  // Only add to our list if they're unshipped. 
                        jDebugConsole.setText(jDebugConsole.getText() + temp.orderNumber + "\n");
                        customerList.add(temp);
                    }
                }
                b.close();
            } catch (Exception e) {     //handle errors, pass to calling function 
                if (e instanceof FileNotFoundException) {
                    throw new FileNotFoundException();
                } else {
                    throw new IOException();
                }
            }
        } else if(storeVersion.compareTo("WooCommerce") == 0)
        {
            /*** Not implemented yet! ***/
        }
        return customerList;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jBigCartelButton = new javax.swing.JRadioButton();
        jShopifyButton = new javax.swing.JRadioButton();
        jWooCommerceButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextArea_CSV = new javax.swing.JTextField();
        jButton_CSV = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jEnvelopeButton = new javax.swing.JRadioButton();
        jLabelSheetButton = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jTextArea_Template = new javax.swing.JTextField();
        jButton_Template = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jButtonTime = new javax.swing.JRadioButton();
        jButtonName = new javax.swing.JRadioButton();
        jButtonOrderNumber = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDebugConsole = new javax.swing.JTextArea();
        jCheckBox4 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("Go");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(954, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(684, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Main", jPanel1);

        jBigCartelButton.setText("Bigcartel");

        jShopifyButton.setText("Shopify");

        jWooCommerceButton.setText("WooCommerce");

        jLabel1.setText("orders.csv Format");

        jLabel3.setText("orders.csv Location");

        jTextArea_CSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextArea_CSVActionPerformed(evt);
            }
        });

        jButton_CSV.setText("Browse");

        jLabel4.setText("Template Format");

        jEnvelopeButton.setText("Envelope");
        jEnvelopeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEnvelopeButtonActionPerformed(evt);
            }
        });

        jLabelSheetButton.setText("Label Sheet");

        jLabel5.setText("Template Location");

        jTextArea_Template.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextArea_TemplateActionPerformed(evt);
            }
        });

        jButton_Template.setText("Browse");

        jLabel6.setText("Output Folder Settings");

        jRadioButton1.setText("Delete old orders");

        jLabel7.setText("Filename");

        jButtonTime.setText("Time of Order");
        jButtonTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimeActionPerformed(evt);
            }
        });

        jButtonName.setText("Name Of Customer");

        jButtonOrderNumber.setText("Order Number");

        jLabel8.setText("Output Folder Location");

        jButton1.setText("Browse");

        jCheckBox1.setText("Save settings on exit?");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(532, 532, 532)
                        .addComponent(jButton_CSV))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Template)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextArea_Template, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextArea_CSV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jSeparator1)
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jBigCartelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jShopifyButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jWooCommerceButton)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jEnvelopeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSheetButton)
                        .addGap(12, 12, 12))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jSeparator2)
                .addGap(12, 12, 12))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonOrderNumber))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jWooCommerceButton)
                            .addComponent(jShopifyButton)
                            .addComponent(jBigCartelButton)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextArea_CSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_CSV)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jEnvelopeButton)
                    .addComponent(jLabelSheetButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextArea_Template, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Template))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jButtonTime)
                    .addComponent(jButtonName)
                    .addComponent(jButtonOrderNumber))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addContainerGap(419, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Settings", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1013, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 722, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Customization", jPanel3);

        jLabel2.setText("Console Log");

        jDebugConsole.setColumns(20);
        jDebugConsole.setRows(5);
        jScrollPane1.setViewportView(jDebugConsole);

        jCheckBox4.setText("Verbose Error Messages");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jCheckBox4))
                        .addGap(0, 820, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox4)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Debug", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1018, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextArea_CSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextArea_CSVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea_CSVActionPerformed

    private void jEnvelopeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEnvelopeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jEnvelopeButtonActionPerformed

    private void jTextArea_TemplateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextArea_TemplateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea_TemplateActionPerformed

    private void jButtonTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonTimeActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton jBigCartelButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JRadioButton jButtonName;
    private javax.swing.JRadioButton jButtonOrderNumber;
    private javax.swing.JRadioButton jButtonTime;
    private javax.swing.JButton jButton_CSV;
    private javax.swing.JButton jButton_Template;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JTextArea jDebugConsole;
    private javax.swing.JRadioButton jEnvelopeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jLabelSheetButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JRadioButton jShopifyButton;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextArea_CSV;
    private javax.swing.JTextField jTextArea_Template;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JRadioButton jWooCommerceButton;
    // End of variables declaration//GEN-END:variables
}
