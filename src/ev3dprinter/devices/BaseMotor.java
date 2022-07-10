package ev3dprinter.devices;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class BaseMotor {

    public final TouchSensor touchSensor;

    public EV3LargeRegulatedMotor m;
    public final float defaultSpeed;
    public final float defaultVelocity;
    public final float degMmRatio;
    public float position;
    public float offset;

    public BaseMotor(Port port, Port sensorAddress, float defaultVelocity, float degMmRatio, Boolean ev3TouchSensor) {

        this.m = new EV3LargeRegulatedMotor(port);
        this.touchSensor = new TouchSensor(sensorAddress, ev3TouchSensor);
        this.defaultVelocity =  defaultVelocity;
        this.defaultSpeed = defaultVelocity * degMmRatio;
        this.position = 0f;
        this.offset = 0f;
        this.degMmRatio = degMmRatio;

    }

    public void calibrate(Boolean invert) {

        float fasterSpeed = 86;

        if(invert) {

            // Move quickly until near sensor, skip if sensor is already pressed
            this.m.setSpeed(2f * fasterSpeed);
            this.m.backward();
            this.touchSensor.waitUntilPressed();
            this.m.stop();

            // Move back enough so there is a gap between the sensor
            this.m.setSpeed(fasterSpeed);
            this.m.forward();
            this.touchSensor.waitUntilReleased();
            Delay.msDelay(100);
            this.m.stop();

            // Move towards sensor slowly until it is pressed and reset position
            this.m.setSpeed(0.2f * fasterSpeed);
            this.m.backward();
            this.touchSensor.waitUntilPressed();
            this.m.stop();

            // Move to (0,0,0) by axis offset
            this.gotoPosition(this.offset, 0.4f * fasterSpeed);

            // Reset position and motor speed
            this.m.setSpeed(fasterSpeed);
            this.position = 0;

            return;

        }

        // Move quickly until near sensor, skip if sensor is already pressed
        this.m.setSpeed(2f * fasterSpeed);
        this.m.forward();
        this.touchSensor.waitUntilPressed();
        this.m.stop();

        // Move back enough so there is a gap between the sensor
        this.m.setSpeed(fasterSpeed);
        this.m.backward();
        this.touchSensor.waitUntilReleased();
        Delay.msDelay(100);
        this.m.stop();

        // Move towards sensor slowly until it is pressed and reset position
        this.m.setSpeed(0.2f * fasterSpeed);
        this.m.forward();
        this.touchSensor.waitUntilPressed();
        this.m.stop();

        // Move to (0,0,0) by axis offset
        this.gotoPosition(this.offset, 0.4f * fasterSpeed);

        // Reset position and motor speed
        this.m.setSpeed(fasterSpeed);
        this.position = 0;

    }

    public void gotoPosition(float pos, float speed) {

        // Convert mm position to degree amount
        int deltaDeg = Math.round((pos - this.position) * degMmRatio);


        this.m.setSpeed(speed * this.degMmRatio);
        this.m.rotate(deltaDeg);

        // Reset motor and update position
        this.m.setSpeed(this.defaultSpeed);
        this.position = pos;

    }



}
