package org.example.client;

public interface IClient {
    boolean register(String username, String password, String name);
    boolean login(String username, String password);
    void sendMessage();
    void createRoom(String roomName);
    void addMember(String memberName, String roomName);
    void logout();
    void exit();
}