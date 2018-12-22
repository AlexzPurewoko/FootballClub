package apwdevs.football.club

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.design.resources.TextAppearance
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import com.squareup.picasso.Picasso

import org.jetbrains.anko.*
import java.io.InputStream

class DetailFootball : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val detailUI = DetailFootballUI().setContentView(this)
        gets(detailUI)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun gets(detailUI: View) {
        val data: DataListFC = intent.getParcelableExtra("EXTRA_DATA")
        val stream = assets.open(data.name)
        var bytes = ByteArray(stream.available())
        stream.read(bytes)
        stream.close()
        data.image.let { Picasso.get().load(it).into(detailUI.find<ImageView>(R.id.football_image)) }
        detailUI.find<TextView>(R.id.football_name).text = data.name
        detailUI.find<TextView>(R.id.football_description).text = String(bytes)
    }

    class DetailFootballUI : AnkoComponent<DetailFootball> {
        override fun createView(ui: AnkoContext<DetailFootball>): View = with(ui) {
            scrollView {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                verticalLayout {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    padding = dip(16)

                    imageView {
                        id = R.id.football_image
                    }.lparams(width = dip(100), height = dip(100)) {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                    textView {
                        id = R.id.football_name
                        textAppearance = android.R.style.TextAppearance_DeviceDefault_Medium
                        textColor = Color.BLACK
                        gravity = Gravity.CENTER
                    }.lparams(
                        width = LinearLayout.LayoutParams.MATCH_PARENT,
                        height = LinearLayout.LayoutParams.WRAP_CONTENT
                    ) {
                        gravity = Gravity.CENTER_HORIZONTAL
                        topMargin = dip(10)
                    }
                    textView {
                        id = R.id.football_description
                        textAppearance = android.R.style.TextAppearance_DeviceDefault_Small
                        textColor = Color.BLACK
                    }.lparams(
                        width = LinearLayout.LayoutParams.MATCH_PARENT,
                        height = LinearLayout.LayoutParams.WRAP_CONTENT
                    ) {
                        topMargin = dip(10)
                    }
                }
            }

        }

    }
}
