package gatherer.lionellopez.discipleoftheland

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import gatherer.lionellopez.discipleoftheland.NodeItemFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_node_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [NodeItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class NodeItemRecyclerViewAdapter(
    private val mValues: List<NodeItem>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<NodeItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as NodeItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_node_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]

        holder.mNameView.text = item.itemName

        // assigns appropriate suffix for the item's available time.
        when(item.itemTime) {
            0 -> holder.mTimeView.text = "12 AM "
            12 -> holder.mTimeView.text = "${item.itemTime} PM"
            in 13..23 -> holder.mTimeView.text = "${item.itemTime - 12} PM"
            else -> holder.mTimeView.text = "${item.itemTime} AM"
        }

        holder.mContentView.text = item.itemArea

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {

        val mNameView: TextView = mView.name
        val mTimeView: TextView = mView.time
        val mContentView: TextView = mView.content


        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
