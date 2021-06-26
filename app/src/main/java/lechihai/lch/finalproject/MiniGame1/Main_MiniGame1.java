package lechihai.lch.finalproject.MiniGame1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import lechihai.lch.finalproject.MainActivity;
import lechihai.lch.finalproject.R;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main_MiniGame1 extends AppCompatActivity {

    TextView txtDiem;
    ImageButton ibtnPlay;
    CheckBox cbOne, cbTwo, cbThree;
    SeekBar skOne, skTwo, skThree;
    int soDiem = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame1_main);

        txtDiem = (TextView) findViewById(R.id.textviewDiemSo);
        ibtnPlay = (ImageButton) findViewById(R.id.imagebuttonPlay);
        cbOne = (CheckBox) findViewById(R.id.checkboxOne);
        cbTwo = (CheckBox) findViewById(R.id.checkboxTwo);
        cbThree = (CheckBox) findViewById(R.id.checkboxThree);
        skOne = (SeekBar) findViewById(R.id.seekbarOne);
        skTwo = (SeekBar) findViewById(R.id.seekbarTwo);
        skThree = (SeekBar) findViewById(R.id.seekbarThree);

        skOne.setEnabled(false);
        skTwo.setEnabled(false);
        skThree.setEnabled(false);

        txtDiem.setText(soDiem + "");

        CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long millisUntilFinished) {
                int number = 5;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);

                //check win
                if(skOne.getProgress() >= skOne.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(Main_MiniGame1.this, "ONE WIN", Toast.LENGTH_SHORT).show();
                    // kiểm tra đặt cược
                    if(cbOne.isChecked()){
                        soDiem += 10;
                        Toast.makeText(Main_MiniGame1.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    }else {
                        soDiem -= 5;
                        Toast.makeText(Main_MiniGame1.this, "Bạn đoán sai rồi", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckbox();
                }
                if(skTwo.getProgress() >= skTwo.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(Main_MiniGame1.this, "TWO WIN", Toast.LENGTH_SHORT).show();
                    if(cbTwo.isChecked()){
                        soDiem += 10;
                        Toast.makeText(Main_MiniGame1.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    }else {
                        soDiem -= 5;
                        Toast.makeText(Main_MiniGame1.this, "Bạn đoán sai rồi", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckbox();
                }
                if(skThree.getProgress() >= skThree.getMax()){
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(Main_MiniGame1.this, "THREE WIN", Toast.LENGTH_SHORT).show();
                    if (cbThree.isChecked()){
                        soDiem += 10;
                        Toast.makeText(Main_MiniGame1.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    }else {
                        soDiem -= 5;
                        Toast.makeText(Main_MiniGame1.this, "Bạn đoán sai rồi", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                    EnableCheckbox();
                }

                skOne.setProgress(skOne.getProgress() + one);
                skTwo.setProgress(skTwo.getProgress() + two);
                skThree.setProgress(skThree.getProgress() + three);
            }

            @Override
            public void onFinish() {

            }
        };

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()){
                    skOne.setProgress(0);
                    skTwo.setProgress(0);
                    skThree.setProgress(0);

                    ibtnPlay.setVisibility(View.INVISIBLE);
                    countDownTimer.start();

                    AbledCheckBox();
                }
                else {
                    Toast.makeText(Main_MiniGame1.this, "Vui lòng đặt cược trước khi chơi", Toast.LENGTH_SHORT).show();
                }

            }
        });

        cbOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //bỏ check 2,3
                    cbTwo.setChecked(false);
                    cbThree.setChecked(false);
                }
            }
        });
        cbTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cbOne.setChecked(false);
                cbThree.setChecked(false);
            }
        });
        cbThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cbOne.setChecked(false);
                cbTwo.setChecked(false);
            }
        });


    }

    private void EnableCheckbox(){
        cbOne.setEnabled(true);
        cbTwo.setEnabled(true);
        cbThree.setEnabled(true);
    }
    private void AbledCheckBox(){
        cbOne.setEnabled(false);
        cbTwo.setEnabled(false);
        cbThree.setEnabled(false);
    }

}