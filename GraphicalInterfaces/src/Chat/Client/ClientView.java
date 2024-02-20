package Chat.Client;

public interface ClientView {
    void setMessage(String message);
    void setVisiblePanelLogin(boolean visible);
    String getMessage();
}
