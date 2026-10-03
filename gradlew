#!/bin/sh

APP_HOME=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

if [ ! -f "$CLASSPATH" ]; then
    echo "Missing gradle/wrapper/gradle-wrapper.jar" >&2
    echo "Run: curl -L -o gradle/wrapper/gradle-wrapper.jar https://repo1.maven.org/maven2/org/gradle/gradle-wrapper/8.10.2/gradle-wrapper-8.10.2.jar" >&2
    exit 1
fi

exec java -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
