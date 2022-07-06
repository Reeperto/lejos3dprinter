package ev3dprinter.devices;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class BaseMotor {

    TouchSensor touchSensor;

    EV3LargeRegulatedMotor m;

    Boolean invert;

    float pos;
    float offset;
    float studRot;
    float defaultSpeed;

    // Used to invert angle inputs and rotation commands to match the motor's orientation.
    float v = 1f;

    public BaseMotor(Port motorAddress, Port sensorAddress, float rotRatio, float defaultSpeed, Boolean inverted) {

        this.m = new EV3LargeRegulatedMotor(motorAddress);
        this.touchSensor = new TouchSensor(sensorAddress);

        if (inverted) {
            this.v = -1f;
        }

        this.pos = 0f;
        this.invert = inverted;
        this.offset = 0f;
        this.studRot = rotRatio;
        this.defaultSpeed = defaultSpeed;
        this.m.setSpeed(defaultSpeed);

    }


    public void Calibrate() {

        this.forward();

        touchSensor.waitUntilReleased();

        Delay.msDelay(500);

        this.m.stop();
        this.m.setSpeed(defaultSpeed * 0.2f);
        this.backward();

        touchSensor.waitUntilPressed();

        this.m.stop();

    }

    public void forward() {
        if (invert) {
            this.m.backward();
        }

        this.m.forward();

    }

    public void backward() {
        if (invert) {
            this.m.forward();
        }

        this.m.backward();

    }


}
