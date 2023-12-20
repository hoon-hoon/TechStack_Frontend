package kr.ac.kumoh.ce.s20171225.techstack

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.ac.kumoh.ce.prof01.s23w1301songlist.TechApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TechViewModel() : ViewModel(){
    private val SERVER_URL = "https://port-0-techstack-backend-5yc2g32mlomgoxb0.sel5.cloudtype.app/"
    private val techApi: TechApi
    private val _techList = MutableLiveData<List<Tech>>()
    val techList: LiveData<List<Tech>>
        get() = _techList

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        techApi = retrofit.create(TechApi::class.java)
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val response = techApi.getTech()
                _techList.value = response
            } catch (e: Exception) {
                Log.e("fetchData()", e.toString())
            }
        }
    }
}