package com.example.fragments

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ContactDatabaseHelper(context: Context)
    :SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {
        sqLiteDatabase?.execSQL(CREATE_CONTACT_TABLE)

    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase?, previousVersion: Int, newVersion: Int) {
        onCreate(sqLiteDatabase)

    }

    fun insertPersonIntoDatabase(contact:Contact){
        val database = writableDatabase

        val contentValues = ContentValues()

        contentValues.put(COL_FIRST_NAME,contact.firstName)
        contentValues.put(COL_LAST_NAME, contact.lastName)
        contentValues.put(COL_NUM,contact.number)

        database.insert(TABLE_NAME, null, contentValues)
        database.close()


    }

    fun getOnePersonFromDatabase(num:String):Contact?{
        val database = readableDatabase
        var contact:Contact? = null
        val cursor = database
            .rawQuery("SELECT * FROM $TABLE_NAME WHERE $COL_NUM = $num",
                null)
        if (cursor.moveToFirst()){

            val firstName = cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME))
            val lastName = cursor.getString(cursor.getColumnIndex(COL_LAST_NAME))
            val number = cursor.getString(cursor.getColumnIndex(COL_NUM))
            contact = Contact(firstName,lastName,number)


        }
        cursor.close()
        database.close()
        return contact


    }

    fun getAllPeopleFromDatabase():ArrayList<Contact>
    {
        val database = readableDatabase
        var contactList:ArrayList<Contact> = ArrayList<Contact>()
        val cursor = database
            .rawQuery("SELECT * FROM $TABLE_NAME",
                null)
        if (cursor.moveToFirst()){
            do {
                val firstName = cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME))
                val lastName = cursor.getString(cursor.getColumnIndex(COL_LAST_NAME))
                val number = cursor.getString(cursor.getColumnIndex(COL_NUM))
                val contact = Contact(firstName, lastName, number)
                contactList.add(contact)
            } while (cursor.moveToNext())

        }
        cursor.close()
        database.close()
        return contactList


    }
    fun  updatePersonInDatabase(contact:Contact){
        val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_FIRST_NAME,contact.firstName)
        contentValues.put(COL_LAST_NAME, contact.lastName)
        contentValues.put(COL_NUM,contact.number)

        database.update(TABLE_NAME,contentValues, "$COL_NUM = '?'", arrayOf(contact.number))
        database.close()

    }

    fun removePersonFromDatabase(num:String){
        val database = writableDatabase
        database.delete(TABLE_NAME,"$COL_NUM = '?'", arrayOf(num))
        database.close()




    }

}