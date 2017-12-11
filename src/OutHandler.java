
import java.util.ArrayList;
import java.util.Iterator;


public class OutHandler {
    private static Information serverInformation;
    private static ArrayList<Information> connectedInformationList = new ArrayList<Information>();

    public Information getServerInformation() {
        return serverInformation;
    }

    public void setServerInformation(Information aServerInformation) {
        serverInformation = aServerInformation;
    }

    public ArrayList<Information> getConnectedInformationList() {
        return connectedInformationList;
    }

    public void setConnectedInformation(ArrayList<Information> aConnectedInformation) {
        connectedInformationList = aConnectedInformation;
    }
    
    public int getIndexBySerialID(String id){
        int count = 0;
        int index = -1;
        Iterator<Information> i = getConnectedInformationList().iterator();
        Information info;
        while(i.hasNext()){
            info = i.next();
            if(info.getNonStaticSerialID().equals(id)){
                index = count;
                break;
            }
            count++;
        }
        return index;
    }
    
    public int getIndexByName(String name){
        int count = 0;
        int index = -1;
        Iterator<Information> i = getConnectedInformationList().iterator();
        Information info;
        while(i.hasNext()){
            info = i.next();
            if(info.getNonStaticName().equals(name)){
                index = count;
                break;
            }
            count++;
        }
        return index;
    }
    
    public boolean isSerialID(String serialID){
        boolean isSID = false;
        int index = -1;
        Iterator<Information> i = getConnectedInformationList().iterator();
        Information info;
        while(i.hasNext()){
            info = i.next();
            if(info.getNonStaticSerialID().equals(serialID)){
                isSID = true;
                break;
            }
        }
        return isSID;
    }
    
    public boolean isName(String name){
        boolean isN = false;
        int index = -1;
        Iterator<Information> i = getConnectedInformationList().iterator();
        Information info;
        while(i.hasNext()){
            info = i.next();
            if(info.getNonStaticName().toUpperCase().equals(name)){
                isN = true;
                break;
            }
        }
        return isN;
    }


}
