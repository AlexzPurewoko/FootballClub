package apwdevs.football.club

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class DialogShowHelper(internal val activity: AppCompatActivity) {
    private var dialog: AlertDialog? = null

    fun stopDialog() {
        dialog!!.cancel()
    }

    fun showDialog() {
        dialog!!.show()
    }

    fun buildLoadingLayout() {
        if (dialog == null) {
            val rootElement = buildAndConfigureRootelement()
            val builder = AlertDialog.Builder(activity)
            builder.setView(rootElement)
            builder.setCancelable(false)
            dialog = builder.create()
        }
    }

    private fun buildAndConfigureRootelement(): LinearLayout {
        return DialogUI().createView(AnkoContext.Companion.create(activity)) as LinearLayout
    }
    class DialogUI : AnkoComponent<Context>{
        override fun createView(ui: AnkoContext<Context>): View = with(ui){
            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                padding = dip(10)
                layoutParams = ViewGroup.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                progressBar{

                }.lparams(width = dip(40), height = dip(40))

                textView {
                    text = "Loading..."
                    textColor = Color.BLACK
                    layoutParams = ViewGroup.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }.lparams(width = LinearLayout.LayoutParams.WRAP_CONTENT,
                    height = LinearLayout.LayoutParams.WRAP_CONTENT){
                    setMargins(dip(10),1,1,1)
                    gravity = Gravity.CENTER
                }
            }
        }

    }
    /**
     * <LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal"
    android:padding="10dp"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <ProgressBar
    android:layout_width="@dimen/actdialog_dimen_loading_wh"
    android:layout_height="@dimen/actdialog_dimen_loading_wh"
    style="@style/Base.Widget.AppCompat.ProgressBar"/>
    <TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginLeft="10dp"
    android:text="Loading"
    android:textColor="@android:color/black"
    android:layout_gravity="center_vertical" />
    </LinearLayout>
     */
}