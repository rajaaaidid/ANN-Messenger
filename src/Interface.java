
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;


public class Interface {
    static private final boolean GUI = true;
    static private ArrayList<InSocket> listOfConnected = new ArrayList<InSocket>();
    static private ArrayList<OutSocket> listOfSocket = new ArrayList<OutSocket>();
    static private ArrayList<ObjectOutputStream> listOfSocketOutput = new ArrayList<ObjectOutputStream>();
    static private String input;
    static private Socket socket;
    static private StyledDocument chatWindowInsert;
    private InHandler inHandler = new InHandler();
    private OutHandler outHandler = new OutHandler();
    private Attribute attribute = new Attribute();
    private SecretKey key64;
    private Cipher cipher;
    private Random rand;
    
    public boolean isOwnMessage(Object m){
        boolean b = false;
        if(m instanceof String){
            String s = (String) m;
            s = s.substring(0, 10);
            if(s.equals(new Information().getSerialID())){
                b = true;
            }else{
                b = false;
            }
        }else if(m instanceof Message){
            Message msg = (Message) m;
            if(msg.getSerialID().equals(new Information().getSerialID())){
                b = true;
            }else{
                b = false;
            }
        }
        return b;
    }
    
    public void setInput(String s){
        input = s;
    }
    
    public void showDirectMessage(Object m, boolean convertToName) throws BadLocationException{
        String userHeader = (String) m;
        String holder = (String) m;
        String message = (String) m;
        userHeader = holder.split(" ")[0].concat(" "+holder.split(" ")[1]).concat(" "+holder.split(" ")[2]);
        message = message.substring(userHeader.length());
        if(convertToName){
            int index;
            if(!isOwnMessage(m)){
                index = outHandler.getIndexBySerialID(holder.split(" ")[0]);
                userHeader = outHandler.getConnectedInformationList().get(index).getNonStaticName();
                index = outHandler.getIndexBySerialID(holder.split(" ")[2]);
                userHeader = userHeader.concat(" >> "+"You");
            }else{
                index = outHandler.getIndexBySerialID(holder.split(" ")[2]);
                userHeader = outHandler.getConnectedInformationList().get(index).getNonStaticName();
                index = outHandler.getIndexBySerialID(holder.split(" ")[0]);
                userHeader = userHeader.concat(" << "+"You");
            }
        }
        
        if(isOwnMessage(m)){
            chatWindowInsert.insertString(chatWindowInsert.getLength(), "["+getTime()+"] ", attribute.getTime());
            chatWindowInsert.insertString(chatWindowInsert.getLength(), userHeader+": ", attribute.getUniversalName());
            chatWindowInsert.insertString(chatWindowInsert.getLength(), message+"\n", null);
        }else{
            chatWindowInsert.insertString(chatWindowInsert.getLength(), "["+getTime()+"] ", attribute.getConnectedTime());
            chatWindowInsert.insertString(chatWindowInsert.getLength(), userHeader+": ", attribute.getUniversalName());
            chatWindowInsert.insertString(chatWindowInsert.getLength(), message+"\n", null);
        }
        
        if(isOwnMessage(m)){
            System.out.println(getTime()+" << "+m);
        }else{
            System.out.println(getTime()+" >> "+m);
        }
    }
    
    public void showMessage(Object m, boolean convertToName) throws BadLocationException{
        String userHeader = (String) m;
        String holder = (String) m;
        String message = (String) m;
        userHeader = userHeader.split(" ")[0];
        message = message.substring(userHeader.length());
        if(convertToName){
            int index = outHandler.getIndexBySerialID(holder.split(" ")[0]);
            userHeader = outHandler.getConnectedInformationList().get(index).getNonStaticName();
        }
        
        if(isOwnMessage(m)){
            chatWindowInsert.insertString(chatWindowInsert.getLength(), "["+getTime()+"] ", attribute.getTime());
            chatWindowInsert.insertString(chatWindowInsert.getLength(), userHeader+": ", attribute.getUniversalName());
            chatWindowInsert.insertString(chatWindowInsert.getLength(), message+"\n", null);
        }else{
            chatWindowInsert.insertString(chatWindowInsert.getLength(), "["+getTime()+"] ", attribute.getConnectedTime());
            chatWindowInsert.insertString(chatWindowInsert.getLength(), userHeader+": ", attribute.getUniversalName());
            chatWindowInsert.insertString(chatWindowInsert.getLength(), message+"\n", null);
        }
        
        if(isOwnMessage(m)){
            System.out.println(getTime()+" << "+m);
        }else{
            System.out.println(getTime()+" >> "+m);
        }
    }
    
    public void showInformation(Object m){
        System.out.println("INFO: "+m);
    }
    
