package ev3dprinter.devices;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.AnalogSensor;
import lejos.utility.Delay;

public class TouchSensor extends AnalogSensor {

    final Boolean ev3;

    public TouchSensor(Port p, Boolean ev3) {
        super(p);
        this.ev3 = ev3;
    }

    public Boolean isPressed() {
        if (ev3) {
            return this.port.getPin6() > 2.5F;
        } else {
            return this.port.getPin1() < 2.5F;
        }
    }

    public void waitUntilReleased() {

        while (this.isPressed()) {

            Delay.msDelay(10);

        }

    }

    public void waitUntilPressed() {

        while (!this.isPressed()) {

            Delay.msDelay(10);

        }

    }

}
