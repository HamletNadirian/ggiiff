package nadirian.hamlet.android.ggiiff

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var ImagesGif = intArrayOf(
        R.drawable.strain1,
        R.drawable.strain2,
        R.drawable.strain3,
        R.drawable.strain4,
        R.drawable.strain5,
        R.drawable.gif1
    )
   var name = arrayOf(
        "Exercise 1",
        "Exercise 2",
        "Exercise 3",
        "Exercise 4",
        "Exercise 5",
        "Exercise 6"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var gridView:GridView = findViewById(R.id.grid)
        gridView.adapter = CustomAdapter(this,ImagesGif,name)

        gridView.onItemClickListener =
            OnItemClickListener { parent, v, position, id ->
                intent = Intent(applicationContext,SecondActivity::class.java)
                intent.putExtra("position",position)
                intent.putExtra("length",ImagesGif.size)

                intent.putExtra("gifImages",ImagesGif)
               intent.putExtra("name",name)

                startActivity(intent)
            }

    }
}