package com.example.firebasesample

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class Task (
    @DocumentId
    val id:String = "",
    val title:String = "",
        )