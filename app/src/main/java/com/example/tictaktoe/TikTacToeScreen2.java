package com.example.tictaktoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class TikTacToeScreen2 extends AppCompatActivity {

    ImageButton b1;
    Button b2;
    char[][] board ={{'c','c','c'},{'c','c','c'},{'c','c','c'}};
    char turn = 'a';

    // saves how valuable character played at that square is
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;
    int e = 0;
    int f = 0;
    int g = 0;
    int h = 0;
    int i =0;

    //count used to keep track how much of each character is used
    int count1 = 2;
    int count2 = 2;
    int count3 = 2;
    int count4 = 2;
    int count5 = 2;
    int count6 = 2;

 //Used to save users character choice
    int choice = 1;
   int choice2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tik_tac_toe_screen2);

        b2= findViewById(R.id.instructions);
        b2.setOnClickListener(
                //when instruction button pressed moves to instruction page
                v -> {
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.tikTacToeScreen2,new InstructionPage()).commit();

                }
        );

        b1 = findViewById(R.id.homebutton);
        b1.setOnClickListener(
                v -> {
                   //when homebutton pressed, page moved to home page
                    FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.tikTacToeScreen2,new FirstFragment()).commit();
                }
        );

    }


// Method to alerts users that a count variable is at 0; thus no more of the character left
    public void Alert(int x, int y) {
    AlertDialog.Builder builder = new AlertDialog.Builder(TikTacToeScreen2.this);
    builder.setMessage("There is no more of this character left");
    builder.setTitle("No More Left");
    builder.setCancelable(true);
    AlertDialog alertDialog = builder.create();
    alertDialog.show();
    board[x][y] = 'c';
}

//When User replaces a used character, the character is added back into count
    public void ChangeText(int x,char a, char b){


        TextView zombie1 = findViewById(R.id.zombie1text)     ;
        TextView zombie2 = findViewById(R.id.zombie2text)     ;
        TextView zombie3 = findViewById(R.id.zombie3text);
        TextView skeleton1 = findViewById(R.id.zombie4text)     ;
        TextView skeleton2 = findViewById(R.id.zombie5text)     ;
        TextView skeleton3 = findViewById(R.id.zombie6text)     ;


//If it is b turn, changes zombie text if zombie was replaced
        if (turn == b){
            if (x==1){
                count1++;
                zombie1.setText("" + count1);
            }

            else if (x==2){
                count2++;
                zombie2.setText("" + count2);
            }
            else if (x==3){
                count3++;
                zombie3.setText("" + count3);
            }

//If it is a turn, changes skeleton text if zombie was replaced
        }
        else if (turn == a){
            if (x==1){
                count4++;
                skeleton1.setText("" + count4);
            }

            else if (x==2){
                count5++;
                skeleton2.setText("" + count5);
            }

            else if (x==3){
                count6++;
                skeleton3.setText("" + count6);
            }




        }
    }

