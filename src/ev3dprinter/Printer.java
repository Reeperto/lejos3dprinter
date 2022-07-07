package ev3dprinter;

import ev3dprinter.interfaces.PrintHead;
import ev3dprinter.interfaces.XMotor;
import ev3dprinter.interfaces.YMotor;
import ev3dprinter.interfaces.ZMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;

public class Printer {

    static float motorSpeed = 360f;

    static XMotor xAxisMotor = new XMotor(MotorPort.A, SensorPort.S1, 0.3125f, motorSpeed, false);
    static YMotor yAxisMotor = new YMotor(MotorPort.B, SensorPort.S2, 1f / 9f, motorSpeed, false);
    static ZMotor zAxisMotor = new ZMotor(MotorPort.C, SensorPort.S3, 2f / 3f, motorSpeed, false);

    static PrintHead printHead = new PrintHead(xAxisMotor, yAxisMotor, zAxisMotor);

    public static void main(String[] args) {

        xAxisMotor.Calibrate();
        yAxisMotor.Calibrate();
        zAxisMotor.Calibrate();

    }


}
