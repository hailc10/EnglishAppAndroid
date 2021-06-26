package lechihai.lch.finalproject.Song;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import lechihai.lch.finalproject.R;

public class Main_Song extends AppCompatActivity {
    TextView txtTitle, txtTimeSong, txtTimeTotal;
    SeekBar skSong;
    ImageView imgHinh;
    ImageButton btnPrev, btnPlay, btnStop, btnNext;

    ArrayList<Song> arraySong;
    int position = 0;
    MediaPlayer mediaPlayer;
    Animation animation;

    SharedPreferences luuTrangThai;
    int mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_main);

        txtTimeSong = (TextView) findViewById(R.id.textviewTimeSong);
        txtTimeTotal = (TextView) findViewById(R.id.textviewTimeTotal);
        txtTitle = (TextView) findViewById(R.id.textviewTitle);
        skSong = (SeekBar) findViewById(R.id.seekbarSong);
        btnNext = (ImageButton) findViewById(R.id.imagebuttonNext);
        btnPlay = (ImageButton) findViewById(R.id.imageButtonPlay);
        btnPrev = (ImageButton) findViewById(R.id.imageButtonPrev);
        btnStop = (ImageButton) findViewById(R.id.imagebuttonStop);
        imgHinh = (ImageView) findViewById(R.id.imageViewDisc);

        AddSong();


        ActionBar ab = getSupportActionBar();
        ab.setTitle("Nghe nhạc");

        animation = AnimationUtils.loadAnimation(this, R.anim.disc_rotate);
        KhoiTaoMediaPlayer();

        //khởi tạo

        luuTrangThai = PreferenceManager.getDefaultSharedPreferences(skSong.getContext());
        mProgress = luuTrangThai.getInt("mMySeekBarProgress", 0);
        mProgress = skSong.getProgress();



        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0) {
                    position = arraySong.size() - 1;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause1);
                SetTimeTotal();
                UpdateTimeSong();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position > arraySong.size() - 1) {
                    position = 0;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause1);
                SetTimeTotal();
                UpdateTimeSong();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    // nếu đang phát thì pause ->đổi hình play
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play1);
                    imgHinh.clearAnimation();
                } else {
                    // đang ngừng -> phát -> đổi hình pause
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause1);
                    imgHinh.startAnimation(animation);
                }
                SetTimeTotal();
                UpdateTimeSong();

            }

        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.play1);
                KhoiTaoMediaPlayer();
            }
        });

        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(skSong.getProgress());
            }
        });
    }



    private void UpdateTimeSong(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                // update progress skSong
                skSong.setProgress(mediaPlayer.getCurrentPosition());

                //kiểm tra thời gian bài hát -> nếu kết thúc ->next
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > arraySong.size() - 1){
                            position = 0;
                        }
                        if(mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        KhoiTaoMediaPlayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.pause1);
                        SetTimeTotal();
                        UpdateTimeSong();
                    }
                });
                handler.postDelayed(this,500);
            }
        }, 100);
    }


    private void AddSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Muộn rồi mà sao còn",R.raw.muon_roi_ma_sao_con));
        arraySong.add(new Song("Điều anh biết",R.raw.dieu_anh_biet));
        arraySong.add(new Song("Anh cứ đi đi",R.raw.anh_cu_di_di));
        arraySong.add(new Song("Cause i love you",R.raw.cause_i_love_you));
        arraySong.add(new Song("Em đã biết",R.raw.em_da_biet));
        arraySong.add(new Song("Đếm ngày xa em",R.raw.dem_ngay_xa_em));
        arraySong.add(new Song("Nếu em còn tồn tại",R.raw.neu_em_con_ton_tai));
        arraySong.add(new Song("Chúng ta không thuộc về nhau",R.raw.chung_ta_khong_thuoc_ve_nhau));
        arraySong.add(new Song("Như phút ban đầu",R.raw.nhu_phut_ban_dau));
        arraySong.add(new Song("Sau tất cả",R.raw.sau_tat_ca));
        arraySong.add(new Song("Say You Do",R.raw.say_you_do));
        arraySong.add(new Song("Tâm sự với người lạ",R.raw.tam_su_voi_nguoi_la));
        arraySong.add(new Song("Mình là gì của nhau",R.raw.minh_la_gi_cua_nhau));
    }

    private void SetTimeTotal(){
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(dinhDangGio.format(mediaPlayer.getDuration()));
        // gán  max của skSong = mediaPlayer.getDuration()
        skSong.setMax(mediaPlayer.getDuration());
    }

    private void KhoiTaoMediaPlayer(){
        mediaPlayer = MediaPlayer.create(Main_Song.this, arraySong.get(position).getFile());
        txtTitle.setText(arraySong.get(position).getTitle());
    }

    private void SaveStatus(){
        SharedPreferences.Editor editor = luuTrangThai.edit();
        skSong.setProgress(mProgress);
        editor.putInt("mMySeekBarProgress", mProgress).commit();
    }

}
