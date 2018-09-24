package com.microntek.ampsetup;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.microntek.MTCData;
import android.os.Handler;
import android.provider.Settings.System;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* renamed from: com.microntek.ampsetup.c */
class AmpDsp implements OnClickListener {
    private BroadcastReceiver ai = new AmpDspBroadcastReceiver(this);
    private IAmpBalance ampBalanceCross = new AmpDspBalanceCross(this);
    private IAmpBalance ampBalanceSCross = new AmpDspBalanceSCross(this);
    private AmpSeekBar verticalSeekBarEq = new AmpDspSeekBarEq(this);
    protected Animation animLeftIn;
    protected int an;
    protected int ao;
    protected Button bttnCommon;
    protected Button bttnSpecialty;
    private OnTouchListener checkBoxesEqTouchListener = new AmpDspCheckBoxesEqOnTouchListener(this);
    protected int as = 0;
    protected String at;
    protected String au;
    protected Button bttnDistanceSetting;
    protected CheckBox cbEf;
    protected int ax = 0;
    protected Button bttnEfCustom1;
    protected Button bttnEfCustom2;
    protected Button bttnEfCustom3;
    protected Button bttnEfReset;
    protected Button bttnEqCustom1;
    protected Button bttnEqCustom2;
    protected Button bttnEqCustom3;
    protected int bf = 0;
    protected Handler bg = new AmpDspHandler(this);
    boolean bh = false;
    boolean bi = false;
    protected int bj;
    protected MyButton myBttnLeftBackDown;
    protected TextView tvLeftBackInfo;
    protected MyButton myBttnLeftBackUp;
    protected int bn;
    protected MyButton myBttnLeftFrontDown;
    protected TextView tvLeftFrontInfo;
    protected MyButton myBttnLeftFrontUp;
    protected MainActivity mainActivity; // br
    private AmpBalance balanceCross; //bs
    protected int bt = 0;
    protected LinearLayout layoutSoundCommon;
    private int[] bv = new int[15];
    protected CheckBox cbCut;
    protected VerticalSeekBar vsbCut;
    protected Dialog dialogDspDistance;
    protected TextView bz;
    protected CheckBox cbEfSub;
    protected VerticalSeekBar vsbEfSub;
    protected ImageView bttnEffect;
    protected View viewDspEffectLayout;
    protected int ce;
    protected Handler cf = new AmpDspVolumeHandler(this);
    protected VerticalSeekBar vsbLudB;
    protected CheckBox cbLud;
    protected VerticalSeekBar vsbLudT;
    private boolean cj = false;
    private AmpBalance balanceSCross; //ck
    protected int cl = 0;
    private ScheduledExecutorService cm;
    protected ImageView bttnSound;
    protected ImageView bttnBalance;
    protected View viewDspEqLayout;
    protected View viewDspSoundLayout;
    protected VerticalSeekBar[] vsbSoundProgress = new VerticalSeekBar[15];
    protected TextView[] tvSoundProgress = new TextView[15];
    protected CheckBox cbSpa;
    protected VerticalSeekBar vsbSpa;
    protected FrameLayout layoutSoundSpecialty;
    protected CheckBox cbSub;
    protected VerticalSeekBar vsbSubL;
    protected VerticalSeekBar vsbSubR;
    protected int cz;
    private CheckBox cbDefer;
    private CheckBox cbVolume;
    private OnClickListener buttonsCommonSpecialtyClickListener = new AmpDspButtonsCommonSpecialtyOnClickListener(this);
    private OnClickListener buttonsEqChangeClickListener = new AmpDspButtonsEqChangeOnClickListener(this);
    private OnClickListener buttonsEqCustomClickListener = new AmpDspButtonsEqCustomOnClickListener(this);
    private OnTouchListener buttonsEqCustomTouchListener = new AmpDspButtonsEqCustomOnTouchListener(this);
    private OnClickListener buttonsEfCustomClickListener = new AmpDspButtonsEfCustomOnClickListener(this);
    private OnTouchListener buttonsEfCustomTouchListener = new AmpDspButtonsEfCustomOnTouchListener(this);
    private AmpSeekBar verticalSeekBarEf = new AmpDspSeekBarEf(this);
    private OnClickListener buttonsEfSpeakerClickListener = new AmpDspButtonsEfSpeakerOnClickListener(this);
    private OnTouchListener buttonsEfSpeakerTouchListener = new AmpDspButtonsEfSpeakerOnTouchListener(this);
    protected int dl;
    private MyButton myBttnRightBackDown;
    private TextView tvRightBackInfo;
    /* renamed from: do */
    private MyButton myBttnRightBackUp;
    protected int dp;
    private MyButton myBttnRightFrontDown;
    private TextView tvRightFrontInfo;
    private MyButton myBttnRightFrontUp;
    protected int dt;
    protected int du;
    private OnClickListener speakerButtonsClickListener = new AmpDspDialogDistanceButtonsSpeakerOnClickListener(this);
    private TextView dw;
    private TextView dx;
    float dy;
    float dz;

