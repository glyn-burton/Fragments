package com.example.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_input.*
import kotlinx.android.synthetic.main.fragment_input.view.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [InputFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
class InputFragment : Fragment(), View.OnClickListener{
    private var listener: OnFragmentInteractionListener? = null
    val databaseHelper by lazy { ContactDatabaseHelper(context as Context) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.btnAddToDatabase.setOnClickListener(this)
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
        fun onContactEntryInteraction(firstName : String, lastName : String, number : String)
    }

    override fun onClick(view: View)
    {
        listener?.onContactEntryInteraction(
            etEnterName.text.toString(),
            etEnterLastName.text.toString(),
            etEnterNumber.text.toString())

        var toast = Toast.makeText(context, etEnterName.text.toString(), Toast.LENGTH_LONG)
        toast. show()
    }



}
