package com.example.androidthreading

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    lateinit var simpleWorker: SimpleWorker

    private val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(message: Message) {
            super.handleMessage(message)
            textView.text = message.obj.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)

        simpleWorker = SimpleWorker()
        simpleWorker.execute {
            Thread.sleep(1000)
            val message = Message.obtain()
            message.obj = "Task-1 completed"
            handler.sendMessage(message)
        }.execute {
            Thread.sleep(500)
            val message = Message.obtain()
            message.obj = "Task-2 completed"
            handler.sendMessage(message)
        }.execute {
            Thread.sleep(1000)
            val message = Message.obtain()
            message.obj = "Task-3 completed"
            handler.sendMessage(message)
        }
    }

    override fun onPause() {
        super.onPause()
        simpleWorker.terminate()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
