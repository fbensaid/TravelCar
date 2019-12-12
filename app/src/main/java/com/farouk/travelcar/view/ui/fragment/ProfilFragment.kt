package com.farouk.travelcar.view.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.farouk.travelcar.TravelCarApplication
import com.farouk.travelcar.data.model.UserResponse
import com.farouk.travelcar.data.repository.UserRepository
import kotlinx.android.synthetic.main.fragment_profil.*
import javax.inject.Inject
import android.content.Intent
import com.farouk.travelcar.global.Constants.Companion.RESULT_LOAD_IMG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import com.squareup.picasso.Picasso
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.farouk.travelcar.R
import permissions.dispatcher.*


/**
 * A placeholder fragment containing a simple view.
 */
@RuntimePermissions
class ProfilFragment : Fragment() {

    @Inject
    lateinit var workoutRepository: UserRepository
    private lateinit var dataUri: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profil, container, false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TravelCarApplication.appComponent.inject(this)

        workoutRepository.getUser().observe(this,
            Observer<UserResponse> { product ->
                if (product != null) {
                    settingData(product)
                }
            })
    }

     private fun settingData(userData: UserResponse) {
        clearTextView()
         Picasso.get().load(userData!!.photoUrl).into(imagePicker)
        email_user.text.append(userData.email.toString())
        phone_user.text.append(userData.phone.toString())
        name_user.text.append(userData.name.toString())

    }

    private fun clearTextView() {
        email_user.text.clear()
        phone_user.text.clear()
        name_user.text.clear()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imagePicker.setOnClickListener {
                showImagePickerWithPermissionCheck()
        }
        save_data_btn.setOnClickListener {
            saveUserData()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            dataUri = data.data!!
            Picasso.get().load(dataUri).into(imagePicker)
        }
    }

    private fun saveUserData() {
        runBlocking {
            withContext(Dispatchers.IO) {
                workoutRepository.insert(
                        UserResponse(
                            name_user.text.toString(),
                            email_user.text.toString(),
                            phone_user.text.toString(),
                            dataUri.toString() ?: "", 1
                        )
                    )
            }
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    private fun showRationaleDialog(message: Int, request: PermissionRequest) {
        AlertDialog.Builder(activity!!)
            .setPositiveButton(R.string.OK) { _, _ -> request.proceed() }
            .setNegativeButton(R.string.NO) { _, _ -> request.cancel() }
            .setCancelable(false)
            .setMessage(message)
            .show()
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
    fun showImagePicker() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        photoPickerIntent.type = "image/*"
        activity!!.startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG)
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
    fun showRationaleForImagePicker(request: PermissionRequest) {
        showRationaleDialog(R.string.app_name, request)
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
    fun onImagePickerDenied() {
        Toast.makeText(activity, R.string.permission_denied, Toast.LENGTH_LONG).show()
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
    fun onImagePickerNeverAskAgain() {
        Toast.makeText(activity, R.string.ask_permission, Toast.LENGTH_LONG).show()
    }
}