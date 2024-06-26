package org.example.client;

import org.example.env.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.example.request.Request;
import org.example.response.Response;
import org.example.model.Chat;
import org.example.model.Room;
import org.example.model.User;
import org.example.model.UserRoom;

public class Client implements IClient {
    private User user;
    private Gson gson;
    private OutputStream output;
    private PrintWriter writer;
    private InputStream inFromServer;
    private BufferedReader readInputFromServer;
    private Socket clientSocket;

    public Client() {
        this.user = new User();
        this.gson = new Gson();
        this.connectToServer();
        // this.listenOnMessage();
    }

    public void connectToServer() {
        // Socket(server ip, server port)
        try {
            // Socket(server ip, server port)
            this.clientSocket = new Socket(Env.getServerIP(), Env.getPort());
            this.output = clientSocket.getOutputStream();
            this.writer = new PrintWriter(output, true);
            this.inFromServer = clientSocket.getInputStream();
            this.readInputFromServer = new BufferedReader(new InputStreamReader(inFromServer));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public void listenOnMessage(){
    //     new Thread(() -> {
    //         String response = "";

    //         try{
    //             response = this.readInputFromServer.readLine();

    //             @SuppressWarnings("unchecked")
    //             Response<String> res = gson.fromJson(response, Response.class);

    //             System.out.println("Message : " + res.getData());
    //         } catch (Exception e){
    //             e.printStackTrace();
    //         }
    //     }).start();
    // }

    @Override
    public boolean register(String username, String password, String name) {
        this.user.setUsername(username);
        this.user.setPassword(password);
        this.user.setName(name);

        Request<String> req = new Request<>("register", gson.toJson(user));

        String response = "";

        writer.println(gson.toJson(req));

        try {
            response = readInputFromServer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        @SuppressWarnings("unchecked")
        Response<String> res = gson.fromJson(response, Response.class);

        // empty user credentials so user must login once registered.
        this.user.setUsername("");
        this.user.setPassword("");
        this.user.setName("");

        // System.out.println(res.getData());
        if(res.getData().equals("400")){
            return false;
        }
        return true;
    }

    @Override
    public boolean login(String username, String password) {
        this.user.setUsername(username);
        this.user.setPassword(password);

        Request<String> req = new Request<>("login", gson.toJson(user));

        writer.println(gson.toJson(req));

        String response = "";

        try {
            response = readInputFromServer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        @SuppressWarnings("unchecked")
        Response<String> res = gson.fromJson(response, Response.class);
        Integer userId = gson.fromJson(res.getData().toString(), Integer.class);

        System.out.println("user id adalah " + userId);

        if (!res.getData().equals("401")) {
            // System.out.println("Masuk sini nih");
            this.user.setId(userId);
            this.user.setIsLoggedIn(true);
            return true;
        }
        return false;
    }

    @Override
    public void sendMessage(String message, String roomName) {
        Request<String> req = new Request<>("sendMessage", gson.toJson(new Chat(message, roomName, this.user.getName())));

        writer.println(gson.toJson(req));
    }

    @Override
    public boolean createRoom(String roomName) {
        if (!this.user.getIsLoggedIn())
            return false;

        Room room = new Room(roomName, user.getName());

        Request<String> req = new Request<>("createRoom", gson.toJson(room));

        writer.println(gson.toJson(req));

        String response = "";

        try {
            response = readInputFromServer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        @SuppressWarnings("unchecked")
        Response<String> res = gson.fromJson(response, Response.class);

        if(res.getData().equals("200")){
            return true;
        }
        return false;
        // System.out.println(res.getData());
    }

    @Override
    public void addMember(String memberName, String roomName) {
        if (!this.user.getIsLoggedIn())
            return;

        UserRoom userRoom = new UserRoom(memberName, roomName);

        Request<String> req = new Request<>("addMember", gson.toJson(userRoom));

        writer.println(gson.toJson(req));

        String response = "";

        try {
            response = readInputFromServer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        @SuppressWarnings("unchecked")
        Response<String> res = gson.fromJson(response, Response.class);

        System.out.println(res.getData());
    }

    @Override
    public void logout() {
        if (!this.user.getIsLoggedIn())
            return;
        Request<String> req = new Request<>("logout");

        writer.println(gson.toJson(req));

        String response = "";

        try {
            response = readInputFromServer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        @SuppressWarnings("unchecked")
        Response<String> res = gson.fromJson(response, Response.class);

        this.user.setIsLoggedIn(false);

        System.out.println(res.getData());
    }

    @Override
    public void exit() {
        try {
            Request<String> req = new Request<>("exit");
            writer.println(gson.toJson(req));
            this.user = null;
            this.clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Room> listAllRooms() {
        // if (!user.getIsLoggedIn()){
        //     System.out.println(user.getIsLoggedIn());
        //     System.out.println("ya gabisa atuh");
        //     return null;
        // }

        Request<String> req = new Request<>("listAllRooms");

        writer.println(gson.toJson(req));

        String response = "";

        try {
            response = readInputFromServer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        @SuppressWarnings("unchecked")
        Response<String> res = gson.fromJson(response, Response.class);

        TypeToken<ArrayList<Room>> typeToken = new TypeToken<ArrayList<Room>>(){};

        ArrayList<Room> alRoom = gson.fromJson(res.getData(), typeToken);

        // for (int i = 0; i < alRoom.size(); i++) {
        //     System.out.println("id room : " + alRoom.get(i).getId());
        //     System.out.println("nama room : " + alRoom.get(i).getName());
        //     System.out.println("owner id : " + alRoom.get(i).getOwner_id());
        //     System.out.println("nama owner : " + alRoom.get(i).getOwner());
        // }

        return alRoom;
    }

    @Override
    public ArrayList<User> listAllMembersInTheRoom(Integer roomId) {
        Request<Integer> req = new Request<>("listAllMembersInTheRoom", roomId);

        writer.println(gson.toJson(req));

        String response = "";

        try {
            response = readInputFromServer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        @SuppressWarnings("unchecked")
        Response<String> res = gson.fromJson(response, Response.class);

        TypeToken<ArrayList<User>> typeToken = new TypeToken<ArrayList<User>>(){};

        ArrayList<User> alMembersInTheRoom = gson.fromJson(res.getData(), typeToken);
        return alMembersInTheRoom;
    }

    @Override
    public boolean joinRoom(String roomName) {
        UserRoom userRoom = new UserRoom(this.user.getId(), roomName);
        Request<String> req = new Request<>("joinRoom", gson.toJson(userRoom));

        writer.println(gson.toJson(req));
        
        String response = "";

        try {
            response = readInputFromServer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        @SuppressWarnings("unchecked")
        Response<String> res = gson.fromJson(response, Response.class);

        if(!res.getData().equals("200")){
            return false;
        }
        return true;
    }

    @Override
    public boolean isMemberInside(Integer roomId) {
        UserRoom userRoom = new UserRoom(this.user.getId(), roomId);
        Request<String> req = new Request<>("isMemberInside", gson.toJson(userRoom));

        writer.println(gson.toJson(req));

        String response = "";
        
        try{
            response = readInputFromServer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        @SuppressWarnings("unchecked")
        Response<Boolean> res = gson.fromJson(response, Response.class);

        return res.getData();
    }

    @Override
    public ArrayList<Chat> listAllChatsInTheRoom(Integer roomId) {
        Request<Integer> req = new Request<>("listAllChatsInTheRoom", roomId);
        writer.println(gson.toJson(req));
        
        String response = "";

        try{
            response = readInputFromServer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        @SuppressWarnings("unchecked")
        // Response<ArrayList<Chat>> res = gson.fromJson(response, Response.class);

        // ArrayList<Chat> alChatsInTheRoom = gson.fromJson(res.getData().toString(), typeToken);
        Response<String> res = gson.fromJson(response, Response.class);

        TypeToken<ArrayList<Chat>> typeToken = new TypeToken<ArrayList<Chat>>(){};

        ArrayList<Chat> alChatsInTheRoom = gson.fromJson(res.getData(), typeToken);

        return alChatsInTheRoom;
    }

    @Override
    public String getClientName() {
        return this.user.getName();
    }

    @Override
    public Integer getClientId() {
        return this.user.getId();
    }

    @Override
    public boolean isOwnerOfTheRoom(Integer userId, Integer roomId) {
        Room room = new Room(roomId, userId);
        Request<String> req = new Request<>("isOwnerOfTheRoom", gson.toJson(room)); 
        writer.println(gson.toJson(req));

        String response = "";

        try{
            response = readInputFromServer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        @SuppressWarnings("unchecked")
        Response<Boolean> res = gson.fromJson(response, Response.class);
        
        return res.getData();
    }

    @Override
    public boolean kickMember(Integer userId, Integer roomId) {
        UserRoom userRoom = new UserRoom(userId, roomId);
        Request<String> req = new Request<>("kickMember", gson.toJson(userRoom)); 
        writer.println(gson.toJson(req));

        String response = "";

        try{
            response = readInputFromServer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        @SuppressWarnings("unchecked")
        Response<Boolean> res = gson.fromJson(response, Response.class);
        System.out.println("datanya adalah : " + res.getData());
        return res.getData();
    }
}
