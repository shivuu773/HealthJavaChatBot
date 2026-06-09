@echo off
echo =========================================
echo Fetching Appointment Data from SQLite Database...
echo =========================================
call "%USERPROFILE%\maven\apache-maven-3.9.6\bin\mvn.cmd" exec:java "-Dexec.mainClass=com.healthcare.db.PrintAppointments"
pause
