package br.com.androidzin.launchablesitens;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class HomeScreenDialog extends DialogFragment{

	private int selectedHomeScreen = 0;
	private NoticeDialogListener mListener;
	
	public interface NoticeDialogListener {
        public void onDialogPositiveClick(int selectedHomeScreen);
    }
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Set the dialog title
	    builder.setTitle("Select the homescreen item number")
	           .setSingleChoiceItems(R.array.number_of_screen, selectedHomeScreen, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					selectedHomeScreen = which;
				}
			})
			.setPositiveButton("Ok", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					mListener.onDialogPositiveClick(selectedHomeScreen);
				}
			})
			.setNegativeButton("Cancel", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					HomeScreenDialog.this.getDialog().cancel();
				}
			});

	    return builder.create();
	}

}
