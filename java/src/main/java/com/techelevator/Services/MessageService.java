package com.techelevator.Services;

import com.techelevator.dao.BandDao;
import com.techelevator.dao.MessageDao;
import com.techelevator.model.Band;
import com.techelevator.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageDao messageDao;
    private final BandDao bandDao;
    public MessageService(MessageDao messageDao, BandDao bandDao) {
        this.messageDao = messageDao;
        this.bandDao = bandDao;
    }

    public Message getMessageById(int messageId) {
        return messageDao.getMessageById(messageId);
    }

    public List<Message> getAllMessagesToUser(int userId) {
        return messageDao.getAllMessagesToUser(userId);
    }

    public List<Message> getAllMessagesFromBand(int bandId) {
        return messageDao.getAllMessagesFromBand(bandId);
    }

    public Message sendMessage(Message message) {
        return messageDao.sendMessage(message);
    }

    public boolean hideMessageForUser(int messageId, int userId) {
        return messageDao.hideMessageForUser(messageId, userId);
    }

    public boolean hideMessageForBand(int messageId) {
        return messageDao.hideMessageForBand(messageId);
    }


}
