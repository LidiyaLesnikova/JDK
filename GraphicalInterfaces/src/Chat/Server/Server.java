package Chat.Server;

import Chat.Client.ClientView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Server {
    private String ipAddress = "127.0.0.1";
    private String iPort = "8189";
    ServerView serverView;
    private boolean isServerWorking;
    private final Users users = new Users();
    private final Map<String, ClientView> clientChatAuthorized = new HashMap<>();
    private FileProcessing fileHandler;

    public Server(ServerView serverView, String fileLog) {
        this.serverView = serverView;
        this.fileHandler = new FileHandler(fileLog);
    }

    public Server(){
        super();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getiPort() {
        return iPort;
    }

    public boolean getIsServerWorking() {
        return isServerWorking;
    }

    public void setServerWorking() {
        if (isServerWorking) {
            serverView.setMessage("Server is already running \n");
        } else {
            this.isServerWorking = true;
            serverView.setMessage("Server started \n");
        }
    }

    public void setServerDisconnect() {
        if (isServerWorking) {
            isServerWorking = false;
            serverView.setMessage("Server stopped \n");
            sendMessageToClientAuthorized("Server stopped \n");
            fileHandler.save(receiveMessageToClientAuthorized());
        } else {
            serverView.setMessage("Server not working \n");
        }
    }

    public void sendMessageToClientAuthorized(String message) {
        for (var client : clientChatAuthorized.entrySet()) {
            client.getValue().setMessage(message);
        }
    }

    public String receiveMessageToClientAuthorized() {
        String messageLog = "\n";
        for (var client : clientChatAuthorized.entrySet()) {
            messageLog += "<<"+client.getKey()+">>:\n"+client.getValue().getMessage()+"\n";
            messageLog += "<<-------------->>\n";
        }
        return messageLog;
    }

    public String[] getListUsers() {
        Set<String> userSet = users.getListUsers().keySet();
        return userSet.toArray(new String[userSet.size()]);
    }

    public String[] authorizedUser(ClientView clientChat, String nameUser, String passwordUser) {
        String[] answer = new String[3];
        answer[0] = "false";
        answer[2] = "";

        if (!nameUser.isEmpty()) {
            if (users.getListUsers().containsKey(nameUser)) {
                if (users.getListUsers().get(nameUser).equals(passwordUser)) {
                    clientChatAuthorized.put(nameUser, clientChat);
                    serverView.setMessage(nameUser + " connected \n");

                    answer[0] = "true";
                    answer[1] = "you have successfully connected.\n";
                    answer[2] = fillMessageLog(nameUser);
                } else {
                    answer[1] = "password is not correct, try another.\n";
                }
            } else {
                answer[1] = nameUser + " - does not exist, try another name.\n";
            }
        } else {
            answer[1] = "you need to select a user.\n";
        }
        return answer;
    }

    private String fillMessageLog(String nameUser) {
        String messageLog = fileHandler.read();
        if (!messageLog.isEmpty()) {
            if (messageLog.indexOf("<<" + nameUser + ">>:")>0) {
                messageLog = messageLog.substring(messageLog.indexOf("<<" + nameUser + ">>:") + nameUser.length() + 5);
                messageLog = messageLog.substring(0, messageLog.indexOf("<<-------------->>"));
            } else {
                messageLog = "";
            }
        }
        return messageLog;
    }
}
