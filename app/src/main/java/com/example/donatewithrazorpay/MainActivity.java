package com.example.donatewithrazorpay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    // Initializing variable
    Button btdonate;
    TextView linkTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // Assign variable
        btdonate = findViewById(R.id.bt_donate);
        // Intialize Amount
        String sAmount = "100";
        // Convert and round off
        int amount = Math.round(Float.parseFloat(sAmount)* 100);

        btdonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize razorpay checkout

                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_OT4MzwuJ1tU9jN");
                // Set image
                checkout.setImage(com.razorpay.R.drawable.rzp_logo);
                // Initialize json object

                JSONObject object = new JSONObject();
                try {
                    // Put name
                    object.put("name","Satyam Kumar Navneet");
                    // Put description
                    object.put("description","Test payment");
                    // Put theme color
                    object.put("theme.color", "#0093DD");
                    // Put currency unit
                    object.put("currency","INR");
                    // Put amount
                    object.put("amount",amount);
                    // Put mobile number
                    object.put("prefill.contact", "9999999999");
                    // Put email address
                    object.put("prefill.email","navneetsatyamkumar@gmail.com");
                    // Open razorPay checkout activity
                    checkout.open(MainActivity.this, object);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

        linkTextView = findViewById(R.id.activity_main_link);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());



    }


    @Override
    public void onPaymentSuccess(String s) {
        // Initoalize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set title
        builder.setTitle("Payment ID");
        // Set message
        builder.setMessage(s);
        // show alert dialog
        builder.show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        // Display toast
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

    }




}