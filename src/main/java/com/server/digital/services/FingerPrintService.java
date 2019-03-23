package com.server.digital.services;

import java.util.Map;

/*
 * @author luis.colmenarez
*/
public interface FingerPrintService {
    String statusServer();

    String numbFingerPrints(Map<Object, Object> request);

    String sendFingerPrint(Map<Object, Object> request);

    String statusReader();

    String waitProcess();

    String startEnrollment();

    String testWait(Integer seconds) throws InterruptedException;
}