    public void showInformationToChat(Object m){
        try {
            chatWindowInsert.insertString(chatWindowInsert.getLength(), "["+getTime()+"] INFO: ", attribute.getSystem());
            chatWindowInsert.insertString(chatWindowInsert.getLength(), m+"\n", attribute.getSystem());
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
        System.out.println("INFO: "+m);
    }
    
    public void showWarning(Object m){
        System.out.println("WARNING: "+m);
    }
    
    public void showServer(Object m){
        System.out.println("SERVER: "+m);
    }
    
    public void sendEncryptedMessage(Object m, ObjectOutputStream output, String key) throws IOException{
        SealedObject encryptedObject = null;
        try {
            key64 = new SecretKeySpec( key.getBytes(), "Blowfish" );
            cipher = Cipher.getInstance( "Blowfish" );
            cipher.init( Cipher.ENCRYPT_MODE, key64);
            encryptedObject = new SealedObject( (Serializable) m, cipher);
        } catch (InvalidKeyException | IllegalBlockSizeException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
            ex.printStackTrace();
        }
        output.writeObject(encryptedObject);
        output.flush();
    }
    
    public Object convertEncryptedMessage(Object m, String key) throws IOException{
        SealedObject encryptedObject = null;
        try {
            key64 = new SecretKeySpec( key.getBytes(), "Blowfish" );
            cipher = Cipher.getInstance( "Blowfish" );
            cipher.init( Cipher.ENCRYPT_MODE, key64);
            encryptedObject = new SealedObject( (Serializable) m, cipher);
        } catch (InvalidKeyException | IllegalBlockSizeException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
            ex.printStackTrace();
        }
        return encryptedObject;
    }
    
    public Object recieveEncryptedMessage(SealedObject m, String key){
        Object recieved = null;
        try {
            key64 = new SecretKeySpec( key.getBytes(), "Blowfish" );
            cipher = Cipher.getInstance( "Blowfish" );
            cipher.init( Cipher.DECRYPT_MODE, key64);
            
            SealedObject sealObject = m;
            recieved = sealObject.getObject( cipher );
        } catch (IOException | ClassNotFoundException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException ex) {
            ex.printStackTrace();
        }
        return recieved;
    }
    
    public void sendMessage(Object m, ObjectOutputStream output) throws IOException{
        output.writeObject(m);
        output.flush();
    }
    
    public void sendInformation(Object m, ObjectOutputStream output) throws IOException{
        output.writeObject("INFO: "+m);
        output.flush();
    }
    
    public void sendWarning(Object m, ObjectOutputStream output) throws IOException{
        output.writeObject("WARNING: "+m);
        output.flush();
    }
    
    public void sendAllMessage(Object m, ArrayList<ObjectOutputStream> output) throws IOException{
        Iterator<ObjectOutputStream> outputs = output.iterator();
        ObjectOutputStream out;
        while(outputs.hasNext()){
            out = outputs.next();
            out.writeObject(m);
            out.flush();
        }
    }
    
    public void sendAllFilteredMessage(Object m, HashMap<String, ObjectOutputStream> outputIdentifier) throws IOException{
        String s = null;
        Message msg = null;
        if(m instanceof String){
            s = (String) m;
        }else if(m instanceof Message){
            msg = (Message) m;
        }
        for(String serialID: outputIdentifier.keySet()){
            if(m instanceof String){
                if(!s.substring(0, 10).equals(serialID)){
                    outputIdentifier.get(serialID).writeObject(m);
                    outputIdentifier.get(serialID).flush();
                }
            }else if(m instanceof Message){
                if(!msg.getSerialID().equals(serialID)){
                    outputIdentifier.get(serialID).writeObject(m);
                    outputIdentifier.get(serialID).flush();
                }
            }
        }
    }
    
    public void sendAllInformation(Object m, ArrayList<ObjectOutputStream> output) throws IOException{
        Iterator<ObjectOutputStream> outputs = output.iterator();
        ObjectOutputStream out;
        while(outputs.hasNext()){
            out = outputs.next();
            out.writeObject("INFO: "+m);
            out.flush();
        }
    }
    
    public void sendAllWarning(Object m, ArrayList<ObjectOutputStream> output) throws IOException{
        Iterator<ObjectOutputStream> outputs = output.iterator();
        ObjectOutputStream out;
        while(outputs.hasNext()){
            out = outputs.next();
            out.writeObject("WARNING: "+m);
            out.flush();
        }
    }

    public ArrayList<InSocket> getListOfConnected() {
        return listOfConnected;
    }
    
    public ArrayList<OutSocket> getListOfSocket() {
        return listOfSocket;
    }
    
    public ArrayList<ObjectOutputStream> getListOfSocketOutput(){
        return listOfSocketOutput;
    }
    
    public Socket getSocket(){
        return socket;
    }
    
    public void setSocket(Socket s){
        socket = s;
    }
    
    public void setChatWindowInsert(StyledDocument sd){
        chatWindowInsert = sd;
    }
    
    public String generateCode(int length){
        rand = new Random();
        String code = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "~!@#$%^&*()_+-=[]\\{}|;:<>?,./";
        String set;
        char character;
        ArrayList<String> sets = new ArrayList<String>();
        sets.add(alphabet);
        sets.add(numbers);
        sets.add(symbols);
        for(int x=0; x<length; x++){
            set = sets.get(rand.nextInt(sets.size()));
            character = set.charAt(rand.nextInt(set.length()));
            code = code.concat(Character.toString(character));
        }
        return code;
    }
    
    public String generateNumbers(int length){
        rand = new Random();
        String code = "";
        String numbers = "0123456789";
        for(int x=0; x<length; x++){
            code = code.concat(Character.toString(numbers.charAt(rand.nextInt(numbers.length()))));
        }
        return code;
    }
    
    public boolean isGUI(){
        return GUI;
    }
    
    public String getTime(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

}
