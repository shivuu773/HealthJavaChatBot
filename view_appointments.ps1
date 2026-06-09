Write-Host "=========================================" -ForegroundColor Cyan
Write-Host "Fetching Appointment Data from SQLite Database..." -ForegroundColor Cyan
Write-Host "=========================================" -ForegroundColor Cyan
& "$env:USERPROFILE\maven\apache-maven-3.9.6\bin\mvn.cmd" exec:java "-Dexec.mainClass=com.healthcare.db.PrintAppointments"
