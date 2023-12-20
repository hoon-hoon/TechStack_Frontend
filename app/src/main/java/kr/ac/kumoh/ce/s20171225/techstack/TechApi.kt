package kr.ac.kumoh.ce.prof01.s23w1301songlist

import kr.ac.kumoh.ce.s20171225.techstack.Tech
import retrofit2.http.GET

interface TechApi {
    @GET("tech")
    suspend fun getTech(): List<Tech>
}