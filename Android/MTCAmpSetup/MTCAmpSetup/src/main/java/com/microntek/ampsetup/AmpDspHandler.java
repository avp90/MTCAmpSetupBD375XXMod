package com.microntek.ampsetup;

import android.os.Handler;
import android.os.Message;

/* renamed from: com.microntek.ampsetup.p */
class AmpDspHandler extends Handler {
    final /* synthetic */ AmpDsp ampDsp;

    AmpDspHandler(AmpDsp ampDsp) {
        this.ampDsp = ampDsp;
    }

    public void handleMessage(Message message) {
        int i = 0;
        super.handleMessage(message);
        if (message.what == 0) {
            this.ampDsp.putSystemString("KeyBalance", this.ampDsp.an + "," + this.ampDsp.ao);
            this.ampDsp.putSystemString("KeyDspBalance", "0,0,140,110," + this.ampDsp.an + "," + (28 - this.ampDsp.ao));
            this.ampDsp.mainActivity.SetParameters("av_dsp=4,0,0,140,110," + this.ampDsp.an + "," + (28 - this.ampDsp.ao));
            this.ampDsp.putSystemString("KeyBalanceMode", "" + this.ampDsp.bt);
            if (this.ampDsp.bt == 0) {
                this.ampDsp.putSystemString("KeyBalanceMode1", this.ampDsp.an + "," + this.ampDsp.ao);
            }
            this.ampDsp.ck();
        } else if (message.what == 1) {
            this.ampDsp.bz.setText(this.ampDsp.mainActivity.getResources().getString(this.ampDsp.bf + R.string.music_style0).toString());
            this.ampDsp.putSystemInt("KeyEQmode", this.ampDsp.bf);
            this.ampDsp.mainActivity.carManager.setEqIdx(this.ampDsp.bf);
        } else if (message.what == 2) {
            this.ampDsp.mainActivity.carManager.setLud(true);
        } else if (message.what == 3) {
            this.ampDsp.mainActivity.SetParameters("av_dsp=3," + this.ampDsp.getSystemString("KeySPA"));
        } else if (message.what == 4) {
            this.ampDsp.mainActivity.SetParameters("av_dsp=2," + this.ampDsp.getSystemString("KeyBASS"));
        } else if (message.what == 5) {
            if (this.ampDsp.getSystemInt("key_Lud", 0) == 1) {
                this.ampDsp.mainActivity.carManager.setLud(true);
            } else {
                this.ampDsp.mainActivity.carManager.setLud(false);
            }
            this.ampDsp.mainActivity.SetParameters("av_dsp=3," + this.ampDsp.getSystemString("KeySPA"));
            this.ampDsp.mainActivity.SetParameters("av_dsp=7," + this.ampDsp.getSystemString("KeyCUTFREQ"));
            this.ampDsp.mainActivity.SetParameters("av_dsp=2," + this.ampDsp.getSystemString("KeyBASS"));
            this.ampDsp.mainActivity.SetParameters("av_dsp=8," + this.ampDsp.getSystemString("KeyEFSUB"));
        } else if (message.what == 6) {
            int r2 = this.ampDsp.getSystemInt("KeyDeferMode", 0);
            String r1s = this.ampDsp.getSystemString("KeyDistance");
            int r1;
            if (r1s == null || r1s.length() <= 0) {
                r1 = 140;
                i = 110;
            } else {
                String[] split = r1s.split(",");
                r1 = Integer.parseInt(split[0]);
                i = Integer.parseInt(split[1]);
            }
            this.ampDsp.putSystemString("KeySBalance", this.ampDsp.dt + "," + this.ampDsp.du);
            this.ampDsp.putSystemString("KeyDspBalance", "1," + r2 + "," + r1 + "," + i + "," + this.ampDsp.dt + "," + (28 - this.ampDsp.du));
            this.ampDsp.mainActivity.SetParameters("av_dsp=4,1," + r2 + "," + r1 + "," + i + "," + this.ampDsp.dt + "," + (28 - this.ampDsp.du));
            this.ampDsp.putSystemString("KeySBalanceMode", "" + this.ampDsp.cl);
            if (this.ampDsp.cl == 0) {
                this.ampDsp.putSystemString("KeySBalanceMode1", this.ampDsp.dt + "," + this.ampDsp.du);
            }
            this.ampDsp.ct();
        } else if (message.what == 7) {
            int i2, r1, r2;
            int fq = this.ampDsp.getSystemInt("KeyVolumeMode", 0);
            String r1s = this.ampDsp.getSystemString("KeyDspVolume");

            if (r1s == null || r1s.length() <= 0) {
                r1 = 0;
                r2 = 0;
                i2 = 0;
            } else {
                String[] split2 = r1s.split(",");
                i2 = Integer.parseInt(split2[0]);
                r2 = Integer.parseInt(split2[1]);
                r1 = Integer.parseInt(split2[2]);
                i = Integer.parseInt(split2[3]);
            }
            this.ampDsp.mainActivity.SetParameters("av_dsp=5," + fq + "," + i2 + "," + r2 + "," + r1 + "," + i);
        } else if (message.what == 8) {
            this.ampDsp.mainActivity.SetParameters("av_dsp=7," + this.ampDsp.getSystemString("KeyCUTFREQ"));
        } else if (message.what == 9) {
            this.ampDsp.mainActivity.SetParameters("av_dsp=8," + this.ampDsp.getSystemString("KeyEFSUB"));
        }
    }
}
