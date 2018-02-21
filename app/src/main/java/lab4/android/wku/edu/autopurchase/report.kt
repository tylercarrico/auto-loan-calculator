package lab4.android.wku.edu.autopurchase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent

import android.widget.TextView

import java.text.DecimalFormat

class report : AppCompatActivity() {

    internal var df = DecimalFormat("#.##")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        val extras = intent.extras
        val tmp = extras!!.getString("price")
        val price = findViewById(R.id.price) as TextView
        price.append(tmp)

        val years = extras.getInt("years")
        val months = years * 12


        val term_length = findViewById(R.id.term) as TextView
        term_length.append(Integer.toString(years))

        val dp = extras.getString("down")
        val down = findViewById(R.id.down) as TextView
        down.append(dp)

        val principal = Integer.parseInt(tmp) - Integer.parseInt(dp)
        val borrowed = findViewById(R.id.borrowed) as TextView
        borrowed.append(Integer.toString(principal))

        val sales_tax = Integer.parseInt(tmp) * 0.06
        val tax = findViewById(R.id.tax) as TextView
        tax.append(java.lang.Double.toString(sales_tax))

        val cost = java.lang.Double.parseDouble(tmp) + sales_tax + 300.0
        val yourCost = findViewById(R.id.yourCost) as TextView
        yourCost.append(java.lang.Double.toString(cost))

        val rate = extras.getString("rate")
        val yearly = findViewById(R.id.rate) as TextView
        yearly.append(rate)
        val monthly = java.lang.Double.parseDouble(rate) / 12

        val payment = principal * (monthly * Math.pow(1 + monthly, months.toDouble())) / (Math.pow(1 + monthly, months.toDouble()) - 1)
        val monthly_payment = findViewById(R.id.monthly) as TextView
        monthly_payment.append(java.lang.Double.toString(payment))

        val total_interest = java.lang.Double.parseDouble(rate) / months * principal
        val totalInterest = findViewById(R.id.totalInterest) as TextView
        totalInterest.append(java.lang.Double.toString(total_interest))


        val total = payment + sales_tax + 300.0 + total_interest
        val totalCost = findViewById(R.id.totalCost) as TextView
        totalCost.append(java.lang.Double.toString(total))

    }
}
