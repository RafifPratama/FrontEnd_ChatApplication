package org.example.client;

import java.util.ArrayList;

import org.example.model.Chat;
import org.example.model.Room;
import org.example.model.User;

public interface IClient {
    boolean register(String username, String password, String name);
    boolean login(String username, String password);
    String getClientName();
    Integer getClientId();
    void sendMessage(String message, String roomName);
    boolean createRoom(String roomName);
    ArrayList<Room> listAllRooms();
    ArrayList<User> listAllMembersInTheRoom(Integer roomId);
    ArrayList<Chat> listAllChatsInTheRoom(Integer roomId);
    void addMember(String memberName, String roomName);
    boolean joinRoom(String roomName);
    boolean isMemberInside(Integer roomId);
    void logout();
    void exit();
}