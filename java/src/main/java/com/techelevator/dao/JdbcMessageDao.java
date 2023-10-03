package com.techelevator.dao;

import com.techelevator.dao.mapper.MessageMapper;
import com.techelevator.model.Message;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Null;
import javax.xml.crypto.Data;
import java.util.List;

public class JdbcMessageDao implements MessageDao{
    private final MessageMapper messageMapper;
    private final JdbcTemplate jdbcTemplate;
    public JdbcMessageDao(MessageMapper messageMapper, JdbcTemplate jdbcTemplate) {
        this.messageMapper = messageMapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Message getMessageById(int messageId) {
        String sql = "SELECT * FROM messages WHERE message_id = ?;";
        try{
            return jdbcTemplate.queryForObject(sql, messageMapper, messageId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found");
        }
    }

    @Override
    public List<Message> getAllMessagesToUser(int userId) {
        String sql = "SELECT * FROM messages JOIN user_messages ON (messages.message_id " +
                "= user_messages.message_id) WHERE user_id = ?;";
        try{
            return jdbcTemplate.query(sql, messageMapper, userId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No messages found");
        }
    }

    @Override
    public List<Message> getAllMessagesFromBand(int bandId) {
        String sql = "SELECT * FROM messages WHERE sender_band_id = ?;";
        try{
            return jdbcTemplate.query(sql, messageMapper, bandId);
        }catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No messages found");
        }
    }

    @Override
    public Message sendMessage(Message message) {
        String sql = "INSERT INTO messages (sender_band_id, message_body) VALUES (?, ?)" +
                "RETURNING message_id;";
        int messageId = 0;
        try{
            messageId = jdbcTemplate.queryForObject(sql, Integer.class, message.getSenderBandId());
            message.setMessageId(messageId);
            return message;
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to send message");
        }
    }

    @Override
    public boolean hideMessageForUser(int messageId, int userId) {
        String sql = "UPDATE user_messages SET (is_deleted = false) WHERE (message_id = ? " +
                "AND user_id = ?)";
        try{
            return jdbcTemplate.update(sql, messageId, userId) == 1;
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to delete message.");
        }
    }

    @Override
    public boolean hideMessageForBand(int messageId) {
        String sql = "UPDATE messages SET (is_visible = false) WHERE message_id = ?;";
        try{
            return jdbcTemplate.update(sql, messageId) == 1;
        } catch (DataAccessException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to delete message.");
        }
    }
}