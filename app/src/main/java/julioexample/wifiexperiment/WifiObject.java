package julioexample.wifiexperiment;

import android.os.Parcel;
import android.os.Parcelable;
import android.net.wifi.ScanResult;
/**
 * Created by mariapb on 05/02/17.
 */

public class WifiObject implements Parcelable {


    public String mac;
    public String ssid;
    public int signal;


    public WifiObject(String mac, String ssid, int signal) {
        this.mac = mac;
        this.ssid = ssid;
        this.signal = signal;
    }

    public WifiObject(ScanResult result) {
        this.mac = result.BSSID;
        this.ssid = result.SSID;
        this.signal = result.level;
    }


    public void readFromParcel(Parcel in) {
        ssid = in.readString();
    }

    public WifiObject(Parcel in) {
        super();
        readFromParcel(in);
    }

    public String getSSID(){

        return  ssid;
    }

    public void setSSID(String SSID){
        this.ssid = SSID;
    }

    public String getMAC(){

        return  mac;
    }

    public void setMAC (String MAC){
        this.mac = MAC;
    }

    public int getWifiRSSI(){
        return signal;
    }



    public static final Parcelable.Creator<WifiObject> CREATOR = new Parcelable.Creator<WifiObject>() {
        public WifiObject createFromParcel(Parcel in) {
            return new WifiObject(in);
        }

        public WifiObject[] newArray(int size) {

            return new WifiObject[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(ssid);

    }
}
