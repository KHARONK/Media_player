package nmhu.edu.sd_bssd4250hw101_mediaplayer

class AudioViewModel2 {


    // could store the audio file name in here too
    //if it were chosen by the user, but it is
    //sent via a bundle, which persists on rotate

    private var currTime2:Int = 0

    fun setCurrentTime2(time:Int)
    {
        currTime2 = time;
    }

    fun getCurrentTime2():Int{
        return currTime2
    }
}