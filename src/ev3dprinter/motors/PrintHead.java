package ev3dprinter.motors;

// TODO make a synchronous calibration function for all motors

import ev3dprinter.util.vector3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintHead {

    final XMotor xAxisMotor;
    final YMotor yAxisMotor;
    final ZMotor zAxisMotor;
    public vector3 headPos;
    public vector3 headVel;

    public PrintHead(XMotor xAxisMotor, YMotor yAxisMotor, ZMotor zAxisMotor) {
        this.xAxisMotor = xAxisMotor;
        this.yAxisMotor = yAxisMotor;
        this.zAxisMotor = zAxisMotor;

        this.headPos = new vector3(this.xAxisMotor.position, this.yAxisMotor.position, this.zAxisMotor.position);
        this.headVel = new vector3(0,0,0);
    }

    Callable<Void> xCalibrate = new Callable<Void>() {
        @Override
        public Void call() {
            xAxisMotor.calibrate();
            return null;
        }
    };

    Callable<Void> yCalibrate = new Callable<Void>() {
        @Override
        public Void call() {
            yAxisMotor.calibrate();
            return null;
        }
    };

    Callable<Void> zCalibrate = new Callable<Void>() {
        @Override
        public Void call() {
            zAxisMotor.calibrate();
            return null;
        }
    };

    Callable<Void> xMove = new Callable<Void>() {
        @Override
        public Void call() {
            xAxisMotor.gotoPosition(headPos.x, headVel.x);
            return null;
        }
    };

    Callable<Void> yMove = new Callable<Void>() {
        @Override
        public Void call() {
            yAxisMotor.gotoPosition(headPos.y, headVel.y);
            return null;
        }
    };

    Callable<Void> zMove = new Callable<Void>() {
        @Override
        public Void call() {
            zAxisMotor.gotoPosition(headPos.z, headVel.z);
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

        headVel.x = xAxisMotor.defaultVelocity;
        headVel.y = (float) Math.sqrt((feedSpeed*feedSpeed - headVel.x * headVel.x));
        headPos.set(x,y,z);

        List<Callable<Void>> calibrateList = new ArrayList<>();
        calibrateList.add(xMove);
        calibrateList.add(yMove);
        calibrateList.add(zMove);

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

}
