package com.technicalApi.technicalApi.repository;

import com.technicalApi.technicalApi.model.CallDetailRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CallDetailRecordRepository extends JpaRepository<CallDetailRecord, Long> {

    @Query(nativeQuery = true, value =
            "SELECT * FROM ussd.call_detail_record cdr " +
                    "WHERE TO_CHAR(cdr.record_date, 'YYYY-MM-DD HH24:MI:SS') BETWEEN :recordDateStart AND :recordDateEnd " +
                    "AND (NULLIF(:msisdn, '') IS NULL OR cdr.msisdn = :msisdn) " +
                    "AND (NULLIF(:imsi, '') IS NULL OR cdr.imsi = :imsi)")
    List<CallDetailRecord> searchRecords(
            @Param("recordDateStart") String recordDateStart,
            @Param("recordDateEnd") String recordDateEnd,
            @Param("msisdn") String msisdn,
            @Param("imsi") String imsi
    );
}