package com.mindex.challenge.service;

// Author: Prajakta Karve
import com.mindex.challenge.data.Compensation;

/** Service class for Compensation Service **/
public interface CompensationService {
    Compensation create(Compensation compensation);
    Compensation read(String employeeId);
}
