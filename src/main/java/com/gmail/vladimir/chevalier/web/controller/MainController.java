package com.gmail.vladimir.chevalier.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.gmail.vladimir.chevalier.web.dto.MessagePageDto;
import com.gmail.vladimir.chevalier.web.entities.User;
import com.gmail.vladimir.chevalier.web.repo.MessageRepo;
import com.gmail.vladimir.chevalier.web.service.MessageService;
import com.gmail.vladimir.chevalier.web.utils.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;


@Controller
@RequestMapping("/")
public class MainController {

    private final MessageService messageService;

    @Value("${spring.profiles.active}")
    private String profile;

    private final ObjectWriter writer;

    @Autowired
    public MainController(MessageService messageService, ObjectMapper mapper) {
        this.messageService = messageService;
        this.writer = mapper.setConfig(mapper.getSerializationConfig()).writerWithView(Views.FullLevel.class);
    }

    @GetMapping
    public String main(
            Model model,
            @AuthenticationPrincipal User user
    ) throws JsonProcessingException {
        HashMap<Object, Object> data = new HashMap<>();

        if (user != null) {
            data.put("profile", user);

            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(0, MessageRestController.MESSAGES_PER_PAGE, sort);
            MessagePageDto messaagePageDto = messageService.list(pageRequest);

            String messages = writer.writeValueAsString(messaagePageDto.getMessages());

            model.addAttribute("messages", messages);

            data.put("currentPage", messaagePageDto.getCurrentPage());
            data.put("totalPages", messaagePageDto.getTotalPage());
        } else {
            model.addAttribute("messages", "[]");
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }


}


//@Controller
//@RequestMapping("/")
//public class MainController {
//
//    private final MessageRepo messageRepo;
//
//    @Value("${spring.profiles.active}")
//    private String profile;
//
//    @Autowired
//    public MainController(MessageRepo messageRepo) {
//        this.messageRepo = messageRepo;
//    }
//
//    @GetMapping
//    public String index(Model model, @AuthenticationPrincipal User user) {
//        HashMap<Object, Object> data = new HashMap<>();
//
//        if (user != null) {
//            data.put("profile", user);
//            data.put("messages", messageRepo.findAll());
//        } else {
//            model.addAttribute("messages", "[]");
//        }
//        model.addAttribute("frontendData", data);
//        model.addAttribute("isDevMode", "dev".equals(profile));
//        return "index";
//    }
//
//
//}







