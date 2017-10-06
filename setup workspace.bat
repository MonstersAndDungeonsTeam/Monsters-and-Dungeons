@echo off
title [Monster and Dungeons] Setup Decompiled Workspace
color 0a
type madsetupworkspace.txt
pause >nul
cls
call gradlew setupDecompWorkspace
echo(
echo The script has finished. You might want to run "setup eclipse.bat" now. Press any button to close this window...
pause >nul