package edvinasnew.app.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edvinasnew.app.R
import kotlinx.android.synthetic.main.activity_news.view.*


//import kotlinx.android.synthetic.main.item_source.view.*

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val list = mutableListOf<NewsItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_news,
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

    fun setItems(list: List<NewsItem>) {
        this.list.clear()
        this.list.addAll(list)
        println(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(source: NewsItem) {

            itemView.title.text = source.title
            itemView.description.text = source.description
            itemView.datetime.text = source.date
            //DownLoadImageTask(itemView.imageUrl).execute(source.urlToImage)


            Glide.with(itemView)
                .load(source.urlToImage)
                .into(itemView.imageUrl)


            //itemView.image.setImageResource(source.image)
//            itemView.setOnClickListener {
//                onSelected(source)
//            }
        }
    }
}