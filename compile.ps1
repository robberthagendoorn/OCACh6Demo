if (!(Test-Path run)) {
    mkdir run
}
javac -d .\run\ .\src\*.java
