package ev3dprinter.motors;

// TODO make a synchronous calibration function for all motors

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintHead {

    final XMotor xAxisMotor;
    final YMotor yAxisMotor;
    final ZMotor zAxisMotor;

    public PrintHead(XMotor xAxisMotor, YMotor yAxisMotor, ZMotor zAxisMotor) {
        this.xAxisMotor = xAxisMotor;
        this.yAxisMotor = yAxisMotor;
        this.zAxisMotor = zAxisMotor;
    }

    Callable<Void> xCalibrate = new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            xAxisMotor.calibrate();
            return null;
        }
    };

    Callable<Void> yCalibrate = new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            yAxisMotor.calibrate();
            return null;
        }
    };

    Callable<Void> zCalibrate = new Callable<Void>() {
        @Override
        public Void call() throws Exception {
            zAxisMotor.calibrate();
            return null;
        }
    };

    public void calibrate() {

        this.zAxisMotor.calibrate();

        List<Callable<Void>> calibrateList = new ArrayList<>();
        calibrateList.add(xCalibrate);
        calibrateList.add(yCalibrate);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        try
        {
            // Start the threads and wait for them to finish
            executor.invokeAll(calibrateList);
            executor.shutdown();
        }
        catch (InterruptedException ie) {

            System.out.println("Unable to start threads");

        }


    }

    // TODO finish gotoPoint function
    public void gotoPoint(float x, float y, float z, float feedSpeed) {

        float xVelocity = xAxisMotor.defaultVelocity;
        float yVelocity = (float) Math.sqrt((feedSpeed*feedSpeed - xVelocity*xVelocity));



    }

}
