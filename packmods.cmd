@ECHO off
REM This script will pack all mods and write them to an output directory.
REM On first execution, the script will ask for the steam install location and optionally store it for future use.

IF /I "%1"=="help" GOTO help

IF DEFINED STEAM_LOCATION GOTO ldloc

:promptloc
REM Prompt the user for the Steam install location and store it for future use.
SET /P steam=Enter your Steam install location: || SET steam=C:\Program Files (x86)\Steam
ECHO Storing your Steam install location for future use. You can change it in the future by running this script with the 'nosavedlocation' option. This may take several seconds to complete...
SETX STEAM_LOCATION "%steam%"
GOTO pack

:ldloc
REM Load the Steam install location unless the user specified not to.
IF /I "%1"=="nosavedlocation" GOTO promptloc
SET steam=%STEAM_LOCATION%
GOTO pack

:pack
REM Pack each mod to the output directory.
IF EXIST "%CD%\output" (RMDIR /S /Q output)
MKDIR output

FOR /F "tokens=*" %%G IN ('DIR /B /A:D mods') DO (
	"%steam%\steamapps\common\Starbound\win32\asset_packer.exe" "%CD%\mods\%%G" "%CD%\output\%%G.pak"
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