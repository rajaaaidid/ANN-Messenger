import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MasterServerSocket extends Thread{
    private Interface func = new Interface();
    private ServerSocket server;
    private Socket connection;
    private InSocket inConnection;
    static private ArrayList<ObjectOutputStream> masterOutput = new ArrayList<ObjectOutputStream>();
    
    MasterServerSocket(){
        if(!func.isGUI()){
            this.start();
        }
    }
    
    @Override
    public void run(){
        try {
            server = new ServerSocket(6789, 100);
            func.showServer("Server creation sucessful...");
            while(true){
                waitForConnection();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void waitForConnection() throws IOException{
        connection = server.accept();
        inConnection = new InSocket(connection, masterOutput);
    }
    
    public ArrayList<ObjectOutputStream> getMasterOutput(){
        return masterOutput;
    }
    
}
