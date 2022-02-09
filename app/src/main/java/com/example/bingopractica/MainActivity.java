package com.example.bingopractica;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Random bolita = new Random();
    int bolaLanzada=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crearBingo();

        final Handler handler = new Handler();
        final int tiempoEspera = 3000; // milisegundos

        handler.postDelayed(new Runnable() {
            public void run() {
                lanzarBola();
                handler.postDelayed(this, tiempoEspera);
            }
        }, tiempoEspera);

    }


     ArrayList<Integer> bolasPartida = new ArrayList<>();

    public void lanzarBola(){
        bolaLanzada = bolita.nextInt(99);
        Log.d("Bolas", String.valueOf(bolaLanzada));
        bolasPartida.add(bolaLanzada);
        Log.d("Bolas arrayList", bolasPartida.toString());

        TextView textViewBola = findViewById(R.id.textViewNumber);
        textViewBola.setText("Salió el " + bolaLanzada);
    }

    public void crearBingo() {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        //Crea la grid dinámicamente


        GridLayout g =  findViewById(R.id.gridLay);
        g.removeAllViews();

        g.setColumnCount(5);
        g.setRowCount(6);

        //parámetros para la grid



        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.setMargins(10, 0, 0, 0);
        param.height = ViewGroup.LayoutParams.MATCH_PARENT;
        param.width = ViewGroup.LayoutParams.MATCH_PARENT;

        g.setRowCount(6);
        g.setColumnCount(5);


        LinearLayout.LayoutParams layoutParams = new
                LinearLayout.LayoutParams(width / 5, (height / 6));
        layoutParams.setMargins(0, 0, 0, 0);

TextView[][] textView = null;
Random aleatorio = new Random();

        for(int i = 0; i < 6 ; i++) {
            for (int j = 0; j < 5 ; j++) {
                    textView =  new TextView[6][5];
                    TextView textView1 = new TextView(this);
                    textView1.setLayoutParams(layoutParams);
                    ArrayList<Integer> carton = new ArrayList<>(20);

                    

                    textView1.setText(String.valueOf(aleatorio.nextInt(99)));
                    textView1.setBackgroundColor(Color.WHITE);
                    textView1.setTextColor(Color.GRAY);
                    textView[i][j] = textView1;


                    textView[i][j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TextView b = (TextView) view;
                            if (textView1.getText().equals(String.valueOf(bolaLanzada))){
                                b.setBackgroundColor(Color.BLACK);
                                textView1.setBackgroundColor(Color.BLACK);
                            } else {
                                perderPartid();
                            }

                        }
                    });


                    g.addView(textView[i][j]);
                }

            }

    }

    private void perderPartid() {

    }
}