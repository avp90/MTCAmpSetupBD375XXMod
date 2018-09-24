package com.microntek.ampsetup;

public class BD37xx {

    int[] Registers = new int[] {
        0b10110111, // InitialSetup1    AdvancedSwitchOnOff=ON, AdvancedSwitchTimeOfInput=11.2ms, AdvancedSwitchTimeOfMute=3.2ms
        0b10000010, // InitialSetup2    LPFPhase=180°, SubwooferOutput=LPF, SubwooferLPFfc=85Hz
        0b00000001, // InitialSetup3    LoudnessFo=250Hz
        0b10000000, // InputSelector    Full-diffBiasType=Bias
        0b00001010, // InputGain        MuteOnOff=OFF, Gain=10dB
        0b11111111, // VolumeGain       -∞dB
        0b11111111, // Fader1chFront    -∞dB
        0b11111111, // Fader2chFront    -∞dB
        0b11111111, // Fader1chRear     -∞dB
        0b11111111, // Fader2chRear     -∞dB
        0b11111111, // FaderSubwoofer   -∞dB
        0b11111111, // Mixing           Mixing=Off
        0b00000000, // BassSetup        BassQ=0.5, BassF=60Hz
        0b00000000, // MiddleSetup      MiddleQ=0.75 MiddleF=500Hz
        0b00110000, // TrebleSetup      TrebleQ=0.75, TrebleF=15kHz
        0b00000000, // BassGain         0dB
        0b00000000, // MiddleGain       0dB
        0b00000000, // TrebleGain       0dB
        0b00000111  // LoudnessGain     Hicut=1, Gain=7dB
    };

    protected int[] IndexesToAvDsp = new int[] {
        BD37xxEnum.InitialSetup1,
        BD37xxEnum.InitialSetup2,
        BD37xxEnum.InitialSetup3,
        BD37xxEnum.InputSelector,
        BD37xxEnum.InputGain,
        BD37xxEnum.VolumeGain,
        BD37xxEnum.Fader1chFront,
        BD37xxEnum.Fader2chFront,
        BD37xxEnum.Fader1chRear,
        BD37xxEnum.Fader2chRear,
        BD37xxEnum.Mixing,
        BD37xxEnum.BassSetup,
        BD37xxEnum.MiddleSetup,
        BD37xxEnum.TrebleSetup,
        BD37xxEnum.LoudnessGain
    };


    public int getInputG() {
        return (Registers[BD37xxEnum.InputGain] & 0b00011111);
    }
    public void putInputG(int value) {
        Registers[BD37xxEnum.InputGain] &= 0b11100000;
        Registers[BD37xxEnum.InputGain] |= (value & 0b00011111);
    }
    public int getMuteOnOff() {
        return (Registers[BD37xxEnum.InputGain] & 0b10000000) >> 7;
    }
    public void putMuteOnOff(int value) {
        Registers[BD37xxEnum.InputGain] &= 0b01111111;
        Registers[BD37xxEnum.InputGain] |= ((value & 0b00000001) << 7);
    }

    public int getBassF() {
        return (Registers[BD37xxEnum.BassSetup] & 0b00110000) >> 4;
    }
    public void putBassF(int value) {
        Registers[BD37xxEnum.BassSetup] &= 0b11001111;
        Registers[BD37xxEnum.BassSetup] |= ((value & 0b00000011) << 4);
    }

    public int getBassQ() {
        return (Registers[BD37xxEnum.BassSetup] & 0b00000011);
    }
    public void putBassQ(int value) {
        Registers[BD37xxEnum.BassSetup] &= 0b11111100;
        Registers[BD37xxEnum.BassSetup] |= (value & 0b00000011);
    }

    public int getMiddleF() {
        return (Registers[BD37xxEnum.MiddleSetup] & 0b00110000) >> 4;
    }
    public void putMiddleF(int value) {
        Registers[BD37xxEnum.MiddleSetup] &= 0b11001111;
        Registers[BD37xxEnum.MiddleSetup] |= ((value & 0b00000011) << 4);
    }

    public int getMiddleQ() {
        return (Registers[BD37xxEnum.MiddleSetup] & 0b00000011);
    }
    public void putMiddleQ(int value) {
        Registers[BD37xxEnum.MiddleSetup] &= 0b11111100;
        Registers[BD37xxEnum.MiddleSetup] |= (value & 0b00000011);
    }

    public int getTrebleF() {
        return (Registers[BD37xxEnum.TrebleSetup] & 0b00110000) >> 4;
    }
    public void putTrebleF(int value) {
        Registers[BD37xxEnum.TrebleSetup] &= 0b11001111;
        Registers[BD37xxEnum.TrebleSetup] |= ((value & 0b00000011) << 4);
    }

    public int getTrebleQ() {
        return (Registers[BD37xxEnum.TrebleSetup] & 0b00000001);
    }
    public void putTrebleQ(int value) {
        Registers[BD37xxEnum.TrebleSetup] &= 0b11111110;
        Registers[BD37xxEnum.TrebleSetup] |= (value & 0b00000001);
    }

    public int getSubCutOff() {
        return (Registers[BD37xxEnum.InitialSetup2] & 0b00000111);
    }
    public void putSubCutOff(int value) {
        Registers[BD37xxEnum.InitialSetup2] &= 0b11111000;
        Registers[BD37xxEnum.InitialSetup2] |= (value & 0b00000111);
    }
    public int getSubOut() {
        return (Registers[BD37xxEnum.InitialSetup2] & 0b00110000) >> 4;
    }
    public void putSubOut(int value) {
        Registers[BD37xxEnum.InitialSetup2] &= 0b11001111;
        Registers[BD37xxEnum.InitialSetup2] |= (value & 0b00000011) << 4;
    }
    public int getSubPhase() {
        return (Registers[BD37xxEnum.InitialSetup2] & 0b10000000) >> 7;
    }
    public void putSubPhase(int value) {
        Registers[BD37xxEnum.InitialSetup2] &= 0b01111111;
        Registers[BD37xxEnum.InitialSetup2] |= (value & 0b00000001) << 7;
    }

    public int getLoudnessG() { return (Registers[BD37xxEnum.LoudnessGain] & 0b00011111); }
    public void putLoudnessG(int value) {
        Registers[BD37xxEnum.LoudnessGain] &= 0b11100000;
        Registers[BD37xxEnum.LoudnessGain] |= (value & 0b00011111);
    }
    public int getLoudnessH() { return (Registers[BD37xxEnum.LoudnessGain] & 0b01100000) >> 5; }
    public void putLoudnessH(int value) {
        Registers[BD37xxEnum.LoudnessGain] &= 0b10011111;
        Registers[BD37xxEnum.LoudnessGain] |= (value & 0b00000011) << 5;
    }
    public int getLoudnessF() { return (Registers[BD37xxEnum.InitialSetup3] & 0b00011000) >> 3; }
    public void putLoudnessF(int value) {
        Registers[BD37xxEnum.InitialSetup3] &= 0b11100111;
        Registers[BD37xxEnum.InitialSetup3] |= (value & 0b00000011) << 3;
    }
}
