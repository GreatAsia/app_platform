package com.okay.appplatform.mapper.performance;

import com.okay.appplatform.domain.report.PerformanceHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {

    public void insertReport(PerformanceHistory performanceHistory);

    public void updateReport(PerformanceHistory performanceHistory);

    public PerformanceHistory findReport(int id);

    public void deleteReport(int id);

    public List<PerformanceHistory> findReportByRunId(int id);

    public Integer getLastRunId();

    public List<PerformanceHistory> findRunIdList();

    public List<PerformanceHistory> findSerialnoList();

    public List<PerformanceHistory> findReportBySerialno(String serialno);

    public void deleteReportBySerialno(String serialno);

    public List<PerformanceHistory> sreachSerialno(String serialno);

    public int getTotal();

}
