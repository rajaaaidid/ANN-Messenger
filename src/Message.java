import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.text.SimpleAttributeSet;


public class Message implements Serializable{
    //100 = SeverInitialization /
    //0 = Initialization /
    //1 = Text
    //2 = Request
    //3 = ServerRequest
    //4 = Object
    //5 = MultipleObject
    //6 = SealedObject 
    //7 = DirectMessage /
    //8 = DirectEncryptedMessage /
    //9 = CloseDownInitialization /
    //10 = RequestReturn
    //11 = ServerRequestReturn
    private int type = 50;
    private String serialID;
    private String toSerialID;
    private String text;
    private String textCommand;
    private String nameOfSender;
    private SimpleAttributeSet attribute;
    private Object mainObject;
    private ArrayList<Object> otherObjects = new ArrayList<Object>();


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNameOfSender() {
        return nameOfSender;
    }

    public void setNameOfSender(String nameOfSender) {
        this.nameOfSender = nameOfSender;
    }

    public SimpleAttributeSet getAttribute() {
        return attribute;
    }

    public void setAttribute(SimpleAttributeSet attribute) {
        this.attribute = attribute;
    }

    public Object getMainObject() {
        return mainObject;
    }

    public void setMainObject(Object mainObject) {
        this.mainObject = mainObject;
    }

    public ArrayList<Object> getOtherObjects() {
        return otherObjects;
    }

    public void setOtherObjects(Object otherObjects) {
        this.otherObjects.add(otherObjects);
    }

    public String getSerialID() {
        return serialID;
    }

    public void setSerialID(String serialID) {
        this.serialID = serialID;
    }
    
    public String getToSerialID(){
        return toSerialID;
    }
    
    public void setToSerialID(String toSerialID){
        this.toSerialID = toSerialID;
    }

    public String getTextCommand() {
        return textCommand;
    }

    public void setTextCommand(String textCommand) {
        this.textCommand = textCommand;
    }
}
