# SpaceGame

## Release 1.0.0

Its a space shooter what did you think?

[446.889s][warning][os,thread] Failed to start thread - pthread_create failed (EAGAIN) for attributes: stacksize: 1024k, guardsize: 4k, detached.
Exception in thread "Thread-0" java.lang.OutOfMemoryError: unable to create native thread: possibly out of memory or process/resource limits reached
	at java.base/java.lang.Thread.start0(Native Method)
	at java.base/java.lang.Thread.start(Thread.java:800)
	at java.desktop/com.sun.media.sound.DirectAudioDevice$DirectClip.open(DirectAudioDevice.java:1058)
	at java.desktop/com.sun.media.sound.DirectAudioDevice$DirectClip.open(DirectAudioDevice.java:1134)
	at Main.Sound.setFile(Sound.java:59)
	at Main.GamePanel.playSE(GamePanel.java:509)
	at rocketship.rocketship.update(rocketship.java:192)
	at Main.GamePanel.update(GamePanel.java:206)
	at Main.GamePanel.run(GamePanel.java:192)
	at java.base/java.lang.Thread.run(Thread.java:831)
