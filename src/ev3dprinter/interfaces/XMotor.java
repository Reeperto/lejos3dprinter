package ev3dprinter.interfaces;

import ev3dprinter.devices.BaseMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class XMotor extends BaseMotor {

    public XMotor(Port motorAddress, Port sensorAddress, float degRatio, int defaultSpeed, Boolean inverted, Boolean ev3) {
        super(motorAddress, sensorAddress, degRatio, defaultSpeed, inverted, ev3);
        super.offset = 104f;
    }

}