package com.studytrial.synrgyretrofit.main

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.studytrial.synrgyretrofit.add.AddActivity
import com.studytrial.synrgyretrofit.R
import com.studytrial.synrgyretrofit.edit.EditActivity
import com.studytrial.synrgyretrofit.pojo.GetPersonsRespons
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityPresenter.Listener {
    lateinit var progressDialog: ProgressDialog
    lateinit var presenter: MainActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(this)
        presenter = MainActivityPresenter(this)

        fabAddActivity.setOnClickListener {
            presenter.gotoAddActivity()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getPerson()
    }

    fun setupRecyclerView(listPerson: List<GetPersonsRespons.Result>) {
        recyclerView.adapter = PersonAdapter(listPerson, presenter)
    }

    override fun onPersonListSuccess(personList: List<GetPersonsRespons.Result>) {
        setupRecyclerView(personList)
    }

    override fun onGetPersonListFailure(errMessage: String) {
        Toast.makeText(this, "$errMessage", Toast.LENGTH_SHORT).show()
    }

    override fun showProgressDialog() {
        progressDialog.show()
    }

    override fun hideProgressDialog() {
        progressDialog.hide()
    }

    override fun goToAddActivity() {
        startActivity(Intent(this, AddActivity::class.java))
    }

    override fun goToEditActivity(result: GetPersonsRespons.Result) {
        val gotoEditActivity = Intent(this, EditActivity::class.java)
        gotoEditActivity.putExtra("PERSON", result)
        startActivity(gotoEditActivity)
    }

    override fun onPersonDeleteSuccess(message: String) {
        Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
        presenter.getPerson()
    }

    override fun onPersonDeleteFailed(errMessage: String) {
        Toast.makeText(this, "$errMessage", Toast.LENGTH_SHORT).show()
    }
}



