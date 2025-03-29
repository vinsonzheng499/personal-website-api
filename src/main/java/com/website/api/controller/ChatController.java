package com.website.api.controller;

import java.util.Map;
import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.website.api.service.ChatService;

import lombok.Data;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = { "http://localhost:4200"/*, "https://vinsonzheng499.github.io" */})
public class ChatController {

  private final ChatService chatService;

  public ChatController(ChatService chatService) {
    this.chatService = chatService;
  }

  @PostMapping
  public ChatResponse processQuery(@RequestBody ChatRequest request) {
    String response = chatService.processQuery(request.getQuery());
    return new ChatResponse(response);
  }

  @GetMapping("/ping")
  public Map<String, String> ping() {
    Map<String, String> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", "API is running!");
    return response;
  }

  @Data
  public static class ChatRequest {
    private String query;
  }

  @Data
  public static class ChatResponse {
    private final String response;
  }
}
