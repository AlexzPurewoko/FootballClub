package apwdevs.football.club

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class RecyclerDataAdapter(
    private val ctx: Context,
    private val listData: List<DataListFC>,
    private val listeners: (item: DataListFC) -> Unit
) : RecyclerView.Adapter<RecyclerDataAdapter.ViewDataListFCHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewDataListFCHolder {
        val layout = AdapterRecyclerView().createView(AnkoContext.create(ctx))
        return ViewDataListFCHolder(layout, p0)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(p0: ViewDataListFCHolder, p1: Int) {
        p0.bindItem(listData[p1], listeners)
    }

    class ViewDataListFCHolder(itemView: View, override val containerView: View?) : RecyclerView.ViewHolder(itemView),
        LayoutContainer {
        fun bindItem(item: DataListFC, listeners: (item: DataListFC) -> Unit) {
            itemView.find<TextView>(R.id.football_name).text = item.name
            item.image.let { Picasso.get().load(it).into(itemView.find<ImageView>(R.id.football_image)) }
            itemView.setOnClickListener {
                listeners(item)
            }
        }

    }
}