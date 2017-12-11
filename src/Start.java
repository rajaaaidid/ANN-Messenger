
public class Start {
    static Interface func = new Interface();
    static UserInterface UI;
    //static UserInput input;
    
    public static void main(String[] args) throws Exception{
        if(func.isGUI()){
            UserInterface UI = new UserInterface();
            UI.runGUI();
        }
    }
    
}
