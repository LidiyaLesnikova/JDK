package Chat.Client;

import Chat.Server.ServerWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2, 1));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfIPort = new JTextField("8189");
    private final JTextField tfILogin = new JTextField();;
    private final JPasswordField tfPassword = new JPasswordField("12345678");
    private final JList listUsers = new JList();
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBotton = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final ClientGUI client;

    public ClientGUI(ServerWindow serverWindow){
        client = this;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        JPanel dataAddress = new JPanel(new GridLayout(1, 2));
        dataAddress.add(tfIPAddress);
        dataAddress.add(tfIPort);

        JPanel dataLogin = new JPanel(new GridLayout(1, 3));
        listUsers.setListData(serverWindow.getListUsers());
        listUsers.setVisibleRowCount(1);
        dataLogin.add(new JScrollPane(listUsers));
        dataLogin.add(tfPassword);
        dataLogin.add(btnLogin);

        panelTop.add(dataAddress);
        panelTop.add(dataLogin);
        add(panelTop, BorderLayout.NORTH);

        panelBotton.add(tfMessage, BorderLayout.CENTER);
        panelBotton.add(btnSend, BorderLayout.EAST);
        add(panelBotton, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        addListeners(serverWindow);

        setVisible(true);
    }

    private void addListeners(ServerWindow serverWindow) {
        listUsers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tfILogin.setText((String) ((JList<?>) e.getSource()).getSelectedValue());
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow.getServerWorking()) {
                    String[] answer = serverWindow.authorizedUser(client, tfILogin.getText(), tfPassword.getText());
                    log.append(answer[1]);
                    if (answer[0].equals("true")) {
                        panelTop.setVisible(false);
                        log.append(answer[2]);
                    }
                } else {
                    log.append("Server not working \n");
                    panelTop.setVisible(true);
                }
            }
        });
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow.getServerWorking()) {
                    serverWindow.setMessage(tfILogin.getText(), tfMessage.getText());
                } else {
                    log.append("Server not working \n");
                    panelTop.setVisible(true);
                }
            }
        });
        tfMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow.getServerWorking()) {
                    serverWindow.setMessage(tfILogin.getText(), tfMessage.getText());
                } else {
                    log.append("Server not working \n");
                    panelTop.setVisible(true);
                }
            }
        });
    }

    public void setMessage(String message){
        log.append(message);
    }

    public String disconnect(){
        panelTop.setVisible(true);
        return log.getText();
    }
}
