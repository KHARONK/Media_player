package nmhu.edu.sd_bssd4250hw101_mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider

private const val AUDIO_RES1 ="audio_file" //key for bundle
class Audio1Fragment : Fragment() {

    private lateinit var viewModel1:AudioViewModel

    private var mediaPlayer1: MediaPlayer? = null///will hold mediaPlayer
    private var audioRes1:Int? = null //resource ti play

    override  fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        viewModel1 = ViewModelProvider(this).get(AudioViewModel::class.java)
        arguments?.let {
            audioRes1 = it.getInt(AUDIO_RES1) //load argument from companion
        }
    }

    override fun onCreateView(
        inflator: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?, ): View? {
        if (this.mediaPlayer1 == null) {
            this.mediaPlayer1 = MediaPlayer.create(context, audioRes1!!)//make mediaplayer
        }

        val v: View = inflator.inflate(R.layout.fragment_audio, container, false)
        //look up each button and give it click listener
        v.findViewById<Button>(R.id.play_button1).apply {
            setOnClickListener {
                playMedia()//reusable function
            }
        }

        v.findViewById<Button>(R.id.stop_button1).apply {
            setOnClickListener {
                stopMedia()//reusable function
            }

        }
        return v
    }

    private fun playMedia(time:Int = 0){
        mediaPlayer1?.seekTo(time)
        mediaPlayer1?.start()
    }

    private fun stopMedia(time:Int = 0){
        mediaPlayer1?.stop()
        mediaPlayer1?.prepare()
    }

    //these two functions are pretty much same
    //concepts as the instance state stuff from before
    override fun onDestroyView(){
        super.onDestroyView()
        viewModel1.setCurrentTime(this.mediaPlayer1?.currentPosition!!)
        stopMedia()
        mediaPlayer1?.release()
        mediaPlayer1 = null
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (viewModel1.getCurrentTime() > 0){
            playMedia(viewModel1.getCurrentTime())
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(audio:Int) =
            AudioFragment().apply {
                arguments = Bundle().apply {
                    putInt(AUDIO_RES1, audio)
                }
            }
    }
}