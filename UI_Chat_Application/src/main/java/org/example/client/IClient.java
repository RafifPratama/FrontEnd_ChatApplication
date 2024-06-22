package org.example.client;

import java.util.ArrayList;

import org.example.model.Room;
import org.example.model.User;

public interface IClient {
    boolean register(String username, String password, String name);
    boolean login(String username, String password);
    void sendMessage(String message, String roomName);
    void createRoom(String roomName);
    ArrayList<Room> listAllRooms();
    ArrayList<User> listAllMembersInTheRoom(Integer roomId);
    void addMember(String memberName, String roomName);
    void logout();
    void exit();
}