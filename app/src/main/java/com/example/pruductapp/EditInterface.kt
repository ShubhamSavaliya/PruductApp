package com.example.pruductapp

import android.content.Context

interface EditInterface {
    fun intentcall(
        context: Context,
        id: Int,
        name: String
    )
}