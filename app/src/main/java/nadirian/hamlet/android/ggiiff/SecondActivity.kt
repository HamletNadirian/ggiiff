package nadirian.hamlet.android.ggiiff

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import java.util.*

class SecondActivity : AppCompatActivity() {

    var positionOfImage = 0
    var totalLength = 0

    private lateinit var imageView: ImageView

    private var START_TIME_IN_MILLIS: Long = 10000
    private var mTimerRunning: Boolean = false
    private var mTimeLeftInMillis = START_TIME_IN_MILLIS

    private lateinit var startBtn: ImageView
    private lateinit var stopBtn: ImageView
    private lateinit var restartBtn: ImageView

    lateinit var next: ImageView
    lateinit var prev: ImageView
    lateinit var textView: TextView
    lateinit var textViewSc: TextView

    private lateinit var intsImage: IntArray
    private lateinit var stringsName: Array<String>
    var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        imageView = findViewById(R.id.gifImage_full_screen)
        textView = findViewById(R.id.textview_full_screen)
        textViewSc = findViewById(R.id.sc)

        next = findViewById(R.id.next)
        prev = findViewById(R.id.prev)

        startBtn = findViewById(R.id.start)
        stopBtn = findViewById(R.id.stop)
        restartBtn = findViewById(R.id.reset)

        intsImage = intent.getIntArrayExtra("gifImages")!!
        stringsName = intent.getStringArrayExtra("name")!!

        positionOfImage = intent.extras?.getInt("position")!!
        totalLength = intent.extras?.getInt("length")!!

        startBtn.setOnClickListener {
            if (mTimerRunning == false) {
                timeForNextExer()
            }
        }

        stopBtn.setOnClickListener {
            if (mTimerRunning) {
                pauseTimer()
            }
        }
        restartBtn.setOnClickListener {

            resetTimer()
        }

        next.setOnClickListener {
            nextExerciseBtn()
        }

        previousExercise()

        Glide.with(this)
            .load(intsImage[positionOfImage])
            .into(imageView)
        textView.text = stringsName[positionOfImage]

    }

    private fun resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS
        timeForNextExer()
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
        mTimerRunning = false

    }

    private fun timeForNextExer() {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                textViewSc.setText("seconds remaining: " + (millisUntilFinished / 1000))
            }

            override fun onFinish() {
                val drawable = imageView.drawable
                if (drawable is Animatable) {
                    countDownTimer?.cancel()
                    (drawable as Animatable).stop()
                }
                textViewSc.setText("done!")
                nextExerciseWithTimer()
            }
        }.start()
        mTimerRunning = true
    }

    private fun previousExercise() {
        prev.setOnClickListener {
            mTimeLeftInMillis = START_TIME_IN_MILLIS

            timeForNextExer()
            if (totalLength == 0) {
                Toast.makeText(this, "This is first exercise", Toast.LENGTH_SHORT).show()
            } else {
                if (positionOfImage > 1)
                    positionOfImage -= 1
                else {
                    positionOfImage = 0
                    Toast.makeText(this, "This is first exercise", Toast.LENGTH_SHORT).show()
                }
            }
            Glide.with(this).load(intsImage[positionOfImage]).into(imageView)
            textView.text = stringsName[positionOfImage!!]
        }
    }

    private fun nextExerciseWithTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS
        countDownTimer?.cancel()
        timeForNextExer()
        if (totalLength - 1 == positionOfImage) {
            Toast.makeText(this, "You have reached at the end", Toast.LENGTH_SHORT).show()
        } else {
            positionOfImage += 1

        }
        Glide.with(this).load(intsImage[positionOfImage!!]).into(imageView)
        textView.text = stringsName[positionOfImage!!]

    }

    private fun nextExerciseBtn() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS
        timeForNextExer()
        if (totalLength - 1 == positionOfImage) {

            Toast.makeText(this, "You have reached at the end", Toast.LENGTH_SHORT).show()
        } else {
            positionOfImage += 1

        }
        Glide.with(this).load(intsImage[positionOfImage!!]).into(imageView)
        textView.text = stringsName[positionOfImage!!]
    }

    override fun onStart() {
        super.onStart()
        countDownTimer?.start()
    }

    override fun onStop() {
        super.onStop()
        countDownTimer?.cancel()
    }
}
