package com.studytrial.synrgyretrofit.edit

import com.studytrial.synrgyretrofit.network.ApiClient
import com.studytrial.synrgyretrofit.pojo.GetPersonsRespons
import com.studytrial.synrgyretrofit.pojo.PutPersonBody
import com.studytrial.synrgyretrofit.pojo.PutPersonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditActivityPresenter(val listener: EditActivityPresenter.Listerner) {

    fun editPerson(result: GetPersonsRespons.Result) {
        val objectEdit = PutPersonBody(result.firstName, result.lastName)
        ApiClient.apiService.updatePerson(objectEdit, result.iD.toString())
            .enqueue(object : Callback<PutPersonResponse> {
                override fun onResponse(
                    call: Call<PutPersonResponse>,
                    response: Response<PutPersonResponse>
                ) {
                    listener.onEditPersonSuccess("Edit Person Success")
                }

                override fun onFailure(call: Call<PutPersonResponse>, t: Throwable) {
                    t.message?.let {
                        listener.onEditPersonFailed(it)
                    }

                }
            })
    }

    fun gotoMainActivity() {
        listener.goToMainActivity()
    }

    interface Listerner {
        fun onEditPersonSuccess(message: String)
        fun onEditPersonFailed(errorMessage: String)
        fun goToMainActivity()
    }
}