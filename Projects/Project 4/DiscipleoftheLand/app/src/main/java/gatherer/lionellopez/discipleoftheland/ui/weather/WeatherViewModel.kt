package gatherer.lionellopez.discipleoftheland.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Weather is critical for Fishing. Forecast coming soon!"
    }
    val text: LiveData<String> = _text
}