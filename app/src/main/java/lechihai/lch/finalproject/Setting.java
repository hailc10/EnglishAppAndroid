package lechihai.lch.finalproject;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;



public class Setting extends AppCompatActivity {
    Button btxoaTest, btxoasTratu;
    TextView tvthongtin;
    DataBases db = new DataBases(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        ActionBar ab = getSupportActionBar();
        //set mầu cho actionBar
//        ab.setTitle("Setting");
        //Hiện nút back
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //
        btxoasTratu = (Button) findViewById(R.id.btxoatratu);
        btxoaTest = (Button) findViewById(R.id.btxoaTest);
        tvthongtin = (TextView) findViewById(R.id.tvThongtin);
        btxoasTratu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.ExecuteSQL("DELETE FROM LichSuTraTu");
                Toast.makeText(Setting.this, "Xóa Thành công", Toast.LENGTH_SHORT).show();
            }
        });
        btxoaTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.ExecuteSQL("DELETE FROM LichSuTest");
                Toast.makeText(Setting.this, "Xóa Thành công", Toast.LENGTH_SHORT).show();
            }
        });
        String s = "\n"+"\nPhiên Bản: 1.0"+"\n"+"";
        tvthongtin.setText(s);
    }
}
