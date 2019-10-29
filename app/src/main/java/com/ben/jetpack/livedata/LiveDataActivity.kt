package com.ben.jetpack.livedata

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ben.jetpack.Person
import com.ben.jetpack.R
import kotlinx.android.synthetic.main.activity_livedata.*

class LiveDataActivity : AppCompatActivity(), View.OnClickListener {

    private val num = MutableLiveData(0)
    private val person = MutableLiveData<Person>(Person("Tom", 0))
    private val lock = "T"
    private var lockNum = 0
    private var isObservePerson = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata)

        num.observe(this, Observer<Int> {
            if (!isObservePerson) {
                text1.text = it.toString()
            }
        })

        person.observe(this, Observer<Person> {
            if (isObservePerson) {
                text1.text = it.toString()
            }
        })

        text1.setOnClickListener(this)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v) {
            text1 -> {
                isObservePerson = !isObservePerson
                if (isObservePerson) {
                    person.value = person.value
                } else {
                    num.value = num.value
                }
            }
            button1 -> {
                // 主线程取值，加一再设置回去
                if (isObservePerson) {
                    val value = person.value ?: Person("Tom", 0)
                    value.age += 1
                    person.value = value
                } else {
                    var value = num.value ?: 0
                    value += 1
                    num.value = value
                }
            }
            button2 -> {
                isObservePerson = false
                Thread {
                    // LiveData.getValue方法并非同步
                    var value = num.value ?: 0
                    value += 1
                    // 在异步线程需要用post方法同步回主线程对数值进行修改
                    num.postValue(value)
                }.start()
            }
            button3 -> {
                isObservePerson = false
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
                isObservePerson = false
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
