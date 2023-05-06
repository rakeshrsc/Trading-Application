package com.Trading.com.base;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SignalController {
    SignalHandler signalHandler = new Application();

    @GetMapping("addSignal")
    public void getSignal(@RequestParam int signalId) {
        try {
            signalHandler.handleSignal(signalId);
        } catch (Exception expection) {
            System.out.println(expection.getMessage());
            throw expection;
        }
    }
}

