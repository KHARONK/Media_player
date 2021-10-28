package nmhu.edu.sd_bssd4250hw101_mediaplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.Voice
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {

    private val STEP_TAG:String = "nmhu.edu.sd_bssd4250hw101_mediaplayer"
    private val VOICE_TAG:String = "nmhu.edu.sd_bssd4250hw101_mediaplayer"
    private val RAIN_TAG:String = "nmhu.edu.sd_bssd4250hw101_mediaplayer"

    private val LLID:Int = 123 //constant id for linear layout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var mediaPlayer: MediaPlayer? = null

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(applicationContext, R.raw.step)
        }

        var mediaPlayer1: MediaPlayer? = null

        if (mediaPlayer1 == null) {
            mediaPlayer1 = MediaPlayer.create(applicationContext, R.raw.Voice)
        }

        var mediaPlayer2: MediaPlayer? = null

        if (mediaPlayer2 == null) {
            mediaPlayer2 = MediaPlayer.create(applicationContext, R.raw.Rain)
        }


        val playButtton = Button(this).apply {
            text = "Play"
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
            )
            setOnClickListener {
                playAudio()
            }
        }

        val playButtton1 = Button(this).apply {
            text = "Play"
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
            )
            setOnClickListener {
                playAudio()
            }
        }

        val playButtton2 = Button(this).apply {
            text = "Play"
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
            )
            setOnClickListener {
                playAudio()
            }
        }


        //LinearLayout for First audio step

        val ll = LinearLayoutCompat(this).apply {
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT
            )

            id = LLID
        }
        setContentView(ll)

        if (savedInstanceState == null) {
            //create fragment for collection edits buttons
            supportFragmentManager.commit {
                replace(ll.id, AudioFragment.newInstance(R.raw.step), STEP_TAG)
            }
        }
        else
        {
            val stepFragment = supportFragmentManager.findFragmentByTag(STEP_TAG) as AudioFragment
            supportFragmentManager.commit{
                replace(ll.id, stepFragment, STEP_TAG)
            }
        }

        //LinearLayout for Second audio Voice

        val voice = LinearLayoutCompat(this).apply {
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT
            )

            id = LLID
        }
        setContentView(voice)


        if (savedInstanceState == null) {
            //create fragment for collection edits buttons
            supportFragmentManager.commit {
                replace(voice.id, Audio1Fragment.newInstance(R.raw.Voice), VOICE_TAG)
            }
        }
        else
        {
            val voiceFragment = supportFragmentManager.findFragmentByTag(VOICE_TAG) as Audio1Fragment
            supportFragmentManager.commit{
                replace(voice.id, voiceFragment, VOICE_TAG)
            }
        }


        //LinearLayout for third audio Rain

        val rain = LinearLayoutCompat(this).apply {
            orientation = LinearLayoutCompat.VERTICAL
            layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT
            )

            id = LLID
        }
        setContentView(rain)


        if (savedInstanceState == null) {
            //create fragment for collection edits buttons
            supportFragmentManager.commit {
                replace(rain.id, Audio1Fragment.newInstance(R.raw.Rain), RAIN_TAG)
            }
        }
        else
        {
            val rainFragment = supportFragmentManager.findFragmentByTag(VOICE_TAG) as Audio1Fragment
            supportFragmentManager.commit{
                replace(rain.id, rainFragment, RAIN_TAG)
            }
        }

    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val currentTime = savedInstanceState.getInt(STEP_TAG)
        val currentTime1 = savedInstanceState.getInt(VOICE_TAG)
        val currentTime2 = savedInstanceState.getInt(RAIN_TAG)

        playAudio(currentTime)
        playAudio1(currentTime1)
        playAudio2(currentTime2)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STEP_TAG, mediaPlayer?.currentPosition!!)
        outState.putInt(VOICE_TAG, mediaPlayer1?.currentPosition!!)

        mediaPlayer?.pause()
        mediaPlayer1?.pause()
    }

    // play for wav 1
    private fun playAudio(time:Int = 0){
        if (mediaPlayer?.isPlaying!!){
            mediaPlayer?.pause()
            mediaPlayer?.seekTo(time) //rewinds and resets buffers
        }
        else
        {
            mediaPlayer?.start()
        }

    }


    // play for wav 2
    private fun playAudio1(time:Int = 0){
        if (mediaPlayer1?.isPlaying!!){
            mediaPlayer1?.pause()
            mediaPlayer1?.seekTo(time) //rewinds and resets buffers
        }
        else
        {
            mediaPlayer1?.start()
        }
    }


    // play for wav 3
    private fun playAudio2(time:Int = 0){
        if (mediaPlayer2?.isPlaying!!){
            mediaPlayer2?.pause()
            mediaPlayer2?.seekTo(time) //rewinds and resets buffers
        }
        else
        {
            mediaPlayer1?.start()
        }

    }

    companion object{
        private var mediaPlayer:MediaPlayer? = null
        private var mediaPlayer1:MediaPlayer?=null
        private var mediaPlayer2:MediaPlayer?=null

    }


}
