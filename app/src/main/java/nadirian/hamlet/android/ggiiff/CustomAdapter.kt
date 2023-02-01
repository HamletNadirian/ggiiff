package nadirian.hamlet.android.ggiiff

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class CustomAdapter(var context:Context, var ImagesGif: IntArray, var ImagesName: Array<String>): BaseAdapter()
{
    override fun getCount(): Int {
        return ImagesGif.size
    }

    override fun getItem(position: Int): Any {
        return ImagesGif[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var convertView=convertView
        if (convertView==null)
            convertView=LayoutInflater.from(context).inflate(R.layout.grid_row_item,parent,false)
        var imageView = convertView?.findViewById<ImageView>(R.id.images_grid)
        var textView = convertView?.findViewById<TextView>(R.id.textview_grid)

        imageView?.let {
            Glide.with(context)
                .load(ImagesGif[position])
                .centerCrop()
                .into(it)
        }
        if (textView != null) {
            textView.text = ImagesName[position!!]
        }

        return convertView!!
    }
}