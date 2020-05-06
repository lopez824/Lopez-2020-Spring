package gatherer.lionellopez.discipleoftheland

import io.objectbox.Box
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*
Object that manages all items pulled from the json file.
 */
object NodeItemManager {

    private val mItemList = mutableListOf<NodeItem>()
    private val itemBox: Box<NodeItem> = ObjectBox.boxStore.boxFor(NodeItem::class.java)
    private val queryAll = itemBox.query().build()

    /*
    Manages successful or failed requests for the json file
     */
    private val callback = object: Callback<List<NodeItem>> {
        override fun onFailure(call: Call<List<NodeItem>>, t: Throwable) {
            mItemList.addAll(queryAll.find()) // pulls from the database if the user can't connect
            EventBus.getDefault().post(NodeItemsEvent())
        }

        override fun onResponse(call: Call<List<NodeItem>>, response: Response<List<NodeItem>>) {
            if( response.isSuccessful){
                mItemList.addAll(response.body()!!)

                if (itemBox.isEmpty) {
                    itemBox.put(response.body()) // makes sure database is only populated once.
                }
                // updates the subscribed list in NodeItemFragment
                EventBus.getDefault().post(NodeItemsEvent())
            }
        }
    }

    /*
    Returns current list of items.
     */
    fun itemList():List<NodeItem> {
        return mItemList
    }

    /*
    Displays all items in the database.
     */
    fun showAll() {
        mItemList.clear();
        mItemList.addAll(queryAll.find())
        EventBus.getDefault().post(NodeItemsEvent())
    }

    /*
    Updates specified item. Used for the favorites list.
     */
    fun updateItem(item: NodeItem) {
        itemBox.put(item)
    }

    /*
    Displays items that are deemed favorites.
     */
    fun getFavorites() {
        mItemList.clear()
        val query = itemBox.query().equal(NodeItem_.isFavorite, true).build()
        val results = query.find()
        mItemList.addAll(results)
        EventBus.getDefault().post(NodeItemsEvent())
    }

    /*
    Displays specified item search.
     */
    fun searchItem(str: String) {
        mItemList.clear()
        val query = itemBox.query().contains(NodeItem_.itemName, str).build()
        val results = query.find()
        mItemList.addAll(results)
        EventBus.getDefault().post(NodeItemsEvent())
    }

    /*
    Displays items based on desired role, Miner or Botanist.
     */
    fun filterByClass(job: String) {
        mItemList.clear()
        val query = itemBox.query().equal(NodeItem_.itemClass, job).build()
        val results = query.find()
        mItemList.addAll(results)
        EventBus.getDefault().post(NodeItemsEvent())
    }

    /*
    Sorts current list by time, in ascending order.
     */
    fun sortByTime() {
        mItemList.sortBy { it.itemTime }
        EventBus.getDefault().post(NodeItemsEvent())
    }

    /*
    Sorts current list in alphabetical order.
     */
    fun alphabetize() {
        mItemList.sortBy { it.itemName }
        EventBus.getDefault().post(NodeItemsEvent())
    }

    /*
    Sorts current by region, then alphabetically by area name.
     */
    fun sortByArea() {
        mItemList.sortWith(compareBy({it.itemRegion}, {it.itemArea}))
        EventBus.getDefault().post(NodeItemsEvent())
    }

    /*
    Initiates request for the json file.
     */
    fun fetchItems() {
        WebServices.nodeItems(callback)
    }
}