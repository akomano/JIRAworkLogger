@echo off
call export_local_env.bat
echo CHROME_DRIVER_PATH: %CHROME_DRIVER_PATH%
echo PATH_TO_INPUT_FILE: %PATH_TO_INPUT_FILE%
echo JVM_PROPS: %JVM_PROPS%

java %JVM_PROPS% -Dwebdriver.chrome.driver=%CHROME_DRIVER_PATH% -jar target/mvpreport-1.0-SNAPSHOT-jar-with-dependencies.jar "%PATH_TO_INPUT_FILE%"