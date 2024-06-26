package org.example.model;

public class UserRoom {
    private Integer id;
    private Integer userId;
    private String memberName;
    private String roomName;
    
    public UserRoom(String memberName, String roomName){
        this.memberName = memberName;
        this.roomName = roomName;
    }

    public UserRoom(Integer userId, String roomName){
        this.userId = userId;
        this.roomName = roomName;
    }

    public UserRoom(Integer userId, Integer id){
        this.userId = userId;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMemberName(String memberName){
        this.memberName = memberName;
    }

    public String getMemberName(){
        return this.memberName;
    }

    public void setRoomName(String roomName){
        this.roomName = roomName;
    }

    public String getRoomName(){
        return this.roomName;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
