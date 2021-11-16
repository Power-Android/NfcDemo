package com.abbottdiabetescare.flashglucose.sensorabstractionservice;

public class AlarmConfiguration {
    private int highGlucoseAlarmThreshold;
    private int lowGlucoseAlarmThreshold;

    public AlarmConfiguration(int i, int i2) {
        this.lowGlucoseAlarmThreshold = i;
        this.highGlucoseAlarmThreshold = i2;
    }

    public int getHighGlucoseAlarmThreshold() {
        return this.highGlucoseAlarmThreshold;
    }

    public int getLowGlucoseAlarmThreshold() {
        return this.lowGlucoseAlarmThreshold;
    }
}