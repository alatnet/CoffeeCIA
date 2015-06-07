# CoffeeCIA (A derivative of FunkyCIA)

Features:
- GUI
- Multithreaded (Hard limit to 5 threads)
- Filter Selection.


Issues:
- Takes a while to load the ticket.db. this is due to it having to search for a specific string in the ticket.db file and it's scanning the entire file one byte at a time. Feel free to improve on that part of the program.

How to use the filter:  It is a bastardisation of a mysql query command fit for this program.  Filter command is in this form: "titleid/regex/all WHERE type=type cid=regex SET build personal patch ignore download IGNORE titleid/regex/all"  Regex is a regular expression.  Beginning titleid MUST be a titleid/regex or "all" though can be omitted when using just IGNORE.  "type" is ticket type in lowercase, i.e eshopapp, downloadplaychild, demo, updatepatch, dlc, dsiware, system, dsisystemapp, dsisystemdataarchives, mystery. "type" can be comma separated to select multiple types.  SET command sets what action should be enabled.  ignore and download set commands are NOT used at the same time.

Example filter commands:
- all SET ignore (ignore all tickets)
- IGNORE all (ignore all tickets)
- all WHERE type=system SET download build (download and build all tickets that are system)
- all WHERE type=dlc,demo SET download build patch (download, patch, and build dlc and demo tickets)
- all WHERE cid=00000000 type=eshopapp SET ignore (ignore tickets that have a console id of 00000000 and are a type of eshopapp)

**REQUIRES [make_cdn_cia](https://github.com/ctrdev/ctrsdk/tree/master/tools/make_cdn_cia) TO BUILD CIA FILES!**

**Java Runtime 8 minimum**


Screenshots:
![Alt text](http://s5.postimg.org/76k2zdgav/Screenshot.png)
