package org.example.client;

import java.util.ArrayList;

import org.example.model.Room;

public interface IClient {
    boolean register(String username, String password, String name);
    boolean login(String username, String password);
    void sendMessage();
    void createRoom(String roomName);
    ArrayList<Room> listAllRooms();
    void addMember(String memberName, String roomName);
    void logout();
    void exit();
}