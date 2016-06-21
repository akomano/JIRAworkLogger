set JAVA_CLASSPATH="c:\Program Files\Java\jdk1.7.0_21\bin"
set PATH=%JAVA_CLASSPATH%;%PATH%
set CHROME_DRIVER_PATH=C:\Apps\selenium-wd-drivers\chrome\chromedriver.exe
java %* -Dwebdriver.chrome.driver=%CHROME_DRIVER_PATH% -jar target/mvpreport-1.0-SNAPSHOT-jar-with-dependencies.jar "C:\Users\jjamrich\myProjects\mvpreport\src\test\resources\eu\ibacz\o2sk\reporting\test-claim-data.txt"