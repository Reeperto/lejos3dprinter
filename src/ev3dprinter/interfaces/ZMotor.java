package ev3dprinter.interfaces;

import ev3dprinter.devices.BaseMotor;
import lejos.hardware.port.Port;

// TODO Implement a method that converts a millimeter distance along the spool to a degree angle to turn.
// TODO Implement a method to move the print head to a given millimeter height.

public class ZMotor extends BaseMotor {
    public ZMotor(Port motorAddress, Port sensorAddress, float degRatio, int defaultSpeed, Boolean inverted) {
        super(motorAddress, sensorAddress, degRatio, defaultSpeed, inverted);
    }
}
