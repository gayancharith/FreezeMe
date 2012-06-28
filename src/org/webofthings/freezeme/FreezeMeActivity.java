package org.webofthings.freezeme;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FreezeMeActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button scanQrBtn = (Button) findViewById(R.id.scanButton);
		scanQrBtn.setOnClickListener(mScanQr);

	}

	protected Button.OnClickListener mScanQr = new Button.OnClickListener() {
		public void onClick(View v) {
			// launch the scanning app...
			Intent intent = new Intent("com.google.zxing.client.android.SCAN");
			intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
			startActivityForResult(intent, 0);
		}
	};

	/**
	 * Triggered when the scanning has finished!
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String contents = intent.getStringExtra("SCAN_RESULT");
				Intent foodVizuIntent = new Intent(this, FoodVisualizer.class);
				foodVizuIntent.setData(Uri.parse(contents));
				startActivity(foodVizuIntent);
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}
		}
	}

}