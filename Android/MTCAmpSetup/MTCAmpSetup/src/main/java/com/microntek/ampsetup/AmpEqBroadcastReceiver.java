package com.microntek.ampsetup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: com.microntek.ampsetup.k and C011k*/
class AmpEqBroadcastReceiver extends BroadcastReceiver
{
    final /* synthetic */ AmpEq ampEq;

    AmpEqBroadcastReceiver(AmpEq ampEq)
    {
        this.ampEq = ampEq;
    }

    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();
        if (!action.equals("com.microntek.eqchange"))
        {
            if (action.equals("com.microntek.balancechange"))
            {
                this.ampEq.getBalance();
                this.ampEq.putSystemString("KeyBalanceMode1", this.ampEq.avBalance1 + "," + this.ampEq.avBalance2);
                this.ampEq.f20u = 0;
                this.ampEq.updateViewForBalance();
            }
            else if (!action.equals("com.microntek.loundchange") && action.equals("com.microntek.ampclose"))
            {
                this.ampEq.mainActivity.finish();
            }
        }
    }
}
