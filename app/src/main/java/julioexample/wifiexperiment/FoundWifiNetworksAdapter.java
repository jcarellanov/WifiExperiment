package julioexample.wifiexperiment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mariapb on 15/02/17.
 */

public class FoundWifiNetworksAdapter extends ArrayAdapter <WifiObject> {

    private Context context;
    //need to create an array that corresponds to readings
    private ArrayList<WifiObject> arrayFoundDevices;
    //private ArrayList arrayFoundDevices;

    public FoundWifiNetworksAdapter(Context context, ArrayList<WifiObject> arrayOfAlreadyPairedDevices) {
        super(context, R.layout.row_bt_scan_new_devices, arrayOfAlreadyPairedDevices);

        this.context = context;
        this.arrayFoundDevices = arrayOfAlreadyPairedDevices;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        WifiObject wifiObject = arrayFoundDevices.get(position);

        // 1. Create Inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.row_bt_scan_new_devices, parent, false);

        // 3. Get the widgets from the rowView
        TextView wifi_ssid = (TextView) rowView.findViewById(R.id.textview_wifi_scan_ssid);
        TextView wifi_mac = (TextView) rowView.findViewById(R.id.textview_wifi_mac);
        TextView wifi_strength = (TextView) rowView.findViewById(R.id.textview_wifi_signal_strength);

        // 4. Set the text for each widget

        wifi_ssid.setText("Name: " + wifiObject.getSSID());
        wifi_mac.setText("MAC: " + wifiObject.getMAC());
        wifi_strength.setText("RSSI: " + wifiObject.getWifiRSSI());

        /*ParcelUuid uuid[] = bluetoothObject.getBluetooth_uuids();
        if (uuid != null)
            bt_uuid.setText("uuid" + uuid[0]);*/


        // 5. return rowView
        return rowView;

    }
}
