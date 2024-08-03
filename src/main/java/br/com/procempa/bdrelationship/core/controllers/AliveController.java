package br.com.procempa.bdrelationship.core.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alive")
public class AliveController {

    @GetMapping
    public String alive() {
        return "I'm alive!";
    }
    
}
