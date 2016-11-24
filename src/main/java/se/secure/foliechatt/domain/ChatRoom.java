package se.secure.foliechatt.domain;

import java.util.HashMap;
import java.util.Map;

public class ChatRoom {

    //TODO MAKE GUEST LIST
    private Long id;
    private String name;
    private Map<Key, User> roomUsers;

    public ChatRoom() {
        this(null);
    }
    // testing
    public ChatRoom(Long id) {
        this.id = id;
        roomUsers = new HashMap<Key, User>();
    }

    public boolean addUserIfNotPresent(Key key) {
        //boolean doesNotExists = !roomUsers.containsKey(key);

        Boolean u = roomUsers.containsKey(key.getValue());

        // roomUsers.containsKey(key);

        boolean doesNotExists =  roomUsers.keySet().stream()
                .filter(k -> k.getValue().equals(key.getValue()))
                .count() == 0l;
        if(doesNotExists) {
            System.out.println("adding user with key " + key.getValue() + " to chatroom with id " + id);
            addUser(key, new User());
            return true;
        } else {
            // nothing added, did exist
            return false;
        }

    }

    public User addUser(Key key, User value) {
        roomUsers.put(key, value);
        return roomUsers.put(key, value);
    }

    public User remove(Object key) {
        return roomUsers.remove(key);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Key, User> getRoomUsers() {
        return roomUsers;
    }

    public void setRoomUsers(Map<Key, User> roomUsers) {
        this.roomUsers = roomUsers;
    }
}
