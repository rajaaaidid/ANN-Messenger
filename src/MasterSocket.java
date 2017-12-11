import java.io.IOException;
import java.net.Socket;


public class MasterSocket extends Thread{
    private String ip;
    private Information info = new Information();
    private Interface func = new Interface();
    private OutSocket connection;
    private String name;
    
    MasterSocket(String ipaddress){
        ip = ipaddress;
        if(!func.isGUI()){
            this.start();
        }
    }
    
    @Override
    public void run(){
        try {
            info.setEncryptionCode(func.generateCode(10));
            connect(ip);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    public void connect(String serverIP) throws IOException, InterruptedException {
        try{
            func.setSocket(new Socket(serverIP, 6789));
            func.getListOfSocket().add(new OutSocket(func.getSocket()));
        }catch(java.net.ConnectException e){
            func.showWarning("Cannot connect to desired server... Retrying!");
            Thread.sleep(5000);
            connect(serverIP);
        }
    }
    
    public Interface getInterface(){
        return func;
    }
    
}
