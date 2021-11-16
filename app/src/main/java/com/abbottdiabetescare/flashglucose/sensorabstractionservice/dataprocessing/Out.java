package com.abbottdiabetescare.flashglucose.sensorabstractionservice.dataprocessing;

public class Out<T> {
    private T value;

    public T value() {
        return this.value;
    }

    public void value(T t) {
        this.value = t;
    }
}