call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runcrud
echo.
echo Could not run runcrud.bat
goto fail

:runcrud
start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto success
echo.
echo Cannot run explorer
goto fail

:fail
echo.
echo There were errors
