#include <stdint.h>
#include <stdarg.h>
#include <stdio.h>

#include "MTCD.h"

uint8_t __attribute__((section(".Data.AvDspEq"),aligned(0x01))) AvDspEq[15];
uint8_t __attribute__((section(".Data.BD37xxRegisters"),aligned(0x01))) BD37xxRegisters[19];

void SendToBD37xx(int registerNumber, int data) __attribute__((section(".Code.SendToBD37xx"),aligned(0x02)));
void SendToBD37xxAll(void) __attribute__((section(".Code.SendToBD37xxAll"),aligned(0x02)));
void SendDbgStrToArmTask(const char *fmt, ...) __attribute__((section(".Code.SendDbgStrToArmTask"),aligned(0x02)));




void SendToBD37xx(int registerNumber, int data) {};
void SendToBD37xxAll() {};
void SendDbgStrToArmTask(const char *fmt, ...)
{
    va_list ap;

    va_start(ap, fmt);
    va_end(ap);
}
