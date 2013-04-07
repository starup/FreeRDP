/*
   Manual Bookmark implementation

   Copyright 2013 Thinstuff Technologies GmbH, Author: Martin Fleisz

   This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. 
   If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
*/

package com.freerdp.freerdpcore.domain;

import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

public class ManualBookmark extends BookmarkBase
{
	private String hostname;
	private int port;
	
	//vpass external info
	private String resId;
	private String commandLine;
	private String rdpHostName;
	private int rdpHostPort;
	private int isVpn;
	
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}
	public String getCommandLine() {
		return commandLine;
	}
	public void setCommandLine(String commandLine) {
		this.commandLine = commandLine;
	}
	public String getRdpHostName() {
		return rdpHostName;
	}
	public void setRdpHostName(String rdpHostName) {
		this.rdpHostName = rdpHostName;
	}
	public int getRdpHostPort() {
		return rdpHostPort;
	}
	public void setRdpHostPort(int rdpHostPort) {
		this.rdpHostPort = rdpHostPort;
	}
	public int getIsVpn() {
		return isVpn;
	}
	public void setIsVpn(int isVpn) {
		this.isVpn = isVpn;
	}

	private void init()
	{
		type = TYPE_MANUAL;
		hostname = "";
		port = 3389;
		
		resId = "";
		commandLine = "";
		rdpHostName = "";
	}
	
	public ManualBookmark(Parcel parcel)
	{
		super(parcel);
		type = TYPE_MANUAL;
		hostname = parcel.readString();
		port = parcel.readInt();	
		
		//vpass
		resId = parcel.readString();
		commandLine = parcel.readString();
		rdpHostName = parcel.readString();
		rdpHostPort = parcel.readInt();
		isVpn = parcel.readInt();
	}

	public ManualBookmark() {
		super();
		init();
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	public String getHostname() {
		return hostname;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return port;
	}

	public static final Parcelable.Creator<ManualBookmark> CREATOR = new Parcelable.Creator<ManualBookmark>()
	{
		public ManualBookmark createFromParcel(Parcel in) {
			return new ManualBookmark(in);
		}

		@Override
		public ManualBookmark[] newArray(int size) {
			return new ManualBookmark[size];
		}
	};
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags)
	{
		super.writeToParcel(out, flags);
		out.writeString(hostname);
		out.writeInt(port);
		
		//vpass
		out.writeString(resId);
		out.writeString(commandLine);
		out.writeString(rdpHostName);
		out.writeInt(rdpHostPort);
		out.writeInt(isVpn);
	}

	@Override
	public void writeToSharedPreferences(SharedPreferences sharedPrefs)
	{
		super.writeToSharedPreferences(sharedPrefs);
		
		SharedPreferences.Editor editor = sharedPrefs.edit();
		editor.putString("bookmark.hostname", hostname);
		editor.putInt("bookmark.port", port);
		editor.commit();		
	}

	@Override
	public void readFromSharedPreferences(SharedPreferences sharedPrefs)
	{
		super.readFromSharedPreferences(sharedPrefs);
		
		hostname = sharedPrefs.getString("bookmark.hostname", "");
		port = sharedPrefs.getInt("bookmark.port", 3389);
	}
	
	// Cloneable
	public Object clone()
	{
		return super.clone();					
	}
}
