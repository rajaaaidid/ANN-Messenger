import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SealedObject;
import javax.swing.text.BadLocationException;
import org.apache.commons.io.input.CountingInputStream;

public class OutSocket extends Thread{
    protected int dfcount;
    private Interface func = new Interface();
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Object recieved;
    private String recievedString;
    private Message recievedMessage;
    private Information information = new Information();
    private OutHandler outHandler = new OutHandler();

    OutSocket(Socket s) {
        socket = s;
        this.start();
    }
    
    @Override
    public void run(){
        try {
            func.showInformation("Connection to server is sucessfully established...");
            //OUTPUT STREAM
            output = new ObjectOutputStream( new BufferedOutputStream(socket.getOutputStream()) );
            func.getListOfSocketOutput().add(output);
            output.flush();
            //END OF OUTPUT STREAM DECLARATION
            //INPUT STREAM
            input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            //END OF INPUT STREAM DECLARATION
            runOutInterface();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void runOutInterface() throws IOException{
        Information information = new Information();
        information.serializeInfo();
        Message initialize = new Message();
        initialize.setType(0);
        initialize.setMainObject(information);
        func.sendMessage(initialize, output);
        while(true){
            try{
                recieved = input.readObject();
            }catch(java.net.SocketException e){
                func.showInformationToChat("Connection to the server has been terminated..");
                func.showInformationToChat("Reconnecting...");
                func.getListOfSocketOutput().clear();
                new MasterSocket(socket.getInetAddress().getHostAddress()).start();
                break;
            }catch(ClassNotFoundException ex) {
                ex.printStackTrace();
            }catch(InvalidClassException ex){
                ex.printStackTrace();
            }
            try {
                runOutAction(recieved);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void runOutAction(Object recieved) throws BadLocationException{
        try{
            if(recieved instanceof String){
                func.showMessage((String)recieved, true);
            }else if(recieved instanceof Message){
                runOutActionMessage(recieved);
            }else if(recieved instanceof SealedObject){
                runOutActionSealedObjectServer(recieved);
            }
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
    }
    
    private void runOutActionMessage(Object recieved){
        Message m = (Message) recieved;
        if(m.getType()==0){
            Information i = (Information) m.getMainObject();
            System.out.println(i.getNonStaticSerialID()+" "+i.getNonStaticName());
            if(!i.getNonStaticSerialID().equals(i.getSerialID())){
                func.showInformationToChat("Connection to "+i.getNonStaticSerialID()+" "+i.getNonStaticName()+" is established");
                func.showInformationToChat(i.getNonStaticName()+" is online");
            }
            outHandler.getConnectedInformationList().add(i);
        }else if(m.getType()==100){
            Information i = (Information) m.getMainObject();
            outHandler.setServerInformation(i);
            if(i.isNonStaticServerEncryptionType()){
                func.showInformation("Connection to the server is secured and encrypted...");
            }else{
                func.showWarning("Connection to the server is NOT secured and encrypted...");
            }
        }else if(m.getType()==7){
            String text = m.getText();
            String fromSerialID = m.getSerialID();
            String toSerialID = m.getToSerialID();
            
            if(toSerialID.equals(information.getSerialID())){
                try {
                    func.showDirectMessage(fromSerialID+" >> "+toSerialID+" "+text, true);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }else if(fromSerialID.equals(information.getSerialID())){
                try {
                    func.showDirectMessage(fromSerialID+" >> "+toSerialID+" "+text, true);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        }else if(m.getType()==8){
            String fromSerialID = m.getSerialID();
            String toSerialID = m.getToSerialID();
            
            if(toSerialID.equals(information.getSerialID())||fromSerialID.equals(information.getSerialID())){
                try {
                    runOutActionSealedObjectDirect(m.getMainObject(), fromSerialID);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        }else if(m.getType()==9){
            String SerialID = m.getSerialID();
            int index = outHandler.getIndexBySerialID(SerialID);
            func.showInformationToChat(outHandler.getConnectedInformationList().get(index).getNonStaticName()+" is offline");
            outHandler.getConnectedInformationList().remove(index);
        }
    }
    
    private void runOutActionSealedObjectServer(Object m) throws BadLocationException{
        Object o = null;
        SealedObject so = (SealedObject) m;
        o = func.recieveEncryptedMessage(so, outHandler.getServerInformation().getNonStaticEncryptionCode());
        runOutAction(o);
    }
    
    private void runOutActionSealedObjectDirect(Object m, String id) throws BadLocationException{
        Object o = null;
        SealedObject so = (SealedObject) m;
        o = func.recieveEncryptedMessage(so, outHandler.getConnectedInformationList().get(outHandler.getIndexBySerialID(id)).getNonStaticEncryptionCode());
        runOutAction(o);
    }
    
    public ObjectOutputStream getOutput(){
        return output;
    }
    
}
