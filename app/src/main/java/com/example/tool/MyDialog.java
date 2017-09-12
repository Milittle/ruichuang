package com.example.tool;

import com.example.ruichuang.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyDialog extends Dialog {

	private EditText mEditText;
	private Button mPositiveButton, mNegativeButton;
	private TextView mTitle;
	public MyDialog(Context context) {
		super(context);
		setCustomeDialog();
	}
	private void setCustomeDialog() {
		View view = LayoutInflater.from(getContext()).inflate(R.layout.mydialog, null);
		mEditText=(EditText) view.findViewById(R.id.d_editText);
		mPositiveButton=(Button) view.findViewById(R.id.d_ok);
		mNegativeButton=(Button) view.findViewById(R.id.d_cancel);
		mTitle=(TextView) view.findViewById(R.id.d_textView);
		
		super.setContentView(view);
	}
	
	public EditText getEditText(){
		return mEditText;
	}
	
	public void setText(String editText){
		mEditText.setText(editText);
	}
	
	public void setOnPositiveListener(View.OnClickListener listener){  
		mPositiveButton.setOnClickListener(listener);
    }  
	
	public void setOnNegativeListener(View.OnClickListener listener){  
		mNegativeButton.setOnClickListener(listener);  
    }
	
}
