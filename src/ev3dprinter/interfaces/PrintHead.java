package ev3dprinter.interfaces;

public class PrintHead {

    final XMotor xAxisMotor;
    final YMotor yAxisMotor;
    final ZMotor zAxisMotor;

    public PrintHead(XMotor xAxisMotor, YMotor yAxisMotor, ZMotor zAxisMotor) {
        this.xAxisMotor = xAxisMotor;
        this.yAxisMotor = yAxisMotor;
        this.zAxisMotor = zAxisMotor;
    }

    public void Goto(float x, float y, float z) {

    }

}
