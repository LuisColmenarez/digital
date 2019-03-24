package com.server.digital.services.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.server.digital.Constants;
import com.server.digital.common.Utils;
import com.server.digital.services.FingerPrintService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/*
 * @author luis.colmenarez
*/
@Service
public class FingerPrintServiceImpl implements FingerPrintService {

    private final static Logger LOGGER = LogManager.getLogger(FingerPrintServiceImpl.class);

    private boolean wait = true;

    @Override
    public String statusServer() {
        return this.wait ? Constants.SERVER_WAIT : Constants.SERVER_BUSY;
    }

    @Override
    public String numbFingerPrints(Map<String, String> request) {
        String response = Constants.ERROR;
        if(this.wait) {
            this.lockServer();
            Map<String, String[]> hashServer = new HashMap<String, String[]>();
            Map<String, String[]> hashFile;
            Map<String, String[]> hashRequest;
            
            try {
                String hashStr = request.get(Constants.HASH).toString();

                for (String key : hashStr.split(Constants.CHAR_COMMA)) {
                    hashServer.put(key, null);
                }

                hashFile = Utils.readCSVfile(Constants.PATH_FILE_CSV);
                hashRequest = this.hashRequest(hashFile, hashServer);

                String strRequest = "[";
                for (Object key : hashRequest.keySet()) {
                    strRequest += "\"" + key.toString() + "\",";
                }

                String aux_ = strRequest.substring(strRequest.length() - 1);
                if (aux_.equals(Constants.CHAR_COMMA)) {
                    strRequest = strRequest.substring(0, strRequest.length() - 1);
                }

                strRequest += "]";

                response = "{\"s\":1, \"d\":" + strRequest + ",\"m\"}";
            } catch (IOException e) {
                LOGGER.error("Exception Read CSV File: " + e.getMessage());
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            this.unlockServer();
        }
        return this.wait ? response : Constants.SERVER_BUSY;
    }

    @Override
    public String sendFingerPrint(Map<String, String> request) {
        return this.wait ? Constants.DUMMY_SEND_FINGERP : Constants.SERVER_BUSY;
    }

    @Override
    public String statusReader() {
        return this.wait ? Constants.DUMMY_STATUS_READER : Constants.SERVER_BUSY;
    }

    @Override
    public String waitProcess() {
        return this.wait ? Constants.DUMMY_WAIT_PROCESS : Constants.SERVER_BUSY;
    }

    @Override
    public String startEnrollment() {
        return  this.wait ? Constants.DUMMY_START_ENROLLMENT : Constants.SERVER_BUSY;
    }

    @Override
    public String testWait(Integer seconds) throws InterruptedException {
        wait = false;
        Thread.sleep(seconds*1000);
        wait = true;
        return Constants.DUMMY_TEST_WAIT + seconds;
    }   
    
    private void lockServer() {
        this.wait = false;
    }

    private void unlockServer() {
        this.wait = true;
    }

    private Map<String, String[]> hashRequest(Map<String, String[]> hashCsv, Map<String, String[]> hashServer) {
        Map<String, String[]> request = new HashMap<String, String[]>();
        for (Object key : hashCsv.keySet()) {
            boolean x = hashServer.containsKey(key);
            if (hashServer.containsKey(key)) {
                request.remove(key);
            }
        }
        return request;
    }

    private Map<String, String[]> hashToLoad(Map<String, String[]> hashCsv, Map<String, String[]> hashServer) {
        Map<String, String[]> hashLoad = new HashMap<String, String[]>();
        for (Object key : hashCsv.keySet()) {
            boolean x = hashServer.containsKey(key);
            if (hashServer.containsKey(key)) {
                hashLoad.put(key.toString(), hashCsv.get(key));
            }
        }
        return hashLoad;
    }
}