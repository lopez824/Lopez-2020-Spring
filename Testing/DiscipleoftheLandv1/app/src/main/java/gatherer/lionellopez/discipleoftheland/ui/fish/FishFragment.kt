package gatherer.lionellopez.discipleoftheland.ui.fish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import gatherer.lionellopez.discipleoftheland.R

class FishFragment : Fragment() {

    private lateinit var fishViewModel: FishViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fishViewModel =
            ViewModelProviders.of(this).get(FishViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_fish, container, false)
        val textView: TextView = root.findViewById(R.id.text_fish)
        fishViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}