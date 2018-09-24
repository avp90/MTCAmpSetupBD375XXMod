/*** GS 2.88  ********************
sendToBD37xx        = 0x08004F64;
sendToBD37xxAll     = 0x0800FCAC;
sendDbgStrToArmTask = 0x0801DCE8;

avDspEq         = 0x200029E6;
bd37xxRegisters = 0x20005B50;
**********************************/

/*** GS 2.93  ********************/
sendToBD37xx        = 0x08004F64;
sendToBD37xxAll     = 0x0800FCE8;
sendDbgStrToArmTask = 0x0801E02C;

avDspEq         = 0x20002A33;
bd37xxRegisters = 0x20005BA8;
/**********************************/

/*** HA 2.88  ********************
sendToBD37xx        = 0x08004F64;
sendToBD37xxAll     = 0x0800FCAC;
sendDbgStrToArmTask = 0x0801DCF4;

avDspEq         = 0x200029EE;
bd37xxRegisters = 0x20005B58;
**********************************/
/*** HA 2.89  ********************
sendToBD37xx        = 0x08004F64;
sendToBD37xxAll     = 0x0800FCA8;
sendDbgStrToArmTask = 0x0801DE2C;

avDspEq         = 0x20002A26;
bd37xxRegisters = 0x20005B90;
**********************************/

/*** HXD 2.86  ********************
sendToBD37xx        = 0x08004F64;
sendToBD37xxAll     = 0x0800FC7C;
sendDbgStrToArmTask = 0x0801DB80;

avDspEq         = 0x200029AE;
bd37xxRegisters = 0x20005B18;
**********************************/

/*** KLD 2.88  ********************
sendToBD37xx        = 0x08004F64;
sendToBD37xxAll     = 0x0800FCAC;
sendDbgStrToArmTask = 0x0801DD04;

avDspEq         = 0x200029EE;
bd37xxRegisters = 0x20005B58;
**********************************/

/*** LM 2.88  ********************
sendToBD37xx        = 0x08004F64;
sendToBD37xxAll     = 0x0800FCAC;
sendDbgStrToArmTask = 0x0801DCFC;

avDspEq         = 0x200029E6;
bd37xxRegisters = 0x20005B50;
**********************************/

/*** MX 2.90  ********************
sendToBD37xx        = 0x08004F64;
sendToBD37xxAll     = 0x0800FCE8;
sendDbgStrToArmTask = 0x0801DF98;

avDspEq         = 0x20002A33;
bd37xxRegisters = 0x20005BA0;
**********************************/
/*** MX 2.91  ********************
sendToBD37xx        = 0x08004F64;
sendToBD37xxAll     = 0x0800FCE8;
sendDbgStrToArmTask = 0x0801DFDC;

avDspEq         = 0x20002A33;
bd37xxRegisters = 0x20005BA0;
**********************************/

/*** XRC 2.84  ********************
sendToBD37xx        = 0x08004F64;
sendToBD37xxAll     = 0x0800FD68;
sendDbgStrToArmTask = 0x0801DA30;

avDspEq         = 0x200029AE;
bd37xxRegisters = 0x20005B18;
**********************************/

modCode = 0x0803D000;
modData = 0x20007000;



/*****************************************************************************/
PROVIDE(_sendToBD37xx        = sendToBD37xx - ORIGIN(FLASH));
PROVIDE(_sendToBD37xxAll     = sendToBD37xxAll - ORIGIN(FLASH));
PROVIDE(_sendDbgStrToArmTask = sendDbgStrToArmTask - ORIGIN(FLASH));
PROVIDE(_modCode = modCode - ORIGIN(FLASH));


/* Specify the memory areas */
MEMORY
{
  FLASH (rx)     : ORIGIN = 0x08004000, LENGTH = 240K
  RAM (rw)       : ORIGIN = 0x20000000, LENGTH = 32K
}

SECTIONS
{
  .code :
  {
    . = ALIGN(2);
    
    . = _sendToBD37xx;
    *(.Code.SendToBD37xx);
    . = _sendToBD37xxAll;
    *(.Code.SendToBD37xxAll);
    . = _sendDbgStrToArmTask;
    *(.Code.SendDbgStrToArmTask);
    
    . = _modCode;
    *(.Code_Mod.AvDspMod)
    *(.Data_Mod.AvDspEqTableIdx)
    *(.Code_Mod*)
        
    *(.rodata)
    *(.rodata*)

    *(.text)
    *(.text*)

    . = ALIGN(2);
  } >FLASH


  .data1 avDspEq (NOLOAD) : { . = ALIGN(1); *(.Data.AvDspEq); . = ALIGN(1); } >RAM AT> FLASH
  .data2 bd37xxRegisters (NOLOAD) : { . = ALIGN(1); *(.Data.BD37xxRegisters); . = ALIGN(1); } >RAM AT> FLASH
  .data3 modData (NOLOAD) :
  {
    . = ALIGN(1);
    *(.Data_Mod.AvDspEqSaved); 
    
    *(.data)
    *(.data*)
	. = ALIGN(1);
  } >RAM AT> FLASH

  .ARM.attributes 0 : { *(.ARM.attributes) }
}


