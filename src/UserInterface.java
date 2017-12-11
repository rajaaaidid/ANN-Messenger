import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UserInterface extends javax.swing.JFrame {
    
    static private MasterSocket masterSocket;
    static private MasterServerSocket masterServerSocket;
    private Interface func = new Interface();
    private Information info = new Information();
    private UserInput userInput = new UserInput();

    public UserInterface() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameTextField = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        hostServer = new javax.swing.JButton();
        connect = new javax.swing.JButton();
        ipAddressTextField = new javax.swing.JTextField();
        ipAddressLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ANN Messenger");
        setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        setLocation(new java.awt.Point(10, 10));
        setLocationByPlatform(true);

        usernameTextField.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        usernameTextField.setText("<Default>");

        usernameLabel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        usernameLabel.setLabelFor(usernameTextField);
        usernameLabel.setText("Username");

        hostServer.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        hostServer.setText("Host Server");
        hostServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hostServerActionPerformed(evt);
            }
        });

        connect.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        connect.setText("Connect");
        connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectActionPerformed(evt);
            }
        });

        ipAddressTextField.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N

        ipAddressLabel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        ipAddressLabel.setLabelFor(usernameTextField);
        ipAddressLabel.setText("IP Address");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ipAddressLabel)
                                .addGap(16, 16, 16))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(usernameLabel)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ipAddressTextField)
                            .addComponent(usernameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(connect, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hostServer, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ipAddressLabel))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hostServer)
                    .addComponent(connect))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectActionPerformed
        info.setName(usernameTextField.getText());
        masterSocket =  new MasterSocket(ipAddressTextField.getText());
        masterSocket.start();
        userInput.runUserInput();
        this.dispose();
    }//GEN-LAST:event_connectActionPerformed

    private void hostServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hostServerActionPerformed
        info.setName(usernameTextField.getText());
        masterServerSocket =  new MasterServerSocket();
        masterServerSocket.start();
        masterSocket =  new MasterSocket("127.0.0.1");
        masterSocket.start();
        userInput.runUserInput();
        this.dispose();
    }//GEN-LAST:event_hostServerActionPerformed

    /**
     * @param args the command line arguments
     */
    public void runGUI(){
        
        info.setSerialID(func.generateNumbers(10));
        System.out.println("Serial ID: "+info.getSerialID());
        info.setEncryptionCode(func.generateCode(16));
        setLookAndFeel();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
    }
    
    private void setLookAndFeel(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connect;
    private javax.swing.JButton hostServer;
    private javax.swing.JLabel ipAddressLabel;
    private javax.swing.JTextField ipAddressTextField;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
