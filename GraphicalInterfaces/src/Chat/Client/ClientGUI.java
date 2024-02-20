package Chat.Client;

import Chat.Server.ServerView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame implements ClientView {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2, 1));
    private final JTextField tfIPAddress;
    private final JTextField tfIPort;
    private final JTextField tfILogin = new JTextField();;
    private final JPasswordField tfPassword;
    private final JList listUsers = new JList();
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBotton = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final ClientGUI clientGUI;
    private final Client client;

    public ClientGUI(ServerView serverWindow){
        this.clientGUI = this;
        this.client = new Client(clientGUI, serverWindow);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        JPanel dataAddress = new JPanel(new GridLayout(1, 2));

        tfIPAddress = new JTextField(serverWindow.getServer().getIpAddress());
        tfIPort = new JTextField(serverWindow.getServer().getiPort());
        dataAddress.add(tfIPAddress);
        dataAddress.add(tfIPort);

        JPanel dataLogin = new JPanel(new GridLayout(1, 3));
        listUsers.setListData(serverWindow.getServer().getListUsers());
        listUsers.setVisibleRowCount(1);
        dataLogin.add(new JScrollPane(listUsers));
        tfPassword = new JPasswordField(client.getPassword());
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

        addListeners();

        setVisible(true);
    }

    private void addListeners() {
        listUsers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tfILogin.setText((String) ((JList<?>) e.getSource()).getSelectedValue());
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.setLoginServer(clientGUI, tfILogin.getText(), tfPassword.getText());
            }
        });
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendMessageOnServer(tfILogin.getText(), tfMessage.getText());
            }
        });
        tfMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendMessageOnServer(tfILogin.getText(), tfMessage.getText());
            }
        });
    }

    @Override
    public void setMessage(String message){
        log.append(message);
    }

    @Override
    public void setVisiblePanelLogin(boolean visible) {
        panelTop.setVisible(visible);
    }

    @Override
    public String getMessage() {
        return log.getText();
    }
}
