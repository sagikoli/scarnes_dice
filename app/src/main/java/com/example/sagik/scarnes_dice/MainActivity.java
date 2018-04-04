package com.example.sagik.scarnes_dice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int user_score=0;
    private int temp_score=0;
    private int comp_score=0;
    private int ctemp_score=0;
    Random r=new Random();
    Button roll=null;
    Button hold=null;
    Button reset=null;
    boolean bot=false;
    TextView com_score=null;
    TextView usr_score=null;
    ImageView img=null;
    TextView turn_score=null;
    Context screen=null;
    void Block(View v)
    {
        v.setClickable(false);
        }
        void  UnBlock(View v)
        {
            v.setClickable(true);
        }
    Handler h=new Handler();
        Runnable play=new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {

                switch (r.nextInt(7)) {
                    case 1:
                        img.setImageDrawable(getDrawable(R.drawable.dice1));
                        temp_score=0;
                        bot = false;
                        UnBlock(roll);
                        UnBlock(hold);
                        comp_score+=temp_score;
                        temp_score=0;
                        com_score.setText(Integer.toString(comp_score));
                        turn_score.setText(Integer.toString(temp_score));
                        break;
                    case 2:
                        img.setImageDrawable(getDrawable(R.drawable.dice2));
                        temp_score+=2;
                        break;
                    case 3:
                        img.setImageDrawable(getDrawable(R.drawable.dice3));
                        temp_score+=3;
                        break;
                    case 4:
                        img.setImageDrawable(getDrawable(R.drawable.dice4));
                        temp_score+=4;
                        break;
                    case 5:
                        img.setImageDrawable(getDrawable(R.drawable.dice5));
                        temp_score+=5;
                        break;
                    case 6:
                        img.setImageDrawable(getDrawable(R.drawable.dice6));
                        temp_score+=6;
                        break;
                }
                turn_score.setText(Integer.toString(temp_score));
                if(temp_score+comp_score>=100)
                {
                    reset.callOnClick();
                    Toast.makeText(screen,"Bot wins...!",Toast.LENGTH_LONG).show();
                }
                if (temp_score<10 && bot==true)
                    h.postDelayed(this,800);
                else {
                    bot = false;
                    UnBlock(roll);
                    UnBlock(hold);
                    comp_score+=temp_score;
                    temp_score=0;
                    com_score.setText(Integer.toString(comp_score));
                    turn_score.setText(Integer.toString(temp_score));
                }
            }
        };

    void computer_turn()
    {
        Block(hold);
        Block(roll);
        bot=true;
        h.postDelayed(play,800);
        //play.run();


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hold= findViewById(R.id.hold);
        roll=findViewById(R.id.roll);
        reset=findViewById(R.id.reset);
        com_score=findViewById(R.id.comp_score);
        img=findViewById(R.id.imageView);
        usr_score=findViewById(R.id.user_score);
        com_score=findViewById(R.id.comp_score);
        turn_score=findViewById(R.id.turn_score);
        screen=this;

        roll.setOnClickListener(
                new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View v) {

                        switch (r.nextInt(7)) {
                            case 1:
                            img.setImageDrawable(getDrawable(R.drawable.dice1));
                            temp_score=0;
                            computer_turn();
                            break;
                            case 2:
                                img.setImageDrawable(getDrawable(R.drawable.dice2));
                                temp_score+=2;
                                break;
                            case 3:
                                img.setImageDrawable(getDrawable(R.drawable.dice3));
                                temp_score+=3;
                                break;
                            case 4:
                                img.setImageDrawable(getDrawable(R.drawable.dice4));
                                temp_score+=4;
                                break;
                            case 5:
                                img.setImageDrawable(getDrawable(R.drawable.dice5));
                                temp_score+=5;
                                break;
                            case 6:
                                img.setImageDrawable(getDrawable(R.drawable.dice6));
                                temp_score+=6;
                                break;
                        }
                        turn_score.setText(Integer.toString(temp_score));
                    }
                }
        );

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_score+=temp_score;
                if(user_score>=100)
                {
                    reset.callOnClick();
                    Toast.makeText(screen,"You win...!",Toast.LENGTH_LONG).show();
                }
                usr_score.setText(Integer.toString(user_score));
                temp_score=0;
                turn_score.setText(Integer.toString(temp_score));
                computer_turn();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_score=0;
                comp_score=0;
                ctemp_score=0;
                temp_score=0;
                usr_score.setText(Integer.toString(user_score));
                com_score.setText(Integer.toString(comp_score));
                turn_score.setText(Integer.toString(temp_score));
            }
        });
    }
}
