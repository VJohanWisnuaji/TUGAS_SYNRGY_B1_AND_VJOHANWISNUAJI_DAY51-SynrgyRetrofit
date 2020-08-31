package com.studytrial.synrgyretrofit.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.studytrial.synrgyretrofit.R
import com.studytrial.synrgyretrofit.main.MainActivity
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity(), AddActivityPresenter.Listener {
    lateinit var presenter: AddActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        presenter = AddActivityPresenter(this)


        btnAddPerson.setOnClickListener {
            presenter.addPerson(etFirstName.text.toString(), etLastName.text.toString())
        }

        iv_back.setOnClickListener {
            presenter.gotoMainActivity()
        }
    }

    override fun onAddPersonSuccess(successMessage: String) {
        Toast.makeText(this, successMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onAddPersonFailure(failureMessage: String) {
        Toast.makeText(this, failureMessage, Toast.LENGTH_SHORT).show()
    }

    override fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}