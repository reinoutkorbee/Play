package see.actions;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import see.comm.ChannelObstacleDetected;
import see.events.ObstacleDetected;

public class ToObstacle extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6822166686882775398L;
	private ChannelObstacleDetected obstacleDetected;
	
	@Override
	public void perform(DifferentialPilot pilot, EV3MediumRegulatedMotor mediumMotor) throws Exception {
		ObstacleDetected detected = obstacleDetected.read(); // check if there is an obstacle in front before moving
		if(!detected.occurred()) {
			pilot.travel(-30, true);
			while( pilot.isMoving() ) {
				detected = obstacleDetected.read();
				if(detected.occurred()) {
					System.out.println("Obstacle detected");
					pilot.quickStop();
				}
			}
		}
	}
	
	public void setObstacleDetected(ChannelObstacleDetected obstacleDetected) {
		this.obstacleDetected = obstacleDetected;
	}

	@Override
	public String toString() {
		return "Forward";
	}

}
