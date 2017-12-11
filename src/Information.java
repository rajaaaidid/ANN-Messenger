import java.io.Serializable;


public class Information implements Serializable{
    private static String name;
    private static String encryptionCode = "Default";
    private static String serialID = "0";
    private static boolean serverEncryptionType = false;
    
    private String nonStaticName;
    private String nonStaticEncryptionCode = "Default";
    private String nonStaticSerialID = "0";
    private boolean nonStaticServerEncryptionType = false;

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public String getEncryptionCode() {
        return encryptionCode;
    }

    public void setEncryptionCode(String aEncryptionCode) {
        encryptionCode = aEncryptionCode;
    }

    public String getSerialID() {
        return serialID;
    }

    public void setSerialID(String aSerialID) {
        serialID = aSerialID;
    }

    public String getNonStaticName() {
        return nonStaticName;
    }

    public void setNonStaticName(String nonStaticName) {
        this.nonStaticName = nonStaticName;
    }

    public String getNonStaticEncryptionCode() {
        return nonStaticEncryptionCode;
    }

    public void setNonStaticEncryptionCode(String nonStaticEncryptionCode) {
        this.nonStaticEncryptionCode = nonStaticEncryptionCode;
    }

    public String getNonStaticSerialID() {
        return nonStaticSerialID;
    }

    public void setNonStaticSerialID(String nonStaticSerialID) {
        this.nonStaticSerialID = nonStaticSerialID;
    }

    public static boolean isServerEncryptionType() {
        return serverEncryptionType;
    }

    public static void setServerEncryptionType(boolean aServerEncryptionType) {
        serverEncryptionType = aServerEncryptionType;
    }

    public boolean isNonStaticServerEncryptionType() {
        return nonStaticServerEncryptionType;
    }

    public void setNonStaticServerEncryptionType(boolean nonStaticServerEncryptionType) {
        this.nonStaticServerEncryptionType = nonStaticServerEncryptionType;
    }
        
    public void serializeInfo(){
        nonStaticName = name;
        nonStaticSerialID = serialID;
        nonStaticEncryptionCode = encryptionCode;
        nonStaticServerEncryptionType = serverEncryptionType;
    }
}
