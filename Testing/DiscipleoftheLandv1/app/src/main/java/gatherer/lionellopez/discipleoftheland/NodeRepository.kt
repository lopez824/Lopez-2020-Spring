package gatherer.lionellopez.discipleoftheland

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NodeRepository(private val nodeDao:NodeDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allNodes: LiveData<List<NodeItem>> = nodeDao.getAllItems()

    suspend fun insert(item: NodeItem) {
        nodeDao.insert(item)
    }
}