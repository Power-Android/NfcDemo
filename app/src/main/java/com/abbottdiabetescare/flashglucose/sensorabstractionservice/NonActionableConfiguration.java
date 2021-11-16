package com.abbottdiabetescare.flashglucose.sensorabstractionservice;

public class NonActionableConfiguration {
    private boolean isEnabled;
    private boolean isVelocityCheckEnabled;
    private int maximumActionableValue;
    private double maximumPositiveActionableVelocity;
    private int minimumActionableId;
    private int minimumActionableValue;
    private double minimumNegativeActionableVelocity;

    public NonActionableConfiguration(boolean z, boolean z2, int i, int i2, int i3, double d, double d2) {
        this.isEnabled = z;
        this.isVelocityCheckEnabled = z2;
        this.minimumActionableId = i;
        this.minimumActionableValue = i2;
        this.maximumActionableValue = i3;
        this.minimumNegativeActionableVelocity = d;
        this.maximumPositiveActionableVelocity = d2;
    }

    public boolean getIsEnabled() {
        return this.isEnabled;
    }

    public boolean getIsVelocityCheckEnabled() {
        return this.isVelocityCheckEnabled;
    }

    public int getMaximumActionableValue() {
        return this.maximumActionableValue;
    }

    public double getMaximumPositiveActionableVelocity() {
        return this.maximumPositiveActionableVelocity;
    }

    public int getMinimumActionableId() {
        return this.minimumActionableId;
    }

    public int getMinimumActionableValue() {
        return this.minimumActionableValue;
    }

    public double getMinimumNegativeActionableVelocity() {
        return this.minimumNegativeActionableVelocity;
    }
}