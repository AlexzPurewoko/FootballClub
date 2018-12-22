package apwdevs.football.club

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.SupportActivity
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import apwdevs.football.club.R.color.colorPrimary
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list_names = resources.getStringArray(R.array.nama_klub_sepakbola)
        val list_images = resources.obtainTypedArray(R.array.nama_logo_sepakbola)
        var list_data: MutableList<DataListFC> = mutableListOf()
        list_data.clear()
        for (i in list_names.indices) {
            list_data.add(DataListFC(list_names[i], list_images.getResourceId(i, 0)))
        }
        list_images.recycle()
        main_recycler.adapter = RecyclerDataAdapter(this, list_data) {
            toast("Selected Club : ${it.name}")
            startActivity(intentFor<DetailFootball>("EXTRA_DATA" to it).singleTop())
        }
        main_recycler.layoutManager = LinearLayoutManager(this)
    }
}
