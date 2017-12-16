@ECHO off
REM This script will pack all mods and write them to an output directory.
REM On first execution, the script will ask for the Starbound install location and optionally store it for future use.

IF /I "%1"=="help" GOTO help

IF EXIST .starboundlocation.txt GOTO ldloc

:promptloc
REM Prompt the user for the Starbound install location and store it for future use.
SET /P starbound=Enter your Starbound install location: || SET steam="C:\Program Files (x86)\Steam\steamapps\common\Starbound"
ECHO Storing your Starbound install location for future use. You can change it in the future by running this script with the 'nosavedlocation' option.
IF EXIST .starboundlocation.txt DEL .starboundlocation.txt
ECHO %starbound% > .starboundlocation.txt
ATTRIB +h .starboundlocation.txt
GOTO pack

:ldloc
REM Load the Starbound install location unless the user specified not to.
IF /I "%1"=="nosavedlocation" GOTO promptloc
SET /P starbound=<.starboundlocation.txt
GOTO pack

:pack
REM Pack each mod to the output directory.
IF EXIST "%CD%\output" (RMDIR /S /Q output)
MKDIR output

FOR /F "tokens=*" %%G IN ('DIR /B /A:D mods') DO (
	"%starbound%\win32\asset_packer.exe" "%CD%\mods\%%G" "%CD%\output\%%G.pak"
)
PAUSE
GOTO end

:help
REM Display help and exit.
ECHO Command line options:
ECHO help            - Display this message and exit.
ECHO nosavedlocation - Prompt for a location instead of using the stored one.

:end
EXIT