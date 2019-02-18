package com.catharsis256.helloworld3

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton

class MainActivity : AppCompatActivity() {


    private var _btnTurnOn: Button? = null
    val btnTurnOn: Button
        get() {
            return _btnTurnOn?: backingFieldInit(R.id.button_turn_on)
        }

    private var _btnTurnOff: Button? = null
    val btnTurnOff: Button
        get() =
            _btnTurnOff?: backingFieldInit(R.id.button_turn_off)


    private var _check: CheckBox? = null
    val check: CheckBox
        get() =
            _check?: backingFieldInit(R.id.checkbox_check_me)


    fun <T : View> backingFieldInit(id: Int) =
        findViewById<T>(id) ?: throw AssertionError("Set to null by another thread")

/*
    fun <T : View> backingFieldInit(_t: AtomicReference<T?>, i: Int) =
        _t.updateAndGet { findViewById(i) } ?: throw AssertionError("Set to null by another thread")
*/


    private fun <T : View> T.setMyOnClick() = setOnClickListener {
        when (it.id) {
            R.id.button_turn_on ->
                if (!check.isChecked)
                    check.isChecked = true
            R.id.button_turn_off ->
                if (check.isChecked)
                    check.isChecked = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*
        btnTurnOn = (findViewById(R.id.button_turn_on) as? Button)?.apply { setMyOnClick() }?:
                throw RuntimeException("button 'turn on' has not found")

        btnTurnOff = (findViewById(R.id.button_turn_off) as? Button)?.apply { setMyOnClick() }?:
                throw RuntimeException("button 'turn on' has not found")

        check = (findViewById(R.id.checkbox_check_me) as? CheckBox)?: // .apply { setMyOnClick() }?:
                throw RuntimeException("checkbox 'check me' has not found")
*/
        run {
            btnTurnOn.apply { setMyOnClick() }
        }

        run {
            btnTurnOff.apply { setMyOnClick() }
        }

        check.apply {
            setOnCheckedChangeListener {
                _: CompoundButton?, isChecked: Boolean ->
                Log.d("HAPPY", "Status: $isChecked")
            }
        }

    }


}

internal fun <T : View> T.setMyOnClick(check: CheckBox) = setOnClickListener {
    it?.let { k ->
        when (k.id) {
            R.id.button_turn_on -> check.isChecked = true
            R.id.button_turn_off -> check.isChecked = false
        }
    }
}

