package com.abbottdiabetescare.flashglucose.sensorabstractionservice.dataprocessing;

public class DataProcessingOutputs {
    private final AlgorithmResults algorithmResults;
    private final int estimatedSensorEndTimestamp;
    private final int estimatedSensorStartTimestamp;
    private final boolean insertionIsConfirmed;
    private final byte[] newState;
    private final boolean sensorHasBeenRemoved;

    public DataProcessingOutputs(int i, int i2, boolean z, boolean z2, byte[] bArr, AlgorithmResults algorithmResults2) {
        this.estimatedSensorStartTimestamp = i;
        this.estimatedSensorEndTimestamp = i2;
        this.insertionIsConfirmed = z;
        this.sensorHasBeenRemoved = z2;
        this.newState = bArr;
        this.algorithmResults = algorithmResults2;
    }

    public AlgorithmResults getAlgorithmResults() {
        return this.algorithmResults;
    }

    public int getEstimatedSensorEndTimestamp() {
        return this.estimatedSensorEndTimestamp;
    }

    public int getEstimatedSensorStartTimestamp() {
        return this.estimatedSensorStartTimestamp;
    }

    public boolean getInsertionIsConfirmed() {
        return this.insertionIsConfirmed;
    }

    public byte[] getNewState() {
        return this.newState;
    }

    public boolean getSensorHasBeenRemoved() {
        return this.sensorHasBeenRemoved;
    }
}