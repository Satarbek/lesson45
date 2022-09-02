package kz.attractor.java.lesson44;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserModel {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserModel() {
        this.user = readUser();
    }

    public static User readUser(){
        String json = getJson("./user.json");
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }

    public static String getJson(String file){
        String json = "";
        Path path = Paths.get(file);
        try {
            json = Files.readString(path);
        }catch (IOException e){
            e.printStackTrace();
        }
        return json;
    }


}
