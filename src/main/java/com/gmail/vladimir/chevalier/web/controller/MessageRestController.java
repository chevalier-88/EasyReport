package com.gmail.vladimir.chevalier.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.gmail.vladimir.chevalier.web.dto.MessagePageDto;
import com.gmail.vladimir.chevalier.web.entities.Message;
import com.gmail.vladimir.chevalier.web.service.MessageService;
import com.gmail.vladimir.chevalier.web.utils.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("messages")
public class MessageRestController {

    public static final int MESSAGES_PER_PAGE = 10;

    private final MessageService messageService;


    @Autowired
    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @JsonView(Views.FullLevel.class)
    public MessagePageDto list(@PageableDefault(size = MESSAGES_PER_PAGE, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return messageService.list(pageable);
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