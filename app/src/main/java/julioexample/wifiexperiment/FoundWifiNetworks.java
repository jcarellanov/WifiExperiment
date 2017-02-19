package julioexample.wifiexperiment;

import android.app.ListActivity;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariapb on 05/02/17.
 */

public class FoundWifiNetworks extends ListActivity {

    private WifiManager mWifiManager;
    private ArrayList<WifiObject> arrayOfFoundWifiNetworks;
    boolean scanFinished = false;
    boolean gotResults = false;
    BroadcastReceiver mReceiver;
    StringBuilder sb = new StringBuilder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        displayListOfFoundDevices();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        registerReceiver(mReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        super.onResume();
    }

    private void displayListOfFoundDevices() {


        arrayOfFoundWifiNetworks = new ArrayList<WifiObject>();
        mWifiManager.setWifiEnabled(true);


        // Discover new APs

         mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                // When discovery finds a device
                if (action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                    WifiManager device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                    // Get the scan results
                    List<ScanResult> results = mWifiManager.getScanResults();
                    List<WifiObject> readings = new ArrayList<>();

                    for (ScanResult result : results) {
                        readings.add(new WifiObject(result));

                    }

                    
//hay que pasar el contenido de readings al adapter

                   /* FoundWifiNetworksAdapter adapter = new FoundWifiNetworksAdapter(getApplicationContext(), arrayOfFoundWifiNetworks);

                    // 2. setListAdapter
                    setListAdapter(adapter);*/
                }
            }
        };
        // Register the BroadcastReceiver
        IntentFilter filter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(mReceiver, filter);
        mWifiManager.startScan();
    }


}
