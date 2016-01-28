DroneMission
============

DroneMission is a case study that analyzed and developed a software solution
for Civil Protection, in order to control and drive a UAV. The UAV takes
pictures of the area, bundles them with data from its own sensors, and sends
them back to the control unit  and to the men on the ground who use a Smart
Device Android. The control unit stores drone information received and sends
back commands.

Here we developed all the communication stack (Java), the Drone simulator
(Java) and the  HeadQuarter dashboard (DJango + JavaScript) to control the
Drone. We also implemented a Storage service (Java) which stores all the info
received from the floating object  (such as pictures, sensors data, and more).
