package gatherer.lionellopez.discipleoftheland.ui.fishing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import gatherer.lionellopez.discipleoftheland.R

/*
Empty class for possible future additions.
 */
class FishingFragment : Fragment() {

    private lateinit var fishingViewModel: FishingViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fishingViewModel =
                ViewModelProvider(this).get(FishingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_fishing, container, false)
        val textView: TextView = root.findViewById(R.id.text_fishing)
        fishingViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
