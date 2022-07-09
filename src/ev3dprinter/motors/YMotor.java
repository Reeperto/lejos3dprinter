package ev3dprinter.motors;

import ev3dprinter.devices.BaseMotor;
import lejos.hardware.port.Port;

public class YMotor extends BaseMotor {
    public YMotor(Port port, Port sensorAddress, float defaultSpeed, float degMmRatio, Boolean ev3TouchSensor) {
        super(port, sensorAddress, defaultSpeed, degMmRatio, ev3TouchSensor);
    }
}