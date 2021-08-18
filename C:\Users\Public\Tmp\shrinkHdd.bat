@echo off

echo "Se va a proceder a ejecutar el procedimiento seleccionado."
echo "No cierre la ventana para que se ejecute correctamente."
VBoxManage.exe modifymedium disk %~1 --compact
echo "Proceso finalizado correctamente-"
pause