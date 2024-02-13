package Chat;

import Chat.Client.ClientGUI;
import Chat.Server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        String fileLog = "./src/Chat/Server/Message.log";
        ServerWindow serverWindow = new ServerWindow(fileLog);
        new ClientGUI(serverWindow);
        new ClientGUI(serverWindow);
    }
}
