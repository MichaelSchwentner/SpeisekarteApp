package com.example.speisekarteapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.speisekarteapp.databinding.ActivityMainBinding
import kotlin.math.round

/**
 * Main Activity, dient als Einstiegspunkt für die App
 */
class MainActivity : AppCompatActivity() {

    /* -------------------- Klassen Variablen -------------------- */
    private var bill: Float = 0f
    private val drink1 = Drink("Kaffee", 3.95f)
    private val drink2 = Drink("Wein", 4.20f)
    private val drink3 = Drink("Cocktail", 6.90f)

    /* -------------------- Lifecycle -------------------- */
    /**
     * Lifecycle Funktion, wird aufgerufen wenn Activity erstellt wird
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Lege hier die Binding Variable an
        // TODO Schreibe hier deinen Code
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (savedInstanceState != null){
            drink1.count = savedInstanceState.getInt("Drink1")
            drink2.count = savedInstanceState.getInt("Drink2")
            drink3.count = savedInstanceState.getInt("Drink3")
            bill = savedInstanceState.getFloat("Bill")
        }
        // Setze hier die Namen, Preise und Count in der View über die binding Variable
        // TODO Schreibe hier deinen Code
        binding.drink1Name.text = drink1.name
        binding.drink2Name.text = drink2.name
        binding.drink3Name.text = drink3.name

        binding.drink1Price.text = "%.2f".format(drink1.price)
        binding.drink2Price.text = "%.2f".format(drink2.price)
        binding.drink3Price.text = "%.2f".format(drink3.price)

        binding.drink1Count.text = drink1.count.toString()
        binding.drink2Count.text = drink2.count.toString()
        binding.drink3Count.text = drink3.count.toString()
        binding.totalPrice.text = ("%.2f".format(bill)).toString()
        //binding.totalPrice.text = bill.toString()
        // implementiere einen onClicklistener für das Kaffee Icon
        // TODO Schreibe hier deinen Code
        binding.drink1Button.setOnClickListener {
        try {
            addToBill(drink1.price)
            drink1.count++
            binding.drink1Count.text = drink1.count.toString()
            binding.totalPrice.text = "%.2f".format(bill)
        }catch (e: Exception){
            Toast.makeText(this,"Problem", Toast.LENGTH_LONG)
        }
        }
        // implementiere einen onClicklistener für das Wein Icon
        // TODO Schreibe hier deinen Code
        binding.drink2Button.setOnClickListener {
            try {
                addToBill(drink2.price)
                drink2.count++
                binding.drink2Count.text = drink2.count.toString()
                binding.totalPrice.text = "%.2f".format(bill)
            }catch (e: Exception){
                Toast.makeText(this,"Problem", Toast.LENGTH_LONG)
            }
        }
        // implementiere einen onClicklistener für das Cocktail Icon
        // TODO Schreibe hier deinen Code
        binding.drink3Button.setOnClickListener {
            try {
                addToBill(drink3.price)
                drink3.count++
                binding.drink3Count.text = drink3.count.toString()
                binding.totalPrice.text = "%.2f".format(bill)
            }catch (e: Exception){
                Toast.makeText(this,"Problem", Toast.LENGTH_LONG)
            }
        }
        //TODO Impementier einen Reset Button
        binding.resetButton.setOnClickListener {
            drink1.count = 0
            binding.drink1Count.text = drink1.count.toString()

            drink2.count = 0
            binding.drink2Count.text = drink2.count.toString()

            drink3.count = 0
            binding.drink3Count.text = drink3.count.toString()

            bill = 0f
            binding.totalPrice.text = "%.2f".format(bill)
        }

    }

    /**
     * Diese Funktion addiert den Preis zur Rechnung
     */
    private fun addToBill(price: Float?) {
        if (price != null) {
            bill += price
            bill = round(bill * 100) / 100
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("Drink1", drink1.count)
        outState.putInt("Drink2", drink2.count)
        outState.putInt("Drink3", drink3.count)
        outState.putFloat("Bill", bill)
    }
}
