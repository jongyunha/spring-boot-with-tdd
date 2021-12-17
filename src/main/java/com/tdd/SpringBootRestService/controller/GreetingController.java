package com.tdd.SpringBootRestService.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
  private final Greeting greeting;

  AtomicLong counter = new AtomicLong();

  @Autowired
  public GreetingController(Greeting greeting) {
    this.greeting = greeting;
  }

  @GetMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name") String name) {
    greeting.setId(counter.incrementAndGet());

    StringBuilder content = new StringBuilder("Hey I learning Spring Boot from ");
    content.append(name);
    greeting.setContent(content.toString());
    return greeting;
  }
}
