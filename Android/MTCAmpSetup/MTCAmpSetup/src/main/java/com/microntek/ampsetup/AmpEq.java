package com.microntek.ampsetup;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.microntek.MTCData;
import android.os.Handler;
import android.os.SystemProperties;
import android.provider.Settings.System;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

class AmpEq implements OnClickListener {

    MainActivity mainActivity;

    ImageView tvAmpBalanceButton;
    ImageView tvAmpSoundButton;

    VerticalSeekBar vsbInputG, vsbBassG, vsbMiddleG, vsbTrebleG, vsbLoudnessG, vsbSubG;
    TextView tvInputG, tvBassG, tvMiddleG, tvTrebleG, tvLoudnessG, tvSubG;
    HorizontalSeekBar hsbBassF, hsbMiddleF, hsbTrebleF;
    HorizontalSeekBar hsbBassQ, hsbMiddleQ, hsbTrebleQ;
    HorizontalSeekBar hsbSubCutOff, hsbSubOut, hsbSubPhase;
    HorizontalSeekBar hsbLoudnessF, hsbLoudnessH;

    int avEquMode = 0;

    int[] avCustomEq = new int[] { 10, 10, 10};
    int avCustomSUB = 10;
    BD37xx BD37xx = new BD37xx();

    protected View viewAmpEqLayout;
    protected View viewAmpSoundLayout;
    protected View viewAmpLeftPanel;

    protected OnClickListener buttonsSoundEffectChangeClickListener = new AmpEqButtonsSoundEffectChangeOnClickListener(this);

    private BroadcastReceiver broadcastReceiver = new AmpEqBroadcastReceiver(this);

    private IAmpBalance ampEqBalance = new AmpEqBalance(this);

    private AmpSeekBar ampEqGainSeekBar = new AmpEqGainSeekBar(this);
    private AmpSeekBar ampEqFrequencySeekBar = new AmpEqFrequencySeekBar(this);
    private AmpSeekBar ampEqQFactorSeekBar = new AmpEqQFactorSeekBar(this);
    private AmpSeekBar ampEqSubSeekBar = new AmpEqSubSeekBar(this);
    private AmpSeekBar ampEqLoudnessSeekBar = new AmpEqLoudnessSeekBar(this);

    protected int avBalance1;
    protected int avBalance2;


    protected Handler ampEqHandler = new AmpEqHandler(this);
    protected AmpBalance ampBalance;
    protected int f20u = 0;
    protected TextView tvAmpSoundMode;
    private CheckBox cbLoudnessCheck;

    private boolean f24y = false;


