package Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private static final int POSX = 500;
    private static final int POSY = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JTextArea log = new JTextArea();
    private boolean isServerWorking;

    public static void main(String[] args) {
        new ServerWindow();
    }

    private ServerWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POSX, POSY, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        setLayout(new GridLayout(1, 2));
        add(btnStart);
        add(btnStop);
        addListeners();

        setVisible(true);
    }

    private void addListeners() {
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking == false) {
                    log.append("Server not working");
                    //System.out.println("Server not working");
                } else {
                    isServerWorking = false;
                    log.append("Server stopped");
                    //System.out.println("Server stopped");

                }
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking == true) {
                    log.append("Server is already running");
                    //System.out.println("Server is already running");
                } else {
                    isServerWorking = true;
                    System.out.println("Server started");
                }
            }
        });
    }
}
