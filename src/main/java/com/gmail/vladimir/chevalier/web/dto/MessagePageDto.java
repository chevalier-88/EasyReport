package com.gmail.vladimir.chevalier.web.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.gmail.vladimir.chevalier.web.entities.Message;
import com.gmail.vladimir.chevalier.web.utils.Views;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonView(Views.FullLevel.class)
public class MessagePageDto {
    private List<Message> messages;
    private int currentPage;
    private int totalPage;
}
