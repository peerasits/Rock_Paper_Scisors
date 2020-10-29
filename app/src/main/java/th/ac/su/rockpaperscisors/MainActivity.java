package th.ac.su.rockpaperscisors;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private int round = 1,score = 0;
    private Button[] mButtons =  new Button [3];
    private TextView roundText,challengeText;
    private String answer[] = {"rock","paper","scissors"};
    private String challengeStr[] = {"You turn","Cool?","You turn","Guess What?","Hey!"};
    private int indexAnswer,indexChallenge;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roundText = findViewById(R.id.round_text);
        challengeText = findViewById(R.id.challenge_text);

        mButtons[0] = findViewById(R.id.rock_btn);
        mButtons[1] = findViewById(R.id.paper_btn);
        mButtons[2] = findViewById(R.id.scissors_btn);

        mButtons[0].setOnClickListener(this);
        mButtons[1].setOnClickListener(this);
        mButtons[2].setOnClickListener(this);

        random = new Random();

        game();
    }

    public void game(){
        if(round<=5)
            roundText.setText("Round "+String.valueOf(round));
        indexAnswer = random.nextInt(answer.length);
        indexChallenge = random.nextInt(challengeStr.length);
        challengeText.setText(challengeStr[indexChallenge]);
    }
    @Override
    public void onClick(View v) {
        Button b = findViewById(v.getId());
        String answerStr = answer[indexAnswer];
        String userGuess = b.getText().toString();
        String display = "";
        if(answerStr.equals(userGuess)) {
            display = "draw";
            score++;
        }
        else if(userGuess.equals("rock")){
            if(answerStr.equals("paper")){
               display = "lose";
            }else if(answerStr.equals("scissors"))
            {
                display = "win";
                score+=2;
            }
        }else if(userGuess.equals("paper")){
            if(answerStr.equals("rock")){
                display = "win";
                score+=2;
            }else if(answerStr.equals("scissors"))
            {
                display = "lose";
            }
        }else{
            if(answerStr.equals("rock")){
                display = "lose";
            }else if(answerStr.equals("paper"))
            {
                display = "win";
                score+=2;
            }
        }
        //Toast.makeText(MainActivity.this, display, Toast.LENGTH_SHORT).show();

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Your "+display);
        dialog.setMessage("Competitor try : "+answerStr+"\n your score = "+score);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (round > 5){
                    Intent intent = new Intent(MainActivity.this,Summary.class);
                    intent.putExtra("score",score);
                    startActivity(intent);
                }
            }
        });
        dialog.show();


        if(round <=5) {
            round++;
            game();
        }

    }
}