package com.server.digital.services;

import java.util.Map;

/*
 * @author luis.colmenarez
*/
public interface FingerPrintService {
    String statusServer();

    String numbFingerPrints(Map<String, String> request);

    String sendFingerPrint(Map<String, String> request);

    String statusReader();

    String waitProcess();

    String startEnrollment();

    String testWait(Integer seconds) throws InterruptedException;
}