package gatherer.lionellopez.discipleoftheland.ui.node

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NodeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the Gathering Node Fragment"
    }
    val text: LiveData<String> = _text
}