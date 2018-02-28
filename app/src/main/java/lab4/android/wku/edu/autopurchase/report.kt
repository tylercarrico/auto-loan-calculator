package lab4.android.wku.edu.autopurchase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.support.v7.widget.Toolbar

import android.widget.TextView

import java.text.DecimalFormat

//Tyler Carrico
// Activity for calculating loan and displaying
class report : AppCompatActivity() {

    internal var df = DecimalFormat("#.##")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        val actionBarToolbar = findViewById(R.id.my_toolbar) as Toolbar
        setSupportActionBar(actionBarToolbar)
        val menu = supportActionBar
        menu!!.setDisplayShowHomeEnabled(true)
        menu!!.setDisplayHomeAsUpEnabled(true)
        menu!!.setIcon(R.drawable.car_sedan2)

        //price
        val extras = intent.extras
        val tmp = extras!!.getString("price")
        val price = findViewById(R.id.price) as TextView
        price.append(tmp)
        //length
        val years = extras.getInt("years")
        val months = years * 12

        //term
        val term_length = findViewById(R.id.term) as TextView
        term_length.append(Integer.toString(years))

        //downpayment
        val dp = extras.getString("down")
        val down = findViewById(R.id.down) as TextView
        down.append(dp)
        //principle
        val principal = Integer.parseInt(tmp) - Integer.parseInt(dp)
        val borrowed = findViewById(R.id.borrowed) as TextView
        borrowed.append(Integer.toString(principal))
        //tax
        val sales_tax = Integer.parseInt(tmp) * 0.06
        val tax = findViewById(R.id.tax) as TextView
        tax.append(java.lang.Double.toString(sales_tax))
        //your cost
        val cost = java.lang.Double.parseDouble(tmp) + sales_tax + 300.0
        val yourCost = findViewById(R.id.yourCost) as TextView
        yourCost.append(java.lang.Double.toString(cost))
        //rate
        val rate = extras.getString("rate")
        val yearly = findViewById(R.id.rate) as TextView
        yearly.append(rate)
        val monthly = java.lang.Double.parseDouble(rate) / 12
        //payment
        val payment = principal * (monthly * Math.pow(1 + monthly, months.toDouble())) / (Math.pow(1 + monthly, months.toDouble()) - 1)
        val monthly_payment = findViewById(R.id.monthly) as TextView
        monthly_payment.append("%.2f".format(payment))
        //interest
        val total_interest = java.lang.Double.parseDouble(rate) / months * principal
        val totalInterest = findViewById(R.id.totalInterest) as TextView
        totalInterest.append(java.lang.Double.toString(total_interest))

        //total
        val total = payment + sales_tax + 300.0 + total_interest
        val totalCost = findViewById(R.id.totalCost) as TextView
        totalCost.append("%.2f".format(total))
    }

    override fun onStart() {
        super.onStart()
        overridePendingTransition(R.anim.report_in, R.anim.main_out)
    }
}
