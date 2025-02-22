package com.FmsProject.controllers;

import com.FmsProject.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/Request/")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping("noOfIncity/{username}")
    public int getNumOfIncity(@PathVariable String username) {
        return requestService.getNumOfIncity(username);
    }

    @GetMapping("noOfOfftime/{username}")
    public int getNumOfOfftime(@PathVariable String username) {
        return requestService.getNumOfOfftime(username);
    }

    @GetMapping("noOfField/{username}")
    public int getNumOfField(@PathVariable String username) {
        return requestService.getNumOfField(username);
    }
    @GetMapping("isdelegated/{username}")
    public String isdelegated(@PathVariable String username) {
        return requestService.isDelegated(username);
    }
}
