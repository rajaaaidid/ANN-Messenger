
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SealedObject;


public class CommandHandler {

    //private UniversalInterface uh = new UniversalInterface();
    private Information info = new Information();
    private OutHandler outHandler = new OutHandler();
    private Interface func = new Interface();
    private String userText;
    
    CommandHandler(String userText) throws IOException {
        this.userText = userText;
        if(info.isNonStaticServerEncryptionType()){
            commandHandlerEncrypted();
        }else{
            commandHandlerUnencrypted();
        }
    }

    public void commandHandlerUnencrypted() throws IOException {
        if(!outHandler.getServerInformation().isNonStaticServerEncryptionType()){
            String holder = userText.split(" ")[0];
            String[] arguments = userText.split(" ");
            if(holder.startsWith("/")){
                if(holder.equals("/message")){
                    boolean send = false;
                    String serialID = arguments[1];
                    System.out.println(serialID);
                    if(outHandler.isSerialID(serialID)){
                        send = true;
                    }else if(outHandler.isName(serialID.toUpperCase())){
                        serialID = outHandler.getConnectedInformationList().get(outHandler.getIndexByName(serialID)).getNonStaticSerialID();
                        if(outHandler.isSerialID(serialID)){
                            send = true;
                        }else{
                            func.showInformationToChat("Invalid client to send the message to...");
                        }
                    }else{
                        func.showInformationToChat("Invalid client to send the message to...");
                    }
                    System.out.println(serialID);
                    if(send){
                        //Direct Message
                        Message m = new Message();
                        m.setType(7);
                        m.setText(userText.substring(arguments[0].length()+1+arguments[1].length()));
                        m.setSerialID(info.getSerialID());
                        m.setToSerialID(serialID);
                        func.sendAllMessage(m, func.getListOfSocketOutput());
                    }
                }else if(holder.equals("/emessage")){
                    boolean send = false;
                    String serialID = arguments[1];
                    System.out.println(serialID);
                    if(outHandler.isSerialID(serialID)){
                        send = true;
                    }else if(outHandler.isName(serialID.toUpperCase())){
                        serialID = outHandler.getConnectedInformationList().get(outHandler.getIndexByName(serialID)).getNonStaticSerialID();
                        if(outHandler.isSerialID(serialID)){
                            send = true;
                        }else{
                            func.showInformationToChat("Invalid client to send the message to...");
                        }
                    }else{
                        func.showInformationToChat("Invalid client to send the message to...");
                    }
                    System.out.println(serialID);
                    if(send){
                        //Direct Message
                        Message m = new Message();
                        m.setType(7);
                        m.setText(userText.substring(arguments[0].length()+1+arguments[1].length()));
                        m.setSerialID(info.getSerialID());
                        m.setToSerialID(serialID);
                        //Seal Message
                        SealedObject j = (SealedObject) func.convertEncryptedMessage(m, info.getEncryptionCode());
                        Message m2 = new Message();
                        m2.setType(8);
                        m2.setMainObject(j);
                        m2.setSerialID(info.getSerialID());
                        m2.setToSerialID(serialID);
                        func.sendAllMessage(m2, func.getListOfSocketOutput());
                    }
                }else{
                    
                }
            }else{
                //Send Normal Text
                func.sendAllMessage(info.getSerialID()+" "+userText, func.getListOfSocketOutput());
            }
        }
    }

    public void commandHandlerEncrypted() throws IOException {
        if(!outHandler.getServerInformation().isNonStaticServerEncryptionType()){
            func.sendEncryptedMessage(info.getSerialID()+" "+userText, func.getListOfSocketOutput().get(0), outHandler.getServerInformation().getNonStaticEncryptionCode());
        }
    }
    
    
    
}
