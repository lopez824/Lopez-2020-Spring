package gatherer.lionellopez.discipleoftheland.ui.node

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import gatherer.lionellopez.discipleoftheland.R

class NodeFragment : Fragment() {

    private lateinit var nodeViewModel: NodeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        nodeViewModel =
                ViewModelProvider(this).get(NodeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_node, container, false)
    }
}
