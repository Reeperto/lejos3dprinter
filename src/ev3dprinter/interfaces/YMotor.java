package ev3dprinter.interfaces;

import ev3dprinter.devices.BaseMotor;
import lejos.hardware.port.Port;

public class YMotor extends BaseMotor {
    public YMotor(Port motorAddress, Port sensorAddress, float degRatio, int defaultSpeed, Boolean inverted) {
        super(motorAddress, sensorAddress, degRatio, defaultSpeed, inverted);
    }
}