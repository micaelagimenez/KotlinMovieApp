package com.app.movieapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.movieapp.api.MovieRemoteDataSource

class RatingViewModel constructor(
    private val remoteDataSource: MovieRemoteDataSource
): ViewModel() {

    private var _success = MutableLiveData<Boolean>()
    private var _fail = MutableLiveData<String?>()

    val success: LiveData<Boolean>
        get() = _success

    fun postRating(rating:Int, id:Map<String,Float>, session_id:String){
        remoteDataSource.postRating(rating, id, session_id,  object: MovieRemoteDataSource.RatingCallBack{
            override fun onSuccess(){
                _success.postValue(true)
            }
            override fun onError(message:String?){
                _success.postValue(false)
                _fail.postValue(message)
            }
        })
    }
}