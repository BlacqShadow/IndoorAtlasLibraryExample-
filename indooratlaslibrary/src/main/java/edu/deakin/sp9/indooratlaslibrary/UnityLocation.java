package edu.deakin.sp9.indooratlaslibrary;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;
import com.indooratlas.android.sdk.IARegion;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

/**
 * Created by Rudra on 11/9/17.
 */

public class UnityLocation
        implements IALocationListener, IARegion.Listener{

    private IALocationManager mIALocationManager;
    private Context currentContext;
    public static double x;
    public static double y;

    public String testLibrary()
    {
        return "This is what we will return when using indoorAtlas Library";
    }






    // These two function will be called from unity to start location services
    public void setContext(Context context)
    {
        currentContext = context;
    }
    public void startLocationServices()
    {
        Log.d("TESTING", "Start Location services function called");
        mIALocationManager = IALocationManager.create(currentContext);
        final IARegion region = IARegion.floorPlan("1e7d3247-9aab-4de9-903e-aa0fcccd3b45");
        mIALocationManager.setLocation(IALocation.from(region));
        IALocationRequest request = IALocationRequest.create();
        mIALocationManager.requestLocationUpdates(request,this);
    }



    public double returnXCoordinate()
    {
        return x;
    }

    public double returnYCoordinate()
    {
        return y;
    }


    // This is the function where the co-ordinates are retrieved
    @Override
    public void onLocationChanged(IALocation iaLocation) {
        x = iaLocation.getLatitude();
        y = iaLocation.getLongitude();

        // Log the received co-ordinates to the console.
        Log.d("LOCATION UPDATE", "Latiutude: " + iaLocation.getLatitude());
        Log.d("LOCATION UPDATE", "LONGITUDE: " + iaLocation.getLongitude());
    }


    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }


    // These two methods can be used to identify different regions.
    @Override
    public void onEnterRegion(IARegion iaRegion) {

    }

    @Override
    public void onExitRegion(IARegion iaRegion) {

    }

}
