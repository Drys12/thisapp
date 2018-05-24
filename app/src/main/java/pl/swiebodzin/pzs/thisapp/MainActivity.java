package pl.swiebodzin.pzs.thisapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button przycisk;
    TextView player1;
    TextView player2;
    TextView points1;
    TextView points2;
    TextView currentNumber;
    EditText liczba;
    int counter = 5;
    int currentPlayer = 1;
    int collectPoints1;
    int collectPoints2;
    int playerNum;
    int number;
    int globalCounter = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        przycisk = findViewById(R.id.przycisk);
        currentNumber = findViewById(R.id.currentNumber);
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        points1 = findViewById(R.id.points1);
        points2 = findViewById(R.id.points2);
        liczba = findViewById(R.id.liczba);

        przycisk.setText("START");
        currentNumber.setText(" ");
        player1.setText("your turn");
        player2.setText("your turn");

        przycisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 number = getRandomNumber();
                playerNum= getNumberFromEditText();

                if(playerNum !=0) {
                    gameLogic();
                }
            }
        });


    }

    public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(10);


    }

    //przelaczanie pomiedzy graczami
    public int shiftPlayer(int p) {
        switch (p) {
            case 1:
                currentPlayer = 2;
                player2.setText("your turn");
                player1.setText(" ");
                counter = 5;
                break;

            case 2:
                currentPlayer = 1;
                player1.setText("your turn");
                player2.setText(" ");
                counter = 5;
                break;

        }
        return currentPlayer;
    }

    public void gameLogic() {
        try {
            currentNumber.setText(String.valueOf(number));
            counter--;
            przycisk.setText(String.valueOf(counter));
            if (counter ==0) {
             shiftPlayer(currentPlayer);
            }

            int number = Integer.parseInt(currentNumber.getText().toString());
            playerNum= Integer.parseInt(liczba.getText().toString());

            if (number == playerNum && playerNum != 0) {
                switch (currentPlayer) {
                    case 1:
                        collectPoints1++;
                        points1.setText(String.valueOf(collectPoints1));
                        break;

                    case 2:
                        collectPoints2++;
                        points2.setText(String.valueOf(collectPoints2));
                        break;
                }
            }
            }catch(NumberFormatException e){
                Toast.makeText(getBaseContext(), "Podaj liczbe", Toast.LENGTH_SHORT).show();
            }
        }



    public int getNumberFromEditText(){

        try {
            return  Integer.parseInt(liczba.getText().toString());
        } catch (NumberFormatException e) {
            Log.d("errors", "number not found!!!");
        }
        return  0;

    }
    public void checkCounter(){
        if (counter== 0){
            globalCounter--;
        }
    }
}










