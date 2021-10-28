package nmhu.edu.sd_bssd4250hw101_mediaplayer

class AudioViewModel {

    // could store the audio file name in here too
    //if it were chosen by the user, but it is
    //sent via a bundle, which persists on rotate

    private var currTime:Int = 0

    fun setCurrentTime(time:Int)
    {
        currTime = time;
    }

    fun getCurrentTime():Int{
        return currTime
    }
}