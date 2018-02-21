package lab4.android.wku.edu.autopurchase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity(), View.OnClickListener {

    internal var years = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submit = findViewById(R.id.submit) as Button
        submit.setOnClickListener(this) // calling onClick() method

        val radioGroup = findViewById(R.id.radioGroup) as RadioGroup
        radioGroup.setOnClickListener(this)


    }

    fun onRadioButtonClick(view: View) {// Is the button now checked?

        val checked = (view as RadioButton).isChecked

        // Check which radio button was clicked
        when (view.getId()) {
            R.id.two_year -> if (checked)
                years = 2
            R.id.three_year -> if (checked)
                years = 3
            R.id.five_year -> if (checked)
                years = 5
        }

    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.submit -> {

                val price = findViewById(R.id.price) as EditText
                val price_message = price.text.toString()
                val launch = Intent(this, report::class.java)
                launch.putExtra("price", price_message)

                val down = findViewById(R.id.down) as EditText
                val down_message = down.text.toString()
                launch.putExtra("down", down_message)

                val rate = findViewById(R.id.rate) as EditText
                val rate_message = rate.text.toString()
                launch.putExtra("rate", rate_message)

                launch.putExtra("years", years)


                startActivity(launch)
            }

            else -> {
            }
        }

    }
}