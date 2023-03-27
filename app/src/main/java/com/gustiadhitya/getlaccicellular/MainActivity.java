package com.gustiadhitya.getlaccicellular;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String myLAC, myCI;
    TextView textLAC, textCI;
    Button buttonLacCi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textLAC = (TextView) findViewById(R.id.textLac);
        textCI = (TextView) findViewById(R.id.textCI);
        buttonLacCi = (Button) findViewById(R.id.buttonLacCi);

        buttonLacCi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textLAC.setText("...");
                textCI.setText("...");
                getPhoneLacCi();
            }
        });

    }

    private void getPhoneLacCi(){
        TelephonyManager telephony = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (telephony.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            final GsmCellLocation location = (GsmCellLocation) telephony.getCellLocation();
            if (location != null) {
                myLAC =  String.valueOf(location.getLac());
                myCI = String.valueOf(location.getCid());
                textLAC.setText(myLAC);
                textCI.setText(myCI);
            }
        }
    }
}