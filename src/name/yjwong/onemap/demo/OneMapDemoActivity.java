package name.yjwong.onemap.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.esri.android.map.Layer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.android.runtime.ArcGISRuntime;
import com.esri.core.geometry.Point;

public class OneMapDemoActivity extends Activity {
	
	MapView mMapView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Retrieve the map from XML layout.
		mMapView = (MapView) findViewById(R.id.map);
		
		// Create the layer.
		final Layer oneMapLayer = new ArcGISTiledMapServiceLayer(
				"http://e1.onemap.sg/arcgis/rest/services/BASEMAP/MapServer"
		);
		
		// We want to know when the map layer is loaded.
		mMapView.setOnStatusChangedListener(new OnStatusChangedListener() {
			private static final long serialVersionUID = 1L;

			public void onStatusChanged(Object source, STATUS status) {
				if (source == oneMapLayer && status == STATUS.LAYER_LOADED) {
					mMapView.centerAt(1.286389, 103.79, false);
					mMapView.setScale(1500, false);
				}
			}
		});
		
		// Add dynamic layer to MapView.
		mMapView.addLayer(oneMapLayer);
		
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