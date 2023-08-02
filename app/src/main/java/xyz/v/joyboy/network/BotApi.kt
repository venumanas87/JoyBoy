package xyz.v.luffy.network


import Base
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap
import xyz.v.joyboy.models.BotResponse
import xyz.v.joyboy.models.TypeMessage

interface BotApi {
    @Headers("Authorization:  Bearer XQVIF6RKSEMNT27JT7Q3NUCQKR5C3EMC")
    @GET("message")
    fun sendMessage(@Query("q") q:String):Call<JsonObject>

    @Headers("Authorization:  Bearer XQVIF6RKSEMNT27JT7Q3NUCQKR5C3EMC")
    @POST("event")
    fun convoMessage(@Query("v") v:String,@Query("session_id") sessionID:String,@Query("context_map") contextMap:String, @Body typeMessage: TypeMessage):Call<BotResponse>
}