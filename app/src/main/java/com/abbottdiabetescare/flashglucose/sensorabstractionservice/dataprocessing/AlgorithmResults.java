package com.abbottdiabetescare.flashglucose.sensorabstractionservice.dataprocessing;

import com.abbottdiabetescare.flashglucose.sensorabstractionservice.Alarm;
import com.abbottdiabetescare.flashglucose.sensorabstractionservice.TrendArrow;

import java.util.List;

public class AlgorithmResults {
    private Alarm alarm;
    private List<GlucoseValue> historicGlucose;
    private boolean isActionable;
    private GlucoseValue realTimeGlucose;
    private TrendArrow trendArrow;

    public AlgorithmResults(List<GlucoseValue> list, GlucoseValue glucoseValue, TrendArrow trendArrow2, Alarm alarm2, boolean z) {
        this.historicGlucose = list;
        this.realTimeGlucose = glucoseValue;
        this.trendArrow = trendArrow2;
        this.alarm = alarm2;
        this.isActionable = z;
    }

    public Alarm getAlarm() {
        return this.alarm;
    }

    public List<GlucoseValue> getHistoricGlucose() {
        return this.historicGlucose;
    }

    public boolean getIsActionable() {
        return this.isActionable;
    }

    public GlucoseValue getRealTimeGlucose() {
        return this.realTimeGlucose;
    }

    public TrendArrow getTrendArrow() {
        return this.trendArrow;
    }
}