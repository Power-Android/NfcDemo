package com.power.nfcdemo.oopalgorithm;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.util.Log;

import com.abbottdiabetescare.flashglucose.sensorabstractionservice.AlarmConfiguration;
import com.abbottdiabetescare.flashglucose.sensorabstractionservice.ApplicationRegion;
import com.abbottdiabetescare.flashglucose.sensorabstractionservice.dataprocessing.DataProcessingException;
import com.abbottdiabetescare.flashglucose.sensorabstractionservice.dataprocessing.DataProcessingNative;
import com.abbottdiabetescare.flashglucose.sensorabstractionservice.dataprocessing.DataProcessingOutputs;
import com.abbottdiabetescare.flashglucose.sensorabstractionservice.dataprocessing.GlucoseValue;
import com.abbottdiabetescare.flashglucose.sensorabstractionservice.NonActionableConfiguration;
import com.power.nfcdemo.librestate.LibreState;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


public class AlgorithmRunner {

    static public OOPResults RunAlgorithm(long timestamp, Context context, byte[] packet, boolean usedefaultstatealways, String sensorid) {
        byte oldState[];

        DataProcessingNative data_processing_native= new DataProcessingNative(1095774808);/*DataProcessingType.APOLLO_PG2*/

        MyContextWrapper my_context_wrapper = new MyContextWrapper(context);

        data_processing_native.initialize(my_context_wrapper);
//        byte[] bDat = {-33, 0, 0, 1, 1, 2};
        byte[] bDat = {(byte)0xdf, 0x00, 0x00, 0x01, 01, 02};
        boolean bret = data_processing_native.isPatchSupported(bDat , ApplicationRegion.LEVEL_1);

        Log.e(TAG,"data_processing_native.isPatchSupported returned " + bret);
        if(!bret) {
            Log.e(TAG,"gson:");
            return new OOPResults(timestamp,-1, 0, null);
        }

        AlarmConfiguration alarm_configuration = new AlarmConfiguration(70, 180);
        NonActionableConfiguration non_actionable_configuration = new NonActionableConfiguration (true, true, 0, 40, 500, -2, 2);

        int sensorStartTimestamp = 0x0e181349;
        int sensorScanTimestamp = 0x0e1c4794;
        int currentUtcOffset = 0x0036ee80;
        if(usedefaultstatealways) {
            Log.e(TAG, "dabear: using default oldstate");
            oldState = LibreState.getDefaultState();
        } else {
            Log.e(TAG, "dabear:  getting state from persistent storage:");
            oldState = LibreState.getStateForSensor(sensorid, context);
        }



        Log.e(TAG, "dabear: oldstate is now :" + Arrays.toString(oldState));


        DataProcessingOutputs data_processing_outputs = null;
        try {

            data_processing_outputs = data_processing_native.processScan(alarm_configuration, non_actionable_configuration, packet, sensorStartTimestamp, sensorScanTimestamp, currentUtcOffset, oldState);

        } catch (DataProcessingException e) {
            Log.e(TAG,"cought exception on data_processing_native.processScan ", e);
            Log.e(TAG,"gson:");
            return new OOPResults(timestamp, -2, 0, null);
        }
        Log.e(TAG,"data_processing_native.processScan returned successfully " + data_processing_outputs);
        if(data_processing_outputs == null) {
            Log.e(TAG,"data_processing_native.processScan returned null");
            Log.e(TAG,"gson:");
            return new OOPResults(timestamp,-3, 0, null);
        }
        Log.e(TAG,"data_processing_native.processScan returned successfully " + data_processing_outputs.getAlgorithmResults().getRealTimeGlucose().getValue());

        byte[] newState = data_processing_outputs.getNewState();

        if(sensorid != null) {
            LibreState.saveSensorState(sensorid, newState, context);
        }

        OOPResults OOPResults = new OOPResults(timestamp,  data_processing_outputs.getAlgorithmResults().getRealTimeGlucose().getValue(),
                data_processing_outputs.getAlgorithmResults().getRealTimeGlucose().getId(),
                                                data_processing_outputs.getAlgorithmResults().getTrendArrow());

        if (data_processing_outputs.getAlgorithmResults().getHistoricGlucose() != null) {
            for(GlucoseValue glucoseValue : data_processing_outputs.getAlgorithmResults().getHistoricGlucose()) {
                Log.e(TAG, "  id " + glucoseValue.getId() + " value " + glucoseValue.getValue() + " quality " + glucoseValue.getDataQuality());
            }
            OOPResults.setHistoricBg(data_processing_outputs.getAlgorithmResults().getHistoricGlucose());
        } else {
            Log.e(TAG,"getAlgorithmResults.getHistoricGlucose() returned null");
            Log.e(TAG,"gson:");
        }
        Log.e(TAG,"gson:"+OOPResults.toGson());

        return OOPResults;

    }

    static public String getPackageCodePathNoCreate(Context context) {
        return MyContextWrapper.getPackageCodePathNoCreate(context);
    }


    static final  String TAG = "xOOPAlgorithm";

}

