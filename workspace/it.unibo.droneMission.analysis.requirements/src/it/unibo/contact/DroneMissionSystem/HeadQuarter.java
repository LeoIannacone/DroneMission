package it.unibo.contact.DroneMissionSystem;

public class HeadQuarter extends HeadQuarterSupport {
		// Just for testing purpose
		private int commandCounter;
		private String sensors_received; 
		private int MAX_CMD = 3;
		
		public HeadQuarter(String s) throws Exception{
			
			super(s);
			commandCounter = 0;
			sensors_received = "";
		}

		@Override
		protected String getCommandToSend() throws Exception {
			String cmd;
			if (commandCounter >= MAX_CMD) {
				cmd = "stop";
			}
			else {
				commandCounter += 1;
				cmd = "setspeed " + ((int) (Math.round(Math.random() * 100)) % 60 + 60); 
			}
			return cmd;
		}

		@Override
		protected void updateDashboard(String dataSensorsReceived)
				throws Exception {
			env.println("DATA RECEIVED: " +dataSensorsReceived);
		}

		@Override
		protected void storePhotoData(String photoReceived) throws Exception {
			env.println("PHOTO RECEIVED: " +photoReceived);
		}

		@Override
		protected void shutdown() throws Exception {
			env.println("MISSION END - SHUTDOWN.");			
		}

		@Override
		protected String getCommandStart() throws Exception {
			return Messages.COMMAND_START;
		}

		@Override
		protected boolean replyIsOk(String reply) throws Exception {
			return reply.equalsIgnoreCase(Messages.REPLY_OK);
		}

		@Override
		protected void storeSensorsData(String sensorsDatasReceived)
				throws Exception {
			sensors_received = sensorsDatasReceived;
		}

		@Override
		protected void showPicturePackage(String photoReceived)
				throws Exception {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected boolean missionIsGoingToEnd() throws Exception {
			return sensors_received.equalsIgnoreCase(Messages.SENSORS_LAST);
		}
}
