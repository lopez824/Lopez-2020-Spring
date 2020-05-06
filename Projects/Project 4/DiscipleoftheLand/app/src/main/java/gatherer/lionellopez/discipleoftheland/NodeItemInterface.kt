package gatherer.lionellopez.discipleoftheland

import retrofit2.Call
import retrofit2.http.GET

/*
Gets endpoint that holds the item data for this app.
 */
interface NodeItemInterface {
    @GET("NodeItems.json")
    fun currentItems(): Call<List<NodeItem>>
}