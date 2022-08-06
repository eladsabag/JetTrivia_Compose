package com.example.jettrivia_compose.repository

import android.util.Log
import com.example.jettrivia_compose.data.DataOrException
import com.example.jettrivia_compose.model.QuestionItem
import com.example.jettrivia_compose.network.QuestionAPI
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: QuestionAPI) {
    private val dataOrException =
        DataOrException<ArrayList<QuestionItem>,
            Boolean,
            Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions()
            if(dataOrException.data.toString().isNotEmpty())
                dataOrException.loading = false
        } catch (e: Exception) {
            dataOrException.e = e
            Log.d("Exception", "getAllQuestions: ${dataOrException.e!!.localizedMessage}")
        }
        return dataOrException
    }
}