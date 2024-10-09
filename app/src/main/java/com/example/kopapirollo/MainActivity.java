package com.example.kopapirollo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int userwins = 0;
    int cpuwins = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    protected void init() {

    }

    public void userClicked(View v) throws Exception {
        Button button = (Button) v;
        String myId = button.getTag().toString();

        ImageView myTmp = (ImageView) findViewById(R.id.userselect);
        switch (myId) {
            case "buttonRock":
                myTmp.setImageResource(R.drawable.rock);
                myTmp.setTag("rock");
                break;
            case "buttonPaper":
                myTmp.setImageResource(R.drawable.paper);
                myTmp.setTag("paper");
                break;
            case "buttonScissors":
                myTmp.setImageResource(R.drawable.scissors);
                myTmp.setTag("scissors");
                break;
            default:
                throw new Exception("Im Stupid");//ha ez lefut akkor ég aház
        }
        computerTurn();
    }

    protected void computerTurn() throws Exception {
        Random random = new Random();
        ImageView myTmp = (ImageView) findViewById(R.id.computerselect);
        int myInt = random.nextInt(3);
        switch (myInt) {
            case 0:
                myTmp.setImageResource(R.drawable.rock);
                myTmp.setTag("rock");
                break;
            case 1:
                myTmp.setImageResource(R.drawable.paper);
                myTmp.setTag("paper");
                break;
            case 2:
                myTmp.setImageResource(R.drawable.scissors);
                myTmp.setTag("scissors");
                break;
            default:
                throw new Exception("Alert Int bound");//ha ez lefut akkor ég a ház
        }
        roundWon();
    }


    protected void roundWon() {
        ImageView cpuTmp = (ImageView) findViewById(R.id.computerselect);
        ImageView userTmp = (ImageView) findViewById(R.id.userselect);

        String cpuSrc = cpuTmp.getTag().toString();
        String userSrc = userTmp.getTag().toString();

        if (cpuSrc == userSrc) {

        } else if (cpuSrc == "rock" && userSrc == "paper") {
            //user won
            userWonRound(true);
        } else if (cpuSrc == "scissors" && userSrc == "rock") {
            //user won
            userWonRound(true);
        } else if (cpuSrc == "paper" && userSrc == "scissors") {
            //user won
            userWonRound(true);
        } else {
            //cpu won
            userWonRound(false);
        }


        if (cpuwins > 2 || userwins > 2) {
            whoWon();
        }
    }

    protected void userWonRound(boolean userwon) {
        if (userwon) {
            userwins++;

        } else {
            cpuwins++;
        }

        TextView myTextView = (TextView) findViewById(R.id.eredmeny);
        myTextView.setText("Eredmeny: Ember:".concat(String.valueOf(userwins)).concat("Computer: ").concat(String.valueOf(cpuwins)));

        Toast myToast = new Toast(this);
        myToast.setDuration(Toast.LENGTH_SHORT);
        myToast.setText(userwon ? "Megnyerted a kört" : "Vesztetted a kört");
        myToast.show();
    }


    protected void whoWon() {
        new AlertDialog.Builder(this)
                .setTitle(userwins > cpuwins ? "Győzelem" : "Vereség")
                .setMessage("Szeretne új játékot játszani?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetGame();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).show();


    }

    protected void resetGame() {
        cpuwins = 0;
        userwins = 0;
        TextView myTextView = (TextView) findViewById(R.id.eredmeny);
        myTextView.setText("Eredmeny: Ember: 0 Computer: 0");
    }

}