package com.example.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_recylcer.*


class RecylcerFragment : Fragment(), ContactCallback {

    override fun passContact(contact: Contact) {
        listener?.onContentListInteraction(contact.firstName, contact.lastName, contact.number)
    }

    private var listener: OnFragmentInteractionListener? = null
    val recylcerFragment by lazy { RecylcerFragment() }
    val databaseHelper by lazy { ContactDatabaseHelper(context as Context) }
    val adapter by lazy {ContactAdapter(databaseHelper.getAllPeopleFromDatabase(),this)}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recylcer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        fun onContentListInteraction(firstName : String, lastName : String, number : String)
    }

    private fun initRecyclerView(){

        rvDisplayList.layoutManager = LinearLayoutManager(context)
        rvDisplayList.adapter = adapter

    }


}
