package com.gmail.vladimir.chevalier.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gmail.vladimir.chevalier.web.dto.EventType;
import com.gmail.vladimir.chevalier.web.dto.ObjectType;
import com.gmail.vladimir.chevalier.web.entities.Message;
import com.gmail.vladimir.chevalier.web.repo.MessageRepo;
import com.gmail.vladimir.chevalier.web.service.MessageService;
import com.gmail.vladimir.chevalier.web.utils.Views;
import com.gmail.vladimir.chevalier.web.utils.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;


@RestController
@RequestMapping("messages")
public class MessageRestController {

    private final MessageService messageService;

    @Autowired
    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @JsonView(Views.BasicLevel.class)
    public List<Message> list() {
        return messageService.list();
    }

    @GetMapping("{id}")
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }


    @PostMapping
    public Message create(@RequestBody Message message) {
        return messageService.create(message);
    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDb, @RequestBody Message message) {
        return messageService.update(messageFromDb, message);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageService.delete(message);
    }
}