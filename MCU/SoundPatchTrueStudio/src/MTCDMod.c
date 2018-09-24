#include "MTCD.h"
#include "MTCDMod.h"
#include <stdarg.h>
#include <stdio.h>

uint8_t __attribute__((section(".Data_Mod.AvDspEqSaved"))) AvDspEqSaved[15];

const uint8_t AvDspEqTableIdx[] = {
		INITIAL_SETUP1, INITIAL_SETUP2, INITIAL_SETUP3, INPUT_SELECTOR, INPUT_GAIN, VOLUME_GAIN, 
		FADER_1CH_FRONT, FADER_2CH_FRONT, FADER_1CH_REAR, FADER_2CH_REAR, MIXING, BASS_SETUP, MIDDLE_SETUP, TREBLE_SETUP, LOUDNESS_GAIN } ;

void __attribute__((section(".Code_Mod.AvDspMod"))) AvDspMod(int cmd)
{
	if (cmd != 190)
		return;
	
	for(int i=0; i< 15; i++)
	{
		uint8_t sendToBD37xxRegister = AvDspEqTableIdx[i];

		if (AvDspEq[i] != 10 && AvDspEq[i] != AvDspEqSaved[i])
		{
			AvDspEqSaved[i] = AvDspEq[i];

			if (sendToBD37xxRegister == INITIAL_SETUP1 || sendToBD37xxRegister == INITIAL_SETUP2 || sendToBD37xxRegister == INITIAL_SETUP3 ||
				sendToBD37xxRegister == INPUT_GAIN || sendToBD37xxRegister == LOUDNESS_GAIN ||
				sendToBD37xxRegister == BASS_SETUP || sendToBD37xxRegister == MIDDLE_SETUP || sendToBD37xxRegister == TREBLE_SETUP)
			{
				SendDbgStrToArmTask("AvEqMod: %d %d", sendToBD37xxRegister, AvDspEqSaved[i]);
				BD37xxRegisters[sendToBD37xxRegister] = AvDspEqSaved[i];
				SendToBD37xx(sendToBD37xxRegister, AvDspEqSaved[i]);
				//SendToBD37xxAll();
			}
		}
	}
}
