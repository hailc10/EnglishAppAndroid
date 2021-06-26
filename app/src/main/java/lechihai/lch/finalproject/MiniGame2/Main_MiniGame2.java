package lechihai.lch.finalproject.MiniGame2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import lechihai.lch.finalproject.MainActivity;
import lechihai.lch.finalproject.R;

public class Main_MiniGame2 extends AppCompatActivity {

    public static ArrayList<String> arrayName;
    ImageView imgGoc, imgNhan;
    TextView txtDiem;
    Button btnThoat;
    int REQUEST_CODE_IMAGE = 123;
    String tenHinhGoc = "";
    int total = 100;

    SharedPreferences luuDiemSo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame2_main);

        imgGoc = (ImageView) findViewById(R.id.imageViewGoc);
        imgNhan = (ImageView) findViewById(R.id.imageViewNhan);
        txtDiem = (TextView) findViewById(R.id.textViewDiem);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("Game nhanh tay lẹ mắt");
        ab.setDisplayHomeAsUpEnabled(true);



        // khởi tạo
        luuDiemSo = getSharedPreferences("DiemSoGame", MODE_PRIVATE);

        //get điểm số
        total = luuDiemSo.getInt("diem",100);

        txtDiem.setText(total + "");

        String[] mangTen = getResources().getStringArray(R.array.list_name);
        arrayName = new ArrayList<>(Arrays.asList(mangTen));

        //trộn mảng
        Collections.shuffle(arrayName);
        tenHinhGoc = arrayName.get(5);
        int idHinh = getResources().getIdentifier(arrayName.get(5), "drawable" , getPackageName());
        imgGoc.setImageResource(idHinh);

        imgNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Main_MiniGame2.this,imageActivity_MiniGame2.class), REQUEST_CODE_IMAGE);
            }
        });


    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null){
            String tenHinhNhan = data.getStringExtra("tenhinhchon");
            int idHinhNhan = getResources().getIdentifier(tenHinhNhan,"drawable", getPackageName());
            imgNhan.setImageResource(idHinhNhan);
            //so sánh theo tên hình
            if (tenHinhGoc.equals(tenHinhNhan)){
                Toast.makeText(this, "Chính xác! Bạn được cộng 10 điểm", Toast.LENGTH_SHORT).show();
                // cộng điểm
                total += 10;
                LuuDiem();

                // đổi hình gốc
                new CountDownTimer(2000, 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        //trộn mảng
                        Collections.shuffle(arrayName);
                        tenHinhGoc = arrayName.get(5);
                        int idHinh = getResources().getIdentifier(arrayName.get(5), "drawable" , getPackageName());
                        imgGoc.setImageResource(idHinh);
                    }
                }.start();
            }else {
                Toast.makeText(this, "Sai rồi! \n Bạn bị trừ 5 điểm", Toast.LENGTH_SHORT).show();
                total -= 5;
                LuuDiem();
                if (total <= 0){
//                    Toast.makeText(this, "Chơi lại thôi!!!", Toast.LENGTH_SHORT).show();
//                    total = 100;
//                    LuuDiem();
                    onBackPressed();
                }
            }


            txtDiem.setText(total + "");
        }

        //kiểm tra màn hình thứ 2 không chọn hình
        if(requestCode == REQUEST_CODE_IMAGE  && resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Bạn chưa chọn hình, muốn xem lại à? \n Bị trừ 15 điểm ^^", Toast.LENGTH_SHORT).show();
            total -= 15;
            LuuDiem();
            if (total <= 0){
//                Toast.makeText(this, "Chơi lại thôi!!!", Toast.LENGTH_SHORT).show();
//                total = 100;
//                LuuDiem();
                onBackPressed();
            }
            txtDiem.setText(total + "");
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reload, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuReload){
            //trộn mảng
            Collections.shuffle(arrayName);
            tenHinhGoc = arrayName.get(5);
            int idHinh = getResources().getIdentifier(arrayName.get(5), "drawable" , getPackageName());
            imgGoc.setImageResource(idHinh);
        }
        return super.onOptionsItemSelected(item);
    }

    private void LuuDiem(){
        SharedPreferences.Editor editor = luuDiemSo.edit();
        editor.putInt("diem",total);
        editor.commit();
    }

    //xuất thông báo hỏi chơi tiếp không?
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Game over")
                .setMessage("Do you want to continue?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        total = 100;
                        txtDiem.setText(total + "");
                        LuuDiem();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }


}