package com.technicalApi.technicalApi.controller;

import com.technicalApi.technicalApi.model.CallDetailRecord;
import com.technicalApi.technicalApi.repository.CallDetailRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    @Autowired
    private CallDetailRecordRepository recordRepository;

    @GetMapping("/search")
    public ResponseEntity<List<CallDetailRecord>> searchRecords(
            @RequestParam("record_date_start") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime recordDateStart,
            @RequestParam("record_date_end") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime recordDateEnd,
            @RequestParam(name = "msisdn", required = false) String msisdn,
            @RequestParam(name = "imsi", required = false) String imsi
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedRecordDateStart = recordDateStart.format(formatter);
        String formattedRecordDateEnd = recordDateEnd.format(formatter);

        List<CallDetailRecord> records = recordRepository.searchRecords(
                formattedRecordDateStart,
                formattedRecordDateEnd,
                msisdn,
                imsi
        );

        return ResponseEntity.ok(records);
    }

}