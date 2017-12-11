
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SealedObject;

public class InSocket extends Thread{
    private Interface func = new Interface();
    private Socket inConnection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private ArrayList<ObjectOutputStream> masterOutput;
    private Object recieved;
    private String recievedString;
    private Message recievedMessage;
    private String connectedSerialID;
    InHandler inHandler = new InHandler();
    
    InSocket(Socket s, ArrayList<ObjectOutputStream> mo){
        this.inConnection = s;
        this.masterOutput = mo;
        this.start();
    }
    
    @Override
    public void run(){
        try {
            func.showServer("Session opened to "+inConnection.getInetAddress().getHostAddress()+"...");
            //OUTPUT STEAM
            output = new ObjectOutputStream( new BufferedOutputStream( inConnection.getOutputStream()));
            masterOutput.add(output);
            output.flush();
            //INPUT STREAM
            input = new ObjectInputStream( new BufferedInputStream( inConnection.getInputStream()));
            runInInterface();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                output.close();
                input.close();
                inConnection.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void runInInterface() throws IOException, ClassNotFoundException{
        Information information = new Information();
        information.serializeInfo();
        Message initialize = new Message();
        initialize.setType(100);
        initialize.setMainObject(information);
        func.sendMessage(initialize, output);
        while(true){
            try{
                recieved = input.readObject();
            }catch(java.net.SocketException e){
                func.showServer("Session "+inConnection.getInetAddress().getHostAddress() +" was terminated");
                runInCloseDown();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                break;
            }
            runInAction(recieved);
        }
    }
    
    private void runInAction(Object recieved){
        if(recieved instanceof String){
            try {
                func.sendAllMessage(recieved, masterOutput);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else if(recieved instanceof Message){
            runInActionMessage(recieved);
        }else if(recieved instanceof SealedObject){

        }
    }
    
    private void runInActionMessage(Object recieved){
        Message m = (Message) recieved;
        if(m.getType()==0){
            Information i = (Information) m.getMainObject();
            connectedSerialID = i.getNonStaticSerialID();
            inHandler.getOutputIdentifier().put(i.getNonStaticSerialID(), output);
            
            Iterator<Information> it = inHandler.getConnectedInformationList().iterator();
            Information info;
            while(it.hasNext()){
                try {
                    Message msg = new Message();
                    info = it.next();
                    msg.setType(0);
                    msg.setMainObject(info);
                    try{
                        func.sendMessage(msg, output);
                    } catch(SocketException ex){
                        ex.printStackTrace();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            inHandler.getConnectedInformationList().add(i);
            
            try {
                func.sendAllMessage(recieved, masterOutput);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else if(m.getType()==7||m.getType()==8){
            System.out.println("Server: Recieved");
            ObjectOutputStream toSerialID, fromSerialID;
            toSerialID = inHandler.getOutputIdentifier().get(m.getToSerialID());
            fromSerialID = inHandler.getOutputIdentifier().get(m.getSerialID());
            try {
                func.sendMessage(recieved, toSerialID);
                func.sendMessage(recieved, fromSerialID);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            try {
                func.sendAllMessage(recieved, masterOutput);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void runInCloseDown() throws IOException{
        Message msg = new Message();
        msg.setType(9);
        msg.setSerialID(connectedSerialID);
        masterOutput.remove(output);
        func.sendAllMessage(msg, masterOutput);
    }
    
    
}
