package julioexample.wifiexperiment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button enableWifi, disableWifi;
    public int MY_PERMISSIONS_ACCESS_FINE = 1;
    public int MY_PERMISSIONS_ACCESS_COARSE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_ACCESS_FINE);

        enableWifi = (Button) findViewById(R.id.buttonEnable);
        disableWifi = (Button) findViewById(R.id.buttonDisable);

        View.OnClickListener listenEnable = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //WifiManager wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
                //wifiManager.setWifiEnabled(true);
                scanForWifiNetworks();

            }
        };

        enableWifi.setOnClickListener(listenEnable);

        View.OnClickListener listenDisable = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(false);

            }
        };

        disableWifi.setOnClickListener(listenDisable);
    }

    private void scanForWifiNetworks() {
        // Start this on a new activity without passing any data to it
        Intent intent = new Intent(this, FoundWifiNetworks.class);
        startActivity(intent);
    }
}
