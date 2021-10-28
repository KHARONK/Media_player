package nmhu.edu.sd_bssd4250hw101_mediaplayer

class AudioViewModel1{

    // could store the audio file name in here too
    //if it were chosen by the user, but it is
    //sent via a bundle, which persists on rotate

    private var currTime1:Int = 0

    fun setCurrentTime1(time:Int)
    {
        currTime1 = time;
    }

    fun getCurrentTime1():Int{
        return currTime1
    }
}