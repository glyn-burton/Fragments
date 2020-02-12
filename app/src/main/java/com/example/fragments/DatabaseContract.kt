package com.example.fragments

const val DATABASE_NAME = "data_con_database"
const val TABLE_NAME = "contact_table"
const val DATABASE_VERSION = 1
const val COL_FIRST_NAME = "first_name"
const val COL_LAST_NAME = "last_name"
const val COL_NUM = "number"


const val CREATE_CONTACT_TABLE =
    "CREATE TABLE $TABLE_NAME (" +
            "$COL_FIRST_NAME String,"+
            "$COL_LAST_NAME String,"+
            "$COL_NUM String PRIMARY_KEY)"