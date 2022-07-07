package ev3dprinter.devices;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class BaseMotor {

    final TouchSensor touchSensor;

    final EV3LargeRegulatedMotor m;

    final Boolean invert;

    final float position;
    final float offset;
    final float mmDegRatio;
    float studDeg;
    final float defaultSpeed;

    // Used to invert angle inputs and rotation commands to match the motor's orientation.
    float v = 1f;

    public BaseMotor(Port motorAddress, Port sensorAddress, float degRatio, int defaultSpeed, Boolean inverted) {

        this.m = new EV3LargeRegulatedMotor(motorAddress);
        this.touchSensor = new TouchSensor(sensorAddress);

        if (inverted) {
            this.v = -1f;
        }

        this.invert = inverted;
        this.position = 0f;
        this.offset = 0f;

        this.mmDegRatio = degRatio;

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

    public void gotoPosition(float pos, float speed) {

        int deltaDeg = Math.round((pos - this.position) * studDeg);

        this.m.setSpeed(speed);
        this.m.rotate(deltaDeg);
        this.m.setSpeed(defaultSpeed);

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
