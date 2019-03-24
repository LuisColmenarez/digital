package com.server.digital.controllers;

import java.util.Map;

import com.server.digital.services.FingerPrintService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * @author luis.colmenarez
*/
@RestController
@RequestMapping("/")
public class DigitalController {

    @Autowired
    private FingerPrintService service;

    @RequestMapping(value="statusServer", method = RequestMethod.GET)
    public String statusServer() {
        return service.statusServer();
    }

    @RequestMapping(value="numbFingerPrints", method = RequestMethod.GET)
    public String numbFingerPrints(@RequestParam Map<String, String> request) {
        return service.numbFingerPrints(request);
    }

    @RequestMapping(value="sendFingerPrint", method = RequestMethod.POST)
    public String sendFingerPrint(@RequestParam Map<String, String> request) {
        return service.sendFingerPrint(request);
    }

    @RequestMapping(value="statusReader", method = RequestMethod.GET)
    public String statusReader() {
        return service.statusReader();
    }

    @RequestMapping(value="waitProcess", method = RequestMethod.GET)
    public String waitProcess() {
        return service.waitProcess();
    }

    @RequestMapping(value="startEnrollment", method = RequestMethod.GET)
    public String startEnrollment() {
        return service.startEnrollment();
    }

    @RequestMapping(value="testWait", method = RequestMethod.GET)
    public String testWait(@RequestParam(value = "seconds") Integer seconds) throws InterruptedException {
        return service.testWait(seconds);
    }

}