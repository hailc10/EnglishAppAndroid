package lechihai.lch.finalproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import lechihai.lch.finalproject.MiniGame1.Main_MiniGame1;
import lechihai.lch.finalproject.MiniGame2.Main_MiniGame2;
import lechihai.lch.finalproject.NguPhap.Main_NguPhap;
import lechihai.lch.finalproject.Note.Main_Note;
import lechihai.lch.finalproject.Question.main_question;
import lechihai.lch.finalproject.Song.Main_Song;
import lechihai.lch.finalproject.TraTu.Search_tran;
import lechihai.lch.finalproject.lichSuTest.lichSu_Test;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    DataBases db = new DataBases(this);
    ArrayList<String> ar = null;
    String[] item = {"Tra Từ Điển", "Luyện Tập", "Ghi Chú", "Ngữ Pháp", "Lịch sử Luyện Tập", "Cài Đặt","Game cuộc đua kỳ thú","Game nhanh tay lẹ mắt","Âm nhạc"};
    Integer[] icon = {R.drawable.lookup, R.drawable.test, R.drawable.notee, R.drawable.theory, R.drawable.history, R.drawable.setting,R.drawable.pikachu,R.drawable.angrybird,R.drawable.music};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            db.CopyDB2SDCard();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ar = new ArrayList<String>();
        for (String a : item) {
            ar.add(a);
        }
        ActionBar ab = getSupportActionBar();
        //set mầu cho actionBar
//       ab.setTitle("EngLish Learning Support");
        Custom_listView adapter = new Custom_listView(this, ar, icon);
        lv = (ListView) findViewById(R.id.Listitem);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stubin
                if (position == 0) {
                    Intent in = new Intent(getApplication(), Search_tran.class);
                    startActivity(in);
                    overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                }
                if (position == 1) {
                    Intent in = new Intent(getApplication(), main_question.class);
                    startActivity(in);
                    overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                }
                if (position == 2) {
                    Intent in = new Intent(getApplication(), Main_Note.class);
                    startActivity(in);
                    overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                }
                if (position == 3) {
                    Intent in = new Intent(getApplication(), Main_NguPhap.class);
                    startActivity(in);
                    overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                }
                if (position == 4) {
                    Intent in = new Intent(getApplication(), lichSu_Test.class);
                    startActivity(in);
                    overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                }
                if (position == 5) {
                    Intent in = new Intent(getApplication(), Setting.class);
                    startActivity(in);
                    overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                }
                if (position == 6) {
                    Intent in = new Intent(getApplication(), Main_MiniGame1.class);
                    startActivity(in);
                    overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                }
                if (position == 7) {
                    Intent in = new Intent(getApplication(), Main_MiniGame2.class);
                    startActivity(in);
                    overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                }
                if (position == 8) {
                    Intent in = new Intent(getApplication(), Main_Song.class);
                    startActivity(in);
                    overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
                }
            }
        });
    }
}