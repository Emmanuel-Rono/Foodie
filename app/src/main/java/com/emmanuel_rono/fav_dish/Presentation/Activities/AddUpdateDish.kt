package com.emmanuel_rono.fav_dish.Presentation.Activities

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.emmanuel_rono.fav_dish.R
import com.emmanuel_rono.fav_dish.databinding.ActivityAddUpdateDishBinding
import com.emmanuel_rono.fav_dish.databinding.PopupScreenAddDishBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.PermissionListener

class AddUpdateDish : AppCompatActivity(), View.OnClickListener {

    private lateinit var updateDishBinding: ActivityAddUpdateDishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateDishBinding = ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(updateDishBinding.root)
        setupActionBar()
        updateDishBinding.addDishImage.setOnClickListener(this)
        updateDishBinding.dishimage.setOnClickListener(this)
    }

    private fun setupActionBar() {
        setSupportActionBar(updateDishBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        updateDishBinding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(view: View) {
        when (view.id) {
            R.id.addDishImage -> {
                imageSelectionDialog()
            }
        }
    }

    private fun imageSelectionDialog() {
        val dialog = Dialog(this)
        val binding: PopupScreenAddDishBinding = PopupScreenAddDishBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)

        binding.addViaCamera.setOnClickListener {
            requestCameraPermission()
            dialog.dismiss()
        }

        binding.addViaGallery.setOnClickListener {
            requestGalleryPermission()
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun requestCameraPermission() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report != null && report.areAllPermissionsGranted()) {
                        startCamera()
                    } else {
                        showPermissionDeniedDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    showPermissionDeniedDialog()
                }
            }).check()
    }

    private fun requestGalleryPermission() {
        Dexter.withContext(this)
            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    startGallery()
                    Toast.makeText(
                        this@AddUpdateDish,
                        "Got gallery permission",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Toast.makeText(
                        this@AddUpdateDish,
                        "Permission denied",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    showPermissionDeniedDialog()
                }
            }).check()
    }

    private fun startCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CODE_CAMERA)
    }
    private fun startGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_CODE_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_CAMERA -> {
                if (resultCode == RESULT_OK) {
                    val thumbnail: Bitmap? = data?.extras?.get("data") as? Bitmap
                    thumbnail?.let {
                        updateDishBinding.dishimage.setImageBitmap(thumbnail)
                        updateDishBinding.addDishImage.setImageDrawable(
                            ContextCompat.getDrawable(this, R.drawable.ic_edit_icon)
                        )
                    }

                }
            }
            REQUEST_CODE_GALLERY -> {
                if (resultCode == RESULT_OK && data != null) {
                    val selectedImage: Uri? = data.data
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImage)
                    updateDishBinding.dishimage.setImageBitmap(bitmap)
                    updateDishBinding.addDishImage.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_edit_icon))
                }
            }

        }
    }

    private fun showPermissionDeniedDialog() {
        AlertDialog.Builder(this)
            .setMessage("Oops! Looks like you have turned off the required permissions for this action.")
            .setPositiveButton("Go to Settings") { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    companion object {
        private const val REQUEST_CODE_CAMERA = 1
        private const val REQUEST_CODE_GALLERY=2
    }
}
