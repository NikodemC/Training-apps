package com.example.android.justjava;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    /**
     * This method is called when the order button is clicked.
     */
    int coffeeNo = 0;
    int coffeCupPrice = 5;
    String name = "Nikodem Cabała";
    boolean addCream = false;
    boolean addChocolate = false;


    public void submitOrder(View view) {
        addCream = ((CheckBox) findViewById(R.id.cream_checkBox)).isChecked();
        addChocolate = ((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked();
        displayOrderSummary(coffeeNo, addCream, addChocolate);
        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        if (coffeeNo > 0)
            cancelButton.setBackgroundColor(Color.RED);
        else
            cancelButton.setBackgroundColor(Color.GRAY);
    }

    public void clearOrder(View view) {
        if (coffeeNo > 0) {
            coffeeNo = 0;
            Button cancelButton = (Button) findViewById(R.id.cancel_button);
            cancelButton.setBackgroundColor(Color.GRAY);
            display(coffeeNo);
            displayOrderSummary(coffeeNo, addCream, addChocolate);
            Toast.makeText(MainActivity.this, getString(R.string.cancel_message), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Calculates the price of the order
     */
    private int calculatePrice() {
        int price = coffeeNo * coffeCupPrice;
        if (addCream == true)
            price += 2 * coffeeNo;
        if (addChocolate == true)
            price += 3 * coffeeNo;
        return price;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public String displayOrderSummary(int number, boolean addCream, boolean addChocolate) {
        String orderSummary="";
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        EditText name = (EditText) findViewById(R.id.name_text);
        if (number > 0) {
            orderSummary = getString(R.string.name) + ": " + name.getText() + "\n";
            if (addCream == true) {
                orderSummary += getString(R.string.add_cream);
                if (addChocolate == true)
                    orderSummary += getString(R.string.and_chocolate) + "\n";
                else
                    orderSummary +="\n";
            }
            if (addChocolate == true && addCream == false)
                orderSummary += getString(R.string.add_chocolate) + "\n";
            orderSummary += getString(R.string.quantity) + ": " + coffeeNo + "\n";
            orderSummary += getString(R.string.total)+" " + NumberFormat.getCurrencyInstance().format(calculatePrice());
            orderSummary += "\n" +getString(R.string.thank_you) ;
            orderSummaryTextView.setText(orderSummary);
        } else {
            orderSummaryTextView.setText("0,00 zł");
        }
        return orderSummary;
    }

    public void composeEmail(View view) {
        String message = displayOrderSummary(coffeeNo, addCream, addChocolate);
        if(message.length()<2){
            Toast.makeText(MainActivity.this, getString(R.string.submit_message), Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject) + ((EditText) findViewById(R.id.name_text)).getText());
            intent.putExtra(Intent.EXTRA_TEXT, message);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }

    public void increase(View view) {
        display(++coffeeNo);
    }

    public void decrease(View view) {
        if (coffeeNo > 0)
            display(--coffeeNo);
    }
}