package ev3dprinter.interfaces;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;

public class EMotor {

    public final float mmToDeg = (float) ((360 * 24) / (24.2 * Math.PI));
    public final EV3MediumRegulatedMotor m;
    public final float defaultSpeed;
    public float position;


    public EMotor(Port port, Float defaultSpeed) {

        this.m = new EV3MediumRegulatedMotor(port);
        this.position = 0;
        this.defaultSpeed = defaultSpeed;
        this.m.setSpeed(defaultSpeed);

    }

    public void extrudeMaterial(float materialLength, float fS, float mD, float lH, float nD) {

        float speed = this.convertFeedSpeed(fS, mD, lH, nD);
        this.m.setSpeed(speed);

        float deltaPos = materialLength - this.position;

        this.m.rotate(Math.round(deltaPos * this.mmToDeg));

        this.position = materialLength;

    }

    public void resetPos() { this.position = 0; }

    public float convertFeedSpeed(float fSpeed, float materialDiameter, float layerHeight, float nozzleDiameter) {

        return (float) ((24 * 4.8 * fSpeed * layerHeight * nozzleDiameter) / (Math.PI * materialDiameter)) * this.mmToDeg;

    }

}
