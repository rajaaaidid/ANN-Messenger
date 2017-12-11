
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class InHandler {
    private static ArrayList<Information> connectedInformationList = new ArrayList<Information>();
    private static HashMap<String, ObjectOutputStream> outputIdentifier = new HashMap<String, ObjectOutputStream>();

    public HashMap<String, ObjectOutputStream> getOutputIdentifier() {
        return outputIdentifier;
    }

    public void setOutputIdentifier(HashMap<String, ObjectOutputStream> aOutputIdentifier) {
        outputIdentifier = aOutputIdentifier;
    }

    public ArrayList<Information> getConnectedInformationList() {
        return connectedInformationList;
    }

    public void setConnectedInformationList(ArrayList<Information> aConnectedInformationList) {
        connectedInformationList = aConnectedInformationList;
    }
}
