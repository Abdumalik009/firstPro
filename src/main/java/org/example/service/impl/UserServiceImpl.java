package org.example.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.example.model.User;
import org.example.service.FileService;
import org.example.service.UserService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private List<User> users = new ArrayList<>();
    private FileService fileService= new FileServiceImpl();

private final String fileName ="user.txt";

    @Override
    public User create(User user) {
        if (get(user.getChatId()) == null) {
            User newUser = new User(
                    user.getChatId(),
                    user.getFullName(),
                    user.getUsername(),
                    user.getState()
            );
            boolean add = users.add(newUser);
            if (add){
                File file = new File("users.txt");
                try {
                    file.createNewFile();

                    FileWriter writer = new FileWriter(file);
                    writer.write(user.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        String dataFromFile =fileService.getDataFromFile(fileName);
        ObjectMapper objectMapper = new ObjectMapper();

        try {


            List<User> list = objectMapper.readValue(dataFromFile, List.class);
            list.add(user);

            Gson gson = new Gson();
            String json = gson.toJson(list);
            boolean b = fileService.writeDateToFile(json, fileName);
            System.out.println(b);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
        }


        return user;
    }

    @Override
    public User get(long chatId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getChatId() == chatId){
                System.out.println(i);
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User update(long chatId, User user) {
        User oldUser = get(chatId);
        for (int i = 0; i < users.size(); i++) {

            if(oldUser.getChatId() == chatId){
                oldUser.setState(user.getState());
                users.set(i, oldUser);

            }
        }
        return oldUser;
    }

    @Override
    public User delete ( long chatId){
        return null;
    }


}
