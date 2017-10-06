@echo off
title [Monster and Dungeons] Setup Eclipse Workspace
color 0a
type madsetupeclipse.txt
pause >nul
cls
call gradlew eclipse
echo(
echo The script has finished. Press any button to close this window...
pause >nul