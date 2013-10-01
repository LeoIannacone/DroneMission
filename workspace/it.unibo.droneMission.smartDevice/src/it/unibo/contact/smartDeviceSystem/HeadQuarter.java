package it.unibo.contact.SmartDeviceSystem;

public class HeadQuarter extends HeadQuarterSupport {
		// Just for testing purpose
		private int commandCounter;
		private int MAX_CMD = 10;
		
		public HeadQuarter(String s) throws Exception{
			
			super(s);
			commandCounter = 0;
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
}
