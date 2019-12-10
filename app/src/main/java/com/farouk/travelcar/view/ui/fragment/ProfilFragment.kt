package com.farouk.travelcar.view.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.farouk.travelcar.R
import com.farouk.travelcar.TravelCarApplication
import com.farouk.travelcar.data.model.UserResponse
import com.farouk.travelcar.data.repository.UserRepository
import kotlinx.android.synthetic.main.fragment_profil.*
import javax.inject.Inject
import android.content.Intent
import android.net.Uri
import com.farouk.travelcar.global.Constants.Companion.RESULT_LOAD_IMG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import com.squareup.picasso.Picasso
import java.io.File


/**
 * A placeholder fragment containing a simple view.
 */
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
        //imagePicker.setImageURI(Uri.parse(userData!!.photoUrl))
        Picasso.get().load(Uri.parse(userData!!.photoUrl)).into(imagePicker)

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
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            activity!!.startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG)
        }
        save_data_btn.setOnClickListener {
            saveUserData()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            dataUri = data!!.data!!
            Picasso.get().load(File(dataUri.path)).into(imagePicker)
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
                            dataUri.toString(), 1
                        )
                    )
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(): ProfilFragment {
            return ProfilFragment()
        }
    }
}