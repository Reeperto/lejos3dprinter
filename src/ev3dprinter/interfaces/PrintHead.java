package ev3dprinter.interfaces;

// TODO make a synchronous calibration function for all motors

public class PrintHead {

    final XMotor xAxisMotor;
    final YMotor yAxisMotor;
    final ZMotor zAxisMotor;

    public PrintHead(XMotor xAxisMotor, YMotor yAxisMotor, ZMotor zAxisMotor) {
        this.xAxisMotor = xAxisMotor;
        this.yAxisMotor = yAxisMotor;
        this.zAxisMotor = zAxisMotor;
    }

    public void calibrate() {



    }

    public void gotoPoint(float x, float y, float z) {

    }

}
