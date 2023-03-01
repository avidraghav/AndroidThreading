package com.example.androidthreading

import android.util.Log
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Own implementation of a thread which has
 * a Looper to keep it active (mimicked by a simple loop)
 * a Message Queue which keeps the list of tasks x
 */

class SimpleWorker : Thread() {
    companion object {
        private const val TAG = "SimpleWorker"
    }

    private var isThreadAlive = true
    private val taskQueue: ConcurrentLinkedQueue<Runnable> = ConcurrentLinkedQueue()

    init {
        start()
    }

    override fun run() {
        while (isThreadAlive) {
            val task = taskQueue.poll()
            task?.run()
        }
        Log.d(TAG, "SimpleWorker Terminated")
    }

    fun execute(task: Runnable): SimpleWorker {
        taskQueue.add(task)
        return this
    }

    fun quit() {
        isThreadAlive = false
    }
}
