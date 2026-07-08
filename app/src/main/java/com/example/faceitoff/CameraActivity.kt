package com.example.faceitoff

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import java.io.File

class CameraActivity : AppCompatActivity() {

    private lateinit var previewView: PreviewView
    private lateinit var imageCapture: ImageCapture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        previewView = findViewById(R.id.previewView)
        val btnCapture = findViewById<Button>(R.id.btnCapture)

        startCamera()

        btnCapture.setOnClickListener {
            takePhoto()
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            imageCapture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build()

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                this,
                cameraSelector,
                preview,
                imageCapture
            )

        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {
        val photoFile = File(cacheDir, "face.jpg")
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    Toast.makeText(this@CameraActivity, "Photo Captured ✅", Toast.LENGTH_SHORT).show()
                    val bitmap = BitmapFactory.decodeFile(photoFile.absolutePath)
                    analyzeMood(bitmap)
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(this@CameraActivity, "Capture Failed ❌", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    private fun analyzeMood(bitmap: Bitmap) {
        val image = InputImage.fromBitmap(bitmap, 0)
        val options = FaceDetectorOptions.Builder()
            .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
            .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
            .build()

        FaceDetection.getClient(options).process(image)
            .addOnSuccessListener { faces ->
                if (faces.isEmpty()) {
                    Toast.makeText(this, "No face detected", Toast.LENGTH_SHORT).show()
                    return@addOnSuccessListener
                }

                val face = faces[0]
                val smile = face.smilingProbability ?: 0f
                val leftEye = face.leftEyeOpenProbability ?: 0f
                val rightEye = face.rightEyeOpenProbability ?: 0f
                val avgEye = (leftEye + rightEye) / 2f

                val mood = when {
                    smile >= 0.70f -> "happy"
                    smile in 0.40f..0.69f -> "neutral"
                    smile < 0.40f && avgEye < 0.30f -> "sad"
                    smile < 0.40f && avgEye > 0.65f -> "angry"
                    smile < 0.40f && avgEye in 0.30f..0.65f -> "stressed"
                    else -> "relaxed"
                }

                Toast.makeText(this, "Mood: $mood", Toast.LENGTH_SHORT).show()
                openSuggestionsScreen(mood)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Face analysis failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun openSuggestionsScreen(mood: String) {
        val intent = Intent(this, SuggestionsActivity::class.java)
        intent.putExtra("MOOD", mood)
        startActivity(intent)
        finish()
    }
}
