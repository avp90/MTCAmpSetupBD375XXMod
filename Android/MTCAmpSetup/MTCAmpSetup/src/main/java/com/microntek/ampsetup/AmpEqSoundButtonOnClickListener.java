package com.microntek.ampsetup;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.microntek.ampsetup.m */
class AmpEqSoundButtonOnClickListener implements OnClickListener
{
    final /* synthetic */ AmpEq ampEq;

    AmpEqSoundButtonOnClickListener(AmpEq ampEq)
    {
        this.ampEq = ampEq;
    }

    public void onClick(View view)
     {
        if (this.ampEq.viewAmpSoundLayout.getVisibility() != 0)
        {
            this.ampEq.changeView();
        }
    }
}
