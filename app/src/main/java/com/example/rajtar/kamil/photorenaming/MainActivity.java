package com.example.rajtar.kamil.photorenaming;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main,menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id=item.getItemId();

		//noinspection SimplifiableIfStatement
		if(id==R.id.action_settings)
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
	public static File getAppPublicExternalStorageDir() {
		File appDir = null;

		try {
			appDir = new File(Environment.getExternalStorageDirectory(), "Canon EOS 70D");
			appDir.mkdirs();
		}
		catch (Exception e) { }

		return appDir;
	}
	public static void zamien(View view)
	{
		Log.w("nazwa","Start");
		File photos[]={};
		File folder=getAppPublicExternalStorageDir();
		Log.w("nazwa","poFolderze");
		try
		{
			photos=folder.listFiles(new FilenameFilter()
			{
				public boolean accept(File dir,String name)
				{
					return name.toLowerCase().endsWith(".jpg");
				}
			});
			Arrays.sort(photos);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		for(File photo:photos)
		{
			String nazwa="hasdhksadhj"+photo.getName(); //photo.toString().substring(aa+1,photo.toString().length());
			Log.w("nazwa",nazwa);
			File nowa=new File(folder,nazwa);
			photo.renameTo(nowa);
		}
		try
		{
			photos=folder.listFiles(new FilenameFilter()
			{
				public boolean accept(File dir,String name)
				{
					return name.toLowerCase().endsWith(".jpg");
				}
			});
			Arrays.sort(photos);
		}catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
		int i=100;
		for(File photo:photos)
		{
			//int aa=photo.toString().lastIndexOf("_");
			//if (aa==-1)
			//	continue;
			//Log.w("po aa","das"+aa+"L"+(int)photo.toString().length());

			String nazwa="IMG_0"+Integer.toString(i)+".jpg";//photo.toString().substring(aa+1,photo.toString().length());
			i++;
			Log.w("nazwa",nazwa);
			File nowa=new File(folder,nazwa);
			while(nowa.exists())
			{
				nazwa="IMG_0"+Integer.toString(i)+".jpg";//photo.toString().substring(aa+1,photo.toString().length());
				i++;
				Log.w("nazwa",nazwa);
				nowa=new File(folder,nazwa);
			}
			photo.renameTo(nowa);

		}
		System.exit(0);
	}

}
