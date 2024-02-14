package org.example.model;

import lombok.*;
import org.example.modul.enums.BotState;
import org.example.modul.enums.Language;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long chatId;
    private String fullName;
    private String username;
    private String phoneNumber;
    private double longitude;
    private double latitude;
    private BotState state;
    private Language lang;

    public User(long chatId, String fullName, String username, BotState state) {
        this.chatId = chatId;
        this.fullName = fullName;
        this.username = username;
        this.state = state;
    }
}
