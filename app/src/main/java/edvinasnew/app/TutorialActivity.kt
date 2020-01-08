package edvinasnew.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)
        findViewById<Button>(R.id.next).setOnClickListener {
                finish()
        }

    }
}
