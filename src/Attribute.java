import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


public class Attribute {

    private static SimpleAttributeSet time, universalName, system, connectedTime;
    
    Attribute(){
        preAttribute();
    }

    private void preAttribute(){
        
        time = new SimpleAttributeSet();
        StyleConstants.setForeground(getTime(), Color.BLUE);
        StyleConstants.setBold(getTime(), true);

        universalName = new SimpleAttributeSet();
        StyleConstants.setForeground(getUniversalName(), Color.BLACK);
        StyleConstants.setBold(getUniversalName(), true);

        system = new SimpleAttributeSet();
        StyleConstants.setForeground(getSystem(), Color.GRAY);
        StyleConstants.setBold(getSystem(), false);

        connectedTime = new SimpleAttributeSet();
        StyleConstants.setForeground(getConnectedTime(), Color.GREEN);
        StyleConstants.setBold(getConnectedTime(), true);
    }

    public SimpleAttributeSet getTime() {
        return time;
    }

    public SimpleAttributeSet getUniversalName() {
        return universalName;
    }

    public SimpleAttributeSet getSystem() {
        return system;
    }

    public SimpleAttributeSet getConnectedTime() {
        return connectedTime;
    }
}
