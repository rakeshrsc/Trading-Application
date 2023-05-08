
package com.Trading.com.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/"})
public class SignalController {
    Logger logger = LoggerFactory.getLogger(SignalController.class);
    SignalHandler signalHandler = new Application();



    @GetMapping({"addSignal"})
    public String getSignal(@RequestParam int signalId) {
        String response = "";

        try {
             logger.trace("" + signalId + " coming inside getSignal method");
             signalHandler.handleSignal(signalId);
            response = "" + signalId + " Signals added successfully";
        } catch (Exception exp) {
             logger.trace(exp.getMessage());
        }

        return response;
    }
}
