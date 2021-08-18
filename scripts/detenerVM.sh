#!/bin/bash

echo "Deteniendo la máquina "$1 
vboxmanage controlvm $1 savestate
echo $1" ha sido detenida correctamente"
