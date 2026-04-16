
::script windows
@echo off

if "%1"=="" goto help

if /i "%1"=="build" goto build
if /i "%1"=="test" goto test
if /i "%1"=="run" goto run
if /i "%1"=="report" goto report

goto help

:build
echo ===== BUILD =====
call mvnw clean install
goto end

:test
echo ===== TEST =====
call mvnw test
goto end

:run
echo ===== RUN =====
call mvnw spring-boot:run
goto end

:report
echo ===== REPORT =====
call mvnw surefire-report:report

echo Abrindo o relatorio no navegador quando estiver pronto...

timeout /t 5 /nobreak >nul

:waitReport
IF EXIST target\site\surefire-report.html (
    echo Relatorio encontrado!
    start "" target\site\surefire-report.html
    goto end
)

timeout /t 5 /nobreak >nul
goto waitReport
:help
echo Uso:
echo run build   ^> builda e gera o jar
echo run test    ^> roda testes
echo run run     ^> executa a aplicacao
echo run report  ^> gera relatorio de testes 

:end