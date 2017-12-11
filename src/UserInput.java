
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class UserInput extends javax.swing.JFrame {
    
    private Interface func = new Interface();
    private Information info = new Information();
    private OutHandler outHandler = new OutHandler();
    
    private boolean shift = false;

    public UserInput() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userTextScrollPane = new javax.swing.JScrollPane();
        userText = new javax.swing.JTextArea();
        chatWindowScrollPane = new javax.swing.JScrollPane();
        chatWindow = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        userTextScrollPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        userText.setColumns(20);
        userText.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        userText.setLineWrap(true);
        userText.setRows(5);
        userText.setBorder(null);
        userText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userTextKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                userTextKeyReleased(evt);
            }
        });
        userTextScrollPane.setViewportView(userText);

        chatWindowScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        chatWindow.setEditable(false);
        chatWindowPostInitCode();
        chatWindowScrollPane.setViewportView(chatWindow);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userTextScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(chatWindowScrollPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatWindowScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userTextScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userTextKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
            shift = true;
        }
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(!shift){
                evt.consume();
                try{
                    runInOutAction();
                }catch(Exception e) { e.printStackTrace();}
                userText.setEditable(false);
                userText.setText("");
                userText.setEditable(true);
            }else{
                userText.insert("\n", userText.getCaretPosition());
            }
        }
    }//GEN-LAST:event_userTextKeyPressed

    private void userTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userTextKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
            shift = false;
        }
    }//GEN-LAST:event_userTextKeyReleased


    public void runUserInput() {
        setLookAndFeel();
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserInput().setVisible(true);
            }
        });
    }
    
    private void chatWindowPostInitCode(){
        func.setChatWindowInsert(chatWindow.getStyledDocument());
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
    
    private void runInOutAction() throws IOException{
        new CommandHandler(userText.getText());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane chatWindow;
    private javax.swing.JScrollPane chatWindowScrollPane;
    private javax.swing.JTextArea userText;
    private javax.swing.JScrollPane userTextScrollPane;
    // End of variables declaration//GEN-END:variables
}
