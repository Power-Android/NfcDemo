package com.abbottdiabetescare.flashglucose.sensorabstractionservice.dataprocessing;

public class DataProcessingException extends Exception {
    private static long serialVersionUID = 1;
    private DataProcessingResult result;

    public DataProcessingException(DataProcessingResult dataProcessingResult) {
        super(dataProcessingResult.toString());
        this.result = dataProcessingResult;
    }

    public DataProcessingResult getResult() {
        return this.result;
    }
}