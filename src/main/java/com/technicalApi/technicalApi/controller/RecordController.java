package com.technicalApi.technicalApi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.technicalApi.technicalApi.model.CallDetailRecord;
import com.technicalApi.technicalApi.repository.CallDetailRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    @Autowired
    private CallDetailRecordRepository recordRepository;

    @PostMapping("/search")
    public ResponseEntity<List<CallDetailRecord>> searchRecords(
            @RequestBody Map<String, Object> payload) throws JsonProcessingException {

        String recordDateStart = (String) payload.get("record_date_start");
        String recordDateEnd = (String) payload.get("record_date_end");
        String msisdn = (String) payload.get("msisdn");
        String imsi = (String) payload.get("imsi");

        List<CallDetailRecord> records = recordRepository.searchRecords(
                recordDateStart,
                recordDateEnd,
                msisdn,
                imsi);

        return ResponseEntity.ok(records);
    }

}