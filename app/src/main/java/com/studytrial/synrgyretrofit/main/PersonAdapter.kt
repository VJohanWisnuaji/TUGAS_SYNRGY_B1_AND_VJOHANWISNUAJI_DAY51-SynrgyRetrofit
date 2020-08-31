package com.studytrial.synrgyretrofit.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studytrial.synrgyretrofit.R
import com.studytrial.synrgyretrofit.pojo.GetPersonsRespons
import kotlinx.android.synthetic.main.person_item.view.*

class PersonAdapter(
    val listPerson: List<GetPersonsRespons.Result>,
    val presenter: MainActivityPresenter
) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listPerson.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvID.text = listPerson[position].iD.toString()
        holder.itemView.tvCreatedAt.text = listPerson[position].createdAt
        holder.itemView.tvUpdatedAt.text = listPerson[position].updatedAt
        holder.itemView.tvDeletedAt.text = "Deleted At : ${listPerson[position].deletedAt} "
        holder.itemView.tvFirstName.text = listPerson[position].firstName
        holder.itemView.tvLastName.text = listPerson[position].lastName

        holder.itemView.ivDelete.setOnClickListener {
            presenter.deletePerson(listPerson[position])
        }

        holder.itemView.ivEdit.setOnClickListener {
            presenter.gotoEditActivity(listPerson[position])
        }
    }

}