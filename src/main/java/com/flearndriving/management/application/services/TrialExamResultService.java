package com.flearndriving.management.application.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.management.application.entities.TrialExamResult;
import com.flearndriving.management.application.respositories.TrialExamResultRepository;
import com.flearndriving.management.application.utils.DateTimeUtils;

@Service
public class TrialExamResultService {
	
	@Autowired
	TrialExamResultRepository trialExamResultRepository;
	
	public List<TrialExamResult> getDataReportTrialExamMonth() {
		Date today = new Date();
		Date firstDayOfMonthAgo = DateTimeUtils.getFirstDateOfMonth(DateTimeUtils.plusMonthToDate(today, -2));
		Date lastDayOfMonth = DateTimeUtils.getLastDateOfMonth(DateTimeUtils.plusMonthToDate(today, -1));
		return trialExamResultRepository.findReportByCreateDate(firstDayOfMonthAgo, lastDayOfMonth);
	}
}
