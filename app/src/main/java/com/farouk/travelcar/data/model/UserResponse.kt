package com.farouk.travelcar.data.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USERID=0
@Entity
data class UserResponse(
    val name: String?,
    val email: String?,
    val phone: String?,
    val photoUrl: String?,
    var uid: Int?

)
{
   // we need to store only one user
    @PrimaryKey(autoGenerate= false)
    var id_db_user:Int = 0
}

