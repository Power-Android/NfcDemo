package com.power.nfcdemo.oopalgorithm;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.power.nfcdemo.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NfcActivity extends Activity {

    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    private TextView mText;
    private NfcV nfcv = null;
    private byte[] tag_data_raw = new byte[360];
    private byte[] mOneBlock;
    private byte[] packet = {(byte) 0x3a, (byte) 0xcf, (byte) 0x10, (byte) 0x16, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x4f, (byte) 0x11, (byte) 0x08, (byte) 0x10, (byte) 0xad, (byte) 0x02, (byte) 0xc8, (byte) 0xd4,
            (byte) 0x5b, (byte) 0x00, (byte) 0xaa, (byte) 0x02, (byte) 0xc8, (byte) 0xb4, (byte) 0x1b, (byte) 0x80,
            (byte) 0xa9, (byte) 0x02, (byte) 0xc8, (byte) 0x9c, (byte) 0x5b, (byte) 0x00, (byte) 0xa9, (byte) 0x02,
            (byte) 0xc8, (byte) 0x8c, (byte) 0x1b, (byte) 0x80, (byte) 0xb0, (byte) 0x02, (byte) 0xc8, (byte) 0x30,
            (byte) 0x5c, (byte) 0x80, (byte) 0xb0, (byte) 0x02, (byte) 0x88, (byte) 0xe6, (byte) 0x9c, (byte) 0x80,
            (byte) 0xb8, (byte) 0x02, (byte) 0xc8, (byte) 0x3c, (byte) 0x9d, (byte) 0x80, (byte) 0xb8, (byte) 0x02,
            (byte) 0xc8, (byte) 0x60, (byte) 0x9d, (byte) 0x80, (byte) 0xa1, (byte) 0x02, (byte) 0xc8, (byte) 0xdc,
            (byte) 0x9e, (byte) 0x80, (byte) 0xab, (byte) 0x02, (byte) 0xc8, (byte) 0x14, (byte) 0x9e, (byte) 0x80,
            (byte) 0xa9, (byte) 0x02, (byte) 0xc8, (byte) 0xc0, (byte) 0x9d, (byte) 0x80, (byte) 0xab, (byte) 0x02,
            (byte) 0xc8, (byte) 0x78, (byte) 0x9d, (byte) 0x80, (byte) 0xaa, (byte) 0x02, (byte) 0xc8, (byte) 0x40,
            (byte) 0x9d, (byte) 0x80, (byte) 0xa8, (byte) 0x02, (byte) 0xc8, (byte) 0x08, (byte) 0x9d, (byte) 0x80,
            (byte) 0xa8, (byte) 0x02, (byte) 0xc8, (byte) 0x2c, (byte) 0x5c, (byte) 0x80, (byte) 0xad, (byte) 0x02,
            (byte) 0xc8, (byte) 0xf8, (byte) 0x5b, (byte) 0x00, (byte) 0x29, (byte) 0x06, (byte) 0xc8, (byte) 0xf4,
            (byte) 0x9b, (byte) 0x80, (byte) 0xc9, (byte) 0x05, (byte) 0xc8, (byte) 0x8c, (byte) 0xde, (byte) 0x80,
            (byte) 0xc3, (byte) 0x05, (byte) 0xc8, (byte) 0x28, (byte) 0x9e, (byte) 0x80, (byte) 0x2c, (byte) 0x06,
            (byte) 0xc8, (byte) 0xd0, (byte) 0x9e, (byte) 0x80, (byte) 0x7b, (byte) 0x06, (byte) 0x88, (byte) 0xa6,
            (byte) 0x9e, (byte) 0x80, (byte) 0xf9, (byte) 0x05, (byte) 0xc8, (byte) 0xb0, (byte) 0x9e, (byte) 0x80,
            (byte) 0x99, (byte) 0x05, (byte) 0xc8, (byte) 0xf0, (byte) 0x9e, (byte) 0x80, (byte) 0x2e, (byte) 0x05,
            (byte) 0xc8, (byte) 0x00, (byte) 0x9f, (byte) 0x80, (byte) 0x81, (byte) 0x04, (byte) 0xc8, (byte) 0x48,
            (byte) 0xa0, (byte) 0x80, (byte) 0x5d, (byte) 0x04, (byte) 0xc8, (byte) 0x38, (byte) 0x9d, (byte) 0x80,
            (byte) 0x12, (byte) 0x04, (byte) 0xc8, (byte) 0x10, (byte) 0x9e, (byte) 0x80, (byte) 0xcf, (byte) 0x03,
            (byte) 0xc8, (byte) 0x4c, (byte) 0x9e, (byte) 0x80, (byte) 0x6f, (byte) 0x03, (byte) 0xc8, (byte) 0xb8,
            (byte) 0x9e, (byte) 0x80, (byte) 0x19, (byte) 0x03, (byte) 0xc8, (byte) 0x40, (byte) 0x9f, (byte) 0x80,
            (byte) 0xc5, (byte) 0x02, (byte) 0xc8, (byte) 0xf4, (byte) 0x9e, (byte) 0x80, (byte) 0xaa, (byte) 0x02,
            (byte) 0xc8, (byte) 0xf8, (byte) 0x5b, (byte) 0x00, (byte) 0xa2, (byte) 0x04, (byte) 0xc8, (byte) 0x38,
            (byte) 0x9a, (byte) 0x00, (byte) 0xd1, (byte) 0x04, (byte) 0xc8, (byte) 0x28, (byte) 0x9b, (byte) 0x80,
            (byte) 0xe4, (byte) 0x04, (byte) 0xc8, (byte) 0xe0, (byte) 0x1a, (byte) 0x80, (byte) 0x8f, (byte) 0x04,
            (byte) 0xc8, (byte) 0x20, (byte) 0x9b, (byte) 0x80, (byte) 0x22, (byte) 0x06, (byte) 0xc8, (byte) 0x50,
            (byte) 0x5b, (byte) 0x80, (byte) 0xbc, (byte) 0x06, (byte) 0xc8, (byte) 0x54, (byte) 0x9c, (byte) 0x80,
            (byte) 0x7f, (byte) 0x05, (byte) 0xc8, (byte) 0x24, (byte) 0x5c, (byte) 0x80, (byte) 0xc9, (byte) 0x05,
            (byte) 0xc8, (byte) 0x38, (byte) 0x5c, (byte) 0x80, (byte) 0x38, (byte) 0x05, (byte) 0xc8, (byte) 0xf4,
            (byte) 0x1a, (byte) 0x80, (byte) 0x37, (byte) 0x07, (byte) 0xc8, (byte) 0x84, (byte) 0x5b, (byte) 0x80,
            (byte) 0xfb, (byte) 0x08, (byte) 0xc8, (byte) 0x4c, (byte) 0x9c, (byte) 0x80, (byte) 0xfb, (byte) 0x09,
            (byte) 0xc8, (byte) 0x7c, (byte) 0x9b, (byte) 0x80, (byte) 0x77, (byte) 0x0a, (byte) 0xc8, (byte) 0xe4,
            (byte) 0x5a, (byte) 0x80, (byte) 0xdf, (byte) 0x09, (byte) 0xc8, (byte) 0x88, (byte) 0x9f, (byte) 0x80,
            (byte) 0x6d, (byte) 0x08, (byte) 0xc8, (byte) 0x2c, (byte) 0x9f, (byte) 0x80, (byte) 0xc3, (byte) 0x06,
            (byte) 0xc8, (byte) 0xb0, (byte) 0x9d, (byte) 0x80, (byte) 0xd9, (byte) 0x11, (byte) 0x00, (byte) 0x00,
            (byte) 0x72, (byte) 0xc2, (byte) 0x00, (byte) 0x08, (byte) 0x82, (byte) 0x05, (byte) 0x09, (byte) 0x51,
            (byte) 0x14, (byte) 0x07, (byte) 0x96, (byte) 0x80, (byte) 0x5a, (byte) 0x00, (byte) 0xed, (byte) 0xa6,
            (byte) 0x0e, (byte) 0x6e, (byte) 0x1a, (byte) 0xc8, (byte) 0x04, (byte) 0xdd, (byte) 0x58, (byte) 0x6d};


    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_main);

        mText = (TextView) findViewById(R.id.TextView);

        mAdapter = NfcAdapter.getDefaultAdapter(this);
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter nfcv = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        mFilters = new IntentFilter[]{nfcv};
        mTechLists = new String[][]{new String[]{NfcV.class.getName()}};
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        if (intent.getAction().trim().equals("android.nfc.action.TECH_DISCOVERED")) {
            Log.d("intent", "intent tag=" + NfcAdapter.EXTRA_TAG + ", " + NfcAdapter.EXTRA_ID);
            nfcv = NfcV.get(tag);
        }
        String tagString = getTagString(tag.getId());

        //读数据
        try {
            if (nfcv.isConnected())
                nfcv.close();
            nfcv.connect();

            int offset = 2;
//            int offset = 1;

            for (int i = 0; i <= 42; i++) {

                byte[] cmd;

                cmd = new byte[]{
                        (byte) 0x60,                  // flags: addressed (= UID field present)
                        (byte) 0x20,
                        (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,  // placeholder for tag UID
                        (byte) (i & 0x0ff),
                        (byte) ((0) & 0x0ff)
                };

                System.arraycopy(tag.getId(), 0, cmd, 2, 8);

//                cmd = new byte[]{
//                        (byte) 0x00, // Flags
//                        (byte) 0x20, // Command: Read
//                };


                mOneBlock = new byte[8];

                while (true) {
                    try {
                        mOneBlock = nfcv.transceive(cmd);
                        break;
                    } catch (IOException e) {

                    }
                }

                mOneBlock = Arrays.copyOfRange(mOneBlock, offset, mOneBlock.length);

                tag_data_raw[i * 8 + 0] = mOneBlock[0];
                tag_data_raw[i * 8 + 1] = mOneBlock[1];
                tag_data_raw[i * 8 + 2] = mOneBlock[2];
                tag_data_raw[i * 8 + 3] = mOneBlock[3];
                tag_data_raw[i * 8 + 4] = mOneBlock[4];
                tag_data_raw[i * 8 + 5] = mOneBlock[5];
                tag_data_raw[i * 8 + 6] = mOneBlock[6];
                tag_data_raw[i * 8 + 7] = mOneBlock[7];
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.e("Power", "这个是copy的格式1： " + Arrays.toString(Arrays.copyOfRange(packet, 0, 344)));
        Log.e("Power", "这个是copy的格式2： " + Arrays.toString(Arrays.copyOfRange(tag_data_raw, 0, 344)));
        /**
         *  paket是能读出来的
         *  tag_data_raw的获取方式目前还未知
         */
        int sgv = (int) AlgorithmRunner.RunAlgorithm(System.currentTimeMillis(), getApplicationContext(), Arrays.copyOfRange(tag_data_raw, 0, 344), true, "").currentBg;

        if (sgv == 63) {
            mText.setText("Algorithm worked correctly");
        } else {
            String ApkName = AlgorithmRunner.getPackageCodePathNoCreate(getApplicationContext());
            File f = new File(ApkName);
            mText.setText("Algorithm returned " + sgv + " apk file size " + f.length());
            Log.e("socialdiabetes", "Deleting file due to apk failure" + ApkName);
            f.delete();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onDestroy() {
        if (nfcv != null && nfcv.isConnected())
            try {
                nfcv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        super.onDestroy();
    }

    public static String getTagString(byte[] bArr) {
        if (bArr.length <= 0) {
            return "";
        }
        byte[] bArr2 = new byte[11];
        System.arraycopy(bArr, 0, bArr2, 3, 8);
        return tagByteToStr(bArr2);
    }

    public static String tagByteToStr(byte[] bArr) {
        String[] strArr = {"0", "1", "2", "3",
                "4", "5", "6", "7", "8", "9", "A",
                "C", "D", "E",
                "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "T",
                "U", "V",
                "W", "X", "Y", "Z"};
        byte[] bArr2 = {0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 2; i < 8; i++) {
            bArr2[i - 2] = bArr[10 - i];
        }
        bArr2[6] = 0;
        bArr2[7] = 0;
        String str = "";
        for (int i2 = 0; i2 < 8; i2++) {
            str = str + String.format("%8s", Integer.toBinaryString(bArr2[i2] & 255)).replace(' ', '0');
        }
        char[] cArr = {0, 0, 0, 0, 0};
        String str2 = "0";
        for (int i3 = 0; i3 < 10; i3++) {
            for (int i4 = 0; i4 < 5; i4++) {
                cArr[i4] = str.charAt((i3 * 5) + i4);
            }
            int i5 = ((cArr[0] - '0') * 16) + ((cArr[1] - '0') * 8) + ((cArr[2] - '0') * 4) + ((cArr[3] - '0') * 2) + ((cArr[4] - '0') * 1);
            str2 = str2 + strArr[i5];
        }
        return str2;
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String byteToHex(byte byte_) {
        char[] hexChars = new char[2];

        int v = byte_ & 0xFF;
        hexChars[0] = hexArray[v >>> 4];
        hexChars[1] = hexArray[v & 0x0F];

        return new String(hexChars);
    }

}