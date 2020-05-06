package gatherer.lionellopez.discipleoftheland

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
Sends a request to pull json file from S3 bucket.
 */
object WebServices {
    private var service: NodeItemInterface

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://wolbucket.s3-us-west-2.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(NodeItemInterface::class.java)
    }

    fun nodeItems(callback: Callback<List<NodeItem>>) {
        service.currentItems().enqueue(callback)
    }
}