    public AmpEq(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    protected void getBalance()
    {
        if (this.ampBalance != null) 
        {
            String x = getSystemString("KeyBalance");
            if (x != null) 
            {
                String[] split = x.split(",");
                try 
                {
                    this.avBalance1 = Integer.parseInt(split[0]);
                    this.avBalance2 = Integer.parseInt(split[1]);
                } 
                catch (NumberFormatException e) 
                {
                    this.avBalance1 = 14;
                    this.avBalance2 = 14;
                }
            }
            else
            {
                this.avBalance1 = 14;
                this.avBalance2 = 14;
            }
            if (this.avBalance1 > 28)
            {
                this.avBalance1 = 28;
            }
            if (this.avBalance2 > 28)
            {
                this.avBalance2 = 28;
            }
            this.ampBalance.gn(this.avBalance1, this.avBalance2, 28);
        }
    }

    protected void updateViewForBalance()
    {
        boolean z = true;
        if (this.f20u == 0)
        {
            this.mainActivity.findViewById(R.id.amp_banlance1).setSelected(true);
            this.mainActivity.findViewById(R.id.amp_banlance2).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance3).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance4).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance5).setSelected(false);
        }
        else if (this.f20u == 1)
        {
            this.mainActivity.findViewById(R.id.amp_banlance1).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance2).setSelected(!this.f24y);
            View r0 = this.mainActivity.findViewById(R.id.amp_banlance3);
            if (!this.f24y) {
                z = false;
            }
            r0.setSelected(z);
            this.mainActivity.findViewById(R.id.amp_banlance4).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance5).setSelected(false);
        }
        else if (this.f20u == 2)
        {
            this.mainActivity.findViewById(R.id.amp_banlance1).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance2).setSelected(this.f24y);
            View r0 = this.mainActivity.findViewById(R.id.amp_banlance3);
            if (this.f24y) {
                z = false;
            }
            r0.setSelected(z);
            this.mainActivity.findViewById(R.id.amp_banlance4).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance5).setSelected(false);
        }
        else if (this.f20u == 3)
        {
            this.mainActivity.findViewById(R.id.amp_banlance1).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance2).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance3).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance4).setSelected(true);
            this.mainActivity.findViewById(R.id.amp_banlance5).setSelected(false);
        }
        else if (this.f20u == 4)
        {
            this.mainActivity.findViewById(R.id.amp_banlance1).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance2).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance3).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance4).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance5).setSelected(true);
        }
    }

    private void m14t()
    {
        this.tvInputG = (TextView) this.mainActivity.findViewById(R.id.inputG_value);
        this.tvBassG = (TextView) this.mainActivity.findViewById(R.id.bassG_value);
        this.tvMiddleG = (TextView) this.mainActivity.findViewById(R.id.middleG_value);
        this.tvTrebleG = (TextView) this.mainActivity.findViewById(R.id.trebleG_value);

        this.vsbInputG = (VerticalSeekBar) this.mainActivity.findViewById(R.id.seekbarInputG);
        this.vsbInputG.setOnChangeListener(this.ampEqGainSeekBar);
        this.vsbBassG = (VerticalSeekBar) this.mainActivity.findViewById(R.id.seekbarBassG);
        this.vsbBassG.setOnChangeListener(this.ampEqGainSeekBar);
        this.vsbMiddleG = (VerticalSeekBar) this.mainActivity.findViewById(R.id.seekbarMiddleG);
        this.vsbMiddleG.setOnChangeListener(this.ampEqGainSeekBar);
        this.vsbTrebleG = (VerticalSeekBar) this.mainActivity.findViewById(R.id.seekbarTrebleG);
        this.vsbTrebleG.setOnChangeListener(this.ampEqGainSeekBar);

        this.hsbBassF = (HorizontalSeekBar) this.mainActivity.findViewById(R.id.seekbarBassF);
        this.hsbBassF.setOnChangeListener(this.ampEqFrequencySeekBar);
        this.hsbMiddleF = (HorizontalSeekBar) this.mainActivity.findViewById(R.id.seekbarMiddleF);
        this.hsbMiddleF.setOnChangeListener(this.ampEqFrequencySeekBar);
        this.hsbTrebleF = (HorizontalSeekBar) this.mainActivity.findViewById(R.id.seekbarTrebleF);
        this.hsbTrebleF.setOnChangeListener(this.ampEqFrequencySeekBar);

        this.hsbBassQ = (HorizontalSeekBar) this.mainActivity.findViewById(R.id.seekbarBassQ);
        this.hsbBassQ.setOnChangeListener(this.ampEqQFactorSeekBar);
        this.hsbMiddleQ = (HorizontalSeekBar) this.mainActivity.findViewById(R.id.seekbarMiddleQ);
        this.hsbMiddleQ.setOnChangeListener(this.ampEqQFactorSeekBar);
        this.hsbTrebleQ = (HorizontalSeekBar) this.mainActivity.findViewById(R.id.seekbarTrebleQ);
        this.hsbTrebleQ.setOnChangeListener(this.ampEqQFactorSeekBar);

        this.tvSubG = (TextView) this.mainActivity.findViewById(R.id.subG_value);
        this.vsbSubG = (VerticalSeekBar) this.mainActivity.findViewById(R.id.seekbarSubG);
        this.vsbSubG.setOnChangeListener(this.ampEqSubSeekBar);
        this.hsbSubCutOff = (HorizontalSeekBar) this.mainActivity.findViewById(R.id.seekbarSubCutOff);
        this.hsbSubCutOff.setOnChangeListener(this.ampEqSubSeekBar);
        this.hsbSubOut = (HorizontalSeekBar) this.mainActivity.findViewById(R.id.seekbarSubOut);
        this.hsbSubOut.setOnChangeListener(this.ampEqSubSeekBar);
        this.hsbSubPhase = (HorizontalSeekBar) this.mainActivity.findViewById(R.id.seekBarSubPhase);
        this.hsbSubPhase.setOnChangeListener(this.ampEqSubSeekBar);

        this.tvLoudnessG = (TextView) this.mainActivity.findViewById(R.id.loudnessG_value);
        this.vsbLoudnessG = (VerticalSeekBar) this.mainActivity.findViewById(R.id.seekbarLoudnessG);
        this.vsbLoudnessG.setOnChangeListener(this.ampEqLoudnessSeekBar);
        this.hsbLoudnessF = (HorizontalSeekBar) this.mainActivity.findViewById(R.id.seekbarLoudnessF);
        this.hsbLoudnessF.setOnChangeListener(this.ampEqLoudnessSeekBar);
        this.hsbLoudnessH = (HorizontalSeekBar) this.mainActivity.findViewById(R.id.seekbarLoudnessH);
        this.hsbLoudnessH.setOnChangeListener(this.ampEqLoudnessSeekBar);

        Button buttonLeft = (Button) this.mainActivity.findViewById(R.id.soundeffect_arrowhead_left);
        Button buttonRight = (Button) this.mainActivity.findViewById(R.id.soundeffect_arrowhead_right);
        buttonLeft.setOnClickListener(this.buttonsSoundEffectChangeClickListener);
        buttonRight.setOnClickListener(this.buttonsSoundEffectChangeClickListener);

        this.tvAmpSoundMode = (TextView) this.mainActivity.findViewById(R.id.amp_soundmode);
        this.cbLoudnessCheck = (CheckBox) this.mainActivity.findViewById(R.id.loudness_check);
        this.cbLoudnessCheck.setOnCheckedChangeListener(new AmpEqLoudnessOnCheckedChangeListener(this));

        Button buttonReset = (Button) this.mainActivity.findViewById(R.id.bttn_reset);
        buttonReset.setOnClickListener(this);

        updateSeekBars();
        updateLoudnessCheck();
    }


    private void m15u()
    {
        this.ampBalance = (AmpBalance) this.mainActivity.findViewById(R.id.balanceCross);
        getBalance();
        this.ampBalance.go(this.ampEqBalance);
        String x = getSystemString("KeyBalanceMode");
        int i = 4;
        if (x != null)
        {
            i = Integer.parseInt(x);
        }
        this.f20u = i;
        updateViewForBalance();
    }

    protected void changeView()
    {
        Animation loadAnimation;
        Animation loadAnimation2;
        if (this.viewAmpEqLayout.getVisibility() != View.VISIBLE)
        {
            this.tvAmpBalanceButton.setSelected(true);
            this.tvAmpSoundButton.setSelected(false);
            //loadAnimation = AnimationUtils.loadAnimation(this.mainActivity, R.anim.left_in);
            //loadAnimation2 = AnimationUtils.loadAnimation(this.mainActivity, R.anim.right_out);
            loadAnimation = AnimationUtils.loadAnimation(this.mainActivity, R.anim.fade_in);
            loadAnimation2 = AnimationUtils.loadAnimation(this.mainActivity, R.anim.fade_out);
            this.viewAmpEqLayout.setVisibility(View.VISIBLE);
            this.viewAmpSoundLayout.setVisibility(View.GONE);
            this.viewAmpEqLayout.startAnimation(loadAnimation);
            this.viewAmpSoundLayout.startAnimation(loadAnimation2);
        }
        else if (this.viewAmpSoundLayout.getVisibility() != View.VISIBLE)
        {
            this.tvAmpBalanceButton.setSelected(false);
            this.tvAmpSoundButton.setSelected(true);
            //loadAnimation = AnimationUtils.loadAnimation(this.mainActivity, R.anim.left_out);
            //loadAnimation2 = AnimationUtils.loadAnimation(this.mainActivity, R.anim.right_in);
            loadAnimation = AnimationUtils.loadAnimation(this.mainActivity, R.anim.fade_out);
            loadAnimation2 = AnimationUtils.loadAnimation(this.mainActivity, R.anim.fade_in);
            this.viewAmpEqLayout.setVisibility(View.GONE);
            this.viewAmpSoundLayout.setVisibility(View.VISIBLE);
            this.viewAmpEqLayout.startAnimation(loadAnimation);
            this.viewAmpSoundLayout.startAnimation(loadAnimation2);
        }
    }


    protected int getSystemInt(String str, int value)
    {
        try {
            value = System.getInt(this.mainActivity.getContentResolver(), str, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    private String getSystemString(String str)
    {
        try {
            return System.getString(this.mainActivity.getContentResolver(), str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void getSystemKeyCustomEq()
    {
        String x = getSystemString("KeyCustomEQ");
        if (x == null || x.length() <= 0) {
            for (int i = 0; i < this.avCustomEq.length; i++) {
                this.avCustomEq[i] = 10;
            }
        }
        else {
            String[] split = x.split(",");
            for (int i = 0; i < split.length && i < avCustomEq.length; i++) {
                try {
                    this.avCustomEq[i] = Integer.parseInt(split[i]);
                } catch (NumberFormatException e) {
                    this.avCustomEq[i] = 10;
                }
            }
        }
    }
    private void getSystemKeyCustomEqMod()
    {
        String x = getSystemString("KeyCustomEQMod");
        if (x != null && x.length() > 0) {
            String[] split = x.split(",");
            for (int i = 0; i < split.length && i < BD37xx.Registers.length; i++) {
                try {
                    BD37xx.Registers[i] = Integer.parseInt(split[i]);
                } catch (NumberFormatException e) {
                }
            }
        }
    }

    protected void putSystemInt(String str, int i)
    {
        try {
            System.putInt(this.mainActivity.getContentResolver(), str, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void putSystemString(String str, String str2)
    {
        try {
            System.putString(this.mainActivity.getContentResolver(), str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void putSystemKeyCustomEq()
    {
        putSystemString("KeyCustomEQ", this.avCustomEq[0] + "," + this.avCustomEq[1] + "," + this.avCustomEq[2]);
    }
    protected void putSystemKeyCustomEqMod()
    {
        String x = "";
        for (int i=0; i<BD37xx.Registers.length; i++)
            x = x + BD37xx.Registers[i] + ",";
        x = x.substring(0, x.length() - 1);
        putSystemString("KeyCustomEQMod", x);
    }

    protected void putAvDspParameter()
    {
        String parameters = "";
        for (int i = 0; i < 15; i++) {
            parameters = parameters + this.BD37xx.Registers[this.BD37xx.IndexesToAvDsp[i]] + ",";
        }
        parameters = parameters.substring(0, parameters.length() - 1);
        this.mainActivity.SetParameters("av_dsp=0," + parameters);
    }

    public int getContentId()
    {
        return R.layout.hct_ampfield;
    }

    public void onClick(View view)
    {
        int i = 14;
        switch (view.getId())
        {
            case R.id.bttn_reset:
                this.putSystemInt("KeyCTmode", 0);
                this.putSystemInt("KeyEQmode", 0);
                this.putSystemString("KeyCustomEQ", "");
                this.putSystemString("KeyCustom1EQ", "");
                this.putSystemString("KeyCustomEQMod", "");
                this.mainActivity.SetParameters("av_dsp=0,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10");
                return;
            case R.id.btn_amp_left:
                this.f20u = 0;
                this.ampBalance.left();
                return;
            case R.id.btn_amp_right:
                this.f20u = 0;
                this.ampBalance.right();
                return;
            case R.id.btn_amp_front:
                this.f20u = 0;
                this.ampBalance.front();
                return;
            case R.id.btn_amp_back:
                this.f20u = 0;
                this.ampBalance.back();
                return;
            case R.id.amp_banlance2:
                if (this.f24y) {
                    this.f20u = 2;
                    this.ampBalance.setValue(28, 0);
                    return;
                }
                this.f20u = 1;
                this.ampBalance.setValue(0, 0);
                return;
            case R.id.amp_banlance3:
                if (this.f24y) {
                    this.f20u = 1;
                    this.ampBalance.setValue(i, 0);
                    return;
                }
                this.f20u = 2;
                this.ampBalance.setValue(i, 0);
                return;
            case R.id.amp_banlance4:
                this.f20u = 3;
                this.ampBalance.setValue(i, 28);
                return;
            case R.id.amp_banlance5:
                this.f20u = 4;
                this.ampBalance.setValue(i, i);
                return;
            case R.id.amp_banlance1:
                int parseInt;
                this.f20u = 0;
                String x = getSystemString("KeyBalanceMode1");
                if (x != null)
                {
                    String[] split = x.split(",");
                    try
                    {
                        parseInt = Integer.parseInt(split[0]);
                        try
                        {
                            i = Integer.parseInt(split[1]);
                        }
                        catch (NumberFormatException e) {}
                    }
                    catch (NumberFormatException e2) 
                    {
                        parseInt = i;
                    }
                } else {
                    parseInt = i;
                }
                this.ampBalance.setValue(parseInt, i);
                return;
            default:
                return;
        }
    }

    protected void m22p()
    {
        this.viewAmpEqLayout = this.mainActivity.findViewById(R.id.amp_eq_layout);
        this.viewAmpSoundLayout = this.mainActivity.findViewById(R.id.amp_sound_layout);
        this.viewAmpLeftPanel = this.mainActivity.findViewById(R.id.amp_eq_left_panel);
        this.mainActivity.findViewById(R.id.btn_amp_front).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.btn_amp_back).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.btn_amp_left).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.btn_amp_right).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.amp_banlance1).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.amp_banlance2).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.amp_banlance3).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.amp_banlance4).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.amp_banlance5).setOnClickListener(this);
        if (this.mainActivity.getParameters("cfg_cansub_0=").equals("1"))
        {
            this.f24y = true;
        }
        m15u();
        m14t();
        this.tvAmpBalanceButton = (ImageView) this.mainActivity.findViewById(R.id.amp_blance_button);
        this.tvAmpSoundButton = (ImageView) this.mainActivity.findViewById(R.id.amp_sound_button);
        this.tvAmpBalanceButton.setOnClickListener(new AmpEqBalanceButtonOnClickListener(this));
        this.tvAmpSoundButton.setOnClickListener(new AmpEqSoundButtonOnClickListener(this));
        changeView();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.microntek.eqchange");
        intentFilter.addAction("com.microntek.balancechange");
        intentFilter.addAction("com.microntek.loundchange");
        intentFilter.addAction("com.microntek.ampclose");
        this.mainActivity.registerReceiver(this.broadcastReceiver, intentFilter);

        this.putAvDspParameter();
    }

    protected String formatGain(int gain) {
        if (gain == 0) return "0 dB";
        return String.format("%+d dB", gain);
    }

    public void updateSeekBars() {
        int i;
        if (this.tvAmpSoundMode != null) {
            if (SystemProperties.get("ro.product.customer.sub").equals("KGLMMR")) {
                this.avEquMode = getSystemInt("KeyEQmode", 1);
            } else {
                this.avEquMode = getSystemInt("KeyEQmode", 0);
            }

            getSystemKeyCustomEqMod();

            if (this.avEquMode > 6 || this.avEquMode < 0) {
                this.avEquMode = 0;
            }
            if (this.avEquMode == 0) {
                getSystemKeyCustomEq();

                vsbBassG.setValue(this.avCustomEq[0]); tvBassG.setText(formatGain(this.avCustomEq[0] - 20));
                vsbMiddleG.setValue(this.avCustomEq[1]); tvMiddleG.setText(formatGain(this.avCustomEq[1] - 20));
                vsbTrebleG.setValue(this.avCustomEq[2]); tvTrebleG.setText(formatGain(this.avCustomEq[2] - 20));
            } else {
                this.avCustomEq[0] = (MTCData.music_stytle_data9[this.avEquMode - 1][0]*2 + MTCData.music_stytle_data9[this.avEquMode - 1][1]*2 + MTCData.music_stytle_data9[this.avEquMode - 1][2]*2) / 3;
                vsbBassG.setValue(this.avCustomEq[0]); tvBassG.setText(formatGain(this.avCustomEq[0] - 20));
                this.avCustomEq[1] = (MTCData.music_stytle_data9[this.avEquMode - 1][3]*2 + MTCData.music_stytle_data9[this.avEquMode - 1][4]*2 + MTCData.music_stytle_data9[this.avEquMode - 1][5]*2) / 3;
                vsbMiddleG.setValue(this.avCustomEq[1]); tvMiddleG.setText(formatGain(this.avCustomEq[1] - 20));
                this.avCustomEq[2] = (MTCData.music_stytle_data9[this.avEquMode - 1][6]*2 + MTCData.music_stytle_data9[this.avEquMode - 1][7]*2 + MTCData.music_stytle_data9[this.avEquMode - 1][8]*2) / 3;
                vsbTrebleG.setValue(this.avCustomEq[2]); tvTrebleG.setText(formatGain(this.avCustomEq[2] - 20));
                this.mainActivity.SetParameters("av_eq=" + this.avCustomEq[0] + "," + this.avCustomEq[1] + "," + this.avCustomEq[2]);
            }

            vsbInputG.setValue(BD37xx.getInputG()); tvInputG.setText(formatGain(BD37xx.getInputG()));
            hsbBassF.setValue(BD37xx.getBassF()); hsbMiddleF.setValue(BD37xx.getMiddleF()); hsbTrebleF.setValue(BD37xx.getTrebleF());
            hsbBassQ.setValue(BD37xx.getBassQ()); hsbMiddleQ.setValue(BD37xx.getMiddleQ()); hsbTrebleQ.setValue(BD37xx.getTrebleQ());
            vsbLoudnessG.setValue(BD37xx.getLoudnessG()); tvLoudnessG.setText(formatGain(BD37xx.getLoudnessG()));
            hsbLoudnessF.setValue(BD37xx.getLoudnessF());
            hsbLoudnessH.setValue(BD37xx.getLoudnessH());

            this.avCustomSUB = getSystemInt("KeyCustomSUB", 10);
            this.tvSubG.setText(formatGain(this.avCustomSUB));
            this.vsbSubG.setValue(this.avCustomSUB + 79);
            this.hsbSubCutOff.setValue(BD37xx.getSubCutOff());
            this.hsbSubOut.setValue(BD37xx.getSubOut());
            this.hsbSubPhase.setValue(BD37xx.getSubPhase());

            this.tvAmpSoundMode.setText(this.mainActivity.getResources().getString(this.avEquMode + R.string.music_style0));
        }
    }


    public void updateLoudnessCheck()
    {
        if (this.cbLoudnessCheck != null)
        {
            if ((SystemProperties.get("ro.product.customer.sub").equals("KGLMMR") ? getSystemInt("key_Lud", 1) : getSystemInt("key_Lud", 0)) == 1) {
                this.cbLoudnessCheck.setChecked(true);
            } else {
                this.cbLoudnessCheck.setChecked(false);
            }
        }
    }

    protected void unregisterReceiver()
    {
        this.mainActivity.unregisterReceiver(this.broadcastReceiver);
    }
}
