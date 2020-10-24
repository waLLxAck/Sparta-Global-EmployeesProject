package com.sparta.svilen.View;

import com.sparta.svilen.Model.EmployeeDTO;
import com.sparta.svilen.DataAccessLayer.DatabaseDAO;
import org.apache.logging.log4j.LogManager;

import java.util.HashMap;


public class Logger {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(DatabaseDAO.class);

    public static void logDuplicates(HashMap<Integer, EmployeeDTO> dtoHashMap) {
        dtoHashMap.values().forEach(Logger::logToFile);
    }

    private static void logToFile(EmployeeDTO employeeDTO) {
        logger.info(employeeDTO.toString());
    }

    public static void logError(Exception e) {
        StringBuilder error = new StringBuilder();
        error.append("\nMessage: ").append(e.getMessage()).append("\nStack:\n");
        for (StackTraceElement stackTraceElement : (e.getStackTrace())) {
            error.append(stackTraceElement).append("\n");
        }
        logger.error(error);
    }
}
