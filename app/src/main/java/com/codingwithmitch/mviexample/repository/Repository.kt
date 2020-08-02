package com.codingwithmitch.mviexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.codingwithmitch.mviexample.api.ApiService
import com.codingwithmitch.mviexample.api.RetrofitBuilder
import com.codingwithmitch.mviexample.ui.main.state.MainViewState
import com.codingwithmitch.mviexample.util.ApiEmptyResponse
import com.codingwithmitch.mviexample.util.ApiErrorResponse
import com.codingwithmitch.mviexample.util.ApiSuccessResponse
import com.codingwithmitch.mviexample.util.DataState

object Repository {

    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return Transformations.switchMap(RetrofitBuilder.apiService.getBlogPosts()) { apiResponse ->
            object : LiveData<DataState<MainViewState>>() {
                override fun onActive() {
                    super.onActive()

                    when (apiResponse) {
                        is ApiSuccessResponse -> {
                            value = DataState.data(null ,
                                MainViewState(blogPosts = apiResponse.body))
                        }

                        is ApiErrorResponse -> {
                            value = DataState.error(apiResponse.errorMessage)
                        }

                        is ApiEmptyResponse -> {
                            value = DataState.error("HTTP 204 is Empty")
                        }
                    }
                }
            }
        }
    }

        fun getUser(userId:String): LiveData<DataState<MainViewState>> {
            return Transformations.switchMap(RetrofitBuilder.apiService.getUser(userId)) { apiResponse ->
                object : LiveData<DataState<MainViewState>>() {
                    override fun onActive() {
                        super.onActive()
                        when (apiResponse) {
                            is ApiSuccessResponse -> {
                                value = DataState.data(null ,
                                    MainViewState(user = apiResponse.body))
                            }

                            is ApiErrorResponse -> {
                                value = DataState.error(apiResponse.errorMessage)
                            }

                            is ApiEmptyResponse -> {
                                value = DataState.error("HTTP 204 is Empty")
                            }
                        }
                    }
                }
            }
    }
}