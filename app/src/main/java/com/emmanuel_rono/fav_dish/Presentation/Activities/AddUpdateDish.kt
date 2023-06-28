package com.emmanuel_rono.fav_dish.Presentation.Activities

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Dialog
import android.app.appsearch.SetSchemaRequest.READ_EXTERNAL_STORAGE
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import android.Manifest
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import androidx.core.content.ContextCompat.startActivity
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


class AddUpdateDish : AppCompatActivity() , View.OnClickListener{
     private lateinit var updateDishBinding:ActivityAddUpdateDishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateDishBinding=ActivityAddUpdateDishBinding.inflate(layoutInflater)
        setContentView(updateDishBinding.root)
        setupActionbar()
        updateDishBinding.addDishImage.setOnClickListener(this)
    }
//Setting the Action Bar
    //create a function to do that
    private fun setupActionbar()
{
        setSupportActionBar(updateDishBinding.toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    //Set onclick Listerner
    updateDishBinding.toolbar.setNavigationOnClickListener{
    onBackPressed()
    }

}
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onClick(V: View?) {
        if (V != null) {
            when(V.id){
                R.id.add_dish_image-> {
                    ImageSelectionDialog()
                    return
                }
            }
        }
    }
    private fun ImageSelectionDialog()
    {
        val dialog=Dialog(this)
        val binding:PopupScreenAddDishBinding=PopupScreenAddDishBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)


        binding.addViaCamera.setOnClickListener{
            Dexter.withContext( this).withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ).withListener(object :MultiplePermissionsListener
            {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if(report.areAllPermissionsGranted())
                        {
                            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            startActivityForResult(intent, camra)
                    }

                    }
                }
                override fun onPermissionRationaleShouldBeShown(
                    Permissions: MutableList<PermissionRequest>?,
                    Token: PermissionToken?
                ) {
                    showAlertDialogForPermissions()
                }

            }).onSameThread().check()
            dialog.dismiss()
        }


        binding.addViaGallery.setOnClickListener{
           Dexter.withContext( this).withPermission(
               Manifest.permission.READ_EXTERNAL_STORAGE,
               ).withListener(object:PermissionListener
           {
               override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                  Toast.makeText(this@AddUpdateDish, "Got gallery Permission",Toast.LENGTH_LONG).show()
               }
               override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                   Toast.makeText(this@AddUpdateDish,"Denied Permission",Toast.LENGTH_SHORT).show()
               }
               override fun onPermissionRationaleShouldBeShown(
                   p0: PermissionRequest?,
                   p1: PermissionToken?
               ) {
                  showAlertDialogForPermissions()
               }

           }).onSameThread().check()
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)
        if(resultCode== camra){
            data?.let {
                val thumbnail:Bitmap= data.extras?.get("data") as Bitmap
                updateDishBinding.dishImage.setImageBitmap(thumbnail)
            }
        }
    }

    private fun showAlertDialogForPermissions()
    {
        AlertDialog.Builder(this).setMessage("ooops!, Looks Like you have Turned Off Permission Required for This Action")
            .setPositiveButton("GO TO SETTINGS")
            {_,_->
                try {
                    val intent= Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri= Uri.fromParts("package",packageName,null)
                    intent.data=uri
                    startActivity(intent)
                }catch (e:ActivityNotFoundException)
                {
                    e.printStackTrace()
                }
            }
            .setNegativeButton("cancel"){dialog,_ ->
                dialog.dismiss()}.show()
    }
    companion object{
        private const val camra=1
    }

}