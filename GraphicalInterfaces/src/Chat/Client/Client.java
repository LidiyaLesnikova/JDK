package Chat.Client;

import Chat.Server.ServerView;

public class Client {
    private String password = "12345678";
    private ClientView clientView;
    private ServerView serverView;

    public Client(ClientView clientView, ServerView serverView) {
        this.clientView = clientView;
        this.serverView = serverView;
    }

    public String getPassword() {
        return password;
    }

    public void setLoginServer(ClientView clientChat, String nameUser, String passwordUser) {
        if (serverView.getServer().getIsServerWorking()) {
            String[] answer = serverView.getServer().authorizedUser(clientChat, nameUser, passwordUser);
            clientView.setMessage(answer[1]);
            if (answer[0].equals("true")) {
                clientView.setVisiblePanelLogin(false);
                clientView.setMessage(answer[2]);
            }
        } else {
            clientView.setVisiblePanelLogin(true);
            clientView.setMessage("Server not working \n");
        }
    }

    public void sendMessageOnServer(String nameUser, String messageUser) {
        if (serverView.getServer().getIsServerWorking()) {
            serverView.setMessage(nameUser+": "+messageUser+"\n");
            serverView.getServer().sendMessageToClientAuthorized(nameUser+": "+messageUser+"\n");
        } else {
            clientView.setVisiblePanelLogin(true);
            clientView.setMessage("Server not working \n");
        }
    }
}
