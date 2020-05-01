package gatherer.lionellopez.discipleoftheland

object NodeItemManager {

   private val mItemList = mutableListOf<NodeItem>()

    init {
        for(i in 1..20) {
            addItem(NodeItem("Darksteel Ore", 1, "Coerthas Central Highlands", i, "Miner"))
        }
    }
    fun itemList():List<NodeItem> {
        return mItemList
    }

    fun addItem(name:String, time:Int, area:String, slot:Int, job:String) {
        val newItem = NodeItem(name,time,area,slot,job)
        addItem(newItem)
    }

    fun addItem(newItem:NodeItem) {
        mItemList.add(newItem)
    }

    fun addItems(list:List<NodeItem>){
        mItemList.addAll(list)
    }

    fun removeItem(item: NodeItem) {
        mItemList.remove(item)
    }
}