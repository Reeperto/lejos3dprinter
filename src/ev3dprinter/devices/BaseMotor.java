package ev3dprinter.devices;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class BaseMotor {

    public final TouchSensor touchSensor;

    public final EV3LargeRegulatedMotor m;

    public final Boolean invert;

    public float position;
    public float offset;
    public final float mmDegRatio;
    public final float defaultSpeed;

    // Used to invert angle inputs and rotation commands to match the motor's orientation.
    float v = 1f;

    public BaseMotor(Port motorAddress, Port sensorAddress, float degRatio, int defaultSpeed, Boolean inverted, Boolean ev3) {

        this.m = new EV3LargeRegulatedMotor(motorAddress);
        this.touchSensor = new TouchSensor(sensorAddress, ev3);

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


    public void calibrate() {

        this.forward();
        this.touchSensor.waitUntilPressed();
        this.m.stop();

        this.backward();
        this.touchSensor.waitUntilReleased();
        Delay.msDelay(100);
        this.m.stop();

        this.m.setSpeed(defaultSpeed * 0.2f);
        this.forward();
        this.touchSensor.waitUntilPressed();
        this.m.stop();

        this.m.setSpeed(this.defaultSpeed);

        // Move by inverse offset
        this.gotoPosition(-this.offset, this.defaultSpeed);

        this.position = 0;

    }

    public void home() {



    }

    public void gotoPosition(float pos, float speed) {

        int deltaDeg = Math.round((pos - this.position) * this.mmDegRatio);

        this.m.setSpeed(speed);
        this.m.rotate(deltaDeg);

        this.position = pos;
        this.m.setSpeed(defaultSpeed);

    }

    public void forward() {
        if (this.invert) {
            this.m.backward();
            return;
        }

        this.m.forward();

    }

    public void backward() {
        if (this.invert) {
            this.m.forward();
            return;
        }

        this.m.backward();

    }


}
