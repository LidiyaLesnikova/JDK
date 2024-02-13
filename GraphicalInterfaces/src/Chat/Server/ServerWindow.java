package Chat.Server;

import Chat.Client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ServerWindow extends JFrame {
    private static final int POSX = 500;
    private static final int POSY = 350;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();

    private boolean isServerWorking;
    private final Users users = new Users();
    private final Map<String, ClientGUI> clientChatAutorizeded = new HashMap<>();
    private FileHandler fileHandler;

    public ServerWindow(String fileLog) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POSX, POSY, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2));
        buttonsPanel.add(btnStart);
        buttonsPanel.add(btnStop);
        add(buttonsPanel, BorderLayout.SOUTH);
        addListeners();
        add(new JScrollPane(log), BorderLayout.CENTER);

        setVisible(true);

        fileHandler = new FileHandler(fileLog);
    }

    private void addListeners() {
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    isServerWorking = false;
                    setMessage("", "Server stopped \n");
                    setDisconnect();
                } else {
                    log.append("Server not working \n");
                }

            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    log.append("Server is already running \n");
                } else {
                    isServerWorking = true;
                    log.append("Server started \n");
                }
            }
        });
    }

    public boolean getServerWorking() {
        return isServerWorking;
    }

    public String[] getListUsers() {
        Set<String> userSet = users.getListUsers().keySet();
        return userSet.toArray(new String[userSet.size()]);
    }

    public String[] authorizedUser(ClientGUI clientChat, String nameUser, String passwordUser) {
        String[] answer = new String[3];
        answer[0] = "false";
        answer[2] = "";

        if (!nameUser.isEmpty()) {
            if (users.getListUsers().containsKey(nameUser)) {
                if (users.getListUsers().get(nameUser).equals(passwordUser)) {
                    log.append(nameUser + " connected \n");
                    answer[0] = "true";
                    answer[1] = "you have successfully connected.\n";
                    clientChatAutorizeded.put(nameUser, clientChat);
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

    public void setMessage(String name, String message) {
        log.append(name + ": " + message + "\n");
        for (var client : clientChatAutorizeded.entrySet()) {
            client.getValue().setMessage(name+": "+message+"\n");
        }
    }

    private void setDisconnect() {
        String messageLog = "\n";
        for (var client : clientChatAutorizeded.entrySet()) {
            messageLog += "<<"+client.getKey()+">>:\n"+client.getValue().disconnect()+"\n";
            messageLog += "<<-------------->>\n";
        }
        fileHandler.save(messageLog);
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
