package com.example.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    private SeekBar seekBarSound;
    private SeekBar seekBarBrightness;

    private RadioGroup  radioGroupDiffLevel;
    private RadioButton radioButtonEasy;
    private RadioButton radioButtonMedium;
    private RadioButton radioButtonHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.seekBarSound = (SeekBar) findViewById(R.id.seekBar);
        this.seekBarBrightness = (SeekBar) findViewById(R.id.seekBar2);

        this.radioGroupDiffLevel = (RadioGroup) findViewById(R.id.radioGroupDiffLevel);
        this.radioButtonEasy = (RadioButton) findViewById(R.id.radioButtonEasy);
        this.radioButtonMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        this.radioButtonHard = (RadioButton) findViewById(R.id.radioButtonHard);

        this.loadGameSetting();

    }

    private void loadGameSetting(){

        SharedPreferences sharedPreferences = this.getSharedPreferences("gameSetting", Context.MODE_PRIVATE);

        if(sharedPreferences != null) {
            int brightness = sharedPreferences.getInt("brightness",90);
            int sound = sharedPreferences.getInt("sound",95);
            int checkedRadioGroup = sharedPreferences.getInt("checkedRadioButtonId",R.id.radioButtonMedium);

            this.seekBarSound.setProgress(sound);
            this.seekBarBrightness.setProgress(brightness);
            this.radioGroupDiffLevel.check(checkedRadioGroup);

        } else{

            this.radioGroupDiffLevel.check(R.id.radioButtonMedium);
            Toast.makeText(this,"Use the default Game Setting",Toast.LENGTH_SHORT).show();

        }

    }

    public void doSave(View view){

        SharedPreferences sharedPreferences = this.getSharedPreferences("gameSetting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("brightness",this.seekBarBrightness.getProgress());
        editor.putInt("sound",this.seekBarSound.getProgress());

        int checkedRadioButtonId = radioGroupDiffLevel.getCheckedRadioButtonId();

        editor.putInt("checkedRadioButtonId",checkedRadioButtonId);

        editor.apply();

        Toast.makeText(this,"Game Setting saved!",Toast.LENGTH_LONG).show();


    }

}
