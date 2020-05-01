package gatherer.lionellopez.discipleoftheland.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import gatherer.lionellopez.discipleoftheland.R

class WeatherFragment : Fragment() {

    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        weatherViewModel =
            ViewModelProviders.of(this).get(WeatherViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_weather, container, false)
        val textView: TextView = root.findViewById(R.id.text_weather)
        weatherViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}