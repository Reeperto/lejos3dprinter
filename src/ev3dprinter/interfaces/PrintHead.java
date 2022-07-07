package ev3dprinter.interfaces;

public class PrintHead {

    XMotor xAxisMotor;
    YMotor yAxisMotor;
    ZMotor zAxisMotor;

    public PrintHead(XMotor xAxisMotor, YMotor yAxisMotor, ZMotor zAxisMotor) {
        this.xAxisMotor = xAxisMotor;
        this.yAxisMotor = yAxisMotor;
        this.zAxisMotor = zAxisMotor;
    }

    public void Goto(float x, float y, float z) {

    }

}
