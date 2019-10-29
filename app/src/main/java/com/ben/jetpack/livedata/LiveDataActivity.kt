package com.ben.jetpack.livedata

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ben.jetpack.R
import kotlinx.android.synthetic.main.activity_livedata.*

class LiveDataActivity : AppCompatActivity(), View.OnClickListener {

    private val num = MutableLiveData(0)
    private val lock = "T"
    private var lockNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata)

        num.observe(this, Observer<Int> {
            text1.text = it.toString()
        })

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v) {
            button1 -> {
                // 主线程取值，加一再设置回去
                var value = num.value ?: 0
                value += 1
                num.value = value
            }
            button2 -> {
                Thread {
                    // LiveData.getValue方法并非同步
                    var value = num.value ?: 0
                    value += 1
                    // 在异步线程需要用post方法同步回主线程对数值进行修改
                    num.postValue(value)
                }.start()
            }
            button3 -> {
                for (a in 0 until 100) {
                    Thread {
                        // LiveData.getValue方法并非同步
                        var value = num.value ?: 0
                        value += 1
                        num.postValue(value)
                    }.start()
                }
            }
            button4 -> {
                for (a in 0 until 100) {
                    // 异步，锁住值，再同步回主线程
                    Thread {
                        synchronized(lock) {
                            lockNum += 1
                        }
                        num.postValue(lockNum)
                    }.start()
                }
            }
            else -> {
            }
        }
    }
}
