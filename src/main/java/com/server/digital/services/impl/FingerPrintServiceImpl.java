package com.server.digital.services.impl;

import java.util.Map;

import com.server.digital.Constants;
import com.server.digital.services.FingerPrintService;

import org.springframework.stereotype.Service;

/*
 * @author luis.colmenarez
*/
@Service
public class FingerPrintServiceImpl implements FingerPrintService {

    private boolean wait = true;

    @Override
    public String statusServer() {
        return wait ? Constants.SERVER_WAIT : Constants.SERVER_BUSY;
    }

    @Override
    public String numbFingerPrints(Map<Object, Object> request) {
        return wait ? Constants.DUMMY_NUMB_FINGERP : Constants.SERVER_BUSY;
    }

    @Override
    public String sendFingerPrint(Map<Object, Object> request) {
        return wait ? Constants.DUMMY_SEND_FINGERP : Constants.SERVER_BUSY;
    }

    @Override
    public String statusReader() {
        return wait ? Constants.DUMMY_STATUS_READER : Constants.SERVER_BUSY;
    }

    @Override
    public String waitProcess() {
        return wait ? Constants.DUMMY_WAIT_PROCESS : Constants.SERVER_BUSY;
    }

    @Override
    public String startEnrollment() {
        return  wait ? Constants.DUMMY_START_ENROLLMENT : Constants.SERVER_BUSY;
    }

    @Override
    public String testWait(Integer seconds) throws InterruptedException {
        wait = false;
        Thread.sleep(seconds*1000);
        wait = true;
        return Constants.DUMMY_TEST_WAIT + seconds;
    }

}