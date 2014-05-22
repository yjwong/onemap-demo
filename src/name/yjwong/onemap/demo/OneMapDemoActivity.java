package name.yjwong.onemap.demo;

import android.app.Activity;
import android.os.Bundle;

import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.runtime.ArcGISRuntime;


public class OneMapDemoActivity extends Activity {
	
	MapView mMapView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Retrieve the map from XML layout.
		mMapView = (MapView) findViewById(R.id.map);
		
		// Add dynamic layer to MapView.
		mMapView.addLayer(new ArcGISTiledMapServiceLayer(
				"http://e1.onemap.sg/arcgis/rest/services/BASEMAP/MapServer"
		));
		
		// Set the client ID for Runtime SDK Licensing.
		// Replace this with your own.
		ArcGISRuntime.setClientId("XXXXXXXXXXXXXXXX");
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mMapView.pause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mMapView.unpause();
	}
}