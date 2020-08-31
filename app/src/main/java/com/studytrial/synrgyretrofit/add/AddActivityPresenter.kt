package com.studytrial.synrgyretrofit.add

import com.studytrial.synrgyretrofit.network.ApiClient
import com.studytrial.synrgyretrofit.pojo.PostPersonBody
import com.studytrial.synrgyretrofit.pojo.PostPersonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivityPresenter(val listener: Listener) {
    fun addPerson(firstname: String, lastname: String) {
        val person =
            PostPersonBody(firstname, lastname)

        ApiClient.apiService.addPerson(person).enqueue(object : Callback<PostPersonResponse> {
            override fun onResponse(
                call: Call<PostPersonResponse>,
                response: Response<PostPersonResponse>
            ) {
                listener.onAddPersonSuccess("Success Adding Person")
            }

            override fun onFailure(call: Call<PostPersonResponse>, t: Throwable) {
                listener.onAddPersonFailure(t.toString())
            }
        })
    }

    fun gotoMainActivity() {
        listener.goToMainActivity()
    }

    interface Listener {
        fun onAddPersonSuccess(successMessage: String)
        fun onAddPersonFailure(failureMessage: String)
        fun goToMainActivity()
    }
}