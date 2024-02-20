package Chat.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame implements ServerView {
    private static Server server;

    private static final int POSX = 500;
    private static final int POSY = 350;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();

    public ServerWindow(String fileLog) {

        server = new Server(this, fileLog);

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
    }

    private void addListeners() {
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.setServerDisconnect();
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.setServerWorking();
            }
        });
    }

    @Override
    public Server getServer(){
        return server;
    }
    @Override
    public void setMessage(String messageUser) {
        log.append(messageUser);
    }
}
