#ifndef __MTCD_H__
#define __MTCD_H__

#include <stdint.h>

#ifdef __cplusplus
 extern "C" {
#endif


extern void SendToBD37xx(int registerNumber, int data);
extern void SendToBD37xxAll(void);

extern void SendDbgStrToArmTask(const char *fmt, ...);

extern uint8_t BD37xxRegisters[19];
extern uint8_t AvDspEq[15];

typedef enum 
{
  INITIAL_SETUP1 	= 0x0,
  INITIAL_SETUP2 	= 0x1,
  INITIAL_SETUP3 	= 0x2,
  INPUT_SELECTOR 	= 0x3,
  INPUT_GAIN 		= 0x4,
  VOLUME_GAIN 		= 0x5,
  FADER_1CH_FRONT 	= 0x6,
  FADER_2CH_FRONT 	= 0x7,
  FADER_1CH_REAR	= 0x8,
  FADER_2CH_REAR 	= 0x9,
  FADER_SUBWOOFER 	= 0xA,
  MIXING 			= 0xB,
  BASS_SETUP 		= 0xC,
  MIDDLE_SETUP 		= 0xD,
  TREBLE_SETUP 		= 0xE,
  BASS_GAIN 		= 0xF,
  MIDDLE_GAIN 		= 0x10,
  TREBLE_GAIN 		= 0x11,
  LOUDNESS_GAIN 	= 0x12,
  SYSTEM_RESET 		= 0x13,
}BD37xxEnum;


#ifdef __cplusplus
}
#endif

#endif
