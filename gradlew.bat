@ECHO OFF
SET "APP_HOME=%~dp0"
SET "CLASSPATH=%APP_HOME%gradle\wrapper\gradle-wrapper.jar"

IF NOT EXIST "%CLASSPATH%" (
  ECHO Missing gradle\wrapper\gradle-wrapper.jar
  EXIT /B 1
)

java -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
