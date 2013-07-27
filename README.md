# Introduction
A simple remote app for android devices equipped with an IR blaster compatible with the IRDA system service.

Codes are stored as pronto hex in an xml resource file (res/xml/remotes.xml). Samsung LED LCD tv and VIZIO tv codes are included (cause those are the two tvs I own), the codes seem to be consistant between models going back at least a few years.

This project can be opened with the eclipse android development environment included with the Android SDK.

# Compatibility
## Known compatible devices
* Galaxy Note 8.0

## Untested but probably compatible
* Galaxy Note 10.1
* Galaxy S4

# Resources
* http://www.haxd.me/index.php/2013/03/reversing-samsungs-irdamanager/ - While I had started decompiling the Peel.apk that comes with the Samsung Galaxy Note 8.0, once I saw how obsfucated it was I poked xda developers to see if anyone had done it before, and someone had!  (Note: this link appeared dead on 7/27/2013)
* http://www.remotecentral.com/ - where most of the pronto hex codes came from (Note: most codes were found via forum posts)