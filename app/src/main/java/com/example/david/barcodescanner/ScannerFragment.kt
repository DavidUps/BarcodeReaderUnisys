package com.example.david.barcodescanner

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.fragment_scanner.*


class ScannerFragment : Fragment() {

    private lateinit var detector: BarcodeDetector
    private lateinit var cameraSource: CameraSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scanner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_NOTIFICATION_URI)
        detector = BarcodeDetector.Builder(context).setBarcodeFormats(Barcode.ALL_FORMATS).build()
        detector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {}
            @SuppressLint("SetTextI18n")
            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
            override fun receiveDetections(detections: Detector.Detections<Barcode>?) {
                val barcodes = detections?.detectedItems
                if (barcodes!!.size() > 0) {
                    var result = barcodes.valueAt(0).displayValue
                    activity!!.runOnUiThread {
                        detector.release()
                        mediaPlayer.start()
                        (activity as MainActivity).openListFragment(result)
                    }
                }
            }
        })
        cameraSource = CameraSource.Builder(context, detector)
            .setRequestedPreviewSize(1024, 768)
            .setRequestedFps(25f)
            .setAutoFocusEnabled(true)
            .build()

        svScanner.holder.addCallback(object : SurfaceHolder.Callback2 {
            override fun surfaceRedrawNeeded(p0: SurfaceHolder?) {}
            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {}
            override fun surfaceDestroyed(p0: SurfaceHolder?) {
                cameraSource.stop()
            }

            override fun surfaceCreated(holder: SurfaceHolder?) {
                if (ContextCompat.checkSelfPermission(
                        activity!!.applicationContext,
                        android.Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    cameraSource.start(holder)
                } else ActivityCompat.requestPermissions(
                    activity!!,
                    arrayOf(android.Manifest.permission.CAMERA),
                    123
                )
            }
        })
    }

}
