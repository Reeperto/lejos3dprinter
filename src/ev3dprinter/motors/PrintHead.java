package ev3dprinter.motors;

// TODO make a synchronous calibration function for all motors

import ev3dprinter.util.vector3;
import lejos.robotics.RegulatedMotor;

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
            xAxisMotor.calibrate(true);
            return null;
        }
    };

    Callable<Void> yCalibrate = new Callable<Void>() {
        @Override
        public Void call() {
            yAxisMotor.calibrate(false);
            return null;
        }
    };


    public void calibrate() {

        this.zAxisMotor.calibrate(true);

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

/*
    private void gotoPosition(float pos, float speed) {

        // Convert mm position to degree amount
        int deltaDeg = Math.round((pos - this.position) * degMmRatio);


        this.m.setSpeed(speed * this.degMmRatio);
        this.m.rotate(deltaDeg);

        // Reset motor and update position
        this.m.setSpeed(this.defaultSpeed);
        this.position = pos;

    }
*/

    // TODO finish gotoPoint function
    public void gotoPoint(float x, float y, float z, float feedSpeed) {


        // Proportionality constant to scale both speeds by to produce a constant speed nozzle

        vector3 fPos = new vector3(x,y,z);
        vector3 deltaPos = fPos.sub(this.headPos);

        float k = (float) Math.sqrt((feedSpeed*feedSpeed) / deltaPos.magnitude2());
        System.out.println(k);

        headVel.x = deltaPos.x * k;
        headVel.y = deltaPos.y * k;
        headVel.z = zAxisMotor.defaultVelocity;

        headPos.set(x,y,z);

        System.out.println(headVel.x);
        System.out.println(headVel.y);

        int xDeg = Math.round(deltaPos.x * xAxisMotor.degMmRatio);
        int yDeg = Math.round(deltaPos.y * xAxisMotor.degMmRatio);
        int zDeg = Math.round(deltaPos.z * xAxisMotor.degMmRatio);

        xAxisMotor.m.setSpeed(headVel.x * xAxisMotor.degMmRatio);
        yAxisMotor.m.setSpeed(headVel.y * yAxisMotor.degMmRatio);
        zAxisMotor.m.setSpeed(headVel.z * zAxisMotor.degMmRatio);

        RegulatedMotor[] motorList = new RegulatedMotor[2];
        motorList[0] = (RegulatedMotor) yAxisMotor;
        motorList[1] = (RegulatedMotor) zAxisMotor;


        xAxisMotor.m.synchronizeWith(motorList);

        xAxisMotor.m.rotate(xDeg);
        yAxisMotor.m.rotate(yDeg);
        zAxisMotor.m.rotate(zDeg);

        xAxisMotor.m.endSynchronization();

    }

}