    public AmpDsp(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private void bo() {
        this.mainActivity.findViewById(R.id.btn_s_front).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.btn_s_back).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.btn_s_left).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.btn_s_right).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.s_banlance0).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.s_banlance1).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.s_banlance2).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.s_banlance3).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.s_banlance4).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.s_banlance5).setOnClickListener(this);
        this.balanceSCross = (AmpBalance) this.mainActivity.findViewById(R.id.s_balanceCross);
        this.cbDefer = (CheckBox) this.mainActivity.findViewById(R.id.ck_defer);
        this.bttnDistanceSetting = (Button) this.mainActivity.findViewById(R.id.distance_setting);
        this.bttnDistanceSetting.setOnClickListener(this);
        this.cbVolume = (CheckBox) this.mainActivity.findViewById(R.id.ck_volume);
        this.myBttnLeftFrontUp = (MyButton) this.mainActivity.findViewById(R.id.left_front_up);
        this.tvLeftFrontInfo = (TextView) this.mainActivity.findViewById(R.id.left_front_info);
        this.myBttnLeftFrontDown = (MyButton) this.mainActivity.findViewById(R.id.left_front_down);
        this.myBttnRightFrontUp = (MyButton) this.mainActivity.findViewById(R.id.right_front_up);
        this.tvRightFrontInfo = (TextView) this.mainActivity.findViewById(R.id.right_front_info);
        this.myBttnRightFrontDown = (MyButton) this.mainActivity.findViewById(R.id.right_front_down);
        this.myBttnLeftBackUp = (MyButton) this.mainActivity.findViewById(R.id.left_back_up);
        this.tvLeftBackInfo = (TextView) this.mainActivity.findViewById(R.id.left_back_info);
        this.myBttnLeftBackDown = (MyButton) this.mainActivity.findViewById(R.id.left_back_down);
        this.myBttnRightBackUp = (MyButton) this.mainActivity.findViewById(R.id.right_back_up);
        this.tvRightBackInfo = (TextView) this.mainActivity.findViewById(R.id.right_back_info);
        this.myBttnRightBackDown = (MyButton) this.mainActivity.findViewById(R.id.right_back_down);
        this.myBttnLeftFrontUp.setOnClickListener(this.buttonsEfSpeakerClickListener);
        this.myBttnLeftFrontDown.setOnClickListener(this.buttonsEfSpeakerClickListener);
        this.myBttnRightFrontUp.setOnClickListener(this.buttonsEfSpeakerClickListener);
        this.myBttnRightFrontDown.setOnClickListener(this.buttonsEfSpeakerClickListener);
        this.myBttnLeftBackUp.setOnClickListener(this.buttonsEfSpeakerClickListener);
        this.myBttnLeftBackDown.setOnClickListener(this.buttonsEfSpeakerClickListener);
        this.myBttnRightBackUp.setOnClickListener(this.buttonsEfSpeakerClickListener);
        this.myBttnRightBackDown.setOnClickListener(this.buttonsEfSpeakerClickListener);
        this.myBttnLeftFrontUp.setOnTouchListener(this.buttonsEfSpeakerTouchListener);
        this.myBttnLeftFrontDown.setOnTouchListener(this.buttonsEfSpeakerTouchListener);
        this.myBttnRightFrontUp.setOnTouchListener(this.buttonsEfSpeakerTouchListener);
        this.myBttnRightFrontDown.setOnTouchListener(this.buttonsEfSpeakerTouchListener);
        this.myBttnLeftBackUp.setOnTouchListener(this.buttonsEfSpeakerTouchListener);
        this.myBttnLeftBackDown.setOnTouchListener(this.buttonsEfSpeakerTouchListener);
        this.myBttnRightBackUp.setOnTouchListener(this.buttonsEfSpeakerTouchListener);
        this.myBttnRightBackDown.setOnTouchListener(this.buttonsEfSpeakerTouchListener);
        this.cbDefer.setOnCheckedChangeListener(new AmpDspDeferOnCheckedChangeListener(this));
        this.cbVolume.setOnCheckedChangeListener(new AmpDspVolumeOnCheckedChangeListener(this));
        cs();
        this.balanceSCross.go(this.ampBalanceSCross);
        String bx = getSystemString("KeySBalanceMode");
        int i = 3;
        if (bx != null) {
            i = Integer.parseInt(bx);
        }
        this.cl = i;
        ct();
        cn();
        co();
        bm();
        bp();
    }

    private void bp() {
        View inflate = View.inflate(this.mainActivity, R.layout.hct_dsp_distance, null);
        this.dialogDspDistance = new Dialog(this.mainActivity, R.style.Translucent_NoTitle);
        this.dialogDspDistance.setContentView(inflate);
        this.dialogDspDistance.findViewById(R.id.speaker_h_down).setOnClickListener(this.speakerButtonsClickListener);
        this.dialogDspDistance.findViewById(R.id.speaker_h_up).setOnClickListener(this.speakerButtonsClickListener);
        this.dialogDspDistance.findViewById(R.id.speaker_v_down).setOnClickListener(this.speakerButtonsClickListener);
        this.dialogDspDistance.findViewById(R.id.speaker_v_up).setOnClickListener(this.speakerButtonsClickListener);
        this.dw = (TextView) this.dialogDspDistance.findViewById(R.id.speaker_h_info);
        this.dx = (TextView) this.dialogDspDistance.findViewById(R.id.speaker_v_info);
        cw();
        this.dialogDspDistance.findViewById(R.id.affirm).setOnClickListener(new AmpDspDialogDistanceConfirmOnClickListener(this));
    }

    protected void bq(int i) {
        this.cm = Executors.newSingleThreadScheduledExecutor();
        this.cm.scheduleWithFixedDelay(new AmpDspRunnable(this, i), 0, 150, TimeUnit.MILLISECONDS);
    }

    protected void br() {
        this.cbEf = (CheckBox) this.mainActivity.findViewById(R.id.ef_checkbox);
        if (getSystemInt("key_efcheck", 1) == 1) {
            this.cbEf.setChecked(true);
        } else {
            this.cbEf.setChecked(false);
        }
        this.bttnEfCustom1 = (Button) this.mainActivity.findViewById(R.id.ef_custom1);
        this.bttnEfCustom2 = (Button) this.mainActivity.findViewById(R.id.ef_custom2);
        this.bttnEfCustom3 = (Button) this.mainActivity.findViewById(R.id.ef_custom3);
        this.bttnEfReset = (Button) this.mainActivity.findViewById(R.id.ef_reset);
        this.bttnEfReset.setOnClickListener(this);
        this.bttnEfCustom1.setOnClickListener(this.buttonsEfCustomClickListener);
        this.bttnEfCustom2.setOnClickListener(this.buttonsEfCustomClickListener);
        this.bttnEfCustom3.setOnClickListener(this.buttonsEfCustomClickListener);
        this.bttnEfCustom1.setOnTouchListener(this.buttonsEfCustomTouchListener);
        this.bttnEfCustom2.setOnTouchListener(this.buttonsEfCustomTouchListener);
        this.bttnEfCustom3.setOnTouchListener(this.buttonsEfCustomTouchListener);
        this.vsbLudT = (VerticalSeekBar) this.mainActivity.findViewById(R.id.lud_tseekbar);
        this.vsbLudB = (VerticalSeekBar) this.mainActivity.findViewById(R.id.lud_bseekbar);
        this.vsbSpa = (VerticalSeekBar) this.mainActivity.findViewById(R.id.spa_seekbar);
        this.vsbEfSub = (VerticalSeekBar) this.mainActivity.findViewById(R.id.efsub_seekbar);
        this.vsbCut = (VerticalSeekBar) this.mainActivity.findViewById(R.id.cut_seekbar);
        this.vsbSubL = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sub_lseekbar);
        this.vsbSubR = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sub_rseekbar);
        this.vsbLudT.setOnChangeListener(this.verticalSeekBarEf);
        this.vsbLudB.setOnChangeListener(this.verticalSeekBarEf);
        this.vsbSpa.setOnChangeListener(this.verticalSeekBarEf);
        this.vsbEfSub.setOnChangeListener(this.verticalSeekBarEf);
        this.vsbCut.setOnChangeListener(this.verticalSeekBarEf);
        this.vsbSubL.setOnChangeListener(this.verticalSeekBarEf);
        this.vsbSubR.setOnChangeListener(this.verticalSeekBarEf);
        this.cbLud = (CheckBox) this.mainActivity.findViewById(R.id.lud_check);
        this.cbSpa = (CheckBox) this.mainActivity.findViewById(R.id.spa_check);
        this.cbEfSub = (CheckBox) this.mainActivity.findViewById(R.id.efsub_check);
        this.cbCut = (CheckBox) this.mainActivity.findViewById(R.id.cut_check);
        this.cbSub = (CheckBox) this.mainActivity.findViewById(R.id.sub_check);
        this.cbLud.setOnTouchListener(this.checkBoxesEqTouchListener);
        this.cbSpa.setOnTouchListener(this.checkBoxesEqTouchListener);
        this.cbEfSub.setOnTouchListener(this.checkBoxesEqTouchListener);
        this.cbCut.setOnTouchListener(this.checkBoxesEqTouchListener);
        this.cbSub.setOnTouchListener(this.checkBoxesEqTouchListener);
        this.cbEf.setOnCheckedChangeListener(new AmpDspEffectOnCheckedChangeListener(this));
        this.cbLud.setOnCheckedChangeListener(new AmpDspLudOnCheckedChangeListener(this));
        this.cbSpa.setOnCheckedChangeListener(new AmpDspSpaOnCheckedChangeListener(this));
        this.cbEfSub.setOnCheckedChangeListener(new AmpDspEfSubOnCheckedChangeListener(this));
        this.cbCut.setOnCheckedChangeListener(new AmpDspCutOnCheckedChangeListener(this));
        this.cbSub.setOnCheckedChangeListener(new AmpDspSubOnCheckedChangeListener(this));
        cp();
        cr();
        cu();
        cm();
        cv();
        cq();
    }

    protected void bs() {
        bz();
        bo();
        ca();
    }

    protected void bt() {
        this.tvSoundProgress[0] = (TextView) this.mainActivity.findViewById(R.id.sound_progress1_vaule);
        this.tvSoundProgress[1] = (TextView) this.mainActivity.findViewById(R.id.sound_progress2_vaule);
        this.tvSoundProgress[2] = (TextView) this.mainActivity.findViewById(R.id.sound_progress3_vaule);
        this.tvSoundProgress[3] = (TextView) this.mainActivity.findViewById(R.id.sound_progress4_vaule);
        this.tvSoundProgress[4] = (TextView) this.mainActivity.findViewById(R.id.sound_progress5_vaule);
        this.tvSoundProgress[5] = (TextView) this.mainActivity.findViewById(R.id.sound_progress6_vaule);
        this.tvSoundProgress[6] = (TextView) this.mainActivity.findViewById(R.id.sound_progress7_vaule);
        this.tvSoundProgress[7] = (TextView) this.mainActivity.findViewById(R.id.sound_progress8_vaule);
        this.tvSoundProgress[8] = (TextView) this.mainActivity.findViewById(R.id.sound_progress9_vaule);
        this.tvSoundProgress[9] = (TextView) this.mainActivity.findViewById(R.id.sound_progress10_vaule);
        this.tvSoundProgress[10] = (TextView) this.mainActivity.findViewById(R.id.sound_progress11_vaule);
        this.tvSoundProgress[11] = (TextView) this.mainActivity.findViewById(R.id.sound_progress12_vaule);
        this.tvSoundProgress[12] = (TextView) this.mainActivity.findViewById(R.id.sound_progress13_vaule);
        this.tvSoundProgress[13] = (TextView) this.mainActivity.findViewById(R.id.sound_progress14_vaule);
        this.tvSoundProgress[14] = (TextView) this.mainActivity.findViewById(R.id.sound_progress15_vaule);
        this.vsbSoundProgress[0] = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sound_progress1);
        this.vsbSoundProgress[1] = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sound_progress2);
        this.vsbSoundProgress[2] = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sound_progress3);
        this.vsbSoundProgress[3] = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sound_progress4);
        this.vsbSoundProgress[4] = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sound_progress5);
        this.vsbSoundProgress[5] = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sound_progress6);
        this.vsbSoundProgress[6] = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sound_progress7);
        this.vsbSoundProgress[7] = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sound_progress8);
        this.vsbSoundProgress[8] = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sound_progress9);
        this.vsbSoundProgress[9] = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sound_progress10);
        this.vsbSoundProgress[10] = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sound_progress11);
        this.vsbSoundProgress[11] = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sound_progress12);
        this.vsbSoundProgress[12] = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sound_progress13);
        this.vsbSoundProgress[13] = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sound_progress14);
        this.vsbSoundProgress[14] = (VerticalSeekBar) this.mainActivity.findViewById(R.id.sound_progress15);
        for (int i = 0; i < this.vsbSoundProgress.length; i++) {
            this.vsbSoundProgress[i].setOnChangeListener(this.verticalSeekBarEq);
            this.vsbSoundProgress[i].setMax(20);
        }
        ImageButton imageButton = (ImageButton) this.mainActivity.findViewById(R.id.soundeffect_arrowhead_left);
        ImageButton imageButton2 = (ImageButton) this.mainActivity.findViewById(R.id.soundeffect_arrowhead_right);
        ((ImageView) this.mainActivity.findViewById(R.id.eq_reset)).setOnClickListener(this.buttonsEqChangeClickListener);
        this.bttnEqCustom1 = (Button) this.mainActivity.findViewById(R.id.eq_custom1);
        this.bttnEqCustom2 = (Button) this.mainActivity.findViewById(R.id.eq_custom2);
        this.bttnEqCustom3 = (Button) this.mainActivity.findViewById(R.id.eq_custom3);
        this.bttnEqCustom1.setOnClickListener(this.buttonsEqCustomClickListener);
        this.bttnEqCustom2.setOnClickListener(this.buttonsEqCustomClickListener);
        this.bttnEqCustom3.setOnClickListener(this.buttonsEqCustomClickListener);
        this.bttnEqCustom1.setOnTouchListener(this.buttonsEqCustomTouchListener);
        this.bttnEqCustom2.setOnTouchListener(this.buttonsEqCustomTouchListener);
        this.bttnEqCustom3.setOnTouchListener(this.buttonsEqCustomTouchListener);
        this.bz = (TextView) this.mainActivity.findViewById(R.id.amp_soundmode);
        imageButton.setOnClickListener(this.buttonsEqChangeClickListener);
        imageButton2.setOnClickListener(this.buttonsEqChangeClickListener);
        bj();
    }

    protected void bu() {
        this.bttnBalance.setSelected(true);
        this.bttnSound.setSelected(false);
        this.viewDspEffectLayout.setSelected(false);
        this.viewDspEqLayout.setVisibility(0);
        this.viewDspEqLayout.startAnimation(this.animLeftIn);
    }

    protected int getCheckedAsInt(CheckBox checkBox) {
        return checkBox.isChecked() ? 1 : 0;
    }

    protected int getSystemInt(String str, int i) {
        try {
            i = System.getInt(this.mainActivity.getContentResolver(), str, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    protected String getSystemString(String str) {
        try {
            return System.getString(this.mainActivity.getContentResolver(), str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected String by(int i) {
        return i == 0 ? "0 dB" : "-" + (((float) i) / 10.0f) + " dB";
    }

    private void bz() {
        this.mainActivity.findViewById(R.id.btn_amp_front).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.btn_amp_back).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.btn_amp_left).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.btn_amp_right).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.amp_banlance1).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.amp_banlance2).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.amp_banlance3).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.amp_banlance4).setOnClickListener(this);
        this.mainActivity.findViewById(R.id.amp_banlance5).setOnClickListener(this);
        this.balanceCross = (AmpBalance) this.mainActivity.findViewById(R.id.balanceCross);
        cj();
        this.balanceCross.go(this.ampBalanceCross);
        String bx = getSystemString("KeyBalanceMode");
        int i = 4;
        if (bx != null) {
            i = Integer.parseInt(bx);
        }
        this.bt = i;
        ck();
    }

    protected void ca() {
        this.bttnCommon = (Button) this.mainActivity.findViewById(R.id.bt_common);
        this.bttnSpecialty = (Button) this.mainActivity.findViewById(R.id.bt_specialty);
        this.layoutSoundCommon = (LinearLayout) this.mainActivity.findViewById(R.id.sound_common);
        this.layoutSoundSpecialty = (FrameLayout) this.mainActivity.findViewById(R.id.sound_specialty);
        this.bttnCommon.setOnClickListener(this.buttonsCommonSpecialtyClickListener);
        this.bttnSpecialty.setOnClickListener(this.buttonsCommonSpecialtyClickListener);
        cl();
    }

    protected boolean cb(float f, float f2, float f3, float f4, long j, long j2, long j3) {
        return Math.abs(f3 - f) <= 10.0f && Math.abs(f4 - f2) <= 10.0f && j2 - j >= j3;
    }

    protected void putSystemInt(String str, int i) {
        try {
            System.putInt(this.mainActivity.getContentResolver(), str, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cd() {
        this.ax = 0;
        putSystemInt("KeyEfCTmode", this.ax);
        cp();
        putSystemInt("key_Lud", 0);
        putSystemString("KeyLudGain", "0,0");
        putSystemString("KeySPA", "0,0");
        putSystemString("KeyCUTFREQ", "0,0");
        putSystemString("KeyBASS", "0,0,0");
        putSystemString("KeyEFSUB", "0,0");
        cr();
        cu();
        cm();
        cv();
        cq();
    }

    private void ce(int i) {
        String str = null;
        switch (i) {
            case 1:
                str = getSystemString("KeyCustom1EF");
                break;
            case 2:
                str = getSystemString("KeyCustom2EF");
                break;
            case 3:
                str = getSystemString("KeyCustom3EF");
                break;
        }
        if (str == null || str.length() <= 0) {
            putSystemInt("key_Lud", 1);
            switch (i) {
                case 1:
                    putSystemString("KeyLudGain", "2,0");
                    putSystemString("KeySPA", "0,0");
                    putSystemString("KeyCUTFREQ", "0,0");
                    putSystemString("KeyBASS", "0,0,2");
                    putSystemString("KeyEFSUB", "1,15");
                    return;
                case 2:
                    putSystemString("KeyLudGain", "2,0");
                    putSystemString("KeySPA", "0,0");
                    putSystemString("KeyCUTFREQ", "1,0");
                    putSystemString("KeyBASS", "1,0,2");
                    putSystemString("KeyEFSUB", "1,15");
                    return;
                case 3:
                    putSystemString("KeyLudGain", "2,0");
                    putSystemString("KeySPA", "1,1");
                    putSystemString("KeyCUTFREQ", "1,1");
                    putSystemString("KeyBASS", "1,0,3");
                    putSystemString("KeyEFSUB", "1,15");
                    return;
                default:
                    return;
            }
        }
        String[] split = str.split(",");
        putSystemInt("key_Lud", Integer.parseInt(split[0]));
        putSystemString("KeyLudGain", Integer.parseInt(split[1]) + "," + Integer.parseInt(split[2]));
        putSystemString("KeySPA", Integer.parseInt(split[3]) + "," + Integer.parseInt(split[4]));
        putSystemString("KeyCUTFREQ", Integer.parseInt(split[5]) + "," + Integer.parseInt(split[6]));
        putSystemString("KeyBASS", Integer.parseInt(split[7]) + "," + Integer.parseInt(split[8]) + "," + Integer.parseInt(split[9]));
        putSystemString("KeyEFSUB", Integer.parseInt(split[10]) + "," + Integer.parseInt(split[11]));
    }

    protected void cf() {
        this.cbLud.setEnabled(false);
        this.cbSpa.setEnabled(false);
        this.cbCut.setEnabled(false);
        this.cbSub.setEnabled(false);
        this.cbEfSub.setEnabled(false);
        this.bttnEfCustom1.setEnabled(false);
        this.bttnEfCustom2.setEnabled(false);
        this.bttnEfCustom3.setEnabled(false);
        this.bttnEfReset.setEnabled(false);
        putSystemString("KeyEffect", getCheckedAsInt(this.cbLud) + "," + getCheckedAsInt(this.cbSpa) + "," + getCheckedAsInt(this.cbCut) + "," + getCheckedAsInt(this.cbSub) + "," + getSystemInt("KeyEfCTmode", 0) + "," + getCheckedAsInt(this.cbEfSub));
        putSystemInt("KeyEfCTmode", 0);
        putSystemInt("key_Lud", 0);
        String bx = getSystemString("KeySPA");
        if (bx == null || bx.length() <= 0) {
            putSystemString("KeySPA", "0,0");
        } else {
            putSystemString("KeySPA", "0," + Integer.parseInt(bx.split(",")[1]));
        }
        bx = getSystemString("KeyCUTFREQ");
        if (bx == null || bx.length() <= 0) {
            putSystemString("KeyCUTFREQ", "0,0");
        } else {
            putSystemString("KeyCUTFREQ", "0," + Integer.parseInt(bx.split(",")[1]));
        }
        bx = getSystemString("KeyBASS");
        if (bx == null || bx.length() <= 0) {
            putSystemString("KeyBASS", "0,0,0");
        } else {
            String[] split = bx.split(",");
            putSystemString("KeyBASS", "0," + Integer.parseInt(split[1]) + "," + Integer.parseInt(split[2]));
        }
        bx = getSystemString("KeyEFSUB");
        if (bx == null || bx.length() <= 0) {
            putSystemString("KeyEFSUB", "0,0");
        } else {
            putSystemString("KeyEFSUB", "0," + Integer.parseInt(bx.split(",")[1]));
        }
        cp();
        cr();
        cu();
        cm();
        cv();
        cq();
    }

    protected void cg() {
        this.cbLud.setEnabled(true);
        this.cbSpa.setEnabled(true);
        this.cbCut.setEnabled(true);
        this.cbSub.setEnabled(true);
        this.cbEfSub.setEnabled(true);
        this.bttnEfCustom1.setEnabled(true);
        this.bttnEfCustom2.setEnabled(true);
        this.bttnEfCustom3.setEnabled(true);
        this.bttnEfReset.setEnabled(true);
        String[] split = getSystemString("KeyEffect").split(",");
        putSystemInt("KeyEfCTmode", Integer.parseInt(split[4]));
        putSystemInt("key_Lud", Integer.parseInt(split[0]));
        String bx = getSystemString("KeySPA");
        if (bx == null || bx.length() <= 0) {
            putSystemString("KeySPA", "0,0");
        } else {
            putSystemString("KeySPA", Integer.parseInt(split[1]) + "," + Integer.parseInt(bx.split(",")[1]));
        }
        bx = getSystemString("KeyCUTFREQ");
        if (bx == null || bx.length() <= 0) {
            putSystemString("KeyCUTFREQ", "0,0");
        } else {
            putSystemString("KeyCUTFREQ", Integer.parseInt(split[2]) + "," + Integer.parseInt(bx.split(",")[1]));
        }
        bx = getSystemString("KeyBASS");
        if (bx == null || bx.length() <= 0) {
            putSystemString("KeyBASS", "0,0,0");
        } else {
            String[] split2 = bx.split(",");
            putSystemString("KeyBASS", Integer.parseInt(split[3]) + "," + Integer.parseInt(split2[1]) + "," + Integer.parseInt(split2[2]));
        }
        bx = getSystemString("KeyEFSUB");
        if (bx == null || bx.length() <= 0) {
            putSystemString("KeyEFSUB", "0,0");
        } else {
            putSystemString("KeyEFSUB", Integer.parseInt(split[5]) + "," + Integer.parseInt(bx.split(",")[1]));
        }
        cp();
        cr();
        cu();
        cm();
        cv();
        cq();
    }

    protected void ci() {
        if (this.cm != null) {
            this.cm.shutdownNow();
            this.cm = null;
        }
    }

    protected void cj() {
        if (this.balanceCross != null) {
            String bx = getSystemString("KeyBalance");
            if (bx != null) {
                String[] split = bx.split(",");
                try {
                    this.an = Integer.parseInt(split[0]);
                    this.ao = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    this.an = 14;
                    this.ao = 14;
                }
            } else {
                this.an = 14;
                this.ao = 14;
            }
            if (this.an > 28) {
                this.an = 28;
            }
            if (this.ao > 28) {
                this.ao = 28;
            }
            this.balanceCross.gn(this.an, this.ao, 28);
        }
    }

    protected void ck() {
        boolean z = true;
        if (this.bt == 0) {
            this.mainActivity.findViewById(R.id.amp_banlance1).setSelected(true);
            this.mainActivity.findViewById(R.id.amp_banlance2).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance3).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance4).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance5).setSelected(false);
        } else if (this.bt == 1) {
            this.mainActivity.findViewById(R.id.amp_banlance1).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance2).setSelected(!this.cj);
            View r0 = this.mainActivity.findViewById(R.id.amp_banlance3);
            if (!this.cj) {
                z = false;
            }
            r0.setSelected(z);
            this.mainActivity.findViewById(R.id.amp_banlance4).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance5).setSelected(false);
        } else if (this.bt == 2) {
            this.mainActivity.findViewById(R.id.amp_banlance1).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance2).setSelected(this.cj);
            View r0 = this.mainActivity.findViewById(R.id.amp_banlance3);
            if (this.cj) {
                z = false;
            }
            r0.setSelected(z);
            this.mainActivity.findViewById(R.id.amp_banlance4).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance5).setSelected(false);
        } else if (this.bt == 3) {
            this.mainActivity.findViewById(R.id.amp_banlance1).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance2).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance3).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance4).setSelected(true);
            this.mainActivity.findViewById(R.id.amp_banlance5).setSelected(false);
        } else if (this.bt == 4) {
            this.mainActivity.findViewById(R.id.amp_banlance1).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance2).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance3).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance4).setSelected(false);
            this.mainActivity.findViewById(R.id.amp_banlance5).setSelected(true);
        }
    }

    protected void cl() {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mainActivity, R.anim.left_in);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this.mainActivity, R.anim.right_out);
        Animation loadAnimation3 = AnimationUtils.loadAnimation(this.mainActivity, R.anim.left_out);
        Animation loadAnimation4 = AnimationUtils.loadAnimation(this.mainActivity, R.anim.right_in);
        if (getSystemInt("KeyFaderMode", 0) == 1) {
            this.bttnSpecialty.setSelected(true);
            this.bttnCommon.setSelected(false);
            this.layoutSoundSpecialty.setVisibility(0);
            this.layoutSoundCommon.setVisibility(8);
            this.layoutSoundSpecialty.startAnimation(loadAnimation);
            this.layoutSoundCommon.startAnimation(loadAnimation2);
            return;
        }
        this.bttnCommon.setSelected(true);
        this.bttnSpecialty.setSelected(false);
        this.layoutSoundCommon.setVisibility(0);
        this.layoutSoundSpecialty.setVisibility(8);
        this.layoutSoundSpecialty.startAnimation(loadAnimation3);
        this.layoutSoundCommon.startAnimation(loadAnimation4);
    }

    protected void cm() {
        if (this.cbCut != null) {
            String bx = getSystemString("KeyCUTFREQ");
            if (bx == null || bx.length() <= 0) {
                this.cbCut.setChecked(false);
                this.vsbCut.setValue(0);
                this.vsbCut.setEnabled(false);
            } else {
                String[] split = bx.split(",");
                this.vsbCut.setValue(Integer.parseInt(split[1]));
                if (Integer.parseInt(split[0]) == 0) {
                    this.cbCut.setChecked(false);
                    this.vsbCut.setEnabled(false);
                } else {
                    this.cbCut.setChecked(true);
                    this.vsbCut.setEnabled(true);
                }
            }
        }
    }

    protected void cn() {
        if (this.cbDefer != null) {
            if (getSystemInt("KeyDeferMode", 0) == 1) {
                this.cbDefer.setChecked(true);
            } else {
                this.cbDefer.setChecked(false);
            }
        }
    }

    private void co() {
        if (this.cbVolume != null) {
            if (getSystemInt("KeyVolumeMode", 0) == 1) {
                this.cbVolume.setChecked(true);
                cx(1);
            } else {
                this.cbVolume.setChecked(false);
                cx(0);
            }
        }
    }

    protected void cp() {
        switch (getSystemInt("KeyEfCTmode", 0)) {
            case 0:
                this.bttnEfCustom1.setSelected(false);
                this.bttnEfCustom2.setSelected(false);
                this.bttnEfCustom3.setSelected(false);
                return;
            case 1:
                ce(1);
                this.bttnEfCustom1.setSelected(true);
                this.bttnEfCustom2.setSelected(false);
                this.bttnEfCustom3.setSelected(false);
                return;
            case 2:
                ce(2);
                this.bttnEfCustom2.setSelected(true);
                this.bttnEfCustom1.setSelected(false);
                this.bttnEfCustom3.setSelected(false);
                return;
            case 3:
                ce(3);
                this.bttnEfCustom3.setSelected(true);
                this.bttnEfCustom2.setSelected(false);
                this.bttnEfCustom1.setSelected(false);
                return;
            default:
                return;
        }
    }

    protected void cq() {
        if (this.cbEfSub != null) {
            String bx = getSystemString("KeyEFSUB");
            if (bx == null || bx.length() <= 0) {
                this.cbEfSub.setChecked(false);
                this.vsbEfSub.setValue(0);
                this.vsbEfSub.setEnabled(false);
            } else {
                String[] split = bx.split(",");
                this.vsbEfSub.setValue(Integer.parseInt(split[1]));
                if (Integer.parseInt(split[0]) == 0) {
                    this.cbEfSub.setChecked(false);
                    this.vsbEfSub.setEnabled(false);
                } else {
                    this.cbEfSub.setChecked(true);
                    this.vsbEfSub.setEnabled(true);
                }
            }
        }
    }

    private void cs() {
        if (this.balanceSCross != null) {
            String bx = getSystemString("KeySBalance");
            if (bx != null) {
                String[] split = bx.split(",");
                try {
                    this.dt = Integer.parseInt(split[0]);
                    this.du = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    this.dt = 14;
                    this.du = 7;
                }
            } else {
                this.dt = 14;
                this.du = 7;
            }
            if (this.dt > 28) {
                this.dt = 28;
            }
            if (this.du > 28) {
                this.du = 28;
            }
            this.balanceSCross.gn(this.dt, this.du, 28);
        }
    }

    protected void ct() {
        if (this.cl == 0) {
            this.mainActivity.findViewById(R.id.s_banlance0).setSelected(true);
            this.mainActivity.findViewById(R.id.s_banlance1).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance2).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance3).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance4).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance5).setSelected(false);
        } else if (this.cl == 1) {
            this.mainActivity.findViewById(R.id.s_banlance0).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance1).setSelected(true);
            this.mainActivity.findViewById(R.id.s_banlance2).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance3).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance4).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance5).setSelected(false);
        } else if (this.cl == 2) {
            this.mainActivity.findViewById(R.id.s_banlance0).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance1).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance2).setSelected(true);
            this.mainActivity.findViewById(R.id.s_banlance3).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance4).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance5).setSelected(false);
        } else if (this.cl == 3) {
            this.mainActivity.findViewById(R.id.s_banlance0).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance1).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance2).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance3).setSelected(true);
            this.mainActivity.findViewById(R.id.s_banlance4).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance5).setSelected(false);
        } else if (this.cl == 4) {
            this.mainActivity.findViewById(R.id.s_banlance0).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance1).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance2).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance3).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance4).setSelected(true);
            this.mainActivity.findViewById(R.id.s_banlance5).setSelected(false);
        } else if (this.cl == 5) {
            this.mainActivity.findViewById(R.id.s_banlance0).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance1).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance2).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance3).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance4).setSelected(false);
            this.mainActivity.findViewById(R.id.s_banlance5).setSelected(true);
        }
    }

    protected void cu() {
        if (this.cbSpa != null) {
            String bx = getSystemString("KeySPA");
            if (bx == null || bx.length() <= 0) {
                this.cbSpa.setChecked(false);
                this.vsbSpa.setValue(0);
                this.vsbSpa.setEnabled(false);
            } else {
                String[] split = bx.split(",");
                this.vsbSpa.setValue(Integer.parseInt(split[1]));
                if (Integer.parseInt(split[0]) == 0) {
                    this.cbSpa.setChecked(false);
                    this.vsbSpa.setEnabled(false);
                } else {
                    this.cbSpa.setChecked(true);
                    this.vsbSpa.setEnabled(true);
                }
            }
        }
    }

    protected void cv() {
        if (this.cbSub != null) {
            String bx = getSystemString("KeyBASS");
            if (bx == null || bx.length() <= 0) {
                this.cbSub.setChecked(false);
                this.vsbSubL.setValue(0);
                this.vsbSubR.setValue(0);
                this.vsbSubL.setEnabled(false);
                this.vsbSubR.setEnabled(false);
            } else {
                String[] split = bx.split(",");
                int parseInt = Integer.parseInt(split[1]);
                int parseInt2 = Integer.parseInt(split[2]);
                this.vsbSubL.setValue(parseInt);
                this.vsbSubR.setValue(parseInt2);
                if (Integer.parseInt(split[0]) == 0) {
                    this.cbSub.setChecked(false);
                    this.vsbSubL.setEnabled(false);
                    this.vsbSubR.setEnabled(false);
                } else {
                    this.cbSub.setChecked(true);
                    this.vsbSubL.setEnabled(true);
                    this.vsbSubR.setEnabled(true);
                }
            }
        }
    }

    protected void cw() {
        String bx = getSystemString("KeyDistance");
        if (bx == null || bx.length() <= 0) {
            this.dw.setText("140cm");
            this.dx.setText("110cm");
            return;
        }
        String[] split = bx.split(",");
        this.dw.setText(split[0] + "cm");
        this.dx.setText(split[1] + "cm");
    }

    protected void cx(int i) {
        if (i == 1) {
            this.myBttnLeftFrontUp.setEnabled(true);
            this.myBttnLeftFrontDown.setEnabled(true);
            this.myBttnRightFrontUp.setEnabled(true);
            this.myBttnRightFrontDown.setEnabled(true);
            this.myBttnLeftBackUp.setEnabled(true);
            this.myBttnLeftBackDown.setEnabled(true);
            this.myBttnRightBackUp.setEnabled(true);
            this.myBttnRightBackDown.setEnabled(true);
            return;
        }
        this.myBttnLeftFrontUp.setEnabled(false);
        this.myBttnLeftFrontDown.setEnabled(false);
        this.myBttnRightFrontUp.setEnabled(false);
        this.myBttnRightFrontDown.setEnabled(false);
        this.myBttnLeftBackUp.setEnabled(false);
        this.myBttnLeftBackDown.setEnabled(false);
        this.myBttnRightBackUp.setEnabled(false);
        this.myBttnRightBackDown.setEnabled(false);
    }

    public int bh() {
        return R.layout.hct_dsppfield;
    }

    public void bi() {
        this.viewDspEqLayout = this.mainActivity.findViewById(R.id.dsp_eq_layout);
        this.viewDspSoundLayout = this.mainActivity.findViewById(R.id.dsp_sound_layout);
        this.viewDspEffectLayout = this.mainActivity.findViewById(R.id.dsp_effect_layout);
        if (this.mainActivity.getParameters("cfg_cansub_0=").equals("1")) {
            this.cj = true;
        }
        bt();
        br();
        bs();
        this.bttnBalance = (ImageView) this.mainActivity.findViewById(R.id.amp_blance_button);
        this.bttnSound = (ImageView) this.mainActivity.findViewById(R.id.amp_sound_button);
        this.bttnEffect = (ImageView) this.mainActivity.findViewById(R.id.amp_effect_button);
        this.animLeftIn = AnimationUtils.loadAnimation(this.mainActivity, R.anim.left_in);
        this.bttnBalance.setOnClickListener(new AmpDspBalanceOnClickListener(this));
        this.bttnSound.setOnClickListener(new AmpDspSoundOnClickListener(this));
        this.bttnEffect.setOnClickListener(new AmpDspEffectOnClickListener(this));
        bu();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.microntek.eqchange");
        intentFilter.addAction("com.microntek.balancechange");
        intentFilter.addAction("com.microntek.loundchange");
        intentFilter.addAction("com.microntek.ampclose");
        this.mainActivity.registerReceiver(this.ai, intentFilter);
    }

    public void bj() {
        String str = null;
        int i = 0;
        if (this.bz != null) {
            this.bf = getSystemInt("KeyEQmode", 0);
            if (this.bf > 6 || this.bf < 0) {
                this.bf = 0;
            }
            if (this.bf == 0) {
                this.as = getSystemInt("KeyCTmode", 0);
                switch (this.as) {
                    case 0:
                        str = getSystemString("KeyCustomEQ");
                        break;
                    case 1:
                        this.bttnEqCustom1.setSelected(true);
                        this.bttnEqCustom2.setSelected(false);
                        this.bttnEqCustom3.setSelected(false);
                        str = getSystemString("KeyCustom1EQ");
                        break;
                    case 2:
                        this.bttnEqCustom2.setSelected(true);
                        this.bttnEqCustom1.setSelected(false);
                        this.bttnEqCustom3.setSelected(false);
                        str = getSystemString("KeyCustom2EQ");
                        break;
                    case 3:
                        this.bttnEqCustom3.setSelected(true);
                        this.bttnEqCustom1.setSelected(false);
                        this.bttnEqCustom2.setSelected(false);
                        str = getSystemString("KeyCustom3EQ");
                        break;
                }
                int i2;
                if (str == null || str.length() <= 0) {
                    for (i2 = 0; i2 < this.bv.length; i2++) {
                        this.bv[i2] = 10;
                    }
                } else {
                    String[] split = str.split(",");
                    for (i2 = 0; i2 < split.length; i2++) {
                        try {
                            this.bv[i2] = Integer.parseInt(split[i2]);
                        } catch (NumberFormatException e) {
                            this.bv[i2] = 10;
                        }
                    }
                }
                while (i < this.vsbSoundProgress.length) {
                    this.vsbSoundProgress[i].setValue(this.bv[i]);
                    this.tvSoundProgress[i].setText("" + (this.bv[i] - 10));
                    i++;
                }
            } else {
                this.bttnEqCustom3.setSelected(false);
                this.bttnEqCustom1.setSelected(false);
                this.bttnEqCustom2.setSelected(false);
                while (i < this.vsbSoundProgress.length) {
                    this.vsbSoundProgress[i].setValue(MTCData.music_stytle_data15[this.bf - 1][i]);
                    this.tvSoundProgress[i].setText("" + (MTCData.music_stytle_data15[this.bf - 1][i] - 10));
                    i++;
                }
            }
            this.bz.setText(this.mainActivity.getResources().getString(this.bf + R.string.music_style0).toString());
        }
    }

    public void bk() {
        if (this.cbLud != null) {
            if (getSystemInt("key_Lud", 0) == 1) {
                this.cbLud.setChecked(true);
            } else {
                this.cbLud.setChecked(false);
            }
        }
    }

    public void putSystemString(String str, String str2) {
        try {
            System.putString(this.mainActivity.getContentResolver(), str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bm() {
        String bx = getSystemString("KeyDspVolume");
        if (bx == null || bx.length() <= 0) {
            this.tvLeftFrontInfo.setText("0 dB");
            this.tvRightFrontInfo.setText("0 dB");
            this.tvLeftBackInfo.setText("0 dB");
            this.tvRightBackInfo.setText("0 dB");
            return;
        }
        String[] split = bx.split(",");
        this.tvLeftFrontInfo.setText(by(Integer.parseInt(split[0])));
        this.tvRightFrontInfo.setText(by(Integer.parseInt(split[1])));
        this.tvLeftBackInfo.setText(by(Integer.parseInt(split[2])));
        this.tvRightBackInfo.setText(by(Integer.parseInt(split[3])));
    }

    public void bn() {
        this.mainActivity.unregisterReceiver(this.ai);
        if (this.dialogDspDistance != null) {
            this.dialogDspDistance.dismiss();
        }
    }

    public void ch() {
        LayoutParams attributes = this.dialogDspDistance.getWindow().getAttributes();
        attributes.width = this.mainActivity.getResources().getDimensionPixelSize(R.dimen.distance_width);
        attributes.height = this.mainActivity.getResources().getDimensionPixelSize(R.dimen.distance_height);
        this.dialogDspDistance.getWindow().setAttributes(attributes);
        this.dialogDspDistance.getWindow().setGravity(17);
        this.dialogDspDistance.setCanceledOnTouchOutside(true);
        this.dialogDspDistance.show();
    }

    public void cr() {
        if (this.cbLud != null) {
            int bw = getSystemInt("key_Lud", 0);
            String bx = getSystemString("KeyLudGain");
            if (bx == null || bx.length() <= 0) {
                this.cbLud.setChecked(false);
                this.vsbLudT.setValue(0);
                this.vsbLudB.setValue(0);
                this.vsbLudT.setEnabled(false);
                this.vsbLudB.setEnabled(false);
            } else {
                String[] split = bx.split(",");
                int parseInt = Integer.parseInt(split[0]);
                int parseInt2 = Integer.parseInt(split[1]);
                this.vsbLudT.setValue(parseInt);
                this.vsbLudB.setValue(parseInt2);
                if (bw == 0) {
                    this.cbLud.setChecked(false);
                    this.vsbLudT.setEnabled(false);
                    this.vsbLudB.setEnabled(false);
                } else {
                    this.cbLud.setChecked(true);
                    this.vsbLudT.setEnabled(true);
                    this.vsbLudB.setEnabled(true);
                }
            }
        }
    }

    public void onClick(View view) {
        int i = 7;
        int i2 = 14;
        String[] split;
        switch (view.getId()) {
            case R.id.btn_amp_left:
                this.bt = 0;
                this.balanceCross.left();
                return;
            case R.id.btn_amp_right:
                this.bt = 0;
                this.balanceCross.right();
                return;
            case R.id.btn_amp_front:
                this.bt = 0;
                this.balanceCross.front();
                return;
            case R.id.btn_amp_back:
                this.bt = 0;
                this.balanceCross.back();
                return;
            case R.id.amp_banlance2:
                if (this.cj) {
                    this.bt = 2;
                    this.balanceCross.setValue(28, 0);
                    return;
                }
                this.bt = 1;
                this.balanceCross.setValue(0, 0);
                return;
            case R.id.amp_banlance3:
                if (this.cj) {
                    this.bt = 1;
                    this.balanceCross.setValue(i2, 0);
                    return;
                }
                this.bt = 2;
                this.balanceCross.setValue(i2, 0);
                return;
            case R.id.amp_banlance4:
                this.bt = 3;
                this.balanceCross.setValue(i2, 28);
                return;
            case R.id.amp_banlance5:
                this.bt = 4;
                this.balanceCross.setValue(i2, i2);
                return;
            case R.id.amp_banlance1:
                this.bt = 0;
                String bx = getSystemString("KeyBalanceMode1");
                if (bx != null) {
                    split = bx.split(",");
                    try {
                        i = Integer.parseInt(split[0]);
                        try {
                            i2 = Integer.parseInt(split[1]);
                        } catch (NumberFormatException e) {
                        }
                    } catch (NumberFormatException e2) {
                        i = i2;
                    }
                } else {
                    i = i2;
                }
                this.balanceCross.setValue(i, i2);
                return;
            case R.id.ef_reset:
                cd();
                return;
            case R.id.btn_s_left:
                this.cl = 0;
                this.balanceSCross.left();
                return;
            case R.id.btn_s_right:
                this.cl = 0;
                this.balanceSCross.right();
                return;
            case R.id.btn_s_front:
                this.cl = 0;
                this.balanceSCross.front();
                return;
            case R.id.btn_s_back:
                this.cl = 0;
                this.balanceSCross.back();
                return;
            case R.id.distance_setting:
                ch();
                return;
            case R.id.s_banlance1:
                this.cl = 1;
                if (this.cj) {
                    this.balanceSCross.setValue(21, 9);
                    return;
                } else {
                    this.balanceSCross.setValue(i, 9);
                    return;
                }
            case R.id.s_banlance4:
                this.cl = 4;
                this.balanceSCross.setValue(i, 23);
                return;
            case R.id.s_banlance3:
                this.cl = 3;
                this.balanceSCross.setValue(i2, i);
                return;
            case R.id.s_banlance2:
                this.cl = 2;
                if (this.cj) {
                    this.balanceSCross.setValue(i, 9);
                    return;
                } else {
                    this.balanceSCross.setValue(21, 9);
                    return;
                }
            case R.id.s_banlance5:
                this.cl = 5;
                this.balanceSCross.setValue(21, 23);
                return;
            case R.id.s_banlance0:
                this.cl = 0;
                String bx2 = getSystemString("KeySBalanceMode1");
                if (bx2 != null) {
                    split = bx2.split(",");
                    try {
                        i2 = Integer.parseInt(split[0]);
                        i = Integer.parseInt(split[1]);
                    } catch (NumberFormatException e3) {
                    }
                }
                this.balanceSCross.setValue(i2, i);
                return;
            default:
                return;
        }
    }
}
