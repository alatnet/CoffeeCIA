# CoffeeCIA (A derivative of FunkyCIA)

Features:
- GUI
- Multithreaded (Hard limit to 5 threads)


Issues:
- Takes a while to load the ticket.db. this is due to it having to search for a specific string in the ticket.db file and it's scanning the entire file one byte at a time. Feel free to improve on that part of the program.
- Select Filter is not operational! It will be a feature later on.


**REQUIRES [make_cdn_cia](https://github.com/ctrdev/ctrsdk/tree/master/tools/make_cdn_cia) TO BUILD CIA FILES!**

**Java Runtime 8 minimum**


Screenshots:
![Alt text](http://s5.postimg.org/4kr2vrr2f/Screenshot.png)