// Changes the image of the square
@SuppressLint("SetTextI18n")
public void changeSquare (ImageButton view, int x, int y){


    TextView zombie1 = findViewById(R.id.zombie1text)     ;
    TextView zombie2 = findViewById(R.id.zombie2text)     ;
    TextView zombie3 = findViewById(R.id.zombie3text)     ;
    TextView skeleton1 = findViewById(R.id.zombie4text)     ;
    TextView skeleton2 = findViewById(R.id.zombie5text)     ;
    TextView skeleton3 = findViewById(R.id.zombie6text)     ;

    if (turn == 'a') {
        //finds choice and sets it to the respective image
        if (choice == 1) {
            if (count1 > 0) {
                count1--;
                zombie1.setText("" + count1);
                view.setImageResource(R.drawable.zombiea);
                FlipTurn();
                Win();
            } else {
                Alert(x,y);
            }
        } else if (choice == 2) {
            if (count2 > 0) {
                count2--;
                zombie2.setText("" + count2);
                view.setImageResource(R.drawable.zombie2);
                FlipTurn();
                Win();
            } else {
                Alert(x,y);

            }

        } else if (choice == 3) {
            if (count3 > 0) {
                count3--;
                zombie3.setText("" + count3);
                view.setImageResource(R.drawable.zombie3);
                FlipTurn();
                Win();
            } else {
                Alert(x,y);
            }
        }
    }
    else if (turn == 'b') {
        //finds choice and sets it to the respective image when its skeletons turn
        if (choice == 1) {
           if (count4>0) {
               count4--;
               skeleton1.setText("" + count4);
               view.setImageResource(R.drawable.skeletonb1);
               FlipTurn();
               Win();
           }
           else {
               Alert(x,y);
           }
        }
        else if (choice == 2) {
            if (count5>0) {
                count5--;
                skeleton2.setText("" + count5);
                view.setImageResource(R.drawable.skeletonb2);
                FlipTurn();
                Win();
            }
            else {
                Alert(x,y);
            }
        }
        else if (choice == 3) {
            if (count6>0) {
                count6--;
                skeleton3.setText("" + count6);
                view.setImageResource(R.drawable.skeletonb3);
                FlipTurn();
                Win();
            }
            else {
                Alert(x,y);
            }
        }
    }

}

    public void upDateSquare (int x, int y,ImageButton view,int boardnum){
        // board is empty
    if (board [x][y] == 'c')
    {
        board[x][y] = turn;
        changeSquare(view,x,y);
        choice =1;
        //when board and turn are the same
    } else if (board [x][y] == turn) {
        //boardnum(tells how high played chacter is), if less then new choice code, change square occurs
        if(boardnum < choice) {
            ChangeText(boardnum,'b','a');
            changeSquare(view, x, y);
            choice = 1;
        }

    } else {
        if (boardnum < choice) {
            ChangeText(boardnum,'a','b');
            board[x][y] = turn;
            changeSquare(view,x,y);
            choice = 1;

        }
    }
    }

    // win patters
    public void Win(){
        char winner = 'n';
        if (board[0][0] == board[0][1] && board [0][0] == board [0][2] && board[0][0]!='c')
            winner = board[0][0];
        else if (board[1][0] == board[1][1] && board [1][0] == board [1][2] && board[1][0]!='c')
            winner = board[1][0];
        else if (board[2][0] == board[2][1] && board [2][0] == board [2][2] && board[2][0]!='c')
            winner = board[2][0];
        else if (board[0][1] == board[1][1] && board [0][1] == board [2][1] && board[0][1]!='c')
            winner = board[0][1];
        else if (board[0][2] == board[1][2] && board [0][2] == board [2][2] && board[0][2]!='c')
            winner = board[0][2];
        else if (board[0][0] == board[1][0] && board [0][0] == board [2][0] && board[0][0]!='c')
            winner = board[0][0];
        else if (board[0][0] == board[1][1] && board [0][0] == board [2][2] && board[0][0]!='c')
            winner = board[0][0];
        else if (board[2][0] == board[1][1] && board [2][0] == board [0][2] && board[2][0]!='c')
            winner = board[2][0];
        else if (board[0][0]!='c'&& board[0][1]!='c'&&board[0][2]!='c'&&
                board[1][0]!='c'&&board[1][1]!='c'&&board[1][2]!='c'&&
                board[2][0]!='c'&&board[2][1]!='c'&&board[2][2]!='c')
            winner = 't';

        if (winner == 'a') {
            AlertDialog.Builder builder = new AlertDialog.Builder(TikTacToeScreen2.this);
            builder.setMessage("Congrats, Zombie Wins");
            builder.setTitle("Winner Zombie!");
            builder.setCancelable(false);
            builder.setNeutralButton("Play Again", (dialog, which) -> {
                // When the user click yes button then app will close
                Reset();
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        else if(winner == 'b'){
            AlertDialog.Builder builder = new AlertDialog.Builder(TikTacToeScreen2.this);
            builder.setMessage("Congrats,Skeleton Wins");
            builder.setTitle("Winner Skeleton!");
            builder.setCancelable(false);
            builder.setNeutralButton("Reset", (dialog, which) -> {
                // When the user click yes button then app will close
                Reset();
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else if (winner == 't') {
            AlertDialog.Builder builder = new AlertDialog.Builder(TikTacToeScreen2.this);
            builder.setMessage("Tie Game");
            builder.setTitle("No one wins, Play Again!");
            builder.setCancelable(false);
            builder.setNeutralButton("Reset", (dialog, which) -> {
                        // When the user click yes button then app will close
                        Reset();
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
    public void Reset(){

        TextView zombie1 = findViewById(R.id.zombie1text);
        TextView zombie2 = findViewById(R.id.zombie2text)     ;
        TextView zombie3 = findViewById(R.id.zombie3text)     ;
        TextView skeleton1 = findViewById(R.id.zombie4text)     ;
        TextView skeleton2 = findViewById(R.id.zombie5text)     ;
        TextView skeleton3 = findViewById(R.id.zombie6text)     ;

        ImageView imageturn = findViewById(R.id.TurnImage);
        ImageButton buttonA= findViewById(R.id.buttonA);
        ImageButton buttonB= findViewById(R.id.buttonB);
        ImageButton buttonC= findViewById(R.id.buttonC);
        ImageButton buttonD= findViewById(R.id.buttonD);
        ImageButton buttonE= findViewById(R.id.buttonE);
        ImageButton buttonF= findViewById(R.id.buttonF);
        ImageButton buttonG= findViewById(R.id.buttonG);
        ImageButton buttonH= findViewById(R.id.buttonH);
        ImageButton buttonI= findViewById(R.id.buttonI);



        board[0][0] = 'c';
        board[0][1] = 'c';
        board[0][2] = 'c';
        board[1][0] = 'c';
        board[1][1] = 'c';
        board[1][2] = 'c';
        board[2][0] = 'c';
        board[2][1] = 'c';
        board[2][2] = 'c';

        turn = 'a';
         count1 = 2;
         count2 = 2;
         count3 = 2;
         count4 = 2;
         count5 = 2;
         count6 = 2;
         choice = 1;
         choice2 = 0;
        imageturn.setImageResource(R.drawable.zombiea);
        buttonA.setImageResource(R.drawable.chest);
        buttonB.setImageResource(R.drawable.chest);
        buttonC.setImageResource(R.drawable.chest);
        buttonD.setImageResource(R.drawable.chest);
        buttonE.setImageResource(R.drawable.chest);
        buttonF.setImageResource(R.drawable.chest);
        buttonG.setImageResource(R.drawable.chest);
        buttonH.setImageResource(R.drawable.chest);
        buttonI.setImageResource(R.drawable.chest);
        zombie1.setText("" + count1);
        zombie2.setText("" + count2);
        zombie3.setText("" + count2);
        skeleton1.setText("" + count2);
        skeleton2.setText("" + count2);
        skeleton3.setText("" + count2);

    }

    public void ChangeChoice(int x,char type) {
        ImageView imageturn = findViewById(R.id.TurnImage);
        if (turn == 'a'){
                if (x==1)
                    imageturn.setImageResource(R.drawable.zombiea);
                else if (x==2)
                    imageturn.setImageResource(R.drawable.zombie2);
                else if (x==3)
                    imageturn.setImageResource(R.drawable.zombie3);

            }
        else if (turn == 'b'){
                if (x==1)
                imageturn.setImageResource(R.drawable.skeletonb1);
                else if (x==2)
                    imageturn.setImageResource(R.drawable.skeletonb2);
                else if (x==3)
                    imageturn.setImageResource(R.drawable.skeletonb3);
            }
        choice = x;
    }

    public void FlipTurn(){
        ImageView imageturn = findViewById(R.id.TurnImage);
        //gives turn to other player
        if (turn == 'a') {
            turn = 'b';
            imageturn.setImageResource(R.drawable.skeletonb1);
        }
        else {
                turn = 'a';
                imageturn.setImageResource(R.drawable.zombiea);
            }
        }

    public void ButtonA(View view) {
        ImageButton buttonA= findViewById(R.id.buttonA);
        upDateSquare(0,0,buttonA,a);
        // saves how valuable character played at that square is
        a=choice;

    }

    public void B(View view) {
        ImageButton buttonB= findViewById(R.id.buttonB);
        upDateSquare(0,1,buttonB,b);
        b=choice;
    }

    public void C(View view) {
        ImageButton buttonC= findViewById(R.id.buttonC);
        upDateSquare(0,2,buttonC,c);
        c=choice;
    }
    public void D(View view) {
        ImageButton buttonD= findViewById(R.id.buttonD);
        upDateSquare(1,0,buttonD,d);
        d=choice;
    }

    public void E(View view) {
        ImageButton buttonE= findViewById(R.id.buttonE);
        upDateSquare(1,1,buttonE,e);
        e=choice;
    }

    public void F(View view) {
        ImageButton buttonF= findViewById(R.id.buttonF);
       upDateSquare(1,2,buttonF,f);
        f=choice;
    }

    public void G(View view) {
        ImageButton buttonG= findViewById(R.id.buttonG);
        upDateSquare(2,0,buttonG,g);
        g=choice;
    }

    public void H(View view) {
        ImageButton buttonH= findViewById(R.id.buttonH);
        upDateSquare(2, 1, buttonH,h);
        h=choice;
    }
    public void I(View view) {
        ImageButton buttonI= findViewById(R.id.buttonI);
       upDateSquare(2,2,buttonI,i);
        i=choice;
    }

    public void zombie1(View view) {
        ChangeChoice(1,'a');
    }

    public void zombie2(View view) {
       ChangeChoice(2,'a');
    }

    public void zombie3(View view) {
        ChangeChoice(3,'a');
    }

    public void skeleton1(View view) {
        ChangeChoice(1,'b');
        }

    public void skeleton2(View view) {
        ChangeChoice(2,'b');
    }

    public void skeleton3(View view){
        ChangeChoice(3,'b');
    }

    public void ResetButton(View view) {
        Reset();
    }
}



