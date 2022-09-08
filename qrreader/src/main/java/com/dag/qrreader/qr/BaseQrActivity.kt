package com.dag.qrreader.qr

import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity

abstract class BaseQrActivity : AppCompatActivity(), QrHelper.BarcodeHelperCallback{

    lateinit var barcodeHelper: QrHelper

    fun startBarcode(squareViewQr: SurfaceView) {
        createSurface(squareViewQr)
    }

    private fun createSurface(squareViewQr: SurfaceView) {
        squareViewQr.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {}
            override fun surfaceCreated(holder: SurfaceHolder) {
                startBarcodeDetection(squareViewQr)
            }
        })
    }

    override fun onStop() {
        super.onStop()
        barcodeHelper.releaseBarcodeDetector()
    }

    fun startBarcodeDetection(squareViewQr: SurfaceView) {
        barcodeHelper.apply {
            initCameraSource(squareViewQr)
            startBarcodeDetection()
        }
    }

    override fun onBarcodeDetected(displayValue: String) {
        barcodeHelper.startBarcodeDetection()
    }

    override fun onError(message: String) {}

}