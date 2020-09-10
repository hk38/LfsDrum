package lit.amida.lfsdrum

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Attributes

class MainActivity : AppCompatActivity() {
    private lateinit var soundPool: SoundPool
    private lateinit var soundId: Array<Int?>
    private val soundRes = arrayOf(
        R.raw.cymbal1,
        R.raw.cymbal2,
        R.raw.cymbal3,
        R.raw.tom1,
        R.raw.tom2,
        R.raw.tom3,
        R.raw.hihat,
        R.raw.snare,
        R.raw.bass
    )

    override fun onResume() {
        super.onResume()

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        soundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(soundRes.size)
            .build()

        soundId = arrayOfNulls(soundRes.size)

        for(i in soundRes.indices) soundId[i] = soundPool.load(applicationContext, soundRes[i], 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageList = listOf(imageCymbal1, imageCymbal2, imageCymbal3, imageTom1, imageTom2, imageTom3, imageHihat, imageSnare, imageBass)

        imageList.forEachIndexed {i, it ->
            it.setOnClickListener{
                if(soundId[i] != null) soundPool.play(soundId[i]!!, 1.0f, 1.0f, 0, 0, 1.0f)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        soundPool.release()
    }
}