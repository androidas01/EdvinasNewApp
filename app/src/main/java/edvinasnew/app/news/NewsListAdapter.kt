package edvinasnew.app.news

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edvinasnew.app.R
import kotlinx.android.synthetic.main.activity_news.view.*
import java.text.SimpleDateFormat
import java.util.*


//import kotlinx.android.synthetic.main.item_source.view.*

class NewsListAdapter(val onSelected: (NewsItem) -> Unit,
                      val onFavorite: (NewsItem) -> Unit

) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

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
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(newsItem: NewsItem) {

            itemView.title.text = newsItem.title


            itemView.description.text = newsItem.description

            //var parsedDate = source.date

            //val current = LocalDateTime.now()

            //val formatter = DateTimeFormatter.ofPattern(source.date)

            //itemView.datetime.text = source.date
            val pareseDateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            val displayDateFormatter = SimpleDateFormat("yyyy MM dd", Locale.US)
            val date=  pareseDateFormatter.parse(newsItem.date)
            itemView.datetime.text = displayDateFormatter.format(date)

            //DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH).format(source.date)

            //source.date

            itemView.setOnClickListener {
                onSelected.invoke(newsItem)
            }

            //DownLoadImageTask(itemView.imageUrl).execute(source.urlToImage)


            Glide.with(itemView)
                .load(newsItem.urlToImage)
                .placeholder(R.drawable.loading)
                .into(itemView.imageUrl)


            //itemView.image.setImageResource(source.image)
//            itemView.setOnClickListener {
//                onSelected(source)
//            }

            itemView.button_make_favorite.setOnClickListener {
                onFavorite(newsItem)
            }

            itemView.button_make_favorite.setImageResource(if(newsItem.favorite) R.drawable.star else R.drawable.star_off)
        }
    }
}