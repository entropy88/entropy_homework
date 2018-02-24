package com.example.entropy.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button sendOrder;
    private String orderString = "order";
    private EditText name;
    private String customerName;
    private CheckBox cream;
    private CheckBox choc;
    private TextView coffeeCount;
    private Button increase;
    private Button decrease;
    private int numberOfCoffees = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendOrder = (Button) findViewById(R.id.btn_order);
        name = (EditText) findViewById(R.id.edt_name);
        coffeeCount = (TextView) findViewById(R.id.tv_coffeeCount);
        increase = (Button) findViewById(R.id.btn_increase);
        decrease = (Button) findViewById(R.id.btn_decrease);
        cream = (CheckBox) findViewById(R.id.chk_cream);
        choc = (CheckBox) findViewById(R.id.chk_choc);

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOfCoffees++;
                coffeeCount.setText(String.valueOf(numberOfCoffees));
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOfCoffees--;
                coffeeCount.setText(String.valueOf(numberOfCoffees));

            }
        });


        sendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerName = name.getText().toString();
                orderString = "Hello, I would like " + numberOfCoffees + " coffees.";

                if (cream.isChecked()) {
                    orderString += " Please add sugar.";
                }
                if (choc.isChecked()) {
                    orderString += " Please add chocolate.";
                }

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:entropystarrover@gmail.com"));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order");
                emailIntent.putExtra(Intent.EXTRA_TEXT, orderString);
                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);
                }


            }
        });
    }
}
