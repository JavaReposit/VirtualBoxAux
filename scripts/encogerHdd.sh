#!/bin/bash

echo "Se va a proceder a ejecutar el procedimiento seleccionado."
echo "No cierre la ventana para que se ejecute correctamente."
VBoxManage modifymedium disk $1 --compact
echo "Proceso finalizado correctamente"
read -p "Press enter to continue"

