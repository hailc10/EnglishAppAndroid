package lechihai.lch.finalproject.appIntro;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

import lechihai.lch.finalproject.MainActivity;
import lechihai.lch.finalproject.R;

public class Main_appIntro extends AppIntro {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("Tra từ điển","Dễ dàng tra cứu từ ngữ",R.drawable.lookup, ContextCompat.getColor(getApplicationContext(),R.color.slide1)));
        addSlide(AppIntroFragment.newInstance("Luyện tập","Tập luyện với các bài kiểm tra",R.drawable.test, ContextCompat.getColor(getApplicationContext(),R.color.slide2)));
        addSlide(AppIntroFragment.newInstance("Ghi chú","Ghi chú thêm những thứ cần nhớ",R.drawable.notee,ContextCompat.getColor(getApplicationContext(),R.color.slide3)));
        addSlide(AppIntroFragment.newInstance("Ngữ pháp","Ôn lại các ngữ pháp thường gặp",R.drawable.theory, ContextCompat.getColor(getApplicationContext(),R.color.slide4)));
        addSlide(AppIntroFragment.newInstance("Lịch sử","Xem lại lịch sử làm bài",R.drawable.history, ContextCompat.getColor(getApplicationContext(),R.color.slide5)));
        addSlide(AppIntroFragment.newInstance("Cuộc đua kỳ thú","Đoán xem ai là nhà vô địch!",R.drawable.pikachu, ContextCompat.getColor(getApplicationContext(),R.color.slide6)));
        addSlide(AppIntroFragment.newInstance("Nhanh tay lẹ mắt","Đố bạn chọn đúng đấy ^^",R.drawable.angrybird, ContextCompat.getColor(getApplicationContext(),R.color.slide7)));
        addSlide(AppIntroFragment.newInstance("Âm nhạc","Giải trí với các bài nhạc trẻ năng động",R.drawable.music, ContextCompat.getColor(getApplicationContext(),R.color.slide8)));
        addSlide(AppIntroFragment.newInstance("Bắt đầu thôi nào!!!","",R.drawable.icon_app, ContextCompat.getColor(getApplicationContext(), R.color.slide9)));

        showSkipButton(true);
        setFadeAnimation();


    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(Main_appIntro.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(Main_appIntro.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
