package gatherer.lionellopez.discipleoftheland

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NodeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NodeRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allNodes: LiveData<List<NodeItem>>

    init {
        val nodesDao = DoLRoomDatabase.getDatabase(application,viewModelScope).nodeDao()
        repository = NodeRepository(nodesDao)
        allNodes = repository.allNodes
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(item: NodeItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }
}