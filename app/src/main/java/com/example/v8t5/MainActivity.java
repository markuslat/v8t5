package com.example.v8t5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView moneyamount;
    TextView text;
    SeekBar seekBar;
    TextView seekText;
    private Spinner spinner;
    double money = 0;

    BottleDispenser dispencer = BottleDispenser.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekText = findViewById(R.id.seekText);
        moneyamount = (TextView) findViewById(R.id.moneyAmount);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                seekText.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        spinner = findViewById(R.id.spinner);

        final List<String> pullotLista = new ArrayList<>();

        pullotLista.add(0,"Valitse Pullo: ");
        pullotLista.add("PepsiMax 0.5 l 2 euroa");
        pullotLista.add("Pepsi 0.5 l 2 euroa");
        pullotLista.add("Coca-ColaZero 0.5 l 2 euroa");
        pullotLista.add("Coca-ColaZero 1.5 l 3 euroa");
        pullotLista.add("FantaZero 0.5 l 2 euroa");

        ArrayAdapter<String> pulloja = new ArrayAdapter(this,android.R.layout.simple_spinner_item, pullotLista);

        pulloja.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(pulloja);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                text.setText(" ");

                if (parent.getItemAtPosition(position).equals("Valitse Pullo")) {


                }

                else {

                    String valinta = parent.getItemAtPosition(position).toString();
                    String[] valinnat = valinta.split(" ");

                    valinnat[0].toString().trim();
                    valinnat[1].toString().trim();

                    double osto = 0;

                    osto = dispencer.buyBottle(valinnat[0], valinnat[1], money);

                    if (osto == -1) {

                        text.setText("Syötä rahaa ensin!");
                    }

                    else if (osto == -2) {

                        text.setText("Valintasi on loppunut:(");
                    }

                    else {
                        text.setText(" KACHUNK! " + valinnat[0] + " " + valinnat[1] + " tipahti masiinasta");
                        money = osto;
                        moneyamount.setText("Rahaa jäljellä: " + money + "€");

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void addMoney (View v){
        money = money + seekBar.getProgress();
        dispencer.addMoney(text);
        seekBar.setProgress(0);
        moneyamount.setText("Rahaa jäljellä: " + money + "€");
    }

    public void returnMoney (View v){
        dispencer.returnMoney(text, money);
        money = 0;
        moneyamount.setText("Rahaa jäljellä: " + money + "€");
    }
}
