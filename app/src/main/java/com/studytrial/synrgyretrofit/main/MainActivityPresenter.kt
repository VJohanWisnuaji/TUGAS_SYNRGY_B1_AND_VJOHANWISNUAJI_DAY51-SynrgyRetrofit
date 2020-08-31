package com.studytrial.synrgyretrofit.main

import com.studytrial.synrgyretrofit.network.ApiClient
import com.studytrial.synrgyretrofit.pojo.DeletePersonResponse
import com.studytrial.synrgyretrofit.pojo.GetPersonsRespons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter(val listener: Listener) {
    fun getPerson() {
        listener.showProgressDialog()
        ApiClient.apiService.getAllPersons().enqueue(object : Callback<GetPersonsRespons> {
            override fun onResponse(
                call: Call<GetPersonsRespons>,
                response: Response<GetPersonsRespons>
            ) {
                response.body()?.result?.let {
                    listener.onPersonListSuccess(it)
                }
                listener.hideProgressDialog()
            }

            override fun onFailure(call: Call<GetPersonsRespons>, t: Throwable) {
                t.localizedMessage?.let {
                    listener.onGetPersonListFailure(it)
                }
                listener.hideProgressDialog()
            }
        })
    }

    fun gotoAddActivity() {
        listener.goToAddActivity()
    }

    fun gotoEditActivity(result: GetPersonsRespons.Result) {
        listener.goToEditActivity(result)
    }


    fun deletePerson(result: GetPersonsRespons.Result) {
        listener.showProgressDialog()
        ApiClient.apiService.deletePerson(result.iD.toString())
            .enqueue(object : Callback<DeletePersonResponse> {
                override fun onResponse(
                    call: Call<DeletePersonResponse>,
                    response: Response<DeletePersonResponse>
                ) {
                    listener.onPersonDeleteSuccess(response.message())
                    listener.hideProgressDialog()
                }

                override fun onFailure(call: Call<DeletePersonResponse>, t: Throwable) {
                    t.message?.let {
                        listener.onPersonDeleteFailed(it)
                    }
                    listener.hideProgressDialog()
                }

            })
    }


    interface Listener {
        fun onPersonListSuccess(personList: List<GetPersonsRespons.Result>)
        fun onGetPersonListFailure(errMessage: String)
        fun showProgressDialog()
        fun hideProgressDialog()
        fun goToAddActivity()
        fun goToEditActivity(result: GetPersonsRespons.Result)
        fun onPersonDeleteSuccess(message: String)
        fun onPersonDeleteFailed(errMessage: String)
    }
}