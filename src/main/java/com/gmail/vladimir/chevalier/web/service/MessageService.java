package com.gmail.vladimir.chevalier.web.service;

import com.gmail.vladimir.chevalier.web.dto.EventType;
import com.gmail.vladimir.chevalier.web.dto.ObjectType;
import com.gmail.vladimir.chevalier.web.entities.Message;
import com.gmail.vladimir.chevalier.web.repo.MessageRepo;
import com.gmail.vladimir.chevalier.web.utils.Views;
import com.gmail.vladimir.chevalier.web.utils.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@Service
public class MessageService {

    private final MessageRepo messageRepo;
    private final BiConsumer<EventType, Message> wsSender;


    @Autowired
    public MessageService(MessageRepo messageRepo, WsSender wsSender) {
        this.messageRepo = messageRepo;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.BasicLevel.class);
    }


    public List<Message> list() {
        return messageRepo.findAll();
    }

    public Message create(Message message) {
        message.setCreationDate(LocalDateTime.now());
        Message updatedMessage = messageRepo.save(message);
        wsSender.accept(EventType.CREATE, updatedMessage);
        return updatedMessage;
    }

    public Message update(Message messageFromDb, Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id");
        Message updatedMessage = messageRepo.save(messageFromDb);
        wsSender.accept(EventType.UPDATE, updatedMessage);
        return updatedMessage;
    }

    public void delete(Message message) {
        messageRepo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }
}
