package apwdevs.football.club

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*

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
        p0.bindItem(listData[p1], Point(100, 100), listeners)
    }

    override fun onViewRecycled(holder: ViewDataListFCHolder) {
        super.onViewRecycled(holder)
        holder.recycleBitmaps()
        System.gc()
    }


    class ViewDataListFCHolder(itemView: View, override val containerView: View?) : RecyclerView.ViewHolder(itemView),
        LayoutContainer {
        lateinit var bitmap:  Bitmap
        fun bindItem(item: DataListFC, sizeImg: Point, listeners: (item: DataListFC) -> Unit) {
            itemView.find<TextView>(R.id.football_name).text = item.name
            item.image.let {
                bitmap = LoadScalledImages.getScalledCachesFromResources(itemView.context, it, sizeImg.y, sizeImg.x, 10f)
                itemView.find<ImageView>(R.id.football_image).setImageBitmap(bitmap)
            }
            itemView.setOnClickListener {
                listeners(item)
            }
        }
        fun recycleBitmaps(){
            if(bitmap != null)
                bitmap.recycle()
            System.gc()
        }

    }
}