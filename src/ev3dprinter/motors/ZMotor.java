package ev3dprinter.motors;

import ev3dprinter.devices.BaseMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

// TODO Implement a method that converts a millimeter distance along the spool to a degree angle to turn.
// TODO Implement a method to move the print head to a given millimeter height.

public class ZMotor extends BaseMotor {
    public ZMotor(Port port, Port sensorAddress, float defaultSpeed, float degMmRatio, Boolean ev3TouchSensor) {
        super(port, sensorAddress, defaultSpeed, degMmRatio, ev3TouchSensor);
    }
    
    @Override
    public void calibrate() {

        // Move quickly until near sensor, skip if sensor is already pressed
        super.m.setSpeed(2f * defaultSpeed);
        super.m.backward();
        super.touchSensor.waitUntilPressed();
        super.m.stop();

        // Move back enough so there is a gap between the sensor
        super.m.setSpeed(defaultSpeed);
        super.m.forward();
        super.touchSensor.waitUntilReleased();
        Delay.msDelay(100);
        super.m.stop();

        // Move towards sensor slowly until it is pressed and reset position
        super.m.setSpeed(0.2f * defaultSpeed);
        super.m.backward();
        super.touchSensor.waitUntilPressed();
        super.m.stop();

        // Move to (0,0,0) by axis offset
        super.gotoPosition(super.offset, 0.8f * super.defaultSpeed);

        // Reset position and motor speed
        super.m.setSpeed(defaultSpeed);
        super.position = 0;

    }
    
}
