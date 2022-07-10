package ev3dprinter.motors;

import ev3dprinter.devices.BaseMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.MirrorMotor;
import lejos.robotics.RegulatedMotor;

public class XMotor extends BaseMotor {
    public XMotor(Port port, Port sensorAddress, float defaultSpeed, float degMmRatio, Boolean ev3TouchSensor) {
        super(port, sensorAddress, defaultSpeed, degMmRatio, ev3TouchSensor);
        // super.offset = -1.3f;

        // super.m = (EV3LargeRegulatedMotor) MirrorMotor.invertMotor(super.m);

    }
}