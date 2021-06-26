package lechihai.lch.finalproject.MiniGame2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Collections;

import lechihai.lch.finalproject.R;

public class imageActivity_MiniGame2 extends Activity {

    TableLayout myTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame2_imageactivity);

        myTable = (TableLayout) findViewById(R.id.tableLayoutImage);

        int soDong = 6;
        int soCot = 3;

        //trộn mảng
        Collections.shuffle(Main_MiniGame2.arrayName);

        // tạo dòng và cột
        for(int i=1; i<=soDong; i++){
            TableRow tableRow = new TableRow(this);
            // tạo cột image view
            for (int j=1 ; j<=soCot; j++){
                ImageView imageView = new ImageView(this);

                // Converts 14 dip into its equivalent px
                Resources r = getResources();
                int px = (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 100,
                        r.getDisplayMetrics()
                );
                TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(px,px);
                imageView.setLayoutParams(layoutParams);

                final int vitri = soCot * (i-1) + j - 1;

                int idHinh = getResources().getIdentifier(Main_MiniGame2.arrayName.get(vitri),"drawable",getPackageName());
                imageView.setImageResource(idHinh);
                // add image view vào table row
                tableRow.addView(imageView);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("tenhinhchon",Main_MiniGame2.arrayName.get(vitri));
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }
            // add table row vào table
            myTable.addView(tableRow);
        }
    }
}