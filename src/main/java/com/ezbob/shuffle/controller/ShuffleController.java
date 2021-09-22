package com.ezbob.shuffle.controller;

import com.ezbob.shuffle.service.ServiceLog;
import com.ezbob.shuffle.service.ShuffleService;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@Log
public class ShuffleController {
private final ShuffleService shuffleService;
private final ServiceLog serviceLog;
//    private static final Logger log = LoggerFactory.getLogger(ShuffleController.class);

    public ShuffleController(ShuffleService shuffleService, ServiceLog serviceLog) {
        this.shuffleService = shuffleService;
        this.serviceLog = serviceLog;
    }


@PostMapping("/service-shuffle")
    public String shuffle(@RequestParam(value = "inputValue") String inValue){
    serviceLog.sendLog("Input number: " + inValue);
    if (!shuffleService.validateValue(inValue).equals("OK")) {
        String errorMessage=shuffleService.validateValue(inValue);

        serviceLog.sendErrorLog(errorMessage);
        return errorMessage;
    }
    int inputValue = Integer.parseInt(inValue);

    int[] numberArray = shuffleService.createArray(inputValue);
    String shuffleResult = Arrays.toString(shuffleService.shuffle(numberArray));

    serviceLog.sendLog("Output array: " + shuffleResult);
    return String.format("The output %s ", shuffleResult);
    }

}
