package apwdevs.football.club

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class AdapterRecyclerView : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            padding = dip(16)

            imageView {
                id = R.id.football_image
            }.lparams(width = dip(50), height = dip(50))

            textView {
                id = R.id.football_name
            }.lparams(height = LinearLayout.LayoutParams.WRAP_CONTENT, width = LinearLayout.LayoutParams.WRAP_CONTENT) {
                margin = dip(10)
                gravity = Gravity.CENTER_VERTICAL
            }
        }
    }
}