package ev3dprinter.interfaces;

import ev3dprinter.devices.BaseMotor;
import lejos.hardware.port.Port;

public class YMotor extends BaseMotor
{

	public YMotor(Port motorAddress, Port sensorAddress, float defaultSpeed, Boolean inverted) {
		super(motorAddress, sensorAddress, defaultSpeed, inverted);
	}
	
	
}

//public class YMotor
//{
//	
//	TouchSensor touchSensor = new TouchSensor(SensorPort.S2);
//	
//	EV3LargeRegulatedMotor m;
//	
//	Boolean invert;
//	
//	float pos;
//	float offset;
//	float studRot;
//	float defaultSpeed;
//	
//	// Used to invert angle inputs and rotation commands
//	float v = 1f;
//	
//	public YMotor(Port address, float defaultSpeed, Boolean inverted)
//	{
//	    
//		// For the y-axis motor, 1 full rotation = 9 studs, therefore 1 stud = 0.111... rotations	
//		
//		if(inverted)
//		{
//			this.v = -1f;
//		}
//		
//		this.pos = 0f;
//		this.invert = inverted;
//		this.offset = 0f;
//		this.studRot = 1f / 9f;
//		this.defaultSpeed = defaultSpeed;
//		this.m.setSpeed(defaultSpeed);
//		
//	}	
//	
//	
//	public void Calibrate()
//	{
//		
//		this.forward();
//		
//		touchSensor.waitUntilRealeased();
//		
//		Delay.msDelay(500);
//		
//		this.m.stop();
//		this.m.setSpeed(defaultSpeed * 0.2f);
//		this.backward();
//		
//		touchSensor.waitUntilPressed();
//		
//		this.m.stop();
//		
//	}
//
//	public void forward()
//	{
//		if(invert)
//		{
//			this.m.backward();
//		}
//		
//		this.m.forward();
//		
//	}
//
//	public void backward()
//	{
//		if(invert)
//		{
//			this.m.forward();
//		}
//		
//		this.m.backward();
//		
//	}
//		
//	
//}
