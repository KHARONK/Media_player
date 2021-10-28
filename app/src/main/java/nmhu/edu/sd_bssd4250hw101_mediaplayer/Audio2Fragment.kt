package nmhu.edu.sd_bssd4250hw101_mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider

private const val AUDIO_RES2 ="audio_file" //key for bundle
class Audio2Fragment : Fragment() {

    private lateinit var viewModel2:AudioViewModel

    private var mediaPlayer2: MediaPlayer? = null///will hold mediaPlayer
    private var audioRes2:Int? = null //resource ti play

    override  fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mediaPlayer2 = ViewModelProvider(this).get(AudioViewModel::class.java)
        arguments?.let {
            audioRes2 = it.getInt(AUDIO_RES2) //load argument from companion
        }
    }

    override fun onCreateView(
        inflator: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?, ): View? {
        if (this.mediaPlayer2 == null) {
            this.mediaPlayer2 = MediaPlayer.create(context, audioRes2!!)//make mediaplayer
        }

        val v: View = inflator.inflate(R.layout.fragment_audio, container, false)
        //look up each button and give it click listener
        v.findViewById<Button>(R.id.play_button2).apply {
            setOnClickListener {
                playMedia()//reusable function
            }
        }

        v.findViewById<Button>(R.id.stop_button2).apply {
            setOnClickListener {
                stopMedia()//reusable function
            }

        }
        return v
    }

    private fun playMedia(time:Int = 0){
        mediaPlayer2?.seekTo(time)
        mediaPlayer2?.start()
    }

    private fun stopMedia(time:Int = 0){
        mediaPlayer2?.stop()
        mediaPlayer2?.prepare()
    }

    //these two functions are pretty much same
    //concepts as the instance state stuff from before
    override fun onDestroyView(){
        super.onDestroyView()
        viewModel2.setCurrentTime(this.mediaPlayer2?.currentPosition!!)
        stopMedia()
        mediaPlayer2?.release()
        mediaPlayer2 = null
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (viewModel2.getCurrentTime() > 0){
            playMedia(viewModel2.getCurrentTime())
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(audio:Int) =
            AudioFragment().apply {
                arguments = Bundle().apply {
                    putInt(AUDIO_RES2, audio)
                }
            }
    }
}