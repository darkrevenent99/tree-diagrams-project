package com.mma.probability;

import android.app.*;
import android.content.*;
import android.content.res.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import java.io.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Button startBtn = (Button) findViewById(R.id.startBtn);
		ImageView shareBtn = (ImageView) findViewById(R.id.shareBtn);
		shareBtn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					Log.i("clicks", "jdkdjj");
					Intent i = new Intent(MainActivity.this, MainActivity2.class);
					startActivity(i);
					
					}
		});
		
		startBtn.setOnClickListener( new OnClickListener() {

				@Override
				public void onClick(View v) {
					
				 	File dir = new File("/sdcard/Tree Diagrams");
					
					if(!dir.exists())
						{
						dir.mkdir();
						copyAssetFolder(getAssets(), "Tree Diagrams", 
										"/sdcard/Tree Diagrams");
						}
					
			
				  	File file = new File("/sdcard/Tree Diagrams/index.html") ;
					//Uri uri = Uri.parse("intent:///storage/emulated/0/Tree%20Diagrams/index.html#Intent;scheme=file;type=*/*;launchFlags=0x10000000;B.by_open_as=true;end");//"file:///sdcard/Tree Diagrams/index.html") ;
					
					Intent i = new Intent(Intent.ACTION_VIEW);
				 	i.setDataAndType(Uri.fromFile(file), "*/*");
					//i.setClassName("com.android.chrome", "android.intent.action.VIEW");
					startActivity(i);
				}
			});
		
	}
	private static boolean copyAssetFolder(AssetManager assetManager,
										   String fromAssetPath, String toPath) {
        try {
            String[] files = assetManager.list(fromAssetPath);
            new File(toPath).mkdirs();
            boolean res = true;
            for (String file : files)
                if (file.contains("."))
                    res &= copyAsset(assetManager, 
									 fromAssetPath + "/" + file,
									 toPath + "/" + file);
                else 
                    res &= copyAssetFolder(assetManager, 
										   fromAssetPath + "/" + file,
										   toPath + "/" + file);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean copyAsset(AssetManager assetManager,
									 String fromAssetPath, String toPath) {
        InputStream in = null;
        OutputStream out = null;
        try {
			in = assetManager.open(fromAssetPath);
			new File(toPath).createNewFile();
			out = new FileOutputStream(toPath);
			copyFile(in, out);
			in.close();
			in = null;
			out.flush();
			out.close();
			out = null;
			return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
			out.write(buffer, 0, read);
        }
    }
}
