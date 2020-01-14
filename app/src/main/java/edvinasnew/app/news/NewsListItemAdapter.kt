package edvinasnew.app.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edvinasnew.app.R
import kotlinx.android.synthetic.main.item_news.view.*

//import kotlinx.android.synthetic.main.item_source.view.*

class NewsListItemAdapter : RecyclerView.Adapter<NewsListItemAdapter.ViewHolder>() {

    private val list = mutableListOf<SourceArticle>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news,
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

    fun setItems(list: List<SourceArticle>) {
        this.list.clear()
        this.list.addAll(list)
        println(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(source: SourceArticle) {
            itemView.title.text = source.title
            itemView.description.text = source.description
            //itemView.image.setImageResource(source.image)
            itemView.date.text = source.date
        }
    }
}