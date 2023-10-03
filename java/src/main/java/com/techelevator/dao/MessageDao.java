package com.techelevator.dao;

import com.techelevator.model.Message;

import java.util.List;

public interface MessageDao {
    Message getMessageById(int messageId);
    List<Message> getAllMessagesToUser(int userId);
    List<Message> getAllMessagesFromBand(int bandId);
    Message sendMessage(Message message);
    boolean hideMessageForUser(int messageId, int userId);
    boolean hideMessageForBand(int messageId);
}
