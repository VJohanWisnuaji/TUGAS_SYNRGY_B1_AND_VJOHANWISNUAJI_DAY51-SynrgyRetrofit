package com.studytrial.synrgyretrofit.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.studytrial.synrgyretrofit.R
import com.studytrial.synrgyretrofit.main.MainActivity
import com.studytrial.synrgyretrofit.pojo.GetPersonsRespons
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity(), EditActivityPresenter.Listerner {
    lateinit var presenter: EditActivityPresenter
    private lateinit var result: GetPersonsRespons.Result

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        presenter = EditActivityPresenter(this)

        intent.getParcelableExtra<GetPersonsRespons.Result>("PERSON")?.let {
            result = it
        }


        etEditFirstName.setText(result.firstName)
        etEditLastName.setText(result.lastName)


        btnEditPerson.setOnClickListener {
            result.apply {
                firstName = etEditFirstName.text.toString()
                lastName = etEditLastName.text.toString()
            }

            presenter.editPerson(result)
        }

        iv_back_edit.setOnClickListener {
            presenter.gotoMainActivity()
        }

    }

    override fun onEditPersonSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onEditPersonFailed(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}