# Call Recorder Pro

App records every incoming and outgoing call and upload the recorded calls to defined FTP server. This app runs its function as services in background with any intervantion from user.
This App does not conatin any GUI, Its services automatically starts on lunching app or by using BOOT_COMPLETE intent.

# How to Use

	1. Install the app.
	2. Give permissions by going to the Setting>>Apps>>Call Recorder Pro>> Permissions.
	3. Lunch the App.
	This app does not contain any GUI. So, just lunch and close the app. Its services automatically starts on lunch and boot of device.
	
# Compiling

 Import the project to Android Studio 3.0
 
 The @aykuttasil CallRecorder simple app is used as base to perform call recording function so the call recording operations are same as his app.
 
 
 ```java
callRecord = new CallRecord.Builder(this)
       .setRecordFileName("RecordFileName")
       .setRecordDirName("RecordDirName")
       .setRecordDirPath(Environment.getExternalStorageDirectory().getPath()) // optional & default value
       .setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB) // optional & default value
       .setOutputFormat(MediaRecorder.OutputFormat.AMR_NB) // optional & default value
       .setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION) // optional & default value
       .setShowSeed(true) // optional & default value ->Ex: RecordFileName_incoming.amr || RecordFileName_outgoing.amr
       .build();


callRecord.startCallRecordService();
```
The new functionality added is upload the recorded files to FTP server and delete from local storage.

To use add user FTP server address, User name and Password to SystemUp.java
 Adding FTP Server address
 ```java
  con.connect("Your_FTP_Server_Addess");
 ```
 Adding UserName and Password
 ```java
 con.login("UserName", "Password")
 ```

# Permissions Used
	-Internet
	-Connectivity Change State
	-Phone State Change
	-New outgoing Call
	
# General

This is my first project for android which is i uploaded on the github. So, Feel free to give suggestion for improving the code.

# License
```
Copyright 2018 Sharn25

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```