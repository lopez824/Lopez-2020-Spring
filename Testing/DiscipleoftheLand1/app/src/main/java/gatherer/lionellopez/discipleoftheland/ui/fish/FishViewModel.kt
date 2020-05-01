package gatherer.lionellopez.discipleoftheland.ui.fish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FishViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Fishing Fragment"
    }
    val text: LiveData<String> = _text
}