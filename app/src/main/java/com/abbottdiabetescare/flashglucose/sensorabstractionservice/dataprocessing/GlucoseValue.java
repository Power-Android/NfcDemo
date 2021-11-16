package com.abbottdiabetescare.flashglucose.sensorabstractionservice.dataprocessing;

public class GlucoseValue {
    private final int dataQuality;

    /* renamed from: id */
    private final int f36122id;
    private final int value;

    public GlucoseValue(int i, int i2, int i3) {
        this.f36122id = i;
        this.dataQuality = i2;
        this.value = i3;
    }

    public int getDataQuality() {
        return this.dataQuality;
    }

    public int getId() {
        return this.f36122id;
    }

    public int getValue() {
        return this.value;
    }
}