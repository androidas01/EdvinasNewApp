package edvinasnew.app.source

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edvinasnew.app.R
import kotlinx.android.synthetic.main.item_source.view.*

class SourceListItemeAdapter(
    val listener: OnSourceSelectedListener
) : RecyclerView.Adapter<SourceListItemeAdapter.ViewHolder>() {

    private val list = mutableListOf<Source>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_source,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setItems(list: List<Source>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(source: Source) {
            itemView.title.text = source.title
            itemView.description.text = source.description
            itemView.setOnClickListener {
                listener.onSourceSelected(source)
            }
        }
    }
}


