package com.example.pricecomparisonscanner.ui.activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.pricecomparisonscanner.R;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CameraPreview;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/*public class ScannerActivity extends com.example.pricecomparisonscanner.ui.helpers.BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int PERMISSION_CAMERA_REQUEST = 0;

    // UI
    private DecoratedBarcodeView barcodeScannerView;
    private TextView permissionNeededExplanation;

    // Logic
    private BeepManager beepManager;

    private final CameraPreview.StateListener stateListener = new CameraPreview.StateListener() {
        @Override
        public void previewSized() { }
        @Override
        public void previewStarted() { }
        @Override
        public void previewStopped() { }
        @Override
        public void cameraError(Exception error) { }
        @Override
        public void cameraClosed() { }
    };

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            String contents = result.toString();
            if(contents.isEmpty()) {
                return;
            }

            barcodeScannerView.setStatusText(result.getText());

            beepManager.playBeepSoundAndVibrate();

            //ResultActivity.startResultActivity(ScannerActivity.this, result);

        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scanner);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        permissionNeededExplanation = findViewById(R.id.activity_scanner_permission_needed_explanation);
        barcodeScannerView = findViewById(R.id.zxing_barcode_scanner);

        barcodeScannerView.getBarcodeView().addStateListener(stateListener);

        beepManager = new BeepManager(this);

        if (!preferences.getBoolean("pref_enable_beep_on_scan", true)) {
            beepManager.setBeepEnabled(false);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initScanWithPermissionCheck();
        } else {
            initScan();
        }
    }

    @TargetApi(23)
    private void initScanWithPermissionCheck() {
        boolean hasCameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;

        if (!hasCameraPermission) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_CAMERA_REQUEST);
            showCameraPermissionRequirement(true);
        } else {
            initScan();
        }
    }

    private void showCameraPermissionRequirement(boolean show) {
        barcodeScannerView.setVisibility(show ? View.GONE : View.VISIBLE);

        if(show) {
            barcodeScannerView.pause();
        } else {
            barcodeScannerView.resume();
        }

        permissionNeededExplanation.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void initScan() {
        showCameraPermissionRequirement(false);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Collection<BarcodeFormat> formats = Arrays.asList(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39);
        barcodeScannerView.getBarcodeView().setDecoderFactory(new DefaultDecoderFactory(formats));
        barcodeScannerView.initializeFromIntent(getIntent());
        barcodeScannerView.decodeSingle(callback);
        barcodeScannerView.resume();
    }

    @Override
    protected int getNavigationDrawerID() {
        return R.id.nav_scan;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            showCameraPermissionRequirement(true);
        } else {
            barcodeScannerView.setStatusText(null);
            barcodeScannerView.decodeSingle(callback);
            barcodeScannerView.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeScannerView.pauseAndWait();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_CAMERA_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initScan();
            } else {
                // TODO
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_scanner_permission_needed_container:
                initScanWithPermissionCheck();
                break;
            default:
                break;
        }
    }
}*/
