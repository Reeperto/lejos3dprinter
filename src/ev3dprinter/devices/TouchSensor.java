package ev3dprinter.devices;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Delay;

public class TouchSensor extends EV3TouchSensor {

    public TouchSensor(Port port) {
        super(port);
    }

    public Boolean isPressed() {

        float[] press = new float[1];
        super.fetchSample(press, 0);

        return press[0] == 1f;

    }

    // Pauses execution until the sensor deactivates
    public void waitUntilReleased() {

        float[] press = new float[1];
        super.fetchSample(press, 0);

        while (press[0] == 1f) {
            Delay.msDelay(10);
            super.fetchSample(press, 0);
        }
    }

    // Pauses execution until the sensor activates
    public void waitUntilPressed() {

        float[] press = new float[1];
        super.fetchSample(press, 0);

        while (press[0] == 0f) {
            Delay.msDelay(10);
            super.fetchSample(press, 0);
        }
    }

}
