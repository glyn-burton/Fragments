package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_input.*

class MainActivity : AppCompatActivity(),
    RecylcerFragment.OnFragmentInteractionListener,
    InputFragment.OnFragmentInteractionListener

{

    val databaseHelper by lazy { ContactDatabaseHelper(this) }
    val inputListFrag by lazy { InputFragment() }
    val recycleListFrag by lazy { RecylcerFragment() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fgInputView,inputListFrag)
            .addToBackStack("INPUT_FRAG")
            .commit()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fgContactList, recycleListFrag)
            .addToBackStack("CONTACT_FRAG")
            .commit()
    }

    override fun onContentListInteraction(
        firstName: String,
        lastName: String,
        number: String)
    {
        inputListFrag.etEnterName.setText(firstName)
        inputListFrag.etEnterLastName.setText(lastName)
        inputListFrag.etEnterNumber.setText(number)
    }

    override fun onContactEntryInteraction(
        firstName: String,
        lastName: String,
        number: String)
    {
        if(databaseHelper.getOnePersonFromDatabase(number).toString() == number)
        {
            databaseHelper.updatePersonInDatabase(Contact(firstName, lastName, number))
            recycleListFrag.adapter.updateList(databaseHelper.getAllPeopleFromDatabase())
        }
        else
        {
            databaseHelper.insertPersonIntoDatabase(Contact(firstName, lastName, number))
            recycleListFrag.adapter.updateList(databaseHelper.getAllPeopleFromDatabase())

        }
    }

}
