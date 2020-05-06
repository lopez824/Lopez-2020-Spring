package gatherer.lionellopez.discipleoftheland.ui.fishing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FishingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Rare fish need specific conditions to spawn. More info coming soon!"
    }
    val text: LiveData<String> = _text
}