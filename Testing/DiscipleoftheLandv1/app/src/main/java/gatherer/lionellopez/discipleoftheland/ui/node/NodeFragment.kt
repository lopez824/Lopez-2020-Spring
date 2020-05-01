package gatherer.lionellopez.discipleoftheland.ui.node

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import gatherer.lionellopez.discipleoftheland.ItemListAdapter
import gatherer.lionellopez.discipleoftheland.MainActivity
import gatherer.lionellopez.discipleoftheland.R
import kotlinx.android.synthetic.main.fragment_node.*


class NodeFragment : Fragment() {

    private lateinit var nodeViewModel: NodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nodeViewModel =
            ViewModelProvider(this).get(NodeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_node, container, false)
        val recyclerView = recyclerview
        val adapter = ItemListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        return root
    }
}