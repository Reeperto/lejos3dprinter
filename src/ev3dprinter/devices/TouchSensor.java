package ev3dprinter.devices;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.utility.Delay;

public class TouchSensor extends EV3TouchSensor
{

	public TouchSensor(Port port)
	{
		super(port);
	}
	
	public Boolean isPressed()
	{
		
		float[] press = new float[1];
		super.fetchSample(press, 0);
		
		if(press[0] == 1f)
		{
			return true;
		}
		
		return false;
		
	}

	// Pauses execution until the sensor deactivates
	public void waitUntilRealeased()
	{
		
		float[] press = new float[1];
		super.fetchSample(press, 0);
		
		while (press[0] == 1f) 
		{	
			Delay.msDelay(10);
			super.fetchSample(press, 0);	
		}
	}

	// Pauses execution until the sensor activates
	public void waitUntilPressed()
	{
		
		float[] press = new float[1];
		super.fetchSample(press, 0);
		
		while (press[0] == 0f)
		{	
			Delay.msDelay(10);
			super.fetchSample(press, 0);	
		}
	}
	
	
}
