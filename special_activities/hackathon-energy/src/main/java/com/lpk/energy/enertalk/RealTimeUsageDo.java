package com.lpk.energy.enertalk;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Minwoo on 2017. 4. 7..
 */

@Document(collection = "REAL_TIME_USAGE_TB")
public class RealTimeUsageDo {
    /**
     * timestamp                 timestamp when measurement was made
     * current               electricity current (A)
     * activePower               active power amount (mW)
     * billingActivePower                active power amount for bill charge (mW)
     * apparentPower                 apparent power amount (mW)
     * reactivePower                 reactive power amount (mW)
     * powerFactor               power factor (= active power / apparent power)
     * voltage               voltage amount (V)
     * positiveEnergy                cumulative positive energy (mWh)
     * negativeEnergy                cumulative negative energy (mWh)
     * positiveEnergyReactive                cumulative positive reactive energy (mVarh)
     * negativeEnergyReactive                cumulative negative reactive energy (mVarh)
     */

    long timestamp;
    long voltage;
    long current;
    long activePower;
    long billingActivePower;
    long apparentPower;
    long reactivePower;
    double powerFactor;
    long positiveEnergy;
    long negativeEnergy;
    long positiveEnergyReactive;
    long negativeEnergyReactive;

    public RealTimeUsageDo() {
    }

    public RealTimeUsageDo(long timestamp, long voltage, long current, long activePower, long billingActivePower, long apparentPower, long reactivePower, double powerFactor, long positiveEnergy, long negativeEnergy, long positiveEnergyReactive, long negativeEnergyReactive) {
        this.timestamp = timestamp;
        this.voltage = voltage;
        this.current = current;
        this.activePower = activePower;
        this.billingActivePower = billingActivePower;
        this.apparentPower = apparentPower;
        this.reactivePower = reactivePower;
        this.powerFactor = powerFactor;
        this.positiveEnergy = positiveEnergy;
        this.negativeEnergy = negativeEnergy;
        this.positiveEnergyReactive = positiveEnergyReactive;
        this.negativeEnergyReactive = negativeEnergyReactive;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getActivePower() {
        return activePower;
    }

    public void setActivePower(long activePower) {
        this.activePower = activePower;
    }

    public long getBillingActivePower() {
        return billingActivePower;
    }

    public void setBillingActivePower(long billingActivePower) {
        this.billingActivePower = billingActivePower;
    }

    public long getApparentPower() {
        return apparentPower;
    }

    public void setApparentPower(long apparentPower) {
        this.apparentPower = apparentPower;
    }

    public long getReactivePower() {
        return reactivePower;
    }

    public void setReactivePower(long reactivePower) {
        this.reactivePower = reactivePower;
    }

    public double getPowerFactor() {
        return powerFactor;
    }

    public void setPowerFactor(double powerFactor) {
        this.powerFactor = powerFactor;
    }

    public long getVoltage() {
        return voltage;
    }

    public void setVoltage(long voltage) {
        this.voltage = voltage;
    }

    public long getPositiveEnergy() {
        return positiveEnergy;
    }

    public void setPositiveEnergy(long positiveEnergy) {
        this.positiveEnergy = positiveEnergy;
    }

    public long getNegativeEnergy() {
        return negativeEnergy;
    }

    public void setNegativeEnergy(long negativeEnergy) {
        this.negativeEnergy = negativeEnergy;
    }

    public long getPositiveEnergyReactive() {
        return positiveEnergyReactive;
    }

    public void setPositiveEnergyReactive(long positiveEnergyReactive) {
        this.positiveEnergyReactive = positiveEnergyReactive;
    }

    public long getNegativeEnergyReactive() {
        return negativeEnergyReactive;
    }

    public void setNegativeEnergyReactive(long negativeEnergyReactive) {
        this.negativeEnergyReactive = negativeEnergyReactive;
    }
}
