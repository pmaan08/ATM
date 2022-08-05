import java.io.IOException;

public class ATM {
    
    public static void intro() {
        System.out.println("ATM [Automated teller machine]");
    }
    
    public static void main(String[] args) throws IOException {
         intro();
         Menu options = new Menu();
         options.mainMenu();
    }
    
}