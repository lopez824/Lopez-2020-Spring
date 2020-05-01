package gatherer.lionellopez.discipleoftheland

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import gatherer.lionellopez.discipleoftheland.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var nodeViewModel: NodeViewModel
    lateinit var instance:ViewModelStoreOwner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance = this
        nodeViewModel = ViewModelProvider(this).get(NodeViewModel::class.java)



    }

}
