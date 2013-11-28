package it.unibo.droneMission.smartDevice.android;

import it.unibo.contact.DroneSmartDashboard.DroneSmartDashboard;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.ProgressBar;
import android.widget.TextView;

public class SmartDashboard extends SmartDashboardSupport {
	
	protected Button startMissionButton;
	protected TextView idleTextView;
	protected DroneSmartDashboard contactSystem;

	@Override
	protected void startMission(String inputValue, Bundle b) throws Exception {
		View lay = (View)findViewById(R.id.layoutData);
		lay.setVisibility(View.VISIBLE);
		lay=findViewById(R.id.layoutidle);
		lay.setVisibility(View.GONE);
	}

	@Override
	protected void doJob() {
		
	}

	@Override
	protected void notifyReceived(String inputValue, Bundle b)
			throws Exception {
		if (inputValue.equals("startmission")){
			showMsg("Received \"Mission Started\" from Drone");
//			idleTextView.setText("Drone On Mission");
//			idleTextView.setTextColor(Color.GREEN);
//			startMissionButton.setEnabled(true);
		}
		else{
			endMission("", myBundle);
			showMsg("Received \"Mission Ended\" from Drone");
			wait(3000);
			output.setText("");
//			showMsg("Received \"End Mission\" from Drone");
//			idleTextView.setText("Waiting for Mission");
//			idleTextView.setTextColor(Color.RED);
//			startMissionButton.setEnabled(false);
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		startMissionButton = (Button) findViewById(R.id.startReceivingData);
//		startMissionButton.setEnabled(false);
//		idleTextView = (TextView) findViewById(R.id.idleTextView);
//		idleTextView.setTextColor(Color.RED);
//		View lay = (View)findViewById(R.id.layoutData);
//		lay.setVisibility(View.VISIBLE);
		
		try {
			contactSystem = new DroneSmartDashboard(this);
			contactSystem.doJob();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//just for test
//	@Override
//	protected void bool_OnChanged(boolean checked) {
//		// TODO Auto-generated method stub
//		Button btn = (Button) findViewById(R.id.test);
//		if(checked)
//			btn.setText("start mission");
//		else
//			btn.setText("stop mission");
//		showMsg("onChangeChk");
//	}

	@Override
	protected void endMission(String inputValue, Bundle b) throws Exception {
		AlertDialog.Builder adb = new AlertDialog.Builder(this);
			adb.setMessage("Drone landed.\nWaiting for a new mission.");
			adb.setTitle("Mission ended");
			adb.setIcon(BIND_IMPORTANT);
			adb.setCancelable(false);
			adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	View lay = (View)findViewById(R.id.layoutData);
		       		lay.setVisibility(View.GONE);
		       		lay=findViewById(R.id.layoutidle);
		       		lay.setVisibility(View.VISIBLE);
		       		idleTextView.setText("Waiting for Mission");
					idleTextView.setTextColor(Color.RED);
					startMissionButton.setEnabled(false);
		           }});
		AlertDialog d = adb.create();
		d.show();
		
	}

	@Override
	protected void updateValues(String inputValue, Bundle b) throws Exception {
//		String[] ar=inputValue.split("_=_");
//		TextView tv;
//		if (ar[0].equals("speed")){
//			tv = (TextView)findViewById(R.id.valSpeed);
//			tv.setText(ar[1]);
//		}
//		if (ar[0].equals("fuel")){
//			tv = (TextView)findViewById(R.id.valFuel);
//			tv.setText(ar[1]);
//			ProgressBar pb = (ProgressBar)findViewById(R.id.pbFuel);
//			pb.setProgress(Integer.parseInt(ar[1]));
//			
//		}
//		if (ar[0].equals("odo")){
//			tv = (TextView)findViewById(R.id.valOdo);
//			tv.setText(ar[1]);
//		}
//		if (ar[0].equals("coords")){
//			String[] coords = ar[1].split(";");
//			tv = (TextView)findViewById(R.id.valLat);
//			tv.setText(coords[0]);
//			tv = (TextView)findViewById(R.id.valLong);
//			tv.setText(coords[1]);
//		}
		
	}
	
	//for contact interactions
	
	public void callUpdateValues(String s) throws Exception{
		updateValues(s, myBundle); 
	}
	
	public void callNotify(String s) throws Exception{
		notifyReceived(s, myBundle); 
	}
}
