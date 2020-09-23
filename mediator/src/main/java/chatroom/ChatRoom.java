package chatroom;

import user.User;

public interface ChatRoom {
    void sendMessage(String msg, String userId);
    void addUser(User user);
}
