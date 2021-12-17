package com.tdd.SpringBootRestService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  @GetMapping("/greeting")
  public greeting(@RequestParam(value = "name") String name) {
    Greeting greeting = new Greeting();
  }
}
