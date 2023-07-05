package com.emmanuel_rono.fav_dish.Presentation.Activities

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import com.emmanuel_rono.fav_dish.utils.Constants
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.emmanuel_rono.fav_dish.Domain.Adapters.dialoListAdapter
import com.emmanuel_rono.fav_dish.R
import com.emmanuel_rono.fav_dish.databinding.ActivityAddUpdateDishBinding
import com.emmanuel_rono.fav_dish.databinding.DialogListRecyclerviewBinding
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
    private lateinit var dcustomDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateDishBinding = ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(updateDishBinding.root)
        setupActionBar()
        updateDishBinding.addDishImage.setOnClickListener(this)
        updateDishBinding.dishimage.setOnClickListener(this)
        updateDishBinding.dishTypeName.setOnClickListener(this)
        updateDishBinding.dishCategoryName.setOnClickListener(this)
        updateDishBinding.dishTime.setOnClickListener(this)
        updateDishBinding.addDishButton.setOnClickListener(this)
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
            R.id.dish_category_name->{
                dialogListDialog(resources.getString(R.string.dish_Category),
                    Constants.dishCategory(),
                    Constants.DISH_CATEGORY)
                return
            }
            R.id.dish_Time->{
                dialogListDialog(resources.getString(R.string.dish_cook_time),
                    Constants.CookTime(),
                    Constants.DISH_COOKING_TIME)
                return
            }
            R.id.dish_Type_name->{
                dialogListDialog(resources.getString(R.string.dish_dish_type),
                    Constants.dish_Names(),
                    Constants.DISH_TYPE)
                return
            }
            R.id.add_dish_button ->
            {
                val category_title=updateDishBinding.dishCategoryName.text.toString().trim { it <=' '}
                val typeOfFood_title=updateDishBinding.dishTypeName.text.toString().trim { it <=' '}
                val time_title=updateDishBinding.dishTime.text.toString().trim { it <=' '}
                val cooking_guide_title=updateDishBinding.cookGuideSteps.text.toString().trim { it <=' '}
when{
    TextUtils.isEmpty(category_title) ->{
        Toast.makeText(this@AddUpdateDish, R.string.err_Category_Et, Toast.LENGTH_LONG).show()
    }
    TextUtils.isEmpty(category_title) ->{
        Toast.makeText(this@AddUpdateDish, R.string.err_Category_Et, Toast.LENGTH_LONG).show()
    }
    TextUtils.isEmpty(typeOfFood_title) ->{
        Toast.makeText(this@AddUpdateDish, R.string.err_TypeOfFood_Et, Toast.LENGTH_LONG).show()
    }
    TextUtils.isEmpty(time_title) ->{
        Toast.makeText(this@AddUpdateDish, R.string.err_Cooking_Time_Et, Toast.LENGTH_LONG).show()
    }
    TextUtils.isEmpty(cooking_guide_title) ->{
        Toast.makeText(this@AddUpdateDish, R.string.err_Cooking_Guide_Et, Toast.LENGTH_LONG).show()
    }
    else ->
    {
        Toast.makeText(this@AddUpdateDish,"All entries Correct",Toast.LENGTH_LONG).show()
    }
}

            }
        }
    }
    private fun imageSelectionDialog() {
        val customDialog= Dialog(this)
        val binding: PopupScreenAddDishBinding = PopupScreenAddDishBinding.inflate(layoutInflater)
        customDialog.setContentView(binding.root)
        binding.addViaCamera.setOnClickListener {
            requestCameraPermission()
            customDialog.dismiss()
        }

        binding.addViaGallery.setOnClickListener {
            requestGalleryPermission()
            customDialog.dismiss()
        }
        customDialog.show()
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

    private fun dialogListDialog(title:String, itemList:List<String>,selection:String) {
        //create the dialog
         dcustomDialog = Dialog(this)
        val binding: DialogListRecyclerviewBinding =
            DialogListRecyclerviewBinding.inflate(layoutInflater)
        dcustomDialog.setContentView(binding.root)
        binding.dialogTitle.text = title
        binding.listRecyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = dialoListAdapter(this, itemList, selection)
        binding.listRecyclerview.adapter = adapter
        dcustomDialog.show()

    }
        fun seletedListItem(item: String, selection: String) {
            //dcustomDialog = Dialog(this)
            when (selection) {
                Constants.DISH_TYPE -> {
                    dcustomDialog.dismiss()
                    updateDishBinding.dishTypeName.setText(item)
                }

                Constants.DISH_CATEGORY -> {
                    dcustomDialog.dismiss()
                    updateDishBinding.dishCategoryName.setText(item)
                }
               Constants.DISH_COOKING_TIME-> {
                    dcustomDialog.dismiss()
                    Constants.DISH_COOKING_TIME
                    updateDishBinding.dishTime.setText(item)
                }
            }


        }

    companion object {
        private const val REQUEST_CODE_CAMERA = 1
        private const val REQUEST_CODE_GALLERY=2
    }
}
