package com.server.digital.controllers;

import java.util.concurrent.atomic.AtomicLong;

import com.server.digital.models.FingerPrint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/*
 * @author lcolmenarez
*/
@RestController
@RequestMapping("/")
public class DigitalController {

    private static final String template = "Esto es un %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public FingerPrint test(@RequestParam(value = "content", defaultValue = "Test") String name) {
        return new FingerPrint(counter.incrementAndGet(), String.format(template, name));
    }
}