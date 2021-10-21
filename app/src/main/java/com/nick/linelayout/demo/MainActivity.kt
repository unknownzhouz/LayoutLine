package com.nick.linelayout.demo

import android.graphics.Color
import android.os.Bundle
import android.util.SparseArray
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.nick.widget.InsideLineFrameLayout
import com.nick.widget.InsideLineSupport


class MainActivity : AppCompatActivity() {

    private var _attr: Attr? = null
    private val _array = SparseArray<Attr>()

    private var tvGravity: TextView? = null
    private var tvColor: TextView? = null
    private var lineLayout: InsideLineFrameLayout? = null

    private var seekBarSize: SeekBar? = null
    private var seekBarStart: SeekBar? = null
    private var seekBarEnd: SeekBar? = null

    init {
        _array.put(InsideLineSupport.Gravity.LEFT, Attr(InsideLineSupport.Gravity.LEFT))
        _array.put(InsideLineSupport.Gravity.TOP, Attr(InsideLineSupport.Gravity.TOP))
        _array.put(InsideLineSupport.Gravity.RIGHT, Attr(InsideLineSupport.Gravity.RIGHT))
        _array.put(InsideLineSupport.Gravity.BOTTOM, Attr(InsideLineSupport.Gravity.BOTTOM))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _attr = _array[InsideLineSupport.Gravity.LEFT]

        initView()
        resetPosition()

    }

    private fun initView() {
        tvGravity = findViewById<TextView>(R.id.tvGravity)
        tvGravity?.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("请选择方向")
            builder.setItems(menuPosition) { dialog, which ->
                dialog.dismiss()
                var gravity = -1
                when (which) {
                    0 -> gravity = InsideLineSupport.Gravity.LEFT
                    1 -> gravity = InsideLineSupport.Gravity.TOP
                    2 -> gravity = InsideLineSupport.Gravity.RIGHT
                    3 -> gravity = InsideLineSupport.Gravity.BOTTOM
                }

                if (-1 != gravity && _array[gravity] != _attr) {
                    _attr = _array[gravity]
                    resetPosition()
                }
            }

            builder.create().show()
        }

        tvColor = findViewById<TextView>(R.id.tvColor)
        tvColor?.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("请选择色值")
            builder.setItems(menuColor) { dialog, which ->
                dialog.dismiss()
                tvColor?.text = menuColor[which]
                if (menuColor[which] != _attr?.color) {
                    _attr?.color = menuColor[which]
                    update()
                }
            }
            builder.create().show()
        }


        lineLayout = findViewById<InsideLineFrameLayout>(R.id.lineLayout)

        seekBarSize = findViewById<SeekBar>(R.id.seekBarSize)
        seekBarSize?.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (_attr?.size != progress.toFloat()) {
                    _attr?.size = progress.toFloat()
                    update()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        seekBarStart = findViewById<SeekBar>(R.id.seekBarStart)
        seekBarStart?.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (_attr?.start != progress.toFloat()) {
                    _attr?.start = progress.toFloat()
                    update()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        seekBarEnd = findViewById<SeekBar>(R.id.seekBarEnd)
        seekBarEnd?.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (_attr?.end != progress.toFloat()) {
                    _attr?.end = progress.toFloat()
                    update()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


    }

    private fun update() {
        _attr?.let {
            lineLayout?.setLine(it.gravity, it.size, Color.parseColor(it.color), it.start, it.end)
        }
    }

    private fun resetPosition() {
        _attr?.let {
            tvGravity?.text = when (it.gravity) {
                InsideLineSupport.Gravity.LEFT -> menuPosition[0]
                InsideLineSupport.Gravity.TOP -> menuPosition[1]
                InsideLineSupport.Gravity.RIGHT -> menuPosition[2]
                InsideLineSupport.Gravity.BOTTOM -> menuPosition[3]
                else -> {
                    ""
                }
            }

            tvColor?.text = it.color
            seekBarSize?.progress = it.size.toInt()
            seekBarStart?.progress = it.start.toInt()
            seekBarEnd?.progress = it.end.toInt()
            lineLayout?.setLine(it.gravity, it.size, Color.parseColor(it.color), it.start, it.end)
        }
    }

    companion object {
        val menuPosition: Array<String> = arrayOf("LEFT", "TOP", "RIGHT", "BOTTOM")
        val menuColor: Array<String> = arrayOf("#fe6d2d", "#ff2b44", "#666666", "#11aac3", "#000000")
    }

    class Attr(@InsideLineSupport.Gravity var gravity: Int) {
        var color = menuColor[0]
        var size = 10F
        var start = 0F
        var end = 0F

        init {
            when (gravity) {
                InsideLineSupport.Gravity.LEFT -> color = menuColor[0]
                InsideLineSupport.Gravity.TOP -> color = menuColor[1]
                InsideLineSupport.Gravity.RIGHT -> color = menuColor[2]
                InsideLineSupport.Gravity.BOTTOM -> color = menuColor[3]
            }
        }
    }

}
