package com.example.ntdemo;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SimpleController {

    @GetMapping("/helloWorld")
    public String hello(){
        return  "Hello World!";
    }

    @GetMapping("/add")
    public int add(@RequestParam int a,@RequestParam  int b){
        return a+b;
    }

    @GetMapping("/multiply")
    public int multiply(@PathParam("a") int a){
        return a*10;
    }

}
