@ECHO OFF
TITLE Basic Race Template by DrPvtSkittles
ECHO ==================================================
ECHO IT'S RECOMMENDED TO BACKUP YOUR MOD BEFORE CONTINUING.
ECHO ENTER A NAME FOR YOUR RACE AND HIT ENTER.
ECHO ==================================================
ECHO.
:NAME
SET /P newreplacestr=NAME:

IF defined newreplacestr IF "%newreplacestr:~3,1%"=="" (
	ECHO Please choose a name at least 4 characters in length.
	GOTO:NAME
) ELSE (
	SET search=sktest
	SET replace=%newreplacestr%
)

SETLOCAL EnableDelayedExpansion
ECHO.
ECHO --------------------------------------------------
ECHO THIS COULD TAKE SEVERAL MINUTES DEPENDING ON YOUR SYSTEM.
ECHO DO NOT CLOSE THIS WINDOW.
ECHO --------------------------------------------------
ECHO.
ECHO PLEASE WAIT...

FOR /D /R %%h in ("*!search!*") do (
	SET "File=%%~nxh"
	REN "%%h" "!File:%search%=%replace%!"
) 

FOR /R . %%g in ("*!search!*") do (
	SET "File=%%~nxg"
	REN "%%g" "!File:%search%=%replace%!"
)

FOR /R . %%j in (*.patch, *.species, *.config, *.structure, *.recipes, *.object, *.frames, *.activeitem, *.legs, *.chest, *.codex, *.cinematic, *.disabled) do (
	IF EXIST "%%j" (
		SET /P "=#" <nul
		SET textFile=%%j
		CALL:CONFIGURE
	) ELSE ( ECHO ERROR. NO FILES OR SUB-DIRECTORIES FOUND )
)

ECHO.
ECHO.
ECHO --------------------------------------------------
ECHO THE SETUP HAS SUCCESSFULLY COMPLETED.
ECHO PLEASE CHECK RECIPES AND SHIP BOOSTERS TO MAKE SURE THEY WERE RENAMED
ECHO You may now close this window by pressing any key.
ECHO --------------------------------------------------
PAUSE > nul

:CONFIGURE
FOR /F "delims=" %%i in ('type "%textFile%" ^& break ^> "%textFile%"') do (
	SET "line=%%i"
	set "line=!line:%search%=%replace%!"
	>>"%textFile%" echo(!line!
	)
)
GOTO:EOF