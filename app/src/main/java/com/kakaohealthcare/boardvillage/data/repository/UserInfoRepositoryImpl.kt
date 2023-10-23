package com.kakaohealthcare.boardvillage.data.repository

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kakaohealthcare.boardvillage.base.BaseApplication
import com.kakaohealthcare.boardvillage.domain.model.User
import com.kakaohealthcare.boardvillage.domain.repository.UserInfoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserInfoRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : UserInfoRepository {

    override suspend fun getUserInfo(userId: String) : User? {
        val reference = database.getReference("user_info")
        var userInfo : User? = null

        reference.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user: User? = dataSnapshot.getValue(User::class.java)
                if (user != null) {
                    userInfo = user
                    Log.i("tag","The read profile: " + user.ldap_id);
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.i("tag","The read failed: " + databaseError.code);
            }
        })

        return userInfo
    }

    override suspend fun setUserInfo(user: User) {
        val reference = database.reference.child("user_info").child(user.ldap_id)
        reference.setValue(user) { error, ref ->
            if(error != null){
                BaseApplication.prefs.setString("ldap_Id", user.ldap_id)
            } else {
                Log.i("tag","user save error");
            }
        }
    }
}

suspend fun <T> makeApiCall(
    dispatcher: CoroutineDispatcher,
    call: suspend () -> T
): Result<T> = runCatching {
    withContext(dispatcher) {
        call.invoke()
    }
}