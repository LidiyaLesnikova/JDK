package Chat.Server;

import java.util.*;

public class Users {
    private Map<String, String> listUsers;
    public Users(){
        listUsers = createListUsers();
    }
    private Map<String, String> createListUsers() {
        listUsers = new HashMap();
        listUsers.putIfAbsent("Ivan Igorevich", "12345678");
        listUsers.putIfAbsent("user123", "123");
        listUsers.putIfAbsent("user124", "124");
        listUsers.putIfAbsent("user126", "126");
        listUsers.putIfAbsent("user128", "128");
        listUsers.putIfAbsent("user555", "555");
        return listUsers;
    }

    public Map<String, String> getListUsers() {
        return listUsers;
    }
}
