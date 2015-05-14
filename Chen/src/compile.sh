if [ ! -d  build ];then
    mkdir build
fi

javac -d build `find src/utils -name *.java`
javac -d build -cp build `find src/meeting -name *.java`
jar cf build/meeting.jar build/utils build/meeting
